package ap.exercises.ex2;

import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main_EX2_PM_3_1 extends JFrame implements KeyListener {

    Point pacmanPoint = new Point();
    final int width = 300, height = 300, boxSize = 5;
    static int direction = 1, score = 0;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
    Point dotPoint = new Point();
    boolean quit = false, maxTime = false, maxScore = false;
    long initial = System.currentTimeMillis();

    public Main_EX2_PM_3_1() {
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        getNewDotPointLocation();
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2D);
        drawDotPoint(g2D);
        drawScore(g2D);
        askExit(g2D);
        drawTime(g2D);
        Timeout();
        setVisible(true);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawDotPoint(Graphics2D g2d) {
        if (!maxScore) {
            g2d.setColor(Color.RED);
            g2d.fillRect(dotPoint.x * boxSize, dotPoint.y * boxSize, boxSize, boxSize);
        }
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        String s = "Score: " + score + "/25";
        if (maxScore) {
            s += " you won!";
        }
        g2d.drawString(s, 25, 50);
    }

    private void drawTime(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        String s = ((System.currentTimeMillis() - initial) / 1000) / 60 + ":" + ((System.currentTimeMillis() - initial) / 1000) % 60 + "s";
        String s1 = System.currentTimeMillis() - initial + "";
        g2d.drawString(s, 25, 70);
    }

    private void Timeout() {
        if ((System.currentTimeMillis() - initial) / (2*60000) >= 1) {
            System.out.println("Time out error!");
            System.exit(0);
        }
    }

    private void logic() {
        if (dotPoint.x == pacmanPoint.x && dotPoint.y == pacmanPoint.y && !maxScore) {
            getNewDotPointLocation();
            score++;
            System.out.println("Score: " + score);
            if (score > 24)
                maxScore = true;
        }
        movePacman();
    }

    private void movePacman() {
        int xMovement = 1;
        int yMovement = 0;
        switch (direction) {
            case LEFT:
                xMovement = -1;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 1;
                yMovement = 0;
                break;
            case TOP:
                xMovement = 0;
                yMovement = -1;
                break;
            case BOTTOM:
                xMovement = 0;
                yMovement = 1;
                break;
            default:
                xMovement = yMovement = 0;
                break;
        }
        pacmanPoint.setLocation(pacmanPoint.x + xMovement, pacmanPoint.y + yMovement);

        handleCrossBorder();
    }

    private void getNewDotPointLocation() {
        Random rand = new Random();
        int delta = boxSize * 2;
        dotPoint.setLocation(rand.nextInt(width / boxSize - 2 * delta) + delta, rand.nextInt(height / boxSize - 2 * delta) + delta);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 3;
            quit = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 4;
            quit = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 1;
            quit = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 2;
            quit = false;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            direction = 0;
            quit = false;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
            quit = true;
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            if (quit)
                System.exit(0);
        } else {
            direction = -1;
            quit = false;
        }

        System.out.println("direction:" + direction + "    <- e.getKeyCode()=" + e.getKeyCode());

        repaint();
    }

    private void askExit(Graphics2D g2d) {
        if (quit) {
            g2d.setColor(Color.BLACK);
            String s = "U sure?\n[y/n]\n";
            g2d.drawString(s, 25, 100);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void handleCrossBorder() {
        //wrapping (deepSeek :p )
//        int gridWidth = width / boxSize;
//        int gridHeight = height / boxSize;
//
//        // Handle x-axis wrapping
//        if (pacmanPoint.x < 0) {
//            pacmanPoint.x = gridWidth - 1;
//        } else if (pacmanPoint.x >= gridWidth) {
//            pacmanPoint.x = 0;
//        }
//
//        // Handle y-axis wrapping
//        if (pacmanPoint.y < 0) {
//            pacmanPoint.y = gridHeight - 1;
//        } else if (pacmanPoint.y >= gridHeight) {
//            pacmanPoint.y = 0;
//        }
        //29 6
        //2 6
        //58 58
        if (pacmanPoint.x == 2)
            pacmanPoint.x = 58;
        else if (pacmanPoint.x == 58)
            pacmanPoint.x = 2;
        if (pacmanPoint.y < 6)
            pacmanPoint.y = 58;
        else if (pacmanPoint.y == 58)
            pacmanPoint.y = 6;
    }


    public static void main(String[] args) {
        Main_EX2_PM_3_1 frame = new Main_EX2_PM_3_1();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

