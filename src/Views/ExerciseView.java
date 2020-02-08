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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ExerciseView implements JView{
    
    private final Font f2=new Font("Verdana",Font.PLAIN,15);
    private final Font f1=new Font("Verdana",Font.BOLD,15);
    private final JFrame exerciseView;
    private JList exList;
    private JScrollPane pane;
    private DefaultListModel exListModel;
    private JTextField tb_exName,tb_calValue,tb_exDuration;
    private JLabel lebel_name,lebel_duration,cal_per_min,calorie,lebel,lebel_notice;
    private JButton buttonAdd;
    private JPanel headerPanel,mainPanel,footerPanel,newExPanel,addExMeal;
    private GridBagConstraints gb;
    
    public ExerciseView(String arg){
        exerciseView=new JFrame();
        
        exerciseView.setSize(new Dimension(420,420));
        exerciseView.setVisible(true);
        //exerciseView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exerciseView.setLocationRelativeTo(null);
        exerciseView.setResizable(false);
        
          
        if(arg.equalsIgnoreCase("newExercise")){
            exerciseView.setTitle("New Exercise details");
            exerciseView.add(getAddNewExerciseForm());
            }
        else if(arg.equalsIgnoreCase("moreExercise"))
            {
             exerciseView.setTitle("Add More workout ");
             exerciseView.add(getAddMoreExerciseForm());
            }
        refreshDietView();
       
    }
    
    void refreshDietView(){
        
        exerciseView.revalidate();
        exerciseView.repaint();
    
    }

     JPanel getAddNewExerciseForm(){
        newExPanel=new JPanel(new BorderLayout());
        lebel_notice=new JLabel(" ");
        
        exListModel=new DefaultListModel();
        
        exList=new JList(exListModel);
        exList.setFont(new Font("Verdana",Font.PLAIN,10));
        pane=new JScrollPane(exList);
        
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setPreferredSize(new Dimension(155,50));
        
        lebel=new JLabel("Enter Exercise information");
        lebel.setFont(f1);
        lebel.setForeground(Color.white);
        
        
        buttonAdd=new JButton("Add");
        buttonAdd.setFont(f2);
        buttonAdd.setPreferredSize(new Dimension(100,40));
        
        lebel_name=new JLabel("New Exercise type");
        lebel_name.setFont(f2);
        
        tb_exName=new JTextField(10);
    
        tb_exName.setFont(f2);
        
        cal_per_min=new JLabel("Callorie/Minute Burn");
        cal_per_min.setFont(f2);
        
        tb_calValue=new JTextField(10);
        tb_calValue.setFont(f2);
        
        lebel_duration=new JLabel("Duration (in minutes)");
        
        
        //calorie=new JLabel("Total Calorie");
        
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
        mainPanel.add(tb_exName,gb);
        gb.gridx=1;gb.gridy++;
        mainPanel.add(pane,gb);
        gb.insets=new Insets(15,5,0,0);
        gb.gridx=0;gb.gridy++;
        mainPanel.add(cal_per_min,gb);
        gb.gridx++;
        mainPanel.add(tb_calValue,gb);
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=2;
        mainPanel.add(lebel_notice,gb);
        mainPanel.setBackground(Color.white);
        
        headerPanel=new JPanel();
        headerPanel.add(lebel);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.setBackground(new Color(39,133,238));
        
        footerPanel=new JPanel();
        footerPanel.add(buttonAdd);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        footerPanel.setBackground(new Color(39,133,238));
        
        
        newExPanel.add(headerPanel,BorderLayout.NORTH);
        newExPanel.add(mainPanel,BorderLayout.CENTER);
        newExPanel.add(footerPanel,BorderLayout.SOUTH);
        
        return newExPanel;
        
    }
    
    void deleteForm(){
        exerciseView.remove(headerPanel);
        exerciseView.remove(mainPanel);
        exerciseView.remove(footerPanel);
    }
    
    JPanel getAddMoreExerciseForm(){
        addExMeal=new JPanel(new BorderLayout());
        lebel_notice=new JLabel(" ");
        
        exListModel=new DefaultListModel();
        
        exList=new JList(exListModel);
        exList.setFont(new Font("Verdana",Font.PLAIN,10));
        pane=new JScrollPane(exList);
        
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setPreferredSize(new Dimension(155,50));
        
        lebel=new JLabel("Workout information");
        lebel.setFont(f1);
        lebel.setForeground(Color.white);
        
        
        buttonAdd=new JButton("Add");
        buttonAdd.setFont(f2);
        buttonAdd.setPreferredSize(new Dimension(100,40));
        
        lebel_name=new JLabel("Exercise Item name");
        lebel_name.setFont(f2);
        
        tb_exName=new JTextField(10);
    
        tb_exName.setFont(f2);
        
        lebel_duration=new JLabel("Workout duration(in minutes)");
        lebel_duration.setFont(f2);
           
        tb_exDuration=new JTextField(10);
        tb_exDuration.setFont(f2);
        
        calorie=new JLabel("Total Calorie burn :  ");
        calorie.setFont(f2);
        
        
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
        mainPanel.add(tb_exName,gb);
        gb.gridx=1;gb.gridy++;
        mainPanel.add(pane,gb);
        gb.insets=new Insets(15,5,0,0);
        gb.gridx=0;gb.gridy++;
        mainPanel.add(lebel_duration,gb);
        gb.gridx++;
        mainPanel.add(tb_exDuration,gb);
        gb.gridy++;
        gb.gridx=0;
        gb.gridwidth=2;
        mainPanel.add(lebel_notice,gb);
        gb.gridy++;
        mainPanel.add(calorie,gb);
        mainPanel.setBackground(Color.white);
        
        headerPanel=new JPanel();
        headerPanel.add(lebel);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.setBackground(new Color(39,133,238));
      
        footerPanel=new JPanel();
        footerPanel.add(buttonAdd);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        footerPanel.setBackground(new Color(39,133,238));
        
        addExMeal.add(headerPanel,BorderLayout.NORTH);
        addExMeal.add(mainPanel,BorderLayout.CENTER);
        addExMeal.add(footerPanel,BorderLayout.SOUTH);

        return addExMeal;
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
    public void remove(JPanel panel, String position) {
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
        if(name.equalsIgnoreCase("tb_exName"))
            return tb_exName;
        else if(name.equalsIgnoreCase("tb_calValue"))
            return tb_calValue;
        else if(name.equalsIgnoreCase("tb_exDuration"))
            return tb_exDuration;
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
             else if (name.equalsIgnoreCase("calorie"))
                 return calorie;
             else 
                 return null;
        
    }

    @Override
    public JComboBox getJComboBox(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel getDefaultListModel(String listName) {
        if(listName.equalsIgnoreCase("exerciseList"))
        return exListModel;
        else
            return null;
    }

    @Override
    public JList getJList() {
        return exList;
    }

    @Override
    public JFrame getJFrame(String frameName) {
       if(frameName.equalsIgnoreCase("exerciseView"))
            return exerciseView;
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

















