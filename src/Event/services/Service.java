/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.RequestBuilder;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import Event.entities.Evenement;
import com.mycompany.myapp.entities.Feedback;
import Event.entities.Invite;
import Event.entities.Participation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author LQss
 */
public class Service {

    public ArrayList<Feedback> Feedbacks;
     public ArrayList<Evenement> Evenements;
      public ArrayList<Invite> Invites;
     public ArrayList<Participation>  Participations;

    public static Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public RequestBuilder delete;

    private Service() {
        req = new ConnectionRequest();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public boolean addFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/add/" + 
                f.getDescription() + "/" +
                f.getRate() + "/10";
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

    public boolean updateFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/mod/" + f.getId() + "/" + f.getDescription();
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

    public boolean RemoveFeedback(Feedback f) {
        String url = Statics.BASE_URL + "/reclamation/feedback/rem/" + f.getId();
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

    public ArrayList<Feedback> parseFeedbacks(String jsonText) {
        try {
            Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Feedback f = new Feedback();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
                float rate = Float.parseFloat(obj.get("rate").toString());

                f.setId((int) id);
                f.setDescription(obj.get("description").toString());
                f.setRate((int) rate);

                f.setDate(obj.get("date").toString().substring(0, 10));

                f.setUser((int) idU);
                Feedbacks.add(f);
            }

        } catch (IOException ex) {

        }
        return Feedbacks;
    }

    public ArrayList<Feedback> parseOneFeedback(String jsonText) {
        try {
            Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Feedback f = new Feedback();

            Map<String, Object> User = (Map<String, Object>) FeedbacksListJson.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());

            float id = Float.parseFloat(FeedbacksListJson.get("id").toString());
            float rate = Float.parseFloat(FeedbacksListJson.get("rate").toString());

            f.setId((int) id);
            f.setDescription(FeedbacksListJson.get("description").toString());
            f.setRate((int) rate);

            f.setDate(FeedbacksListJson.get("date").toString().substring(0, 10));

            f.setUser((int) idU);
            Feedbacks.add(f);
        } catch (IOException ex) {

        }
        return Feedbacks;
    }

