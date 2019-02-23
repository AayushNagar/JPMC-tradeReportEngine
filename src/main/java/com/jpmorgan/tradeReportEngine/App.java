package com.jpmorgan.tradeReportEngine;

import java.io.IOException;

import com.jpmorgan.model.TradeInstruction;
import com.jpmorgan.service.ReportPrintingService;
import com.jpmorgan.service.TradeReportService;
import com.jpmorgan.utils.ParseMockInstructions;

/**
 * @author aayushnagar Main entry point for application.
 */
public class App {

	private static TradeReportService service = TradeReportService.getInstance();

	public static void main(String[] args) throws IOException {

		TradeInstruction[] tradeInstructions = ParseMockInstructions
				.getMockInstructions("/sample-trade-instructions.json");
		for (TradeInstruction tradeInstruction : tradeInstructions) {
			service.addTradeInstructionToReport(tradeInstruction);
		}
		service.calculateEntityRank();
		ReportPrintingService.generateReport(service);

	}

}
