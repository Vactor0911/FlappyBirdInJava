package flappybirdinjava;

import java.awt.*;
import javax.swing.*;

public class GameObject {
    
}

class BackgroundPanel extends JPanel {
    Image imgBackground = new ImageIcon( Main.getPath("/sprites/background.png") ).getImage();
    private final int WIDTH = imgBackground.getWidth(this);
    private final int HEIGHT = imgBackground.getHeight(this);

    public BackgroundPanel() {
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Frame frame = Main.getFrame();
        float sizeMultiply = frame.getSizeMultiply();
        int fixedWidth = (int)(WIDTH * sizeMultiply);
        int fixedHeight = (int)(HEIGHT * sizeMultiply);

        for (int i=0; i<frame.getWidth() / fixedWidth + 1; i++) {
            g.drawImage(imgBackground, i * fixedWidth, 0, fixedWidth, fixedHeight, this);
        }
    }
}
