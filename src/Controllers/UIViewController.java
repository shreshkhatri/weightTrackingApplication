package Controllers;
import Models.*;
import Views.*;
import java.awt.Color;import java.awt.HeadlessException;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ItemEvent;import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;import java.sql.Date;import java.sql.ResultSet;
import java.sql.SQLException;import java.util.Arrays;import java.util.logging.Level;
import java.util.logging.Logger;import javax.swing.ImageIcon;import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;import javax.swing.event.ChangeListener;

public final class UIViewController implements MouseListener,KeyListener,ItemListener,ActionListener,ChangeListener{
    
    private  static  UserModel userModel;
    private static DAOModel daoModel;
    private  UIView uiView;
    public static JView headerView,contentView,footerView,userView,reportView,graphView;
    public static ResultSet resultSet1,resultSet2,resultSet;
    private boolean traverse=true;
    
   public UIViewController(){
   }
    
public UIViewController(DAOModel daoModel,UserModel userModel,UIView uiView){
    
    UIViewController.daoModel=daoModel;
    UIViewController.userModel=userModel;
    userModel.setDAOModel(UIViewController.daoModel);
    this.uiView=uiView;
    createGUI();
    
 }

public void createGUI(){
    
    headerView=ViewFactory.createView("header",null);
    contentView=ViewFactory.createView("content",null);
    footerView=ViewFactory.createView("footer",null);
    userView=ViewFactory.createView("user",null);
    reportView=ViewFactory.createView("report", null);
    contentView.add(userView.getPanel("login"),"left");
    contentView.add(contentView.getPanel("sliderPanel"),"right");
    addControllsToLoginFormComponents();
    addControlsToUserInfoFormControls();
    addControllsToMealEditForm();
    addControllsToExerciseEditForm();
    addControllsToDailyRecordForm();
    addControllsToReportPanelComponents();
    headerView.addTo(uiView);
    contentView.addTo(uiView);
    footerView.addTo(uiView);
    uiView.refreshGUI();
    

}

public void setDateRange() {
    try{
   resultSet=userModel.getInitialtDate();
            resultSet.next();
   
     userModel.setStartDate(resultSet.getDate(1));
       
        
   resultSet=userModel.getLatestDate();
           resultSet.next();
        userModel.setEndDate(resultSet.getDate(1));
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
}

public void logOut(){
   
  
     contentView.getPanel("rightpanel").removeAll();
     contentView.getPanel("leftpanel").removeAll();
     
     contentView.add(userView.getPanel("login"),"left");
     contentView.add(contentView.getPanel("sliderPanel"), "right");
    
    userView.getTextBox("tb_userName").setText("");
    userView.getPasswordField("p_password").setText("");
    
    contentView.getPanel("rightPanel").revalidate();
    contentView.getPanel("rightPanel").repaint();
    contentView.getPanel("leftpanel").revalidate();
    contentView.getPanel("leftpanel").repaint();

}

public void addControllsToDailyRecordForm(){
    userView.getButton("btn_addDailyRecord").addActionListener(this);
    userView.getJSlider("activityLevelSlider").addChangeListener(this);
}

public void addControllsToExerciseEditForm(){
    
    userView.getButton("btn_eRemove").addActionListener(this);
    userView.getButton("btn_eEdit").addActionListener(this);
    userView.getJComboBox("exerciselist").addItemListener(this);
}

public void addControllsToMealEditForm(){
    
    userView.getButton("btn_mRemove").addActionListener(this);
    userView.getButton("btn_mEdit").addActionListener(this);
    userView.getJComboBox("meallist").addItemListener(this);
}
public void addControllsToLoginFormComponents(){
    
    userView.getButton("btn_login").addMouseListener(this);
    userView.getJLabel("lbl_linkPart2").addMouseListener(this);
   
}

public void addControllsToSignUpFormComponents(){
    userView.getButton("btn_signUp").addMouseListener(this);
    userView.getJLabel("lbl_linkToMainMenu").addMouseListener(this);
     userView.getTextBox("txt_age").addKeyListener(this);
}

public void addControlsToUserInfoFormControls(){
    userView.getJLabel("lebel_edit").addMouseListener(this);
    userView.getButton("btn_update").addMouseListener(this);
    userView.getButton("btn_cancel").addMouseListener(this);
}

public void addControllsToDashBoardComponents(){
    
    userView.getJLabel("lebel_AddRecord").addMouseListener(this);
    userView.getJLabel("lebel_EditExInfo").addMouseListener(this);
    userView.getJLabel("lebel_EditMealInfo").addMouseListener(this);
    userView.getJLabel("lebel_EditUserInfo").addMouseListener(this);
    userView.getJLabel("lebel_ViewReport").addMouseListener(this);
    userView.getJLabel("lebel_help").addMouseListener(this);
    userView.getJLabel("lebel_Logout").addMouseListener(this);
    userView.getJLabel("dashbordLebel").addMouseListener(this);
}

public void addControllsToSummaryPanelComponents(){

    userView.getJLabel("lbl_moreMeal").addMouseListener(this);
    userView.getJLabel("lbl_moreExercise").addMouseListener(this);
    userView.getJLabel("newMeal").addMouseListener(this);
    userView.getJLabel("newExercise").addMouseListener(this);
    
}

public void addControllsToReportPanelComponents(){
userView.getButton("btn_genReport").addActionListener(this);
}

public static void  loadDietList() throws SQLException{
    if(DietController.getModel("dietModel")==null){
        
        DietController.setModel(new DietModel("diet"));
        DietController.getModel("dietModel").setDAOModel(daoModel);
    }
        
    resultSet=DietController.getModel("dietModel").listDiet();
    while(resultSet.next()){
        userView.getDefaultListModel("mealList").addElement(resultSet.getString("dietName")+" ("+resultSet.getFloat("cal_per_gram")+" cal/gram)");
        
    }
}

public static void loadExerciseList() throws SQLException{
    if(ExerciseController.getModel("exerciseModel")==null){
        
        ExerciseController.setModel(new ExerciseModel("exercise"));
        ExerciseController.getModel("exerciseModel").setDAOModel(daoModel);
    }
        
    resultSet=ExerciseController.getModel("exerciseModel").listExercise();
    while(resultSet.next()){
        userView.getDefaultListModel("exerciseList").addElement(resultSet.getString("eName")+" ("+resultSet.getFloat("cal_per_min")+" cal/min)");
        
    }
}

public void setUserModelData() throws SQLException{

    userModel.setUserID(resultSet1.getInt("userID"));
    userModel.setAge(resultSet1.getInt("age"));
    userModel.setUsername(resultSet1.getString("userName"));
    userModel.setGender(resultSet1.getString("gender"));
    }

public static void loadSummaryData(){

try{
                        if((resultSet2=userModel.getDailyRecord()).next()){

                            userModel.setMealAmount(resultSet2.getFloat("mealAmount"));
                            userModel.setCal_Intake(resultSet2.getFloat("calorieTaken"));
                            userModel.setWorkoutDuration(resultSet2.getFloat("workout"));
                            userModel.setCal_Burned(resultSet2.getFloat("calorieBurned"));
                            
                            userModel.setMorningWeight(resultSet2.getFloat("mWeight"));
                            userModel.setMorningWaistSize(resultSet2.getFloat("mWaistSize"));
                            userModel.setEveningWeight(resultSet2.getFloat("eWeight"));
                            userModel.setEveningWaistSize(resultSet2.getFloat("eWaistSize"));
                            userModel.setActivityLabelIndex(resultSet2.getString("activityLevel"));
                            
                            
                            userView.getJLabel("lbl_Meal").setText("<html>Total meal consumed: "
                                    + "&nbsp;&nbsp;&nbsp;&nbsp;"+userModel.getMealAmount()+" gram <BR><BR>"
                                    + "Total Calorie Intake :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+userModel.getCal_Intake()+" cal</html>");
                            userView.getJLabel("lbl_Workout").setText("<html>your total Workout duration :"
                                    + "&nbsp;&nbsp;&nbsp;&nbsp;"+userModel.getWorkoutDuration()+" minutes<BR><BR>"
                                    + "Total calllorie burn :&nbsp;&nbsp;&nbsp;"+userModel.getCal_Burned()+" cal </html>");

                            if((userModel.getCal_Intake()-userModel.getCal_Burned())>0){
                                 userView.getJLabel("lbl_tips").setText("Your calorie intake "
                                         + "outweighs your workout callorie burn. you need to workout further");
                                 
                                 //userView.getJLabel("lbl_tips").
                            }
                            else if((userModel.getCal_Intake()-userModel.getCal_Burned())<0){
                                userView.getJLabel("lbl_tips").setText("Your workout is enough "
                                        + "as of now.You can stop working hard for now !");
                                }
                            else {
                                userView.getJLabel("lbl_tips").setText("Amazing! you are"
                                        + " maintainng calorie intake and burn process right now");
                                }
                            }
                        else {

                            userView.getJLabel("lbl_Meal").setText("<html>You have not recoreded your consumed meal"
                         + " data yet.Please click on 'Add More Meals' Link below to add more meals.If the meal item "
                         + "doesnot appear on the list ,please add meal item first clicking 'Add New Meal' link at the bottom.<BR>"
                         + "Please go to Help section for application usage guidance.</teml>");
                            userView.getJLabel("lbl_Workout").setText("<html>Your workout data is not added yet. "
                         + "Please click on 'Add More Workout Details' link to add data about your exercises for today."
                         + "If the exercise option does not appear on the list, please add it first by clicking on 'Add New Exercise'"
                         + " link below. <BR>Please refer to Help section for further guidance.</html>");

                        }


                    }  
                catch (SQLException ex) {
                    Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }

}
    @Override
    public void mouseClicked(MouseEvent e) {
        
         if (e.getSource().equals(userView.getButton("btn_login"))){
             
                         if(userView.getTextBox("tb_userName").getText().isEmpty()){
                                JOptionPane.showMessageDialog(null, "Username is Empty");
                                }
                         else if(userView.getPasswordField("p_password").getPassword().length==0)
                                JOptionPane.showMessageDialog(null, "Password field is Empty");
                         else {
                             try {
                                 if((resultSet1=userModel.loginUser(userView.getTextBox("tb_userName").getText(),String.valueOf(
                                         userView.getPasswordField("p_password").getPassword()))).next())
                                    {
                                        setUserModelData();
                                        loadSummaryData();
                                        loadDietList();
                                        loadExerciseList();
                                        
                                        userView.getPanel("extraPnl_2").revalidate();
                                        userView.getPanel("extraPnl_2").repaint();
                                        
                                     contentView.remove(userView.getPanel("login"),"left");
                                     contentView.remove(contentView.getPanel("sliderPanel"),"right");
                                     contentView.add(userView.getPanel("dashboard"),"left");
                                     contentView.add(userView.getPanel("summaryPanel"),"right");
                                     
                                    
                                     
                                     userView.getJLabel("lebel_Welcome").setText("<HTML>Welcome !<BR> "+userModel.getUsername()+"</HTML>");
                                     addControllsToDashBoardComponents();
                                     addControllsToSummaryPanelComponents();
                                     
                                     uiView.refreshGUI();
                                      
                             }
                             else
                                         JOptionPane.showMessageDialog(null, "Login credentials are incorrect",
                                                 "Login failed",JOptionPane.ERROR_MESSAGE);
                            
                         }
                         catch (SQLException ex) {
                                 
                                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         
                         }
                    }
         else if (e.getSource().equals(userView.getButton("btn_signUp"))){
             
                         if(userView.getTextBox("txt_uName").getText().isEmpty()){
                                userView.getJLabel("lbl_notice").setText("username is missing");
                                }
                         else if(userView.getTextBox("txt_age").getText().isEmpty()){
                                userView.getJLabel("lbl_notice").setText("age is missing");
                                }
                         else if(userView.getPasswordField("pswd_1").getPassword().length==0){
                                userView.getJLabel("lbl_notice").setText("password not entered");
                                }
                         else if(userView.getPasswordField("pswd_2").getPassword().length==0){
                                userView.getJLabel("lbl_notice").setText("password not confirmed");
                                }
                         
                         else if(!Arrays.equals(userView.getPasswordField("pswd_1").getPassword(), 
                                 userView.getPasswordField("pswd_2").getPassword()))
                                 userView.getJLabel("lbl_notice").setText("passwords not matched");
                         else {
                             
                             try{
                                 Integer.valueOf(userView.getTextBox("txt_age").getText());
//ensures that user is registered/added
                            if(userModel.addUser(
                                     userView.getTextBox("txt_uName").getText(), 
                                     Integer.valueOf(userView.getTextBox("txt_age").getText()),
                                     String.valueOf(userView.getPasswordField("pswd_2").getPassword()),
                                     userView.getJComboBox("combo_gender").getSelectedItem().toString()
                                     )){
                                    
                                    JOptionPane.showMessageDialog(null,"Congratulations !\n You have "
                                            + "successfully signed up ","Sign up successful" ,JOptionPane.INFORMATION_MESSAGE);
                                     
                                 try {
                                 
  // initialization of user data after successful signnup
                                     if((resultSet1=userModel.loadRecentUserData()).next()){
                                        
                                       setUserModelData();
                                     }
                                    }  
                                 catch (SQLException ex) {
                                     Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                     
                                 try {
                                     loadDietList();
                                     loadExerciseList();
                                 } catch (SQLException ex) {
                                     Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                        
                                        userView.getPanel("extraPnl_2").revalidate();
                                        userView.getPanel("extraPnl_2").repaint();
                                 
                                     contentView.remove(userView.getPanel("signup"),"left");
                                     contentView.remove(contentView.getPanel("sliderPanel"),"right");
                                     contentView.add(userView.getPanel("dashboard"),"left");
                                     contentView.add(userView.getPanel("summaryPanel"),"right");
                                     userView.getJLabel("lbl_Meal").setText("<html>You have not recoreded your consumed meal"
                                             + " data yet.Please click on 'Add More Meals' Link below to add more meals.If the meal item "
                                             + "doesnot appear on the list ,please add meal item first clicking "
                                             + "'Add New Meal' link at the bottom.<BR>"
                                             + "Please go to Help section for application usage guidance.</teml>");
                                     userView.getJLabel("lbl_Workout").setText("<html>Your workout data is not added yet. "
                                             + "Please click on 'Add More Workout Details' link to add data about your exercises for today."
                                             + "If the exercise option does not appear on the list, please add it first"
                                             + " by clicking on 'Add New Exercise'"
                                             + " link below. <BR>Please refer to Help section for further guidance.</html>");
                                     
                                     userView.getJLabel("lebel_Welcome").setText("<HTML>"+
                                             userView.getJLabel("lebel_Welcome").getText()+" !<BR> "+userModel.getUsername()+"</HTML>");
                                     
                                     addControllsToDashBoardComponents();
                                     addControllsToSummaryPanelComponents();
                                     uiView.refreshGUI();
                                
                            }
                  else
                       {
                        JOptionPane.showMessageDialog(null,"Sorry! Signup processes failed\nPlease try "
                                + "with different username or try again later.","Sign up failed" ,JOptionPane.ERROR_MESSAGE);
                       }
                            }
                         catch(NumberFormatException | HeadlessException ex){
                                 
                        userView.getJLabel("lbl_notice").setText("age is non numeric");
                      }
                     }
         }
         
         else if (e.getSource().equals(userView.getJLabel("lbl_linkPart2"))){
           
            contentView.remove(userView.getPanel("login"),"left");
            contentView.add(userView.getPanel("signup"),"left");
            addControllsToSignUpFormComponents();
            uiView.refreshGUI();
            }
         else if (e.getSource().equals(userView.getJLabel("lbl_linkToMainMenu"))){
            
            contentView.remove(userView.getPanel("signup"),"left");
            contentView.add(userView.getPanel("login"),"left");
            uiView.refreshGUI();
         }
         
          else if (e.getSource().equals(userView.getJLabel("lbl_moreMeal"))){
             DietController dietController = new DietController(DAOModel.getDAOModel(),new DietModel("diet"),new DietView("moreMeal"));
             
          }
         else if (e.getSource().equals(userView.getJLabel("newMeal"))){
             DietController dietController = new DietController(DAOModel.getDAOModel(),new DietModel("diet"),new DietView("newMeal"));
            
          }
         else if (e.getSource().equals(userView.getJLabel("lbl_moreExercise"))){
             
             ExerciseController eController=new ExerciseController(DAOModel.getDAOModel(),new ExerciseModel("exercise"),
                     new ExerciseView("moreExercise"));
          }
         else if (e.getSource().equals(userView.getJLabel("newExercise"))){
             ExerciseController eController=new ExerciseController(DAOModel.getDAOModel(),new ExerciseModel("exercise"),
                     new ExerciseView("newExercise"));
             
          }
         else if (e.getSource().equals(userView.getJLabel("lebel_EditUserInfo"))){
           
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("userInfo"),"right");
            
             userView.getTextBox("username").setText(userModel.getUsername());
             userView.getTextBox("userage").setText(String.valueOf(userModel.getAge()));
             userView.getJComboBox("usergender").setSelectedItem(userModel.getGender());
             
             userView.getTextBox("username").setEditable(false);
             userView.getTextBox("userage").setEditable(false);
             userView.getJComboBox("usergender").setEnabled(false);
             userView.getButton("btn_update").setEnabled(false);
             userView.getButton("btn_cancel").setEnabled(false);
             
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
         
         else if (e.getSource().equals(userView.getJLabel("lebel_AddRecord"))){
           
             contentView.getPanel("rightpanel").removeAll();
             
            if(userModel.getMorningWeight()==0&&userModel.getMorningWaistSize()==0&&
                    userModel.getEveningWeight()==0&&userModel.getEveningWaistSize()==0){
                    userView.getButton("btn_addDailyRecord").setText("Add");
            }
            else{
            userView.getButton("btn_addDailyRecord").setText("Update");
            
            userView.getTextBox("morningWeight").setText(String.valueOf(userModel.getMorningWeight()));
            userView.getTextBox("morningWaistSize").setText(String.valueOf(userModel.getMorningWeight()));
            userView.getTextBox("eveningWeight").setText(String.valueOf(userModel.getEveningWeight()));
            userView.getTextBox("eveningWaistSize").setText(String.valueOf(userModel.getEveningWaistSize()));
                 
            switch (userModel.getActivityLabelIndex()) {
                     case "inactive":
                         userView.getJSlider("activityLevelSlider").setValue(0);
                         break;
                     case "moderate":
                         userView.getJSlider("activityLevelSlider").setValue(10);
                         break;
                     case "active":
                         userView.getJSlider("activityLevelSlider").setValue(20);
                         break;
                     default:
                         break;
                 }
            
            }
                           
             contentView.add(userView.getPanel("dailyrecord"),"right");
             
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
         
         else if (e.getSource().equals(userView.getJLabel("lebel_Logout"))){
           
           logOut();
             
          }
         
        else if (e.getSource().equals(userView.getJLabel("lebel_help"))){
           
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("help"),"right");
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
                  }
          else if (e.getSource().equals(userView.getJLabel("lebel_ViewReport"))){
           
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("report"),"right");
             setDateRange();
             
             if(userModel.getStartDate()!=null){
                userView.getJLabel("lebel_startDate").setText("( No earlier than "+userModel.getStartDate()+" )");}
             else
             {
                 userView.getJLabel("lebel_startDate").setText("No Date found");
             }
             
             if(userModel.getEndDate()!=null){
                userView.getJLabel("lebel_endDate").setText("( No later than "+userModel.getEndDate()+" ) ");}
             else
                {userView.getJLabel("lebel_endDate").setText("No Date found");}
             
            /* if(userModel.getStartDate()==null&&userModel.getEndDate()==null)
             {
                 userView.getButton("btn_genReport").removeActionListener(this);
             }
             else
             {
                 userView.getButton("btn_genReport").removeActionListener(this);
             }
             */
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
        else if (e.getSource().equals(userView.getJLabel("dashbordLebel"))){ 
             try {
                 loadSummaryData();
                 loadDietList();
                 loadExerciseList();
             } catch (SQLException ex) {
                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("summaryPanel"),"right");
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
          else if (e.getSource().equals(userView.getJLabel("lebel_EditExInfo"))){
           
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("editexercise"),"right");
             
             ExerciseController.setModel(new ExerciseModel("exercise"));
             ExerciseController.getModel("exerciseModel").setDAOModel(daoModel);
             resultSet=ExerciseController.getModel("exerciseModel").listExercise(); 
             try {
                 while(resultSet.next()){
                 userView.getJComboBox("exerciselist").addItem(resultSet.getString("eName"));
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
 
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
          
          else if (e.getSource().equals(userView.getJLabel("lebel_EditMealInfo"))){
           
             contentView.getPanel("rightpanel").removeAll();
             contentView.add(userView.getPanel("editmeal"),"right");
             
             DietController.setModel(new DietModel("diet"));
             DietController.getModel("dietModel").setDAOModel(daoModel);
             resultSet=DietController.getModel("dietModel").listDiet();
             
             try {
                 while(resultSet.next()){
                 userView.getJComboBox("meallist").addItem(resultSet.getString("dietName"));
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
          }
         else if (e.getSource().equals(userView.getJLabel("lebel_edit"))){
             
             userView.getTextBox("username").setEditable(true);
             userView.getTextBox("userage").setEditable(true);
             userView.getJComboBox("usergender").setEnabled(true);
             userView.getButton("btn_update").setEnabled(true);
             userView.getButton("btn_cancel").setEnabled(true);
             
          }
         else if (e.getSource().equals(userView.getButton("btn_cancel"))){
             
             userView.getTextBox("username").setEditable(false);
             userView.getTextBox("userage").setEditable(false);
             userView.getJComboBox("usergender").setEnabled(false);
             userView.getButton("btn_update").setEnabled(false);
             userView.getButton("btn_cancel").setEnabled(false);
             
          }
         else if (e.getSource().equals(userView.getButton("btn_update"))){
             
             if(
                     userView.getTextBox("username").getText().equals(userModel.getUsername())&&
                     Integer.valueOf(userView.getTextBox("userage").getText()).equals(userModel.getAge())&&
                     userView.getJComboBox("usergender").getSelectedItem().equals(userModel.getGender())
                )
             {
             JOptionPane.showMessageDialog(uiView, "No update required as of now","Update not performed", JOptionPane.INFORMATION_MESSAGE);
             }
             else if(userView.getTextBox("username").getText().isEmpty()){
             JOptionPane.showMessageDialog(uiView, "Username cant be empty","Update not performed", JOptionPane.INFORMATION_MESSAGE);
             }
              else if(userView.getTextBox("userage").getText().isEmpty()){
             JOptionPane.showMessageDialog(uiView, "age feild is empty","Update not performed", JOptionPane.INFORMATION_MESSAGE);
             }
             else
              {
                if(userModel.updateUserdata(
                        userView.getTextBox("username").getText(), 
                        Integer.valueOf(userView.getTextBox("userage").getText()), 
                        userView.getJComboBox("usergender").getSelectedItem().toString(),
                        userModel.getUserID()))
                        {
                           userModel.setUsername(userView.getTextBox("username").getText());
                            userModel.setAge(Integer.valueOf(userView.getTextBox("userage").getText()));
                            userModel.setGender(userView.getJComboBox("usergender").getSelectedItem().toString());
                            
                            JOptionPane.showMessageDialog(uiView, "Information updated","Update successfull", JOptionPane.INFORMATION_MESSAGE);
                            
                             userView.getButton("btn_update").setEnabled(false);
                             userView.getTextBox("username").setEditable(false);
                             userView.getTextBox("userage").setEditable(false);
                             userView.getJComboBox("usergender").setEnabled(false);
                             userView.getButton("btn_cancel").setEnabled(false);
                        }
                else
                {
                        JOptionPane.showMessageDialog(uiView, "Information couldnot be updated\nYou can try with"
                                + " different username or try again later","Update failed", JOptionPane.ERROR_MESSAGE);
                }
              
              }
             
           
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
        if (e.getSource().equals(userView.getJLabel("lbl_linkToMainMenu"))){
            userView.getJLabel("lbl_linkToMainMenu").setIcon(new ImageIcon(getClass().getResource("/graphics/darkGray.png")));
            userView.getPanel("signup").revalidate();
            userView.getPanel("signup").repaint();
        }
        
        if (
                e.getSource().equals(userView.getJLabel("lebel_AddRecord")) ||
                e.getSource().equals(userView.getJLabel("lebel_EditExInfo"))||
                e.getSource().equals(userView.getJLabel("lebel_EditMealInfo")) ||
                e.getSource().equals(userView.getJLabel("lebel_EditUserInfo"))||
                e.getSource().equals(userView.getJLabel("lebel_help"))||
                e.getSource().equals(userView.getJLabel("lebel_Logout"))||
                e.getSource().equals(userView.getJLabel("lebel_ViewReport"))
                ){
            
            e.getComponent().setForeground(Color.red);
            }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(userView.getJLabel("lbl_linkToMainMenu"))){
            userView.getJLabel("lbl_linkToMainMenu").setIcon(new ImageIcon(getClass().getResource("/graphics/lightGray.png")));
            userView.getPanel("signup").revalidate();
            userView.getPanel("signup").repaint();
        }
        
         if (
                e.getSource().equals(userView.getJLabel("lebel_AddRecord")) ||
                e.getSource().equals(userView.getJLabel("lebel_EditExInfo"))||
                e.getSource().equals(userView.getJLabel("lebel_EditMealInfo")) ||
                e.getSource().equals(userView.getJLabel("lebel_EditUserInfo"))||
                e.getSource().equals(userView.getJLabel("lebel_help"))||
                e.getSource().equals(userView.getJLabel("lebel_Logout"))||
                e.getSource().equals(userView.getJLabel("lebel_ViewReport"))
                ){
            
            e.getComponent().setForeground(UserView.c1);
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
        if(e.getKeyCode()!=48)
        {
            e.setKeyCode(0);
            e.setKeyChar('\u0000');
        }
    }

   
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
   
    public static JView getView(String viewName) {
        if(viewName.equalsIgnoreCase("headerView"))
            return headerView;
        else if(viewName.equalsIgnoreCase("contentView"))
            return contentView;
        else if(viewName.equalsIgnoreCase("footerView"))
            return footerView;
        else if(viewName.equalsIgnoreCase("userView"))
            return userView;
        else
            return null;
    }
  
    public static void setView(JView v) {
       
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getSource().equals(userView.getJComboBox("meallist"))){
        
            if(traverse){
                    try {
                       resultSet.beforeFirst();
                       while(resultSet.next()){

                           if(resultSet.getString("dietName").equals(userView.getJComboBox("meallist").getSelectedItem())){

                               userView.getTextBox("mealname").setText(resultSet.getString("dietName"));
                               userView.getTextBox("mcallorieValue").setText(String.valueOf(resultSet.getFloat("cal_per_gram")));
                               break;
                           }
                       }

                        } catch (SQLException ex) {
                       Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                       System.out.println(ex.getMessage());
                        }
            }
       }
       
       else if(e.getSource().equals(userView.getJComboBox("exerciselist"))){
           if(traverse){
                        try {
                            resultSet.beforeFirst();
                            while(resultSet.next()){

                                if(resultSet.getString("eName").equals(userView.getJComboBox("exerciselist").getSelectedItem())){

                                    userView.getTextBox("exercisename").setText(resultSet.getString("eName"));
                                    userView.getTextBox("ecallorieValue").setText(String.valueOf(resultSet.getFloat("cal_per_min")));
                                    break;
                                }

                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(ex.getMessage());
                        }

            }
       }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(userView.getButton("btn_mRemove"))){
            
            if((JOptionPane.showConfirmDialog(uiView,
                    userView.getJComboBox("meallist").getSelectedItem().toString()+"  is going to be deleted\n"
                    + "Do you really want to delete it?", "Deleting an Item", 0, JOptionPane.QUESTION_MESSAGE))==0)
                {
                    if(DietController.getModel("dietModel").deleteDiet(userView.getJComboBox("meallist").getSelectedItem().toString()))
                    {
                        resultSet=DietController.getModel("dietModel").listDiet();
                        userView.getJComboBox("meallist").removeItem(userView.getTextBox("mealname").getText());
                        JOptionPane.showMessageDialog(uiView, "Item removed from database","Item deleted ",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(uiView, "Sorry, deletion failed!\nPlease try again later.",
                                "Deletion Failed ",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            
        }
        
        else if(e.getSource().equals(userView.getButton("btn_mEdit"))){
           
            boolean editable=true;
            try {
                resultSet.beforeFirst();
                
                while(resultSet.next()){
                    
                    if(resultSet.getString("dietName").equalsIgnoreCase(userView.getTextBox("mealname").getText())&&
                       resultSet.getFloat("cal_per_gram")==Float.valueOf(userView.getTextBox("mcallorieValue").getText().isEmpty()
                               ?0:Float.valueOf(userView.getTextBox("mcallorieValue").getText())))
                    {
                        editable=false;
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!editable)
            {
                JOptionPane.showMessageDialog(uiView, "No update is required","Update not necessary",JOptionPane.ERROR_MESSAGE);                
            }
            else if(userView.getTextBox("mealname").getText().isEmpty())
            {
                JOptionPane.showMessageDialog(uiView, "Meal name is empty!","Update failed",JOptionPane.ERROR_MESSAGE);                
            }
            else if(userView.getTextBox("mcallorieValue").getText().isEmpty())
            {
                JOptionPane.showMessageDialog(uiView, "Callorie value is empty!","Update failed",JOptionPane.ERROR_MESSAGE);                
            }
            else
            {
                
                if(DietController.getModel("dietModel").updateDiet(userView.getJComboBox("meallist").getSelectedItem().toString(),
                    userView.getTextBox("mealname").getText(), Float.valueOf(userView.getTextBox("mcallorieValue").getText()))){
                
                    JOptionPane.showMessageDialog(uiView, "Item Updated successfully","Update Successfull",JOptionPane.INFORMATION_MESSAGE);
                    
                    resultSet=DietController.getModel("dietModel").listDiet();
             traverse=false;
             userView.getJComboBox("meallist").removeAllItems();
             
             try {
                 while(resultSet.next()){
                 userView.getJComboBox("meallist").addItem(resultSet.getString("dietName"));
                 }
                 traverse=true;
             } catch (SQLException ex) {
                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
                    
                
                }
                else
                {
                    JOptionPane.showMessageDialog(uiView, "Somthing went wrong.\n you could try using different "
                            + "meal name or try again later","Update failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
            }
        else if(e.getSource().equals(userView.getButton("btn_eRemove"))){
            
            if((JOptionPane.showConfirmDialog(uiView,
                    userView.getJComboBox("exerciselist").getSelectedItem().toString()+"  is going to be deleted\n"
                    + "Do you really want to delete it?", "Deleting an Item", 0, JOptionPane.QUESTION_MESSAGE))==0)
                {
                    if(ExerciseController.getModel("exerciseModel").deleteExercise(userView.getJComboBox("exerciselist").
                            getSelectedItem().toString()))
                    {
                        resultSet=ExerciseController.getModel("exerciseModel").listExercise();
                        userView.getJComboBox("exerciselist").removeItem(userView.getTextBox("exercisename").getText());
                        JOptionPane.showMessageDialog(uiView, "Item removed from database","Item deleted ",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(uiView, "Sorry, deletion failed!\nPlease try again later.",
                                "Deletion Failed ",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            
        }
        
         else if(e.getSource().equals(userView.getButton("btn_eEdit"))){
           
            boolean editable=true;
            try {
                resultSet.beforeFirst();
                
                while(resultSet.next()){
                    
                    if(resultSet.getString("eName").equalsIgnoreCase(userView.getTextBox("exercisename").getText())&&
                       resultSet.getFloat("cal_per_min")==Float.valueOf(userView.getTextBox("ecallorieValue").
                               getText().isEmpty()?0:Float.valueOf(userView.getTextBox("ecallorieValue").getText())))
                    {
                        editable=false;
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!editable)
            {
                JOptionPane.showMessageDialog(uiView, "No update is required","Update not necessary",JOptionPane.ERROR_MESSAGE);                
            }
            else if(userView.getTextBox("exercisename").getText().isEmpty())
            {
                JOptionPane.showMessageDialog(uiView, "Exercise name is empty!","Update failed",JOptionPane.ERROR_MESSAGE);                
            }
            else if(userView.getTextBox("ecallorieValue").getText().isEmpty())
            {
                JOptionPane.showMessageDialog(uiView, "Callorie value is empty!","Update failed",JOptionPane.ERROR_MESSAGE);                
            }
            else
            {
                
                if(ExerciseController.getModel("exerciseModel").updateExercise(userView.getJComboBox("exerciselist").getSelectedItem().toString(),
                    userView.getTextBox("exercisename").getText(), Float.valueOf(userView.getTextBox("ecallorieValue").getText()))){
                
                    JOptionPane.showMessageDialog(uiView, "Item Updated successfully","Update Successfull",JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    resultSet=ExerciseController.getModel("exerciseModel").listExercise();
                    
                    traverse=false;
                   userView.getJComboBox("exerciselist").removeAllItems();
             try {
                 while(resultSet.next()){
                 userView.getJComboBox("exerciselist").addItem(resultSet.getString("eName"));
                 }
                 traverse=true;
             } catch (SQLException ex) {
                 Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
 
             contentView.getPanel("rightpanel").revalidate();
             contentView.getPanel("rightpanel").repaint();
             
            
                }
                else
                {
                    JOptionPane.showMessageDialog(uiView, "Somthing went wrong.\n you could "
                            + "try using different exercise name or try again later","Update failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
            }
        
        else if(e.getSource().equals(userView.getButton("btn_addDailyRecord"))){
           
            if(userView.getTextBox("morningWeight").getText().isEmpty()&&
               userView.getTextBox("morningWaistSize").getText().isEmpty()&&
               userView.getTextBox("eveningWeight").getText().isEmpty()&&
               userView.getTextBox("eveningWaistSize").getText().isEmpty())
                {
                JOptionPane.showMessageDialog(uiView, "Nothing to update","Update failed",JOptionPane.ERROR_MESSAGE);
                }    
           else if(userView.getTextBox("morningWeight").getText().equals(String.valueOf(userModel.getMorningWeight()))&&
               userView.getTextBox("morningWaistSize").getText().equals(String.valueOf(userModel.getMorningWeight()))&&
               userView.getTextBox("eveningWeight").getText().equals(String.valueOf(userModel.getEveningWeight()))&&
               userView.getTextBox("eveningWaistSize").getText().equals(String.valueOf(userModel.getEveningWaistSize()))&&
               userView.getJLabel("lebelIndex").getText().equals(userModel.getActivityLabelIndex())
                   )
                {
                    JOptionPane.showMessageDialog(uiView, "No change in data is detected!","Update unnecessary",JOptionPane.INFORMATION_MESSAGE);
                }
            else
               {
                  resultSet=userModel.getDailyRecord();
                try {
                    if(!resultSet.next()){
                            if(userModel.addDailyRecord(
                                        Float.valueOf(userView.getTextBox("morningWeight").getText()),
                                        Float.valueOf(userView.getTextBox("morningWaistSize").getText()),
                                        Float.valueOf(userView.getTextBox("eveningWeight").getText()),
                                        Float.valueOf(userView.getTextBox("eveningWaistSize").getText()),
                                        userView.getJLabel("lebelIndex").getText()
                            ))
                                {                                    
                                JOptionPane.showMessageDialog(uiView, "Your data has been added","Data Added",JOptionPane.INFORMATION_MESSAGE);
                                }
                        
                    }
                    else
                    {
                        if(userModel.updateDailyRecord(
                                Float.valueOf(userView.getTextBox("morningWeight").getText()),
                                Float.valueOf(userView.getTextBox("morningWaistSize").getText()),
                                Float.valueOf(userView.getTextBox("eveningWeight").getText()),
                                Float.valueOf(userView.getTextBox("eveningWaistSize").getText()),
                                userView.getJLabel("lebelIndex").getText()))
                        {
                        JOptionPane.showMessageDialog(uiView, "Your data has been updated","Data Updated",JOptionPane.INFORMATION_MESSAGE);
                        }  
                    }
                    
                    userModel.setMorningWeight(Float.valueOf(userView.getTextBox("morningWeight").getText()));
                    userModel.setMorningWaistSize(Float.valueOf(userView.getTextBox("morningWaistSize").getText()));
                    userModel.setEveningWeight(Float.valueOf(userView.getTextBox("eveningWeight").getText()));
                    userModel.setEveningWaistSize(Float.valueOf(userView.getTextBox("eveningWaistSize").getText()));
                    userModel.setActivityLabelIndex(userView.getJLabel("lebelIndex").getText());

                    
                } catch (SQLException ex) {
                    Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
        }        
        else if (e.getSource().equals(userView.getButton("btn_genReport"))){
            if(userModel.getStartDate()!=null && userModel.getEndDate()!=null){
           verifyInputDates();
            }
        }
    }

    public void verifyInputDates(){
        
       userView.getJLabel("lebel_error").setText("");
        Date startDate=Date.valueOf(userView.getJComboBox("year1").getSelectedItem().toString()+"-"
        +userView.getJComboBox("month1").getSelectedItem().toString()+"-"
        +userView.getJComboBox("day1").getSelectedItem().toString());
        
        Date endDate=Date.valueOf(userView.getJComboBox("year2").getSelectedItem().toString()+"-"
        +userView.getJComboBox("month2").getSelectedItem().toString()+"-"
        +userView.getJComboBox("day2").getSelectedItem().toString());
        
        
        if(startDate.compareTo(userModel.getStartDate())<0||startDate.compareTo(userModel.getEndDate())>=0){
            userView.getJLabel("lebel_error").setText("Invalid start date");
    
        }
        else if(endDate.compareTo(userModel.getEndDate())>0||endDate.compareTo(userModel.getStartDate())<=0){
            userView.getJLabel("lebel_error").setText("Invalid end date");
        }
        else {
            userView.getJLabel("lebel_error").setText("date parameters are okey");
            userModel.setStartDate(startDate);
            userModel.setEndDate(endDate);
            
            generateReport(startDate, endDate);
        }
    }
    
    
    public void generateReport(Date sdate,Date edate){
        float initialWeight=0,finalWeight=0;
    try {
         
             //emptying the pre-existing rows from the table
             
                 reportView.getDefaultTableModel("tableModel").setRowCount(0);
             
             
             resultSet=userModel.getCallorieConsumptionData(sdate, edate);
             while(resultSet.next()){
                 reportView.getDefaultTableModel("tableModel").addRow(new Object[]{resultSet.getDate(1).toString(),resultSet.getFloat(2),resultSet.getFloat(3),resultSet.getFloat(4)});
                 }
             
             resultSet=userModel.getInitialWeight(sdate);
             while(resultSet.next()){
             if(resultSet.getFloat("mWeight")!=0)
                 {
                     reportView.getJLabel("initialWeight").setText("<html><B>Initial Weight:</B><BR> "+resultSet.getFloat("mWeight")+" kg (in the morning)</html>");
                     initialWeight=resultSet.getFloat("mWeight");
                 }
             else if(resultSet.getFloat("eWeight")!=0)
                 {
                     reportView.getJLabel("initialWeight").setText("<html><B>Initial Weight:<B><BR> "+resultSet.getFloat("mWeight")+" kg (in the evening)</html>");
                     initialWeight=resultSet.getFloat("rWeight");
                 }
             else
             {
                 reportView.getJLabel("initialWeight").setText("No weight was enterd on"+sdate.toString());
                 initialWeight=0;
             }
         }
             
             
             resultSet=userModel.getFinalWeight(edate);
             while(resultSet.next()){
             if(resultSet.getFloat("eWeight")!=0)
                 {
                     reportView.getJLabel("finalWeight").setText("<html><B>Final Weight:</B><BR>"+resultSet.getFloat("eWeight")+" kg (in the evening)</html>");
                     finalWeight=resultSet.getFloat("eWeight");
                 }
             else if(resultSet.getFloat("mWeight")!=0)
                 {
                     reportView.getJLabel("finalWeight").setText("<html><B>Final Weight:</B><BR>"+resultSet.getFloat("mWeight")+" kg (in the morning)");
                     finalWeight=resultSet.getFloat("mWeight");
                 }
             else
                  {
                 reportView.getJLabel("finalWeight").setText("No weight was enterd on"+sdate.toString());
                  finalWeight=0;
                  }
             }
             
             
             resultSet=userModel.getAverageWeight(sdate, edate);
             while(resultSet.next()){
                 reportView.getJLabel("averageWeight").setText("<html><B>Average Weight:</B>"+resultSet.getFloat(1)+" kg<BR><B>Net Weight Change: </B>"+resultSet.getFloat(2)+"kg</html>");
             }
             
             if((initialWeight-finalWeight)>0)
                reportView.getJLabel("result").setText("<HTML><B>Weight Loss :</B>"+(initialWeight-finalWeight)+"kg</HTML>");
             else if((initialWeight-finalWeight)<0)
                 reportView.getJLabel("result").setText("<HTML><B>Weight Gain :</B>"+(-(initialWeight-finalWeight))+"kg</HTML>");
             else
                 reportView.getJLabel("result").setText("No change in weight found");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    reportView.getJLabel("duration").setText("<html>From :"+sdate.toString()+"<BR>To :"+edate.toString()+"</html>");

    resultSet=userModel.getDateAndWeightData(sdate, edate);
    graphView=ViewFactory.createView("graph", resultSet);
    reportView.getScollPane("graphpane").setViewportView(graphView.getPanel("graph"));
    contentView.getPanel("rightpanel").removeAll();
    contentView.add(reportView.getPanel("reportpanel"),"right");
    contentView.getPanel("rightpanel").revalidate();
    contentView.getPanel("rightpanel").repaint();
    graphView.getPanel("graph").removeAll();
    resultSet=null;
    
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        if(e.getSource().equals(userView.getJSlider("activityLevelSlider"))){
            int x=userView.getJSlider("activityLevelSlider").getValue();
            switch (x) {
                case 0:
                    userView.getJLabel("lebelIndex").setText("inactive");
                    break;
                case 10:
                    userView.getJLabel("lebelIndex").setText("moderate");
                    break;
                default:
                    userView.getJLabel("lebelIndex").setText("active");
                    break;
            }
            
        }
    }
}
