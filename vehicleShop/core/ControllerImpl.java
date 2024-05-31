package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private int countMadeVehicles = 0;
    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        if ("FirstShift".equals(type)) {
            worker = new FirstShift(workerName);
        } else if ("SecondShift".equals(type)) {
            worker = new SecondShift(workerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }

        this.workerRepository.add(worker);

        return String.format(ConstantMessages.ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Tool tool = new ToolImpl(power);
        Worker worker = this.workerRepository.findByName(workerName);

        if (worker == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        worker.getTools().add(tool);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        Collection<Worker> workers = this.workerRepository.getWorkers().stream()
                .filter(worker -> worker.getStrength() > 70)
                .collect(Collectors.toList());

        if (workers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }

        Vehicle vehicle = this.vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();

        int brokenTools = 0;

        for (Worker worker : workers) {
            shop.make(vehicle, worker);
            for (Tool tool : worker.getTools()) {
                if (tool.isUnfit()) {
                    brokenTools++;
                }
            }
            if (vehicle.reached()) {
                break;
            }
        }
        String output = "";
        if (vehicle.reached()) {
            output = "done";
        } else {
            output = "not done";
        }
        return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, output) + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenTools);
    }

    @Override
    public String statistics() {
        int ready = 0;
        for (Vehicle vehicle : this.vehicleRepository.getWorkers()) {
            if (vehicle.reached()) {
                ready++;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d vehicles are ready!", ready)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());

        for (Worker worker : this.workerRepository.getWorkers()) {
            sb.append(String.format("Name: %s, Strength: %d", worker.getName(), worker.getStrength())).append(System.lineSeparator());
            List<Tool> collect = worker.getTools().stream().filter(tool -> !tool.isUnfit()).collect(Collectors.toList());
            sb.append(String.format("Tools: %d fit left", collect.size())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
