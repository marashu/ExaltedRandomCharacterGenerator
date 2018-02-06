/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exaltedcharactergenerator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Michael
 */
public class ExaltTypePanel extends JPanel
{
    //a list of castes and exalts for the combo box
    private static final String[] EXALT_TYPES = {"--RANDOM--", "Solar", "Lunar", "Sidereal", "Terrestrial", "Abyssal"};
    private static final String[][] CASTE_TYPES = {{"--RANDOM--"},
        {"--RANDOM--", "Dawn", "Zenith", "Twilight", "Night", "Eclipse"},
        {"--RANDOM", "Full Moon", "Changing Moon","No Moon", "Casteless"},
        {"--RANDOM--", "Journeys", "Serenity", "Battles", "Secrets", "Endings"},
        {"--RANDOM--", "Air", "Earth", "Fire", "Water", "Wood"}, 
        {"--RANDOM", "Dusk", "Midnight", "Daybreak", "Day", "Moonshadow"}};
    
    //declare elements
    private JComboBox boxExalts;
    private JComboBox boxCastes;
    
    private JLabel labelExalts;
    private JLabel labelCastes;
    
    public ExaltTypePanel()
    {
        setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Exalt Type Options", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        setLayout(new GridLayout(2,2));
        
        boxExalts = new JComboBox(EXALT_TYPES);
        boxCastes = new JComboBox(CASTE_TYPES[0]);
        boxCastes.setEnabled(false);
        
        boxExalts.setBorder(new EmptyBorder(10, 10, 10, 10));
        boxCastes.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        boxExalts.addActionListener(new ExaltTypeChanged());
        
        labelExalts = new JLabel("Exalt Type:");
        labelCastes = new JLabel("Caste/Aspect:");
        add(labelExalts);
        add(boxExalts);
        add(labelCastes);
        add(boxCastes);
    }
    
    private class ExaltTypeChanged implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
      {
          if(boxExalts.getSelectedIndex() == 0)
              boxCastes.setEnabled(false);
          else
              boxCastes.setEnabled(true);
          boxCastes.setModel(new DefaultComboBoxModel( CASTE_TYPES[boxExalts.getSelectedIndex()] ));
      }
    }
    
}
