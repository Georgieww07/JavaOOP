package WorkingWithAbstraction;

import java.util.Scanner;

public class RhombusOfStars_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = Integer.parseInt(scanner.nextLine());

        for (int count = 0; count < n; count++) {
            printRow(n, count);
        }

        for (int count = n - 2; count >= 0; count--) {
            printRow(n, count);

        }
    }
    public static void printRow(int n, int count){
        for (int space = 1; space < n - count; space++) {
            System.out.print(" ");
        }

        for (int star = n; star >= n - count; star--) {
            System.out.print("* ");

        }
        System.out.println();

    }
}
