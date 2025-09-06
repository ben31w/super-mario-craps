package supermariocraps;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The character select screen.
 * 
 * @author ben31w
 * @version 2021.01.05
 */
public class CharacterSelectScreen extends JPanel {
    /** label that tells user to select a character */
    private JLabel label;
    
    /** the character buttons */
    private JButton mario, luigi, peach, yoshi, wario, dk;
    
    /** the character the user has chosen */
    protected String chosen = "mario";
    
    /** used to set panel's layout */
    private GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Construct the panel.
     */
    public CharacterSelectScreen() {
        setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 5, 5, 5);

        // Create the label.
        label = new JLabel("Choose your character");
        label.setFont(new Font("calibri", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        add(label, gbc);

        // All buttons have the same width and height.
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Since images are in a subdirectory of supermariocraps package, we can use
        //  a relative path inside getResource.

        // First row of buttons (gbc.gridy = 1)
        mario = new JButton();
        mario.setIcon(new ImageIcon(getClass().getResource("images/mario/mugshot.png")));
        setButton(mario);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(mario, gbc);

        luigi = new JButton();
        luigi.setIcon(new ImageIcon(getClass().getResource("images/luigi/mugshot.png")));
        setButton(luigi);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(luigi, gbc);

        peach = new JButton();
        peach.setIcon(new ImageIcon(getClass().getResource("images/peach/mugshot.png")));
        setButton(peach);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(peach, gbc);

        // Second row of buttons (gbc.gridy = 2)
        yoshi = new JButton();
        yoshi.setIcon(new ImageIcon(getClass().getResource("images/yoshi/mugshot.png")));
        setButton(yoshi);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(yoshi, gbc);

        wario = new JButton();
        wario.setIcon(new ImageIcon(getClass().getResource("images/wario/mugshot.png")));
        setButton(wario);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(wario, gbc);

        dk = new JButton();
        dk.setIcon(new ImageIcon(getClass().getResource("images/dk/mugshot.png")));
        setButton(dk);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(dk, gbc);
    }
    
    
    /**
     * Listener for the buttons.
     * 
     * @author ben31w
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            // Remember which button was pressed.
            if (e.getSource() == mario) {
                chosen = "mario";
            }
            else if (e.getSource() == luigi) {
                chosen = "luigi";
            }
            else if (e.getSource() == peach) {
                chosen = "peach";
            }
            else if (e.getSource() == yoshi) {
                chosen = "yoshi";
            }
            else if (e.getSource() == wario) {
                chosen = "wario";
            }
            else if (e.getSource() == dk) {
                chosen = "dk";
            }
            
            // Write the chosen character to a text file.
            String filePath = "chosen.txt";
            try {
                PrintWriter fout = new PrintWriter(new File(filePath));
                fout.println(chosen);
                fout.close();
            }
            catch (FileNotFoundException f) {
                System.out.println("Failed to open " + filePath);
            }
            
            // Load the game panel.
            CrapsDriver.changeFrame(3);
        }
    }
    
    
    /**
     * Set the JButton's properties.
     */
    private void setButton(JButton jb) {
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        jb.addActionListener(new Listener());
    }
}