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
    Timer pipeSpawnTimer; //6주차
    TimerTask pipeSpawnTimerTask; //6주차

    private static Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static int taskBarHeight = (int)( scrnSize.getHeight() - winSize.getHeight() );

    //Components
    private Bird bird = new Bird();
    private ScoreText scoreText = new ScoreText(); //6주차

    //Variable
    private float sizeMultiply = 1.0f;
    private final int ORIGIN_SIZE = 512;
    private boolean flagGameOver = false; //6주차

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

        //6주차
        scoreText.setLocation(0, 0);
        scoreText.setSize(0, 0);
        pnlGame.add(scoreText);
        //

        bird.setLocation(100, 100);
        bird.setSize(100, 100);
        pnlGame.add(bird);

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

        pipeSpawnTimer = new Timer();
        pipeSpawnTimerTask = new TimerTask() {
            @Override
            public void run() {
                int randY = (int)(Math.random() * 472);
                int clampY = Main.clamp(randY, PipeSpawner.GAP + Pipe.MIN_HEIGHT, 472 - PipeSpawner.GAP - Pipe.MIN_HEIGHT);
                PipeSpawner.spawnPipe(pnlGame, clampY);
            }
        };
        pipeSpawnTimer.scheduleAtFixedRate(pipeSpawnTimerTask, 0, PipeSpawner.SPAWN_DELAY);
    } //Constructor

    public float getSizeMultiply() {
        return sizeMultiply;
    }

    public int getTaskBarHeight() {
        return taskBarHeight;
    }

    //6주차
    public Bird getBird() {
        return bird;
    }

    public void gameOver() {
        flagGameOver = true;
        pipeSpawnTimer.cancel();
    }

    public boolean isGameOver() {
        return flagGameOver;
    }

    public void addScore() {
        scoreText.addScore(7);
    }
    //

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
