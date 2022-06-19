import java.io.File;import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class Box extends JPanel {

    private int xPos, yPos;
    private Letter letter;
    private String wordContained;

    public Box(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        setSize(65, 65);
        setLocation(xPos, yPos);
        setLayout(null);
        setBackground(new Color(18,18,18));
        setVisible(true);  
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
        letter = new Letter();
        letter.setBounds(21,3,65,65);
        letter.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        letter.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        letter.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        letter.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        add(letter);
    }

    // getters
    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }
    public Letter getLabel() {
        return letter;
    }
    
    // setters
    public void setBoxLocation(int x, int y) {
        setLocation(x, y);
        repaint();
    }
    public void resetBounds() {
        setLocation(xPos, yPos);
        setSize(65, 65);
        repaint();
    }

    public void dilate(int factor) {
        setLocation(xPos - factor, yPos - factor);
        setSize(65 + (factor * 2), 65 + (factor * 2));
        repaint();
    }
    public void shiftLeft() {
        setLocation(xPos - 25, yPos);
        setSize(65, 65);
        repaint();
    } 

}
