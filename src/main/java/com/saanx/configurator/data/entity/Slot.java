package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saanx.util.EmptyCollections;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Slot extends BasicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slot", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<SlotEntry> entries = EmptyCollections.list();
	private int position; // in Configuration
	@ManyToOne
	private Configuration configuration;
	@Version
	@JsonIgnore
	private Long version;

	public BigDecimal getValue() {
		return EmptyCollections.emptyIfNull(getEntries()).stream()
				.map(SlotEntry::getValue)
				.max(BigDecimal::compareTo)
				.orElse(BigDecimal.ZERO);
	}

	public Slot id(final Long id) {
		this.id = id;
		return this;
	}

	public Slot version(final Long version) {
		this.version = version;
		return this;
	}

	public Slot name(final String name) {
		this.name = name;
		return this;
	}

	public Slot entries(final List<SlotEntry> entries) {
		this.entries = entries;
		return this;
	}

	public Slot configuration(final Configuration configuration) {
		this.configuration = configuration;
		return this;
	}

	@Override
	protected Object getInternalId() {
		return getId();
	}
}
