package avensis;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pratik
 */
public class QP extends javax.swing.JFrame implements ActionListener  {
     ImagePanel panel = new ImagePanel(new ImageIcon(getClass().getResource("bg3.jpg")).getImage());
static JButton b[]=new JButton[30];
static        String userChoice[]=new String[30];
static        String actualAns[]=new String[30];
        String question;
        String o1,o2,o3,o4,ans;
        int ques_id;
        Integer mm=new Integer(14);
          Integer ss=new Integer(60);

          String mmss="";
        dbLayer d;
         
    /**
     * Creates new form QP
     */
      
      public void actionPerformed1(ActionEvent e)
    {
        if(e.getSource()==prevQ)
        {
            setprevValues();
        }
        else if(e.getSource()==nextQ)
        {
           setnextValues();
  //         Board.buttonGroup.clearSelection();
        }
        else if(e.getSource()==clearch)
        {
            buttonGroup1.clearSelection();
            userChoice[ques_id-1]="";
//           JOptionPane.showMessageDialog(null,Board.userChoice[brd.ques_id-1]);
            b[ques_id-1].setBackground(Color.GRAY);
        }else if(e.getSource()==flag)
        {
            b[ques_id-1].setBackground(new Color(200,0,0));
        }else if(e.getSource()==unflag)
         {
            if(jRadioButton1.isSelected()||jRadioButton2.isSelected()||jRadioButton3.isSelected()||jRadioButton4.isSelected())
            { b[ques_id-1].setBackground(Color.GREEN);
            }else
            {
                b[ques_id-1].setBackground(Color.GRAY);
            }
            
         }
        else if(e.getSource()==submit)
         {//JOptionPane.showMessageDialog(null,"submit");
             setVisible(false);
             FinalScore.scr=0;
            FinalScore.evaluateScore();
         
         }
        else 
         {
             for(int i=0;i<30;i++)
             {
                if(e.getSource()==b[i])
                {//JOptionPane.showMessageDialog(null,"b015");
                    changeStatebyone(i);  
                }
             }

//             for(int i=15;i<30;i++)
//             {
//                if(e.getSource()==b[i])
//                {//JOptionPane.showMessageDialog(null,"b1530");
//                  changeStatebytwo(i);
//                }
//             }
         }
    }  
        
