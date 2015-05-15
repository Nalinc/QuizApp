package avensis;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class FinalScore 
{
    static Connection conn;
     static Statement stt;
     static ResultSet rss;
    static int scr=0;

    public static void evaluateScore()
    {scr=0;
        for(int i=0;i<30;i++)
        {
     
            //JOptionPane.showMessageDialog(null,"loop:"+(i+1)+"\n"+QP.userChoice[i]+"and actual is "+QP.actualAns[i]);
                   
            if(QP.userChoice[i].equals(QP.actualAns[i])&&QP.userChoice[i]!="")
            {
                scr=scr+4;
            }else if(QP.userChoice[i]!="")
            {
                scr=scr-1;
            }
                
        }
        
        Login.setConnection();
        Login.insertDetails();
        
        JOptionPane.showMessageDialog(null, Login.jTextField1.getText()+",Your score is: "+scr);
        System.exit(0);
    }
    
}
