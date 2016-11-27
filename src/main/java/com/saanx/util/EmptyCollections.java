package com.saanx.util;

import java.util.*;

public class EmptyCollections {
	public static List list() {
		return new ArrayList<>();
	}

	public static Map map() {
		return new HashMap<>();
	}

	public static <T> Set<T> set() {
		return new HashSet<T>();
	}

	public static List emptyIfNull(List list) {
		return (list != null) ? list : list();
	}
}
