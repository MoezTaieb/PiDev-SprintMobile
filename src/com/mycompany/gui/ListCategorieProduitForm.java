/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.myapp.user.service.UserService;
import com.mycompany.services.ServiceCategorieProduit;

/**
 *
 * @author LQss
 */
public class ListCategorieProduitForm extends Form {

    Form current;

    public ListCategorieProduitForm(Form previous) {
        current = this;

        setTitle("Catégories Produits");
        setLayout(BoxLayout.y());
        UserService us = new UserService();
        
        if (us.current().getUsername().equals("admin")){
            for (CategorieProduit c : ServiceCategorieProduit.getInstance().getAllCategories()) {

            Container cnt1 = new Container(BoxLayout.y());

            SpanLabel lbNom = new SpanLabel("Catégorie: " + c.getNomCategorie());
            Button btnFilter = new Button("Produits de cette catégorie");
            Button btnUpdate = new Button("Modifier");
            Button btnDelete = new Button("Supprimer");
            //lbDesc.setAutoSizeMode(true);
            
            btnUpdate.addActionListener((e)->{
            int i = c.getId();
            new UpdateCategorieProduitForm(current,c).show();
                });
            
            btnFilter.addActionListener((e)->{
            int i = c.getId();
            new FiltredListProduitForm(current,c).show();
                });
            
            btnDelete.addActionListener((e)->{
                ServiceCategorieProduit.getInstance().RemoveCategorie(c);
                });
            
            cnt1.addAll(lbNom, btnFilter, btnUpdate, btnDelete);
            add(cnt1);
        }
        }
        else{
            for (CategorieProduit c : ServiceCategorieProduit.getInstance().getAllCategories()) {

            Container cnt2 = new Container(BoxLayout.y());

            SpanLabel lbNom = new SpanLabel("Nom: " + c.getNomCategorie());
            Button btnFilter = new Button("Produits de cette catégorie");
            //lbDesc.setAutoSizeMode(true);
            
            btnFilter.addActionListener((e)->{
            int i = c.getId();
            new FiltredListProduitForm(current,c).show();
                });
            
            cnt2.addAll(lbNom, btnFilter);
            add(cnt2);
        }
        }
        
        
        if (previous.getTitle() == "Home") {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        } else {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_DROP_UP, e -> new HomeForm().show());
            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());

        }

    }
}
