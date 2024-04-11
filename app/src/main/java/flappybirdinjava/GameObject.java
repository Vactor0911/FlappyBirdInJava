package flappybirdinjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class GameObject extends JPanel {
    abstract void update();
}

class BackgroundPanel extends JPanel {
    private Image imgBackground = new ImageIcon( Main.getPath("/sprites/background.png") ).getImage();
    private final int WIDTH = imgBackground.getWidth(this);
    private final int HEIGHT = imgBackground.getHeight(this);
    private Bird bird = new Bird();

    public BackgroundPanel() {
        setLayout(null);

        bird.setLocation(100, 100);
        bird.setSize( bird.getWidth(), bird.getHeight() );
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
        float sizeMultiply = frame.getSizeMultiply();
        int fixedWidth = (int)(WIDTH * sizeMultiply);
        int fixedHeight = (int)(HEIGHT * sizeMultiply);

        for (int i=0; i<frame.getWidth() / fixedWidth + 1; i++) {
            g.drawImage(imgBackground, i * fixedWidth, 0, fixedWidth, fixedHeight, this);
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            bird.setJumpPower(-10);
        }
    }
}


class Bird extends GameObject {
    private Image imgBird = new ImageIcon( Main.getPath("/sprites/bird_midflap.png") ).getImage();
    private final int WIDTH = imgBird.getWidth(this);
    private final int HEIGHT = imgBird.getHeight(this);
    private int jumpPower = -1;
    private final int MAX_JUMP_POWER = 2;
    private int y = getY();

    public void update() {
        y = Main.clamp( y + jumpPower, getHeight(), Main.getFrame().getBackgroundPanel().getHeight() );
        setLocation( 100, y - getHeight() );

        if (jumpPower < MAX_JUMP_POWER) {
            jumpPower += 1;
        }
    }

    public int getWidth() {
        return WIDTH;
    }
    public int getHeight() {
        return HEIGHT;
    }

    public void setJumpPower(int jumpPower) {
        this.jumpPower = jumpPower;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Frame frame = Main.getFrame();
        float sizeMultiply = frame.getSizeMultiply();
        int fixedWidth = (int)(WIDTH * sizeMultiply);
        int fixedHeight = (int)(HEIGHT * sizeMultiply);
        g.drawImage(imgBird, 0, 0, fixedWidth, fixedHeight, this);
        setSize(fixedWidth, fixedHeight);
    }
}