    public QP() {
        initComponents();
        submit.setBounds(650,650,100,25);
        setConnection();
        setnextValues();
        add(jPanel1);
        
        for(int i=0;i<30;i++)
        {userChoice[i]="";
        actualAns[i]="";
        }
         ActionListener timerListener = new ActionListener()  
        {  
            public void actionPerformed(ActionEvent e)  
            {  
            if(ss>9&&mm>=9)
            {
               mmss=mm.toString()+":"+ss.toString();
            }else if(ss>9)
            {
                mmss="0"+mm.toString()+":"+ss.toString();
            }else if(mm<=9)
            {
                mmss="0"+mm.toString()+":"+"0"+ss.toString();
            }else
            {
                mmss=mm.toString()+":"+"0"+ss.toString();
            }
            
  if(ss==00&&mm==00)
            {
                setVisible(false);
                FinalScore.evaluateScore();
                
            }          
              if(ss==00)
             {
                 mm--;
                 ss=60;
             }
                String time = mmss;
                //Font f=new Font()
               timeLabel.setText(time);  
               ss--;
            }  
        };  
        Timer timer = new Timer(1000, timerListener);  
        // to make sure it doesn't wait one second at the start  
        timer.setInitialDelay(0);  
        timer.start();
      for(int i=0;i<15;i++)
       {
       b[i]=new JButton("Question "+(i+1));
       b[i].setBounds(0,(25*i)+1, 110, 25);
       b[i].setBackground(Color.GRAY);
       b[i].setForeground(Color.BLACK);
       b[i].setEnabled(true);
       b[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed1(evt);
            }
        });
       jPanel1.add(b[i]);
       }
      
       for(int i=15;i<30;i++)
       {
       b[i]=new JButton("Question "+(i+1));
       b[i].setBounds(111,(25*(i-15))+1, 110, 25);
       b[i].setBackground(Color.GRAY);
       b[i].setForeground(Color.BLACK);
       b[i].setEnabled(true);
       b[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed1(evt);
            }
        });
       jPanel1.add(b[i]);
       }
       
           
       jScrollPane2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        setLayout(new BorderLayout());
        add(panel);
        
        
    }
    public void actionPerformed(ActionEvent e)
    {
        
        //   play.b[ques_id].setBackground(Color.GREEN);
//        JOptionPane.showMessageDialog(null,ques_id);
        
        if(e.getSource()==jRadioButton1&&jRadioButton1.isEnabled())
        {

            b[ques_id-1].setBackground(Color.GREEN);
           userChoice[ques_id-1]="a";
            try {
                actualAns[ques_id-1]=d.rs.getString("ans");
     //           JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);
            } catch (SQLException ex) {
                Logger.getLogger(QP.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }else if(e.getSource()==jRadioButton2&&jRadioButton2.isEnabled())
        {
 
           b[ques_id-1].setBackground(Color.GREEN);
           userChoice[ques_id-1]="b";
           try {
                actualAns[ques_id-1]=d.rs.getString("ans");
     //           JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);
            } catch (SQLException ex) {
                Logger.getLogger(QP.class.getName()).log(Level.SEVERE, null, ex);
            }
     //      JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);
           
        }
        else if(e.getSource()==jRadioButton3&&jRadioButton3.isEnabled())
        {
           b[ques_id-1].setBackground(Color.GREEN);
           userChoice[ques_id-1]="c";
           try {
                actualAns[ques_id-1]=d.rs.getString("ans");
     //           JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);
            } catch (SQLException ex) {
                Logger.getLogger(QP.class.getName()).log(Level.SEVERE, null, ex);
            }
       //               JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);

        }
        else if(e.getSource()==jRadioButton4&&jRadioButton4.isEnabled())
        {
           b[ques_id-1].setBackground(Color.GREEN);
          userChoice[ques_id-1]="d";
          try {
                actualAns[ques_id-1]=d.rs.getString("ans");
     //           JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);
            } catch (SQLException ex) {
                Logger.getLogger(QP.class.getName()).log(Level.SEVERE, null, ex);
            }
//           JOptionPane.showMessageDialog(null,userChoice[ques_id-1]);

          //add code to feed into         
        }
        
        
        
        
    }
    public void changeStatebyone(int i)
    {
        try
        {
            d.rs.first();
            for(int j=0;j<i;j++)
            {
                d.rs.next();
            }
            
                    jTextArea1.setText(d.rs.getString("question"));
                    jRadioButton1.setText(d.rs.getString("opt_1"));
                    jRadioButton2.setText(d.rs.getString("opt_2"));
                    jRadioButton3.setText(d.rs.getString("opt_3"));
                    jRadioButton4.setText(d.rs.getString("opt_4"));
                if(dbLayer.arr==0){
       ques_id=Integer.parseInt(d.rs.getString("ques_id"));
       }
       else if(dbLayer.arr==1){
       ques_id=Integer.parseInt(d.rs.getString("c1"));
       }
       else if(dbLayer.arr==2){
       ques_id=Integer.parseInt(d.rs.getString("c2"));
       }
                else if(dbLayer.arr==3){
       ques_id=Integer.parseInt(d.rs.getString("c3"));
       }
                else if(dbLayer.arr==4){
       ques_id=Integer.parseInt(d.rs.getString("c4"));
       }
                jLabel2.setText("Question "+ques_id);

                if(userChoice[ques_id-1]=="a")
                {
                    jRadioButton1.setSelected(true);
                }else if(userChoice[ques_id-1]=="b")
                {
  //                  JOptionPane.showMessageDialog(null,"b");

                    jRadioButton2.setSelected(true);

                }else if(userChoice[ques_id-1]=="c")
                {
    //                            JOptionPane.showMessageDialog(null,"c");

                    jRadioButton3.setSelected(true);

                }else if(userChoice[ques_id-1]=="d")
                {
      //              JOptionPane.showMessageDialog(null,"d");

                    jRadioButton4.setSelected(true);

                }else
                {
                        buttonGroup1.clearSelection();
                }
            
            
            
            
            
            
            
        }catch(Exception e)
        {
            
        }
    }
  /*  
        public void changeStatebytwo(int i)
    {
        try
        {
            d.rs.last();
            for(int j=30;j>i+1;j--)
            {
                d.rs.previous();
             }
            
                    jTextArea1.setText(d.rs.getString("question"));
                    jRadioButton1.setText(d.rs.getString("opt_1"));
                    jRadioButton2.setText(d.rs.getString("opt_2"));
                    jRadioButton3.setText(d.rs.getString("opt_3"));
                    jRadioButton4.setText(d.rs.getString("opt_4"));
                if(dbLayer.arr==0){
       ques_id=Integer.parseInt(d.rs.getString("ques_id"));
       }
       else if(dbLayer.arr==1){
       ques_id=Integer.parseInt(d.rs.getString("c1"));
       }
       else if(dbLayer.arr==2){
       ques_id=Integer.parseInt(d.rs.getString("c2"));
       }
                
                else if(dbLayer.arr==3){
       ques_id=Integer.parseInt(d.rs.getString("c3"));
       }
                else if(dbLayer.arr==4){
       ques_id=Integer.parseInt(d.rs.getString("c4"));
       }
                jLabel2.setText("Question "+ques_id);

                if(userChoice[ques_id-1]=="a")
                {
                    jRadioButton1.setSelected(true);
                }else if(userChoice[ques_id-1]=="b")
                {
  //                  JOptionPane.showMessageDialog(null,"b");

                    jRadioButton2.setSelected(true);

                }else if(userChoice[ques_id-1]=="c")
                {
    //                            JOptionPane.showMessageDialog(null,"c");

                    jRadioButton3.setSelected(true);

                }else if(userChoice[ques_id-1]=="d")
                {
      //              JOptionPane.showMessageDialog(null,"d");

                    jRadioButton4.setSelected(true);

                }else
                {
                        buttonGroup1.clearSelection();
                }
            
            
            
            
           
        }catch(Exception e)
        {
            
        }
    }
*/
    public void setConnection()
    {
        d=new dbLayer();
    }
    
    public void setnextValues()
    {
        try
        {
            if(ques_id==30)
            {
                
            }
            else
            {

        if(d.rs.next())
        {
        jTextArea1.setText(d.rs.getString("question"));
        jRadioButton1.setText(d.rs.getString("opt_1"));
        jRadioButton2.setText(d.rs.getString("opt_2"));
        jRadioButton3.setText(d.rs.getString("opt_3"));
        jRadioButton4.setText(d.rs.getString("opt_4"));
       if(dbLayer.arr==0){
       ques_id=Integer.parseInt(d.rs.getString("ques_id"));
       }
       else if(dbLayer.arr==1){
       ques_id=Integer.parseInt(d.rs.getString("c1"));
       }
       else if(dbLayer.arr==2){
       ques_id=Integer.parseInt(d.rs.getString("c2"));
       }
                else if(dbLayer.arr==3){
       ques_id=Integer.parseInt(d.rs.getString("c3"));
       }
                else if(dbLayer.arr==4){
       ques_id=Integer.parseInt(d.rs.getString("c4"));
       }
       jLabel2.setText("Question "+ques_id);
       
       actualAns[ques_id-1]=d.rs.getString("ans");
       
       if(userChoice[ques_id-1]=="a")
       {
           jRadioButton1.setSelected(true);
       }else if(userChoice[ques_id-1]=="b")
       {
           jRadioButton2.setSelected(true);
           
       }else if(userChoice[ques_id-1]=="c")
       {
           jRadioButton3.setSelected(true);
           
       }else if(userChoice[ques_id-1]=="d")
       {
           jRadioButton4.setSelected(true);
           
       }else
       {
            buttonGroup1.clearSelection();
       }
       

        }
        else
        {
            d.rs.first();
        }
       
       
        }
        }
        catch(Exception e)
        {
            
        }
    }
    
    
    public void setprevValues()
    {
        try
        {
            if(ques_id==1)
            {
                
            }
            else
            {

        if(d.rs.previous())
        {
                    jTextArea1.setText(d.rs.getString("question"));
                    jRadioButton1.setText(d.rs.getString("opt_1"));
                    jRadioButton2.setText(d.rs.getString("opt_2"));
                    jRadioButton3.setText(d.rs.getString("opt_3"));
                    jRadioButton4.setText(d.rs.getString("opt_4"));
                if(dbLayer.arr==0){
       ques_id=Integer.parseInt(d.rs.getString("ques_id"));
       }
       else if(dbLayer.arr==1){
       ques_id=Integer.parseInt(d.rs.getString("c1"));
       }
       else if(dbLayer.arr==2){
       ques_id=Integer.parseInt(d.rs.getString("c2"));
       }
                
                else if(dbLayer.arr==3){
       ques_id=Integer.parseInt(d.rs.getString("c3"));
       }
                else if(dbLayer.arr==4){
       ques_id=Integer.parseInt(d.rs.getString("c4"));
       }
                jLabel2.setText("Question "+ques_id);

                if(userChoice[ques_id-1]=="a")
                {
                    jRadioButton1.setSelected(true);
                }else if(userChoice[ques_id-1]=="b")
                {
  //                  JOptionPane.showMessageDialog(null,"b");

                    jRadioButton2.setSelected(true);

                }else if(userChoice[ques_id-1]=="c")
                {
    //                            JOptionPane.showMessageDialog(null,"c");

                    jRadioButton3.setSelected(true);

                }else if(userChoice[ques_id-1]=="d")
                {
      //              JOptionPane.showMessageDialog(null,"d");

                    jRadioButton4.setSelected(true);

                }else
                {
                        buttonGroup1.clearSelection();
                }
        
        }
        else
        {
            d.rs.last();
        }
        }
        }
        catch(Exception e)
        {
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        prevQ = new javax.swing.JButton();
        nextQ = new javax.swing.JButton();
        flag = new javax.swing.JButton();
        unflag = new javax.swing.JButton();
        clearch = new javax.swing.JButton();
        submit = new javax.swing.JButton();
        timeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("QUIZ");
        setBounds(new java.awt.Rectangle(0, 0, 1366, 768));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButton1.setAutoscrolls(true);
        jRadioButton1.setPreferredSize(new java.awt.Dimension(940, 25));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButton2.setMaximumSize(new java.awt.Dimension(30, 30));
        jRadioButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ENCODED BEANS");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 1000));

        jTextArea1.setBackground(new java.awt.Color(102, 102, 102));
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setForeground(new java.awt.Color(204, 204, 204));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        prevQ.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        prevQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/prevQ.png"))); // NOI18N
        prevQ.setAutoscrolls(true);
        prevQ.setMargin(new java.awt.Insets(1, 1, 1, 1));
        prevQ.setMaximumSize(new java.awt.Dimension(40, 40));
        prevQ.setPreferredSize(new java.awt.Dimension(40, 40));
        prevQ.setSelected(true);
        prevQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevQActionPerformed(evt);
            }
        });

        nextQ.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        nextQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nextQ.png"))); // NOI18N
        nextQ.setToolTipText("Next");
        nextQ.setMargin(new java.awt.Insets(1, 1, 1, 1));
        nextQ.setMaximumSize(new java.awt.Dimension(40, 40));
        nextQ.setPreferredSize(new java.awt.Dimension(40, 40));
        nextQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQActionPerformed(evt);
            }
        });

        flag.setText("FLAG");
        flag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flagActionPerformed(evt);
            }
        });

        unflag.setText("UNFLAG");
        unflag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unflagActionPerformed(evt);
            }
        });

        clearch.setText("CLEAR");
        clearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearchActionPerformed(evt);
            }
        });

        submit.setText("SUBMIT");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        timeLabel.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 0, 0));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("QUESTION ");

        jLabel3.setFont(new java.awt.Font("Trajan Pro", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Developed By: Nalin & Pratik");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(flag, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unflag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearch))
                    .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(submit)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(prevQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(125, 125, 125)
                        .addComponent(nextQ, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flag)
                            .addComponent(unflag)
                            .addComponent(clearch))
                        .addContainerGap(360, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(nextQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prevQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(submit)
                        .addGap(49, 49, 49))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQActionPerformed
        // TODO add your handling code here:
        actionPerformed1(evt);
    }//GEN-LAST:event_nextQActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void flagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flagActionPerformed
        // TODO add your handling code here:
        actionPerformed1(evt);
    }//GEN-LAST:event_flagActionPerformed

    private void unflagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unflagActionPerformed
        // TODO add your handling code here:
        actionPerformed1(evt);
    }//GEN-LAST:event_unflagActionPerformed

    private void clearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearchActionPerformed
        // TODO add your handling code here:
        actionPerformed1(evt);
    }//GEN-LAST:event_clearchActionPerformed

    private void prevQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevQActionPerformed
        // TODO add your handling code here:
        actionPerformed1(evt);
    }//GEN-LAST:event_prevQActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        int dialogButton=JOptionPane.YES_NO_OPTION;
        int dialogResult= JOptionPane.showConfirmDialog(null,"Do you wish to submit and quit?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION)
        actionPerformed1(evt);
        
        
    }//GEN-LAST:event_submitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and jTextArea1 the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new QP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearch;
    private javax.swing.JButton flag;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton nextQ;
    private javax.swing.JButton prevQ;
    private javax.swing.JButton submit;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton unflag;
    // End of variables declaration//GEN-END:variables
}
