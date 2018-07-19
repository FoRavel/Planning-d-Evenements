/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Fanilo
 */
public class Activite extends Animation{
    private ArrayList<String> jour;
    private ArrayList<Time> heureDebut;
    private ArrayList<Time> heureFin;
    
    //Constructeur sans: idAnimation et nombreInscrit. Utilisé dans JFPlaniferActivite.java, lors de la planification d'une activité 
    //En effet, il est impossible de définir un id et un nombre d'inscrit à ce moment là.
    public Activite(ArrayList<String> j, ArrayList<Time> hD, ArrayList<Time> hF, int idCompte, int idTypeAnim, String lib, String desc, int limiteInsc)
    {
        super(idCompte, idTypeAnim, lib, desc, limiteInsc);
        jour = j;
        heureDebut = hD;
        heureFin = hF;
    }
    //Constructeur complet, pour instancier les activités chargées depuis la BDD. Appelé dans la fonction chargerAnimation() de la classe DonneesAnimation.
    public Activite(int idAnimation, ArrayList<String> j, ArrayList<Time> hD, ArrayList<Time> hF, int idCompte, int idTypeAnim, String lib, String desc, int nombreInscrit, int limiteInsc)
    {
        super(idAnimation, idCompte, idTypeAnim, lib, desc, nombreInscrit, limiteInsc);
        jour = j;
        heureDebut = hD;
        heureFin = hF;
    }
    
    public void setJour(ArrayList<String> jours)
    {
        this.jour = jours;
    }
    
    public void setHeureDeb(Time hD)
    {
        this.heureDebut.add(hD);
    }
    
    public void setHeureFin(Time hF)
    {
        this.heureFin.add(hF);
    }
    
    public ArrayList<String> getJour()
    {
        return this.jour;
    }
    
    public ArrayList<Time> getHeureDeb()
    {
        return this.heureDebut;
    }
    
    public ArrayList<Time> getHeureFin()
    {
        return this.heureFin;
    }
   
    
}
