import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class NotInList extends JLabel {
    public NotInList() {
        setSize(215, 60); //281
        setLocation(265, 110);//270, 90
        setText("    Not in word list");
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setOpaque(true);
        setLayout(null);
        setVisible(true);  
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
    }
}
