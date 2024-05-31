package WorkingWithAbstractionExercise.JediGalaxy_05;

import WorkingWithAbstractionExercise.JediGalaxy_05.JediGalaxyUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = JediGalaxyUtils.fillMatrix(scanner);

        long sumStars = 0;
        String input = scanner.nextLine();
        while (!"Let the Force be with you".equals(input)) {
            int[] peterCoordinates = JediGalaxyUtils.getPeterCoordinates(input);
            int[] evilCoordinates = JediGalaxyUtils.getEvilCoordinates(scanner);

            JediGalaxyUtils.destroyFields(evilCoordinates, matrix);
            sumStars = JediGalaxyUtils.collectUndestroyed(peterCoordinates, matrix, sumStars);

            input = scanner.nextLine();
        }
        System.out.println(sumStars);
    }
}
