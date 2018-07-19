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
public class TypeAnim {
    
    int idType;
    String libelleType;
    
    public TypeAnim(int idType, String libelleType)
    {
        this.idType = idType;
        this.libelleType = libelleType;
    }
    
    public String getLibelle()
    {
        return this.libelleType;
    }
    
    public int getId()
    {
        return this.idType;
    }

    @Override
    public String toString() {
        return this.getLibelle();
    }
}
