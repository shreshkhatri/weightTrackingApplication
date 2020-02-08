package Views;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public final class UIView extends JFrame{
    
    public UIView(){
        setFrame();
    }
   
    public void setFrame(){
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Weight Tracker System");
        setSize(1020,720);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public void refreshGUI(){
        try{
        revalidate();
        repaint();
         }
        catch(Exception e)
        { 
        }
    }
}