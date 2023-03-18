/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.client_udb;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author x66
 */
public class Client_UDB {

    public static void main(String[] args)  {
        
        
        Scanner sc = new Scanner(System.in);
        try{
            
        InetAddress ip = InetAddress.getByName("localhost");    
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] receive = new byte[64535];
        byte[] send = new byte[64535];
        String messageIn;
        String messageOut;
  
        
 
        while (true)
        {
            
                Arrays.fill(send, (byte) 0);
                Arrays.fill(receive, (byte) 0);
            
            System.out.print("Client: ");
            messageOut = sc.nextLine();
  
            send = messageOut.getBytes();
  
           DatagramPacket DpSend = new DatagramPacket(send, send.length, ip , 2020);
           datagramSocket.send(DpSend);
           
            if (messageOut.equalsIgnoreCase("bye")){
                System.out.println("Client Side Closed Succesfully !!!!");
                datagramSocket.close();
                break;
            }
  
     
            DatagramPacket DpReceive  = new DatagramPacket(receive , receive.length);
            datagramSocket.receive(DpReceive);
            messageIn =  "Server: "+String.valueOf( data(receive)) ;
            System.out.println(messageIn);
              
            if (messageIn.equalsIgnoreCase("bye")){
                System.out.println("Connection Closed by Server Succesfully !!!!");
                datagramSocket.close();
                break;
            }
                
        }
        
        }catch(Exception c ){
                System.out.println(c);
        }
        
      
  
     
    }
    
    
    
       public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
