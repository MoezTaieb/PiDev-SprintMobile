/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.RequestBuilder;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CategorieProduit;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceProduit {

    public ArrayList<Produit> Produits;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
    public boolean addProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/add/" + p.getCategorie_id() + "/" + p.getNomProduit() + "/" + p.getPrixProduit() + "/" + p.getQteProduit() + "/" + p.getDescriptionProduit();
        req.setUrl(url);
        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean updateProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/update/" + p.getId() + "/" + p.getCategorie_id() + "/" + p.getNomProduit() + "/" + p.getPrixProduit() + "/" + p.getQteProduit() + "/" + p.getDescriptionProduit();
        req.setUrl(url);
        req.setHttpMethod("PUT");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean RemoveProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/remove/" + p.getId();
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


    public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ProduitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProduitsListJson.get("root");

            for (Map<String, Object> obj : list) {

                Produit p = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());

                p.setId((int) id);
                Map<String, Object> Cat = (Map<String, Object>) obj.get("categorie");
                float categorie_id = Float.parseFloat(Cat.get("id").toString());
                String nomCategorie = Cat.get("nomCategorie").toString();
                p.setCategorie_id((int) categorie_id);
                p.setNomCategorie(nomCategorie);
                
                p.setNomProduit(obj.get("nomProduit").toString());
                p.setPrixProduit(obj.get("prixProduit").toString());
                p.setQteProduit(obj.get("qteProduit").toString());
                p.setDescriptionProduit(obj.get("descriptionProduit").toString());
                p.setPhoto(obj.get("photo").toString());
                Produits.add(p);
            }

        } catch (IOException ex) {

        }
        return Produits;
    }

    public ArrayList<Produit> parseOneProduit(String jsonText) {
        try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ProduitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Produit p = new Produit();

            float id = Float.parseFloat(ProduitsListJson.get("id").toString());

            p.setId((int) id);
            
            Map<String, Object> cat = (Map<String, Object>) ProduitsListJson.get("categorie");
            float categorie_id = Float.parseFloat(cat.get("id").toString());
            String nomCategorie = cat.get("nomCategorie").toString();
            p.setCategorie_id((int) categorie_id);
            p.setNomCategorie(nomCategorie);
            
            
            p.setNomProduit(ProduitsListJson.get("nomProduit").toString());
            p.setPrixProduit(ProduitsListJson.get("prixProduit").toString());
            p.setQteProduit(ProduitsListJson.get("qteProduit").toString());
            p.setDescriptionProduit(ProduitsListJson.get("descriptionProduit").toString());
            p.setPhoto(ProduitsListJson.get("photo").toString());
            
            Produits.add(p);
        } catch (IOException ex) {

        }
        return Produits;
    }

    public ArrayList<Produit> getAllProduits() {
        String url = Statics.BASE_URL + "/produit/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }
    
    public ArrayList<Produit> filterProduits(int id) {
        String url = Statics.BASE_URL + "/produit/filter/" +id;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }
    
    public ArrayList<Produit> getProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/"+p.getId()+"/find";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseOneProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(p);
        return Produits;
    }
/*
    public ArrayList<Feedback> FindFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/find/" + f.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Feedbacks = parseOneFeedback(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }
*/
}
