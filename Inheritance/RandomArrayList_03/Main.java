package Inheritance.RandomArrayList_03;

public class Main {
    public static void main(String[] args) {



        RandomArrayList<Integer> randomArrayList = new RandomArrayList<Integer>();
        randomArrayList.add(12);
        randomArrayList.add(32);
        randomArrayList.add(7);
        randomArrayList.add(1);

        System.out.println(randomArrayList.getRandomElement());
    }
}
