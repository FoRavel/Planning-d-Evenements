/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static vva.TEST_VVA.DB_URL;

/**
 *
 * @author Fanilo
 */
public class TEST_Modification {

    static final String DB_URL = "jdbc:mysql://localhost:3306/vva2";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pStatement = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        /*
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT libAnim FROM animation WHERE idAnim =151");
 
        while(resultSet.next())
        {
            System.out.println(resultSet.getString("libAnim"));
        }
        pStatement = connection.prepareStatement("UPDATE animation SET libAnim = 'MODIFIE4' WHERE idAnim = 151");
        pStatement.execute();
        
        
                resultSet = statement.executeQuery("SELECT libAnim FROM animation WHERE idAnim =151");
 
        while(resultSet.next())
        {
            System.out.println(resultSet.getString("libAnim"));
        }
        */
        
        //("SELECT libAnim FROM animation WHERE idAnim = 2")
        
        DonneesAnimation d = new DonneesAnimation(connection);
        System.out.println(d.estInscrit(1, 156));
        
        
        TEST_Modification.afficher();
    }
    
    public static void afficher()
    {
        System.out.println("erer");
    }
    
}
