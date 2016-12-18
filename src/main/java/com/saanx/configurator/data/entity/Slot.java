package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
	private List<SlotEntry> entries;
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

	public Slot possibleEntries(final List<SlotEntry> possibleEntries) {
		this.entries = possibleEntries;
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
