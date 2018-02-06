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
import java.util.ArrayList;
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
public class AttributeOptionsPanel extends JPanel
{
    private static final String[] ATTRIBUTE_CATEGORIES = {"--RANDOM--", "Physical", "Social", "Mental"};
    
    private JComboBox boxPrimary;
    private JComboBox boxSecondary;
    private JComboBox boxTertiary;
    
    private JLabel labelPrimary;
    private JLabel labelSecondary;
    private JLabel labelTertiary;
    
    //private int iNumSelected = 0;
    //private boolean bPrimarySelected = false;
    //private boolean bSecondarySelected = false;
    //private boolean bTertiarySelected = false;
    
    public AttributeOptionsPanel()
    {
        setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Attribute Options", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        boxPrimary = new JComboBox(ATTRIBUTE_CATEGORIES);
        boxSecondary = new JComboBox(ATTRIBUTE_CATEGORIES);
        boxTertiary = new JComboBox(ATTRIBUTE_CATEGORIES);
        
        boxPrimary.setName("Primary");
        boxSecondary.setName("Secondary");
        boxTertiary.setName("Tertiary");
        
        boxPrimary.setBorder(new EmptyBorder(10, 10, 10, 10));
        boxSecondary.setBorder(new EmptyBorder(10, 10, 10, 10));
        boxTertiary.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        boxPrimary.addActionListener(new CategoryChanged());
        boxSecondary.addActionListener(new CategoryChanged());
        boxTertiary.addActionListener(new CategoryChanged());
        
        labelPrimary = new JLabel("Primary Attributes:");
        labelSecondary = new JLabel("Secondary Attributes:");
        labelTertiary = new JLabel("Tertiary Attributes:");
        
        setLayout(new GridLayout(3,2));
        
        add(labelPrimary);
        add(boxPrimary);
        add(labelSecondary);
        add(boxSecondary);
        add(labelTertiary);
        add(boxTertiary);
        
    }
    
