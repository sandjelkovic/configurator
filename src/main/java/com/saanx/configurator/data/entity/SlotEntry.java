package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SlotEntry extends BasicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Version
	@JsonIgnore
	private Long version;

	private String name;
	private String data;
	@URL
	private String url;
	private BigDecimal price;
	private int position;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "slot")
	@RestResource(exported = false)
	private Slot slot;

	public SlotEntry() {
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

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
		this.price = price;
		return this;
	}

	public SlotEntry position(final int position) {
		this.position = position;
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
