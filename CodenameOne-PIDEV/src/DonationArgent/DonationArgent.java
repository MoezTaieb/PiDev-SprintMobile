/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DonationArgent;

import DonationArgent.Services.Service;
import DonationArgent.Entitys.Argent;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Login;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import com.stripe.param.PaymentIntentCreateParams;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SIRINE
 */
public class DonationArgent extends Form {

    public DonationArgent() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Donnation Argents");
        this.getToolbar().addCommandToLeftBar("logout", null, ev -> {
            new Login().showBack();});
        
        //construir la form
        TextField txtMontant = new TextField("","Montant en millimes");
        txtMontant.setConstraint(TextField.NUMERIC);
        Button btnDonner = new Button("Donner");
        
        btnDonner.addActionListener(a -> {
            
            try{
                Stripe.apiKey = "sk_test_WhRxlL2CKVEMphc4DT0v1SLw00WXkFlMym";
            
                //créer un don
                Map<String, Object> donParams = new HashMap<>();
                donParams.put("amount", parseInt(txtMontant.getText()));
                donParams.put("currency", "usd");
                donParams.put("source", "tok_mastercard");
                donParams.put(
                    "description",
                    "Avengers Mobile App Donation"
                    );

                Charge charge = Charge.create(donParams);
                
                //garder une trace dans la bdd locale
                Argent arg = new Argent(txtMontant.getText(), new SimpleDateFormat("yyyy-MM-dd").format(new Calendar().getCurrentDate()));
                Service.getInstance().ajouterArgent(arg);
                
                //afficher un msg de succes
                Dialog.show("Merci pour votre don", "votre don est effectue avec succes", "ok", "cancel");
            }catch(Exception ex){
                Dialog.show("Don non effectue", "details d erreur : "+ex, "ok", "cancel");
            }
        });
        
        this.addAll(txtMontant, btnDonner);
    }
   
}
