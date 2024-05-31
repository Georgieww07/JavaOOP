package PolymorphismExercise.Vehicles_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carTokens = scanner.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]));

        String[] truckTokens = scanner.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckTokens[1]), Double.parseDouble(truckTokens[2]));

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            switch (command[0]){
                case "Drive":
                    if (command[1].equals("PolymorphismExercise.VehiclesExtension_02.ReflectionAndAnnotations.CreateAnnotation_04.Test.Car")){
                        System.out.println(car.drive(Double.parseDouble(command[2])));
                    }else if (command[1].equals("PolymorphismExercise.VehiclesExtension_02.Truck")){
                        System.out.println(truck.drive(Double.parseDouble(command[2])));
                    }
                    break;
                case "Refuel":
                    if (command[1].equals("PolymorphismExercise.VehiclesExtension_02.ReflectionAndAnnotations.CreateAnnotation_04.Test.Car")){
                        car.refuel(Double.parseDouble(command[2]));
                    }else if (command[1].equals("PolymorphismExercise.VehiclesExtension_02.Truck")){
                        truck.refuel(Double.parseDouble(command[2]));
                    }
                    break;
            }
        }

        System.out.println(car);
        System.out.println(truck);

    }
}
