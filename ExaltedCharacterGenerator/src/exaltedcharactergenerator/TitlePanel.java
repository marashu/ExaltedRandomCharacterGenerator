/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exaltedcharactergenerator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Michael
 */
public class TitlePanel extends JPanel
{
    private JLabel jTitle;
    
    public TitlePanel()
    {
        this.setBorder(new LineBorder(Color.BLACK));
        jTitle = new JLabel("Exalted 1st Edition Random Character Generator");
        add(jTitle);
    }
}
