package Inheritance.RandomArrayList_03;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<T> extends ArrayList<T> {

    public T getRandomElement() {
        int idx = ThreadLocalRandom.current().nextInt(0, size());
        return remove(idx);
    }
}
