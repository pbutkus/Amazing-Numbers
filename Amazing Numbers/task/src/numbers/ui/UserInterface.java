package numbers.ui;

import numbers.logic.PropertiesBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final PropertiesBuilder propertiesBuilder;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.propertiesBuilder = new PropertiesBuilder();
    }

    public void start() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printInstructions();

        while (true) {
            System.out.println("Enter a request:");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            System.out.println();

            String[] splitInput = input.split(" ");

            if ("0".equals(input)) {
                System.out.println("Goodbye!");
                break;
            }

            if ("".equals(input)) {
                printInstructions();
            }

            try {
                if (new BigInteger(splitInput[0]).compareTo(BigInteger.valueOf(1)) < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else {
                    if (splitInput.length > 1) {
                        if (Integer.parseInt(splitInput[1]) > 0) {
                            if (splitInput.length == 2) {
                                printPropertiesOfList(splitInput);
                            } else {
                                printPropertiesOfListWithGivenProperty(splitInput);
                            }
                        } else {
                            System.out.println("The second parameter should be a natural number.");
                        }
                    } else {
                        printPropertiesOfSingleNumber(input);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("The first parameter should be a natural number or zero.");
            }

            System.out.println();
        }
    }

    private void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private void printPropertiesOfSingleNumber(String input) {
        ArrayList<String> properties = propertiesBuilder.propertiesOfSingleNumber(input);

        properties.forEach(System.out::println);
    }

    private void printPropertiesOfList(String[] input) {
        BigInteger num = new BigInteger(input[0]);
        int amount = Integer.parseInt(input[1]);
        ArrayList<String> propertiesToPrint = propertiesBuilder.propertiesOfList(num, amount);

        propertiesToPrint.forEach(System.out::println);
    }

    private void printPropertiesOfListWithGivenProperty(String[] input) {
        BigInteger num = new BigInteger(input[0]);
        int amount = Integer.parseInt(input[1]);

        ArrayList<String> params = new ArrayList<>(Arrays.asList(input).subList(2, input.length));

        ArrayList<String> propertiesToPrint = propertiesBuilder.propertiesOfListWithParameters(num, amount, params);

        propertiesToPrint.forEach(System.out::println);
    }

}
