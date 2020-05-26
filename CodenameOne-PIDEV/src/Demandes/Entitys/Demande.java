/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.Entitys;

import java.util.Date;

/**
 *
 * @author SIRINE
 */
public class Demande {
    
    private int id;
    private String remarque;
    private String etat;
    private String cas;
    private String date;

    public Demande() {
    }
    
    public Demande(String remarque, String etat, String cas, String date) {
        this.remarque = remarque;
        this.etat = etat;
        this.cas = cas;
        this.date = date;
    }

    public Demande(int id, String remarque, String etat, String cas, String date) {
        this.id = id;
        this.remarque = remarque;
        this.etat = etat;
        this.cas = cas;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getEtat() {
        return etat;
    }

    public String getCas() {
        return cas;
    }

    public String getDate() {
        return date;
    }
        
    public void setId(int id) {
        this.id = id;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
