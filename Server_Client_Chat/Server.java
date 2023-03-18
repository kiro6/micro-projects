/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author x66
 */
public class Server {
    int port;
    ServerSocket serverSocket = null;
    Socket socket = null;
    ExecutorService execService = null;
    int clientcount = 0;

    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
        server.startServer();
    }

    Server(int pt) {
        this.port = pt;
        execService = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {

        serverSocket = new ServerSocket(5000);
        System.out.println("S_Class Started....");
        System.out.println("To break the connection send BYE....");
        while (true) {
            socket = serverSocket.accept();
            clientcount++;
            ServerThread st = new ServerThread(socket, clientcount, this);
            execService.execute(st);
        }

    }

    private static class ServerThread implements Runnable {

        Server server = null;
        Socket client = null;
        BufferedReader clientReader;
        PrintStream serverSender;
        BufferedReader serverReader ; 
        int id;
        String message;

        ServerThread(Socket client, int count, Server server) throws IOException {

            this.client = client;
            this.server = server;
            this.id = count;
            System.out.println("Connection established with client " + id);

            clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            serverSender = new PrintStream(client.getOutputStream());
            serverReader= new BufferedReader(new InputStreamReader(System.in));

        }
        
        String calc(String eq) {
            String parts[];
            CharSequence plus = "+";
            CharSequence min = "-";
            CharSequence mult = "*";
            CharSequence div = "/";
            int x;
            int y;
            int res ; 

            if (eq.contains(plus)) {
                parts = eq.split("\\+");
                x = Integer.valueOf(parts[0]);
                y = Integer.valueOf(parts[1]);
                res = x+y ;
                
                return  String.valueOf(res) ; 
            }

            else if (eq.contains(min)) {
                parts = eq.split("\\-");
                x = Integer.valueOf(parts[0]);
                y = Integer.valueOf(parts[1]);
                res = x-y ;
                
                return  String.valueOf(res) ; 
            }

            else if (eq.contains(mult)) {
                parts = eq.split("\\*");
                x = Integer.valueOf(parts[0]);
                y = Integer.valueOf(parts[1]);
                res = x*y ;
                
                return  String.valueOf(res) ; 
            }

            else if (eq.contains(div)) {
                parts = eq.split("\\/");
                x = Integer.valueOf(parts[0]);
                y = Integer.valueOf(parts[1]);
                res = x/y ;
                
                return  String.valueOf(res) ; 
            }else{
                String warn = "Enter valid equation" ; 
                return warn  ; 
            }
            
            

        }

        @Override
        public void run() {
            int x = 1;
            try {
                while (true) {
                    message = clientReader.readLine();
                    if(message.equalsIgnoreCase("calc")){
                         serverSender.println("Enter equation");
                         message = clientReader.readLine();
                         serverSender.println("result : "+calc(message)) ; 
                         continue;
                    }

                    if(message.equalsIgnoreCase("bye")){
                    break;
                    }

                    System.out.print("Client(" + id + ") : " + message + "\n");
                    System.out.print("S_Class : ");
                    message = serverReader.readLine();
                    if (message.equalsIgnoreCase("bye")) {
                        serverSender.println("BYE");
                        x = 0;
                        System.out.println("Connection Ended....");
                        break;
                    }
                    serverSender.println(message);
                }

                clientReader.close();
                client.close();
                serverSender.close();
                if (x == 0) {
                    System.out.println("*****Closing*****");
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Error : " + e);
            }
        }
    }
}
