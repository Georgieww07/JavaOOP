package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.models.seaCatch.SeaCatch;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private int divingCount = 0;
    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        if ("DeepWaterDiver".equals(kind)){
            diver = new DeepWaterDiver(diverName);
        }else if ("OpenWaterDiver".equals(kind)){
            diver = new OpenWaterDiver(diverName);
        }else if ("WreckDiver".equals(kind)){
            diver = new WreckDiver(diverName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }
        this.diverRepository.add(diver);
        return String.format(ConstantMessages.DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        for (String creature : seaCreatures) {
            divingSite.getSeaCreatures().add(creature);
        }
        this.divingSiteRepository.add(divingSite);
        return String.format(ConstantMessages.DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diverToBeRemoved = this.diverRepository.byName(diverName);
        if (diverToBeRemoved == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_DOES_NOT_EXIST, diverName));
        }
        this.diverRepository.remove(diverToBeRemoved);
        return String.format(ConstantMessages.DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        Collection<Diver> divers = this.diverRepository.getCollection().stream()
                .filter(diver -> diver.getOxygen() > 30)
                .collect(Collectors.toList());

        if (divers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }

        DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        diving.searching(divingSite, divers);

        int removedDivers = 0;
        for (Diver diver : divers) {
            if (diver.getOxygen() == 0){
                removedDivers++;
            }
        }
        divingCount++;
        return String.format(ConstantMessages.SITE_DIVING, siteName, removedDivers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_DIVING_SITES, divingCount)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_DIVERS_STATISTICS).append(System.lineSeparator());

        for (Diver diver : this.diverRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.FINAL_DIVER_NAME, diver.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, diver.getOxygen())).append(System.lineSeparator());

            SeaCatch seaCatch = diver.getSeaCatch();

            if (seaCatch.getSeaCreatures().isEmpty()){
                sb.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, "None")).append(System.lineSeparator());
            }else {
                String diverCatch = String.join(", ", seaCatch.getSeaCreatures());
                sb.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, diverCatch)).append(System.lineSeparator());
            }

        }
        return sb.toString().trim();
    }
}
