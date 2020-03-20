package com.emsoft.riskwaring.util;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalCountUtil {
	public final static String ADD = "add";
    public final static String DIVIDE = "divide";
    public final static String SUBTRACT = "subtract";
    public final static String MULTIPLY = "multiply";
    
    private BigDecimal count;
    private String message;
    
    
    
    public static BigDecimal count(List<BigDecimal> list,String tag){
    	tag=tag.toLowerCase();
    	BigDecimal count = new BigDecimal("0");
    	switch (tag) {
		case ADD:
			count=add(count,list);
			break;
		case DIVIDE:
			count=divide(count,list);
			break;
		case SUBTRACT:
			count=subtract(count,list);
			break;
		case MULTIPLY:
			count=multiply(count,list);
			break;

		default:
			break;
		}
    	return count;
    }
    private static BigDecimal add(BigDecimal count,List<BigDecimal> list) {
		for (BigDecimal bigDecimal : list) {
			count.add(bigDecimal);
		}
    	return  count;
	}
    private static BigDecimal divide(BigDecimal count,List<BigDecimal> list) {
		for (BigDecimal bigDecimal : list) {
			if (count.compareTo(BigDecimal.ZERO)==0) {
				count=bigDecimal;
				continue;
			}
			if (bigDecimal.compareTo(bigDecimal.ZERO)==0) {
				break;
			}
			count.divide(bigDecimal);
		}
    	return  count;
	}
    private static BigDecimal subtract(BigDecimal count,List<BigDecimal> list) {
		for (int i = 0; i < list.size(); i++) {
			if (i==0) {
				count=list.get(i);
				continue;
			}
			count.subtract(list.get(i));
		}
    	return  count;
	}
    private static BigDecimal multiply(BigDecimal count,List<BigDecimal> list) {
    	count.add(new BigDecimal("1"));
		for (BigDecimal bigDecimal : list) {
			count.multiply(bigDecimal);
		}
    	return  count;
	}
}
