/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import RMI.Calculator;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author x66
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1022);
            Calculator calc1 = (Calculator) reg.lookup("server");
            //System.out.println("Calc : " + calc.add(5, 5));

            Scanner scanner = new Scanner(System.in);
            
             System.out.print("what operation do you want ? \n+\n-\n*\n/\n");
             String op = scanner.nextLine();
            

            System.out.print("How many numbers do you want to enter? ");
            int numNumbers = scanner.nextInt();

            double[] numbers = new double[numNumbers];

            for (int i = 0; i < numNumbers; i++) {
                System.out.print("Enter number " + (i + 1) + ": ");
                numbers[i] = scanner.nextDouble();
            };

            System.out.println("Calc : " + calc1.doArithmetic(op, numbers));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
