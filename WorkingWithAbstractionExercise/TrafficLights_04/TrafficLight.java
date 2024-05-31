package WorkingWithAbstractionExercise.TrafficLights_04;

public enum TrafficLight {
    RED,
    GREEN,
    YELLOW;



    public static void print(TrafficLight[] trafficLight){
        for (int i = 0; i < trafficLight.length; i++) {
            System.out.print(trafficLight[i] + " ");
        }
        System.out.println();
    }

    public static void update(TrafficLight[] trafficLight){
        for (int i = 0; i < trafficLight.length; i++) {
            switch (trafficLight[i]){
                case RED:
                    trafficLight[i] = TrafficLight.GREEN;
                    break;
                case GREEN:
                    trafficLight[i] = TrafficLight.YELLOW;
                    break;
                case YELLOW:
                    trafficLight[i] = TrafficLight.RED;
                    break;
            }
        }
    }
}
