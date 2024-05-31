package Polymorphism.MathOperation_01;

public class MathOperation {
    public int add(int a, int b){
        return calculateResult(a, b);
    }

    public int add(int a, int b, int c){
        return calculateResult(a, b, c);
    }

    public int add(int a, int b, int c, int d){
        return calculateResult(a, b, c, d);
    }

    private int calculateResult(int... args){
        int res = 0;
        for (int e : args) {
            res += e;
        }
        return res;
    }
}
