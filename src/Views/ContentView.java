
package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ContentView implements JView {

    JPanel contentPanel,leftPanel,rightPanel,rightBottomPanel,sliderPanel;
    URL  url_userIcon,url_discImage,url_discImageShadow;
    URL[] url_slideImage;
    JLabel lbl_Banner;
    JLabel[] lbl_discHolder;
    String[] slideText;
    JButton btnActionTrigger;
    int counter=0;
    Timer timer;
    
    ActionListener slideChanger=new ActionListener() {
     
        @Override
     public void actionPerformed(ActionEvent ae) {
         sliderPanel.remove(lbl_Banner);
         
         counter++;
         updateSlide(counter);   
         
         if(counter==3)
             counter=-1;
     }
 };
    
    public ContentView(){
        
        contentPanel=new JPanel(new BorderLayout());
        url_discImage=getClass().getResource("/graphics/slides/sphere.png");
        url_discImageShadow=getClass().getResource("/graphics/slides/sphereShadow.png");
        contentPanel.setPreferredSize(new Dimension(1020,570));
        setLeftPanel();
        setRightPanel();
        startSlideShow();
        contentPanel.add(leftPanel,BorderLayout.WEST);
        contentPanel.add(rightPanel,BorderLayout.EAST);
        
    }
    
    void updateSlide(int slidenumber){
        
        if(slidenumber!=0){
            lbl_discHolder[slidenumber-1].setIcon(new ImageIcon(url_discImage));}
        else if(slidenumber==0){
                lbl_discHolder[3].setIcon(new ImageIcon(url_discImage));}
  
        lbl_Banner=new JLabel(slideText[slidenumber],new ImageIcon(url_slideImage[slidenumber]),0);
        lbl_discHolder[slidenumber].setIcon(new ImageIcon(url_discImageShadow));
        lbl_Banner.setIconTextGap(-580);
        lbl_Banner.setForeground(new Color(96,97,99));
        lbl_Banner.setFont(new Font("Verdana",Font.BOLD,15));
        sliderPanel.add(lbl_Banner,BorderLayout.CENTER);
        sliderPanel.revalidate();
        sliderPanel.repaint();
}
        
    public void setLeftPanel(){
    
        leftPanel=new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300,500));
        leftPanel.setBackground(Color.white);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        //leftPanel.add(pnl_formHeader,BorderLayout.NORTH);
        //leftPanel.add(pnl_signUpForm,BorderLayout.CENTER);
        }
    
    public void setRightPanel(){
       
        rightPanel=new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(720,500));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        rightPanel.setBackground(Color.white);    
    }
    
    public void startSlideShow(){        
        sliderPanel=new JPanel(new BorderLayout());
        sliderPanel.setBackground(Color.white);
        lbl_discHolder=new JLabel[4];

        btnActionTrigger=new JButton();
        btnActionTrigger.addActionListener(slideChanger);
        
        url_slideImage=new URL[4];
   
        slideText=new String[4];
        slideText[0]="<HTML>Know your diet<BR><BR> \u2022 Calories impact your health<BR><BR>\u2022 Track your callorie intake</HTML>";
        slideText[1]="<HTML>Measure your workout<BR><BR> \u2022 Know callorie burn<BR><BR>\u2022 know effectiveness of your workout </HTML>";
        slideText[2]="<HTML>Analyse calorie vs workout<BR><BR> \u2022 Know how much you ate<BR><BR>\u2022 know how much callorie"
                + " is used<BR><BR>\u2022 know your callorie-weight trend </HTML>";
        slideText[3]="<HTML>Log your Waist size<BR><BR> \u2022 keep record of your waist<BR><BR>\u2022 compare it against callorie"
                + " intake<BR><BR>\u2022 compare it against your weightloss plan </HTML>";
        url_slideImage[0]=getClass().getResource("/graphics/slides/diet.png");
        url_slideImage[1]=getClass().getResource("/graphics/slides/exercise.png");
        url_slideImage[2]=getClass().getResource("/graphics/slides/graph.png");
        url_slideImage[3]=getClass().getResource("/graphics/slides/waist.png");
    
        
        rightBottomPanel=new JPanel();
        rightBottomPanel.setBackground(Color.white);
        rightBottomPanel.setComponentOrientation(ComponentOrientation.UNKNOWN);
        for(int i=0;i<4;i++){
            lbl_discHolder[i]=new JLabel(new ImageIcon(url_discImage));
            rightBottomPanel.add(lbl_discHolder[i],BorderLayout.SOUTH);
        }
        
        updateSlide(counter);
        sliderPanel.add(rightBottomPanel,BorderLayout.SOUTH);
        timer=new Timer(3000,slideChanger);
        timer.start();
    }
 
    
    @Override
    public void addTo(UIView object) {
       
        object.add(contentPanel,BorderLayout.CENTER);
    }

    @Override
    public void removeFrom(UIView object) {
        object.remove(contentPanel);
    }

    @Override
    public JPanel getPanel(String panelName) {
        if(panelName.equalsIgnoreCase("leftpanel"))
            return leftPanel;
        else if(panelName.equalsIgnoreCase("rightpanel"))
            return rightPanel; 
        else if(panelName.equalsIgnoreCase("sliderPanel"))
        {
            timer.start();
            return sliderPanel;
            
        }else
            return null;
    }

    @Override
    public void add(JPanel panel,String position) {
        if(position.equalsIgnoreCase("left")){
        leftPanel.add(panel);
        }
        else if(position.equalsIgnoreCase("right")){
        rightPanel.add(panel);
        }
    }

    @Override
    public void remove(JPanel panel,String position) {
        if (panel.equals(sliderPanel)&&position.equalsIgnoreCase("right"))
        {    
            timer.stop();
            rightPanel.remove(sliderPanel);
        }
        else
            leftPanel.remove(panel);
    }

    @Override
    public JButton getButton(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JTextField getTextBox(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPasswordField getPasswordField(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JLabel getJLabel(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComboBox getJComboBox(String name) {
        throw
                new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel getDefaultListModel(String listName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JList getJList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JFrame getJFrame(String frameName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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














