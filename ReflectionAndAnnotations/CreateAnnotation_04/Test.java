package ReflectionAndAnnotations.CreateAnnotation_04;

@Subject(categories = {"ReflectionAndAnnotations.CreateAnnotation_04.Test", "Test1"})
public class Test {
    private String name;
    private int age;


    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
