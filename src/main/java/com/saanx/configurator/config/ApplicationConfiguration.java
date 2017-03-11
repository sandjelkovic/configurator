package com.saanx.configurator.config;

import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.handlers.ConfigurationEventHandler;
import com.saanx.configurator.data.repository.UserRepository;
import com.saanx.configurator.processor.ConfigurationProcessor;
import com.saanx.configurator.processor.SlotProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import static org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setRepositoryDetectionStrategy(ANNOTATED);
				config.setReturnBodyForPutAndPost(true);
			}
		};
	}

	@Bean
	public ConfigurationEventHandler configurationEventHandler(UserRepository userRepository) {
		return new ConfigurationEventHandler(userRepository);
	}

	@Bean
	public ResourceProcessor<Resource<Slot>> slotProcessor() {
		return new SlotProcessor();
	}

	@Bean
	public ResourceProcessor<Resource<com.saanx.configurator.data.entity.Configuration>> configurationProcessor() {
		return new ConfigurationProcessor();
	}
}
