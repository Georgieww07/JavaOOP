package restaurant.core;

import restaurant.common.ConstantMessages;
import restaurant.common.ExceptionMessages;
import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.Repository;
import restaurant.repositories.WaiterRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private Repository<Waiter> waiterRepository;
    private Repository<Client> clientRepository;
    private int countServed;

    public ControllerImpl() {
        this.waiterRepository = new WaiterRepository();
        this.clientRepository = new ClientRepository();
    }

    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter;
        if ("FullTimeWaiter".equals(type)){
            waiter = new FullTimeWaiter(waiterName);
        }else if ("HalfTimeWaiter".equals(type)){
            waiter = new HalfTimeWaiter(waiterName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_INVALID_TYPE);
        }

        this.waiterRepository.add(waiter);

        return String.format(ConstantMessages.WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);
        client.getClientOrders().addAll(List.of(orders));

        this.clientRepository.add(client);

        return String.format(ConstantMessages.CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = this.waiterRepository.byName(waiterName);

        if (waiter == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.WAITER_DOES_NOT_EXIST, waiterName));
        }

        this.waiterRepository.remove(waiter);

        return String.format(ConstantMessages.WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        Client client = this.clientRepository.byName(clientName);

        if (client == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIENT_DOES_NOT_EXIST, clientName));
        }

        this.clientRepository.remove(client);

        return String.format(ConstantMessages.CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        Collection<Waiter> waiters = this.waiterRepository.getCollection();
        if (waiters.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_WAITERS);
        }

        Client client = this.clientRepository.byName(clientName);

        Working working = new WorkingImpl();
        working.takingOrders(client, waiters);

        countServed++;

        return String.format(ConstantMessages.ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_CLIENTS_COUNT, countServed)).append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_WAITERS_STATISTICS).append(System.lineSeparator());

        for (Waiter waiter : this.waiterRepository.getCollection()) {
            sb.append(String.format(ConstantMessages.FINAL_WAITER_NAME, waiter.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_WAITER_EFFICIENCY, waiter.getEfficiency())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_WAITER_ORDERS,
                    waiter.takenOrders().getOrdersList().isEmpty() ? "None" : String.join(", ", waiter.takenOrders().getOrdersList()))).append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
