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
public class CategorieProduit {
    private int id;
    private String nomCategorie;

    public CategorieProduit(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public CategorieProduit(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
    }
    
    public CategorieProduit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return nomCategorie;
    }

    
}
