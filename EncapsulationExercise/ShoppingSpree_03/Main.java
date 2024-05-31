package EncapsulationExercise.ShoppingSpree_03;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] people = scanner.nextLine().split(";");
        String[] products = scanner.nextLine().split(";");

        Map<String, Person> personMap;
        Map<String, Product> productMap;

        try{
            personMap = fillPersonMap(people);
            productMap = fillProductMap(products);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        while (true) {
            String command = scanner.nextLine();
            if ("END".equals(command)) {
                break;
            }
            String[] commandParts = command.split("\\s+");
            String currPerson = commandParts[0];
            String currProduct = commandParts[1];

            try {
                if (personMap.containsKey(currPerson) && productMap.containsKey(currProduct)) {
                    personMap.get(currPerson).buyProduct(productMap.get(currProduct));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                break;
            }
            System.out.printf("%s bought %s\n", currPerson, currProduct);
        }
        print(personMap);
    }

    private static void print(Map<String, Person> personMap){
        for (Map.Entry<String, Person> entry : personMap.entrySet()) {
            String name = entry.getKey();
            if (personMap.get(name).getProducts().size() == 0){
                System.out.printf("%s - Nothing bought\n", name);
            }else {
                List<Product> list = entry.getValue().getProducts();
                List<String> resultList = new ArrayList<>();
                for (Product product : list) {
                    resultList.add(product.getName());

                }
                System.out.printf("%s - %s\n", name, String.join(", ", resultList).replace("[", "").replace("]", ""));
            }

        }
    }

    private static Map<String, Product> fillProductMap(String[] products) {
        Map<String, Product> productMap = new LinkedHashMap<>();

        for (String info : products) {
            String name = info.split("=")[0];
            double cost = Double.parseDouble(info.split("=")[1]);


            Product product = new Product(name, cost);
            productMap.putIfAbsent(name, product);

        }
        return productMap;
    }

    private static Map<String, Person> fillPersonMap(String[] people) {
        Map<String, Person> personMap = new LinkedHashMap<>();

        for (String info : people) {
            String name = info.split("=")[0];
            double money = Double.parseDouble(info.split("=")[1]);


            Person person = new Person(name, money);
            personMap.putIfAbsent(name, person);

        }
        return personMap;
    }
}
