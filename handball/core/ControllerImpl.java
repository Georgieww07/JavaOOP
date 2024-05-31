package handball.core;

import handball.common.ConstantMessages;
import handball.common.ExceptionMessages;
import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private Repository equipmentRepository;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipmentRepository = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay;
        if ("Indoor".equals(gameplayType)){
            gameplay = new Indoor(gameplayName);
        }else if ("Outdoor".equals(gameplayType)){
            gameplay = new Outdoor(gameplayName);
        }else {
            throw new NullPointerException(ExceptionMessages.INVALID_GAMEPLAY_TYPE);
        }

        this.gameplays.add(gameplay);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment currEquipment;
        if (equipmentType.equals("ElbowPad")) {
            currEquipment = new ElbowPad();
        } else if (equipmentType.equals("Kneepad")) {
            currEquipment = new Kneepad();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_EQUIPMENT_TYPE);
        }

        this.equipmentRepository.add(currEquipment);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment desiredEquipment = this.equipmentRepository.findByType(equipmentType);
        Gameplay desiredGameplay = this.gameplays.stream()
                .filter(gameplay -> gameplay.getName().equals(gameplayName))
                .findFirst()
                .get();

        if (desiredEquipment == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_EQUIPMENT_FOUND, equipmentType));
        }

        //desiredGameplay.getEquipments().add(desiredEquipment);
        desiredGameplay.addEquipment(desiredEquipment);
        this.equipmentRepository.remove(desiredEquipment);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team;
        if ("Bulgaria".equals(teamType)){
            team = new Bulgaria(teamName, country, advantage);
        }else if ("Germany".equals(teamType)){
            team = new Germany(teamName, country, advantage);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TEAM_TYPE);
        }

        Gameplay gameplay = this.gameplays.stream()
                .filter(gameplay1 -> gameplay1.getName().equals(gameplayName))
                .findFirst()
                .get();


        //Bulgaria -> Outdoor
        //Germany -> Indoor

        String message = "";
        if (team.getClass().getSimpleName().equals("Bulgaria") && gameplay.getClass().getSimpleName().equals("Indoor") ||
                team.getClass().getSimpleName().equals("Germany") && gameplay.getClass().getSimpleName().equals("Outdoor") ){
            message = ConstantMessages.GAMEPLAY_NOT_SUITABLE;
        }else {
            gameplay.addTeam(team);
            message = String.format(ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
        }


        return message;
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = this.gameplays.stream()
                .filter(gameplay1 -> gameplay1.getName().equals(gameplayName))
                .findFirst()
                .get();

        gameplay.getTeam().forEach(Team::play);

        return String.format(ConstantMessages.TEAMS_PLAYED, gameplay.getTeam().size());
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay = this.gameplays.stream()
                .filter(gameplay1 -> gameplay1.getName().equals(gameplayName))
                .findFirst()
                .get();


      // gameplay.getTeam().forEach(Team::play);
       int sumAdvantage = gameplay.getTeam().stream().mapToInt(Team::getAdvantage).sum();


        return String.format(ConstantMessages.ADVANTAGE_GAMEPLAY, gameplayName, sumAdvantage);
    }

    @Override
    public String getStatistics() {
        StringBuilder statistics = new StringBuilder();
        for (Gameplay gameplay : this.gameplays) {
            statistics.append(String.format("%s %s", gameplay.getName(), gameplay.getClass().getSimpleName())).append(System.lineSeparator());
            Collection<Team> teams = gameplay.getTeam();
            statistics.append("Team:");
            if (teams.isEmpty()) {
                statistics.append(" None");
            } else {
                for (Team team : teams) {
                    statistics.append(String.format(" %s", team.getName()));
                }
            }
            statistics.append(System.lineSeparator());
            statistics.append(String.format("Equipment: %d, Protection: %d", gameplay.getEquipments().size(), gameplay.allProtection())).append(System.lineSeparator());
        }

        return statistics.toString().trim();
    }
}
