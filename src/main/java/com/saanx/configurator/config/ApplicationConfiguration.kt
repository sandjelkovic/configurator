package com.saanx.configurator.config

import com.saanx.configurator.data.entity.Slot
import com.saanx.configurator.data.handlers.ConfigurationEventHandler
import com.saanx.configurator.data.handlers.ConfigurationKtEventHandler
import com.saanx.configurator.data.repository.UserRepository
import com.saanx.configurator.processor.ConfigurationProcessor
import com.saanx.configurator.processor.SlotProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceProcessor

/**
 * @author sandjelkovic
 * @date 22.7.17.
 */
@Configuration
open class ApplicationConfiguration {

    @Bean
    open fun repositoryRestConfigurer(): RepositoryRestConfigurer {
        return object : RepositoryRestConfigurerAdapter() {
            override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
                if (config != null) {
                    config.repositoryDetectionStrategy = ANNOTATED;
                    config.setReturnBodyForPutAndPost(true)
                }
            }
        }
    }

    @Bean
    open fun slotProcessor(): ResourceProcessor<Resource<Slot>> = SlotProcessor()

    @Bean
    open fun configurationProcessor(): ResourceProcessor<Resource<com.saanx.configurator.data.entity.Configuration>> = ConfigurationProcessor()

    @Bean
    open fun configurationKtEventHandler(userRepository: UserRepository) = ConfigurationKtEventHandler(userRepository)

    @Bean
    open fun configurationEventHandler(userRepository: UserRepository) = ConfigurationEventHandler(userRepository)
}
