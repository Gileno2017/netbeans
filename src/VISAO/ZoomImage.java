package VISAO;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.SwingUtilities;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.nodes.PImage;

public class ZoomImage extends PFrame implements Serializable{

    public ZoomImage() {
        super("Zoom Mapa", false, null);
        setSize(1320, 920);
        setLocationRelativeTo(null);
          setDefaultCloseOperation(HIDE_ON_CLOSE);
      
    }

    public void initializeImagem(BufferedImage img) {

        PImage imageNode = new PImage(img);
        getCanvas().getLayer().addChild(imageNode);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ZoomImage().setVisible(true);
                
            }
        });
    }
}
