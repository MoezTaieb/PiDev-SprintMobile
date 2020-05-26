/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Entitys;

import java.util.Date;

/**
 *
 * @author SIRINE
 */
public class Affectation {
    
    private int id;
    private String remarque;
    private String date;

    public Affectation() {
    }
    
    public Affectation(String remarque, String date) {
        this.remarque = remarque;
        this.date = date;
    }

    public Affectation(int id, String remarque, String date) {
        this.id = id;
        this.remarque = remarque;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getRemarque() {
        return remarque;
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

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
