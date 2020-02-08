package Views;

import java.sql.ResultSet;

public class ViewFactory {

    public static JView createView(String viewName,ResultSet rs){
    if(viewName.equalsIgnoreCase("user"))
        return new UserView();
    else if(viewName.equalsIgnoreCase("content"))
        return new ContentView();
    else if(viewName.equalsIgnoreCase("footer"))
        return new FooterView();
    else if(viewName.equalsIgnoreCase("header"))
        return new HeaderView();
     else if(viewName.equalsIgnoreCase("header"))
        return new ReportView();
      else if(viewName.equalsIgnoreCase("graph")&&rs!=null)
        return new GraphView(rs);
      else if(viewName.equalsIgnoreCase("report"))
        return new ReportView();
    else
        return null;
    }
    
}
