package com.saanx.configurator.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saanx.util.EmptyCollections;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

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
@Data
public class User extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email
	private String email;

	@JsonIgnore
	@Length(max = 256)
	private String password;

	@Length(min = 3, max = 50)
	private String username;

	@JsonIgnore
	private boolean enabled = true;

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
	protected Object getInternalId() {
		return getId();
	}
}
