package flappybirdinjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class GameObject extends JLabel {
    public GameObject() {
        super();
        setOpaque(false);
    }

    public abstract void update();
}

class BackgroundPanel extends JPanel {
    private Image imgBackground = new ImageIcon( Main.getPath("/sprites/background.png") ).getImage();
    private final int WIDTH = imgBackground.getWidth(null);
    private final int HEIGHT = imgBackground.getHeight(null);

    private Bird bird = new Bird();

    public BackgroundPanel() {
        this.setLayout(null);

        bird.setLocation(100, 100);
        bird.setSize(100, 100);
        add(bird);

        addMouseListener( new MyMouseListener() );
    }

    public void update() {
        bird.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Frame frame = Main.getFrame();
        float sizeMultiply = Main.getFrame().getSizeMultiply();
        int fixedWidth = (int)(WIDTH * sizeMultiply);
        int fixedHeight = (int)(HEIGHT * sizeMultiply);

        for (int i=0; i<frame.getWidth() / fixedWidth + 1; i++) {
            g.drawImage(imgBackground, i * fixedWidth, 0, fixedWidth, fixedHeight, this);
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            bird.jump();
        }
    }
}


class Bird extends GameObject {
    private final ImageIcon image = new ImageIcon( Main.getPath("/sprites/bird_midflap.png") );
    private final int WIDTH = image.getImage().getWidth(null);
    private final int HEIGHT = image.getImage().getHeight(null);

    private float jump = 0f;
    private final float GRAVITY = 3f;
    private final float G_FORCE = 0.5f;

    public Bird() {
        setOpaque(false);
    }

    public void update() {
        if ( jump > -GRAVITY) {
            jump -= G_FORCE;
        }
        else{
            jump = -GRAVITY;
        }

        float sizeMultiply = Main.getSizeMultiply();

        setSize( (int)(WIDTH * sizeMultiply), (int)(HEIGHT * sizeMultiply) );

        int y = Main.clamp( getY() - (int)( jump * Main.getSizeMultiply() ), 0, Main.getFrame().getBackgroundPanel().getHeight() - this.getHeight() );
        setLocation( (int)(100 * Main.getSizeMultiply() ), y);
    }

    public void jump() {
        jump = 10;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}