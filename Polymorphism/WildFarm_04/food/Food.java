package Polymorphism.WildFarm_04.food;

public abstract class Food {
    private Integer quantity = 0;

    public Food(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
