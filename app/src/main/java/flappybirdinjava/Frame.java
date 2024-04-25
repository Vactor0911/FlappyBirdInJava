package flappybirdinjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Frame extends JFrame {
    private BackgroundPanel pnlGame = new BackgroundPanel();
    private Timer timer = new Timer();
    private TimerTask timerTask;

    private static Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static int taskBarHeight = (int)( scrnSize.getHeight() - winSize.getHeight() );

    //Components
    Bird bird = new Bird();

    //Variable
    private float sizeMultiply = 1.0f;
    private final int ORIGIN_SIZE = 512;

    public Frame() {
        //Initialize
        setTitle("Flappy Bird In Java");
        setSize(513, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize( new Dimension(256, 256) );
        setLayout( new CardLayout() );

        //Game Screen
        pnlGame.setLayout(null);
        bird.setLocation(100, 100);
        bird.setSize(100, 100);
        pnlGame.add(bird);

         PipeDown pipe = new PipeDown();
         pipe.setLocation(600, -164);
         pipe.setSize(100, 100);
         pnlGame.add(pipe);

        PipeUp pipe2 = new PipeUp();
        pipe2.setLocation(600, 356);
        pipe2.setSize(100, 100);
        pnlGame.add(pipe2);

        add(pnlGame, "Game");
        pnlGame.addMouseListener( new MyMouseListener() );

        //Timer
        timerTask = new TimerTask() {
            @Override
            public void run() {
                pnlGame.update();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    } //Constructor

    public float getSizeMultiply() {
        return sizeMultiply;
    }

    public int getTaskBarHeight() {
        return taskBarHeight;
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
        sizeMultiply = (float)(getHeight() - taskBarHeight) / (float)(ORIGIN_SIZE - taskBarHeight);
    }

    //Listeners
    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            bird.jump();
        }
    }
    
} //Frame class
