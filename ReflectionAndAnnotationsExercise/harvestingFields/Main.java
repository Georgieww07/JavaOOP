package ReflectionAndAnnotationsExercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
        Field[] declaredFields = richSoilLandClass.getDeclaredFields();

        while (true) {
            String line = scanner.nextLine();
            if ("HARVEST".equalsIgnoreCase(line)) {
                break;
            }

            if (line.equals("all")) {
                Arrays.stream(declaredFields)
                        .forEach(field ->
                                System.out.printf("%s %s %s\n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
            } else {
                Arrays.stream(declaredFields)
                        .filter(field -> Modifier.toString(field.getModifiers())
                                .equals(line)).forEach(field ->
                                System.out.printf("%s %s %s\n", line, field.getType().getSimpleName(), field.getName()));
            }
        }
    }
}
