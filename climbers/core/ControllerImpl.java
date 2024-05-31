package climbers.core;

import climbers.common.ConstantMessages;
import climbers.common.ExceptionMessages;
import climbers.models.climber.Climber;
import climbers.models.climber.RockClimber;
import climbers.models.climber.WallClimber;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.ClimberRepository;
import climbers.repositories.MountainRepository;
import climbers.repositories.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private Repository<Climber> climberRepository;
    private Repository<Mountain> mountainRepository;
    private int mountainsClimbed;
    public ControllerImpl() {
        this.climberRepository = new ClimberRepository();
        this.mountainRepository = new MountainRepository();
    }

    @Override
    public String addClimber(String type, String climberName) {
        Climber climber;
        if ("WallClimber".equals(type)){
            climber = new WallClimber(climberName);
        }else if ("RockClimber".equals(type)){
            climber = new RockClimber(climberName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.CLIMBER_INVALID_TYPE);
        }

        this.climberRepository.add(climber);
        return String.format(ConstantMessages.CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {
        Mountain mountain = new MountainImpl(mountainName);
        mountain.getPeaksList().addAll(List.of(peaks));

        this.mountainRepository.add(mountain);

        return String.format(ConstantMessages.MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {
        Climber climber = this.climberRepository.byName(climberName);
        if (climber == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIMBER_DOES_NOT_EXIST, climberName));
        }
        this.climberRepository.remove(climber);

        return String.format(ConstantMessages.CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {
        Mountain mountain = this.mountainRepository.byName(mountainName);
        Collection<Climber> climbers = this.climberRepository.getCollection();

        if (climbers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_CLIMBERS);
        }

        ClimbingImpl climbing = new ClimbingImpl();
        climbing.conqueringPeaks(mountain, climbers);

        int countRemoved = 0;
        for (Climber climber : climbers) {
            if (climber.getStrength() == 0){
                countRemoved++;
            }

        }
        mountainsClimbed++;
        return String.format(ConstantMessages.PEAK_CLIMBING, mountainName, countRemoved);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_MOUNTAIN_COUNT, mountainsClimbed)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_CLIMBERS_STATISTICS).append(System.lineSeparator());

        for (Climber climber : this.climberRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.FINAL_CLIMBER_NAME, climber.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_CLIMBER_STRENGTH, climber.getStrength())).append(System.lineSeparator());
            sb.append("Conquered peaks:");

            if (climber.getRoster().getPeaks().isEmpty()){
                sb.append(" None");
            }else {
                String collect = String.join(", ", climber.getRoster().getPeaks());
                sb.append(String.format(" %s", collect));
            }
            sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
