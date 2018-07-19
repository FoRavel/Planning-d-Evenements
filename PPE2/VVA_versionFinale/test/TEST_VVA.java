/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author Fanilo
 */
public class TEST_VVA {

    static final String DB_URL = "jdbc:mysql://localhost:3306/vva2";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        System.out.println("RESULTAT");
        // SQL-GUIDE CONNEXION BDD ET REQUETE NORMAL:https://www.developer.com/java/data/creating-a-jdbc-application-in-netbeans-a-step-by-step-guide.html
        // REQUETE PREPAREE: https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
        /*
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        ArrayList<Animation> aArr = new ArrayList<>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT a.idAnim, idCompte, idTypeAnim, libAnim, descriptionAnim, nombreInscrit, limiteInscrit"
                    + " FROM animation a");
            while(resultSet.next())
            {
                Animation a = new Animation(resultSet.getInt("idAnim"), 0, 0, resultSet.getString("libAnim"), resultSet.getString("descriptionAnim"), resultSet.getInt("nombreInscrit"), resultSet.getInt("limiteInscrit"));
                aArr.add(a);
            }            
                    
            statement = connection.createStatement();
            int i = 0;
            for(Animation a: aArr)
            {
                ArrayList<Time>hD = new ArrayList<>();
                ArrayList<Time>hF = new ArrayList<>();
                ArrayList<String>j = new ArrayList<>();
                resultSet = statement.executeQuery("SELECT ac.idAnimation, ac.libelleJour, ac.heureDeb,  ac.heureFin"
                        + " FROM activite ac"
                        + " WHERE ac.idAnimation ="+ aArr.get(i).getId()+"");
                while(resultSet.next())
                {
                    System.out.println(resultSet.getTime("heureDeb") +" "+ resultSet.getTime("heureFin"));
                    j.add(resultSet.getString("libelleJour"));
                    hD.add(resultSet.getTime("heureDeb"));
                    hF.add(resultSet.getTime("heureFin"));
                }
                Activite ac = new Activite(j, hD, hF, 0, 0, aArr.get(i).getLibelle(), aArr.get(i).getDescription(), aArr.get(i).getLimiteInscrit(), aArr.get(i).getNombreInscrit());
                System.out.println(ac.getHeureDeb());
                System.out.println(ac.getHeureFin());
                System.out.println();
               
                i++;
            }
        }
        catch(SQLException exception){
        }
        
        DonneesAnimation d = new DonneesAnimation(connection);
        
        d.chargerEvenement();
        /*
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        DonneesAnimation da = new DonneesAnimation(connection);
        
        ArrayList<String> jour = new ArrayList<>();
        ArrayList<Time> heureDebut = new ArrayList<>();;
        ArrayList<Time> heureFin = new ArrayList<>();;
        Time t1 = new Time(1000);
        Time t2 = new Time(2000);
        jour.add("j1");
        jour.add("j2");
        heureDebut.add(t1);
        heureDebut.add(t2);
        heureFin.add(t1);
        heureFin.add(t2);
        */
        //Activite a = new Activite(jour, heureDebut, heureFin, 1, "nomAct", "descAct");
        
        //da.enregistrerActivite(a);
        
        //Date d = new Date(1000); 
        //Time t = new Time(3000);
        
        //Evenement e = new Evenement(4,d,t, 3, "libEv", "descEv");
        //da.enregistrerEvenement(e);
        
        //Date dt = new Date(951951600000L);
        
        //System.out.println(dt);
   /*     
CALENDRIER 
        //Date actuelle et dernière date de l'année
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfYear = LocalDate.of(2018, 12, 31);
        //Forme alphabétique du mois
        Month nowMonth = today.getMonth();
        //Nombre de mois entre la date actuelle et la dernière date de l'année
        Period beginingToEndOfYear = today.until(lastDayOfYear);
        int nombreDeMois = beginingToEndOfYear.getMonths();
        
        LocalDate newDate = today;
        LocalDate RAZDate;
        Period monthToMonth; 
        int nombreDeJour;
        for(int i=0;i<nombreDeMois;i++)
        {
            newDate = newDate.plusMonths(1);
            RAZDate = newDate.withDayOfMonth(1);
            LocalDate nextMonth = RAZDate.plusMonths(1);
            monthToMonth = RAZDate.until(nextMonth);
            nombreDeJour = (int) ChronoUnit.DAYS.between(RAZDate, nextMonth);
            Month monthOfDate = newDate.getMonth();
            System.out.println(monthOfDate + ": " + nombreDeJour );
            for(int j = 1; j<=nombreDeJour; j++)
            {
                System.out.println(j);
            }
        }
        
        System.out.println(today);
        System.out.println(today.plusMonths(1));
        System.out.println(ChronoUnit.DAYS.between(today, today.plusMonths(1)));
        
        /*System.out.println(today.until(today.plusMonths(1)));
        System.out.println(today.until(today.plusMonths(1)).getChronology());
        */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vva2", "root", "");
        DonneesAnimation d = new DonneesAnimation(conn);
        
        
        Activite a = d.chargerActivite().get(0);
        ArrayList<String> arr = a.getJour();
        
        for(String s:arr)
            System.out.println(s);
        
    }
    
    
}
