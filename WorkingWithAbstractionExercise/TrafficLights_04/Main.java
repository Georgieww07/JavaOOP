package WorkingWithAbstractionExercise.TrafficLights_04;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TrafficLight[] trafficLight = Arrays.stream(scanner.nextLine()
                .split(" "))
                .map(TrafficLight::valueOf)
                .toArray(TrafficLight[]::new);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            TrafficLight.update(trafficLight);
            TrafficLight.print(trafficLight);
        }

    }
}
