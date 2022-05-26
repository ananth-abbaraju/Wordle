import java.util.ArrayList;import java.util.Scanner;import javax.swing.*;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;
import static javax.swing.JOptionPane.showMessageDialog;
public class Wordle extends JFrame implements ActionListener {
    private ArrayList<String> wordList;
    private String theWord;
    private static Box[][] wordGrid;
    private Box toReset;
    private Box[] arrToReset;
    private static Stat stat = new Stat();
    private static boolean needToRemoveNotInList;
    private static boolean needToShowNotInList;
    private Keyboard keyboard;
    private ArrayList<Key> keyList;
    private ArrayList<String> greenLetters;
    //stats
    private static int numPlayed=0;
    private static int winCount=0;
    private static int currentStreak=0;
    private static int maxStreak=0;
    private static int winPercent=0;
    private static boolean addStat;
    private static NotInList notInList = new NotInList();
    //game 
    private static boolean gameEnded, buttonClicked;
    private static JButton playAgain = new JButton();
    public static void main(String[] args) throws InterruptedException, IOException {
        Wordle game = new Wordle();
        while(true) {
            if(addStat) {
                Thread.sleep(150);
                stat.repaint();
                game.add(stat);
                game.repaint();
                for(int i = 1; i < 6; i++) {
                    for(int j = 0; j < 5; j++) {
                        wordGrid[i][j].setVisible(false);
                    }
                }
                addStat = false;
            }      
            if(gameEnded && buttonClicked) {
                game.dispose();
                try {
                    game = new Wordle();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(needToShowNotInList) {
                game.add(notInList);
                notInList.repaint();
                needToShowNotInList = false;
                needToRemoveNotInList = true;
            } else if(needToRemoveNotInList) {
                Thread.sleep(250);
                game.remove(notInList);
                game.repaint();
                needToRemoveNotInList = false;
            }
            Thread.sleep(850);  
        }
    }
    public Wordle() throws IOException{
        gameEnded = false;
        buttonClicked = false;
        addStat = false;
        needToRemoveNotInList = false;
        wordList = new ArrayList<String>();
        greenLetters = new ArrayList<String>();
        File text = new File("five_letter_words.txt");
        Scanner s = new Scanner(text);
        while(s.hasNextLine()) {
            wordList.add(s.nextLine());
        }
        setSize(750, 900); 
        setTitle("Wordle - Abbaraju");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(18,18,18));

        JLabel title = new JLabel("Wordle");
        title.setFont(new Font("Georgia", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(310, 10, 500, 50);
        add(title);
        repaint();

        playAgain.setBounds(100, 200, 215, 75);
        playAgain.setBackground(new Color(105, 168, 101));
        playAgain.setForeground(Color.BLACK);
        playAgain.setOpaque(true);
        playAgain.setBorderPainted(false);
        playAgain.setFont(new Font("Arial", Font.BOLD, 25));
        playAgain.setText("Play Again!");
        playAgain.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playAgain.addActionListener(this);

        keyboard = new Keyboard();
        keyboard.setSize(650, 350);
        keyboard.setLocation(105, 560);
        keyboard.setLayout(null);
        keyboard.setBackground(new Color(18,18,18));
        keyboard.setVisible(true);  
        add(keyboard);
        repaint();
        keyList = keyboard.getKeyList();

        theWord = randomWord().toUpperCase();
        wordGrid = new Box[6][5];
        int tempY = 90;
        for(int r = 0; r < wordGrid.length; r++) {
            int tempX = 200;
            for(int c = 0; c < wordGrid[r].length; c++) {
                wordGrid[r][c] = new Box(tempX, tempY);
                add(wordGrid[r][c]);
                tempX += 70;
            }
            tempY += 70;
        }
        setVisible(true); 
   
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            int currentCol = 0;
            int currentRow = 0;
            int greenCount = 0;
            String currentGuess = "";
            String toIgnore = "";
            public void keyPressed(KeyEvent e) {
                // GAME OVER 
                if(e.getKeyCode()==KeyEvent.VK_ENTER && ((currentRow >=5 && currentCol == 5)|| currentGuess.equals(theWord))) {
                    numPlayed++;
                    if(currentGuess.equals(theWord)) {
                        winCount++;
                        currentStreak++;
                    } else if(currentRow >= 5 && !currentGuess.equals(theWord)) {
                        currentStreak = 0;

                    } 
                    
                    if(currentStreak > maxStreak) {
                        maxStreak = currentStreak;
                    }
                    stat.setNumPlayed(numPlayed);
                    stat.setCurrentStreak(currentStreak);
                    stat.setMaxStreak(maxStreak);
                    winPercent = (int)(((winCount*1.0)/numPlayed)*100);
                    stat.setWinPercent(winPercent);
                    stat.setTheWord(theWord);
                    stat.add(playAgain);
                    addStat = true;

                    // REMOVE CURRENT WORD FROM LIST
                    for(int i = 0; i < wordList.size(); i++){ 
                        if(wordList.get(i).equals(theWord)) {
                            wordList.remove(i);
                            break;
                        }
                    }
                    gameEnded = true;
                }

                // --------------------GAME LOGIC------------------------------------------------------------------------------------------------------------------
                if(greenCount < 5) {

                    //RESET ANIMATION
                    if(toReset != null) {
                        toReset.resetBounds();
                        repaint();
                    }
                    if(arrToReset != null) {
                        for(int i = 0; i < arrToReset.length; i++) {
                            arrToReset[i].resetBounds();
                        }
                        repaint();
                    }

                    if((isAlpha(e.getKeyText(e.getKeyCode())) || e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_BACK_SPACE) && currentRow < 6) {

                        // --------------------D E L E T E------------------------------------------------------------------------------------------------------------------
                        if(e.getKeyCode() ==KeyEvent.VK_BACK_SPACE && currentCol > 0) {
                            currentCol--;
                            currentGuess = currentGuess.substring(0, currentGuess.length() - 1);
                            wordGrid[currentRow][currentCol].getLabel().setText("");
                            wordGrid[currentRow][currentCol].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
                        } 
                        
                        // --------------------E N T E R------------------------------------------------------------------------------------------------------------------
                        else if(e.getKeyCode()==KeyEvent.VK_ENTER && currentCol == 5) {
                            // NOT IN WORD LIST
                            if(!wordList.contains(currentGuess.toLowerCase())) {
                                shake(wordGrid[currentRow]); 
                                needToShowNotInList = true;

                            } else { // IN THE WORD LIST
                                for(int i = 0; i < currentGuess.length(); i++) {
                                    String solutionLetter = theWord.substring(i, i+1);
                                    String guessLetter = currentGuess.substring(i, i+1);
                                    if(guessLetter.equals(solutionLetter)) {
                                        wordGrid[currentRow][i].setBackground(new Color(105, 168, 101)); //green
                                        for(int k = 0; k < keyList.size(); k++) {
                                            if(keyList.get(k).getKey().equals(guessLetter)) {
                                                keyList.get(k).setBackground(new Color(105, 168, 101));
                                            }
                                        }
                                        greenCount++;
                                        greenLetters.add(guessLetter);
                                    } else {
                                        wordGrid[currentRow][i].setBackground(new Color(43,43,45)); // gray
                                        for(int k = 0; k < keyList.size(); k++) {
                                            if(keyList.get(k).getKey().equals(guessLetter) && !theWord.contains(guessLetter) && !greenLetters.contains(guessLetter)) {
                                                keyList.get(k).setBackground(new Color(43,43,45));
                                            }
                                        }
                                        wordGrid[currentRow][i].setBorder(BorderFactory.createLineBorder(new Color(43,43,45),1));
                                    }
                                    int realCount = 0;
                                    int guessCount = 0;
                                    for(int x = 0; x < 5; x++) {
                                        if(currentGuess.substring(i, i+1).equals(theWord.substring(x, x+1))) {
                                            realCount++;
                                        }
                                        if(currentGuess.substring(i, i+1).equals(currentGuess.substring(x, x+1))) {
                                            guessCount++;
                                        }
                                    }
                                    if(guessCount > realCount) {
                                        toIgnore += currentGuess.substring(i, i+1);
                                    } 

                                }
                                for(int i = 0; i < currentGuess.length(); i++) {
                                    String solutionLetter = theWord.substring(i, i+1);
                                    String guessLetter = currentGuess.substring(i, i+1);
                                    if (theWord.contains(guessLetter) && !guessLetter.equals(solutionLetter) && !toIgnore.contains(guessLetter)){
                                        wordGrid[currentRow][i].setBackground(new Color(216, 181, 97)); //orange
                                        Color comparison = new Color(43,43,45);
                                        for(int k = 0; k < keyList.size(); k++) {
                                            boolean anyGreen = false;
                                            
                                            if(keyList.get(k).getKey().equals(guessLetter) && !greenLetters.contains(guessLetter)) {
                                                keyList.get(k).setBackground(new Color(216, 181, 97));
                                            }
                                        }
                                    } 
                                    
                                }
                                currentCol = 0;
                                currentRow++;
                                currentGuess = "";
                                toIgnore = "";
                            }
                        } else if (e.getKeyCode()==KeyEvent.VK_ENTER && currentCol < 5) {
                            showMessageDialog(null, "Not enough letters");
                        }
                        
                        // --------------------L E T T E R------------------------------------------------------------------------------------------------------------------
                        else {
                            if(currentCol >= 0 && currentCol < 5 && e.getKeyCode() !=KeyEvent.VK_BACK_SPACE && e.getKeyCode() !=KeyEvent.VK_ENTER) {
                                wordGrid[currentRow][currentCol].getLabel().setText(e.getKeyText(e.getKeyCode()).toUpperCase());
                                wordGrid[currentRow][currentCol].setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
                                currentGuess += e.getKeyText(e.getKeyCode());
                                expand(wordGrid[currentRow][currentCol]);
                                currentCol++;
                            }
                        }
                    } 
                }
            }
            
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER && currentCol == 5 && !wordList.contains(currentGuess.toLowerCase())) {
                    shake(wordGrid[currentRow]);
                }
                if(toReset != null) {
                    toReset.resetBounds();
                    repaint();
                }
                if(arrToReset != null) {
                    for(int i = 0; i < arrToReset.length; i++) {
                        arrToReset[i].resetBounds();
                    }
                    repaint();
                }
            }
        });
        setFocusable(true); 
    }

    public String randomWord() {
        int rand = (int) (Math.random() * 500);
        theWord = wordList.get(rand);
        return theWord;
    }   
    public boolean isAlpha(String str) {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for(int i = 0; i < alphabet.length; i++) {
            if(alphabet[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void shake(Box[] boxArr) {
        for(int i = 0; i < boxArr.length; i++) {
            boxArr[i].shiftLeft();
        }
        repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrToReset = boxArr;
    }
    
    public void expand(Box box) {
        box.dilate(5);
        repaint();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        toReset = box;
    }
  
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==playAgain) {
            buttonClicked = true;
        }
    }
}