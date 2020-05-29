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
public class CommentProduit {
    int id;
    String content;
    int produit_id;
    int user_id;
    String username;

    public CommentProduit() {
    }

    public CommentProduit(int id, String content, int produit_id, int user_id) {
        this.id = id;
        this.content = content;
        this.produit_id = produit_id;
        this.user_id = user_id;
    }

    public CommentProduit(String content, int produit_id, int user_id) {
        this.content = content;
        this.produit_id = produit_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


