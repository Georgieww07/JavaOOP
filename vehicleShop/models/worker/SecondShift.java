package vehicleShop.models.worker;

public class SecondShift extends BaseWorker{
    private static final int STRENGTH = 70;
    public SecondShift(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void working() {
        int decreasedStrength = this.getStrength() - 5;
        if (decreasedStrength < 0){
            this.setStrength(0);
        }else {
            this.setStrength(decreasedStrength);
        }
        //super.working();

    }
}
