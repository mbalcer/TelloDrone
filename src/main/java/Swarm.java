import java.util.ArrayList;
import java.util.List;

public class Swarm {
    private List<Drone> droneList = new ArrayList<>();

    public void doSwarmChoreography() {
        droneList.forEach(drone -> createChoreography(drone).start());
    }

    private Thread createChoreography(Drone drone) {
        return new Thread(() -> {
            drone.connect();
            drone.takeOff();
            drone.makeSquareFigure(50);
            drone.makeCircleFigure(50);
            drone.land();
        });
    }

    public void addDrone(Drone drone) {
        this.droneList.add(drone);
    }
}
