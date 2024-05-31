package Inheritance.StackOfStrings_04;

public class Main {
    public static void main(String[] args) {

        StackOfStrings stack = new StackOfStrings();
        stack.push("Gogo");
        stack.push("Eva");
        stack.push("Monsturo");

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());


    }
}
