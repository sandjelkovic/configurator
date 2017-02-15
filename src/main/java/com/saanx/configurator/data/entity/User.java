package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saanx.util.EmptyCollections;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;
import java.util.Set;

@Entity
public class User extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false, length = 100)
	@Email
	private String email;

	@Column(nullable = false, length = 256)
	@JsonIgnore
	private String password;

	@Column(unique = true, nullable = false, length = 50)
	@Length(min = 3, max = 50)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	private boolean enabled = true;

	@Column(nullable = false)
	@JsonIgnore
	private boolean approved = false;

	@ElementCollection(fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<String> authorities = EmptyCollections.set();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Configuration> configurations = EmptyCollections.list();

	@Version
	@JsonIgnore
	private Long version;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public User id(final Long id) {
		this.id = id;
		return this;
	}

	public User email(final String email) {
		this.email = email;
		return this;
	}

	public User password(final String password) {
		this.password = password;
		return this;
	}

	public User username(final String username) {
		this.username = username;
		return this;
	}

	public User enabled(final boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public User approved(final boolean approved) {
		this.approved = approved;
		return this;
	}

	public User authorities(final Set<String> authorities) {
		this.authorities = authorities;
		return this;
	}

	public User configurations(final List<Configuration> configurations) {
		this.configurations = configurations;
		return this;
	}


	public User version(final Long version) {
		this.version = version;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		if (!super.equals(o)) return false;

		User user = (User) o;

		if (isEnabled() != user.isEnabled()) return false;
		if (isApproved() != user.isApproved()) return false;
		if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
		if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
		if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null) return false;
		if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null) return false;
		if (getAuthorities() != null ? !getAuthorities().equals(user.getAuthorities()) : user.getAuthorities() != null) return false;
		return getVersion() != null ? getVersion().equals(user.getVersion()) : user.getVersion() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getId() != null ? getId().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
		result = 31 * result + (isEnabled() ? 1 : 0);
		result = 31 * result + (isApproved() ? 1 : 0);
		result = 31 * result + (getAuthorities() != null ? getAuthorities().hashCode() : 0);
		result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
		return result;
	}

	@Override
	protected Object getInternalId() {
		return getId();
	}
}
