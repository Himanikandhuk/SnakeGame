import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Instructions extends JFrame implements ActionListener {

    public Instructions() {
        JLabel titleLabel = new JLabel("How it Works ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        JLabel infoLabel = new JLabel("Hit the snakes and gain a point. Miss the snake and lose the game.");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(infoLabel);

        JButton playButton = new JButton("Play Now");
        playButton.setBackground(Color.GREEN);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
                dispose();
            }
        });
        this.add(playButton);

        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
