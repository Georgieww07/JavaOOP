package ReflectionAndAnnotations.Reflection_01;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<Reflection> reflectionClass = Reflection.class;
        System.out.println(reflectionClass);
        Class<? super Reflection> superclass = reflectionClass.getSuperclass();
        System.out.println(superclass);
        Class<?>[] interfaces = reflectionClass.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
        }
        Object o = reflectionClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
    }
}
