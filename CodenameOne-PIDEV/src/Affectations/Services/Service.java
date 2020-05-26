/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Services;

import Affectations.Entitys.Affectation;
import Affectations.Utilitys.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.processing.Result.JSON;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SIRINE
 */
public class Service {

    public ArrayList<Affectation> AffectationsList;

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
    
    public ArrayList<Affectation> parseAffectations(String jsonText) {

        try {
            AffectationsList = new ArrayList<Affectation>();

            JSONParser jsonParser = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            //le flux json un map <String, Object> ou la clé par defaut est root (un string)
            //la valeur (Object) est un tableaux des map (List<MAP<String,Object>>).. chaque case est un map<idAffectation, AffectationObject>
            Map<String, Object> affectationMap = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //on recupere l Object (un list des map) de la map racine associee au cle root
            List<Map<String, Object>> affectationMapObject = (List<Map<String, Object>>) affectationMap.get("root");
            
            //un objet pour formatter la date
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            
            //on doit parcourir la list des map (object du map racine) map<idAffectation, AffectationObject> pour recuperer les objets Affectation
            for (Map<String, Object> i : affectationMapObject) {
                
                //construir un object de type affectation a partir de l object de la map affectationMapObject<String, Object>
                Affectation a = new Affectation();
                a.setId((int)Float.parseFloat( i.get("id").toString()));
                a.setRemarque(i.get("remarque").toString());
                //a.setDate(dateFormat.parse(i.get("date").toString()));
                a.setDate(i.get("date").toString());
                //ajouter cette objet a notre liste d affectation finale et propre
                AffectationsList.add(a);
            }

        } catch (Exception ex) {
            System.out.println("erreur de parsing" + ex);
        }

        return AffectationsList;
    }

    //extraire les objets apartir de la reponse json et les enregistrer dans arraylist
    
    public ArrayList<Affectation> readAffectations(String tag) {

        //construir l url vers le service read
        String url = Statics.AFFECTATIONS_URL + "Read" + tag;

        //etablir la cnx vers ce service
        cnx.setUrl(url);
        cnx.setPost(false);

        cnx.addResponseListener(e -> {

            //une fois la cnx est etablit le service s execute et renvoie un flux json comme reponse
            //on recupere cette reponse json et on la transforme en une liste d objets
            AffectationsList = parseAffectations(new String(cnx.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return AffectationsList;
    }

    //ajouter une affectation a l aide du service d ajout sur le serveur web
    
    public boolean ajouterAffectation(Affectation a){
        //construir l url vers le service New (ajout)
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        String url = Statics.AFFECTATIONS_URL + "New" + "?remarque="+a.getRemarque()+"&date="+a.getDate();
        
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
    
    //modifier une affectation a l aide d un service web de modification
    
    public boolean modifierAffectation(Affectation a){
        
        //construir l url vers le service Edit
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        String url = Statics.AFFECTATIONS_URL + "Edit"+ a.getId() + "?remarque="+a.getRemarque()+"&date="+a.getDate();
        
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
    
    //supprimer une affectation a l aide d un service web de suppression
    
    public boolean supprimerAffectation(Affectation a){
        
        //construir l url vers le service Delete
        String url = Statics.AFFECTATIONS_URL + "Delete"+ a.getId();
        
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
