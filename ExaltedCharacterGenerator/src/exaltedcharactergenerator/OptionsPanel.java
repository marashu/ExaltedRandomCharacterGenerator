/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exaltedcharactergenerator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Michael
 */
public class OptionsPanel extends JPanel
{
    private JPanel jExaltType;
    private JPanel jAttributeOptions;
    public OptionsPanel()
    {
        setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Options", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        jExaltType = new ExaltTypePanel();
        jAttributeOptions = new AttributeOptionsPanel();
        add(jExaltType);
        add(jAttributeOptions);
    }
    
}
