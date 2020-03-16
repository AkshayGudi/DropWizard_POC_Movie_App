package org.movie.utility;

import java.util.List;

public class StringListComparator extends AbstractComparator<List<String>, List<String>> {

	@Override
	public List<String> getValueList(List<String> compareValues) {
		return compareValues;
	}

	@Override
	public List<String> getFieldValue(Object fieldValue) {
		List<String> fieldValues = (List<String>) fieldValue;
		return fieldValues;
	}

	@Override
	public Boolean compare(Object fieldValue, List<String> comparisonValues, String operator) {

		Boolean passed = Boolean.FALSE;

		List<String> stringListValues = this.getFieldValue(fieldValue);
		List<String> stringListComparisonValues = this.getValueList(comparisonValues);

		if (operator != null) {
			CompareOperator valueOf = CompareOperator.valueOf(operator.toUpperCase());
			switch (valueOf) {

			case IN:
				for (String compareValues : stringListComparisonValues) {
					if (stringListValues.stream().anyMatch(fieldVal -> fieldVal.equalsIgnoreCase(compareValues.substring(1, compareValues.length()-1)))) {
						passed = Boolean.TRUE;
						break;
					}
				}
			default:
				break;

			}

		} else {
			passed = Boolean.TRUE;
			for (String eachComparisonValue : stringListComparisonValues) {
				if (!stringListValues.contains(eachComparisonValue)) {
					passed = Boolean.FALSE;
					break;
				}
			}
		}

		return passed;
	}

}
