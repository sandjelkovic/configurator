package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
@Data
public class SlotEntry extends BasicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	@JsonIgnore
	private Long version;

	private String name;
	private String data;
	@URL
	private String url;
	private BigDecimal value;
	private boolean selected;
	private int position;
	@JsonIgnore
	@ManyToOne
	@RestResource(exported = false)
	private Slot slot;

	public SlotEntry id(final Long id) {
		this.id = id;
		return this;
	}

	public SlotEntry version(final Long version) {
		this.version = version;
		return this;
	}

	public SlotEntry name(final String name) {
		this.name = name;
		return this;
	}

	public SlotEntry data(final String data) {
		this.data = data;
		return this;
	}

	public SlotEntry url(final String url) {
		this.url = url;
		return this;
	}

	public SlotEntry price(final BigDecimal price) {
		this.value = price;
		return this;
	}

	public SlotEntry position(final int position) {
		this.position = position;
		return this;
	}

	public SlotEntry selected(final boolean selected) {
		this.selected = selected;
		return this;
	}

	public SlotEntry slot(final Slot slot) {
		this.slot = slot;
		return this;
	}

	@Override
	protected Object getInternalId() {
		return getId();
	}
}
