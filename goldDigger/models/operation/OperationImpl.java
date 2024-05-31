package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OperationImpl implements Operation{
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        List<Discoverer> currentDiscoverers = discoverers.stream().filter(discoverer -> discoverer.getEnergy() > 0).collect(Collectors.toList());
        Collection<String> exhibits = spot.getExhibits();

        for (Discoverer discoverer : currentDiscoverers) {
            while (discoverer.canDig() && exhibits.iterator().hasNext()){
                discoverer.dig();
                String currentExhibit = exhibits.iterator().next();
                discoverer.getMuseum().getExhibits().add(currentExhibit);
                exhibits.remove(currentExhibit);
            }

        }
    }
}
