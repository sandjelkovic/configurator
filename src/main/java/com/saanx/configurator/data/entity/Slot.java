package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Version
	@JsonIgnore
	private Long version;
	private String name;
	@OneToMany
	private List<SlotEntry> possibleEntries;
	@OneToOne
	private SlotEntry selectedEntry;
	private int position; // in Configuration


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

	public List<SlotEntry> getPossibleEntries() {
		return possibleEntries;
	}

	public void setPossibleEntries(List<SlotEntry> possibleEntries) {
		this.possibleEntries = possibleEntries;
	}

	public SlotEntry getSelectedEntry() {
		return selectedEntry;
	}

	public void setSelectedEntry(SlotEntry selectedEntry) {
		this.selectedEntry = selectedEntry;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
		this.possibleEntries = possibleEntries;
		return this;
	}

	public Slot selectedEntry(final SlotEntry selectedEntry) {
		this.selectedEntry = selectedEntry;
		return this;
	}

	public Slot position(final int position) {
		this.position = position;
		return this;
	}

}
