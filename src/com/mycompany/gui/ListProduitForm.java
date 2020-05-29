/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.entities.Produit;
import com.mycompany.myapp.user.service.UserService;
import com.mycompany.services.ServiceCategorieProduit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author LQss
 */
public class ListProduitForm extends Form {

    Form current;
    int id;

    public ListProduitForm(Form previous) {
        current = this;

        setTitle("Produits");
        UserService us = new UserService();
        
        if (us.current().getUsername().equals("admin")){
            for (Produit p : ServiceProduit.getInstance().getAllProduits()) {

            Container cnt1 = new Container(BoxLayout.y());
            
            Image imgUrl;

            SpanLabel lbNom = new SpanLabel("Nom: " + p.getNomProduit());
            SpanLabel lbDesc = new SpanLabel("Description: " + p.getDescriptionProduit());
            SpanLabel lbCat = new SpanLabel("Catégorie: " + p.getNomCategorie());
            SpanLabel lbQte = new SpanLabel("Quantité: " + p.getQteProduit() + " Pièces");
            SpanLabel lbPrix = new SpanLabel("Prix: " + p.getPrixProduit() + " DT");
            
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/images/" + p.getPhoto(), "http://localhost/images/" + p.getPhoto());
            ImageViewer img = new ImageViewer(imgUrl);
            
            Button btnUpdate = new Button("Modifier");
            Button btnDelete = new Button("Supprimer");
            Button btnAffich = new Button("Afficher");
            //lbDesc.setAutoSizeMode(true);
            
            btnAffich.addActionListener((e)->{
            int i = p.getId();
            new AfficheProduitForm(current,p).show();
                });
            
            btnDelete.addActionListener((e)->{
            ServiceProduit.getInstance().RemoveProduit(p);
            current.show();
                });
            
            btnUpdate.addActionListener((e)->{
            int i = p.getId();
            new UpdateProduitForm(current,p).show();
                });
            
            cnt1.addAll(lbNom, lbDesc, lbCat, lbQte, lbPrix, img, btnAffich, btnUpdate, btnDelete);
            add(cnt1);
        }
        }
        else{
            for (Produit p : ServiceProduit.getInstance().getAllProduits()) {

            Container cnt2 = new Container(BoxLayout.y());
            
            Image imgUrl;

            SpanLabel lbNom = new SpanLabel("Nom: " + p.getNomProduit());
            SpanLabel lbDesc = new SpanLabel("Description: " + p.getDescriptionProduit());
            SpanLabel lbCat = new SpanLabel("Catégorie: " + p.getNomCategorie());
            
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/images/" + p.getPhoto(), "http://localhost/images/" + p.getPhoto());
            ImageViewer img = new ImageViewer(imgUrl);
            
            Button btnAffich = new Button("Afficher");
            //lbDesc.setAutoSizeMode(true);
            
            btnAffich.addActionListener((e)->{
            int i = p.getId();
            new AfficheProduitForm(current,p).show();
                });
            
            cnt2.addAll(lbNom, lbDesc, lbCat, img, btnAffich);
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
