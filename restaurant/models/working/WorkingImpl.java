package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingImpl implements Working{
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {
        List<Waiter> availableWaiters = waiters.stream().filter(Waiter::canWork).collect(Collectors.toList());
        Collection<String> clientOrders = client.getClientOrders();

        for (Waiter waiter : availableWaiters) {
            while (waiter.canWork() && clientOrders.iterator().hasNext()){
                waiter.work();
                String currentOrder = clientOrders.iterator().next();
                waiter.takenOrders().getOrdersList().add(currentOrder);
                clientOrders.remove(currentOrder);
            }

        }
    }
}
