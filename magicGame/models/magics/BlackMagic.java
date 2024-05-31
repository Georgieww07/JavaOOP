package magicGame.models.magics;

public class BlackMagic extends MagicImpl{
    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int remainingBullets = Math.max(0, this.getBulletsCount() - 10);
        this.setBulletsCount(remainingBullets);
        return 10;
    }
}
