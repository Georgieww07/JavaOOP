package restaurant.models.waiter;


//TODO
public class FullTimeWaiter extends BaseWaiter{
    private static final int EFFICIENCY = 8;

    public FullTimeWaiter(String name) {
        super(name, EFFICIENCY);
    }

    @Override
    public void work() {
        this.setEfficiency(Math.max(0, this.getEfficiency() - 1));
    }
}
