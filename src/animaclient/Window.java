/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaclient;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author Kail
 */
public class Window extends JFrame{
    
        //szerokość ekranu
    Toolkit tool=Toolkit.getDefaultToolkit();
    Dimension ekran=tool.getScreenSize();
    int szer=(int)ekran.getWidth();
    int wys=(int)ekran.getHeight();
    //Dodanie panelu
    Panel panel=new Panel();
    Container powZawartosci=getContentPane();
    
    public void Remap(int map[][]){
    //panel.Remap(map);
    }
    public void Connect()throws IOException{
    //        String IPServer = "91.192.90.174";
        Socket socket= new Socket("127.0.0.1",2030);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer  = new ObjectInputStream(socket.getInputStream());
            BufferedReader klawiatura = new BufferedReader(new InputStreamReader(System.in));
            String a = "t";
            CommunicationPackiet pakiet;
////            while (socket.isConnected()) //PAMIETAC O TYM!
//            
            while (!a.equals("/quit")){
            if (klawiatura.ready()){
            a = klawiatura.readLine();
            pakiet = new CommunicationPackiet(a);
            pakiet.Info();
            outToServer.writeObject(pakiet);
            }
        }    

        
//        BufferedReader klawiatura = new BufferedReader(new InputStreamReader(System.in));
//        
//        String a = null; 
//        while (a==null){
//        a=klawiatura.readLine();
//        }
//        String message=null;
//        
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//       PrintStream out= new PrintStream(socket.getOutputStream());
//       out.println(a);

       while (!a.equals("/quit")){
            if (klawiatura.ready()){
            a = klawiatura.readLine();
            pakiet = new CommunicationPackiet(a);
            pakiet.Info();
            outToServer.writeObject(pakiet);
            }
//            while(bufferedReader.ready()){
//            while ((message = bufferedReader.readLine())!=null){
//                     System.out.println(message);
//                     break;
//                     /////////////////////////////////////////to jestkiepskie, poprawic
//            }}
       }
       socket.close();
       
    
    
    }
    
    //Wygląd okna
    public Window() throws IOException{
//        setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(szer,wys);
//        setTitle("Anima");
        Image im = getToolkit().getImage("src/Logo.png");
        setIconImage(im);
        powZawartosci.add(panel);
        
//        setResizable(false);
    }
  
    
    


}
