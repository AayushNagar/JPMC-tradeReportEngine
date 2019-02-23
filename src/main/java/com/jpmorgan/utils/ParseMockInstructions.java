package com.jpmorgan.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.customExceptions.TradeInputFileNotFoundException;
import com.jpmorgan.model.TradeInstruction;

/**
 * @author aayushnagar Parse util to read trade instructions file from resources
 */
public class ParseMockInstructions {

	public static TradeInstruction[] getMockInstructions(String sampleInput) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		TradeInstruction[] tradeInstructions;
		try {
			tradeInstructions = mapper.readValue(ParseMockInstructions.class.getResourceAsStream(sampleInput),
					TradeInstruction[].class);
		} catch (IOException e) {
			throw new TradeInputFileNotFoundException(
					"Sample trade instruction file missing in path or could not be read", e);
		}

		return tradeInstructions;
	}

}
