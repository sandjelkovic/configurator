package com.saanx.configurator.data.handlers;

import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.repository.UserRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;

@RepositoryEventHandler(Configuration.class)
public class ConfigurationEventHandler {

	private UserRepository userRepository;

	public ConfigurationEventHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@HandleBeforeCreate
	public void handleConfigurationCreate(Configuration configuration) {
		userRepository.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.ifPresent(configuration::setUser);
	}
}
