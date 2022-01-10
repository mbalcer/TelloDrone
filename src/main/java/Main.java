public class Main {
    public static void main(String[] args) {
        Drone tello = new Drone("192.168.0.52");

        tello.connect();
        tello.takeOff();
        tello.makeSquareFigure(100);
        tello.makeCircleFigure(50);
        tello.land();
    }
}
