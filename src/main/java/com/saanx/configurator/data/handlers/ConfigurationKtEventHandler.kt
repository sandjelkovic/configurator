package com.saanx.configurator.data.handlers

import com.saanx.configurator.data.entity.ConfigurationKt
import com.saanx.configurator.data.repository.UserRepository
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.context.SecurityContextHolder

/**
 * @author sandjelkovic
 * @date 22.7.17.
 */
@RepositoryEventHandler(ConfigurationKt::class)
class ConfigurationKtEventHandler(val userRepository: UserRepository) {

    @HandleBeforeCreate
    fun handleConfigurationCreate(configuration: ConfigurationKt) {
        userRepository.findOneByUsername(SecurityContextHolder.getContext()?.authentication?.name)
                .ifPresent { configuration.user = it }
    }
}
