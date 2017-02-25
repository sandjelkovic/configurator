package com.saanx.configurator.processor;

import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.entity.SlotEntry;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.util.Comparator;
import java.util.List;

/**
 * @author ${sandjelkovic}
 * @date 19.2.17.
 */
public class SlotProcessor implements ResourceProcessor<Resource<Slot>> {
	@Override
	public Resource<Slot> process(Resource<Slot> resource) {
		List<SlotEntry> entryList = resource.getContent().getEntries();
		if (entryList != null) {
			entryList.sort(Comparator.comparing(SlotEntry::getPosition));
		}
		return resource;
	}
}
