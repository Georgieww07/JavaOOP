package robotService.entities.services;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseService implements Service{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;

    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.capacity <= this.robots.size()){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        this.robots.add(robot);

    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);

    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);

    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);

    }

    @Override
    public int sumHardness() {
        return this.supplements.stream()
                .mapToInt(Supplement::getHardness)
                .sum();
    }

    @Override
    public String getStatistics() {
        //"{serviceName} {serviceType}:
        //Robots: {robotName1} {robotName2} {robotName3} ... / Robots: none
        //Supplements: {supplementsCount} Hardness: {sumHardness}"

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        if (this.robots.isEmpty()){
            sb.append("Robots: none").append(System.lineSeparator());
        }else {
            sb.append(String.format("Robots: %s", this.robots.stream()
                    .map(Robot::getName)
                    .collect(Collectors.joining(" ")))).append(System.lineSeparator());
        }
        sb.append(String.format("Supplements: %d Hardness: %d", this.supplements.size(), this.sumHardness())).append(System.lineSeparator());


        return sb.toString();
    }
}
