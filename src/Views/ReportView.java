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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class ReportView implements JView{
    private final Font f1=new Font("Verdana",Font.BOLD,14);
    private final Font f2=new Font("Verdana",Font.PLAIN,11);
    private JPanel reportPanel,briefDataPanel ,tableDataPanel,graphPanel;
    private GridBagConstraints gb1,gb2,gb3;
    private JScrollPane tablePane,graphPane;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JLabel lebel_Duration,lebel_header1,lebel_initialWt,lebel_finalWt,lebel_result,lebel_avgWt,lebel_header2;
    
    ReportView(){
        
        loadReport();
    }

    void loadReport(){

        reportPanel=new JPanel(new BorderLayout());
        reportPanel.setPreferredSize(new Dimension(700,480));
        reportPanel.setBackground(Color.white);
        reportPanel.setBorder(BorderFactory.createLineBorder(Color.gray));        
        //header--------------
        lebel_header1=new JLabel("<html><u>Data Summary</u></html>");
        lebel_header1.setFont(f1);
        lebel_initialWt=new JLabel();
        lebel_initialWt.setFont(f2);
        lebel_finalWt=new JLabel();
        lebel_finalWt.setFont(f2);
        lebel_avgWt=new JLabel();
        lebel_avgWt.setFont(f2);
        lebel_result=new JLabel();
        lebel_result.setFont(f2);
        lebel_Duration=new JLabel();
        lebel_Duration.setFont(f2);
        
        //brief data panel---------------------
        briefDataPanel=new JPanel(new GridBagLayout());
        briefDataPanel.setBackground(Color.white);
        gb1=new GridBagConstraints();
        gb1.gridx=gb1.gridy=0;
        briefDataPanel.add(lebel_header1,gb1);
        gb1.gridy++;
         briefDataPanel.add(lebel_Duration,gb1);
        gb1.gridy++;
        gb1.insets=new Insets(10,0,0,0);
        briefDataPanel.add(lebel_initialWt,gb1);
        gb1.gridy++;
        briefDataPanel.add(lebel_finalWt,gb1);
        gb1.gridy++;
        briefDataPanel.add(lebel_avgWt,gb1);
        gb1.gridy++;
        briefDataPanel.add(lebel_result,gb1);
        briefDataPanel.setPreferredSize(new Dimension(210,200));
       
//callorie consumption report section
        tableModel=new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Callorie Intake");
        tableModel.addColumn("Callorie Burn");
        tableModel.addColumn("Net Callorie");
      
        dataTable=new JTable(tableModel);
        
        dataTable.setFillsViewportHeight(true);
        dataTable.getTableHeader().setForeground(Color.black);
        dataTable.getTableHeader().setEnabled(false);
        dataTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 11));
        dataTable.setFont(f2);
        dataTable.setEnabled(false);

        tablePane=new JScrollPane(dataTable);
        tablePane.setPreferredSize(new Dimension(485,172));
        tableDataPanel=new JPanel(new GridBagLayout());
        gb2=new GridBagConstraints();
        gb2.gridx=gb2.gridy=0;
        
        tableDataPanel.setBackground(Color.white);
        lebel_header2=new JLabel("Calorie Consumption Report");
        lebel_header2.setFont(f1);
        tableDataPanel.add(lebel_header2,gb2);
        gb2.gridy++;
        tableDataPanel.add(tablePane,gb2);
        
        //graph plotting section
        graphPanel=new JPanel(new GridBagLayout());
        graphPanel.setBackground(Color.white);
        gb3=new GridBagConstraints();
        gb3.gridx=gb3.gridy=0;
        
       
        graphPane=new JScrollPane();
        graphPane.setPreferredSize(new Dimension(695,270));
    
        graphPanel.add(graphPane,gb3);
        
        reportPanel.add(briefDataPanel,BorderLayout.WEST);
        reportPanel.add(tableDataPanel,BorderLayout.EAST);
        reportPanel.add(graphPanel,BorderLayout.SOUTH);           
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
         if(panelName.equalsIgnoreCase("reportpanel"))
                return reportPanel;
        else if(panelName.equalsIgnoreCase("datapanel"))
            return tableDataPanel;
        else if(panelName.equalsIgnoreCase("graphPanel"))
            return graphPanel;
        else return null;
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
    
          if(name.equalsIgnoreCase("initialWeight"))
              return lebel_initialWt;
          else if(name.equalsIgnoreCase("finalWeight"))
              return lebel_finalWt;
          else if(name.equalsIgnoreCase("averageWeight"))
              return lebel_avgWt;
          else if(name.equalsIgnoreCase("result"))
              return lebel_result;
          else if(name.equalsIgnoreCase("duration"))
              return lebel_Duration;
          else
                return null;
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
        if(name.equalsIgnoreCase("tableModel"))
            return tableModel;
        else
            return null;
    }
    @Override
    public JScrollPane getScollPane(String name) {
        if(name.equalsIgnoreCase("graphpane"))
            return graphPane;
        else if(name.equalsIgnoreCase("tablepane"))
            return tablePane;
        else
            return null;
    }
}
