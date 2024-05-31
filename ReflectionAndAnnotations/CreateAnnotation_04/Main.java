package ReflectionAndAnnotations.CreateAnnotation_04;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Class<Test> testClass = Test.class;
       if (testClass.isAnnotationPresent(Subject.class)){
           Subject annotation = testClass.getAnnotation(Subject.class);
           System.out.println(Arrays.toString(annotation.categories()));
       }
    }
}
