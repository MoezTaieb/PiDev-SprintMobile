/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import Event.gui.qrcode.ErrorCorrectionLevel;
import Event.gui.qrcode.QRCode;
import Event.gui.qrcode.QREncoder;


/**
 *
 * @author Moez
 */
public class Qrcode {
    

    
    public static Image qrcode(String s)
    {
  
         Container cnt1 = new Container(BoxLayout.y());
      
        QRCode email = QREncoder.encode(s, ErrorCorrectionLevel.H);
        byte[][] bm = email.getMatrix().getArray();
        
        int d = bm.length; //Dimension in pixel
        int[] a = new int[d * d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                a[i * d + j] = ((bm[i][j] - 1) & 0x00FFFFFF) | 0x99000000; //-> Appropriate transparency
            }
        }
        Image img = Image.createImage(a, d, d);
       
   return img;
    }
    
}
