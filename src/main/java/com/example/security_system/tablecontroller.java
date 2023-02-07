package com.example.security_system;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
//import javax.swing.table.TableColumn;
//import javax.swing.text.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class tablecontroller implements Initializable {
    @FXML
    private TableView<newfile> SecurityData;

    @FXML
    private TableColumn<newfile,Integer> IAge;

    @FXML
    private TableColumn<newfile,String> IEntry_Date;

    @FXML
    private TableColumn<newfile,String> IExit_Date;

    @FXML
    private TableColumn<newfile,String> IName;

    @FXML
    private TableColumn<newfile,String> INumber;

    @FXML
    private TableColumn<newfile,String> IType;

    @FXML
    private TableColumn<newfile,Integer> S_No;

    ObservableList<newfile> oblist= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection con;
        ResultSet rs;
        PreparedStatement pst;
        String query;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SHAILU" , "security","123124136");
            query= "select * from Display order by Display.serial_no";
            pst=con.prepareStatement(query);
            rs= pst.executeQuery();
            while (rs.next()){
                oblist.add(new newfile(rs.getInt("Serial_No"),rs.getString("Id_NO"),rs.getString("ID_TYPE"),rs.getString("NAME"),rs.getInt("AGE"),rs.getString("ENTRY_DATE"),rs.getString("EXIT_DATE")));
            }
            S_No.setCellValueFactory(new PropertyValueFactory<>("Tserial"));
            IType.setCellValueFactory(new PropertyValueFactory<>("Tidtype"));
            INumber.setCellValueFactory(new PropertyValueFactory<>("TidNumber"));
            IName.setCellValueFactory(new PropertyValueFactory<>("TName"));
            IAge.setCellValueFactory(new PropertyValueFactory<>("Tage"));
            IEntry_Date.setCellValueFactory(new PropertyValueFactory<>("Tentrydate"));
            IExit_Date.setCellValueFactory(new PropertyValueFactory<>("Texitdate"));
            SecurityData.setItems(oblist);
        }
        catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error in Display");
        }
    }
}
