/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exaltedcharactergenerator;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Michael
 */
public class GeneratorScreen extends JFrame
{
    private final int SCREEN_HEIGHT = 768;
    private final int SCREEN_WIDTH = 1024;
    
    private JPanel jOptions;
    private JPanel jTitle;
    public GeneratorScreen()
    {
        setTitle("Character Generator");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        jOptions = new OptionsPanel();
        jTitle = new TitlePanel();
        add(jTitle, BorderLayout.NORTH);
        add(jOptions, BorderLayout.CENTER);
        setVisible(true);
        
    }
}
