package ExceptionsAndErrorHandling;

import java.util.Scanner;

public class SquareRoot_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        try {
            int number = Integer.parseInt(input);
            if (number > 0){
                double sqrt = Math.sqrt(number);
                System.out.printf("%.2f\n", sqrt);
            }else {
                System.out.println("Invalid");
            }
        }catch (NumberFormatException ex){
            System.out.println("Invalid");
        }finally {
            System.out.println("Goodbye");
        }
    }
}
