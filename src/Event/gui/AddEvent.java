/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

/**
 *
 * @author LQss
 */
import com.codename1.capture.Capture;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.codename1.util.Callback;
import Event.entities.Evenement;
import Event.services.Service;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;





/**
 *
 * @author bhk
 */
public class AddEvent extends Form {

    private String imageName;
    
    public AddEvent(Form previous) {

        setTitle("Add a new Feedback");
        setLayout(BoxLayout.y());

      Picker dateev = new Picker();
        TextField nomev = new TextField("", "nomEv: ");
  TextField lieu = new TextField("", "lieu: ");
    TextField nbr = new TextField("", "nbr: ");
      TextField image = new TextField("", "image: ");
        Button btnValider = new Button("Add Event");

        Button img = new Button("add image");
      
       

      
       img.addActionListener(new ActionListener<ActionEvent>() {
 @Override
 public void actionPerformed(ActionEvent evt) {
 // TODO Auto-generated method stub
 Display.getInstance().openGallery((e) -> {
            if(e != null && e.getSource() != null) {
//                String file = (String)e.getSource();
//                try {
//                Label path = new Label(file);
//                hi.add(path);
//                    
//                } catch(Exception err) {
//                    Log.e(err);
//                } 

// Upload Picture 
  

                         String filePath = (String) e.getSource();
       File file = new File(filePath);
       String fileName = file.getName();
       imageName = fileName;
                try {
                    int fileNameIndex = filePath.lastIndexOf("/") + 1;
                    
                    System.out.println("a"+fileNameIndex);
                    System.out.println("aa"+fileName);
                    image.setText(fileName);
                     MultipartRequest request = new MultipartRequest();
                     System.out.println("1");
                            request.setUrl("http://localhost/pidev20/web/uploads/images/image.php");
                      
                            request.setPost(true);
                    request.addData("avatar", file.getAbsolutePath(), "image/png");
                 
                            request.setFilename("avatar", file.getName());
           
                            NetworkManager.getInstance().addToQueue(request);
                             System.out.println("5");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                    //ServiceFeedback.UploadPic(filePath, Statics.BASE_URL+"/uploadMobile");
                    Image img = null;
            
                try {
                    img = Image.createImage(FileSystemStorage.getInstance().openInputStream(file.getAbsolutePath()));
                    System.out.println("aaa"+img);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }, Display.GALLERY_IMAGE);
 }
 });
       // BLock Shutdown
            
                
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ( (nomev.getText().length() == 0)&(dateev.getText().length() == 0)&(lieu.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                   
                       
                        String lieuu =lieu.getText();
                        String nome = nomev.getText();
                        Integer nbre = (int) Float.parseFloat(nbr.getText());
                     Image img ;
                     
                   img=  Qrcode.qrcode(nomev.getText());
                      String qr;
                    img.setImageName("qr"+nomev.getText());
                    qr=img.getImageName();
                       System.out.println(imageName);
                      System.out.println("aaaaaaaa"+qr);
                    try {
                        if (Service.getInstance().AddEvent(nome, lieuu, dateev.getValue().toString(),nbre ,imageName,qr )) {

                            
                            
                             
                            Dialog.show("Success", "Event accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (ParseException ex) {
                        System.out.println("aaaaa");
                    }
                        
                        
                  

                }

            }
        });

        addAll(dateev,nbr,lieu,image, nomev,img, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface prÃ©cÃ©dente

    }

    
}
