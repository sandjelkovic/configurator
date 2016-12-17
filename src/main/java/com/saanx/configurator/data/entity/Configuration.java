package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
public class Configuration extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Version
	@JsonIgnore
	private Long version;

	@NotBlank
	private String name;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private Visibility visibility = Visibility.PRIVATE;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	@RestResource(path = "slots", rel = "slots")
	private List<Slot> slots;

	private User user;

	public Configuration() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Configuration id(final Long id) {
		this.id = id;
		return this;
	}

	public Configuration version(final Long version) {
		this.version = version;
		return this;
	}

	public Configuration name(final String name) {
		this.name = name;
		return this;
	}

	public Configuration description(final String description) {
		this.description = description;
		return this;
	}

	public Configuration visibility(final Visibility visibility) {
		this.visibility = visibility;
		return this;
	}

	public Configuration slots(final List<Slot> slots) {
		this.slots = slots;
		return this;
	}

	public Configuration user(final User user) {
		this.user = user;
		return this;
	}

	@JsonIgnore
	public boolean isPublic() {
		return Visibility.PUBLIC.equals(visibility);
	}

	@JsonIgnore
	public boolean isPrivate() {
		return Visibility.PRIVATE.equals(visibility);
	}

	@Override
	protected Object getInternalId() {
		return id;
	}
}
