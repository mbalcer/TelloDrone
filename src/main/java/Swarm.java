import java.util.ArrayList;
import java.util.List;

public class Swarm {
    private List<Drone> droneList = new ArrayList<>();
    private String filePath; // file path to choreography

    public Swarm() {
    }

    public Swarm(String filePathToChoreography) {
        this.filePath = filePathToChoreography;
    }

    public void doSwarmChoreography() {
        droneList.forEach(drone -> {
            if (filePath.length() > 0) {
                createChoreographyFromFile(drone, filePath).start();
            } else {
                createChoreography(drone).start();
            }
        });
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

    private Thread createChoreographyFromFile(Drone drone, String filePath) {
        List<String> commands = FileService.readFile(filePath);

        return new Thread(() -> {
            drone.connect();
            drone.takeOff();
            commands.forEach(cmd -> drone.sendCommand(cmd));
            drone.land();
        });
    }

    public void addDrone(Drone drone) {
        this.droneList.add(drone);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
