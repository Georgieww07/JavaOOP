package ReflectionAndAnnotations.CodingTracker_05;

import java.util.Arrays;

public class Tracker {

    @Author(name = "Stamat")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);

    }
    @Author(name = "George")
    public static void printMethodsByAuthor(Class<?> clazz){
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Author.class))
                .forEach(m -> System.out.println(m.getAnnotation(Author.class).name() + ": " + m.getName() + "()"));
    }
}
