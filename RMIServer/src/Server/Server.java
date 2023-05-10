/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Server;
import RMI.Calculator;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author x66
 */
public class Server extends UnicastRemoteObject implements Calculator {

    public Server() throws RemoteException {
        super();

    }

 

    @Override
      public String doArithmetic(String operation, double[] numbers) {
    try {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            switch (operation) {
                case "+":
                    result += numbers[i];
                    break;
                case "-":
                    result -= numbers[i];
                    break;
                case "*":
                    result *= numbers[i];
                    break;
                case "/":
                    if (numbers[i] == 0) {
                        System.out.println("An error occurred: can not divide by zero");
                        return "can not divide by zero" ; 
                    }
                    result /= numbers[i];
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }
        return  String.valueOf(result);
    } catch (Exception e) {
        // Handle the exception in a way that makes sense for your use case.
        System.out.println("An error occurred: " + e.getMessage());
        return "An error occurred: " + e.getMessage() ;
    }
}




    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(5555);
            reg.rebind("server", new Server());
            System.out.println("Server Is Running");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 
}

