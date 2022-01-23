public class Main {
    public static void main(String[] args) {
        Swarm swarm = new Swarm();
        swarm.addDrone(new Drone("192.168.0.51"));
        swarm.addDrone(new Drone("192.168.0.52"));
        swarm.addDrone(new Drone("192.168.0.53"));

        swarm.doSwarmChoreography();
    }
}
