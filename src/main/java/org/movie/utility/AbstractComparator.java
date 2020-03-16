package org.movie.utility;

import java.util.List;

public abstract class AbstractComparator<T,V> {

	public abstract V getValueList(List<String> compareValues);
	public abstract T getFieldValue(Object fieldValue);
	
	public abstract Boolean compare(Object fieldValue, List<String> comparisonValues, String operator);
	
}
