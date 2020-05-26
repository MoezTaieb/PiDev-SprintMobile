/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DonationArgent.Services;

import DonationArgent.Entitys.Argent;
import DonationArgent.Utilitys.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

/**
 *
 * @author SIRINE
 */
public class Service {
    
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
    
    //ajouter une trace du don argent a l aide du service d ajout sur le serveur web
    
    public boolean ajouterArgent(Argent a){
        //construir l url vers le service New (ajout)
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        String url = Statics.ARGENTS_URL + "New" + "?montant="+a.getMontant()+"&date="+a.getDate();
        
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
}
