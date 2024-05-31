package magicGame.models.magics;

public class RedMagic extends MagicImpl{
    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int remainingBullets = Math.max(0, this.getBulletsCount() - 1);
        this.setBulletsCount(remainingBullets);
        return 1;

    }
}
