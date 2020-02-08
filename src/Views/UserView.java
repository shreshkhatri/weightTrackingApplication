package Views;
import java.awt.BorderLayout;import java.awt.Color;import java.awt.Dimension;import java.awt.Font;import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;import java.awt.Insets;import java.net.URL;import javax.swing.BorderFactory;import java.awt.Cursor;
import java.text.SimpleDateFormat;import java.util.Date;import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;import javax.swing.GroupLayout;import javax.swing.ImageIcon;import javax.swing.JButton;
import javax.swing.JComboBox;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JList;
import javax.swing.JPanel;import javax.swing.JPasswordField;import javax.swing.JScrollPane;import javax.swing.JSlider;
import javax.swing.JTextField;import javax.swing.LayoutStyle;import javax.swing.table.DefaultTableModel;

public final class UserView implements JView{
  
    private final Font f1=new Font("Verdana",Font.PLAIN,12);
    private final Font f2=new Font("Verdana",Font.BOLD,15); 
    private final Font f3=new Font("Verdana", 0, 15);
    public  static final Color c1=new Color(89, 90, 91);
    
    private JPanel pnl_t,pnl_m,pnl_b,pnl_form;
    private JLabel lbl_user,lbl_login,lbl_username,lbl_password,lbl_linkPart1,lbl_linkPart2;
    private URL url_userIcon;
    private JTextField tb_userName;
    private JPasswordField p_password;
    private JButton btn_login;
    private GridBagConstraints gb1;
    private Color fontColor;
    
     //controls for signup form
    private JPanel pnl_SignUp,pnl_signUpForm,pnl_formHeader,pnl_last,pnl_last1;
    private GridBagConstraints gbc1,gbc2;
    private JLabel lbl_name,lbl_pword,lbl_confrim_pword,lbl_notice,lbl_userSignup,lbl_linkToMainMenu,lbl_gender,lbl_age;
    private JButton btn_signUp;
    private JTextField txt_uName,txt_age;
    private JComboBox combo_gender;
    private JPasswordField pswd_1,pswd_2;
    private URL url_linkGray,urlDarkGray;
    boolean userNameAvailability;  
    private final String[] gender={"male","female","others"};  

    //contents for dashboard
    private JPanel dashboardPanel,panel_Dashboard;
    private JLabel lebel_help,lebel_Logout,lebel_AddRecord,lebel_DashBoard,lebel_EditExInfo,lebel_EditMealInfo,
            lebel_EditUserInfo,lebel_ViewReport,lebel_Welcome;
    private GridBagConstraints gb4Dashboard;
    
    //contents for summary board
    private JPanel summaryPanel,mealSummaryPanel,exSummaryPanel,extraPanel,p1,p2,p3,p4;
    private JPanel extraPnl_1,extraPnl_2;
    private JLabel extraLbl_1,extraLbl_2,lbl_moreMeal,lbl_moreExercise,newMeal,newExercise;
    private GridBagConstraints gb4Summary;
    private JLabel lebel_mealSummary,lebel_workoutSummary,lbl_Meal,lbl_Workout,lbl_tips;
    SimpleDateFormat dateFormat;
    String date;
    private JList meal_List,ex_list; 
    private JScrollPane pane1,pane2;
    private DefaultListModel mlListModel,eListModel;
    
    //controls for userinfo edit
    private JButton btn_cancel,btn_update;
    private JPanel userInfoPanel;
    private JComboBox<String> cb_gender;
    private JLabel lebel_userInfo,lebel_u,lebel_g,jLabel5,lebel_a,lebel_edit;
    private JTextField tb_username,tb_age;
    
    //controls for mealEdit Form
    private JPanel mealEditPanel;
     private javax.swing.JButton btn_mEdit;
    private javax.swing.JButton btn_mRemove;
    private javax.swing.JComboBox<String> cb_meal;
    private javax.swing.JLabel lebel_browseMeal;
    private javax.swing.JLabel lebel_editMealForm;
    private javax.swing.JLabel lebel_mcalvalue;
    private javax.swing.JLabel lebel_mname;
    private javax.swing.JTextField tb_mcalvalue;
    private javax.swing.JTextField tb_mname;
    private GroupLayout lm_forMealEditForm;
        
    //controls for exerciseEditForm
    private JPanel exerciseEditPanel;
    private javax.swing.JButton btn_eEdit;
    private javax.swing.JButton btn_eRemove;
    private javax.swing.JComboBox cb_exercise;
    private javax.swing.JLabel lebel_browseEx;
    private javax.swing.JLabel lebel_ecalvalue;
    private javax.swing.JLabel lebel_editExercise;
    private javax.swing.JLabel lebel_ename;
    private javax.swing.JTextField tb_ecalvalue;
    private javax.swing.JTextField tb_ename;
    private GroupLayout lm_forExerciseEditForm;
    
    //controls for daily record add form
     private javax.swing.JButton btn_addDailyRecord;
    private javax.swing.JSlider activityLevelSlider;
    private javax.swing.JLabel lbl_activityQuestion;
    private javax.swing.JLabel lbl_e;
    private javax.swing.JLabel lbl_index;
    private javax.swing.JLabel lbl_m;
    private javax.swing.JLabel lbl_waistSize1;
    private javax.swing.JLabel lbl_waistSize2;
    private javax.swing.JLabel lbl_weight1;
    private javax.swing.JLabel lbl_weight2;
    private javax.swing.JLabel lebel_addDailyRecord;
    private javax.swing.JPanel pnl_activityLevel;
    private javax.swing.JPanel pnl_evening;
    private javax.swing.JPanel pnl_morning;
    private javax.swing.JTextField tb_eWaist;
    private javax.swing.JTextField tb_eWight;
    private javax.swing.JTextField tb_mWaist;
    private javax.swing.JTextField tb_mWight;
    private JPanel dailyRecordPanel;
    private GroupLayout lmForDailyRecordForm;
    
    //report for view report panel
    private JPanel reportPanel,pnl_date;
    private JComboBox d1,m1,y1,d2,m2,y2;
    private GridBagConstraints gb4report;
    private JLabel lebel_To,lebel_From,lebel_startDate,lebel_endDate,lebel_title,lebel_error;
    private JButton btn_genReport;
    
    //controls for help section
    private JPanel helpPanel,pnl_helpContent;
    private GridBagConstraints gb4HelpPanel;
    private JScrollPane pane;
    private JLabel lbl_hlpTitle,lbl_topic,lbl_explanation;
    
