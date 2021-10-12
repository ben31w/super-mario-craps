import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The title screen.
 * 
 * @author ben31w
 * @version 2021.01.04
 */
public class TitleScreen extends JPanel {
    /** play button */
    private JButton button;

    /**
     * Construct the panel.
     */
    public TitleScreen() {
        setLayout(new GridLayout(1,1));

        button = new JButton();
        button.setIcon(new ImageIcon("images/titleScreen.png"));
        button.addActionListener(new Listener());

        add(button);
    }
    
    /**
     * Listener for the button, which takes the player to the character select 
     * screen.
     * 
     * @author ben31w
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CrapsDriver.changeFrame(2);
        }
    }
}