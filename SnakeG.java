import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeG {
    public static void main(String[] args) {
        JFrame f = new JFrame("Snake Game");
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        f.add(titleLabel);

        JButton playButton = new JButton("Play Now");
        playButton.setBackground(Color.GREEN);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
                f.dispose();
            }
        });
        f.add(playButton);

        f.pack();
        f.setLocationRelativeTo(null); 
        f.setVisible(true);
    }
}

