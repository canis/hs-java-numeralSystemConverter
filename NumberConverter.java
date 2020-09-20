package converter;

public class NumberConverter {
    private final int sourceRadix;
    private final int targetRadix;
    private final String integerPart;
    private final String fractionPart;

    public NumberConverter(int sourceRadix, String sourceNumber, int targetRadix) {
        this.sourceRadix = sourceRadix;
        this.targetRadix = targetRadix;

        String[] parts = sourceNumber.split("\\.");
        this.integerPart = parts[0];
        this.fractionPart = parts.length == 2 ? parts[1] : "";
    }

    @Override
    public String toString() {
        return convertInteger() + (!"".equals(fractionPart) ? "." + convertFraction() : "");
    }

    private String convertInteger() {
        int number = sourceRadix == 1
            ? integerPart.length()
            : Integer.parseInt(integerPart, sourceRadix);

        return targetRadix == 1
            ? "1".repeat(number)
            : Integer.toString(number, targetRadix);
    }

    private String convertFraction() {
        double fraction = 0;
        if (sourceRadix != 10) {
            int divisor = sourceRadix;
            for (int i = 0; i < fractionPart.length(); i++, divisor *= sourceRadix) {
                fraction += (double) Character.getNumericValue(fractionPart.charAt(i)) / divisor;
            }
        } else {
            fraction = Double.parseDouble("0." + fractionPart);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            double step = fraction * targetRadix;
            int number = (int) step;
            fraction = step - number;
            result.append(number > 9 ? Character.toString(number + 87) : number);
        }
        return result.toString();
    }
}
