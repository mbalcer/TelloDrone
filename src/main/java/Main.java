public class Main {
    public static final String CHOREOGRAPHY_FILE_PATH = "src/main/resources/choreography.txt";

    public static void main(String[] args) {
        Swarm swarm = new Swarm();
        swarm.addDrone(new Drone("192.168.0.51"));
        swarm.addDrone(new Drone("192.168.0.52"));
        swarm.addDrone(new Drone("192.168.0.53"));
        swarm.setFilePath(CHOREOGRAPHY_FILE_PATH);

        swarm.doSwarmChoreography();
    }
}
