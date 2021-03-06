package com.saanx.configurator.data.entity;

public abstract class BasicEntity {
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BasicEntity)) {
			return false;
		}

		BasicEntity oth = (BasicEntity) other;
		if (getInternalId() == null || oth.getInternalId() == null) {
			return false;
		}
		return getInternalId().equals(oth.getInternalId());
	}

	@Override
	public int hashCode() {
		int result = getInternalId() != null ? getInternalId().hashCode() : 0;
		return result;
	}


	abstract protected Object getInternalId();
}