    public void initializeHelpSection(){
    
        helpPanel=new JPanel(new BorderLayout());
        helpPanel.setBorder(BorderFactory.createLineBorder(c1));
     
        lbl_hlpTitle=new JLabel("<html><u> Help and Instructions <u></html>");
        lbl_hlpTitle.setFont(f2);
        
        lbl_topic=new JLabel();
        lbl_topic.setFont(new Font("Verdana",Font.BOLD,12)); 
        
        lbl_explanation=new JLabel();
        lbl_explanation.setFont(f1);
           
        pnl_helpContent=new JPanel(new GridBagLayout());
        pnl_helpContent.setBackground(Color.white);
        pnl_helpContent.setPreferredSize(new Dimension(680,500));
        
        gb4HelpPanel=new GridBagConstraints();
        gb4HelpPanel.gridx=gb4HelpPanel.gridy=0;
        
        pane=new JScrollPane(pnl_helpContent);
        gb4HelpPanel.insets=new Insets(0,0,35,0);
        pnl_helpContent.add(lbl_hlpTitle,gb4HelpPanel);
        gb4HelpPanel.insets=new Insets(10,0,5,0);
        gb4HelpPanel.gridy++;
        lbl_topic.setText("<html><u>Signing Up</u></html>");
        lbl_explanation.setText("<html>To signup into the system, click on the 'click here link provided on home screen of the application.<BR> You will have to choose a unique username, set a password, define the age and select<BR>"
                + " your gender</html>");
        pnl_helpContent.add(lbl_topic,gb4HelpPanel);
        gb4HelpPanel.gridy++;
        pnl_helpContent.add(lbl_explanation,gb4HelpPanel);
        gb4HelpPanel.gridy++;
         pnl_helpContent.add(lbl_topic,gb4HelpPanel);
        gb4HelpPanel.gridy++;
        pnl_helpContent.add(lbl_explanation,gb4HelpPanel);
        gb4HelpPanel.gridy++;
         pnl_helpContent.add(lbl_topic,gb4HelpPanel);
        gb4HelpPanel.gridy++;
        pnl_helpContent.add(lbl_explanation,gb4HelpPanel);
                
        
        helpPanel.add(pane,BorderLayout.CENTER);
    }
    
    
    public void initializeDateSelectionForm(){ 
        lebel_title=new JLabel("<html><u>Date Selection for Report generation</u></html>");
        lebel_title.setFont(f2);
        
        lebel_error=new JLabel(" ");
        
        btn_genReport=new JButton("Generate Report");
        btn_genReport.setFont(f1);
        btn_genReport.setPreferredSize(new Dimension(200,40));
        
        reportPanel=new JPanel(new BorderLayout());
        
        lebel_startDate=new JLabel();
        lebel_startDate.setFont(f1);
        
        lebel_endDate=new JLabel();
        lebel_endDate.setFont(f1);
        
        pnl_date=new JPanel(new GridBagLayout());
        pnl_date.setBackground(Color.white);
        pnl_date.setBorder(BorderFactory.createLineBorder(c1));
        
        gb4report=new GridBagConstraints();
        gb4report.gridx=gb4report.gridy=0;
        
        
        lebel_From=new JLabel("Select start date");
        lebel_From.setFont(f1);
        
        y1=new JComboBox();
        y1.setFont(f1);
        
        m1=new JComboBox();
        m1.setFont(f1);
        
        d1=new JComboBox();
        d1.setFont(f1);
        
        lebel_To=new JLabel("Select end Date ");
        lebel_To.setFont(f1);
        
        y2=new JComboBox();
        y2.setFont(f1);
        
        m2=new JComboBox();
        m2.setFont(f1);
        
        d2=new JComboBox();
        d2.setFont(f1);
       
       for(int i=0;i<20;i++)
        {
        y1.addItem(2017+i);
        y2.addItem(2017+i);
        }
        
        for(int i=0;i<31;i++){
        d1.addItem(i+1);
        d2.addItem(i+1);
        }
        
        for(int i=0;i<12;i++){
        m1.addItem(i+1);
        m2.addItem(i+1);
        }
        gb4report.gridwidth=5;
        gb4report.insets=new Insets(0,0,80,0);
        pnl_date.add(lebel_title,gb4report);
        gb4report.gridwidth=1;
        gb4report.gridy++;
        gb4report.insets=new Insets(0,5,15,5);
        pnl_date.add(lebel_From,gb4report);
        gb4report.gridx++;
        pnl_date.add(y1,gb4report);
        gb4report.gridx++;
        pnl_date.add(m1,gb4report);
        gb4report.gridx++;
        pnl_date.add(d1,gb4report);
        gb4report.gridx++;
        pnl_date.add(lebel_startDate,gb4report);
        gb4report.gridx=0;
        gb4report.gridy++;
        gb4report.insets=new Insets(25,5,0,5);
        pnl_date.add(lebel_To,gb4report);
        gb4report.gridx++;
        pnl_date.add(y2,gb4report);
        gb4report.gridx++;
        pnl_date.add(m2,gb4report);
        gb4report.gridx++;
        pnl_date.add(d2,gb4report);
        gb4report.gridx++;
        pnl_date.add(lebel_endDate,gb4report);
        gb4report.gridx=0;gb4report.gridy++;
        gb4report.gridwidth=5;
        pnl_date.add(lebel_error,gb4report);
        gb4report.gridy++;
        gb4report.insets=new Insets(80,0,0,0);
        pnl_date.add(btn_genReport,gb4report);
        
        
        reportPanel.add(pnl_date,BorderLayout.CENTER);
        
}
    
    public UserView(){
       initializeLoginform();
       initializeSignUpform();
       initializeDashBoard();
       initializeSummaryForm();
       initializeEditUserInfoForm();
       initializeMealEditForm();
       initializeExerciseEditForm();
       initializeAddDailyRecordForm();
       initializeDateSelectionForm();
       initializeHelpSection();
    }
    
