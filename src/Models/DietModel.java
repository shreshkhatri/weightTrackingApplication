package Models;

import java.sql.ResultSet;

public class DietModel{
    private String name;
    private float cal_per_gram;
    private final String tableName;
    private  DAOModel model;
    
    public DietModel(String tableName){
    this.tableName=tableName;
    }
    
    public void setDAOModel(DAOModel model){
   this.model=model;
   }
   
    public ResultSet dietLookUp(String name){
    String query="select * from "+tableName+" where dietName like '"+name+"%' order by dietName asc";
    return model.runSelectQuery(query);
    }
    
      public ResultSet checkDiet(String name){
        String query="select * from "+tableName+" where dietName = '"+name+"'";
        return model.runSelectQuery(query);
    }
    
    public boolean  addDiet(String dietName,float calValue){
        String query="insert into "+tableName+"(dietName,cal_per_gram) values (\'"+dietName+"\',"+calValue+")";
        return model.runInsertQuery(query);
        
    }
    
    public ResultSet checkRecordForToday(String tablename,int userID){
    
        String query="select * from "+tablename+" where date=current_date and userID="+userID;
        return model.runSelectQuery(query);
  
    }
    
    public ResultSet listDiet(){
        String query="select * from "+tableName+" order by dietName asc";
        return model.runSelectQuery(query);
    }
    public boolean  addMoreDiet(float ammount,float totalCalorie,int userid){

        String query="insert into dailyrecord(date,userID,mealAmount,calorieTaken) values (CURRENT_DATE,"+userid+","+ammount+","+totalCalorie+")";
        return model.runInsertQuery(query);
        
    }
    
    public boolean deleteDiet(String dietName){
       String query="delete from "+tableName+" where dietName=\'"+dietName+"\'";
       return model.runDeleteQuery(query);
    }
    
    public boolean updateDiet(String previousName,String newName,float f){
        String query="update "+tableName+" set dietName=\'"+newName+"\', cal_per_gram="+f+" where dietName=\'"+previousName+"\'";
        return  model.runUpdateQuery(query);
    }
    
     public void updateDailyMeal(float dietAmmount,float calorie){
        String query="update dailyrecord set mealAmount=mealAmount + "+dietAmmount+", calorieTaken=calorieTaken +"
                +calorie+" where userID="+UserModel.userID+" and date=current_date";
        model.runUpdateQuery(query);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCal_per_gram() {
        return cal_per_gram;
    }
    
    public void setCal_per_gram(float cal_per_gram) {
        this.cal_per_gram = cal_per_gram;
    }
 }
