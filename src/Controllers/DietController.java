package Controllers;
import Models.DAOModel;import Models.DietModel;import Models.UserModel;import Views.DietView;
import java.awt.event.FocusEvent;import java.awt.event.FocusListener;import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;import java.awt.event.WindowListener;import java.sql.ResultSet;
import java.sql.SQLException;import java.util.logging.Level;import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class DietController implements MouseListener,KeyListener,FocusListener,WindowListener{

    private static DietModel dModel;
    private static DietView dView;
    private  ResultSet result;
    public DietController(DAOModel daoModel,DietModel dModel,DietView dView){
       
        DietController.dView=dView;
        DietController.dModel=dModel;
        dModel.setDAOModel(daoModel);
        addControllsToNewMealFormComponents();
    }
    
    public  void  addControllsToNewMealFormComponents(){
    
    dView.getButton("buttonAdd").addMouseListener(this);
    dView.getTextBox("tb_dietName").addKeyListener(this);
    dView.getTextBox("tb_dietName").addFocusListener(this);
    if(dView.getTextBox("tb_calValue")!=null)
    {
        dView.getTextBox("tb_calValue").addFocusListener(this);
    }
    if(dView.getTextBox("tb_dietAmount")!=null)
    {
        dView.getTextBox("tb_dietAmount").addFocusListener(this);
    }
    dView.getJList().addMouseListener(this);
    dView.getJFrame("dietView").addWindowListener(this);
    }
    
      public void addNewMeal(){
    
    if(dModel.addDiet(dView.getTextBox("tb_dietName").getText(), Float.valueOf(dView.getTextBox("tb_calValue").getText())))
                {
                    JOptionPane.showMessageDialog(null,"Meal item added successfully","Item added",JOptionPane.INFORMATION_MESSAGE);
                    dView.getTextBox("tb_calValue").setText("");
                    dView.getTextBox("tb_dietName").setText("");
                    UIViewController.userView.getDefaultListModel("mealList").removeAllElements();
                    result=dModel.listDiet();
                    try{
                    while(result.next()){
                        UIViewController.userView.getDefaultListModel("mealList").addElement(result.getString("dietName")+
                                " ("+result.getFloat("cal_per_gram")+" cal/gram)");
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
    
    public void addMoreMeal() throws SQLException{
        float unitcal;
        result=dModel.checkDiet(dView.getTextBox("tb_dietName").getText());
        if(result.next()){
        
        unitcal=result.getFloat("cal_per_gram");
        
        result=dModel.checkRecordForToday("dailyrecord",UserModel.userID);
        
        if(!result.next()){
            
                    if(dModel.addMoreDiet(Float.valueOf(dView.getTextBox("tb_dietAmount").getText()),
                            unitcal*Float.valueOf(dView.getTextBox("tb_dietAmount").getText()),
                            UserModel.userID))
                    {
                        JOptionPane.showMessageDialog(null,"Meal addedd","Meal added",JOptionPane.INFORMATION_MESSAGE);
                        dView.getTextBox("tb_dietAmount").setText("");
                        dView.getTextBox("tb_dietName").setText(""); 
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Meal could not be added","Update failed",JOptionPane.ERROR_MESSAGE);

                    }
        else {
            
             dModel.updateDailyMeal(Float.valueOf(dView.getTextBox("tb_dietAmount").getText()),
                     unitcal*Float.valueOf(dView.getTextBox("tb_dietAmount").getText()));
             JOptionPane.showMessageDialog(null,"Meal is updated","Update successfull",JOptionPane.INFORMATION_MESSAGE);
            
            }
        }
        else{
        JOptionPane.showMessageDialog(null,"Meal item you selected is not registered yet.\n"
                + "Please add it into the system first","Update failed",JOptionPane.ERROR_MESSAGE);
        }
             
        UIViewController.loadSummaryData();
        UIViewController.userView.getPanel("summaryPanel").revalidate();
        UIViewController.userView.getPanel("summaryPanel").repaint();
        
    }
    
    
    public static DietModel getModel(String modelName) {
       if(modelName.equalsIgnoreCase("dietModel"))
        return dModel;
       else
           return null;
    }

    
    public static DietView getView(String viewName) {
        if(viewName.equalsIgnoreCase("dietView"))
        return dView;
        else return null;
    }

    public static void setModel(DietModel dModel){
        DietController.dModel=dModel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource().equals(dView.getButton("buttonAdd")))
        {
            if(dView.getTextBox("tb_dietName").getText().isEmpty())
            {
                dView.getJLabel("lebel_notice").setText("Meal name is not entered");
           
            }
            else if (dView.getTextBox("tb_calValue")!=null && dView.getTextBox("tb_calValue").getText().isEmpty())
            {
                 dView.getJLabel("lebel_notice").setText("calorie value is required");
               
            }
             else if (dView.getTextBox("tb_dietAmount")!=null && dView.getTextBox("tb_dietAmount").getText().isEmpty())
            {
                 dView.getJLabel("lebel_notice").setText("Meal ammount is not entered");
               
            }
            else
            {
                if(dView.getTextBox("tb_calValue")!=null){
                    addNewMeal();
                }
                else if(dView.getTextBox("tb_dietAmount")!=null){
                    try {
                        addMoreMeal();
                    } catch (SQLException ex) {
                        Logger.getLogger(DietController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }    
            
        }
        else if (e.getSource().equals(dView.getJList())&&!dView.getDefaultListModel("mealList").isEmpty())
        {
          dView.getTextBox("tb_dietName").setText(dView.getJList().getSelectedValue().toString());
            
          dView.getDefaultListModel("mealList").removeAllElements();
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
        
        if(e.getSource().equals(dView.getTextBox("tb_dietName"))){
            
            if(!dView.getDefaultListModel("mealList").isEmpty())
            {dView.getDefaultListModel("mealList").removeAllElements();}
            
            result=dModel.dietLookUp(dView.getTextBox("tb_dietName").getText()+e.getKeyChar());
            try {
                while(result.next()){
                   dView.getDefaultListModel("mealList").addElement(result.getString("dietName"));
                   dView.getPanel("mainPanel").revalidate();
                   dView.getPanel("mainPanel").repaint();
                
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
        if(e.getSource().equals(dView.getTextBox("tb_dietName"))||
                e.getSource().equals(dView.getTextBox("tb_calValue"))||
                e.getSource().equals(dView.getTextBox("tb_dietAmount"))
                )
        {
                dView.getJLabel("lebel_notice").setText(" ");
             
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
            dModel=null;
            dView=null;
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


