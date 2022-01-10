import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Drone {
    private final int udpPort = 8889;
    private final String ip;
    private DatagramSocket socket;
    private InetAddress IPAddress;
    private boolean isConnected = false;

    public Drone(String ip) {
        this.ip = ip;
    }

    public boolean isOk() {
        String receiveMessage = receiveMessage();
        System.out.println("Receive message: " + receiveMessage);
        return receiveMessage.contains("ok");
    }

    public boolean connect() {
        try {
            System.out.println("Connecting to drone");
            IPAddress = InetAddress.getByName(ip);
            socket = new DatagramSocket(udpPort);
            isConnected = sendMessage("command");
            if (isConnected) {
                System.out.println("Successfully connected to the drone");
                return true;
            } else {
                System.out.println("Cannot connect to the drone");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void sendCommand(String command) {
        System.out.println("Command: " + command);
        sendMessage(command);
        try {
            Thread.sleep(5000);
            System.out.println("Waiting 5 sec...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Received message: " + receiveMessage());
        System.out.println(command + " complete");
    }

    private boolean sendMessage(String command) {
        try {
            byte[] sendData = command.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, udpPort);
            socket.send(sendPacket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String receiveMessage() {
        byte[] receiveData = new byte[5];
        DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            return "communication error";
        }
        return new String(packet.getData());
    }

    public void takeOff() {
        sendCommand("takeoff");
        System.out.println("Drone is taking off");
    }

    public void land() {
        sendCommand("land");
        System.out.println("Drone is landing");
    }

    public boolean move(MoveDirection direction, int cm) {
        if (cm >= 20 && cm <= 500) {
            sendCommand(direction.getCommand() + " " + cm);
            System.out.println("Drone moved " + direction + ": " + cm + "cm. ");
            return true;
        } else {
            System.out.println(direction + " command failed (only cm between 20 and 500 allowed)");
            return false;
        }
    }

    public boolean rotate(RotateDirection direction, int degrees) {
        if (degrees >= 1 && degrees <= 360) {
            sendCommand(direction.getCommand() + " " + degrees);
            System.out.println("Drone turned " + degrees + " degrees clockwise");
            return true;
        } else {
            System.out.println("Rotation clockwise command failed");
            return false;
        }
    }

    public boolean curve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {
        if (validateCurveCoordinate(x1) && validateCurveCoordinate(y1) && validateCurveCoordinate(z1) &&
            validateCurveCoordinate(x2) && validateCurveCoordinate(y2) && validateCurveCoordinate(z2) &&
            speed >= 10 && speed <= 60) {
            sendCommand(String.format("curve %d %d %d %d %d %d %d", x1, y1, z1, x2, y2, z2, speed));
            System.out.println("Drone performed curve command");
            return true;
        } else {
            System.out.println("Curve command failed");
            return false;
        }
    }

    private boolean validateCurveCoordinate(int coordinate) {
        return coordinate >= 20 && coordinate <= 500;
    }

    public void makeSquareFigure(int sideLength) {
        for (int i = 0; i < 4; i++) {
            move(MoveDirection.FORWARD, sideLength);
            rotate(RotateDirection.CLOCKWISE, 90);
        }
        System.out.println("Square figure was done");
    }

    public void makeCircleFigure(int radius) {
        curve(radius, radius, 0, radius * 2, 0, 0, 20);
        curve(-radius, -radius, 0, -(radius * 2), 0, 0, 20);

        System.out.println("Circle figure was done");
    }

}
