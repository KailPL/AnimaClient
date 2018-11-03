/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package animaclient;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;



/**
 *
 * @author Kail
 */
public class AnimaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here


        CommunicationPackiet pakiet;
       

        final Window window = new Window();
        window.addComponentListener(new ComponentAdapter() {
    public void componentResized(ComponentEvent e)
    {
        window.repaint();
    }
});

        window.show();
      
        
//        int il_obrazkow_sz=75;
//        int il_obrazkow_wys=45;
//        int[][] mapa= new int[il_obrazkow_wys][il_obrazkow_sz];
//        int i;
//        for (i=0; i<il_obrazkow_wys; i++){
//            int j;
//             for (j=0; j<il_obrazkow_sz; j++)
//             {
//                 mapa[i][j]=2;
//             }
//         }
//         mapa[20][20]=mapa[21][20]=mapa[20][21]=mapa[21][21]=mapa[21][22]=1;  
//
//         
//        window.Remap(mapa);
//        window.repaint();

        


    }

}
