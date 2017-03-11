package com.saanx.configurator.processor;

import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.entity.Slot;
import org.junit.Test;
import org.springframework.hateoas.Resource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author ${sandjelkovic}
 * @date 11.3.17.
 */
public class ConfigurationProcessorTest {
	@Test
	public void sortingProcessTest() throws Exception {
		List<Slot> slots = Stream.of(new Slot().id(1L).position(10),
				new Slot().id(3L).position(3),
				new Slot().id(2L).position(20),
				new Slot().id(4L).position(1))
				.collect(Collectors.toList());

		Resource<Configuration> configResource = new Resource<>(new Configuration().slots(slots));

		ConfigurationProcessor configurationProcessor = new ConfigurationProcessor();

		Resource<Configuration> processed = configurationProcessor.process(configResource);

		assertThat(processed.getContent().getSlots().get(0).getId(), is(4L));
		assertThat(processed.getContent().getSlots().get(1).getId(), is(3L));
		assertThat(processed.getContent().getSlots().get(2).getId(), is(1L));
		assertThat(processed.getContent().getSlots().get(3).getId(), is(2L));
	}

}
