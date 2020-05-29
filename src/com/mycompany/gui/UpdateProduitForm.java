/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author LQss
 */
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceCategorieProduit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author bhk
 */
public class UpdateProduitForm extends Form {

    public UpdateProduitForm(Form previous, Produit prod) {

        setTitle("Ajout d'un Produit");
        setLayout(BoxLayout.y());

        TextField tfnomProduit = new TextField(prod.getNomProduit(), "Nom Produit");
        TextField tfprixProduit = new TextField(prod.getPrixProduit(), "Prix");
        TextField tfqteProduit = new TextField(prod.getQteProduit(), "Quantité");
        TextField tfdescProduit = new TextField(prod.getDescriptionProduit(), "Description");
        ComboBox<CategorieProduit> cat = new ComboBox();
        for (CategorieProduit c : ServiceCategorieProduit.getInstance().getAllCategories()) {
            cat.addItem(c);
        }

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnomProduit.getText().length() == 0) || (tfprixProduit.getText().length() == 0) || (tfqteProduit.getText().length() == 0) || (tfdescProduit.getText().length() == 0)) {
                    Dialog.show("Erreur", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        Produit p = new Produit(prod.getId(),tfnomProduit.getText(),tfprixProduit.getText(),tfqteProduit.getText(),tfdescProduit.getText(),cat.getSelectedItem().getId());
                        if (ServiceProduit.getInstance().updateProduit(p)) {
                            Dialog.show("Succès", "Produit modifié", new Command("OK"));
                            new ListProduitForm(previous).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfnomProduit, tfdescProduit, tfprixProduit, tfqteProduit, cat, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
