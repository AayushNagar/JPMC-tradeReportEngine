package com.jpmorgan.enums;

/**
 * @author aayushnagar Enum declared buying type for an instruction i.e., Buy(B) or Sell(S).
 */

public enum OrderType {

	BUY('B'), SELL('S');

	private final int value;

	OrderType(char value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
