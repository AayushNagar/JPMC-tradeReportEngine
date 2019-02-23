package com.jpmorgan.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.jpmorgan.enums.DayOfWeek;
import com.jpmorgan.model.TradeInstruction;
import com.jpmorgan.model.TradeInstructionsReport;

/**
 * @author aayushnagar
 * Trade Report Service to add incoming trades to reports and calculate rank for entities.
 */

public class TradeReportService {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

	private static HashMap<String, TradeInstructionsReport> report;
	private static TradeReportService instance = null;

	private TradeReportService() {
		report = new HashMap<String, TradeInstructionsReport>();
	}

	public static TradeReportService getInstance() {
		if (instance == null) {
			instance = new TradeReportService();
		}
		return instance;
	}

	/**
	 * @param tradeInstructions
	 * calculate trade instruction's settlement day, AED & SAR settled from Sun-Thu
	 * all other currencies can settle normal Mon-Fri
	 * After calculating settlement day, add instruction to Instruction Report
	 */
	public void addTradeInstructionToReport(TradeInstruction tradeInstructions) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tradeInstructions.getSettlementDate());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (tradeInstructions.getCurrency().equals("AED") || tradeInstructions.getCurrency().equals("SAR")) {
			if (dayOfWeek == DayOfWeek.FRIDAY.getValue())
				calendar.add(Calendar.DATE, 2);
			else if (dayOfWeek == DayOfWeek.SATURDAY.getValue())
				calendar.add(Calendar.DATE, 1);
		} else {
			if (dayOfWeek == DayOfWeek.SATURDAY.getValue())
				calendar.add(Calendar.DATE, 2);
			else if (dayOfWeek == DayOfWeek.SUNDAY.getValue())
				calendar.add(Calendar.DATE, 1);
		}

		String settlementDate = dateFormat.format(calendar.getTime());
		if (settlementDate != null)
			if (report.containsKey(settlementDate))
				report.get(settlementDate).addTradeInstruction(tradeInstructions);
			else
				report.put(settlementDate, new TradeInstructionsReport(tradeInstructions));
	}

	
	/**
	 * Calculate rank of the entity sending trade instruction for both incoming 
	 * and outgoing trades
	 */
	public void calculateEntityRank() {
		for (String settlementDate : report.keySet()) {
			TradeInstructionsReport instructionReport = report.get(settlementDate);
			instructionReport.rankEntities(instructionReport.getIncomingEntities());
			instructionReport.rankEntities(instructionReport.getOutgoingEntities());
		}
	}

	public HashMap<String, TradeInstructionsReport> getReport() {
		return report;
	}

}
