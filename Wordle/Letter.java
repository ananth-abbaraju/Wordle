import java.io.File;import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class Letter extends JLabel {
    private String letter;
    public Letter() {
        letter = "";
        setFont(new Font("Arial", Font.BOLD, 30));
        setForeground(Color.WHITE);
        setText("");
        setVisible(true);   
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }
    public void setLetter(String newString) {
        letter = newString;
        setText(newString);
    }
    public String getLetter() {
        return letter;
    }
}
