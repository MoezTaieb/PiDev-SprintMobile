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
import com.mycompany.entities.CommentProduit;
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
public class ServiceCommentProduit {

    public ArrayList<CommentProduit> Comments;

    public static ServiceCommentProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private ServiceCommentProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceCommentProduit getInstance() {
        if (instance == null) {
            instance = new ServiceCommentProduit();
        }
        return instance;
    }
    
    public boolean addComment(CommentProduit c) {
        String url = Statics.BASE_URL + "/produit/newcomment/" +c.getUser_id()+ "/" +c.getProduit_id()+ "/" +c.getContent();
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
    /*
    public boolean updateCategorie(CategorieProduit c) {
        String url = Statics.BASE_URL + "/categorieproduit/"+ c.getId() +"update/" + c.getNomCategorie();
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
    */

    public boolean RemoveComment(CommentProduit c) {
        String url = Statics.BASE_URL + "/produit/removecomment/" + c.getId();
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


    public ArrayList<CommentProduit> parseComments(String jsonText) {
        try {
            Comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CommentsListJson.get("root");

            for (Map<String, Object> obj : list) {

                CommentProduit c = new CommentProduit();

                float id = Float.parseFloat(obj.get("id").toString());

                c.setId((int) id);
                Map<String, Object> user = (Map<String, Object>) obj.get("user");
                float user_id = Float.parseFloat(user.get("id").toString());
                String username = user.get("username").toString();
                Map<String, Object> produit = (Map<String, Object>) obj.get("produit");
                float produit_id = Float.parseFloat(produit.get("id").toString());
                c.setUser_id((int) user_id);
                c.setProduit_id((int) produit_id);
                c.setContent(obj.get("content").toString());
                c.setUsername(username);
                Comments.add(c);
            }

        } catch (IOException ex) {

        }
        return Comments;
    }

    public ArrayList<CommentProduit> parseOneComment(String jsonText) {
        try {
            Comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            CommentProduit c = new CommentProduit();

            float id = Float.parseFloat(CommentsListJson.get("id").toString());
            c.setId((int) id);
            
            Map<String, Object> user = (Map<String, Object>) CommentsListJson.get("user");
            float user_id = Float.parseFloat(user.get("id").toString());
            String username = user.get("username").toString();
            
            Map<String, Object> produit = (Map<String, Object>) CommentsListJson.get("produit");
            float produit_id = Float.parseFloat(user.get("id").toString());
            
            c.setUser_id((int) user_id);
            c.setProduit_id((int) produit_id);
            c.setContent(CommentsListJson.get("content").toString());
            c.setUsername(username);
            
            Comments.add(c);
        } catch (IOException ex) {

        }
        return Comments;
    }

    public ArrayList<CommentProduit> getAllComments(Produit p) {
        String url = Statics.BASE_URL + "/produit/comments/" +p.getId();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Comments = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Comments;
    }
    /*
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
*/

    
}
