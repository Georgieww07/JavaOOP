package ReflectionAndAnnotations.HighQualityMistakes_03;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Class<Reflection> reflectionClass = Reflection.class;
        Field[] declaredFields = reflectionClass.getDeclaredFields();

        List<Field> fieldList = new ArrayList<>();

        for (Field field : declaredFields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPrivate(modifiers)) {
                fieldList.add(field);
            }
        }

        List<Method> getters = new ArrayList<>();
        List<Method> setters = new ArrayList<>();

        Method[] declaredMethods = reflectionClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            int modifiers = method.getModifiers();
            if (method.getName().startsWith("get") && method.getParameterCount() == 0 && !method.getReturnType().getName().equalsIgnoreCase("void") && !Modifier.isPublic(modifiers)) {
                getters.add(method);

            } else if (method.getName().startsWith("set") && method.getParameterCount() == 1 && method.getReturnType().getName().equalsIgnoreCase("void") && !Modifier.isPrivate(modifiers)) {
                setters.add(method);
            }
        }

        fieldList.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(field -> System.out.printf("%s must be private!%n", field.getName()));

        getters.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(getter -> System.out.printf("%s have to be public!%n", getter.getName()));

        setters.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(setter -> System.out.printf("%s have to be private!%n", setter.getName()));

    }
}
