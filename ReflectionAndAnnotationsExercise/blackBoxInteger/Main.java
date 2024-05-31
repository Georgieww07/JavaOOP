package ReflectionAndAnnotationsExercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> blackBoxIntClass = BlackBoxInt.class;
        Constructor<BlackBoxInt> declaredConstructor = blackBoxIntClass.getDeclaredConstructor();

        declaredConstructor.setAccessible(true);
        BlackBoxInt blackBoxInt = declaredConstructor.newInstance();

        while (true) {
            String line = scanner.nextLine();
            if ("END".equals(line)) {
                break;
            }
            String[] tokens = line.split("_");
            String methodName = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method declaredMethod = blackBoxInt.getClass().getDeclaredMethod(methodName, int.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(blackBoxInt, value);

            Field innerValue = blackBoxInt.getClass().getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            System.out.println(innerValue.get(blackBoxInt));

        }
    }
}

