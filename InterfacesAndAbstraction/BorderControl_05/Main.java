package InterfacesAndAbstraction.BorderControl_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> list = new ArrayList<>();

        while (true){
            String line = scanner.nextLine();
            if ("End".equals(line)){
                break;
            }
            String[] tokens = line.split("\\s+");

            Identifiable identifiable;
            if (tokens.length == 3){
                identifiable = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                list.add(identifiable);

            } else if (tokens.length == 2) {
                identifiable = new Robot(tokens[0], tokens[1]);
                list.add(identifiable);
            }
        }

        String fakeIdEnding = scanner.nextLine();

        for (Identifiable object : list) {
            if (object.getId().endsWith(fakeIdEnding)){
                System.out.println(object.getId());
            }
        }
    }
}
