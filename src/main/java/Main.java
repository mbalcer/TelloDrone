public class Main {
    public static void main(String[] args) {
        Drone tello = new Drone("192.168.0.52");

        tello.connect();
        tello.takeOff();
        square(tello);
        circle(tello);
        tello.land();
    }

    public static void square(Drone drone) {
        for (int i = 0; i < 4; i++) {
            drone.move(MoveDirection.FORWARD, 100);
            drone.rotate(RotateDirection.CLOCKWISE, 90);
        }
    }

    public static void circle(Drone drone) {
        drone.curve(50, 50, 0, 100, 0, 0, 20);
        drone.curve(-50, -50, 0, -100, 0, 0, 20);
    }
}
