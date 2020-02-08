package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DAOModel {
    private  Connection connectionString;
    private  Statement statement;
    private  ResultSet resultSet;
    private  static DAOModel model;
    
   private  DAOModel(){}
    
   public  boolean setUpConnection(String databaseName,String databaseUser,String databasePassword){
        try{
        connectionString=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,databaseUser,databasePassword);
        statement=connectionString.createStatement();
        return true;
       }
     catch(Exception e){
         JOptionPane.showMessageDialog(null, "Please, make sure that MySQL server is active\n"
                 + "before you run the application", "MySql Server down",JOptionPane.ERROR_MESSAGE);
        System.exit(0);
         return false;
     }   
    }
   
   public static DAOModel getDAOModel(){
   if(model==null)
       return model=new DAOModel();
     else return model;
   }
    
    public boolean runInsertQuery(String queryName){
     
        try{
            return statement.executeUpdate(queryName)!=0;
         
        }catch(Exception e){
           
         return false;
        }
    
        
    }
    
    public ResultSet runSelectQuery(String queryName){
    try{
         resultSet=statement.executeQuery(queryName);
         return resultSet;
         
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(),"Retrieving process failed",JOptionPane.ERROR_MESSAGE);
         return null;
        }
    
    }
    
    public boolean runUpdateQuery(String queryName){
        try{
         statement.executeUpdate(queryName);
         return true;
         
        }catch(Exception e){
         JOptionPane.showMessageDialog(null,e.getMessage(), "Update failed" ,JOptionPane.ERROR_MESSAGE);
         return false;
        }
    }
    
    public boolean runDeleteQuery(String queryName){
    try{
         statement.executeUpdate(queryName);
         return true;
         
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "Deletion failed",e.getMessage() ,JOptionPane.ERROR_MESSAGE);
         return false;
        }
    }
    
}

