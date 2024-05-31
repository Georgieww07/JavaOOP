package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int inspectedSpotCount;
    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        if ("Anthropologist".equals(kind)){
            discoverer = new Anthropologist(discovererName);
        }else if ("Archaeologist".equals(kind)){
            discoverer = new Archaeologist(discovererName);
        }else if ("Geologist".equals(kind)){
            discoverer = new Geologist(discovererName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }

        this.discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        spot.getExhibits().addAll(List.of(exhibits));

        this.spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discovererToExclude = this.discovererRepository.byName(discovererName);

        if (discovererToExclude == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        this.discovererRepository.remove(discovererToExclude);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverers = this.discovererRepository.getCollection().stream().filter(discoverer -> discoverer.getEnergy() > 45).collect(Collectors.toList());

        if (discoverers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = this.spotRepository.byName(spotName);
        Operation operation = new OperationImpl();

        operation.startOperation(spot, discoverers);

        int countExcluded = 0;
        for (Discoverer discoverer : discoverers) {
            if (discoverer.getEnergy() == 0){
                countExcluded++;
            }
        }

        inspectedSpotCount++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, countExcluded);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, inspectedSpotCount)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_DISCOVERER_INFO).append(System.lineSeparator());

        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_NAME, discoverer.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_ENERGY, discoverer.getEnergy())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                    discoverer.getMuseum().getExhibits().isEmpty() ? "None" : String.join(", ", discoverer.getMuseum().getExhibits()))).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
