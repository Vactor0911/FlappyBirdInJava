package flappybirdinjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame {
    BackgroundPanel pnlBackground = new BackgroundPanel();

    //Variable
    private float sizeMultiply = 1.0f;
    private final int ORIGIN_SIZE = 512;

    public Frame() {
        setTitle("Flappy Bird In Java");
        setSize(512, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize( new Dimension(256, 256) );
        
        add(pnlBackground);
    }

    public float getSizeMultiply() {
        return sizeMultiply;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();

        if (width > height) {
            setSize(height, height);
        }
        else {
            setSize(width, width);
        }
        sizeMultiply = (float)getWidth() / (float)ORIGIN_SIZE;
    }
    
}
