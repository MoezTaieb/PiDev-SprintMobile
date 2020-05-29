/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

/**
 *
 * @author Moez
 */
public class Evenement {
    
 private int id;
    private String nomEvenement;
    private String lieuEvenement;
    private String dateEvenement;
    private int nbrMaxParticipants;
    private String imageEvenement;
     private String qr;
    private int produit_id;
    private String nomProduit;
    

    public Evenement() {
    }

    public Evenement(int id, String nomEvenement, String lieuEvenement, String dateEvenement, int nbrMaxParticipants, String imageEvenement, int produit_id) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.nbrMaxParticipants = nbrMaxParticipants;
        this.imageEvenement = imageEvenement;
        this.produit_id = produit_id;
        
    }
    
    
    
    
    

    public Evenement(String nomEvenement, String lieuEvenement, String dateEvenement, int nbrMaxParticipants, String imageEvenement, int produit_id) {
        this.nomEvenement = nomEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.nbrMaxParticipants = nbrMaxParticipants;
        this.imageEvenement = imageEvenement;
        this.produit_id = produit_id;
    }

    public Evenement(int id, String nomEvenement, String lieuEvenement, String dateEvenement, int nbrMaxParticipants, String imageEvenement) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.nbrMaxParticipants = nbrMaxParticipants;
        this.imageEvenement = imageEvenement;
    }

    public Evenement(String nomEvenement, String lieuEvenement, String dateEvenement, int nbrMaxParticipants, String imageEvenement) {
        this.nomEvenement = nomEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.nbrMaxParticipants = nbrMaxParticipants;
        this.imageEvenement = imageEvenement;
 
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public int getNbrMaxParticipants() {
        return nbrMaxParticipants;
    }

    public void setNbrMaxParticipants(int nbrMaxParticipants) {
        this.nbrMaxParticipants = nbrMaxParticipants;
    }

    public String getImageEvenement() {
        return imageEvenement;
    }

    public void setImageEvenement(String imageEvenement) {
        this.imageEvenement = imageEvenement;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    @Override
    public String toString() {
        return "Evenement{" +"id=" + id + ", nomEvenement=" + nomEvenement + ", lieuEvenement=" + lieuEvenement + ", dateEvenement=" + dateEvenement + ", nbrMaxParticipants=" + nbrMaxParticipants + ", imageEvenement=" + imageEvenement + '}';
    }
    
    
    
    
    
}
