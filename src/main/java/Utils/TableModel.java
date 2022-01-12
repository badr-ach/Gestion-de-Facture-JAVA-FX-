/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haddad
 */
public class TableModel {

    private int NbreLignes=0 ;
    private int Nbcolonnes ;
    private ArrayList<String> Titres ;
    private ArrayList<Vector<String> > MesLignes = new ArrayList<Vector<String>>() ;

    public ArrayList<Vector<String>> getMesLignes() {
        return MesLignes;
    }

    public void setMesLignes(ArrayList<Vector<String>> mesLignes) {
        MesLignes = mesLignes;
    }

    public TableModel(ResultSet Res)
    {
        try {

            ResultSetMetaData rsmd = Res.getMetaData();
            Nbcolonnes = rsmd.getColumnCount();
            Titres = new ArrayList<>(Nbcolonnes);
            for(int i=0 ; i< Nbcolonnes ;i++)
                Titres.add(rsmd.getColumnLabel(i+1));
            Vector<String> Ligne ;
            while(Res.next())
            { 
               Ligne = new Vector<String>();
                for(int i=0 ; i<Nbcolonnes ;i++)
                {
                    if(Res.getObject(i+1)!=null)
                        Ligne.add(Res.getObject(i+1).toString());
                    else
                        Ligne.add("");
                }
                    
                MesLignes.add(Ligne);
                NbreLignes++ ;
            }
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int getRowCount() {
       return NbreLignes ;
    }


    public int getColumnCount() {
       return Nbcolonnes ;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return MesLignes.get(rowIndex).get(columnIndex);
    }

    public ArrayList<String> getColumnNames() {
        return Titres;
    }

    public String getColumnName(int column){return Titres.get(column); }
    
}

