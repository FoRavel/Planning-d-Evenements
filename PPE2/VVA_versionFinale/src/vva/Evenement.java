/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Fanilo
 */
public class Evenement extends Animation{
    Date date;
    Time heureDebut;
    Time heureFin;
    
    public Evenement(Date dt, Time hDeb, Time hFin, int idCompte, int idTypeAnim, String libelle, String desc, int limiteInsc)
    {
        super(idCompte, idTypeAnim, libelle, desc, limiteInsc);
        this.date = dt;
        this.heureDebut = hDeb;
        this.heureFin = hFin;
    }
    
    public Evenement(int idAnim, Date dt, Time hDeb, Time hFin, int idCompte, int idTypeAnim, String libelle, String desc, int nombreInscrit, int limiteInsc)
    {
        super(idAnim, idCompte, idTypeAnim, libelle, desc, nombreInscrit, limiteInsc);
        this.date = dt;
        this.heureDebut = hDeb;
        this.heureFin = hFin;
    }
    
    public void setDate(Date d)
    {
        this.date = d;
    }
    
    public void setHeureDeb(Time hD)
    {
        this.heureDebut = hD;
    }
    
    public void setHeureFin(Time hF)
    {
        this.heureFin = hF;
    }
    
    public Date getDate()
    {
        return this.date;
    }
    
    public Time  getHeureDeb()
    {
        return this.heureDebut;
    }
    
    public Time  getHeureFin()
    {
        return this.heureFin;
    }
    
}
