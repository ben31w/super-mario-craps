import java.awt.GridLayout;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel containing two dice images. The dice can be rolled with the roll() 
 * method.
 * 
 * @author ben31w
 * @version 2021.01.05
 */
public class Dice extends JPanel {
    /** label for the first die */
    private JLabel label1;
    
    /** label for the second die */
    private JLabel label2;
    
    /** the character the user has chosen */
    private String chosen;
    
    
    /**
     * Construct an instance of Dice by setting the JPanel's layout.
     */
    public Dice() {
        setLayout(new GridLayout(1, 2, 5, 5));
        
        // Get the character the user has chosen, then set the dice images to 
        // correspond to the character.
        String filePath = "chosen.txt";
        try {
            Scanner fout = new Scanner(new File(filePath));
            chosen = fout.next();
            
            label1 = new JLabel();
            label1.setIcon(new ImageIcon("images" + File.separator + chosen + 
                    File.separator + "1.png"));
            add(label1);

            label2 = new JLabel();
            label2.setIcon(new ImageIcon("images" + File.separator + chosen + 
                    File.separator + "1.png"));
            add(label2);
            
            fout.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Failed to open " + filePath);
        }
    }
    
    
    /**
     * Roll both dice and return their sum.
     * 
     * @return the sum of the two dice
     */
    public int roll() {
        int a = rollOne(label1);
        int b = rollOne(label2);
        return a + b;
    }
    
    
    /** 
     * Roll a die and return the value.
     * 
     * @param label
     *          label with a die image
     * @return
     *          the value on the die
     */
    private int rollOne(JLabel label) {
        // Get a random int between 1 and 6 (inclusive).
        int value = (int)(Math.random()*6+1);

        // Set the image on the label to match the value.
        String filePath = "images" + File.separator + chosen + File.separator;
        switch (value) {            
            case 1: 
                label.setIcon(new ImageIcon(filePath + "1.png"));
                break;
            case 2: 
                label.setIcon(new ImageIcon(filePath + "2.png"));
                break;
            case 3: 
                label.setIcon(new ImageIcon(filePath + "3.png"));
                break;
            case 4: 
                label.setIcon(new ImageIcon(filePath + "4.png"));
                break;
            case 5: 
                label.setIcon(new ImageIcon(filePath + "5.png"));
                break;
            case 6:
                label.setIcon(new ImageIcon(filePath+ "6.png"));
        }

        return value;
    }
}