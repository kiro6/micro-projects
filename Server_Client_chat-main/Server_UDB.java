/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server_udb;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author x66
 */
public class Server_UDB {

    public final static int PORT = 2020;
    private final static int BUFFER = 64535;
    Scanner scanner = new Scanner(System.in);

    private DatagramSocket socket;
 

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Server_UDB() throws IOException {
        socket = new DatagramSocket(PORT);
        System.out.println("Server is running and is listening on port " + PORT);
        System.out.println("Enter Bye to ShutDown Server !!!");
     
    }

    public void run() {
        byte[] bufferRes = new byte[BUFFER];
        byte[] bufferSend = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(bufferRes, (byte) 0);
                Arrays.fill(bufferSend, (byte) 0);
                DatagramPacket packetRes = new DatagramPacket(bufferRes, bufferRes.length);
                DatagramPacket packetSend = null;

                socket.receive(packetRes);
                InetAddress clientAddress = packetRes.getAddress();
                int client_port = packetRes.getPort();
                String messageOut = null; 
                String messageIn = String.valueOf(data(packetRes.getData()));
                String id = clientAddress.toString() + "|" + client_port;
                System.out.println(id + ": " + messageIn);

                if (messageIn.equalsIgnoreCase("calc")) {

                    
                    Arrays.fill(bufferSend, (byte) 0);
                    messageOut = "Enter equation ";
                    System.out.println(messageOut);
                    bufferSend = messageOut.getBytes();
                    packetSend = new DatagramPacket(bufferSend, bufferSend.length, clientAddress, client_port);
                    socket.send(packetSend);
                   
                    Arrays.fill(bufferRes, (byte) 0);
                    packetRes = new DatagramPacket(bufferRes, bufferRes.length);
                    socket.receive(packetRes);
                    messageIn = String.valueOf(data(packetRes.getData()));
                    System.out.println(id + ": " + messageIn);
                    clientAddress = packetRes.getAddress();
                    client_port = packetRes.getPort();

                    packetSend = null ; 
                    Arrays.fill(bufferSend, (byte) 0);
                    messageOut = calc(messageIn);
                    System.out.println( "Server: "+messageOut);
                    byte[] bufferSendd = messageOut.getBytes();
                    packetSend = new DatagramPacket(bufferSendd, bufferSendd.length, clientAddress, client_port);
                    socket.send(packetSend);

                    continue;
                }

                System.out.print("Server: ");
                messageOut = scanner.nextLine();
                bufferSend = messageOut.getBytes();
                packetSend = new DatagramPacket(bufferSend, bufferSend.length, clientAddress, client_port);
                socket.send(packetSend);
                
                
                if (messageOut.equalsIgnoreCase("bye")) {
                    System.out.println("Server ShutDown Successfully !!!!");
                    socket.close();
                    break;
                }

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        Server_UDB server_thread = new Server_UDB();
        server_thread.run();
    }

    String calc(String eq) {
        String parts[];
        CharSequence plus = "+";
        CharSequence min = "-";
        CharSequence mult = "*";
        CharSequence div = "/";
        int x;
        int y;
        int res;

        if (eq.contains(plus)) {
            parts = eq.split("\\+");
            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);
            res = x + y;

            return String.valueOf(res);
        } else if (eq.contains(min)) {
            parts = eq.split("\\-");
            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);
            res = x - y;

            return String.valueOf(res);
        } else if (eq.contains(mult)) {
            parts = eq.split("\\*");
            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);
            res = x * y;

            return String.valueOf(res);
        } else if (eq.contains(div)) {
            parts = eq.split("\\/");
            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);
            res = x / y;

            return String.valueOf(res);
        } else {
            String warn = "Enter valid equation";
            return warn;
        }

    }

    public static StringBuilder data(byte[] a) {
        if (a == null) {
            return null;
        }
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
