package org.movie.utility;

import java.util.List;

/**
 * String implementation of Comparator,
 * In order to compare and filter the attributes of String datatype
 * 
 * @author AkshayGudi
 *
 */
public class StringComparator extends AbstractComparator<String, List<String>> {

	@Override
	public List<String> getValueList(List<String> compareValues) {
		return compareValues;
	}

	@Override
	public String getFieldValue(Object fieldValue) {
		return fieldValue.toString();
	}

	@Override
	public Boolean compare(Object fieldValue, List<String> comparisonValues, String operator) {

		Boolean passed = Boolean.TRUE;

		String stringFieldValue = this.getFieldValue(fieldValue);
		List<String> stringComparisonValues = this.getValueList(comparisonValues);

		if (operator == null) {
			passed = stringFieldValue.equalsIgnoreCase((stringComparisonValues.get(0)));
		}

		return passed;
	}

}
