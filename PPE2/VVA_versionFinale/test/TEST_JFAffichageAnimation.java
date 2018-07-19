/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Fanilo
 */
public class TEST_JFAffichageAnimation extends JFrame{
    DonneesAnimation d;
    Connection conn;
    ArrayList<Evenement> arrEv;
    Animation animation;
    ArrayList<JPanel> arrJPanel;
    int nombreInscrit;
    int idCompte;
    public TEST_JFAffichageAnimation(int idCompte) throws SQLException
    {
        this.idCompte = idCompte;
        //CREATION FENETRE
        this.setTitle("Ma première fenêtre");
        //Largeur et hauteur (px)
        this.setSize(400,400);
        //Centrer la fenêtre
        this.setLocationRelativeTo(null);
        //Quitter le processus en cliquant sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vva2", "root", "");
        d = new DonneesAnimation(conn);

        arrJPanel = new ArrayList<>();
        arrEv = d.chargerEvenement();
        
        int i=0;
        while(i<arrEv.size())
        {
            JPanel container = new JPanel();
            
            JButton jbtInscription = new JButton("S'inscrire");
            JButton jbtDesinscription = new JButton("Se désinscrire");
            animation = arrEv.get(i);      
            nombreInscrit = animation.getNombreInscrit();
            int limiteInscription = animation.getLimiteInscrit();
            JLabel jlNbInscription = new JLabel(String.valueOf(nombreInscrit));
            JLabel jlLmtInscription = new JLabel(String.valueOf(limiteInscription));
            JLabel jlDescription = new JLabel(animation.getDescription());
            JLabel jlLibAnim = new JLabel(animation.getLibelle());
            
            if(d.estInscrit(this.idCompte, animation.getId()))
            {
                jbtDesinscription.setEnabled(true);
                jbtInscription.setEnabled(false);
            }
            else
            {
                jbtDesinscription.setEnabled(false);
                jbtInscription.setEnabled(true);
            }
            
            //Panel qui contient Libellé, Description, bouton...
            JPanel jpLibDesc = new JPanel();
            jpLibDesc.setLayout(new GridLayout(6,1));
            jpLibDesc.add(jlLibAnim);
            jpLibDesc.add(jlDescription);
            jpLibDesc.add(jlNbInscription);
            jpLibDesc.add(jlLmtInscription);
            jpLibDesc.add(jbtInscription);
            jpLibDesc.add(jbtDesinscription);
             
            container.add(jpLibDesc);
            container.setBackground(Color.blue);
            
            
            
            arrJPanel.add(jpLibDesc);
            
            /* 
            ACTIONLISTENER BOUTONS INSCRIPTIONS/DESINSCRIPTION
            Quand l'utilisateur clique sur le bouton inscription:
            - incrémentation de la valeur du nombre d'inscrit
            - Désactiver le bouton d'inscription
            - appliquer l'incrémentation de la valeur du nombre d'inscrit côté serveur
            */
            ActionListener clickInscription = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {             
                    try {
                        JButton j = (JButton) e.getSource();
                        int nbInscritModifie = Integer.parseInt(jlNbInscription.getText())+ 1;
                        jlNbInscription.setText(String.valueOf(nbInscritModifie));
                        
                        j.setEnabled(false);
                        jbtDesinscription.setEnabled(true);
                        d.ajouterUnInscrit(jlLibAnim.getText(), idCompte);
                    } catch (SQLException ex) {
                        Logger.getLogger(TEST_JFAffichageAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            };
             ActionListener clickDesinscription = new ActionListener()
            {   
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    
                    try {
                        JButton j = (JButton) e.getSource();
                        int nbInscritModifie = Integer.parseInt(jlNbInscription.getText())- 1;
                        jlNbInscription.setText(String.valueOf(nbInscritModifie));
                        
                        j.setEnabled(false);
                        jbtInscription.setEnabled(true);
                        d.supprimerUnInscrit(jlLibAnim.getText(), idCompte);
                    } catch (SQLException ex) {
                        Logger.getLogger(TEST_JFAffichageAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            };
            jbtInscription.addActionListener(clickInscription);
            jbtDesinscription.addActionListener(clickDesinscription);
            /*
            Au moment du chargement de la fenêtre, vérifier que le nombre d'inscrit est inférieur au nombre limite d'inscription
            Dans le cas contraire, bloquer le bouton d'inscription
            */
            if(Integer.parseInt(jlLmtInscription.getText()) <= Integer.parseInt(jlNbInscription.getText()))
            {
                jbtInscription.setEnabled(false);
            }
            i++;
        }
        GridLayout gl = new GridLayout(i,1);
        gl.setVgap(5);
        this.setLayout(gl);
        for(JPanel j: arrJPanel)
        {
            this.getContentPane().add(j);
        }
        this.setVisible(true);
    }
    
    
}
