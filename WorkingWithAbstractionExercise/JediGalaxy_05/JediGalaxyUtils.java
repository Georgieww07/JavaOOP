package WorkingWithAbstractionExercise.JediGalaxy_05;

import java.util.Arrays;
import java.util.Scanner;

public class JediGalaxyUtils {
    private JediGalaxyUtils() {
    }
    public static int[][] fillMatrix(Scanner scanner) {
        int[] dimensions = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }

    public static long collectUndestroyed(int[] peterCoordinates, int[][] matrix, long sumStars) {
        int rowPeter = peterCoordinates[0];
        int colPeter = peterCoordinates[1];

        while (rowPeter >= 0 && colPeter < matrix[0].length) {
            if (rowPeter < matrix.length && colPeter >= 0) {
                sumStars += matrix[rowPeter][colPeter];
            }
            colPeter++;
            rowPeter--;
        }
        return sumStars;
    }

    public static void destroyFields(int[] evilCoordinates, int[][] matrix) {
        int rowEvil = evilCoordinates[0];
        int colEvil = evilCoordinates[1];

        while (rowEvil >= 0 && colEvil >= 0) {
            if (rowEvil < matrix.length && colEvil < matrix[0].length) {
                matrix[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }

    public static int[] getPeterCoordinates(String input) {
        return Arrays.stream(input
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int[] getEvilCoordinates(Scanner scanner) {
        return Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
