package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saanx.util.EmptyCollections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Slot extends BasicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Version
	@JsonIgnore
	private Long version;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slot", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<SlotEntry> entries = EmptyCollections.list();
	private Long selectedEntryId;
	private int position; // in Configuration
	@ManyToOne
	@JoinColumn(name = "configurationId")
	private Configuration configuration;

	public Slot() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SlotEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SlotEntry> entries) {
		this.entries = entries;
	}

	public Long getSelectedEntryId() {
		return selectedEntryId;
	}

	public void setSelectedEntryId(Long index) {
		this.selectedEntryId = index;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Slot)) return false;
		if (!super.equals(o)) return false;

		Slot slot = (Slot) o;

		if (getPosition() != slot.getPosition()) return false;
		if (getId() != null ? !getId().equals(slot.getId()) : slot.getId() != null) return false;
		if (getVersion() != null ? !getVersion().equals(slot.getVersion()) : slot.getVersion() != null) return false;
		if (getName() != null ? !getName().equals(slot.getName()) : slot.getName() != null) return false;
		if (getEntries() != null ? !getEntries().equals(slot.getEntries()) : slot.getEntries() != null) return false;
		if (getSelectedEntryId() != null ? !getSelectedEntryId().equals(slot.getSelectedEntryId()) : slot.getSelectedEntryId() != null)
			return false;
		return getConfiguration() != null ? getConfiguration().equals(slot.getConfiguration()) : slot.getConfiguration() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getId() != null ? getId().hashCode() : 0);
		result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getEntries() != null ? getEntries().hashCode() : 0);
		result = 31 * result + (getSelectedEntryId() != null ? getSelectedEntryId().hashCode() : 0);
		result = 31 * result + getPosition();
		result = 31 * result + (getConfiguration() != null ? getConfiguration().hashCode() : 0);
		return result;
	}

	@Override
	protected Object getInternalId() {
		return getId();
	}
}
