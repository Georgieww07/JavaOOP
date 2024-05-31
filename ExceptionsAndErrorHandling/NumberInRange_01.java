package ExceptionsAndErrorHandling;

import java.util.Arrays;
import java.util.Scanner;

public class NumberInRange_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = range[0];
        int end = range[1];

        System.out.printf("Range: [%d...%d]\n", start, end);

        while (true){
            String input = scanner.nextLine();
            try{
                int number = Integer.parseInt(input);
                if (isInRange(number, start, end)){
                    System.out.printf("Valid number: %d\n", number);
                    break;
                }else {
                    System.out.printf("Invalid number: %d\n", number);
                }
            }catch (NumberFormatException ex){
                System.out.printf("Invalid number: %s\n", input);
            }
        }
    }
    private static boolean isInRange(int number, int start, int end){
        return start <= number && number <= end;
    }
}
