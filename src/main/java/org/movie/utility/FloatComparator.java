package org.movie.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Float implementation of Comparator,
 * In order to compare and filter the attributes of float datatype
 * 
 * @author AkshayGudi
 *
 */
public class FloatComparator extends AbstractComparator<Float,List<Float>> {

	@Override
	public List<Float> getValueList(List<String> compareValues) {
		List<Float> floatCompareValues = new ArrayList<Float>();
		for (String cv : compareValues) {
			floatCompareValues.add(Float.parseFloat(cv));
		}

		return floatCompareValues;
	}

	@Override
	public Float getFieldValue(Object fieldValue) {
		return Float.parseFloat(fieldValue.toString());
	}

	@Override
	public Boolean compare(Object fieldValue, List<String> comparisonValues, String operator) {

		Boolean passed = Boolean.TRUE;

		Float floatFieldValue = this.getFieldValue(fieldValue);
		List<Float> floatComparisonValues = this.getValueList(comparisonValues);

		if (operator != null) {
			CompareOperator valueOf = CompareOperator.valueOf(operator.toUpperCase());
			switch (valueOf) {

			case GTE:
				passed = floatFieldValue >= (floatComparisonValues.get(0));
				break;
			case LTE:
				passed = floatFieldValue <= (floatComparisonValues.get(0));
				break;
			case GT:
				passed = floatFieldValue > (floatComparisonValues.get(0));
				break;
			case LT:
				passed = floatFieldValue < (floatComparisonValues.get(0));
				break;
			default:
				break;
			}

		} else {
			passed = floatFieldValue.equals((floatComparisonValues.get(0)));
		}

		return passed;
	}

}
