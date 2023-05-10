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
            Registry reg = LocateRegistry.getRegistry("localhost", 5555);
            Calculator calc1 = (Calculator) reg.lookup("server");
            boolean flage = true;

            while (flage) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("what operation do you want ? (+ , - , * , /) ");
                String op = scanner.nextLine();

                if (!op.equals("+") && !op.equals("-") && !op.equals("/") && !op.equals("*")) {
                    System.out.println("Invalid operator !!");
                    continue;
                }

                System.out.print("How many numbers do you want to enter? ");
                int numNumbers = scanner.nextInt();
                if (numNumbers == 1) {
                    System.out.println("Enter more than one number");
                    continue;
                }
                double[] numbers = new double[numNumbers];

                int c = 0;
                while (c < numNumbers) {
                    System.out.print("Enter number " + (c + 1) + ": ");

                    try {
                        numbers[c] = scanner.nextDouble();
                        
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Enter numric value !!!");
                        continue;
                    }
                    c++;
                }

                System.out.println("Calc : " + calc1.doArithmetic(op, numbers));
                scanner.nextLine();
                System.out.println("do you want to continue  y/n");
                String flage_S = scanner.nextLine();
                if (flage_S.equalsIgnoreCase("y")) {
                    continue;
                } else if (flage_S.equalsIgnoreCase("n")) {
                    System.out.println("Bye !!");
                    flage = false;
                } else {
                    System.out.println("invalid choice so will continue");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
