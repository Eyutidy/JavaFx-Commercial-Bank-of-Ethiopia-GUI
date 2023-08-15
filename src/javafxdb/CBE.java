package javafxdb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CBE extends Application {

    String fname, uname, pass, gndr;
    String unamelog, passlog;
    Connection conn;
    Statement st;
    ResultSet rs;

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException, Exception {

        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDA", "root", "root");
        st = conn.createStatement();

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(30, 50, 50, 50));
        HBox hb = new HBox();
        hb.setPadding(new Insets(30, 20, 20, 30));
        //For the first scene this are the things 
        Label unlabel = new Label("User Name");
        unlabel.setId("uname");
        Label palabel = new Label("Password");
        TextField untext = new TextField();
        PasswordField pwtext = new PasswordField();
        Button log = new Button("Login");
        Label quest = new Label("Don't have account?");
        Hyperlink reglink = new Hyperlink("Register Here");
        Text text = new Text("Commercial Bank Of Ethiopia");
        text.setId("tx");
        Image cbe = new Image("me/CBE.png");
        ImageView img = new ImageView(cbe);
        img.setId("img");
        img.setFitHeight(260);
        img.setFitWidth(250);
        img.setImage(cbe);

        //For my image 
        GridPane logLayout = new GridPane();
        logLayout.setPadding(new Insets(100, 200, 400, 200));
        logLayout.setHgap(20);
        logLayout.setVgap(20);
        GridPane.setConstraints(img, 1, 1);
        logLayout.getChildren().add(img);
        hb.getChildren().add(text);
        logLayout.addRow(2, unlabel, untext);
        logLayout.addRow(3, palabel, pwtext);
        GridPane.setConstraints(log, 1, 4);
        logLayout.getChildren().add(log);
        logLayout.addRow(5, quest, reglink);
        Scene loginScene = new Scene(bp, 1100, 900);
        bp.getStylesheets().add("css/bank.css");

        //For the second register scene
        Label namelabel = new Label("Full Name");
        Label userlabel = new Label("User Name");
        Label passlabel = new Label("Password");
        Label genlabel = new Label("Gender");
        TextField nameText = new TextField();
        TextField userText = new TextField();
        PasswordField passText = new PasswordField();
        RadioButton maleradio = new RadioButton("Male");
        RadioButton femaleradio = new RadioButton("Female");
        Button resetButton = new Button("Reset");
        Button regButton = new Button("Register");

        regButton.setOnAction(e -> {
            fname = nameText.getText();
            uname = userText.getText();
            pass = passText.getText();
            if(fname.isEmpty()&& uname.isEmpty() && pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "please fill the forms!");
            }else if(fname.isEmpty()){
                 JOptionPane.showMessageDialog(null, "please fill fullname!");
            }else if(uname.isEmpty()){
                 JOptionPane.showMessageDialog(null, "please fill the username!");
            } else if(pass.isEmpty()){
                 JOptionPane.showMessageDialog(null, "please fill the password!");
            }
            else{ 
                if (maleradio.isSelected()) {
                gndr = "Male";
            }
            if (femaleradio.isSelected()) {
                gndr = "femal";
            }

            try {
                String sql = "INSERT INTO FORMTABLE VALUES('" + fname + "','" + uname + "','" + pass + "','" + gndr + "')";
                int i = st.executeUpdate(sql);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Successfully Registered");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Went Wrong:)");
                }
            } catch (Exception ex) {
                Logger.getLogger(CBE.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
     
        });
        resetButton.setOnAction(e ->{
          nameText.clear();
          userText.clear();
          passText.clear();
        });
        
        ToggleGroup genderGroup = new ToggleGroup();
        maleradio.setToggleGroup(genderGroup);
        femaleradio.setToggleGroup(genderGroup);
        Label quest2 = new Label("Already Registered?");
        Hyperlink loglink = new Hyperlink("Login Here");
        GridPane mylayout = new GridPane();
        ImageView img2 = new ImageView(cbe);
        img.setFitHeight(260);
        img.setFitWidth(250);
        img.setImage(cbe);
        mylayout.setHgap(20);
        mylayout.setVgap(20);
        mylayout.setPadding(new Insets(50, 200, 400, 200));
        GridPane.setConstraints(img2, 1, 0);
        mylayout.getChildren().add(img2);
        mylayout.addRow(1, namelabel, nameText);
        mylayout.addRow(2, userlabel, userText);
        mylayout.addRow(3, passlabel, passText);
        mylayout.addRow(4, genlabel, maleradio, femaleradio);
        mylayout.addRow(5, resetButton, regButton);
        mylayout.addRow(6, quest2, loglink);

        //----------------------------------After login scene--------------------------------------------
        ImageView img3 = new ImageView(cbe);
        img.setId("img");
        img.setFitHeight(260);
        img.setFitWidth(250);
        img.setImage(cbe);
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(1, 1);
        Scene transfer = new Scene(root, 1100, 900);
        root.getStylesheets().add("css/bank.css");
        Button deposit = new Button("Deposit");
        Button transfer2 = new Button("Transfer");
        Hyperlink hm=new Hyperlink("back");
        root.getChildren().add(deposit);
        root.getChildren().add(img3);
        root.getChildren().add(transfer2);
        root.getChildren().add(hm);
        root.setSpacing(20);
         hm.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        });
        //----------------------------------------------------------------
        GridPane dp = new GridPane();
        Text text2 = new Text("Deposit Voucher");
        text2.setId("voucher");
        dp.setVgap(15);
        dp.setHgap(10);
        dp.setPadding(new Insets(5,5,10,10));
        Hyperlink bck=new Hyperlink("back");
        //****************************************************
        Label branch = new Label("Branch");
        Label AccH = new Label("Account Holder");
        Label AccN = new Label("Account No");
        Label Deno = new Label("Denomination");
        Label birr = new Label("Birr 200X");
        Label type = new Label("Type of Account");
        Label meto = new Label("100X");
        Label save = new Label("Saving Account");
        Label hams = new Label("50X");
        Label curr = new Label("Current Account");
        Label five = new Label("5X");
        Label special = new Label("Special Account");
        Label one = new Label("1X");
        Label zero = new Label("0.50X");
        Label amount = new Label("Amount in word");
        Label zero2 = new Label("0.25X");
        Label Source = new Label("Source of Proceed");
        Label zero3 = new Label("0.10X");
        Label total = new Label("total");
        Label dpst = new Label("Deposited by");
        Label phn = new Label("Phone");
        Label sign = new Label("Signature");
        Button bs=new Button("Submit");
        //****************************************************
        TextField br = new TextField();
        TextField ach = new TextField();
        TextField acn = new TextField();
        TextField bir = new TextField();
        TextField mto = new TextField();
        TextField sa = new TextField();
        TextField hms = new TextField();
        TextField cra = new TextField();
        TextField fv = new TextField();
        TextField spa = new TextField();
        TextField src = new TextField();
        TextField on = new TextField();
        TextField zr = new TextField();
        TextField aiw = new TextField();
        TextField z2 = new TextField();
        TextField z3 = new TextField();
        TextField tt2 = new TextField();
        TextField dpb = new TextField();
        TextField ph = new TextField();
        TextField sgn = new TextField();
        //****************************************************
        ImageView img4 = new ImageView(cbe);
        img4.setFitHeight(160);
        img4.setFitWidth(150);
        img4.setImage(cbe);
        //****************************************************
       
        dp.addRow(1, branch, br);
        dp.addRow(2, AccH, ach);
        dp.addRow(3, AccN, acn);
        dp.addRow(4, type );
        dp.addRow(5, save, sa);
        dp.addRow(6, curr, cra);
        dp.addRow(7, special, spa);
        dp.addRow(9, amount, aiw);
        dp.addRow(10, Source,src);
        dp.addRow(12, dpst, dpb,phn,ph,sign,sgn);
        dp.addRow(13, bck);
        
        //****************************************************
        GridPane.setConstraints(text2, 7, 0);
        dp.getChildren().add(text2); 
        GridPane.setConstraints(img4, 6, 0);
        dp.getChildren().add(img4);
        GridPane.setConstraints(Deno, 4, 2);
        dp.getChildren().add(Deno);
        GridPane.setConstraints(birr, 4, 3);
        dp.getChildren().add(birr);
        GridPane.setConstraints(bir, 5, 3);
        dp.getChildren().add(bir);
        GridPane.setConstraints(meto, 4, 4);
        dp.getChildren().add(meto);
        GridPane.setConstraints(mto, 5, 4);
        dp.getChildren().add(mto);
        GridPane.setConstraints(hams, 4, 5);
        dp.getChildren().add(hams);
        GridPane.setConstraints(hms, 5, 5);
        dp.getChildren().add(hms);
        GridPane.setConstraints(five, 4, 6);
        dp.getChildren().add(five);
        GridPane.setConstraints(fv, 5, 6);
        dp.getChildren().add(fv);
        GridPane.setConstraints(one, 4, 7);
        dp.getChildren().add(one);
        GridPane.setConstraints(on, 5, 7);
        dp.getChildren().add(on);
        GridPane.setConstraints(zero, 4, 8);
        dp.getChildren().add(zero);
        GridPane.setConstraints(zr, 5, 8);
        dp.getChildren().add(zr);
        GridPane.setConstraints(zero2, 4, 9);
        dp.getChildren().add(zero2);
        GridPane.setConstraints(z2, 5, 9);
        dp.getChildren().add(z2);
        GridPane.setConstraints(zero3, 4, 10);
        dp.getChildren().add(zero3);
        GridPane.setConstraints(z3, 5, 10);
        dp.getChildren().add(z3);
        GridPane.setConstraints(total, 4, 11);
        dp.getChildren().add(total);
        GridPane.setConstraints(tt2, 5, 11);
        dp.getChildren().add(tt2);
        GridPane.setConstraints(bs, 6, 13);
        dp.getChildren().add(bs);
      //----------------------------------------------------------------------------------
     
            
        //****** **********************************************
        Scene dscene = new Scene(dp, 1200, 700);
        dp.getStylesheets().add("css/bank.css");
        deposit.setOnAction(e -> {
            primaryStage.setScene(dscene);
        });
         bck.setOnAction(e -> {
            primaryStage.setScene(transfer);
        });

        //-----------------------------------------------------------------------------------------------
        log.setOnAction(e -> {

            unamelog = untext.getText();
            passlog = pwtext.getText();
            String passdb;
            String unamedb;
            String Sql = "SELECT * FROM FORMTABLE WHERE UNAME='" + unamelog + "'";
            try {
                rs = st.executeQuery(Sql);
                while (rs.next()) {
                    passdb = rs.getString("Pass");
                    unamedb =rs.getString("Uname");
                    if (passlog.equals(passdb)&&unamelog.equals(unamedb)) {
                        primaryStage.setScene(transfer);
                        JOptionPane.showMessageDialog(null, "Login successfull!!");
                    } else{
                          JOptionPane.showMessageDialog(null, "Invalid crediential!!");
                    }
                   
                 }                
                     if(unamelog.isEmpty()&&passlog.isEmpty()){
                     JOptionPane.showMessageDialog(null, "Please fill username and password!");
                      }
                     else    if(passlog.isEmpty()){
                     JOptionPane.showMessageDialog(null, "Please fill password");
                     }
                     else if(unamelog.isEmpty()){
                     JOptionPane.showMessageDialog(null, "please fill username!");
                 }
              

            } catch (SQLException ex) {
                Logger.getLogger(CBE.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        //------------------------------------------------------------------------------------------------
        GridPane tf = new GridPane();
        Text text3 = new Text("Cash Withdrawal Voucher");
        text3.setId("voucher");
        tf.setVgap(15);
        tf.setHgap(10);
        tf.setPadding(new Insets(5,5,10,10));
        
        //********************************************************************************
        Label acch1=new Label("Account Holder");
        Label biw=new Label("Birr in words");
        Label fig =new Label("Birr in figure");
        Label lol = new Label("Select only one");
        String st[] = {"Saving Account", "Special deposit account" };
        Label xm=new Label("Account No.");
        Label bn=new Label("Acc Holder sign"); 
        
                
         //*********************************************************
         TextField acchf=new TextField();
         TextField biwf=new TextField();
         TextField figf=new TextField();
         TextField an=new TextField();
         TextField bu=new TextField();
         Button cd=new Button("withdraw");
         Hyperlink bck3=new Hyperlink("back");
         /////////////////////////////////
        ImageView img5 = new ImageView(cbe);
        img5.setFitHeight(160);
        img5.setFitWidth(150);
        img5.setImage(cbe);
         //*********************************************************
               
                
                tf.addRow(4,lol);

			CheckBox c = new CheckBox(st[0]);
                        CheckBox c1 = new CheckBox(st[1]);
                        tf.addRow(5, c);
                        tf.addRow(6, c1);
                        c.setId("checkbox");
                        c1.setId("checkbox");
               
                tf.addRow(7, xm,an);
          
                
                //////////////////////////////////////
                GridPane.setConstraints(text3, 6, 0);
                   tf.getChildren().add(text3); 
                GridPane.setConstraints(img5, 5, 0);
                   tf.getChildren().add(img5);
                GridPane.setConstraints(acch1, 0, 1);
                   tf.getChildren().add(acch1);
                GridPane.setConstraints(acchf, 1, 1);
                   tf.getChildren().add(acchf);
                GridPane.setConstraints(biw, 0, 2);
                   tf.getChildren().add(biw);
                GridPane.setConstraints(biwf, 1, 2);
                   tf.getChildren().add(biwf);
                GridPane.setConstraints(fig, 2, 2);
                   tf.getChildren().add(fig);
                GridPane.setConstraints(figf, 3, 2);
                   tf.getChildren().add(figf);
                GridPane.setConstraints(bn, 2, 7);
                   tf.getChildren().add(bn);    
                GridPane.setConstraints(bu, 3, 7);
                   tf.getChildren().add(bu);
                GridPane.setConstraints(bck3, 0, 9);
                   tf.getChildren().add(bck3);
                GridPane.setConstraints(cd, 5, 9);
                   tf.getChildren().add(cd);
         //*********************************************************       
        Scene sc = new Scene(tf, 1200, 900);
        sc.getStylesheets().add("css/bank.css");
            transfer2.setOnAction(e -> {
            primaryStage.setScene(sc);
            });
             bck3.setOnAction(e -> {
            primaryStage.setScene(transfer);
            });
        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(logLayout);

        Scene regScene = new Scene(mylayout, 1100, 900);
        mylayout.getStylesheets().add("css/bank.css");
        reglink.setOnAction(e -> {
            primaryStage.setScene(regScene);
        });

        loglink.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        });
        primaryStage.setScene(loginScene);
        primaryStage.getIcons().add(cbe);
        primaryStage.show();
        primaryStage.setTitle("CBE login");

    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}