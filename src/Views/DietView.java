package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public final class DietView implements JView {
    
    private final Font f2=new Font("Verdana",Font.PLAIN,15);
    private final Font f1=new Font("Verdana",Font.BOLD,15);
    private final JFrame dietView;
    private JList mealNameList;
    private JScrollPane pane;
    private DefaultListModel mealListModel;
    private JTextField tb_dietName,tb_calValue,tb_dietAmount;
    private JLabel lebel_name,lebel_ammount,cal_per_gram,lebel,lebel_notice;
    private JButton buttonAdd;
    private JPanel headerPanel,mainPanel,footerPanel,newMealPanel,addMealPanel;
    private GridBagConstraints gb;
    
    public DietView(String arg){
        dietView=new JFrame();
        dietView.setSize(new Dimension(420,420));
        dietView.setVisible(true);
        //dietView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dietView.setLocationRelativeTo(null);
        dietView.setResizable(false);
        dietView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(arg.equalsIgnoreCase("newMeal")){
            dietView.setTitle("New Meal Item");
            dietView.add(getAddNewMealForm());
            }
        else if(arg.equalsIgnoreCase("moreMeal"))
            {
             dietView.setTitle("More meal Item");
             dietView.add(getAddMoreMealForm());
            }
        
        
        refreshDietView();
       
    }
    
    void refreshDietView(){
        
        dietView.revalidate();
        dietView.repaint();
    
    }

     JPanel getAddNewMealForm(){
        newMealPanel=new JPanel(new BorderLayout());
        
        lebel_notice=new JLabel(" ");
        
        mealListModel=new DefaultListModel();
   
        
        mealNameList=new JList(mealListModel);
      
        mealNameList.setFont(new Font("Verdana",Font.PLAIN,10));
        pane=new JScrollPane(mealNameList);
        
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setPreferredSize(new Dimension(155,50));
        
        lebel=new JLabel("Enter Meal information");
        lebel.setFont(f1);
        lebel.setForeground(Color.white);
        
        
        buttonAdd=new JButton("Add");
        buttonAdd.setFont(f2);
        buttonAdd.setPreferredSize(new Dimension(100,40));
        
        lebel_name=new JLabel("New Meal Item name");
        lebel_name.setFont(f2);
        
        tb_dietName=new JTextField(10);
    
        tb_dietName.setFont(f2);
        
        cal_per_gram=new JLabel("Callorie/Gram");
        cal_per_gram.setFont(f2);
        
        tb_calValue=new JTextField(10);
        tb_calValue.setFont(f2);
      
        //calorie=new JLabel("Total Calorie");
        
        mainPanel=new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        
        gb=new GridBagConstraints();
        gb.gridx=gb.gridy=0;
        gb.gridwidth=2;
        mainPanel.add(lebel,gb);
        gb.gridwidth=1;
        gb.gridy++;
        mainPanel.add(lebel_name,gb);
        gb.gridx++;
        gb.insets=new Insets(0,5,0,0);
        mainPanel.add(tb_dietName,gb);
        gb.gridx=1;gb.gridy++;
        mainPanel.add(pane,gb);
        gb.insets=new Insets(15,5,0,0);
        gb.gridx=0;gb.gridy++;
        mainPanel.add(cal_per_gram,gb);
        gb.gridx++;
        mainPanel.add(tb_calValue,gb);
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=2;
        mainPanel.add(lebel_notice,gb);
        
        
        headerPanel=new JPanel();
        headerPanel.add(lebel);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.setBackground(new Color(39,133,238));
        
        
        footerPanel=new JPanel();
        footerPanel.add(buttonAdd);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        footerPanel.setBackground(new Color(39,133,238));
        
        
        newMealPanel.add(headerPanel,BorderLayout.NORTH);
        newMealPanel.add(mainPanel,BorderLayout.CENTER);
        newMealPanel.add(footerPanel,BorderLayout.SOUTH);
        
        return newMealPanel;
        
    }
    
    void deleteForm(){
        dietView.remove(headerPanel);
        dietView.remove(mainPanel);
        dietView.remove(footerPanel);
    }
    
    JPanel getAddMoreMealForm(){
        
        lebel_notice=new JLabel(" ");
        
            
        addMealPanel=new JPanel(new BorderLayout());
        mealListModel=new DefaultListModel();
       
        
        mealNameList=new JList(mealListModel);
        mealNameList.setFont(new Font("Verdana",Font.PLAIN,10));
        pane=new JScrollPane(mealNameList);
        
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setPreferredSize(new Dimension(155,50));
        
        lebel=new JLabel("Consumed Meal information");
        lebel.setFont(f1);
        lebel.setForeground(Color.white);
        
        
        buttonAdd=new JButton("Add");
        buttonAdd.setFont(f2);
        buttonAdd.setPreferredSize(new Dimension(100,40));
        
        lebel_name=new JLabel("Meal Item name");
        lebel_name.setFont(f2);
        
        tb_dietName=new JTextField(10);
    
        tb_dietName.setFont(f2);
        
        lebel_ammount=new JLabel("Meal amount(in gram)");
        lebel_ammount.setFont(f2);
        
        tb_dietAmount=new JTextField(10);
        tb_dietAmount.setFont(f2);
        
        
        
        mainPanel=new JPanel(new GridBagLayout());
        gb=new GridBagConstraints();
        gb.gridx=gb.gridy=0;
        gb.gridwidth=2;
        mainPanel.add(lebel,gb);
        gb.gridwidth=1;
        gb.gridy++;
        mainPanel.add(lebel_name,gb);
        gb.gridx++;
        gb.insets=new Insets(0,5,0,0);
        mainPanel.add(tb_dietName,gb);
        gb.gridx=1;gb.gridy++;
        mainPanel.add(pane,gb);
        gb.insets=new Insets(15,5,0,0);
        gb.gridx=0;gb.gridy++;
        mainPanel.add(lebel_ammount,gb);
        gb.gridx++;
        mainPanel.add(tb_dietAmount,gb);
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=2;
        mainPanel.add(lebel_notice,gb);
        gb.gridy++;
       
  
        mainPanel.setBackground(Color.white);
        
        headerPanel=new JPanel();
        headerPanel.add(lebel);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.setBackground(new Color(39,133,238));
        
        
        footerPanel=new JPanel();
        footerPanel.add(buttonAdd);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        footerPanel.setBackground(new Color(39,133,238));
        
        
        addMealPanel.add(headerPanel,BorderLayout.NORTH);
        addMealPanel.add(mainPanel,BorderLayout.CENTER);
        addMealPanel.add(footerPanel,BorderLayout.SOUTH);

        return addMealPanel;
    }

    @Override
    public void addTo(UIView u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeFrom(UIView u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel getPanel(String panelName) {
        if(panelName.equalsIgnoreCase("mainPanel"))
            return mainPanel;
        else
            return null;
    }

    @Override
    public void add(JPanel panel, String position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(JPanel panel,String position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JButton getButton(String name) {
      
        if(name.equalsIgnoreCase("buttonAdd"))
            return buttonAdd;
        else
            return null;
    }

    @Override
    public JTextField getTextBox(String name) {
        
        if(name.equalsIgnoreCase("tb_dietName"))
            return tb_dietName;
        else if(name.equalsIgnoreCase("tb_calValue"))
            return tb_calValue;
        else if(name.equalsIgnoreCase("tb_dietAmount"))
            return tb_dietAmount;
        else
            return null;
    }

    @Override
    public JPasswordField getPasswordField(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JLabel getJLabel(String name) {
        
        if(name.equalsIgnoreCase("lebel_notice"))
                return lebel_notice;
       else 
                 return null;
             
    }

    @Override
    public JComboBox getJComboBox(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel getDefaultListModel(String listName) {
    return mealListModel;
            
    }

    @Override
    public JList getJList() {
        return mealNameList;
    }

    @Override
    public JFrame getJFrame(String frameName) {
        if(frameName.equalsIgnoreCase("dietView"))
            return dietView;
        else
            return null;
    }

    @Override
    public JSlider getJSlider(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel getDefaultTableModel(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JScrollPane getScollPane(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