    public void initializeAddDailyRecordForm(){
    
        dailyRecordPanel=new JPanel();
        dailyRecordPanel.setBorder(BorderFactory.createLineBorder(c1));
        lmForDailyRecordForm=new GroupLayout(dailyRecordPanel);
        dailyRecordPanel.setLayout(lmForDailyRecordForm);
        
         lebel_addDailyRecord = new javax.swing.JLabel();
        pnl_morning = new javax.swing.JPanel();
        lbl_m = new javax.swing.JLabel();
        lbl_weight1 = new javax.swing.JLabel();
        tb_mWight = new javax.swing.JTextField();
        lbl_waistSize1 = new javax.swing.JLabel();
        tb_mWaist = new javax.swing.JTextField();
        pnl_evening = new javax.swing.JPanel();
        lbl_e = new javax.swing.JLabel();
        lbl_weight2 = new javax.swing.JLabel();
        tb_eWight = new javax.swing.JTextField();
        lbl_waistSize2 = new javax.swing.JLabel();
        tb_eWaist = new javax.swing.JTextField();
        pnl_activityLevel = new javax.swing.JPanel();
        lbl_activityQuestion = new javax.swing.JLabel();
        activityLevelSlider = new javax.swing.JSlider();
        lbl_index = new javax.swing.JLabel();
        btn_addDailyRecord = new javax.swing.JButton();

        dailyRecordPanel.setBackground(new java.awt.Color(255, 255, 255));

        lebel_addDailyRecord.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lebel_addDailyRecord.setText("Enter Records For today [ "+date+" ]");

        pnl_morning.setBackground(new java.awt.Color(39, 133, 238));

        lbl_m.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lbl_m.setForeground(new java.awt.Color(255, 255, 255));
        lbl_m.setText("Data in the Morning");

        javax.swing.GroupLayout pnl_morningLayout = new javax.swing.GroupLayout(pnl_morning);
        pnl_morning.setLayout(pnl_morningLayout);
        pnl_morningLayout.setHorizontalGroup(
            pnl_morningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_morningLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_m)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_morningLayout.setVerticalGroup(
            pnl_morningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_morningLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_m)
                .addContainerGap())
        );

        lbl_weight1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbl_weight1.setText("Weight (in kgs)");

        tb_mWight.setColumns(10);
        tb_mWight.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        lbl_waistSize1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbl_waistSize1.setText("Waist size( in cm)");

        tb_mWaist.setColumns(10);
        tb_mWaist.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        pnl_evening.setBackground(new java.awt.Color(39, 133, 238));

        lbl_e.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lbl_e.setForeground(new java.awt.Color(255, 255, 255));
        lbl_e.setText("Data in the Evening");

        javax.swing.GroupLayout pnl_eveningLayout = new javax.swing.GroupLayout(pnl_evening);
        pnl_evening.setLayout(pnl_eveningLayout);
        pnl_eveningLayout.setHorizontalGroup(
            pnl_eveningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_eveningLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_e)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_eveningLayout.setVerticalGroup(
            pnl_eveningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_eveningLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_e)
                .addContainerGap())
        );

        lbl_weight2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbl_weight2.setText("Weight (in kgs)");

        tb_eWight.setColumns(10);
        tb_eWight.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
       

        lbl_waistSize2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbl_waistSize2.setText("Waist size( in cm)");

        tb_eWaist.setColumns(10);
        tb_eWaist.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        pnl_activityLevel.setBackground(new java.awt.Color(39, 133, 238));

        lbl_activityQuestion.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lbl_activityQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_activityQuestion.setText("How would you rate your activity level for today?");

        javax.swing.GroupLayout pnl_activityLevelLayout = new javax.swing.GroupLayout(pnl_activityLevel);
        pnl_activityLevel.setLayout(pnl_activityLevelLayout);
        pnl_activityLevelLayout.setHorizontalGroup(
            pnl_activityLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_activityLevelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_activityQuestion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_activityLevelLayout.setVerticalGroup(
            pnl_activityLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_activityLevelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_activityQuestion)
                .addContainerGap())
        );

        activityLevelSlider.setMajorTickSpacing(10);
        activityLevelSlider.setMaximum(20);
        activityLevelSlider.setMinimum(0);
        activityLevelSlider.setValue(0);
        activityLevelSlider.setSnapToTicks(true);

        lbl_index.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbl_index.setText("inactive");

        btn_addDailyRecord.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
       
        lmForDailyRecordForm.setHorizontalGroup(
            lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lmForDailyRecordForm.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lmForDailyRecordForm.createSequentialGroup()
                        .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lmForDailyRecordForm.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_weight2)
                                    .addComponent(lbl_waistSize2))
                                .addGap(49, 49, 49)
                                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_eWaist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tb_eWight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pnl_activityLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnl_evening, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lmForDailyRecordForm.createSequentialGroup()
                                    .addGap(134, 134, 134)
                                    .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_weight1)
                                        .addComponent(lbl_waistSize1))
                                    .addGap(49, 49, 49)
                                    .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tb_mWaist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tb_mWight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnl_morning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lmForDailyRecordForm.createSequentialGroup()
                        .addComponent(btn_addDailyRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298))))
            .addGroup(lmForDailyRecordForm.createSequentialGroup()
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lmForDailyRecordForm.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(activityLevelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbl_index, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lmForDailyRecordForm.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(lebel_addDailyRecord)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lmForDailyRecordForm.setVerticalGroup(
            lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lmForDailyRecordForm.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lebel_addDailyRecord)
                .addGap(18, 18, 18)
                .addComponent(pnl_morning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_weight1)
                    .addComponent(tb_mWight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_waistSize1)
                    .addComponent(tb_mWaist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnl_evening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_weight2)
                    .addComponent(tb_eWight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_waistSize2)
                    .addComponent(tb_eWaist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnl_activityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(lmForDailyRecordForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activityLevelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_index))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btn_addDailyRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
    }
    
    public void initializeExerciseEditForm(){
    
        exerciseEditPanel=new JPanel();
        lm_forExerciseEditForm=new GroupLayout(exerciseEditPanel);
        exerciseEditPanel.setLayout(lm_forExerciseEditForm);
        exerciseEditPanel.setBorder(BorderFactory.createLineBorder(c1));
        
        
        btn_eRemove = new javax.swing.JButton();
        btn_eRemove.setForeground(c1);
        
        btn_eEdit = new javax.swing.JButton();
        btn_eEdit.setForeground(c1);
        
        lebel_editExercise = new javax.swing.JLabel();
        lebel_editExercise.setForeground(c1);
        
        lebel_browseEx = new javax.swing.JLabel();
        lebel_browseEx.setForeground(c1);
        
        cb_exercise = new javax.swing.JComboBox<>();
        cb_exercise.setForeground(c1);
        
        lebel_ename = new javax.swing.JLabel();
        lebel_ename.setForeground(c1);
        
        lebel_ecalvalue = new javax.swing.JLabel();
        lebel_ecalvalue.setForeground(c1);
        
        tb_ename = new javax.swing.JTextField();
        tb_ecalvalue = new javax.swing.JTextField();

        exerciseEditPanel.setBackground(new java.awt.Color(255, 255, 255));

        btn_eRemove.setBackground(new java.awt.Color(39, 133, 238));
        btn_eRemove.setFont(f1); // NOI18N
        btn_eRemove.setText("Remove");
      
        btn_eEdit.setBackground(new java.awt.Color(39, 133, 238));
        btn_eEdit.setFont(f1); // NOI18N
        btn_eEdit.setText("Edit");

        lebel_editExercise.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lebel_editExercise.setText("<html><u>Edit or Remove Exercise Items</u></html>");

        lebel_browseEx.setFont(f1); // NOI18N
        lebel_browseEx.setText("Browse Meals");

        cb_exercise.setFont(f1); // NOI18N
        
        lebel_ename.setFont(f1); // NOI18N
        lebel_ename.setText("Meal Name");

        lebel_ecalvalue.setFont(f1); // NOI18N
        lebel_ecalvalue.setText("callorie/min value");

        tb_ename.setColumns(10);
        tb_ename.setFont(f1); // NOI18N
        

        tb_ecalvalue.setColumns(10);
        tb_ecalvalue.setFont(f1); // NOI18N

        lm_forExerciseEditForm.setHorizontalGroup(
            lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lm_forExerciseEditForm.createSequentialGroup()
                .addGap(0, 170, Short.MAX_VALUE)
                .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lm_forExerciseEditForm.createSequentialGroup()
                        .addComponent(lebel_browseEx)
                        .addGap(60, 60, 60)
                        .addComponent(cb_exercise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lebel_editExercise)
                    .addGroup(lm_forExerciseEditForm.createSequentialGroup()
                        .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lebel_ename)
                            .addComponent(lebel_ecalvalue))
                        .addGap(28, 28, 28)
                        .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tb_ename)
                            .addComponent(tb_ecalvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(194, 194, 194))
            .addGroup(lm_forExerciseEditForm.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(btn_eEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_eRemove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lm_forExerciseEditForm.setVerticalGroup(
            lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lm_forExerciseEditForm.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lebel_editExercise)
                .addGap(59, 59, 59)
                .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_browseEx)
                    .addComponent(cb_exercise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_ename)
                    .addComponent(tb_ename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_ecalvalue)
                    .addComponent(tb_ecalvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(lm_forExerciseEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

    }
    public void initializeMealEditForm(){
    
        mealEditPanel=new JPanel();
        lm_forMealEditForm=new GroupLayout(mealEditPanel);
        mealEditPanel.setLayout(lm_forMealEditForm);
        mealEditPanel.setBorder(BorderFactory.createLineBorder(c1));
        btn_mRemove = new javax.swing.JButton();
        btn_mRemove.setForeground(c1);
        
        btn_mEdit = new javax.swing.JButton();
        btn_mEdit.setForeground(c1);
        
        lebel_editMealForm = new javax.swing.JLabel();
        lebel_editMealForm.setForeground(c1);
        
        lebel_browseMeal = new javax.swing.JLabel();
        lebel_browseMeal.setForeground(c1);
        
        cb_meal = new javax.swing.JComboBox<>();
        cb_meal.setForeground(c1);
        
        lebel_mname = new javax.swing.JLabel();
        lebel_mname.setForeground(c1);
        
        lebel_mcalvalue = new javax.swing.JLabel();
        lebel_mcalvalue.setForeground(c1);
        
        tb_mname = new javax.swing.JTextField();
        tb_mcalvalue = new javax.swing.JTextField();

        mealEditPanel.setBackground(new java.awt.Color(255, 255, 255));

        btn_mRemove.setBackground(new java.awt.Color(39, 133, 238));
        btn_mRemove.setFont(f1); // NOI18N
        btn_mRemove.setText("Remove");
       

        btn_mEdit.setBackground(new java.awt.Color(39, 133, 238));
        btn_mEdit.setFont(f1); // NOI18N
        btn_mEdit.setText("Edit");

        lebel_editMealForm.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lebel_editMealForm.setText("<html><u>Edit or Remove Meal Items</u></html>");

        lebel_browseMeal.setFont(f1); // NOI18N
        lebel_browseMeal.setText("Browse Meals");

        cb_meal.setFont(f1); // NOI18N
       

        lebel_mname.setFont(f1); // NOI18N
        lebel_mname.setText("Meal Name");

        lebel_mcalvalue.setFont(f1); // NOI18N
        lebel_mcalvalue.setText("callorie/gram value");

        tb_mname.setColumns(10);
        tb_mname.setFont(f1); // NOI18N
        

        tb_mcalvalue.setColumns(10);
        tb_mcalvalue.setFont(f1); 
        lm_forMealEditForm.setHorizontalGroup(
            lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lm_forMealEditForm.createSequentialGroup()
                .addGap(0, 212, Short.MAX_VALUE)
                .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lm_forMealEditForm.createSequentialGroup()
                        .addComponent(lebel_browseMeal)
                        .addGap(60, 60, 60)
                        .addComponent(cb_meal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lebel_editMealForm)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lm_forMealEditForm.createSequentialGroup()
                        .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lebel_mname)
                            .addComponent(lebel_mcalvalue))
                        .addGap(28, 28, 28)
                        .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tb_mname)
                            .addComponent(tb_mcalvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(194, 194, 194))
            .addGroup(lm_forMealEditForm.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(btn_mEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_mRemove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lm_forMealEditForm.setVerticalGroup(
            lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lm_forMealEditForm.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lebel_editMealForm)
                .addGap(59, 59, 59)
                .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_browseMeal)
                    .addComponent(cb_meal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_mname)
                    .addComponent(tb_mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_mcalvalue)
                    .addComponent(tb_mcalvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(lm_forMealEditForm.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_mEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
    }
    
    public void initializeEditUserInfoForm(){
    
        userInfoPanel=new JPanel();
        GroupLayout layoutObject=new GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(layoutObject);
        userInfoPanel.setBorder(BorderFactory.createLineBorder(c1));
        
        lebel_userInfo = new JLabel();
        lebel_u = new JLabel();
        lebel_a = new JLabel();
        lebel_g = new JLabel();
        cb_gender = new JComboBox<>();
        tb_username = new JTextField();
        tb_age = new JTextField();
        btn_update = new JButton();
        jLabel5 = new JLabel();
        lebel_edit = new JLabel();
        btn_cancel = new JButton();
        
          userInfoPanel.setBackground(new Color(255, 255, 255));

        lebel_userInfo.setFont(new Font("Verdana", 1, 18)); // NOI18N
        lebel_userInfo.setText("<html><u>Your Personal Details</u></html>");

        lebel_u.setFont(f1); // NOI18N
        lebel_u.setText("Username");

        lebel_a.setFont(f1); // NOI18N
        lebel_a.setText("Age");

        lebel_g.setFont(f1); // NOI18N
        lebel_g.setText("Gender");

        cb_gender.setFont(f1); // NOI18N
        cb_gender.setModel(new DefaultComboBoxModel<>(new String[] { "male", "female", "others" }));
        cb_gender.setEnabled(false);
        
        tb_username.setColumns(10);
        tb_username.setFont(f1); // NOI18N
        tb_username.setEditable(false);
        
        tb_age.setColumns(10);
        tb_age.setFont(f1); // NOI18N
        tb_age.setEditable(false);
        
        btn_update.setBackground(new java.awt.Color(39, 133, 238));
        btn_update.setFont(f1); // NOI18N
        btn_update.setText("Update");
        btn_update.setEnabled(false);
        
        jLabel5.setFont(f1); // NOI18N
        jLabel5.setText("If you wish to update your details,");

        lebel_edit.setFont(f1); // NOI18N
        lebel_edit.setText("click here");
        lebel_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn_cancel.setBackground(new Color(39, 133, 238));
        btn_cancel.setFont(f1); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setEnabled(false);

        layoutObject.setHorizontalGroup(
            layoutObject.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutObject.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layoutObject.createSequentialGroup()
                        .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lebel_u)
                            .addComponent(lebel_a)
                            .addComponent(lebel_g))
                        .addGap(41, 41, 41)
                        .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(tb_age)
                            .addComponent(tb_username)
                            .addComponent(cb_gender,GroupLayout.PREFERRED_SIZE, 116,GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layoutObject.createSequentialGroup()
                        .addComponent(btn_update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cancel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layoutObject.createSequentialGroup()
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layoutObject.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(lebel_userInfo))
                    .addGroup(layoutObject.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lebel_edit)))
                .addGap(0, 238, Short.MAX_VALUE))
        );
        layoutObject.setVerticalGroup(
            layoutObject.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutObject.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lebel_userInfo)
                .addGap(60, 60, 60)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lebel_edit))
                .addGap(48, 48, 48)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_u)
                    .addComponent(tb_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_a)
                    .addComponent(tb_age, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lebel_g)
                    .addComponent(cb_gender,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layoutObject.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

    }
    
    
    public void initializeSummaryForm(){
        
        lbl_tips=new JLabel();
        
        lbl_tips.setFont(f1);
        mlListModel=new DefaultListModel();
        meal_List=new JList(mlListModel);
        meal_List.setFont(f1);
        
        eListModel=new DefaultListModel();
        ex_list=new JList(eListModel);
        ex_list.setFont(f1);
                 
        pane1=new JScrollPane(meal_List); 
        pane2=new JScrollPane(ex_list); 
      
        dateFormat=new SimpleDateFormat("MMM d, yyyy");
        date=dateFormat.format(new Date());
        summaryPanel=new JPanel(new GridBagLayout());
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createLineBorder(c1));
        gb4Summary=new GridBagConstraints();
        gb4Summary.gridx=gb4Summary.gridy=0;
        gb4Summary.insets=new Insets(0,0,10,0);
        
        
        //MEAL SUMMARY SECTIOM--------------------------------------
        p1=new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 570));
        lbl_moreMeal=new JLabel("Add more meals");
        p1.add(lbl_moreMeal);
        
        lbl_moreMeal.setFont(f1);
        lbl_moreMeal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Meal=new JLabel();
        lbl_Meal.setFont(f1);
        
        lebel_mealSummary=new JLabel("<html><u>Meal Summary for today [ "+date+" ]</u></html>");
        lebel_mealSummary.setFont(f2);
        
        mealSummaryPanel=new JPanel(new BorderLayout());
        mealSummaryPanel.setPreferredSize(new Dimension(695,150));
        mealSummaryPanel.setBackground(Color.WHITE);
        mealSummaryPanel.add(lebel_mealSummary,BorderLayout.NORTH);
        mealSummaryPanel.add(lbl_Meal,BorderLayout.CENTER);
        mealSummaryPanel.add(p1,BorderLayout.SOUTH);
        mealSummaryPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
          
        //WORKOUT SUMMARY SECTION---------------------------------------
        p2=new JPanel();
        p2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 500));
        
        lbl_moreExercise=new JLabel("Add more workout details");
        p2.add(lbl_moreExercise);
        
        lbl_moreExercise.setFont(f1);
        lbl_moreExercise.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_Workout=new JLabel();
        lbl_Workout.setFont(f1);
        lebel_workoutSummary=new JLabel("<html><u>Workout Summary for today [ "+date+" ]</u></html>");
        lebel_workoutSummary.setFont(f2);
        
        exSummaryPanel=new JPanel(new BorderLayout());
        exSummaryPanel.setPreferredSize(new Dimension(695,150));
        exSummaryPanel.setBackground(Color.WHITE);
        exSummaryPanel.add(lebel_workoutSummary,BorderLayout.NORTH);
        exSummaryPanel.add(lbl_Workout,BorderLayout.CENTER);
        exSummaryPanel.add(p2,BorderLayout.SOUTH);
        exSummaryPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
        
         
         //EXTRA INFO SECTION-------------------------------------------
        extraLbl_1=new JLabel("Currently listed Meal Items");
        extraLbl_1.setFont(f2);
        p3=new JPanel();
        p3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));
        newMeal=new JLabel("Add New Meal");
        newMeal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newMeal.setFont(f1);
        p3.add(newMeal);
        
        extraLbl_2=new JLabel("Currently listed Exercise Items");
        extraLbl_2.setFont(f2);
        p4=new JPanel();
        p4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        newExercise=new JLabel("Add New Exercise");
        newExercise.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newExercise.setFont(f1);
        p4.add(newExercise);
        
        extraPanel=new JPanel(new BorderLayout());
        extraPanel.setPreferredSize(new Dimension(695,150));
        extraPanel.setBackground(Color.WHITE);
        
        extraPnl_1=new JPanel(new BorderLayout());
        extraPnl_1.setPreferredSize(new Dimension(345,150));
        extraPnl_1.setBackground(Color.WHITE);
        extraPnl_1.add(extraLbl_1,BorderLayout.NORTH);
        extraPnl_1.add(pane1,BorderLayout.CENTER);
        extraPnl_1.add(p3,BorderLayout.SOUTH);
        extraPnl_1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        extraPnl_2=new JPanel(new BorderLayout());
        extraPnl_2.setPreferredSize(new Dimension(340,150));
        extraPnl_2.setBackground(Color.WHITE);
        extraPnl_2.add(extraLbl_2,BorderLayout.NORTH);
        extraPnl_2.add(pane2,BorderLayout.CENTER);
        extraPnl_2.add(p4,BorderLayout.SOUTH);
        extraPnl_2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));        
        
        extraPanel.add(extraPnl_1,BorderLayout.EAST);
        extraPanel.add(extraPnl_2,BorderLayout.WEST);
        
        // GUI FORMATION ----------------------------------------
        summaryPanel.add(mealSummaryPanel,gb4Summary);
        gb4Summary.gridy++;
        summaryPanel.add(exSummaryPanel,gb4Summary);
        gb4Summary.gridy++;
        summaryPanel.add(lbl_tips,gb4Summary);
        gb4Summary.gridy++;
        summaryPanel.add(extraPanel,gb4Summary);
        
    }
    
    public void initializeDashBoard(){
       lebel_Welcome = new JLabel();
        lebel_EditUserInfo = new JLabel();
        lebel_EditMealInfo = new JLabel();
        lebel_EditExInfo = new JLabel();
        lebel_AddRecord = new JLabel();
        lebel_ViewReport = new JLabel();
        panel_Dashboard = new JPanel();
        lebel_DashBoard = new JLabel();
        lebel_Logout=new JLabel();
        lebel_help=new JLabel();
        
        lebel_Welcome.setBackground(new Color(39, 133, 238));
        lebel_Welcome.setFont(new Font("Verdana", 1, 20)); // NOI18N
        lebel_Welcome.setText("Welcome ");
        lebel_Welcome.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        lebel_Welcome.setPreferredSize(new Dimension(200,90));
        
        lebel_EditUserInfo.setBackground(new Color(255, 255, 255));
        lebel_EditUserInfo.setFont(f3); // NOI18N
        lebel_EditUserInfo.setForeground(c1);
        lebel_EditUserInfo.setIcon(new ImageIcon(getClass().getResource("/graphics/editInfo.png"))); // NOI18N
        lebel_EditUserInfo.setText("Edit your Details");
        lebel_EditUserInfo.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_EditUserInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_EditUserInfo.setPreferredSize(new Dimension(200,40));
        
        lebel_EditMealInfo.setBackground(new Color(255, 255, 255));
        lebel_EditMealInfo.setFont(f3); // NOI18N
        lebel_EditMealInfo.setForeground(c1);
        lebel_EditMealInfo.setIcon(new ImageIcon(getClass().getResource("/graphics/editMeal.png"))); // NOI18N
        lebel_EditMealInfo.setText("Edit Meal items");
        lebel_EditMealInfo.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_EditMealInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_EditMealInfo.setPreferredSize(new Dimension(200,40));
        
        lebel_EditExInfo.setBackground(new Color(255, 255, 255));
        lebel_EditExInfo.setFont(f3); // NOI18N
        lebel_EditExInfo.setForeground(c1);
        lebel_EditExInfo.setIcon(new ImageIcon(getClass().getResource("/graphics/editExercise.png"))); // NOI18N
        lebel_EditExInfo.setText("Edit Exercise items");
        lebel_EditExInfo.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_EditExInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_EditExInfo.setPreferredSize(new Dimension(200,40));
        
        lebel_AddRecord.setBackground(new Color(255, 255, 255));
        lebel_AddRecord.setFont(f3); // NOI18N
        lebel_AddRecord.setForeground(c1);
        lebel_AddRecord.setIcon(new ImageIcon(getClass().getResource("/graphics/record.png"))); // NOI18N
        lebel_AddRecord.setText("Add Daily Record");
        lebel_AddRecord.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_AddRecord.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_AddRecord.setPreferredSize(new Dimension(200,40));
        
        lebel_ViewReport.setBackground(new Color(255, 255, 255));
        lebel_ViewReport.setFont(f3); // NOI18N
        lebel_ViewReport.setForeground(c1);
        lebel_ViewReport.setIcon(new ImageIcon(getClass().getResource("/graphics/genReport.png"))); // NOI18N
        lebel_ViewReport.setText("View Report");
        lebel_ViewReport.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_ViewReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_ViewReport.setPreferredSize(new Dimension(200,40));
        
        lebel_help.setBackground(new Color(255, 255, 255));
        lebel_help.setFont(f3); // NOI18N
        lebel_help.setForeground(c1);
        lebel_help.setIcon(new ImageIcon(getClass().getResource("/graphics/help.png"))); // NOI18N
        lebel_help.setText("Help");
        lebel_help.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_help.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_help.setPreferredSize(new Dimension(200,40));
        
       lebel_Logout.setBackground(new Color(255, 255, 255));
        lebel_Logout.setFont(f3); // NOI18N
        lebel_Logout.setForeground(c1);
        lebel_Logout.setIcon(new ImageIcon(getClass().getResource("/graphics/logOut.png"))); // NOI18N
        lebel_Logout.setText("Log Out");
        lebel_Logout.setBorder(BorderFactory.createLineBorder(new Color(39, 133, 238), 1, true));
        lebel_Logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lebel_Logout.setPreferredSize(new Dimension(200,40));
        
        panel_Dashboard.setBackground(new Color(39, 133, 238));
        panel_Dashboard.setPreferredSize(new Dimension(200,45));
        
        lebel_DashBoard.setFont(new Font("Verdana", 1, 20)); // NOI18N
        lebel_DashBoard.setForeground(new Color(255, 255, 255));
        lebel_DashBoard.setText("DASHBOARD");
        lebel_DashBoard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel_Dashboard.add(lebel_DashBoard);

        dashboardPanel=new JPanel(new GridBagLayout());
        dashboardPanel.setBackground(Color.white);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        gb4Dashboard=new GridBagConstraints();
        gb4Dashboard.gridx=gb4Dashboard.gridy=0;
        gb4Dashboard.insets=new Insets(2,0,2,0);
        
        dashboardPanel.add(lebel_Welcome,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(panel_Dashboard,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_EditUserInfo,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_EditMealInfo,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_EditExInfo,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_AddRecord,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_ViewReport,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_help,gb4Dashboard);
        gb4Dashboard.gridy++;
        dashboardPanel.add(lebel_Logout,gb4Dashboard);
        
        
    }
    
    public void initializeLoginform(){
        
        fontColor=new Color(96,97,99);
        pnl_form=new JPanel(new BorderLayout());
        url_userIcon=getClass().getResource("/graphics/userIcon.png");
        lbl_user=new JLabel(new ImageIcon(url_userIcon));
        
        pnl_t=new JPanel();
        pnl_t.setPreferredSize(new Dimension(300,105));
        pnl_t.setBackground(Color.white);
        pnl_t.add(lbl_user);
        
        lbl_login=new JLabel("User Login ");
        lbl_username=new JLabel("Username");
        lbl_password=new JLabel("Password");
        
        lbl_linkPart1=new JLabel("For new user registration |");
        lbl_linkPart1.setFont(new Font("Verdana",Font.PLAIN,11));
        lbl_linkPart2=new JLabel("Click here");
        lbl_linkPart2.setFont(new Font("Verdana",Font.PLAIN,11));
        lbl_linkPart2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        tb_userName=new JTextField(10);
        tb_userName.setFont(new Font("Verdana",Font.PLAIN,15));
        tb_userName.setForeground(fontColor);
      
        //tb_userName.setPreferredSize(new Dimension(150,50));
        p_password=new JPasswordField(10);
        p_password.setFont(new Font("Verdana",Font.PLAIN,15));
        p_password.setForeground(fontColor);
        
        btn_login=new JButton("Login");
        
        btn_login.setFont(new Font("Verdana",Font.PLAIN,15));
        btn_login.setPreferredSize(new Dimension(100,40));
        btn_login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btn_login.setBackground(new Color(89,149,247));
        btn_login.setForeground(fontColor);
        btn_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        lbl_login.setFont(new Font("Verdana",Font.PLAIN,20));
        lbl_login.setForeground(fontColor);
        
        lbl_username.setFont(new Font("Verdana",Font.PLAIN,15));
        lbl_username.setForeground(fontColor);
        
        lbl_password.setFont(new Font("Verdana",Font.PLAIN,15));
        lbl_password.setForeground(fontColor);
        
        pnl_m=new JPanel(new GridBagLayout());
        gb1=new GridBagConstraints();
        gb1.gridx=gb1.gridy=0;
        
         pnl_m.setBackground(Color.white);
         pnl_m.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
         gb1.insets=new Insets(0, 0, 10, 0);
         pnl_m.add(lbl_login,gb1);
        gb1.insets=new Insets(0, 0, 0, 0);
         gb1.ipady=5;
        //gb1.insets=new Insets(0, 0, 0, 0);
         gb1.gridy++;
         pnl_m.add(lbl_username,gb1);
         gb1.gridy++;
         pnl_m.add(tb_userName,gb1);
         gb1.gridy++;
         pnl_m.add(lbl_password,gb1);
         gb1.gridy++;
         pnl_m.add(p_password,gb1);
         gb1.gridy++;
         gb1.ipady=0;
         gb1.insets=new Insets(15, 0, 0, 0);
         pnl_m.add(btn_login,gb1);
         pnl_b=new JPanel();
         pnl_b.add(lbl_linkPart1);
         pnl_b.add(lbl_linkPart2);
         pnl_b.setBackground(Color.white);
        
        pnl_form.add(pnl_t,BorderLayout.NORTH);
        
        pnl_form.add(pnl_m,BorderLayout.CENTER);
        pnl_form.add(pnl_b,BorderLayout.SOUTH);
     
        }
    public void initializeSignUpform(){
         btn_signUp=new JButton("Sign up");
        btn_signUp.setFont(f1);
        btn_signUp.setForeground(Color.GRAY);
        btn_signUp.setBackground(new Color(89,149,247));
        btn_signUp.setPreferredSize(new Dimension(150,30));
        
        pnl_formHeader=new JPanel();
        pnl_formHeader.setBackground(Color.white);
        pnl_formHeader.setPreferredSize(new Dimension(300,50));
        pnl_formHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
       
        url_linkGray=getClass().getResource("/graphics/lightGray.png");
        lbl_linkToMainMenu=new JLabel(new ImageIcon(url_linkGray));
        lbl_linkToMainMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pnl_formHeader.add(lbl_linkToMainMenu);
        
        pnl_signUpForm=new JPanel(new GridBagLayout());
        pnl_signUpForm.setBackground(Color.white);
        pnl_signUpForm.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        gbc2=new GridBagConstraints();
        gbc2.gridx=gbc2.gridy=0;
        
        gbc1=new GridBagConstraints();
        gbc1.gridx=gbc1.gridy=0;
        gbc1.ipadx=2;
        gbc1.ipady=2;
        gbc1.insets=new Insets(2,5,15,5);
        
        pswd_1=new JPasswordField(10);
        pswd_1.setFont(f1);
        //pswd_1.addFocusListener(this);
        
        pswd_2=new JPasswordField(10);
        pswd_2.setFont(f1);
       // pswd_2.addFocusListener(this);
        
        txt_uName=new JTextField(10);
        txt_uName.setFont(f1);
        
        txt_age=new JTextField(10);
        txt_age.setFont(f1);
        
        //txt_uName.addFocusListener(this);
        
        lbl_userSignup=new JLabel("<html><u>SignUp Details</u></html>");
        lbl_userSignup.setFont(f2);
        combo_gender=new JComboBox(gender);
        lbl_gender=new JLabel("Your Gender");
        lbl_gender.setFont(f1);
        lbl_gender.setForeground(Color.gray);
       
        combo_gender=new JComboBox(gender);
        combo_gender.setFont(f1);
        combo_gender.setPreferredSize(new Dimension(120,25));
        
        lbl_age=new JLabel("Your age");
        lbl_age.setFont(f1);
        lbl_age.setForeground(Color.gray);
       
        
        lbl_name=new JLabel("User Name");
        lbl_name.setFont(f1);
        lbl_name.setForeground(Color.gray);
       
        lbl_pword=new JLabel("Password");
        lbl_pword.setFont(f1);
        lbl_pword.setForeground(Color.gray);
        
        
        pnl_formHeader.add(lbl_userSignup); 
        lbl_notice=new JLabel(" ");
        
        lbl_confrim_pword=new JLabel("Confirm Password");
        lbl_confrim_pword.setFont(f1);
        lbl_confrim_pword.setForeground(Color.gray);
        
            
        pnl_signUpForm.add(lbl_userSignup,gbc1); 
        gbc1.gridy++;
        gbc1.insets=new Insets(2,5,2,5);
        pnl_signUpForm.add(lbl_name,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(txt_uName,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(lbl_age,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(txt_age,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(lbl_gender,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(combo_gender,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(lbl_pword,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(pswd_1,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(lbl_confrim_pword,gbc1);
        gbc1.gridy++;
        pnl_signUpForm.add(pswd_2,gbc1);
        gbc1.gridy++;
        gbc1.gridwidth=2;
        pnl_signUpForm.add(lbl_notice,gbc1);
        gbc1.gridy++;
        gbc1.insets=new Insets(15,0,0,0);
        pnl_signUpForm.add(btn_signUp,gbc1);
        
        pnl_SignUp=new JPanel(new BorderLayout());
        pnl_SignUp.setPreferredSize(new Dimension(300,500));
        pnl_SignUp.setBackground(Color.white);
      // pnl_SignUp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
       pnl_SignUp.add(pnl_formHeader,BorderLayout.NORTH);
        pnl_SignUp.add(pnl_signUpForm,BorderLayout.SOUTH);
   

     }
    

    @Override
    public void addTo(UIView object) {
    }

    @Override
    public void removeFrom(UIView object) {
       
    }

    @Override
    public JPanel  getPanel(String panelName) {
        
        if(panelName.equalsIgnoreCase("login"))
            return pnl_form;
        else if(panelName.equalsIgnoreCase("signup"))
            return pnl_SignUp;
        else if(panelName.equalsIgnoreCase("dashboard"))
            return dashboardPanel;
        else if(panelName.equalsIgnoreCase("summaryPanel"))
            return summaryPanel;
         else if(panelName.equalsIgnoreCase("extraPnl_2"))
            return extraPnl_2;
        else if(panelName.equalsIgnoreCase("userInfo"))
            return userInfoPanel;
        else if(panelName.equalsIgnoreCase("editmeal"))
            return mealEditPanel;
        else if(panelName.equalsIgnoreCase("dailyrecord"))
            return dailyRecordPanel;
        else if(panelName.equalsIgnoreCase("editexercise"))
            return exerciseEditPanel;
        else if(panelName.equalsIgnoreCase("report"))
            return reportPanel;
        else if(panelName.equalsIgnoreCase("help"))
            return helpPanel;
        else
            return null;

    }

    @Override
    public void add(JPanel panel,String position) {
        
    }

    @Override
    public void remove(JPanel panel,String position) {
     }

   
    public JTextField getTb_userName() {
        return tb_userName;
    }

    public JPasswordField getP_password() {
        return p_password;
    }

    public JButton getBtn_login() {
        return btn_login;
    }
    @Override
    public JButton getButton(String name) {
          
        if(name.equalsIgnoreCase("btn_login"))
           return btn_login;
          else if(name.equalsIgnoreCase("btn_signUp"))
           return btn_signUp;
         else if(name.equalsIgnoreCase("btn_update"))
           return btn_update;
          else if(name.equalsIgnoreCase("btn_cancel"))
           return btn_cancel;
           else if(name.equalsIgnoreCase("btn_mEdit"))
           return btn_mEdit;
           else if(name.equalsIgnoreCase("btn_eEdit"))
           return btn_eEdit;
           else if(name.equalsIgnoreCase("btn_eRemove"))
           return btn_eRemove;
           else if(name.equalsIgnoreCase("btn_addDailyRecord"))
           return btn_addDailyRecord;
            else if(name.equalsIgnoreCase("btn_genReport"))
           return btn_genReport;
            else if(name.equalsIgnoreCase("btn_mRemove"))
           return btn_mRemove;
        else
            return null;
    }

    @Override
    public JTextField getTextBox(String name) {
   
        if(name.equalsIgnoreCase("tb_userName"))
            return tb_userName;
        else if (name.equalsIgnoreCase("txt_uName"))
            return txt_uName;
        else if (name.equalsIgnoreCase("username"))
            return tb_username;
        else if (name.equalsIgnoreCase("userage"))
            return tb_age;
        else if(name.equalsIgnoreCase("txt_age"))
            return txt_age;
        else if(name.equalsIgnoreCase("mealname"))
            return tb_mname;
        else if(name.equalsIgnoreCase("mcallorieValue"))
            return tb_mcalvalue;
        else if(name.equalsIgnoreCase("ecallorieValue"))
            return tb_ecalvalue;
        else if(name.equalsIgnoreCase("exercisename"))
            return tb_ename;
        else if(name.equalsIgnoreCase("morningWeight"))
            return tb_mWight;
        else if(name.equalsIgnoreCase("morningWaistSize"))
            return tb_mWaist;
        else if(name.equalsIgnoreCase("eveningWeight"))
             return tb_eWight;
        else if(name.equalsIgnoreCase("eveningWaistSize"))
            return tb_eWaist;
      
        else
            return null;
        
    }

    @Override
    public JPasswordField getPasswordField(String name) {
        if(name.equalsIgnoreCase("p_password"))
            return p_password;
        else if(name.equalsIgnoreCase("pswd_1"))
            return pswd_1;
        else if(name.equalsIgnoreCase("pswd_2"))
            return pswd_2;
        else
            return null;
    }

    @Override
    public JLabel getJLabel(String name) {

             if(name.equalsIgnoreCase("lbl_linkPart2"))
                return lbl_linkPart2;
             else if (name.equalsIgnoreCase("lbl_linkToMainMenu"))
                     return lbl_linkToMainMenu;
             else if (name.equalsIgnoreCase("dashbordLebel"))
                     return lebel_DashBoard;
             else if (name.equalsIgnoreCase("lebel_edit"))
                     return lebel_edit;
             else if (name.equalsIgnoreCase("lbl_Meal"))
                     return lbl_Meal;
             else if (name.equalsIgnoreCase("lbl_Workout"))
                     return lbl_Workout;
             else if (name.equalsIgnoreCase("lbl_tips"))
                     return lbl_tips;
             else if (name.equalsIgnoreCase("lbl_notice"))
                     return lbl_notice; 
             else if (name.equalsIgnoreCase("lebel_AddRecord"))
                     return lebel_AddRecord;
             else if (name.equalsIgnoreCase("lebel_EditExInfo"))
                     return lebel_EditExInfo; 
             else if (name.equalsIgnoreCase("lebel_EditMealInfo"))
                     return lebel_EditMealInfo;
             else if (name.equalsIgnoreCase("lebel_EditUserInfo"))
                     return lebel_EditUserInfo; 
             else if (name.equalsIgnoreCase("lebel_ViewReport"))
                     return lebel_ViewReport;
             else if (name.equalsIgnoreCase("lebel_Welcome"))
                     return lebel_Welcome;
             else if (name.equalsIgnoreCase("lbl_moreMeal"))
                     return lbl_moreMeal;
             else if (name.equalsIgnoreCase("lbl_moreExercise"))
                     return lbl_moreExercise;
             else if (name.equalsIgnoreCase("newMeal"))
                     return newMeal;
             else if (name.equalsIgnoreCase("newExercise"))
                     return newExercise;
             else if (name.equalsIgnoreCase("lebel_help"))
                     return lebel_help;
             else if (name.equalsIgnoreCase("lebel_Logout"))
                     return lebel_Logout;
            else if (name.equalsIgnoreCase("lebel_addDailyRecord"))
                     return lebel_addDailyRecord; 
            else if (name.equalsIgnoreCase("lebelIndex"))
                     return lbl_index;
            else if (name.equalsIgnoreCase("lebel_startDate"))
                     return lebel_startDate;
            else if (name.equalsIgnoreCase("lebel_endDate"))
                     return lebel_endDate;
             else if (name.equalsIgnoreCase("lebel_error"))
                     return lebel_error;
            else
                    return null;            
    }

    @Override
    public JComboBox getJComboBox(String name) {
       
        if(name.equalsIgnoreCase("combo_gender"))
            return combo_gender;
        else if(name.equalsIgnoreCase("usergender"))
            return cb_gender;
        else if(name.equalsIgnoreCase("meallist"))
            return cb_meal;
        else if(name.equalsIgnoreCase("exerciselist"))
            return cb_exercise;
         else if(name.equalsIgnoreCase("month1"))
            return m1;
          else if(name.equalsIgnoreCase("month2"))
            return m2;
           else if(name.equalsIgnoreCase("day1"))
            return d1;
            else if(name.equalsIgnoreCase("day2"))
            return d2;
             else if(name.equalsIgnoreCase("year1"))
            return y1;
              else if(name.equalsIgnoreCase("year2"))
            return y2;
        else
            return null;
    }

    @Override
    public DefaultListModel getDefaultListModel(String listName) {
        if(listName.equalsIgnoreCase("mealList"))
            return mlListModel;
        else if(listName.equalsIgnoreCase("exerciseList"))
            return eListModel;
        else
            return null;
    }

    @Override
    public JList getJList() {
return null;       
    }

    @Override
    public JFrame getJFrame(String frameName) {
       return null;
    }

    @Override
    public JSlider getJSlider(String name) {
        return activityLevelSlider;
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





















