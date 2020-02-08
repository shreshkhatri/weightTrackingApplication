package Views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
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

public class HeaderView implements JView {

        JPanel pannelTop;
        JLabel lbl_Banner;
         URL url_bannerImage;
    
    HeaderView(){
        
        url_bannerImage=getClass().getResource("../graphics/topBanner.png");
        lbl_Banner=new JLabel("<HTML>Welcome to Weight Tracker System !"
                + "<BR>Maintain log and maintain your body</HTML>"/*new ImageIcon(url_bannerImage),0 */);
        lbl_Banner.setIconTextGap(-1000);
        lbl_Banner.setFont(new Font("Verdana",Font.BOLD,20));
        lbl_Banner.setForeground(Color.WHITE);
        
        pannelTop=new JPanel(new BorderLayout());
        pannelTop.setPreferredSize(new Dimension(1020,100));
        pannelTop.setBackground(new Color(39,133,238));
        pannelTop.add(lbl_Banner,BorderLayout.CENTER);
        pannelTop.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
    }
    
    @Override
    public void addTo(UIView object) {
        object.add(pannelTop,BorderLayout.NORTH);
      
    }

    @Override
    public void removeFrom(UIView object) {
      object.remove(pannelTop);
    }

        @Override
    public JPanel getPanel(String panelName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(JPanel panel,String position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(JPanel panel,String position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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













