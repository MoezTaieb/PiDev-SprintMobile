/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DonationArgent.Entitys;

/**
 *
 * @author SIRINE
 */
public class Argent {
    
    private int id;
    private String montant;
    private String date;

    public Argent() {
    }

    public Argent(int id, String montant, String date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }
    
    public Argent(String montant, String date) {
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getMontant() {
        return montant;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public void setDate(String date) {
        this.date = date;
    }
        
}
