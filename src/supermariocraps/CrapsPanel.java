package supermariocraps;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JPanel containing the craps "game field".
 *  
 * @author ben31w
 * @version 2025.09.06
 */
public class CrapsPanel extends JPanel {
    /** the pair of dice used during the initial roll */
    private Dice dice1;
    
    /** the pair of dice used during all rolls after initial roll */
    private Dice dice2;
    
    /** bet $5 */
    private JButton bet25Button;
    
    /** bet $100 */
    private JButton bet100Button;
    
    /** bet $500 */
    private JButton bet500Button;
    
    /** resets the player's wager to 0 */
    private JButton clearWagerButton;
    
    /** bet half the player's money */
    private JButton betHalfButton;
    
    /** bet all the player's money */
    private JButton allInButton;
    
    /** make the initial roll */
    private JButton initialRollButton;
    
    /** make all subsequent rolls */
    private JButton rollAgainButton;
    
    /** quit the current game */
    private JButton quitButton;
    
    /** displays the player's money */
    private JLabel moneyLabel;
    
    /** displays the player's wager */
    private JLabel wagerLabel;
    
    /** tells the player they can enter a custom bet */
    private JLabel customBetLabel;
    
    /** displays the player's win streak */
    private JLabel streakLabel;
    
    /** displays the game's high score */
    private JLabel highScoreLabel;
    
    /** displays the mugshot of the selected character */
    private JLabel mugshotLabel;
    
    /** displays the player's goal (what they need to roll to get money; e.g., 4,5,6,8,9,10) */
    private JLabel goalLabel;
    
    /** displays the player's win-loss record */
    private JLabel winLossLabel;
    
    /** text field for betting custom amounts */
    private JTextField textfield;
    
    /** the player's money */
    private int money = 500;
    
    /** the player's wager */
    private int wager = 0;
    
    /** the number the player needs to roll to win money */
    private int goal;
    
    /** the player's wins */
    private int wins = 0;
    
    /** the player's losses */
    private int losses = 0;
    
    /** the player's win streak */
    private int streak = 0;
    
    /** the game's high score */
    private int highScore;
    
    /** the character the player has chosen (mario, luigi, peach, dk, wario, yoshi) */
    private String chosen = "mario";
    
    /** used to set the layout of the panel */
    private GridBagConstraints gbc = new GridBagConstraints();
    
    // CONSTANTS
    private static final String MONEY_FORMAT_STRING = "$%,d";
    private static final String WAGER_FORMAT_STRING = "Wager: $%,d";

    // Images for different character moods.
    private final ImageIcon neutral;
    private final ImageIcon happy;
    private final ImageIcon sad;
    private final ImageIcon depressed;

    
    /**
     * Construct the panel. Define the panel's layout and initialize variables 
     * needed throughout the rest of the program.
     */
    public CrapsPanel() {
        // Get the player's character and the high score.
        chosen = getChosenCharacter("chosen.txt");
        highScore = getHighScore("highScore.txt");

        // Get Images for different character moods.
        // Since images are in a subdirectory of supermariocraps package, we can use
        //  a relative path inside getResource.
        neutral   = new ImageIcon(getClass().getResource(String.format("images/%s/neutral.png", chosen)));
        happy     = new ImageIcon(getClass().getResource(String.format("images/%s/happy.png", chosen)));
        sad       = new ImageIcon(getClass().getResource(String.format("images/%s/sad.png", chosen)));
        depressed = new ImageIcon(getClass().getResource(String.format("images/%s/depressed.png", chosen)));
        
        // Set the panel layout.
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);

        // first row (gbc.gridy = 0 or 1)
        // Displays the player's money and wager. Also contains buttons for 
        // betting $25, $100, $500, and a text field for betting custom amounts.
        moneyLabel = new JLabel(String.format(MONEY_FORMAT_STRING, money));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(moneyLabel, gbc);

