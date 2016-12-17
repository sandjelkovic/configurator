package com.saanx.configurator.config;

import com.saanx.configurator.data.handlers.ConfigurationEventHandler;
import com.saanx.configurator.data.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import static org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setRepositoryDetectionStrategy(ANNOTATED);
				config.setReturnBodyOnCreate(true);
			}
		};
	}

	@Bean
	public ConfigurationEventHandler configurationEventHandler(UserRepository userRepository) {
		return new ConfigurationEventHandler(userRepository);
	}
}
