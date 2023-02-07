package com.example.security_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController{

    public HelloController() throws SQLException {

    }
    static Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String query;


    public  Stage stage=new Stage();
    public  String Username="Ram",Password="Ram";
    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;

    @FXML
    private TextField Serial_No;

    @FXML
    private BorderPane Panel;

    @FXML
    private Button Display;

    @FXML
    private Button Exit;

    @FXML
    private Button Insert;

    @FXML
    private Button Update;
    @FXML
    private Button add;

    @FXML
    private Button cut;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField age;

    @FXML
    private TextField idn;

    @FXML
    private TextField idt;

    @FXML
    private TextField name;


    @FXML
    void Clear(ActionEvent event) {
        user.setText("");
        pass.setText("");
    }
    @FXML
    void Login(ActionEvent event) {
        if(user.getText().equals(Username)&&pass.getText().equals(Password)){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mainmenu.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage.setTitle("Security system ");
            Image icon=new Image("F:\\SecuritySystem\\src\\main\\java\\icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        }
        else{
            JOptionPane.showMessageDialog(null,"Username or Password is invalid!!");
            user.setText("");
            pass.setText("");
        }
    }

    @FXML
    void Insert(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("Insertform.fxml"));
        Panel.setCenter(Pane);
    }

    @FXML
    void ExitDate(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("InsertExitform.fxml"));
        Panel.setCenter(Pane);
    }
    @FXML
    void Update(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("Updation.fxml"));
        Panel.setCenter(Pane);
    }

    @FXML
    void Display(ActionEvent event) throws IOException {
        URL url1=null;
        ResourceBundle Rsb1=null;
        ScrollPane Pane = FXMLLoader.load(getClass().getResource("DisplayAll.fxml"));
        Panel.setCenter(Pane);

    }

    @FXML
    void Exit(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU" , "Security","123124136");
        query= "commit";
        pst=con.prepareStatement(query);
        rs= pst.executeQuery();
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"something went wrong");
            return;
        }
        JOptionPane.showMessageDialog(null,"commited Succesfully");
        con.close();
        System.out.println("ended");
        javafx.application.Platform.exit();
    }
    private int ch;
    void checking() throws ClassNotFoundException, SQLException {
        ch=0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU" , "security","123124136");
        query= "select name,age from Basic_details where ID_no= '"+idn.getText()+"' and ID_type='"+idt.getText()+"'";
        pst=con.prepareStatement(query);
        rs= pst.executeQuery();
        if(!rs.next())
        {
            query= "insert into Basic_details values('"+idn.getText()+"',"+"'"+idt.getText()+"','"+name.getText() +"',"+age.getText()+")";
            pst=con.prepareStatement(query);
            rs= pst.executeQuery();
            con.close();
            return;
        }
        String cname= rs.getString(1);
        Integer ag=rs.getInt(2);
        if(!(cname.equals(name.getText())&&Integer.valueOf(age.getText())==(ag))){
            ch=1;
        }
        con.close();
    }
    int checking2() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU" , "security","123124136");
        query= "select Exit_Date.Exit_date from Exit_Date where Sr_no= '"+Serial_No.getText()+"'";
        pst=con.prepareStatement(query);
        rs= pst.executeQuery();
        if(rs.next())
        {
            con.close();
            return 1;
        }
        else{
            con.close();
            return 0;
        }

    }
    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        checking();
        if(user.getText().equals(Username)&&pass.getText().equals(Password)&&ch==0) {
            int id = 0;
            String date = null;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU", "security", "123124136");
            query = "select SerialNo.nextval from dual";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                System.out.println("error");
            }
//        con.close();
//        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL" , "Security","123124136");
            query = "select to_char(sysdate,'DD-MM-YY HH24-MI-SS am') from dual";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next())
                date = rs.getString(1);
            else
                System.out.println("error");
