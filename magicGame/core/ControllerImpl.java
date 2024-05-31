package magicGame.core;

import magicGame.common.ExceptionMessages;
import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        if ("RedMagic".equals(type)){
            magic = new RedMagic(name, bulletsCount);
        }else if ("BlackMagic".equals(type)){
            magic = new BlackMagic(name, bulletsCount);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_TYPE);
        }

        this.magics.addMagic(magic);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = this.magics.findByName(magicName);

        if (magic == null){
            throw new NullPointerException(ExceptionMessages.MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician;
        if ("Wizard".equals(type)){
            magician = new Wizard(username, health, protection, magic);
        }else if ("BlackWidow".equals(type)){
            magician = new BlackWidow(username, health, protection, magic);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_TYPE);
        }

        this.magicians.addMagician(magician);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
        List<Magician> magiciansToPlay = this.magicians.getData().stream().filter(Magician::isAlive).collect(Collectors.toList());

        return this.region.start(magiciansToPlay);
    }

    @Override
    public String report() {
        List<Magician> sortedMagicians = this.magicians.getData().stream()
                .sorted(Comparator.comparingInt(Magician::getHealth)
                .thenComparing(Magician::getUsername)
                .thenComparing(magician -> magician.getClass().getSimpleName()))
                .collect(Collectors.toList());

        //"{magician type}: {magician username}
        // Health: {magician health}
        // Protection: {magician protection}
        // Magic: {magician magic name}"

        StringBuilder sb = new StringBuilder();
        for (Magician magician : sortedMagicians) {
            sb.append(String.format("%s: %s", magician.getClass().getSimpleName(), magician.getUsername())).append(System.lineSeparator());
            sb.append(String.format("Health: %d", magician.getHealth())).append(System.lineSeparator());
            sb.append(String.format("Protection: %d", magician.getProtection())).append(System.lineSeparator());
            sb.append(String.format("Magic: %s", magician.getMagic().getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
