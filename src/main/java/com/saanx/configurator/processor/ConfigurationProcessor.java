package com.saanx.configurator.processor;

import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.entity.Slot;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.util.Comparator;
import java.util.List;

/**
 * @author ${sandjelkovic}
 * @date 19.2.17.
 */
public class ConfigurationProcessor implements ResourceProcessor<Resource<Configuration>> {
	@Override
	public Resource<Configuration> process(Resource<Configuration> resource) {
		List<Slot> slotList = resource.getContent().getSlots();
		if (slotList != null) {
			slotList.sort(Comparator.comparing(Slot::getPosition));
		}
		return resource;
	}
}
