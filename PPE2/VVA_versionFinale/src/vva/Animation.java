/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

/**
 *
 * @author Fanilo
 */
public class Animation {
    protected int id;
    protected int idCompte;
    protected int idTypeAnim;
    protected String libelle;
    protected String description;
    protected int nombreInscrit;
    protected int limiteInscrit;
    
    //Constructeur pour chargement avec id
    public Animation(int id, int idCompte, int idTypeAnim, String lib, String desc, int nombreInscrit , int limiteInsc)
    {
        this.id = id;
        this.idCompte = idCompte;
        this.idTypeAnim = idTypeAnim;
        this.libelle = lib;
        this.description = desc;
        this.nombreInscrit = nombreInscrit;
        this.limiteInscrit = limiteInsc;
    }
    
    //Constructeur pour enregistrement
    public Animation(int idCompte, int idTypeAnim, String lib, String desc, int limiteInsc)
    {
        this.idCompte = idCompte;
        this.idTypeAnim = idTypeAnim;
        this.libelle = lib;
        this.description = desc;
        this.nombreInscrit = 0;
        this.limiteInscrit = limiteInsc;
    }
    
    //Constructeur pour chargement avec nombre d'inscrit
    /*
    public Animation(int idCompte, int idTypeAnim, String lib, String desc, int nombreInscrit, int limiteInsc)
    {
        this.idCompte = idCompte;
        this.idTypeAnim = idTypeAnim;
        this.libelle = lib;
        this.description = desc;
        this.nombreInscrit = nombreInscrit;
        this.limiteInscrit = limiteInsc;
    }
    */
    public int getId()
    {
        return this.id;
    }
    
    public int getIdCompte()
    {
        return this.idCompte;
    }
    
    public int getIdTypeAnim()
    {
        return this.idTypeAnim;
    }
    
    public String getLibelle()
    {
        return this.libelle;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public int getNombreInscrit()
    {
        return this.nombreInscrit;
    }
    
    public int getLimiteInscrit()
    {
        return this.limiteInscrit;
    }
    
    @Override
    public String toString()
    {
        return this.getLibelle();
    }
}
