import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, MouseListener {

    static final int width = 500;
    static final int height = 500;
    static final int unit = 30;
    static final int delay = 2500;
    boolean running = false;
    int score = 0;
    int snakeX;
    int snakeY;
    Timer timer;
    Random random;
    private BufferedImage defaultSnakeImage;
    private BufferedImage powImage;
    private boolean isPowVisible = false;
    private int powX;
    private int powY;
    private Timer powTimer;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        loadImages();
        startGame();
        addMouseListener(this);
    }

    private void loadImages() {
        try {
            defaultSnakeImage = ImageIO.read(new File("snake.png"));
            powImage = ImageIO.read(new File("pow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            drawSnake(g);
            if (isPowVisible) {
                g.drawImage(powImage, powX, powY, unit, unit, this);
            }
        } else {
            gameOver(g);
        }
    }

    public void drawSnake(Graphics g) {
        if (defaultSnakeImage != null) {
            g.drawImage(defaultSnakeImage, snakeX, snakeY, unit, unit, this);
            g.setColor(Color.ORANGE);
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Score: " + score, (width - metrics1.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
        }
        if (!running) {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.ORANGE);
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (width - metrics1.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
        g.setColor(Color.GREEN);
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Click to Restart", (width - metrics2.stringWidth("Click to Restart")) / 2, height / 2 + 100);
    }

    public void addSnake() {
        snakeX = random.nextInt((width / unit)) * unit;
        snakeY = random.nextInt((height / unit)) * unit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            addSnake();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!running) {
            score = 0;
            running = true;
            startGame();
            repaint();
        } else {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int bounds = unit / 2;

            if (mouseX < snakeX - bounds || mouseX > snakeX + unit - bounds ||
                mouseY < snakeY - bounds || mouseY > snakeY + unit - bounds) {
                running = false;
            } else {
                isPowVisible = true;
                powX = mouseX - (unit / 2);
                powY = mouseY - (unit / 2);
                repaint();

                if (powTimer != null && powTimer.isRunning()) {
                    powTimer.stop();
                }

                powTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isPowVisible = false;
                        repaint();
                    }
                });
                powTimer.setRepeats(false);
                powTimer.start();

                score++;
                addSnake();
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
