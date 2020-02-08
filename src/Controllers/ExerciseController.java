package Controllers;

import Models.*;
import Views.*;
import java.awt.event.FocusEvent;import java.awt.event.FocusListener;import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;import java.awt.event.WindowListener;import java.sql.ResultSet;
import java.sql.SQLException;import java.util.logging.Level;import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class ExerciseController implements MouseListener,KeyListener,FocusListener,WindowListener{
    
     private static ExerciseModel eModel;
    private static ExerciseView eView ;
    private  ResultSet result;

    public ExerciseController(){
    }
    public ExerciseController(DAOModel daoModel,ExerciseModel eModel,ExerciseView eView){
    
        ExerciseController.eView=eView;
        ExerciseController.eModel=eModel;
        eModel.setDAOModel(daoModel);
        addControllsToNewExerciseFormComponents();
    }
    
     public  void  addControllsToNewExerciseFormComponents(){
    eView.getButton("buttonAdd").addMouseListener(this);
    eView.getTextBox("tb_exName").addKeyListener(this);
    eView.getTextBox("tb_exName").addFocusListener(this);
    if(eView.getTextBox("tb_calValue")!=null)
    {
        eView.getTextBox("tb_calValue").addFocusListener(this);
    }
    if(eView.getTextBox("tb_exDuration")!=null)
    {
        eView.getTextBox("tb_exDuration").addFocusListener(this);
    }
    eView.getJList().addMouseListener(this);
    eView.getJFrame("exerciseView").addWindowListener(this);
    }
    
  public    void addNewExercise(){
    
    if(eModel.addExercise(eView.getTextBox("tb_exName").getText(), Float.valueOf(eView.getTextBox("tb_calValue").getText())))
                {
                    JOptionPane.showMessageDialog(null,"Exercise type added successfully","Item added",JOptionPane.INFORMATION_MESSAGE);
                    eView.getTextBox("tb_exName").setText("");
                    eView.getTextBox("tb_calValue").setText("");
                    UIViewController.userView.getDefaultListModel("exerciseList").removeAllElements();
                    result=eModel.listExercise();
                    try{
                    while(result.next()){
                        UIViewController.userView.getDefaultListModel("exerciseList").addElement(result.getString("eName")+
                                " ("+result.getFloat("cal_per_min")+" cal/min)");
                    }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else
                    JOptionPane.showMessageDialog(null,"Item already registered","Insert failed",JOptionPane.ERROR_MESSAGE);
            
    }
    
    public void addMoreExercise() throws SQLException{
        
        float unitcal;
        result=eModel.checkExercise(eView.getTextBox("tb_exName").getText().trim());
        
        if(result.next()){
        unitcal=result.getFloat("cal_per_min");
        
        result=eModel.checkRecordForToday("dailyrecord",UserModel.userID);
        
        if(!result.next()){
           
                    if(eModel.addMoreExercise(Float.valueOf(eView.getTextBox("tb_exDuration").getText()),
                            unitcal*Float.valueOf(eView.getTextBox("tb_exDuration").getText()),
                            UserModel.userID))
                    {
                        JOptionPane.showMessageDialog(null,"Your Workout addedd","Workout added",JOptionPane.INFORMATION_MESSAGE);
                        eView.getTextBox("tb_exDuration").setText("");
                        eView.getTextBox("tb_exName").setText(""); 
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Workout could not be added","Update failed",JOptionPane.ERROR_MESSAGE);

                    }
        else {
             
             eModel.updateDailyExercise(Float.valueOf(eView.getTextBox("tb_exDuration").getText()),
                     unitcal*Float.valueOf(eView.getTextBox("tb_exDuration").getText()));
             
             JOptionPane.showMessageDialog(null,"Your workout is updated","Update successfull",JOptionPane.INFORMATION_MESSAGE);
        
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Workout option you entered is not listed yet.\nPlease add it into the"
                    + " system first","Update failed",JOptionPane.INFORMATION_MESSAGE);
        }
             
        UIViewController.loadSummaryData();
        UIViewController.userView.getPanel("summaryPanel").revalidate();
        UIViewController.userView.getPanel("summaryPanel").repaint();
        
    }
    
    
    public static ExerciseModel getModel(String modelName) {
       if(modelName.equalsIgnoreCase("exerciseModel"))
        return eModel;
       else
           return null;
    }

    
    public static ExerciseView getView(String viewName) {
        if(viewName.equalsIgnoreCase("exerciseView"))
        return eView;
        else return null;
    }

    public static void setModel(ExerciseModel eModel){
        ExerciseController.eModel=eModel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource().equals(eView.getButton("buttonAdd")))
        {
            if(eView.getTextBox("tb_exName").getText().isEmpty())
            {
                eView.getJLabel("lebel_notice").setText("Exercise name is not entered");
           
            }
            else if (eView.getTextBox("tb_calValue")!=null && eView.getTextBox("tb_calValue").getText().isEmpty())
            {
                 eView.getJLabel("lebel_notice").setText("calorie value is required");
               
            }
             else if (eView.getTextBox("tb_exDuration")!=null && eView.getTextBox("tb_exDuration").getText().isEmpty())
            {
                 eView.getJLabel("lebel_notice").setText("Workout Duration is not entered");
               
            }
            else
            {
                if(eView.getTextBox("tb_calValue")!=null){
                    addNewExercise();
                }
                else if(eView.getTextBox("tb_exDuration")!=null){
                    try {
                        addMoreExercise();
                    } catch (SQLException ex) {
                        Logger.getLogger(DietController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }    
            
        }
        else if (e.getSource().equals(eView.getJList())&&!eView.getDefaultListModel("exerciseList").isEmpty())
        {
            eView.getTextBox("tb_exName").setText(eView.getJList().getSelectedValue().toString());
            eView.getDefaultListModel("exerciseList").removeAllElements();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if(e.getSource().equals(eView.getTextBox("tb_exName"))){
            
            if(!eView.getDefaultListModel("exerciseList").isEmpty())
            {eView.getDefaultListModel("exerciseList").removeAllElements();}
            
            
            result=eModel.exerciseLookUp(eView.getTextBox("tb_exName").getText()+e.getKeyChar());
            try {
                while(result.next()){
                   eView.getDefaultListModel("exerciseList").addElement(result.getString("eName"));
                   eView.getPanel("mainPanel").revalidate();
                   eView.getPanel("mainPanel").repaint();
                } 
            } 
            catch (SQLException ex) {
                Logger.getLogger(DietController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource().equals(eView.getTextBox("tb_exName"))||
                e.getSource().equals(eView.getTextBox("tb_calValue"))||
                e.getSource().equals(eView.getTextBox("tb_exDuration"))
                )
        {
                eView.getJLabel("lebel_notice").setText(" ");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
     }

    @Override
    public void windowOpened(WindowEvent e) {
     }

    @Override
    public void windowClosing(WindowEvent e) {   
    }

    @Override
    public void windowClosed(WindowEvent e) {
            eModel=null;
            eView=null;
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) { 
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
     }
    
}
