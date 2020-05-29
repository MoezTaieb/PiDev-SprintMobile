/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.entities.CommentProduit;
import com.mycompany.entities.Produit;
import com.mycompany.myapp.user.service.UserService;
import com.mycompany.services.ServiceCategorieProduit;
import com.mycompany.services.ServiceCommentProduit;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author LQss
 */
public class AfficheProduitForm extends Form {

    Form current;
    int id;

    public AfficheProduitForm(Form previous, Produit prod) {
        current = this;
        UserService us = new UserService();
        setLayout(BoxLayout.y());
        
        if (us.current().getUsername().equals("admin")){
            for (Produit p : ServiceProduit.getInstance().getProduit(prod)) {
            setTitle(p.getNomProduit());
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
            
            TextField tfNewComment = new TextField("", "Laissez votre avis");
            Button btnAddComment = new Button("Envoyer");
            //lbDesc.setAutoSizeMode(true);
            
            btnAddComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNewComment.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        CommentProduit c = new CommentProduit(tfNewComment.getText(), p.getId(), us.current().getid());
                        if (ServiceCommentProduit.getInstance().addComment(c)) {
                            new AfficheProduitForm(previous,prod).show();
                            Dialog.show("Success", "Votre avis est envoyé", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
            current.refreshTheme();

            cnt1.addAll( lbNom, lbDesc, lbCat, lbQte, lbPrix, img, tfNewComment, btnAddComment);

            add(cnt1);
        }
        }
        else {
            for (Produit p : ServiceProduit.getInstance().getProduit(prod)) {
            setTitle(p.getNomProduit());
            Container cnt2 = new Container(BoxLayout.y());
            
            Image imgUrl;

            SpanLabel lbNom = new SpanLabel("Nom: " + p.getNomProduit());
            SpanLabel lbDesc = new SpanLabel("Description: " + p.getDescriptionProduit());
            SpanLabel lbCat = new SpanLabel("Catégorie: " + p.getNomCategorie());
            
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/images/" + p.getPhoto(), "http://localhost/images/" + p.getPhoto());
            ImageViewer img = new ImageViewer(imgUrl);
            
            TextField tfNewComment = new TextField("", "Laissez votre avis");
            Button btnAddComment = new Button("Envoyer");
            //lbDesc.setAutoSizeMode(true);
            
            btnAddComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNewComment.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        CommentProduit c = new CommentProduit(tfNewComment.getText(), p.getId(), us.current().getid());
                        if (ServiceCommentProduit.getInstance().addComment(c)) {
                            new AfficheProduitForm(previous,prod).show();
                            Dialog.show("Success", "Votre avis est envoyé", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
            current.refreshTheme();

            cnt2.addAll( lbNom, lbDesc, lbCat, img, tfNewComment, btnAddComment);

            add(cnt2);
        }
        }
        
        
        
        
        Form hi = new Form("Partage");
        ShareButton sb = new ShareButton();
        sb.setText("Partager");
        current.add(sb);

        Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
        current.revalidate();
        current.setVisible(true);
        current.paintComponent(screenshot.getGraphics(), true);

        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
        Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/png");
        
        for (CommentProduit c : ServiceCommentProduit.getInstance().getAllComments(prod)) {
            
            Container cnt3 = new Container(BoxLayout.x());
            
            
            Label lbUsername = new Label(c.getUsername()+ ":");
            Label lbComment = new Label(c.getContent());
            Button btnDelete = new Button("Supprimer");
             btnDelete.addActionListener((e)->{
                 ServiceCommentProduit.getInstance().RemoveComment(c);
                });
            btnDelete.setVisible(false);
            if ((c.getUsername().equals(us.current().getUsername())) || (us.current().getUsername().equals("admin"))){
                btnDelete.setVisible(true);
            }
            
            cnt3.addAll(lbUsername, lbComment, btnDelete);
            add(cnt3);
        }
        
        getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    AfficheProduitForm(Form current, int id) {
        this.current = current;
        this.id = id;
    }

    
}
