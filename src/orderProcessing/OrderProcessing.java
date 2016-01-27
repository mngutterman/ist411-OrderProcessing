/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;
/**
 *
 * @author Matt
 */
public class OrderProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                        
        Customer customer1 = new Customer(0, "John",0);
        
        Order order = new Order(0,"pen",2, customer1);
        customer1.submitTransaction(order);

        //Return ret = new Return(0, "pen", 50000);
        //customer1.submitTransaction(ret);
        
        //InventoryAdjustment adj = new InventoryAdjustment(0, "pen", 2);
        //customer1.submitTransaction(adj);
        
        //Exchange exchange = new Exchange(0, "paper", "pen");
        //customer1.submitTransaction(exchange);
        
        
        System.out.println("blah");
    }
}
