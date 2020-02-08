package Views;
import Controllers.UIViewController;
import Models.DAOModel;
import Models.UserModel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DefaultView {   
    private final UIViewController uiController;
    DefaultView(){    
        uiController=new UIViewController(DAOModel.getDAOModel(),new UserModel("user"),new UIView());
    }
    public static void main(String[] args) {
             try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel()); 
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UIView.class.getName()).log(Level.SEVERE, null, ex);
        }
               if(DAOModel.getDAOModel().setUpConnection("adidatabase", "root", "")){
                new DefaultView();                
                }
    }
} 








