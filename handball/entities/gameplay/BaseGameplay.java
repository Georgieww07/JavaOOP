package handball.entities.gameplay;

import handball.common.ExceptionMessages;
import handball.entities.equipment.Equipment;
import handball.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseGameplay implements Gameplay {
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    public BaseGameplay(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int allProtection() {
        return this.equipments.stream()
                .mapToInt(Equipment::getProtection)
                .sum();
    }

    @Override
    public void addTeam(Team team) {
        this.teams.add(team);

    }

    @Override
    public void removeTeam(Team team) {
        this.teams.remove(team);

    }

    @Override
    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);

    }

    @Override
    public void teamsInGameplay() {
        this.teams.forEach(Team::play);

    }

    @Override
    public Collection<Team> getTeam() {
        return this.teams;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return this.equipments;
    }

    @Override
    public String getName() {
        return this.name;
    }

//    @Override
    // public String toString() {
    //"{gameplayName} {gameplayType}
    //Team: {teamName1} {teamName2} (…) / Team: none
    //Equipment: {equipmentsCount}, Protection: {allProtection}"
        /*StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        if (this.teams.isEmpty()){
            sb.append("Team: None").append(System.lineSeparator());
        }else {
            sb.append(String.format("Team: %s", this.teams.stream().map(Team::getName).collect(Collectors.joining(" ")))).append(System.lineSeparator());
        }
        sb.append(String.format("Equipment: %d, Protection: %d", this.equipments.size(), this.allProtection()));
        return sb.toString();*/

    @Override
    public String toString() {
        return String.format("%s %s%nTeam: %s%nEquipment: %d, Protection: %d%n",
                this.name, this.getClass().getSimpleName(),
                this.teams.stream().map(Team::getName).collect(Collectors.joining(" ")),
                this.equipments.size(), allProtection());
    }

}

