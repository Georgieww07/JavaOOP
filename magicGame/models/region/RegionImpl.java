package magicGame.models.region;

import magicGame.models.magicians.Magician;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = magicians.stream().filter(magician -> magician.getClass().getSimpleName().equals("Wizard")).collect(Collectors.toList());
        List<Magician> blackWidows = magicians.stream().filter(magician -> magician.getClass().getSimpleName().equals("BlackWidow")).collect(Collectors.toList());

        while (wizards.stream().mapToInt(Magician::getHealth).sum() != 0 && blackWidows.stream().mapToInt(Magician::getHealth).sum() != 0) {

            for (Magician wizard : wizards) {
                for (Magician blackWidow : blackWidows) {
                    if (wizard.getMagic().getBulletsCount() > 0) {
                        blackWidow.takeDamage(wizard.getMagic().fire());
                    }
                }
            }

            for (Magician blackWidow : blackWidows) {
                for (Magician wizard : wizards) {
                    if (blackWidow.getMagic().getBulletsCount() > 10) {
                        wizard.takeDamage(blackWidow.getMagic().fire());
                    }
                }
            }
        }
        String message = "";
        if (wizards.stream().mapToInt(Magician::getHealth).sum() == 0) {
            message = "Black widows win!";
        } else if (blackWidows.stream().mapToInt(Magician::getHealth).sum() == 0) {
            message = "Wizards win!";
        }

        return message;
    }
}