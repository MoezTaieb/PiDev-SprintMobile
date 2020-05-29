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
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.services.ServiceCategorieProduit;

/**
 *
 * @author bhk
 */
public class UpdateCategorieProduitForm extends Form {

    public UpdateCategorieProduitForm(Form previous, CategorieProduit cat) {

        setTitle("Modifier");
        setLayout(BoxLayout.y());

        TextField tfnomCategorie = new TextField(cat.getNomCategorie(), "Nom Catégorie");

        Button btnValider = new Button("Modifier");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnomCategorie.getText().length() == 0)) {
                    Dialog.show("Erreur", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        CategorieProduit c = new CategorieProduit(cat.getId(),tfnomCategorie.getText());
                        if (ServiceCategorieProduit.getInstance().updateCategorie(c)) {
                            Dialog.show("Succès", "Modification effectuée", new Command("OK"));
                            new ListCategorieProduitForm(previous).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfnomCategorie, btnValider);
        getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
