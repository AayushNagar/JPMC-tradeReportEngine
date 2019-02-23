package com.jpmorgan.tradeReportEngine;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmorgan.model.Entity;
import com.jpmorgan.model.TradeInstructionsReport;
import com.jpmorgan.service.TradeReportService;

/**
 * @author aayushnagar Test cases covered: 1. Correct settlement dates and
 *         values being calculated. 2. Correct rank is calculated for entity 3.
 *         Correct amount is calculated for reports 4. Null checks for report
 */
public class TestTradeReportEngine {

	private static TradeReportService service = TradeReportService.getInstance();
	private static HashMap<String, TradeInstructionsReport> report = null;

	@BeforeClass
	public static void setUp() throws IOException {
		App.main(new String[0]);
		report = service.getReport();
	}

	/**
	 * Test for correct amount for a given settlement day
	 */
	@Test
	public void testSettlementDateAndValues() {
		TradeInstructionsReport instructionsReport = report.get("28 Feb 2019");
		Assert.assertTrue(instructionsReport.getIncomingAmount() == 60423.084);
		Assert.assertTrue(instructionsReport.getOutgoingAmount() == 49001.5183);
		HashMap<String, Entity> incomingEntities = instructionsReport.getIncomingEntities();
		Assert.assertTrue(incomingEntities.size() == 2);
		HashMap<String, Entity> outgoingEntitites = instructionsReport.getOutgoingEntities();
		Assert.assertTrue(outgoingEntitites.size() == 3);
	}

	/**
	 * Test for rank of an entity on a given day
	 */
	@Test
	public void testRanking() {
		TradeInstructionsReport instructionsReport = report.get("27 Feb 2019");
		for (String entity : instructionsReport.getOutgoingEntities().keySet()) {
			Assert.assertTrue(instructionsReport.getOutgoingEntities().get(entity).getRank() == 1);
		}
	}

	/**
	 * Test for total incoming and outgoing amount for the day
	 */
	@Test
	public void testValues() {
		TradeInstructionsReport instructionsReport = report.get("04 Mar 2019");
		Assert.assertTrue(instructionsReport.getIncomingAmount() == 1150705.0);
		Assert.assertTrue(instructionsReport.getOutgoingAmount() == 127882.662);
		HashMap<String, Entity> incomingEntities = instructionsReport.getIncomingEntities();
		Assert.assertTrue(incomingEntities.size() == 2);
		HashMap<String, Entity> outgoingEntitites = instructionsReport.getOutgoingEntities();
		Assert.assertTrue(outgoingEntitites.size() == 2);
	}

	/**
	 * check if instance is not null
	 */
	@Test
	public void testNull() {
		TradeInstructionsReport instructionsReport = report.get(null);
		Assert.assertTrue(instructionsReport == null);
	}
}
