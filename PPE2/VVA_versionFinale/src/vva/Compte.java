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
public class Compte {
    int id;
    String login;
    String motDePasse;
    String prenom;
    String nom;
    int idTypeCompte;

    public Compte(int id, String login, String motDePasse, String prenom, String nom, int idTypeCompte) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;
        this.idTypeCompte = idTypeCompte;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public int getIdTypeCompte()
    {
        return this.idTypeCompte;
    }
    
    
}
