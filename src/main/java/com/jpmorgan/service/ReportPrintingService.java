package com.jpmorgan.service;

import java.util.HashMap;

import com.jpmorgan.model.Entity;
import com.jpmorgan.model.TradeInstructionsReport;

/**
 * @author aayushnagar Service class to print report to console.
 */

public class ReportPrintingService {

	/**
	 * @param entities
	 * @param direction
	 */
	private static void printReport(HashMap<String, Entity> entities, String direction) {
		int count = 0;
		for (String entityName : entities.keySet()) {
			Entity entity = entities.get(entityName);
			if (entity != null) {
				if (count == 0) {
					System.out.println();
					System.out.format("%25s%25s%25s", "Entity", "Ranking", direction + " Amount");
					System.out.println();
					System.out.format("%25s%25s%25s", "--------", "--------", "------------------");
					count++;
				}
				System.out.println();
				System.out.format("%25s%25s%25s", entity.getEntity(), entity.getRank(), entity.getAmount());
			}
		}
		if (count > 0)
			System.out.println();
	}

	/**
	 * @param service
	 * Print daily reports
	 */
	public static void generateReport(TradeReportService service) {
		HashMap<String, TradeInstructionsReport> report = service.getReport();
		for (String settlementDate : report.keySet()) {
			System.out.println("\n");
			System.out.println("****************  JPMC Daily Trade Report for " + settlementDate +  " *****************");
			System.out.format("%25s%25s%25s", "Settlement Date", "Incoming Amount(USD)", "Outgoing Amount(USD)");
			System.out.println();
			System.out.format("%25s%25s%25s", "-------------------------", "-------------------------",
					"-------------------------");
			TradeInstructionsReport instructionsReport = report.get(settlementDate);
			instructionsReport.getIncomingEntities();
			System.out.println();
			System.out.format("%25s%25s%25s", settlementDate, instructionsReport.getIncomingAmount(),
					instructionsReport.getOutgoingAmount());
			System.out.println();
			printReport(instructionsReport.getIncomingEntities(), "Incoming");
			printReport(instructionsReport.getOutgoingEntities(), "Outgoing");
		}
	}

}