    public ArrayList<Feedback> getAllFeedbacks() {
        String url = Statics.BASE_URL + "/reclamation/feedback/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Feedbacks = parseFeedbacks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }

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
            public ArrayList<Evenement> parseEvent(String jsonText) {
        try {
            Evenements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Evenement f = new Evenement();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
//                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
//                float rate = Float.parseFloat(obj.get("rate").toString());

                f.setId((int) id);
                f.setNomEvenement(obj.get("nomEvenement").toString());
                f.setDateEvenement(obj.get("dateEvenement").toString());

                f.setLieuEvenement(obj.get("lieuEvenement").toString());
                f.setImageEvenement(obj.get("image").toString());
                f.setNbrMaxParticipants((int)Float.parseFloat(obj.get("nombreMaxParticipant").toString()));

             
                Evenements.add(f);
            }

        } catch (IOException ex) {

        }
        return Evenements;
    }
                 public ArrayList<Invite> parseInvite(String jsonText) {
        try {
            Invites = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Invite f = new Invite();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
//                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
//                float rate = Float.parseFloat(obj.get("rate").toString());

                f.setId((int) id);
                f.setNomInvite(obj.get("nomInvite").toString());
                f.setPrenomInvite(obj.get("prenomInvite").toString());


             
                Invites.add(f);
            }

        } catch (IOException ex) {

        }
        return Invites;
    }
    
        public ArrayList<Evenement> getAllEvent() {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/all";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenements;
    }
      public boolean removeEvent(Evenement f) {
        String url = "http://localhost/pidev20/web/app_dev.php/Api/rem/" + f.getId();
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
                  
        public boolean AddEvent(String nome,String lieuu,String datee,Integer nbre ,String img ,String qr ) throws ParseException {
                       
                              //Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(f.getDateEvenement());
        String url =  "http://localhost/pidev20/web/app_dev.php/Api/add/?" + 
              "dateEvenement="+ datee+ "&" +
             "image="+ img+ "&"+
             "lieuEvenement="+  lieuu+"&"+
                "nomEvenement="+nome+"&"+
            "nombreMaxParticipant="+  nbre +"&"+
                "qr="+qr
                ;
      //  String url1="http://localhost/pidev20/web/app_dev.php/Api/add/?dateEvenement="+datee+"&image=%22aaaaaaa%22&lieuEvenement=%22aaaaaaaaaaa%22&nomEvenement=%22ur10%22&nombreMaxParticipant=25&qr=vandalz";
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
                                            public boolean AddInvite(Invite i , int id ) throws ParseException {
                       
                              //Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(f.getDateEvenement());
                           
        String url =  "http://localhost/pidev20/web/app_dev.php/Api/addIn/" + id +
                "?nomInvite="+i.getNomInvite()+"&prenomInvite="+i.getPrenomInvite()
             
                ;
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
                                            
       public ArrayList<Invite> getInviteByid(int id) {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/ListInv/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Invites = parseInvite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Invites;
    }
                                                    
          public boolean removeInvite(Invite f) {
        String url = "http://localhost/pidev20/web/app_dev.php/Api/SuppInvit/" + f.getId();
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
          
    public boolean updateEvent(Evenement f) {
        String url = "http://localhost/pidev20/web/app_dev.php/Api/updateEvent?id="+f.getId()+"&nomEvenement="+f.getNomEvenement()+"&lieuEvenement="+f.getLieuEvenement()+"&dateEvenement="+f.getDateEvenement()+"&NombreMaxParticipant="+f.getNbrMaxParticipants()+"&image="+f.getImageEvenement();
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
          
          
    
     public boolean updateInvite(Invite i) {
        String url = "http://localhost/pidev20/web/app_dev.php/Api/updateInvit?id="+i.getId()+"&nomInvite="+i.getNomInvite()+"&prenomInvite="+i.getPrenomInvite();
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
     
       public boolean ParticiperEvent(int p , int e) {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/addPart?evenementID="+e+"&participantID="+p;
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
       
                  public ArrayList<Participation> parsePart(String jsonText) {
        try {
            Participations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Participation f = new Participation();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
//                float idU = Float.parseFloat(User.get("id").toString());

                float id = Float.parseFloat(obj.get("id").toString());
//                float rate = Float.parseFloat(obj.get("rate").toString());

                f.setId((int) id);
                f.setNomEvent(obj.get("nomEvenement").toString());
                f.setLieuEvent(obj.get("lieuEvenement").toString());
                f.setDateParticipation(obj.get("dateParticipation").toString());


             
                Participations.add(f);
            }

        } catch (IOException ex) {

        }
        return Participations;
    }
                  
                  
                  
                  public ArrayList<Participation> parseParti(String jsonText) {
        try {
            Participations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Participation f = new Participation();

                Map<String, Object> User = (Map<String, Object>) obj.get("idU");
//                float idU = Float.parseFloat(User.get("id").toString());

              
                f.setNomEvent(obj.get("nomEvenement").toString());
         


             
                Participations.add(f);
            }

        } catch (IOException ex) {

        }
        return Participations;
    }
                  
                  
                  
                     public ArrayList<Participation> getMyEvent(int id) {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/myevent/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Participations = parsePart(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Participations;
    }
                     
                               public boolean annulerEvent(Participation f) {
        String url = "http://localhost/pidev20/web/app_dev.php/Api/annulerPart/" + f.getId();
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
                               
    static public void UploadPic(String i,String url)
    {
        try {
            MultipartRequest req = new MultipartRequest();
            req.setUrl(url);
            req.setPost(true);
            req.addData("photo",i,"image/jpeg");
            req.setFilename("photo",i);
            req.addArgument("image", i);
            req.addResponseListener(a -> {
                System.out.println("tanena");
                System.out.println(a.getMessage());
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
                     public ArrayList<Participation> verifEvent(int ide,int idu) {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/verif/"+ide+"/"+idu;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Participations = parseParti(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Participations;
    }
                     
                        public boolean updateEventt(int a , int b) {
        String url ="http://localhost/pidev20/web/app_dev.php/Api/update?id="+a+"&NombreMaxParticipant="+b;
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
    
    
                  
}

