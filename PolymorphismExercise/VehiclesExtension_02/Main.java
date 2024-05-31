package PolymorphismExercise.VehiclesExtension_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carTokens = scanner.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]), Double.parseDouble(carTokens[3]));

        String[] truckTokens = scanner.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckTokens[1]), Double.parseDouble(truckTokens[2]), Double.parseDouble(truckTokens[3]));

        String[] busTokens = scanner.nextLine().split("\\s+");
        Bus bus = new Bus(Double.parseDouble(busTokens[1]), Double.parseDouble(busTokens[2]), Double.parseDouble(busTokens[3]));


        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            try{
                switch (command[0]) {
                    case "Drive":
                        if (command[1].equals("ReflectionAndAnnotations.CreateAnnotation_04.Test.Car")) {
                            System.out.println(car.drive(Double.parseDouble(command[2])));
                        } else if (command[1].equals("Truck")) {
                            System.out.println(truck.drive(Double.parseDouble(command[2])));
                        } else if (command[1].equals("Bus")) {
                            bus.setFuelConsumption(bus.getFuelConsumption() + 1.4);
                            System.out.println(bus.drive(Double.parseDouble(command[2])));
                        }
                        break;
                    case "DriveEmpty":
                        System.out.println(bus.drive(Double.parseDouble(command[2])));
                        break;
                    case "Refuel":

                        if (command[1].equals("ReflectionAndAnnotations.CreateAnnotation_04.Test.Car")) {
                            car.refuel(Double.parseDouble(command[2]));
                        } else if (command[1].equals("Truck")) {
                            truck.refuel(Double.parseDouble(command[2]));
                        } else if (command[1].equals("Bus")) {
                            bus.refuel(Double.parseDouble(command[2]));
                        }
                        break;
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);


    }
}
