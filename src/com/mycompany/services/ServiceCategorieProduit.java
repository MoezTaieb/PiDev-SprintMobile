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
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LQss
 */
public class ServiceCategorieProduit {

    public ArrayList<CategorieProduit> Categories;

    public static ServiceCategorieProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceCategorieProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorieProduit getInstance() {
        if (instance == null) {
            instance = new ServiceCategorieProduit();
        }
        return instance;
    }
    
    public boolean addCategorie(CategorieProduit c) {
        String url = Statics.BASE_URL + "/categorieproduit/add/" + c.getNomCategorie();
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
    
    public boolean updateCategorie(CategorieProduit c) {
        String url = Statics.BASE_URL + "/categorieproduit/update/" + c.getId() + "/" + c.getNomCategorie();
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

    public boolean RemoveCategorie(CategorieProduit c) {
        String url = Statics.BASE_URL + "/" +c.getId()+ "/remove";
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


    public ArrayList<CategorieProduit> parseCategories(String jsonText) {
        try {
            Categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategoriesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CategoriesListJson.get("root");

            for (Map<String, Object> obj : list) {

                CategorieProduit c = new CategorieProduit();

                float id = Float.parseFloat(obj.get("id").toString());

                c.setId((int) id);
                c.setNomCategorie(obj.get("nomCategorie").toString());
                
                Categories.add(c);
            }

        } catch (IOException ex) {

        }
        return Categories;
    }

    public ArrayList<CategorieProduit> parseOneCategorie(String jsonText) {
        try {
            Categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategoriesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            CategorieProduit c = new CategorieProduit();

            float id = Float.parseFloat(CategoriesListJson.get("id").toString());

            c.setId((int) id);
            c.setNomCategorie(CategoriesListJson.get("nomCategorie").toString());
            
            Categories.add(c);
        } catch (IOException ex) {

        }
        return Categories;
    }

    public ArrayList<CategorieProduit> getAllCategories() {
        String url = Statics.BASE_URL + "/categorieproduit/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Categories;
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