    private class CategoryChanged implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
      {
          String name = ((JComboBox)(e.getSource())).getName();
          String sPrimary = (String)boxPrimary.getSelectedItem();
          String sSecondary = (String)boxSecondary.getSelectedItem();
          String sTertiary = (String)boxTertiary.getSelectedItem();
          DefaultComboBoxModel model;
          switch(name)
          {
              case "Primary":
                  if(sPrimary == "--RANDOM--")
                  {
                      //update Secondary and Tertiary to include Primary's value
                      //Start with Tertiary
                      if(sTertiary == "--RANDOM--")
                      {
                          //In this case, primary and tertiary are random, so all should be full
                          if(sSecondary == "--RANDOM--")
                          {
                              boxSecondary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxTertiary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxPrimary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                          }
                          else//in this case, make sure to keep secondary's value, and don't give the others access to it
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if tertiary is not random
                      else
                      {
                          if(sSecondary == "--RANDOM--")
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, don't let them access each 
                          else
                          {
                              ArrayList<String> tempSecondary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      tempSecondary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              ArrayList<String> tempTertiary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      tempTertiary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempSecondary.toArray());
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              model = new DefaultComboBoxModel(tempTertiary.toArray());
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i] && sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }//end else (secondary not random)
                      }//end else (tertiary not random)
                  }//end if primary is random
                  else //this is if primary has a value
                  {
                      //Start with Tertiary
                      if(sTertiary == "--RANDOM--")
                      {
                          //In this case, do not let secondary or tertiary have primary's value
                          if(sSecondary == "--RANDOM--")
                          {
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          else//in this case, keep only selected and random as option for secondary
                          {
                              ArrayList<String> tempSecondary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      tempSecondary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempSecondary.toArray());
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i] && sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if tertiary is not random
                      else
                      {
                          //in this case, keep only selected and random as option for tertiary
                          if(sSecondary == "--RANDOM--")
                          {
                              ArrayList<String> tempTertiary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      tempTertiary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempTertiary.toArray());
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i] && sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, make sure everything can only access itself and random
                          else
                          {
                              String[] tempSecondary = {"--RANDOM--", sSecondary};
                              String[] tempTertiary = {"--RANDOM--", sTertiary};
                              model = new DefaultComboBoxModel(tempSecondary);
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              model = new DefaultComboBoxModel(tempTertiary);
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              
                          }//end else (secondary not random)
                      }//end else (tertiary not random)
                  }//end else (primary not random)
                  break;
              case "Secondary":
                  if(sSecondary == "--RANDOM--")
                  {
                      //update Tertiary and Primary to include Secondary's value
                      //Start with Primary
                      if(sPrimary == "--RANDOM--")
                      {
                          //In this case, secondary and primary are random, so all should be full
                          if(sTertiary == "--RANDOM--")
                          {
                              boxTertiary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxPrimary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxSecondary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                          }
                          else//in this case, make sure to keep tertiary's value, and don't give the others access to it
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if primary is not random
                      else
                      {
                          if(sTertiary == "--RANDOM--")
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, make sure they can't access each other
                          else
                          {
                              ArrayList<String> tempTertiary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      tempTertiary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              ArrayList<String> tempPrimary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      tempPrimary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempTertiary.toArray());
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              model = new DefaultComboBoxModel(tempPrimary.toArray());
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i] && sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }//end else (tertiary not random)
                      }//end else (primary not random)
                  }//end if secondary is random
                  else //this is if secondary has a value
                  {
                      //Start with Primary
                      if(sPrimary == "--RANDOM--")
                      {
                          //In this case, do not let tertiary or primary have secondary's value
                          if(sTertiary == "--RANDOM--")
                          {
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          else//in this case, keep only selected and random as option for tertiary
                          {
                              ArrayList<String> tempTertiary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      tempTertiary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempTertiary.toArray());
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i] && sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if primary is not random
                      else
                      {
                          //in this case, keep only selected and random as option for primary
                          if(sTertiary == "--RANDOM--")
                          {
                              ArrayList<String> tempPrimary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      tempPrimary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempPrimary.toArray());
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i] && sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, make sure everything can only access itself and random
                          else
                          {
                              String[] tempTertiary = {"--RANDOM--", sTertiary};
                              String[] tempPrimary = {"--RANDOM--", sPrimary};
                              model = new DefaultComboBoxModel(tempTertiary);
                              model.setSelectedItem(boxTertiary.getSelectedItem());
                              boxTertiary.setModel(model);
                              model = new DefaultComboBoxModel(tempPrimary);
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              
                          }//end else (tertiary not random)
                      }//end else (primary not random)
                  }//end else (secondary not random)
                  break;
              case "Tertiary":
                  if(sTertiary == "--RANDOM--")
                  {
                      //update Primary and Secondary to include Tertiary's value
                      //Start with Secondary
                      if(sSecondary == "--RANDOM--")
                      {
                          //In this case, tertiary and secondary are random, so all should be full
                          if(sPrimary == "--RANDOM--")
                          {
                              boxPrimary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxSecondary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                              boxTertiary.setModel(new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES));
                          }
                          else//in this case, make sure to keep primary's value, and don't give the others access to it
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if secondary is not random
                      else
                      {
                          if(sPrimary == "--RANDOM--")
                          {
                              model = new DefaultComboBoxModel(ATTRIBUTE_CATEGORIES);
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, make sure they can't access each other
                          else
                          {
                              ArrayList<String> tempPrimary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      tempPrimary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              ArrayList<String> tempSecondary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i])
                                      tempSecondary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempPrimary.toArray());
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              model = new DefaultComboBoxModel(tempSecondary.toArray());
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i] && sSecondary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxTertiary.setModel(new DefaultComboBoxModel(list.toArray()));
                              
                          }//end else (primary not random)
                      }//end else (secondary not random)
                  }//end if tertiary is random
                  else //this is if tertiary has a value
                  {
                      //Start with Secondary
                      if(sSecondary == "--RANDOM--")
                      {
                          //In this case, do not let primary or secondary have tertiary's value
                          if(sPrimary == "--RANDOM--")
                          {
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          else//in this case, keep only selected and random as option for primary
                          {
                              ArrayList<String> tempPrimary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      tempPrimary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempPrimary.toArray());
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sPrimary != ATTRIBUTE_CATEGORIES[i] && sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxSecondary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                      }
                      //if secondary is not random
                      else
                      {
                          //in this case, keep only selected and random as option for secondary
                          if(sPrimary == "--RANDOM--")
                          {
                              ArrayList<String> tempSecondary = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      tempSecondary.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              model = new DefaultComboBoxModel(tempSecondary.toArray());
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              ArrayList<String> list = new ArrayList();
                              for(int i = 0; i < ATTRIBUTE_CATEGORIES.length; i++)
                              {
                                  if(sSecondary != ATTRIBUTE_CATEGORIES[i] && sTertiary != ATTRIBUTE_CATEGORIES[i])
                                      list.add(ATTRIBUTE_CATEGORIES[i]);
                              }
                              boxPrimary.setModel(new DefaultComboBoxModel(list.toArray()));
                          }
                          //to keep it clean, if both are not random, make sure everything can only access itself and random
                          else
                          {
                              String[] tempPrimary = {"--RANDOM--", sPrimary};
                              String[] tempSecondary = {"--RANDOM--", sSecondary};
                              model = new DefaultComboBoxModel(tempPrimary);
                              model.setSelectedItem(boxPrimary.getSelectedItem());
                              boxPrimary.setModel(model);
                              model = new DefaultComboBoxModel(tempSecondary);
                              model.setSelectedItem(boxSecondary.getSelectedItem());
                              boxSecondary.setModel(model);
                              
                          }//end else (primary not random)
                      }//end else (secondary not random)
                  }//end else (tertiary not random)
                  break;
              default:
                  break;
          }
      }
    }
}
