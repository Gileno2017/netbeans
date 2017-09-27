/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cprm.gov.gileno.macedo.MODELO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author gileno.macedo
 */
public class ManipulaBuffImage {
     // metodo responsavel pelo leitura de uma imagem e transforamação de um vetor de bytes para armazenar imagem no banco de dados
         
      public static byte[] getImgBytes(BufferedImage image) {
            
         
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException ex) {      
        }        
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
   
        return baos.toByteArray();
    }
      
      
      public static void exibiImagemLabelCadastro(byte[] minhaimagem, javax.swing.JLabel label){
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO
       
        if(minhaimagem!=null)
        {
          InputStream input = new ByteArrayInputStream(minhaimagem);
            try {
                       BufferedImage image = ImageIO.read(input);           
                       label.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
            }        
        }
        else
        {
            label.setIcon(null);
            
        }

      }
    
}
