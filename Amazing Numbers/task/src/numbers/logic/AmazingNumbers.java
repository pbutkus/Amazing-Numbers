package numbers.logic;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class AmazingNumbers {

    public boolean isEven(String number) {
        BigInteger bigNum = new BigInteger(number);

        return bigNum.remainder(BigInteger.valueOf(2)).equals(new BigInteger("0"));
    }

    public boolean isBuzz(String number) {
        BigInteger bigNum = new BigInteger(number);
        boolean endsWithSeven = false;
        boolean divisibleBySeven = false;

        if (number.endsWith("7")) {
            endsWithSeven = true;
        }

        if (bigNum.remainder(BigInteger.valueOf(7)).equals(new BigInteger("0"))) {
            divisibleBySeven = true;
        }

        return endsWithSeven || divisibleBySeven;
    }

    public boolean isDuck(String number) {
        String[] splitNumber = number.split("");

        for (int i = 1; i < splitNumber.length; i++) {
            if ("0".equals(splitNumber[i])) {
                return true;
            }
        }

        return false;
    }

    public boolean isPalindromic(String number) {
        String[] splitNumber = number.split("");
        StringBuilder reversedNumber = new StringBuilder();

        for (int i = splitNumber.length - 1; i >= 0; i--) {
            reversedNumber.append(splitNumber[i]);
        }

        return reversedNumber.toString().equals(number);
    }

    public boolean isGapful(String number) {
        if (number.length() >= 3) {
            BigInteger firstAndLast = new BigInteger((number.charAt(0) + number.substring(number.length() - 1)));
            BigInteger num = new BigInteger(number);

            return num.remainder(firstAndLast).equals(new BigInteger("0"));
        }

        return false;
    }

    public boolean isSpy(String number) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger product = BigInteger.ONE;
        String[] splitNumber = number.split("");

        for (String s : splitNumber) {
            sum = sum.add(new BigInteger(s));
            product = product.multiply(new BigInteger(s));
        }

        return sum.equals(product);
    }

    public boolean isSquare(String number) {
        BigInteger testNum = new BigInteger(number);
        BigInteger sqrt = testNum.sqrt();

        return (testNum.subtract(sqrt.pow(2)).equals(new BigInteger("0")));
    }

    public boolean isSunny(String number) {
        BigInteger num = new BigInteger(number);
        num = num.add(BigInteger.ONE);

        return isSquare(num.toString());
    }

    public boolean isJumping(String number) {
        if (number.length() > 1) {
            for (int i = 0; i < number.length() - 1; i++) {
                int c1 = number.charAt(i);
                int c2 = number.charAt(i + 1);
                int diff = c1 - c2;

                diff *= diff;

                if (diff != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isHappy(String number) {
        String[] splitNumber = number.split("");
        Set<String> numbersSet = new HashSet<>();
        numbersSet.add(number);
        int counter = 1;

        int sum = 0;

        for (int i = 0; i < splitNumber.length; i++) {
            int val = Integer.parseInt(splitNumber[i]);

            val *= val;

            sum += val;
        }

        while (sum > 1) {
            String sumAsString = "" + sum;

            numbersSet.add(sumAsString);
            counter++;

            String[] splitSum = sumAsString.split("");
            sum = 0;

            if (sumAsString.equals(number) || numbersSet.size() != counter) {
                return false;
            }

            for (int i = 0; i < splitSum.length; i++) {
                int val = Integer.parseInt(splitSum[i]);

                val *= val;

                sum += val;
            }
        }

        return true;
    }

}
