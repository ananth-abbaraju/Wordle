import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class Key extends JLabel {

    private String letter;
    private boolean colorEnabled;

    public Key(int xPos, int yPos, int width, int height) {
        letter = "";
        colorEnabled = false;
        setBounds(xPos, yPos, width, height);
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setText(" ");
        setBackground(Color.GRAY);
        setOpaque(true);
        setVisible(true);   
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
    }

    public void setKey(String newString) {
        letter = newString;
        if(newString.equals("ENTER")) {
            setText("   " + newString);

        } else if(newString.equals("DELETE")) {
            setText("  " + newString);

        } else {
            setText("    " + newString);

        }
    }

    public String getKey() {
        return letter;
    }

    public void disableColor() {
        colorEnabled = false;
    }

    public void setKeyBackground(Color c) {

        if(colorEnabled) {
            this.setBackground(c);
            setOpaque(true);
            repaint();
        }
        
    }
}
