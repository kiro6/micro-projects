/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author x66
 */

public class Client {

    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        PrintStream clientSender = new PrintStream(socket.getOutputStream());
        
        BufferedReader clientStdin = new BufferedReader(new InputStreamReader(System.in));
        
        String str;
        
        while (true) {
        
            System.out.print("Client : ");
            
            str = clientStdin.readLine();
            
            clientSender.println(str);
            
            if (str.equalsIgnoreCase("BYE")) {
                System.out.println("Connection Ended.....");
                break;
            }
            str = serverReader.readLine();
            System.out.print("Server : " + str + "\n");

        }
        socket.close();
        serverReader.close();
        clientSender.close();
        clientStdin.close();
    }

}

