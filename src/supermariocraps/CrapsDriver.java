package supermariocraps;
import javax.swing.JFrame;

/**
 * Driver for Super Mario Craps.
 * 
 * How to play Craps:
 * In order to play, you need to bet money. You can do this using any of the 
 * bet buttons or the bet text field near the top of the frame. Once you have 
 * wagered money, you are ready to roll some dice.
 * 
 * Click the 'initial roll' button. For your initial roll ONLY:
 *  -If you roll a 7 or an 11, you win and receive double what you wagered!
 *  -If you roll a 2, 3, or 12, you lose what you wagered.
 *  -If you roll ANYTHING else (4, 5, 6, 8, 9, 10), that new number becomes 
 *   your 'goal.'
 * 
 * Once you have a goal, click the 'roll again' button. For all subsequent 
 * rolls:
 *  -If you roll your goal, you win!
 *  -If you roll a 7 or an 11, you lose.
 *  -If you roll ANYTHING else, you must keep rolling until you roll your goal, 
 *   a 7, or an 11.
 * 
 * If you win, you get double your wager! If you lose, your wager is lost.
 * 
 * If you run our of money, you lose.
 * 
 * Your ultimate objective is to earn as much money as possible. Good luck!
 * 
 * @author ben31w
 * @version 2025.09.06
 */
public class CrapsDriver {
    /** displays title screen */
    private static JFrame frame1;
    
    /** displays character select screen */
    private static JFrame frame2;
    
    /** displays the game screen */
    private static JFrame frame3;
    
    
    /**
     * Given an int, switches to the corresponding JFrame (1, 2, or 3).
     * <ol>
     * <li>1 = title screen</li>
     * <li>2 = character select screen</li>
     * <li>3 = game screen</li>
     * </ol>
     * 
     * @param num
     *           the frame number to switch to
     */
    public static void changeFrame(int num) {
        if(num == 1) {
            frame1.setVisible(true);
            frame2.setVisible(false);
            frame3.setVisible(false);
            
            frame1.setLocation(frame3.getX(), frame3.getY());
            frame1.setSize(frame3.getWidth(), frame3.getHeight());
        }
        else if(num == 2) {
            frame1.setVisible(false); 
            frame2.setVisible(true);
            frame3.setVisible(false);
          
            frame2.setLocation(frame1.getX(), frame1.getY());
            frame2.setSize(frame1.getWidth(), frame1.getHeight());
        }
        else if (num == 3) {
            frame1.setVisible(false);
            frame2.setVisible(false);       
            frame3.setVisible(true);
            
            frame3.setLocation(frame2.getX(), frame2.getY());
            frame3.setSize(frame2.getWidth(), frame2.getHeight());            

            // Replace the content in frame3 with a new CrapsPanel.
            frame3.getContentPane().removeAll();
            frame3.getContentPane().add(new CrapsPanel());
            frame3.revalidate();
            frame3.repaint();
        }
    }

    
    /**
     * Set basic properties for each JFrame.
     * 
     * @param jf
     *          a JFrame
     */
    private static void setFrame(JFrame jf) {
        jf.setSize(700, 450);
        jf.setLocationRelativeTo( null );
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /**
     * Run the game.
     * 
     * @param args
     *          not used here
     */
    public static void main(String[]args) {
        frame1 = new JFrame("Super Mario Craps");
        setFrame(frame1);
        frame1.setContentPane(new TitleScreen());
        frame1.setVisible(true);
        
        
        frame2 = new JFrame("Choose your character");
        setFrame(frame2);
        frame2.setContentPane(new CharacterSelectScreen());
        frame2.setVisible(false);
        
        frame3 = new JFrame("Super Mario Craps");
        setFrame(frame3);
        frame3.setContentPane(new CrapsPanel());
        frame3.setVisible(false);
    }
   
}