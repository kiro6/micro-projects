/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calc;

/**
 *
 * @author x66
 */

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path ("api")
public class CalcApi {



@GET
@Path("/calc")
@Produces(MediaType.TEXT_HTML)
public String modifyString(@QueryParam("input") String input) {
    try {
            String[] tokens = input.trim().split("\\s+");
            double result = Double.parseDouble(tokens[0]);
            String.valueOf(result) ;

            for (int i = 1; i < tokens.length - 1; i += 2) {
                String operation = tokens[i];
                double number = Double.parseDouble(tokens[i + 1]);

                switch (operation) {
                    case "+":
                        result += number;
                        break;
                    case "-":
                        result -= number;
                        break;
                    case "*":
                        result *= number;
                        break;
                    case "/":
                        if (number == 0) {
                            return "An error occurred: cannot divide by zero";
                        }
                        result /= number;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
            return String.valueOf(result);
        } catch (Exception e) {
            return "An error occurred: " + e;
        }
    
    
}


}
