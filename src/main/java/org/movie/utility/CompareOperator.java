package org.movie.utility;

public enum CompareOperator {

	 GTE(">="), LTE("<="), GT(">"),LT("<"),IN("in");
	  
	  private String symbol;
	  
	  CompareOperator(String symbol) {
	    this.symbol = symbol;
	  }

	  public String getSymbol() {
	    return symbol;
	  }

	  public String toString() {
	    return this.getSymbol();
	  }
	  
	  

	
}
