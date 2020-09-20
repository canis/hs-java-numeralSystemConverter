package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean hasErrors = false;
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = 0;
        String sourceNumber = "";
        int targetRadix = 0;

        if (!scanner.hasNextInt()) {
            hasErrors = true;
        } else {
            sourceRadix = scanner.nextInt();
        }

        if (!scanner.hasNext()) {
            hasErrors = true;
        } else {
            sourceNumber = scanner.next();
        }

        if (!scanner.hasNextInt()) {
            hasErrors = true;
        } else {
            targetRadix = scanner.nextInt();
        }

        if (sourceRadix < 1 || sourceRadix > 36 || targetRadix < 1 || targetRadix > 36) {
            hasErrors = true;
        }

        if (hasErrors) {
            System.out.println("error! check input data.");
        } else {
            System.out.println(new NumberConverter(sourceRadix, sourceNumber, targetRadix));
        }
    }
}