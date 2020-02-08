package Models;
import java.sql.ResultSet;
import java.util.Date;

public class UserModel{
    private String username,gender,activityLabelIndex,password;
    private Date startDate,endDate;
    public static int userID;
    private Integer age;
    private float morningWeight,eveningWeight,averageWeight;
    private float morningWaistSize,eveningWaistSize,averageWaistSize;
    private float calRequired;
    private float mealAmount,workoutDuration,cal_Intake,cal_Burned;
    private final String tableName;
    private DAOModel model;
       
   public UserModel(String tableName){
   this.tableName=tableName;
   }
   
   public void setDAOModel(DAOModel model){
   this.model=model;
   }
   
   public ResultSet getDateAndWeightData(Date from, Date to)
   {
   String query="SELECT DATE_FORMAT(date,'%b %d'),avg_weight FROM dailyrecord WHERE userID="+
           userID+" AND date BETWEEN '"+from.toString()+"' AND '"+to.toString()+"'";
   return model.runSelectQuery(query);
   }
   
   public ResultSet getAverageWeight(Date to,Date from){
   String query="SELECT avg(avg_weight),sum(netWeight) FROM dailyrecord WHERE userID="+userID+
           " and dailyrecord.date BETWEEN '"+to.toString()+"' AND '"+from.toString()+"'";
   return model.runSelectQuery(query);
   }
   
   public ResultSet getCallorieConsumptionData(Date to,Date from){
   String query="SELECT date,calorieTaken,calorieBurned,netCalorie FROM dailyrecord WHERE "
           + "userID="+userID+" AND date BETWEEN '"+to.toString()+"' AND '"+from.toString()+"' ORDER BY date ASC";
   return model.runSelectQuery(query);
   }
   
   
   public ResultSet getInitialWeight(Date sdate){
   String query="SELECT mWeight,eWeight FROM dailyrecord where dailyrecord.date='"+sdate.toString()+"' AND userID="+userID;
   return model.runSelectQuery(query);
   }
   
    public ResultSet getFinalWeight(Date edate){
   String query="SELECT mWeight,eWeight FROM dailyrecord where dailyrecord.date='"+edate.toString()+"' AND userID="+userID;
   return model.runSelectQuery(query);
   }
    
   public ResultSet getGraphDataParameters(Date sdate,Date edate){
   String query="SELECT max(mWeight),max(eWeight) from dailyrecord "
           + "where userID="+userID+" AND dailyrecord.date BETWEEN '"+sdate.toString()+"' AND '"+edate.toString()+"'";
           return model.runSelectQuery(query);
   }
   
   public ResultSet getNumberOfDailyRecords(Date sdate,Date edate){
       String query="SELECT COUNT(*) FROM dailyrecord WHERE userID="+userID+ " AND dailyrecord.date BETWEEN "
               + "'"+sdate.toString()+"' AND '"+edate.toString()+"'";
       return model.runSelectQuery(query);
   }
   
   public ResultSet getInitialtDate(){
       String query="select min(date) from dailyrecord where userID="+getUserID();
       return model.runSelectQuery(query);
   }
   
   public ResultSet getLatestDate(){
    String query="select max(date) from dailyrecord where userID="+getUserID()+" AND dailyrecord.date!=CURRENT_DATE";
       return model.runSelectQuery(query);
   }
   
   public  ResultSet getDailyRecord(){
     
       String query="select * from dailyrecord where userID="+getUserID()+" and date=current_date";
       return model.runSelectQuery(query);
   }
   
   public boolean updateDailyRecord(float mW,float mWS,float eW,float eWS,String activityLevel){
       String query="update dailyrecord set mWeight= "+mW+ ", mWaistSize ="+mWS+ ", eWeight="+eW+
               ", eWaistSize ="+eWS+ ", activityLevel='"+activityLevel+"' where userID="+userID+" and date=current_date ";
      return  model.runUpdateQuery(query);
    }
   
   public boolean addDailyRecord(float mW,float mWS,float eW,float eWS,String activityLevel){
   
       String query="insert into dailyrecord(date,userID,mWeight,mWaistSize,eWeight,eWaistSize,activityLevel)"
               + "values(current_date,"+userID+","+mW+","+mWS+","+eW+","+eWS+",'"+activityLevel+"')";
      return model.runInsertQuery(query);
   
   }
   
   public ResultSet loginUser(String userName,String password){
       
       String query="select * from "+tableName+" where userName=\'"+userName+"\' and password=MD5(\'"+password+"\')";
       return model.runSelectQuery(query);
   }
   
    public boolean addUser(String userName,int age,String password,String gender){
        String query="insert into "+tableName+"(userName,age,password,gender) values(\'"+userName+"\',"+age+",MD5(\'"+password+"\'),\'"+gender+"\')";
        return model.runInsertQuery(query);
    }
   
     public ResultSet loadRecentUserData(){
        String query="SELECT * FROM "+tableName+" WHERE userID =(SELECT max(userID) FROM "+tableName+")";
        return model.runSelectQuery(query);   
    }
    
    public void deleteUser(int id){
        String query="delete from "+tableName+" where userID="+id;
        model.runDeleteQuery(query);
    }
    
    public boolean updateUserdata(String userName,int age, String gender,int id){
        String query="update "+tableName+" set userName=\'"+userName+"\' ,age="+age+", gender=\'"+gender+"\' where userID=\'"+id+"\'";
        return model.runUpdateQuery(query);
    }
    
    public String getUsername() {
        return username;
    }

   
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getActivityLabelIndex() {
        return activityLabelIndex;
    }
     public void setActivityLabelIndex(String activityLabelIndex) {
        this.activityLabelIndex = activityLabelIndex;
    }

    public float getMorningWeight() {
        return morningWeight;
    }

    public void setMorningWeight(float morningWeight) {
        this.morningWeight = morningWeight;
    }

    public float getEveningWeight() {
        return eveningWeight;
    }

    public void setEveningWeight(float eveningWeight) {
        this.eveningWeight = eveningWeight;
    }

    public float getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(float averageWeight) {
        this.averageWeight = averageWeight;
    }

    public float getMorningWaistSize() {
        return morningWaistSize;
    }

    public void setMorningWaistSize(float morningWaistSize) {
        this.morningWaistSize = morningWaistSize;
    }

    public float getEveningWaistSize() {
        return eveningWaistSize;
    }

    public void setEveningWaistSize(float eveningWaistSize) {
        this.eveningWaistSize = eveningWaistSize;
    }

    public float getAverageWaistSize() {
        return averageWaistSize;
    }

    public void setAverageWaistSize(float averageWaistSize) {
        this.averageWaistSize = averageWaistSize;
    }

    public float getCalRequired() {
        return calRequired;
    }

    public void setCalRequired(float calRequired) {
        this.calRequired = calRequired;
    }

    public float getMealAmount() {
        return mealAmount;
    }

    public void setMealAmount(float mealAmount) {
        this.mealAmount = mealAmount;
    }

    public float getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutDuration(float workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        UserModel.userID = userID;
    }

    public float getCal_Intake() {
        return cal_Intake;
    }

    public void setCal_Intake(float cal_Intake) {
        this.cal_Intake = cal_Intake;
    }

    public float getCal_Burned() {
        return cal_Burned;
    }

    public void setCal_Burned(float cal_Burned) {
        this.cal_Burned = cal_Burned;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
