public class Main {
    public static void main(String[] args) {
        Drone tello = new Drone("192.168.0.52");

        tello.connect();
        tello.sendCommand("takeoff");
        tello.sendCommand("forward 100");
        tello.sendCommand("cw 90");
        tello.sendCommand("forward 100");
        tello.sendCommand("land");
    }
}
