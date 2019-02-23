package com.jpmorgan.model;

/**
 * @author aayushnagar Entity class implementing Comparable Interface to 
 * facilitate ranking based the amount.
 */


public final class Entity implements Comparable<Object> {
	private final String entity;
	private int rank;
	private double amount;

	public Entity(String entity, double amount) {
		this.entity = entity;
		this.amount = amount;
	}

	public String getEntity() {
		return entity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Multiplying by -1 to reverse sort so that higher valuation is at the top
	 */ 
	public int compareTo(Object o) {
		return Double.compare(getAmount(), ((Entity) o).getAmount()) * -1;
	}

}
