package harpoonDiver.models.diver;

public class WreckDiver extends BaseDiver{
    private static final double OXYGEN = 150;
    public WreckDiver(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void shoot() {
        double currentOxygen = this.getOxygen();
        currentOxygen -= 30;

        if (currentOxygen < 0){
            this.setOxygen(0);
        }else {
            this.setOxygen(currentOxygen);
        }

    }
}
