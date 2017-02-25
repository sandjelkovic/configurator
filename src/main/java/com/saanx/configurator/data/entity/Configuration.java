package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "configuration", orphanRemoval = true)
	@RestResource(path = "slots", rel = "slots")
	private List<Slot> slots;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	@RestResource(exported = false)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Configuration)) return false;
		if (!super.equals(o)) return false;

		Configuration that = (Configuration) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) return false;
		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
		if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null) return false;
		if (getVisibility() != that.getVisibility()) return false;
		return getSlots() != null ? getSlots().equals(that.getSlots()) : that.getSlots() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + (getVisibility() != null ? getVisibility().hashCode() : 0);
		result = 31 * result + (getSlots() != null ? getSlots().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Configuration{" +
				"id=" + id +
				", version=" + version +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", visibility=" + visibility +
				", slots=" + slots +
				", user=" + user +
				'}';
	}
}
