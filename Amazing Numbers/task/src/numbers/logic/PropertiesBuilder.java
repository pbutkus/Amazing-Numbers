package numbers.logic;

import numbers.enums.Prop;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PropertiesBuilder {

    private final AmazingNumbers amazingNumbers;

    public PropertiesBuilder() {
        this.amazingNumbers = new AmazingNumbers();
    }

    public ArrayList<String> propertiesOfList(BigInteger num, int amount) {
        ArrayList<String> propertiesList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String properties = propertiesOfNumberForList(num);

            propertiesList.add(properties);
            num = num.add(BigInteger.ONE);
        }

        return propertiesList;
    }

    public ArrayList<String> propertiesOfListWithParameters(BigInteger num, int amount, ArrayList<String> parameters) {
        ArrayList<String> propertiesList = new ArrayList<>();
        String availableProperties = "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
        ArrayList<String> wrongProperties = wrongProperties(parameters);
        if (wrongProperties.size() > 0) {
            String wrongPropertiesAsString = String.join(", ", wrongProperties);

            if (wrongProperties.size() == 1) {
                propertiesList.add("The property [" + wrongPropertiesAsString + "] is wrong.");
            } else {
                propertiesList.add("The properties [" + wrongPropertiesAsString + "] are wrong.");
            }

            propertiesList.add("Available properties: " + availableProperties);

            return propertiesList;
        }

        if ((parameters.size() > 1) && mutuallyExclusive(parameters) != null) {
            String illegal = mutuallyExclusive(parameters);

            propertiesList.add("The request contains mutually exclusive properties: " + illegal);
            propertiesList.add("There are no numbers with these properties.");

            return propertiesList;
        }

        int i = 0;

        while (i < amount) {
            String properties = propertiesOfNumberForList(num);

            for (String parameter : parameters) {
                if (!(properties.contains(parameter))) {
                    break;
                }
            }

            if (excludeProperty(parameters, properties) && matchesProperties(parameters, properties)) {
                propertiesList.add(properties);
                i++;
            }

            num = num.add(BigInteger.ONE);
        }

        return propertiesList;
    }

    public ArrayList<String> propertiesOfSingleNumber(String number) {
        ArrayList<String> properties = new ArrayList<>();

        boolean isHappy = amazingNumbers.isHappy(number);

        properties.add("Properties of " + number);
        properties.add("        even: " + amazingNumbers.isEven(number));
        properties.add("         odd: " + !(amazingNumbers.isEven(number)));
        properties.add("        buzz: " + amazingNumbers.isBuzz(number));
        properties.add("        duck: " + amazingNumbers.isDuck(number));
        properties.add(" palindromic: " + amazingNumbers.isPalindromic(number));
        properties.add("      gapful: " + amazingNumbers.isGapful(number));
        properties.add("         spy: " + amazingNumbers.isSpy(number));
        properties.add("      square: " + amazingNumbers.isSquare(number));
        properties.add("       sunny: " + amazingNumbers.isSunny(number));
        properties.add("     jumping: " + amazingNumbers.isJumping(number));
        properties.add("       happy: " + isHappy);
        properties.add("       sad: " + !(isHappy));

        return properties;
    }

    private String propertiesOfNumberForList(BigInteger num) {
        String numAsString = num.toString();
        String properties = "             " + numAsString + " is ";

        if (amazingNumbers.isEven(numAsString)) {
            properties += "even";
        } else {
            properties += "odd";
        }

        if (amazingNumbers.isBuzz(numAsString)) {
            properties += ", buzz";
        }

        if (amazingNumbers.isDuck(numAsString)) {
            properties += ", duck";
        }

        if (amazingNumbers.isPalindromic(numAsString)) {
            properties += ", palindromic";
        }

        if (amazingNumbers.isGapful(numAsString)) {
            properties += ", gapful";
        }

        if (amazingNumbers.isSpy(numAsString)) {
            properties += ", spy";
        }

        if (amazingNumbers.isSquare(numAsString)) {
            properties += ", square";
        }

        if (amazingNumbers.isSunny(numAsString)) {
            properties += ", sunny";
        }

        if (amazingNumbers.isJumping(numAsString)) {
            properties += ", jumping";
        }

        if (amazingNumbers.isHappy(numAsString)) {
            properties += ", happy";
        } else {
            properties += ", sad";
        }

        return properties;
    }

    private ArrayList<String> wrongProperties(ArrayList<String> properties) {
        ArrayList<String> wrongProperties = new ArrayList<>();

        for (String property : properties) {
            String tempProperty = property;

            if (property.startsWith("-")) {
                tempProperty = tempProperty.substring(1);
            }

            if (!(propEnumContains(tempProperty.toUpperCase()))) {
                wrongProperties.add(tempProperty.toUpperCase());
            }
        }

        return wrongProperties;
    }

    private boolean excludeProperty(ArrayList<String> properties, String number) {
        for (String property : properties) {
            if (property.startsWith("-") && number.contains(property.substring(1).toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    private boolean matchesProperties(ArrayList<String> properties, String number) {
        for (String property : properties) {
            if (!(property.startsWith("-")) && !(number.contains(property))) {
                return false;
            }
        }

        return true;
    }

    private String mutuallyExclusive(ArrayList<String> properties) {
        HashMap<String, Integer> propsMap = new HashMap<>();
        HashMap<Integer, String> propsMap1 = new HashMap<>();

        Set<String> propSet = new HashSet<>();

        propSet.addAll(properties);

        String[] propsWithoutDuplicates = propSet.toArray(String[]::new);

        for (int i = 0; i < propsWithoutDuplicates.length; i++) {
            String property = propsWithoutDuplicates[i].toUpperCase();
            for (Prop prop : Prop.values()) {
                if (property.contains(prop.toString())) {
                    int code = prop.getCode();

                    if (property.startsWith("-")) {
                        code *= - 1;

                        if (property.equals("-SUNNY")) {
                            code *= 10;
                        }
                    }

                    propsMap.putIfAbsent(prop.toString(), code);
                    propsMap1.putIfAbsent(code, property);

                    if (propsMap.size() < i + 1) {
                        return "[" + propsMap1.get(code * -1) + ", " + property + "]";
                    }

                    if (propsMap1.size() < i + 1) {
                        return "[" + propsMap1.get(code) + ", " + property + "]";
                    }

                    break;
                }
            }
        }

        return null;
    }

    public boolean propEnumContains(String value) {
        for (Prop prop : Prop.values()) {
            if (prop.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

}
