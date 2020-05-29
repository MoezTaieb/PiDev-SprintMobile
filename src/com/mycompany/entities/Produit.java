/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Othmen
 */
public class Produit {
    private int id;
    private int categorie_id;
    private String nomProduit;
    private String prixProduit;
    private String qteProduit;
    private String descriptionProduit;
    private String photo = "null";
    private String nomCategorie;
    
    
    public static final String CONFIG_FILE = "config.txt";
    
    public Produit(int id, String nomProduit, String prixProduit, String qteProduit, String descriptionProduit, int categorie_id) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.qteProduit = qteProduit;
        this.descriptionProduit = descriptionProduit;
        this.categorie_id = categorie_id;
        
    }

    public Produit(String nomProduit, String prixProduit, String qteProduit, String descriptionProduit, int categorie_id) {
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.qteProduit = qteProduit;
        this.descriptionProduit = descriptionProduit;
        this.categorie_id = categorie_id;
        
    }

    public Produit() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(String prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getQteProduit() {
        return qteProduit;
    }

    public void setQteProduit(String qteProduit) {
        this.qteProduit = qteProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    
    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", nomProduit=" + nomProduit + ", prixProduit=" + prixProduit + ", qteProduit=" + qteProduit + ", descriptionProduit=" + descriptionProduit + ", photo=" + photo + '}';
    }
    
    
    
}
