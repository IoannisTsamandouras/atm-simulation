package com.exciteholidays;

import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.exciteholidays.common.Attributes;
import com.exciteholidays.common.Errors;
import com.exciteholidays.services.AtmService;

import static com.exciteholidays.common.Attributes.error;
import static com.exciteholidays.common.Attributes.fifty;

public class AtmConsoleApplication {

	public static void main(String[] args) {
		AtmService atmService = new AtmService();
		System.out.println("Please enter two numbers seperated by a comma (,) to initialise the ATM app");
		System.out.println("For example, input of 3,4 results in 3x20$ and 4x50$");
		System.out.println("Input a single number to make a withdrawal");
		System.out.println("Input \"exit\" to end the application");
		Scanner scanner = new Scanner(System.in);
		String inputStr = null;
		do {
			System.out.println("Total Amount: " + atmService.getTwenties() + "*20$ + " 
		+ atmService.getFifties() + "*50$ = " + atmService.getTotalAmount() + "$");
			System.out.println("Please enter a command: ");
			inputStr = scanner.nextLine();
			if (!"exit".equals(inputStr)) {
				StringTokenizer tokenizer = new StringTokenizer(inputStr, ",");
				try {
					if (tokenizer.countTokens() == 2) {
						atmService.initialize(new Long(tokenizer.nextToken()), new Long(tokenizer.nextToken()));
					} else {
						Map<String, Long> result = atmService.allocate(new Long(tokenizer.nextToken()));
						if (AtmService.hasError(result)) {
							System.out.println("Error: " + Errors.errors(result.get(error)).getMsg());
						} else {
							System.out.println("Available Amount: " + result.get(Attributes.twenty) + "*20$ and " 
						+ result.get(fifty) + "*50$");
						}
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		} while (!"exit".equals(inputStr));
		scanner.close();
	}
}