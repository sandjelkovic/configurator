package com.saanx.configurator.interceptor;

import com.saanx.configurator.data.entity.Configuration;
import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.entity.SlotEntry;
import com.saanx.configurator.exception.InvalidSelectionException;
import org.junit.Test;

/**
 * @author ${sandjelkovic}
 * @date 3.3.17.
 */
public class SlotInterceptorUnitTest {

	@Test(expected = InvalidSelectionException.class)
	public void beforeSaveTwoSelected() throws Exception, InvalidSelectionException {
		Slot slot = new Slot().configuration(new Configuration());
		slot.getEntries().add(new SlotEntry().selected(true));
		slot.getEntries().add(new SlotEntry().selected(false));
		slot.getEntries().add(new SlotEntry().selected(true));
		SlotInterceptor target = new SlotInterceptor();

		target.beforeSave(slot);
	}

	@Test
	public void beforeSave() throws Exception, InvalidSelectionException {
		Slot slot = new Slot().configuration(new Configuration());
		slot.getEntries().add(new SlotEntry().selected(true));
		slot.getEntries().add(new SlotEntry().selected(false));
		slot.getEntries().add(new SlotEntry().selected(false));
		SlotInterceptor target = new SlotInterceptor();

		target.beforeSave(slot);
	}

	@Test
	public void beforeSaveNoEntries() throws Exception, InvalidSelectionException {
		Slot slot = new Slot().configuration(new Configuration());
		SlotInterceptor target = new SlotInterceptor();

		target.beforeSave(slot);
	}

}
