/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.geom.*;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 *
 * @author Kail
 */
public class Panel extends JPanel {
    
    
    Point2D poczatek;
    ArrayList linie;
    char znakKlawisza;
    int kodKlawisza;
    int d=5,j,i;
    
    Toolkit tool=Toolkit.getDefaultToolkit();
    Dimension ekran=tool.getScreenSize();
    int szer=(int)(super.getWidth()*0.85);
    int wys=(int)(super.getHeight()*0.85);
    int test= super.getHeight();
            int il_obrazkow_sz=150;
        int il_obrazkow_wys=90;
    static int[][] mapa;
    static Panel ten;
    String imag=null;
    
    //syglad
    
       JTextArea oknochatu = new JTextArea("TEST");
    
         
    //pola connecowe
    Socket socket;
    ObjectOutputStream outToServer;
    ObjectInputStream inFromServer;
    CommunicationPackiet pakiet;
    public static int In(CommunicationPackiet InPakiet){
        
        System.out.println("odebrano pakiet");
        
        if (InPakiet.type==5)
            mapa = (int[][])InPakiet.objekt;
        ten.repaint();
        
    return 1;
    }
    Panel(){

        mapa = new int[il_obrazkow_wys][il_obrazkow_sz];

       
        try {
            socket= new Socket("127.0.0.1",2030);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer  = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        

         for (i=0; i<il_obrazkow_wys; i++){
             for (j=0; j<il_obrazkow_sz; j++)
             {
                 mapa[i][j]=0;
             }
         }
        UchwytKlawisza sluchacz=new UchwytKlawisza();
        addKeyListener(sluchacz);
        SendPakiet(1,null);
        InThread test = new InThread(inFromServer);
        test.start();
        
//        JPanel inpanel = new JPanel();
//        inpanel.setBounds(500, 500, 100, 200);
//        inpanel.setBackground(Color.red);
//        inpanel.setVisible(true);
        
        ten=this;
     
        oknochatu.setBackground(Color.CYAN);
        
         this.add(oknochatu);
         this.setLayout(null);

         
         
         
         
         
    }
    @Override
    public boolean isFocusTraversable(){
        return true;
    }
    public void Remap(int map[][]){
     
        for (int i=0; i<il_obrazkow_wys; i++){
             for (int j=0; j<il_obrazkow_sz; j++)
             {
                 mapa[i][j]=map[i][j];
             }
         }
    repaint();    
    }
    public void SendPakiet(int typ, Object obj){
            
        switch (typ) {
            case 1:
                pakiet = new CommunicationPackiet("Pakiet testowy od klijenta");
                break;
            case 101:
                pakiet = new CommunicationPackiet(typ);
                break;
            case 102:
                pakiet = new CommunicationPackiet(typ);
                break;
            case 103:
                pakiet = new CommunicationPackiet(typ);
                break;
            case 104:
                pakiet = new CommunicationPackiet(typ);
                break;                
                
            default:
                System.out.println("Błędny typ pakietu");
                break;
        }
//            pakiet = new CommunicationPackiet(typ,obj);
            //pakiet.Info();
        try {
            outToServer.writeObject(pakiet);
        } catch (IOException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("dziala");
        repaint();
    }
    @Override
    protected  void paintComponent( Graphics g ){
         super.paintComponent( g );
         ekran=tool.getScreenSize();
         szer=(int)(super.getWidth()*0.85);
         wys=(int)(super.getHeight()*0.85);
         oknochatu.setBounds(0, wys, szer, (int)(super.getWidth()*0.15));
         
         Image im = getToolkit().getImage("src/trawa2.png");
         Image im2 = getToolkit().getImage("src/tlo.jpg");
         g.drawImage(im2, 0, 0, (int)(super.getWidth()), (int)(super.getHeight()), this);
         //na szer 60 obraskow robi robote, 80 nie

         int posx=0,posy=0,rx,ry;
         rx=(int)(super.getWidth()*0.85/il_obrazkow_wys);
         ry=(int)(super.getHeight()*0.85/il_obrazkow_sz);
         
         for (i=0; i<il_obrazkow_wys; i++){
             for (j=0; j<il_obrazkow_sz; j++)
             {
                 //g.drawRect(posx, posy, rx, ry);
                     imag="src/"+mapa[i][j]+".png";
                     im = getToolkit().getImage(imag);

                 g.drawImage(im, posx, posy, rx, ry, this);
                 posx+=rx;
                 rx=(int)((szer-posx)*((double)1/(il_obrazkow_sz-1-j)));

                 
                 //g.drawRect(i*(int)(super.getWidth()*0.85()), j*(int)(super.getHeight()*0.0085), (int)(super.getWidth()*0.0085), (int)(super.getHeight()*0.0085));
             }
             posy+=ry;
             ry=(int)((wys-posy)*((double)1/(il_obrazkow_wys-1-i)));
             rx=(int)(super.getWidth()*0.85/il_obrazkow_sz);
             posx=0;
         }
         g.drawRect(0,0,(int)(super.getWidth()*0.85), (int)(super.getHeight()*0.85));
         
     }
    
 
        private class UchwytKlawisza implements KeyListener{
        public void keyPressed(KeyEvent zdarzenie) {
            kodKlawisza=zdarzenie.getKeyCode();
//            if(zdarzenie.isShiftDown()) SendPakiet();
//            else SendPakiet();
            if(kodKlawisza==KeyEvent.VK_LEFT) SendPakiet(101,null); 
            if(kodKlawisza==KeyEvent.VK_RIGHT) SendPakiet(103,null); 
            else if(kodKlawisza==KeyEvent.VK_DOWN) SendPakiet(102,null); 
            else if(kodKlawisza==KeyEvent.VK_UP) SendPakiet(104,null); 
        }
        public void keyReleased(KeyEvent e) {
        }
        public void keyTyped(KeyEvent zdarzenie) {
            znakKlawisza=zdarzenie.getKeyChar();
            if(Character.isUpperCase(znakKlawisza)){
            d*=10;
            znakKlawisza = Character.toLowerCase(znakKlawisza);
            }
            if(kodKlawisza==KeyEvent.VK_A) SendPakiet(101,null); 
            else if(kodKlawisza==KeyEvent.VK_D) SendPakiet(103,null); 
            else if(kodKlawisza==KeyEvent.VK_S) SendPakiet(102,null); 
            else if(kodKlawisza==KeyEvent.VK_W) SendPakiet(104,null);
            else if(kodKlawisza==KeyEvent.VK_C) repaint(); 
        }
        }
    
    

}
