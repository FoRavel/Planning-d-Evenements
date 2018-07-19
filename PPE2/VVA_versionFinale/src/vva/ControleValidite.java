/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vva;

import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fanilo
 */
public class ControleValidite {    
    public static Boolean formatHoraire(String heureDebut, String heureFin)
    {
        boolean conforme = false;
        Pattern p = Pattern.compile("([0-2][0-4])|(0[0-9])|([0-1][0-9]):[0-5][0-9]:[0-5][0-9]");
        
        Matcher m = p.matcher(heureDebut);
        boolean b = m.matches();

        m = p.matcher(heureFin);
        boolean b2 = m.matches();
        
        if(b == true && b2 == true)
            conforme = true;
        else
            conforme = false;
        return conforme;
    }
    
        public static Boolean horaireFinEstSuperieure(Time heureDebut, Time heureFin)
        {
            boolean conforme = false;

            if(heureFin.after(heureDebut))
                conforme = true;
            else
                conforme = false;    
            return conforme;
        }
        /* Obsolète depuis la création du calendrier
        public static Boolean formatDate(String date)
        {
            boolean conforme = false;
            Pattern p = Pattern.compile("[0-9]{4}-([1[0-2])|(0[0-9])-([0-2][0-9])|3[0-1]");
        }
        */
}
