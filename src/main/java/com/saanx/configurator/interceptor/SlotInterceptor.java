package com.saanx.configurator.interceptor;

import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.entity.SlotEntry;
import com.saanx.configurator.exception.InvalidSelectionException;
import com.saanx.util.EmptyCollections;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

/**
 * @author ${sandjelkovic}
 * @date 3.3.17.
 */
@RepositoryEventHandler(Configuration.class)
public class SlotInterceptor {
	@HandleBeforeSave
	public void beforeSave(Slot slot) throws InvalidSelectionException {
		boolean moreThanOneEntrySelected = EmptyCollections.emptyIfNull(slot.getEntries()).stream()
				.filter(SlotEntry::isSelected)
				.count() > 1;
		if (moreThanOneEntrySelected) {
			throw new InvalidSelectionException();
		}
		// move to separate , proper validator
	}
}
