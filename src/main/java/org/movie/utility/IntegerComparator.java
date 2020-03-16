package org.movie.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Integer implementation of Comparator,
 * In order to compare and filter the attributes of Integer datatype
 * 
 * @author AkshayGudi
 *
 */
public class IntegerComparator extends AbstractComparator<Integer,List<Integer>> {

	@Override
	public List<Integer> getValueList(List<String> compareValues) {
		List<Integer> intCompareValues = new ArrayList<Integer>();
		for (String cv : compareValues) {
			intCompareValues.add(Integer.parseInt(cv));
		}

		return intCompareValues;
	}

	@Override
	public Integer getFieldValue(Object fieldValue) {
		return Integer.parseInt(fieldValue.toString());
	}

	@Override
	public Boolean compare(Object fieldValue, List<String> comparisonValues, String operator) {

		Boolean passed = Boolean.TRUE;

		Integer intFieldValue = this.getFieldValue(fieldValue);
		List<Integer> intComparisonValues = this.getValueList(comparisonValues);

		if (operator != null) {
			CompareOperator valueOf = CompareOperator.valueOf(operator.toUpperCase());
			switch (valueOf) {

			case GTE:
				passed = intFieldValue >= (intComparisonValues.get(0));
				break;
			case LTE:
				passed = intFieldValue <= (intComparisonValues.get(0));
				break;
			case GT:
				passed = intFieldValue > (intComparisonValues.get(0));
				break;
			case LT:
				passed = intFieldValue < (intComparisonValues.get(0));
				break;
			default:
				break;
			}

		} else {
			passed = intFieldValue.equals((intComparisonValues.get(0)));
		}

		return passed;
	}

}