//        con.close();
//        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL" , "Security","123124136");
            query = "insert into Entry_Date values(" + id + ",'" + idn.getText() + "'," + "'" + idt.getText() + "','" + date + "')";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null,"something went wrong");
                return;
            }
            query="insert into display values("+id+",'"+idn.getText()+"','"+idt.getText()+"','"+name.getText()+"',"+age.getText()+",'"+date+"','')";
            pst=con.prepareStatement(query);
            rs= pst.executeQuery();
            if(!rs.next()) {
                JOptionPane.showMessageDialog(null,"something went wrong");
                return;
            }
            JOptionPane.showMessageDialog(null,"Inserted Successfully");
            clear();
        } else if (ch==1) {
            JOptionPane.showMessageDialog(null,"Name or Age does not match");
        } else {
            JOptionPane.showMessageDialog(null,"Username or Password is invalid!!");
            clear();
        }
    }
    @FXML
    void InsertExitDate(ActionEvent event) throws ClassNotFoundException, SQLException {
        int p=checking2();
        if (user.getText().equals(Username) && pass.getText().equals(Password)&&p==0) {
            try {
                String date;
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU", "security", "123124136");
                query = "select to_char(sysdate,'DD-MM-YY HH24-MI-SS am') from dual";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                if(!rs.next()) {
                    JOptionPane.showMessageDialog(null,"something went wrong");
                    return;
                }
                    date = rs.getString(1);

                query = "insert into Exit_Date values(" + Serial_No.getText() + ",'" + date + "')";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                if(!rs.next()) {
                    JOptionPane.showMessageDialog(null,"something went wrong");
                    return;
                }

                query="update Display set Display.Exit_date='"+date+"' where Display.serial_no="+Serial_No.getText();
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                if(!rs.next()) {
                    JOptionPane.showMessageDialog(null, "something went wrong");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Inserted Succesfully");
                user.setText("");
                pass.setText("");
                idt.setText("");
                idn.setText("");
                Serial_No.setText("");
            }
            catch (Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error Serial number");
                user.setText("");
                pass.setText("");
                idt.setText("");
                idn.setText("");
                Serial_No.setText("");
            }
        }
        else if (p==1) {
            JOptionPane.showMessageDialog(null,"Alredy inserted");
            user.setText("");
            pass.setText("");
            idt.setText("");
            idn.setText("");
            Serial_No.setText("");
        } else{
            JOptionPane.showMessageDialog(null,"Username or Password is invalid!!");
            user.setText("");
            pass.setText("");
            idt.setText("");
            idn.setText("");
            Serial_No.setText("");
        }
    }
    @FXML
    void cut(ActionEvent event) {
        user.setText("");
        pass.setText("");
        idt.setText("");
        idn.setText("");
        name.setText("");
        age.setText("");

    }
    @FXML
    void cut2(ActionEvent event) {
        user.setText("");
        pass.setText("");
        idt.setText("");
        idn.setText("");
        Serial_No.setText("");

    }
    void clear(){
        user.setText("");
        pass.setText("");
        idt.setText("");
        idn.setText("");
        name.setText("");
        age.setText("");
    }
    @FXML
    void update(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (user.getText().equals(Username) && pass.getText().equals(Password)) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU", "security", "123124136");
            query = "update Basic_details set Name='" + name.getText() + "', Basic_details.Age=" + age.getText() + " where ID_no= '" + idn.getText() + "' and ID_type='" + idt.getText() + "'";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            if(!rs.next()) {
                JOptionPane.showMessageDialog(null,"something went wrong");
                return;
            }
            query = "update display set display.Name='" + name.getText() + "' where display.ID_no= '" + idn.getText() + "' and display.ID_type='" + idt.getText() + "'";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            if(!rs.next()) {
                JOptionPane.showMessageDialog(null,"something went wrong");
                return;
            }
            JOptionPane.showMessageDialog(null,"Updated Succesfully");
            clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Username or Password is invalid!!");
            clear();
        }
    }

}
