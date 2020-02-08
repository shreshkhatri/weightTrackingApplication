package Models;
import java.sql.ResultSet;

public class ExerciseModel  {
    
    private String name;
    private float cal_per_minute;
    private final String tableName;
    private  DAOModel model;
    
    public ExerciseModel(String tableName){
    this.tableName=tableName;
    }
    
    public void setDAOModel(DAOModel model){
   this.model=model;
   }   
    
    public boolean addExercise(String exName,float calValue){
        String query="insert into "+tableName+" (eName,cal_per_min) values ('"+exName+"',"+calValue+")";
       return model.runInsertQuery(query);
    }
    public boolean  deleteExercise(String exName){
       String query="delete from "+tableName+" where eName='"+exName+"'";
       return model.runDeleteQuery(query);
    }
    
    public ResultSet exerciseLookUp(String name){
    String query="select * from "+tableName+" where eName like '"+name+"%' order by eName asc";
    return model.runSelectQuery(query);
    }
    
     public ResultSet checkRecordForToday(String tablename,int userID){
    
        String query="select * from "+tablename+" where date=current_date and userID="+userID;
        return model.runSelectQuery(query);
    }
     
     public ResultSet checkExercise(String name){
       String query="select * from "+tableName+" where eName = '"+name+"'";
        return model.runSelectQuery(query);
     }
     
     public  ResultSet listExercise(){
        String query="select * from "+tableName+" order by eName asc";
        return model.runSelectQuery(query);
    }
    
     public boolean  addMoreExercise(float duration,float totalCalorie,int userid){
        
         String query="insert into dailyrecord(date,userID,workout,calorieBurned) values (CURRENT_DATE,"+userid+","+duration+","+totalCalorie+")";
        return model.runInsertQuery(query);
    }
     
    public boolean updateExercise(String previousName,String exName,float calValue ){
        String query="update "+tableName+" set eName='"+exName+"',cal_per_min="+calValue+" where eName=\'"+previousName+"\'";
        return model.runUpdateQuery(query);
    }
    
    public void updateDailyExercise(float workoutDuration,float calorie){
        String query="update dailyrecord set workout=workout + "+workoutDuration+", calorieBurned=calorieBurned + "+calorie+""
                + " where userID="+UserModel.userID+" and date=current_date";
        model.runUpdateQuery(query);
    }
       public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCal_per_minute() {
        return cal_per_minute;
    }
    
    public void setCal_per_minute(float cal_per_minute) {
        this.cal_per_minute = cal_per_minute;
    }
}