        wagerLabel = new JLabel(String.format(WAGER_FORMAT_STRING, wager));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(wagerLabel, gbc);

        bet25Button = new JButton("Bet $25");
        bet25Button.addActionListener(new BetListener());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(bet25Button, gbc);

        bet100Button = new JButton("Bet $100");
        bet100Button.addActionListener(new BetListener());
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(bet100Button, gbc);

        bet500Button = new JButton("Bet $500");
        bet500Button.addActionListener(new BetListener());
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(bet500Button, gbc);

        customBetLabel = new JLabel("Bet Custom Amount: ");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(customBetLabel, gbc);

        textfield = new JTextField();
        textfield.setToolTipText("Type a number and press Enter");
        textfield.addActionListener(new CustomBetListener());
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textfield, gbc);

        // second row (gbc.gridy = 2)
        // Contains buttons for clearing the wager, betting half the money, and 
        // betting all the money. Also displays the user's win streak and high score.
        clearWagerButton = new JButton("Clear Wager");
        clearWagerButton.addActionListener(new ClearWagerListener());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(clearWagerButton, gbc);

        betHalfButton = new JButton("Bet Half");
        betHalfButton.addActionListener(new BetListener());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(betHalfButton, gbc);

        allInButton = new JButton("ALL IN");
        allInButton.addActionListener(new BetListener());
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(allInButton, gbc);

        streakLabel = new JLabel("Win Streak: " + streak);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(streakLabel, gbc);
        
        highScoreLabel = new JLabel(String.format("High Score: $%,d", highScore));
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(highScoreLabel, gbc);
        
        // third row  (gbc.gridy = 3)
        // Contains the initial roll button, the dice for the initial roll, the 
        // mugshot of the player's character, and the player's goal (if applicable).
        initialRollButton = new JButton("Initial Roll");
        initialRollButton.addActionListener(new DiceListener());
        initialRollButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(initialRollButton, gbc);

        dice1 = new Dice();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(dice1, gbc);

        mugshotLabel = new JLabel();
        mugshotLabel.setIcon(neutral);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        add(mugshotLabel, gbc);
        
        goalLabel = new JLabel("Goal: ");
        goalLabel.setFont(new Font("calibri", Font.BOLD, 30));
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(goalLabel, gbc);

        // fourth row (gbc.gridy = 5)
        // Contains the roll again button and dice, the player's W-L record, 
        // and the quit button.
        rollAgainButton = new JButton("Roll Again");
        rollAgainButton.addActionListener(new DiceListener());
        rollAgainButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(rollAgainButton, gbc);

        dice2 = new Dice();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(dice2, gbc);

        winLossLabel = new JLabel("W-L");
        winLossLabel.setFont(new Font("calibri", Font.BOLD, 40));
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(winLossLabel, gbc);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitListener());
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(quitButton, gbc);
    }
    

    /**
     * Listener for all buttons that bet money. These buttons add to the 
     * player's wager while subtracting the same amount from their money. They 
     * fail if the user doesn't have enough money for the value indicated on 
     * the button.
     * 
     * @author ben31w
     */
    private class BetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {            
            int value = 0;
            
            if (e.getSource() == bet25Button) {
                value = 25;
            }
            else if (e.getSource() == bet100Button) {
                value = 100;
            }
            else if (e.getSource() == bet500Button) {
                value = 500;
            }
            else if (e.getSource() == betHalfButton) {   
                // If the player's money is even, wager half of it.
                if(money % 2 == 0) {
                    value = money / 2;
                }
                // If the player's money is odd, wager half of it rounded up.
                else {
                    value = (money + 1) / 2;
                }
            }
            else if (e.getSource() == allInButton) {
                value = money;
            }

            // Check if the player has enough money to wager.
            if (money >= value) {
                money -= value;
                wager += value;
                moneyLabel.setForeground(Color.black);
            }
            else {
                moneyLabel.setForeground(Color.red);
            }         
            
            // If the player has a wager, activate the initial roll button.
            if (wager > 0) {
                initialRollButton.setEnabled(true);
            }
            else {
                initialRollButton.setEnabled(false);
            }

            // Set labels.
            moneyLabel.setText( String.format(MONEY_FORMAT_STRING, money) );
            wagerLabel.setText( String.format(WAGER_FORMAT_STRING, wager) );
            mugshotLabel.setIcon(neutral);
        }
    }
    

    /**
     * Listener for the text field. The text field takes a custom bet that the 
     * user enters. But it fails if the user tries to bet more money than they 
     * have.
     * 
     * @author ben31w
     */
    private class CustomBetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int value = Integer.parseInt(textfield.getText());
                // The player can only wager positive numbers.
                if (value > 0) {
                    // Check if the amount the player is trying to wager is less 
                    // than or equal to the money. The player can't wager more 
                    // money then they have.
                    if(money >= value) {
                        money -= value;
                        wager += value;
    
                        moneyLabel.setForeground(Color.black);
                    }
                    else {
                        moneyLabel.setForeground(Color.red);
                        initialRollButton.setEnabled(false);
                    }
                    
                    // If the player has a wager, activate the initial roll 
                    // button.
                    if (wager > 0) {
                        initialRollButton.setEnabled(true);
                    }
                    else {
                        initialRollButton.setEnabled(false);
                    }
                    
                    // Set labels and reset text field.
                    moneyLabel.setText( String.format(MONEY_FORMAT_STRING, money) );
                    wagerLabel.setText( String.format(WAGER_FORMAT_STRING, wager) );
                    textfield.setText("");
                    mugshotLabel.setIcon(neutral);
                }
            }
            catch (NumberFormatException f) {
                System.out.println("You can't wager a non-integer!");
            }
        }
    }
    
    
    /**
     * Listener for the clear wager button. This button resets the wager to 0, 
     * and adds the wager back to the player's money.
     * 
     * @author ben31w
     */
    private class ClearWagerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            money += wager;
            wager = 0;
            
            moneyLabel.setForeground(Color.black);
            moneyLabel.setText( String.format(MONEY_FORMAT_STRING, money) );
            wagerLabel.setText( String.format(WAGER_FORMAT_STRING, wager) );
            
            initialRollButton.setEnabled(false);
        }
    }
    
    
    /**
     * Listener for the dice roll buttons. These buttons simulate a dice roll 
     * (create a number between 2-12) and update the images of the dice on the panel.
     * 
     * @author ben31w
     */
    private class DiceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == initialRollButton) {
                goal = dice1.roll();

                if(goal == 7 || goal == 11) {
                    win();
                }
                else if(goal == 2 || goal == 3 || goal == 12) {
                    loss();
                }
                else {
                    setGoal();
                }
                
                moneyLabel.setForeground(Color.black);
            }

            else if(e.getSource() == rollAgainButton) {
                int newRoll = dice2.roll();

                if(newRoll == goal) {
                    win();
                }
                else if(newRoll == 7 || newRoll == 11) {
                    loss();
                }
            }
        }
    }
    

    /**
     * Listener for the quit button. This button exits the game.
     * 
     * @author ben31w
     */
    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CrapsDriver.changeFrame(1);
        }
    }
    
    
    /**
     * Return the character that the player has chosen (i.e., the first string 
     * in the given text file).
     * 
     * @param filePath
     *              a text file to be read
     * @return the first string in the file
     */
    private String getChosenCharacter(String filePath) {
        try {
            Scanner fout = new Scanner(new File(filePath));
            chosen = fout.next();
            fout.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Failed to open " + filePath);
        }

        return chosen;
    }
    
    
    /**
     * Return the high score (i.e., the first integer in the given text file).
     * 
     * @param filePath
     *          a text file to be read
     * @return the first integer in the file
     */
    private int getHighScore(String filePath) {
        try {
            Scanner fout = new Scanner(new File(filePath));
            highScore = fout.nextInt();
            fout.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Failed to open " + filePath);
            System.out.println("Creating " + filePath);
            
            try {
                PrintWriter fin = new PrintWriter(new File(filePath));
                fin.println(500);
                fin.close();
            }
            catch (FileNotFoundException f) {
                System.out.println("Failed to create " + filePath);
            }
            
            return 0;
        }
        return highScore;
    }
    
    
    /**
     * Update the high score label and text file.
     */
    private void updateHighScore(String filePath) {
        highScore = money;
        try {
            PrintWriter fin = new PrintWriter(new File(filePath));
            fin.println(highScore);
            fin.close();
        }
        catch (FileNotFoundException f) {
            System.out.println("Failed to open " + filePath);
        }
        
        highScoreLabel.setText(String.format("High Score: $%,d", highScore));
    }

    
    /**
     * Called when the player wins a round. Award the player double their 
     * wager, and reset the buttons for the next round.
     */
    private void win() {
        goalLabel.setFont(new Font("calibri", Font.BOLD, 15));
        goalLabel.setText("You won $" + wager*2 + "!");
        mugshotLabel.setIcon(happy);

        money += wager*2;
        wager = 0;
        wins += 1;
        streak += 1;

        moneyLabel.setText( String.format(MONEY_FORMAT_STRING, money) );
        wagerLabel.setText( String.format(WAGER_FORMAT_STRING, wager) );
        winLossLabel.setText(wins + "-" + losses);
        streakLabel.setText("Streak: " + streak); 

        resetButtons();
        
        // Update the high score if necessary.
        if (money > highScore) {
            updateHighScore("highScore.txt");
        }
    }
    
    
    /**
     * Called when the player loses a round. Set the wager to 0 and prepare for 
     * the next round (if the user still has money).
     */
    private void loss() {
        goalLabel.setFont(new Font("calibri", Font.BOLD, 15));
        goalLabel.setText("You lost $" + wager + "...");

        wager = 0;
        losses = losses + 1;
        streak = 0;

        wagerLabel.setText(String.format(WAGER_FORMAT_STRING, wager));
        winLossLabel.setText(wins + "-" + losses);
        streakLabel.setText("Streak: " + streak);

        if(money > 0) {
            mugshotLabel.setIcon(sad);
            resetButtons();
        }
        else {
            goalLabel.setText("GAME OVER");
            mugshotLabel.setIcon(depressed);
            disableButtons();
        }
    }
    
    
    /**
     * Disable all buttons and text fields.
     */
    private void disableButtons() {
        bet25Button.setEnabled(false);
        bet100Button.setEnabled(false);
        bet500Button.setEnabled(false);
        textfield.setEnabled(false);
        clearWagerButton.setEnabled(false);
        betHalfButton.setEnabled(false);
        allInButton.setEnabled(false);

        initialRollButton.setEnabled(false);
        rollAgainButton.setEnabled(false);
    }
    
    
    /**
     * Reset the buttons for a new round.
     * */
    private void resetButtons() {
        // Enable all betting buttons.
        bet25Button.setEnabled(true);
        bet100Button.setEnabled(true);
        bet500Button.setEnabled(true);
        textfield.setEnabled(true);
        clearWagerButton.setEnabled(true);
        betHalfButton.setEnabled(true);
        allInButton.setEnabled(true);

        // Disable all rolling buttons.
        initialRollButton.setEnabled(false);
        rollAgainButton.setEnabled(false);
    }
    
    
    /**
     * Set the goal, and disable all buttons except the roll again button.
     */
    private void setGoal() {
        goalLabel.setFont(new Font("calibri", Font.BOLD, 30));
        goalLabel.setText("Goal: " + goal);

        disableButtons();
        rollAgainButton.setEnabled(true);
    }
    
    
}