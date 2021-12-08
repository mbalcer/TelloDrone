public class Main {
    public static void main(String[] args) {
        Drone tello = new Drone("192.168.0.52");

        tello.connect();
        tello.sendCommand("takeoff");
        square(tello);
        tello.sendCommand("land");
    }

    public static void square(Drone drone) {
        for (int i = 0; i < 4; i++) {
            drone.move("forward", 100);
            drone.rotate("cw", 90);
        }
    }
}
