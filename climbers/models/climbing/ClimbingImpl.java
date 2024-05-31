package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing{
    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        Collection<String> peaksList = mountain.getPeaksList();
        for (Climber climber : climbers) {
            while (climber.canClimb() && peaksList.iterator().hasNext()){
                climber.climb();
                String currentPeak = peaksList.iterator().next();
                climber.getRoster().getPeaks().add(currentPeak);
                peaksList.remove(currentPeak);
            }

        }
    }
}
