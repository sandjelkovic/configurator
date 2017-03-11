package com.saanx.configurator.data.handlers;

import com.saanx.ConfiguratorApplication;
import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.entity.User;
import com.saanx.configurator.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author ${sandjelkovic}
 * @date 11.3.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = {"testing"})
@SpringBootTest(classes = {ConfiguratorApplication.class})
public class ConfigurationEventHandlerTest {
	@Autowired
	private ConfigurationEventHandler handler;

	@Autowired
	private UserRepository userRepository;

	@Test
	@WithMockUser(username = "User")
	public void handleCreateUserSettingTest() throws Exception {
		userRepository.save(getNewUser1("User"));
		Configuration config = new Configuration().name("Config").description("config description");
		handler.handleConfigurationCreate(config);

		Assert.assertThat(config.getUser(), notNullValue());
	}

	private User getNewUser1(String username) {
		return new User().approved(true).username(username).password("password").email(username + "@example.com").enabled(true)
				.authorities(Stream.of("ROLE_USER").collect(toSet()));
	}

}
