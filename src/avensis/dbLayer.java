package avensis;

import java.sql.*;
import java.util.Random;

public class dbLayer 
{
    
      Connection con;
        Statement st;
        ResultSet rs;
        String sql;
        static int arr=new Random().nextInt(4);
        
    dbLayer()
    {
        connection();
    }

    public void connection()
    {
      
        try
        {
        String driver="sun.jdbc.odbc.JdbcOdbcDriver";
        Class.forName(driver);
        
        String db="jdbc:odbc:dbbean";
        con=DriverManager.getConnection(db);
        st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        if(arr==0){
            sql="select * from ques order by ques_id";
        }
        else if(arr==1){
            sql="select * from ques order by c1";
        }
        else if(arr==2){
            sql="select * from ques order by c2";
        }
        else if(arr==3){
            sql="select * from ques order by c3";
        }
        
        rs=st.executeQuery(sql);
        
        /*
        while(rs.next())
        {
            String q=rs.getString("question");
            String o1=rs.getString("opt_1");
            String o2=rs.getString("opt_2");
            String o3=rs.getString("opt_3");
            String o4=rs.getString("opt_4");
            String ans=rs.getString("ans");
        
            System.out.println("question->\n"+q+"\n\n---");
            System.out.println("option 1: "+o1);
            System.out.println("option 2: "+o2);
            System.out.println("option 3: "+o3);
            System.out.println("option 4: "+o4);
            System.out.println("\n\nanswer is: "+ans);
            System.out.println("---------------------");     
        }
        */
        }catch(Exception ex)
        {
            System.out.println("generated exception: "+ex.toString());
        }
        
    }
    
    public static void main(String arg[])
    {
        new dbLayer();
    }
    
}
