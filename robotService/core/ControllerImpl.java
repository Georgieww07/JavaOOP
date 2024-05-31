package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if ("MainService".equals(type)){
            service = new MainService(name);
        }else if ("SecondaryService".equals(type)){
            service = new SecondaryService(name);
        }else{
            throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        this.services.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, service.getClass().getSimpleName());
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if ("MetalArmor".equals(type)){
            supplement = new MetalArmor();
        }else if ("PlasticArmor".equals(type)){
            supplement = new PlasticArmor();
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        this.supplements.addSupplement(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, supplement.getClass().getSimpleName());
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplementForService = supplements.findFirst(supplementType);

        if (supplementForService == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }
        Service serviceToAdd = this.services.stream().filter(service -> service.getName().equals(serviceName)).findFirst().get();

        serviceToAdd.addSupplement(supplementForService);
        this.supplements.removeSupplement(supplementForService);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        if("FemaleRobot".equals(robotType)){
            robot = new FemaleRobot(robotName, robotKind, price);
        }else if ("MaleRobot".equals(robotType)){
            robot = new MaleRobot(robotName, robotKind, price);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }

        //FemaleRobot -> SecondaryService
        //MaleRobot -> MainService

        Service serviceToAdd = this.services.stream().filter(service -> service.getName().equals(serviceName)).findFirst().get();

        if (serviceToAdd.getClass().getSimpleName().equals("MainService") && robot.getClass().getSimpleName().equals("FemaleRobot") ||
                serviceToAdd.getClass().getSimpleName().equals("SecondaryService") && robot.getClass().getSimpleName().equals("MaleRobot")){
            return ConstantMessages.UNSUITABLE_SERVICE;
        }
        serviceToAdd.addRobot(robot);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service serviceToFeed = this.services.stream().filter(service -> service.getName().equals(serviceName)).findFirst().get();
        serviceToFeed.feeding();
        return String.format(ConstantMessages.FEEDING_ROBOT, serviceToFeed.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service serviceToCalculate = this.services.stream().filter(service -> service.getName().equals(serviceName)).findFirst().get();
        double sumRobots = serviceToCalculate.getRobots().stream().mapToDouble(Robot::getPrice).sum();
        double sumSupplements = serviceToCalculate.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();

        double sumOfAll = sumRobots + sumSupplements;
        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, sumOfAll);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.services.forEach(service -> sb.append(service.getStatistics()));
        return sb.toString().trim();
    }
}
