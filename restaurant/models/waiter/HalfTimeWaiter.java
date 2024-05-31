package restaurant.models.waiter;

//TODO
public class HalfTimeWaiter extends BaseWaiter {
    private static final int EFFICIENCY = 4;

    public HalfTimeWaiter(String name) {
        super(name, EFFICIENCY);
    }

    @Override
    public void work() {
        this.setEfficiency(Math.max(0, this.getEfficiency() - 2));

    }
}
