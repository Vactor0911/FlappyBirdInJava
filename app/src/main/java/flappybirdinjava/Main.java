package flappybirdinjava;

import java.net.URL;

public class Main {
    private static Frame frame;

    public static void main(String[] args) {
        frame = new Frame();
    }

    public static URL getPath(String path) {
        return Main.class.getResource(path);
    }

    public static Frame getFrame() {
        return frame;
    }
}