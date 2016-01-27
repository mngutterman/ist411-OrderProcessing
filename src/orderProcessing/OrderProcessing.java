/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Matt
 */
public class OrderProcessing {

    /*
    
    Order : check inventory -> tell it to make a sale (generate sale)
    Sale: make sale (don't check inventory?)
    */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Inventory inv = Inventory.getInstance();
                
        Customer customer1 = new Customer(0, "John");
        
        //Order order = new Order(0,"pen",1, customer1);
        //customer1.submitTransaction(order);

        //Return ret = new Return(0, "pen", 5);
        //customer1.submitTransaction(ret);
        
        //InventoryAdjustment adj = new InventoryAdjustment(0, "pen", 2);
        //customer1.submitTransaction(adj);
        
        Exchange exchange = new Exchange(0, "paper", "pen");
        customer1.submitTransaction(exchange);
        
        
        System.out.println("blah");
        
        
    }
}
