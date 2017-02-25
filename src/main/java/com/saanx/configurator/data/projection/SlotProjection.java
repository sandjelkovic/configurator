package com.saanx.configurator.data.projection;

import com.saanx.configurator.data.entity.Slot;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * @author ${sandjelkovic}
 * @date 19.2.17.
 */
@Projection(name = "default", types = {Slot.class})
public interface SlotProjection {
	String getName();

	Long getSelectedEntryId();

	int getPosition();

	BigDecimal getValue();
}
