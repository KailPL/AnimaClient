/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaclient;

/**
 
  @author Kail
  0    blank
  1    testowy
  2    logowania
  3    zwrotny logowania
  4    ruch
  5    mapa
  * 
  7 
  * 
  101 - ruch w lewo
  102 - ruch w dol
  103 - ruch w prawo
  104 - ruch w w gore
  * 
  * 
  * 
  * 
  * 
 **/
import java.io.Serializable;

public class CommunicationPackiet implements Serializable{
int type=0;
Object objekt;
public int getType(){
return type;
}
public CommunicationPackiet(int typ, int integer){

    objekt = integer;
    type=typ;

}
public CommunicationPackiet(int typ){
    type=typ;

}


public CommunicationPackiet(String msg){

    objekt = msg;
    type=1;

}
public CommunicationPackiet( int[][] t){

    objekt = t;
    type=5;

}

public void Info(){
System.out.println("Pakiet o tpie:"+type+" i stringu:"+objekt);

}


}
