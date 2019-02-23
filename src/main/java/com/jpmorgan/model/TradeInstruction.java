package com.jpmorgan.model;

import java.util.Date;

/**
 *  @author aayushnagar Simple Trade Instruction POJO.
 */

public final class TradeInstruction {

	private String entity;
	private char orderType;
	private double agreedFx;
	private String currency;

	private Date instructionDate;
	private Date settlementDate;

	private int units;
	private double perUnitPrice;

	public TradeInstruction() {
	}

	public TradeInstruction(String entity, char orderType, double agreedFx, String currency, Date instructionDate,
			Date settlementDate, int units, double perUnitPrice) {
		this.entity = entity;
		this.orderType = orderType;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.perUnitPrice = perUnitPrice;
	}

	public String getEntity() {
		return entity;
	}

	public char getOrderType() {
		return orderType;
	}

	public double getAgreedFx() {
		return agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public double getPerUnitPrice() {
		return perUnitPrice;
	}
}
