package com.jpmorgan.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.jpmorgan.enums.OrderType;

/**
 * @author aayushnagar Simple Trade Instructions Report POJO.
 * 
 */

public class TradeInstructionsReport {

	private double incomingAmount;
	private double outgoingAmount;
	private HashMap<String, Entity> incomingEntities;
	private HashMap<String, Entity> outgoingEntities;

	public TradeInstructionsReport(TradeInstruction tradeInstructions) {
		incomingEntities = new HashMap<String, Entity>();
		outgoingEntities = new HashMap<String, Entity>();
		addTradeInstruction(tradeInstructions);
	}

	public double getIncomingAmount() {
		return incomingAmount;
	}

	public void setIncomingAmount(double incomingAmount) {
		this.incomingAmount = incomingAmount;
	}

	public double getOutgoingAmount() {
		return outgoingAmount;
	}

	public void setOutgoingAmount(double outgoingAmount) {
		this.outgoingAmount = outgoingAmount;
	}

	public HashMap<String, Entity> getIncomingEntities() {
		return incomingEntities;
	}

	public HashMap<String, Entity> getOutgoingEntities() {
		return outgoingEntities;
	}

	/**
	 * @param tradeInstruction
	 * calculate total trade amount
	 */
	public void addTradeInstruction(TradeInstruction tradeInstruction) {
		double tradeAmountInUSD = tradeInstruction.getPerUnitPrice() * tradeInstruction.getUnits()
				* tradeInstruction.getAgreedFx();
		if (OrderType.BUY.getValue() == tradeInstruction.getOrderType()) {
			setOutgoingAmount(getOutgoingAmount() + tradeAmountInUSD);
			setEntityValuation(tradeInstruction.getEntity(), tradeAmountInUSD, getOutgoingEntities());

		} else if (OrderType.SELL.getValue() == tradeInstruction.getOrderType()) {
			setIncomingAmount(getIncomingAmount() + tradeAmountInUSD);
			setEntityValuation(tradeInstruction.getEntity(), tradeAmountInUSD, getIncomingEntities());
		}
	}

	public void rankEntities(HashMap<String, Entity> entities) {
		List<Entity> entityList = new ArrayList<Entity>(entities.values());
		Collections.sort(entityList);
		double previousAmount = 0;
		for (int i = 1, j = 1; i <= entityList.size(); i++) {
			Entity entity = entityList.get(i - 1);
			if (previousAmount > entity.getAmount())
				j = j + 1;
			entity.setRank(j);
			entities.put(entity.getEntity(), entity);
			previousAmount = entity.getAmount();
		}
	}

	private void setEntityValuation(String entityName, double tradeAmountInUSD, HashMap<String, Entity> entities) {
		Entity entity = null;
		if (entities.containsKey(entityName)) {
			entity = entities.get(entityName);
			entity.setAmount(entity.getAmount() + tradeAmountInUSD);
		} else {
			entity = new Entity(entityName, tradeAmountInUSD);
			entities.put(entityName, entity);
		}
	}

}
