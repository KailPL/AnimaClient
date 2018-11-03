/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kail
 */
public class InThread extends Thread {
 
    ObjectInputStream in;
    CommunicationPackiet pakiet;
    public InThread(ObjectInputStream inFromServer){
    in = inFromServer;
    
    }
        public synchronized void run(){

     while(true) {  
           
            try {
                pakiet = (animaclient.CommunicationPackiet) in.readObject();
                System.out.println("Przyszedl pakiet:");
                pakiet.Info();
                Panel.In(pakiet);
                //while (Panel.In(pakiet)==1) ;
            } catch (IOException ex) {
                Logger.getLogger(InThread.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                
                Logger.getLogger(InThread.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
     }
        
    }
    
}
