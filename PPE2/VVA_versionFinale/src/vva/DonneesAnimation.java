/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanilo
 */
public class DonneesAnimation {
    //REQUETE PREPAREE: https://docs.oracle.com/javase/7/docs/api/
    private static Connection connection;
    
    public DonneesAnimation()
    {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vva2", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DonneesAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void enregistrerAnimation(Animation a)
    {
        //Informations génériques à ajouter dans la table "mère" Animation
        try {
            int idCompte = a.getIdCompte();
            int idTypAnim = a.getIdTypeAnim();
            String libAnim = a.getLibelle();
            String descriptionAnim = a.getDescription();
            int limiteInscrit = a.getLimiteInscrit();
            
            PreparedStatement pStatement = this.connection.prepareStatement("INSERT INTO animation(idCompte, idTypeAnim, libAnim, descriptionAnim, limiteInscrit) VALUES(?, ?, ?, ?, ?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1, idCompte);
            pStatement.setInt(2, idTypAnim);
            pStatement.setString(3, libAnim);
            pStatement.setString(4, descriptionAnim);
            pStatement.setInt(5, limiteInscrit);
            pStatement.execute();
            ResultSet rs = pStatement.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            int idAnimation = auto_id;
            int i = 0;
            
            //Données à ajouter dans la table "fille" Activite.
            if(a instanceof Activite)
            {
                ArrayList<String> jour = ((Activite) a).getJour();
                ArrayList<Time> heureDeb = ((Activite) a).getHeureDeb();
                ArrayList<Time> heureFin = ((Activite) a).getHeureFin();
                //ci dessous, pour chaque jour de la liste "jour" on obtient les horaires correspondantes et on les ajoute en BDD
                for(String j : jour)
                {    
                     String libelleJour = j;
                     Time hD = heureDeb.get(i);
                     Time hF = heureFin.get(i);

                     pStatement = this.connection.prepareStatement("INSERT INTO activite(idAnimation, libelleJour, heureDeb, heureFin) VALUES(?,?,?, ?) ");
                     pStatement.setInt(1, idAnimation);
                     pStatement.setString(2, libelleJour);
                     pStatement.setTime(3, hD);
                     pStatement.setTime(4, hF);
                     pStatement.execute();

                     i++;
                }
            }
            //Données à ajouter dans la table "fille" Evenement.
            if(a instanceof Evenement)
            {
                Date d = ((Evenement) a).getDate();
                Time hD = ((Evenement) a).getHeureDeb();
                Time hF = ((Evenement) a).getHeureFin();

                pStatement = this.connection.prepareStatement("INSERT INTO evenement(idAnim, date, heureDeb, heureFin) VALUES(?,?,?, ?)");
                pStatement.setInt(1, idAnimation);
                pStatement.setDate(2, d);
                pStatement.setTime(3, hD);
                pStatement.setTime(4, hF);
                pStatement.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonneesAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public ArrayList<TypeAnim> getTypeAnim() throws SQLException
    
    {       
        ArrayList<TypeAnim> type = new ArrayList();
         
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT idTypeAnim, libTypAnim FROM typeanimation");
        while(resultSet.next())
        {
            int idType = resultSet.getInt("idTypeAnim");
            String libelle = resultSet.getString("libTypAnim");
            TypeAnim tA = new TypeAnim(idType, libelle);
            type.add(tA);
        }
        return type;
    }
    
    
    public ArrayList<Evenement> chargerEvenement() throws SQLException
    {
        ArrayList<Evenement> arrEven = new ArrayList();
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT a.idAnim, idCompte, idTypeAnim, libAnim, descriptionAnim, nombreInscrit, limiteInscrit, date, heureDeb, heureFin, e.idAnim "
                + "FROM animation a, evenement e "
                + "WHERE a.idAnim = e.idAnim");
        while(resultSet.next())
        {
            int idAnim = resultSet.getInt("idAnim");
            int idTypeAnim = resultSet.getInt("idTypeAnim");
            int idCompte = resultSet.getInt("idCompte");
            String libAnim = resultSet.getString("libAnim");
            String desc = resultSet.getString("descriptionAnim");
            int nbInscrit = resultSet.getInt("nombreInscrit");
            int lmtInscrit = resultSet.getInt("limiteInscrit");
            Date dt = resultSet.getDate("date");
            Time hD = resultSet.getTime("heureDeb");
            Time hF = resultSet.getTime("heureFin");
            Evenement e = new Evenement(idAnim, dt, hD, hF, idCompte, idTypeAnim, libAnim, desc, nbInscrit, lmtInscrit);
            arrEven.add(e);
        }
        return arrEven;
    }
    
     public ArrayList<Activite> chargerActivite() throws SQLException
     {
        ArrayList<Animation> aArr = new ArrayList<>();
        
        
        ArrayList<Activite> actArr = new ArrayList();
        
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT a.idAnim, idCompte, idTypeAnim, libAnim, descriptionAnim, nombreInscrit, limiteInscrit"
                + " FROM animation a, activite ac WHERE a.idAnim = ac.idAnimation ");
        while(resultSet.next())
        {
            Animation a = new Animation(resultSet.getInt("idAnim"), 0, 0, resultSet.getString("libAnim"), resultSet.getString("descriptionAnim"), resultSet.getInt("nombreInscrit"), resultSet.getInt("limiteInscrit"));
            aArr.add(a);
        }
        
        int i = 0;
        for(Animation a: aArr)
        {
            ArrayList<String>j = new ArrayList<>();
            ArrayList<Time>hD = new ArrayList<>();
            ArrayList<Time>hF = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT ac.idAnimation, ac.libelleJour, ac.heureDeb,  ac.heureFin"
                    + " FROM activite ac"
                    + " WHERE ac.idAnimation ="+ a.getId()+"");
            while(resultSet.next())
            {
                j.add(resultSet.getString("libelleJour"));
                hD.add(resultSet.getTime("heureDeb"));
                hF.add(resultSet.getTime("heureFin"));
            }
            Activite ac = new Activite(a.getId(), j, hD, hF, 0, aArr.get(i).getIdTypeAnim(), aArr.get(i).getLibelle(), aArr.get(i).getDescription(), aArr.get(i).getNombreInscrit(), aArr.get(i).getLimiteInscrit());
            actArr.add(ac);
            i++;
        }
        return actArr;
     }
     
     public void ajouterUnInscrit(String libelleAnimation, int idCompte) throws SQLException
     {
         int idAnim = 0;
         PreparedStatement pStatement = this.connection.prepareStatement("SELECT idAnim "
                 + " FROM animation "
                 + " WHERE libAnim = ?");
         pStatement.setString(1, libelleAnimation);
         ResultSet rs =  pStatement.executeQuery();
         while(rs.next())
         {
             idAnim = rs.getInt("idAnim");
         }
         
         pStatement = this.connection.prepareStatement("UPDATE animation"
                 + " SET nombreInscrit = nombreInscrit+1"
                 + " WHERE libAnim = ?");
         pStatement.setString(1, libelleAnimation);    
         pStatement.execute();
         
         pStatement = this.connection.prepareStatement("INSERT INTO inscription(idCompte, idAnimation)"
                 + " VALUES(?, ?)");
         pStatement.setInt(1, idCompte);
         pStatement.setInt(2, idAnim);
         pStatement.execute();
     }
     
     public void supprimerUnInscrit(String libelleAnimation, int idCompte) throws SQLException
     {
         int idAnim = 0;
         PreparedStatement pStatement = this.connection.prepareStatement("SELECT idAnim "
                 + " FROM animation "
                 + " WHERE libAnim = ?");
         pStatement.setString(1, libelleAnimation);
         ResultSet rs =  pStatement.executeQuery();
         while(rs.next())
         {
             idAnim = rs.getInt("idAnim");
         }
         
         pStatement = this.connection.prepareStatement("UPDATE animation"
                 + " SET nombreInscrit = nombreInscrit-1"
                 + " WHERE libAnim = ?");
         pStatement.setString(1, libelleAnimation);    
         pStatement.execute();
         
         pStatement = this.connection.prepareStatement("DELETE FROM inscription"
                 + " WHERE idCompte = ?"
                 + " AND idAnimation = ?");
         pStatement.setInt(1, idCompte); 
         pStatement.setInt(2, idAnim);   
         pStatement.execute();
     }
     
     public boolean estInscrit(int idCompte, int idAnimation) throws SQLException
     {
         int nombreDeResultat = 0;
         boolean resultatTrouve = false;
         PreparedStatement pStatement = this.connection.prepareStatement("SELECT idInscription "
                 + " FROM inscription"
                 + " WHERE idCompte = ?"
                 + " AND idAnimation = ?");
         pStatement.setInt(1, idCompte);
         pStatement.setInt(2, idAnimation);
         
         ResultSet rs = pStatement.executeQuery();
         while(rs.next())
         {
             nombreDeResultat++;
         }
         if(nombreDeResultat > 0)
         {
             resultatTrouve = true;
         }
         
         return resultatTrouve;
     }
     /*
     private boolean compteExiste(String login, String motDePasse) throws SQLException
     {
         int nombreDeResultat = 0;
         boolean resultatTrouve = false;
         PreparedStatement pStatement = this.connection.prepareStatement("SELECT idCompte, login, mdp"
                 + " FROM compte"
                 + " WHERE login = ?"
                 + " AND mdp = ?");
         pStatement.setString(1, login);
         pStatement.setString(2, motDePasse);
         
         ResultSet rs = pStatement.executeQuery();
         while(rs.next())
         {
             nombreDeResultat++;
         }
         if(nombreDeResultat > 0)
         {
             resultatTrouve = true;
         }
          return resultatTrouve;    
     }
     */
     public Compte chargerCompte(String login, String motDePasse) throws SQLException
     {   
         Compte c = null;
         /*if(compteExiste(login, motDePasse))
         {
         */
            PreparedStatement pStatement = this.connection.prepareStatement("SELECT idCompte, prenomCompte, nomCompte, login, mdp, typeCompte"
                    + " FROM compte"
                    + " WHERE login = ?"
                    + " AND mdp = ?");
            pStatement.setString(1, login);
            pStatement.setString(2, motDePasse);

            ResultSet rs = pStatement.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("idCompte");
                String prenom = rs.getString("prenomCompte");
                String nom = rs.getString("nomCompte");
                String lgn = rs.getString("login");
                String mdp = rs.getString("mdp");
                int idTypeCompte = rs.getInt("typeCompte");

                c = new Compte(id, lgn, mdp, prenom, nom, idTypeCompte);
            }
        //}
        return c;
     }
          
}
