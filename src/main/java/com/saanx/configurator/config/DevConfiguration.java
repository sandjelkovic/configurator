package com.saanx.configurator.config;

import com.saanx.ConfiguratorApplication;
import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.entity.SlotEntry;
import com.saanx.configurator.data.entity.User;
import com.saanx.configurator.data.entity.Visibility;
import com.saanx.configurator.data.repository.ConfigurationRepository;
import com.saanx.configurator.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Profile(ConfiguratorApplication.PROFILE_DEV)
public class DevConfiguration {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	@Profile(ConfiguratorApplication.PROFILE_DEV)
	public CommandLineRunner rootUserRefresher() {
		return (args) -> {
			User user = defaultRootUser();
			Optional<User> retrieved = userRepository.findOneByUsername(user.getUsername());
			retrieved.ifPresent(u -> user.setId(u.getId()));
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		};
	}

	@Bean
	@Profile(ConfiguratorApplication.PROFILE_DEV)
	public CommandLineRunner testConfigurationEntities() {
		return (args) -> {
			configurationRepository.save(new com.saanx.configurator.data.entity.Configuration()
					.name("Empty config")
					.visibility(Visibility.PUBLIC));
			configurationRepository.save(new com.saanx.configurator.data.entity.Configuration()
					.name("Prepared config")
					.visibility(Visibility.PUBLIC)
			.slots(createExampleSlots()));
		};
	}

	private List<Slot> createExampleSlots() {
		ArrayList<Slot> slots = new ArrayList<>();
		slots.add(new Slot().name("First slot!")
				.possibleEntries(createExampleEntries()));
		return slots;
	}

	private List<SlotEntry> createExampleEntries() {
		ArrayList<SlotEntry> slotEntries = new ArrayList<>();
		slotEntries.add(new SlotEntry().data("datah")
		.name("entry 1"));
		return slotEntries;
	}

	@Bean("defaultRootUser")
	@Profile(ConfiguratorApplication.PROFILE_DEV)
	@Scope(scopeName = "prototype")
	public User defaultRootUser() {
		return new User()
				.username("root")
				.approved(true)
				.enabled(true)
				.password("password")
				.email("owner@example.com")
				.authorities(Stream.of("ROLE_USER", "ROLE_ADMIN", "ROLE_ROOT")
						.collect(Collectors.toSet()));
	}
}
