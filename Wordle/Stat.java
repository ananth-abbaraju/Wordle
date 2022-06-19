import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class Stat extends JLabel {

    private JLabel statTitle, numPlayed, playedLabel, winPercent, percentLabel, currentStreak, currentStreakLabel, maxStreak, maxStreakLabel, theWordLabel;
    
    public Stat() {

        setSize(415, 355); //281
        setLocation(163, 157);//160, 156
        setBackground(new Color(43,43,45));
        setOpaque(true);
        setLayout(null);
        setVisible(true);  
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
        repaint();

        //---------------TITLE---------------
        statTitle = new JLabel("STATISTICS");
        statTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
        statTitle.setForeground(Color.WHITE);
        statTitle.setBounds(155, 10, 500, 75);
        add(statTitle);

        //---------------PLAYS---------------
        numPlayed = new JLabel("1");
        numPlayed.setFont(new Font("Arial", Font.BOLD, 30));
        numPlayed.setForeground(Color.WHITE);
        numPlayed.setBounds(50, 75, 500, 75);
        add(numPlayed);
        playedLabel = new JLabel("Plays");
        playedLabel.setFont(new Font("Arial", Font.BOLD, 10));
        playedLabel.setForeground(Color.WHITE);
        playedLabel.setBounds(50, 100, 500, 75);
        add(playedLabel);

        //---------------PERCENT---------------
        winPercent = new JLabel("1");
        winPercent.setFont(new Font("Arial", Font.BOLD, 30));
        winPercent.setForeground(Color.WHITE);
        winPercent.setBounds(150, 75, 500, 75);
        add(winPercent);
        percentLabel = new JLabel("Win %");
        percentLabel.setFont(new Font("Arial", Font.BOLD, 10));
        percentLabel.setForeground(Color.WHITE);
        percentLabel.setBounds(150, 100, 500, 75);
        add(percentLabel);

        //-----------CURRENT STREAK-----------
        currentStreak = new JLabel("1");
        currentStreak.setFont(new Font("Arial", Font.BOLD, 30));
        currentStreak.setForeground(Color.WHITE);
        currentStreak.setBounds(250, 75, 500, 75);
        add(currentStreak);
        currentStreakLabel = new JLabel("Current Streak");
        currentStreakLabel.setFont(new Font("Arial", Font.BOLD, 10));
        currentStreakLabel.setForeground(Color.WHITE);
        currentStreakLabel.setBounds(225, 100, 500, 75);
        add(currentStreakLabel);

        //-------------MAX STREAK-----------
        maxStreak = new JLabel("1");
        maxStreak.setFont(new Font("Arial", Font.BOLD, 30));
        maxStreak.setForeground(Color.WHITE);
        maxStreak.setBounds(350, 75, 500, 75);
        add(maxStreak);
        maxStreakLabel = new JLabel("Max Streak");
        maxStreakLabel.setFont(new Font("Arial", Font.BOLD, 10));
        maxStreakLabel.setForeground(Color.WHITE);
        maxStreakLabel.setBounds(335, 100, 500, 75);
        add(maxStreakLabel);

        //-------------PLAY AGAIN-----------
        theWordLabel = new JLabel("");
        theWordLabel.setFont(new Font("Arial", Font.BOLD, 23));
        theWordLabel.setForeground(Color.WHITE);
        theWordLabel.setSize(300, 30);
        theWordLabel.setLocation(170, 300);
        theWordLabel.setAlignmentX(this.CENTER_ALIGNMENT);
        add(theWordLabel);

    }

    // setters
    public void setNumPlayed(int numPlayed) {
        this.numPlayed.setText("" + numPlayed);
    }
    public void setWinPercent(int winPercent) {
        this.winPercent.setText("" + winPercent);
    }
    public void setCurrentStreak(int currentStreak) {
        this.currentStreak.setText("" + currentStreak);
    }
    public void setMaxStreak(int maxStreak) {
        this.maxStreak.setText("" + maxStreak);
    }
    public void setTheWord(String theWord) {
        theWordLabel.setText(theWord);
    }

}
