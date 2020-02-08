package Views;
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

public interface JView {
    void addTo(UIView u);
    void removeFrom(UIView u);      public JPanel  getPanel(String panelName);
    void add(JPanel panel,String position);     void remove(JPanel panel,String position); 
   JButton getButton(String name);
   JTextField getTextBox(String name);
   JPasswordField getPasswordField(String name);
   JLabel getJLabel(String name);
   JComboBox getJComboBox(String name);
   DefaultListModel getDefaultListModel(String listName);       DefaultTableModel getDefaultTableModel(String name);
   JScrollPane getScollPane(String name);       JList getJList();
   JFrame getJFrame(String frameName);      JSlider getJSlider(String name);
}
