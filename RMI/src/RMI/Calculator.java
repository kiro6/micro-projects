/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RMI;
import java.rmi.*;
/**
 *
 * @author x66
 */
public interface Calculator extends Remote {

    public int add(int n, int m) throws RemoteException;
    
    public String doArithmetic(String operation, double[] numbers) throws RemoteException;
}
