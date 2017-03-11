package com.saanx.configurator.processor;

import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.entity.SlotEntry;
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
public class SlotProcessorTest {
	@Test
	public void process() throws Exception {
		List<SlotEntry> slotEntries = Stream.of(new SlotEntry().id(1L).position(10),
				new SlotEntry().id(3L).position(3),
				new SlotEntry().id(2L).position(20),
				new SlotEntry().id(4L).position(1))
				.collect(Collectors.toList());

		Resource<Slot> slotResource = new Resource<>(new Slot().entries(slotEntries));

		SlotProcessor slotProcessor = new SlotProcessor();

		Resource<Slot> processed = slotProcessor.process(slotResource);

		List<SlotEntry> processedEntries = processed.getContent().getEntries();
		assertThat(processedEntries.get(0).getId(), is(4L));
		assertThat(processedEntries.get(1).getId(), is(3L));
		assertThat(processedEntries.get(2).getId(), is(1L));
		assertThat(processedEntries.get(3).getId(), is(2L));
	}

}
