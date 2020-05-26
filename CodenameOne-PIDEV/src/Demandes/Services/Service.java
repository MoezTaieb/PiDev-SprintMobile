/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.Services;

import Demandes.Entitys.Demande;
import Demandes.Utilitys.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SIRINE
 */
public class Service {
    
    public ArrayList<Demande> DemandesList;

    public static Service instance = null;
    public boolean httpResponse;
    private ConnectionRequest cnx;
    
    private Service() {
        //cette classe implemente singleton
        //chaque instance de cet classe instancie une cnx
        cnx = new ConnectionRequest();   
    }
    
    public static Service getInstance() {

        //une seule instance de cette classe (Service) est permise par classe d'appelle
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
    
    //extraire les objets affectation de la reponse json
    
    public ArrayList<Demande> parseDemandes(String jsonText) {

        try {
            DemandesList = new ArrayList<Demande>();

            JSONParser jsonParser = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            //le flux json un map <String, Object> ou la clé par defaut est root (un string)
            //la valeur (Object) est un tableaux des map (List<MAP<String,Object>>).. chaque case est un map<idDemande, DemandeObject>
            Map<String, Object> demandeMap = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //on recupere l Object (un list des map) de la map racine associee au cle root
            List<Map<String, Object>> demandeMapObject = (List<Map<String, Object>>) demandeMap.get("root");
            
            //un objet pour formatter la date
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            
            //on doit parcourir la list des map (object du map racine) map<idAffectation, AffectationObject> pour recuperer les objets Affectation
            for (Map<String, Object> i : demandeMapObject) {

                //construir un object de type affectation a partir de l object de la map affectationMapObject<String, Object>
                Demande d = new Demande();
                d.setId((int)Float.parseFloat( i.get("id").toString()));
                d.setRemarque(i.get("remarque").toString());
                d.setEtat(i.get("etat").toString());
                d.setCas(i.get("cas").toString());
                //d.setDate(dateFormat.parse(i.get("date").toString()));
                d.setDate(i.get("date").toString());

                //ajouter cette objet a notre liste d affectation finale et propre
                DemandesList.add(d);
            }

        } catch (Exception ex) {
            System.out.println("erreur de parsing" + ex);
        }

        return DemandesList;
    }
    
    //extraire les objets apartir de la reponse json et les enregistrer dans arraylist
    
    public ArrayList<Demande> readDemandes(String tag) {

        //construir l url vers le service read et attribuer le tag
        String url = Statics.DEMANDES_URL + "Read" + tag;

        //etablir la cnx vers ce service
        cnx.setUrl(url);
        cnx.setPost(false);

        cnx.addResponseListener(e -> {

            //une fois la cnx est etablit le service s execute et renvoie un flux json comme reponse
            //on recupere cette reponse json et on la transforme en une liste d objets
            DemandesList = parseDemandes(new String(cnx.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return DemandesList;
    }
    
    //ajouter une demande a l aide du service d ajout sur le serveur web
    
    public boolean ajouterDemande(Demande d){
        //construir l url vers le service New (ajout)
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        String url = Statics.DEMANDES_URL + "New" + "?remarque="+d.getRemarque()+"&etat="+d.getEtat()+"&cas="+d.getCas()+"&date="+d.getDate();
        
        //etablir la cnx vers le service d ajout
        cnx.setUrl(url);
        cnx.addResponseListener(e->{
            
            //recuperer la reponse http du service d ajout 200 = ok
            if (cnx.getResponseCode() == 200){
                httpResponse = true;
            }else{
                httpResponse = false;
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        
        return httpResponse;
    }
    
    //modifier une demande a l aide d un service web de modification
    
    public boolean modifierDemande(Demande d){
        
        //construir l url vers le service Edit
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        String url = Statics.DEMANDES_URL + "Edit"+d.getId()+"?remarque="+d.getRemarque()+"&etat="+d.getEtat()+"&cas="+d.getCas()+"&date="+d.getDate();
        
        //etablir la cnx vers le service
        cnx.setUrl(url);
        cnx.addResponseListener(e->{
            
            //recuperer la reponse http du service d ajout 200 = ok
            if (cnx.getResponseCode() == 200){
                httpResponse = true;
            }else{
                httpResponse = false;
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        
        return httpResponse;
    }
    
    //supprimer une demande a l aide d un service web de suppression
    
    public boolean supprimerDemande(Demande d){
        
        //construir l url vers le service Delete
        String url = Statics.DEMANDES_URL + "Delete"+ d.getId();
        
        //etablir la cnx vers le service
        cnx.setUrl(url);
        cnx.addResponseListener(e->{
            
            //recuperer la reponse http du service d ajout 200 = ok
            if (cnx.getResponseCode() == 200){
                httpResponse = true;
            }else{
                httpResponse = false;
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        
        return httpResponse;
    }
}
