import java.io.IOException;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import javax.swing.border.Border;import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class Keyboard extends JPanel {
    private ArrayList<Key> keyList;
    private String[] keyAlphabet = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
    public Keyboard() {
        keyList = new ArrayList<Key>();
        int firstRowTempXPos = 20;
        for(int i = 0; i < 10; i++) {
            Key tempKey = new Key(firstRowTempXPos, 20, 45, 45);
            tempKey.setKey(keyAlphabet[i]);
            add(tempKey);
            keyList.add(tempKey);
            firstRowTempXPos += 50;
        }
        int secondRowTempXPos = 50;
        for(int i = 10; i < 19; i++) {
            Key tempKey = new Key(secondRowTempXPos, 70, 45, 45);
            tempKey.setKey(keyAlphabet[i]);
            add(tempKey);
            keyList.add(tempKey);
            secondRowTempXPos += 50;
        }
        int thirdRowTempXPos = 100;
        for(int i = 19; i < 26; i++) {
            Key tempKey = new Key(thirdRowTempXPos, 120, 45, 45);
            tempKey.setKey(keyAlphabet[i]);
            add(tempKey);
            keyList.add(tempKey);
            thirdRowTempXPos += 50;
        }
        Key enter = new Key(20,120, 75 ,45);
        enter.setKey("ENTER");
        add(enter);
        Key back = new Key(thirdRowTempXPos ,120, 70, 45);
        back.setKey("DELETE");
        add(back);

    }    

    public ArrayList<Key> getKeyList() {
        return keyList;
    }
}
