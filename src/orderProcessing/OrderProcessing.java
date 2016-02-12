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
                        
        // README:::
        // Right now I just have this working with the inventory realizing it has run out of a 
        // particular item when one user buys all the stock and another user tries to purchase the item
        //
        // So really, I have only successfully sychronized order right now.
        
        Customer customer1 = new Customer(0, "John",10);
        Customer customer2 = new Customer(1, "Mike",10);
        
        //Order transaction1 = new Order(0,"pen",2, customer1);
        //Order transaction2 = new Order(0,"paper",1, customer2);
        
        //Return transaction1 = new Return(0,"pen",2);
        //Return transaction2 = new Return(0,"pen",1);
   
        //Exchange transaction1 = new Exchange(0,"pen", "paper");
        //Exchange transaction2 = new Exchange(1,"paper", "pen");
        
        InventoryAdjustment transaction1 = new InventoryAdjustment(0,"pen", 1);
        InventoryAdjustment transaction2 = new InventoryAdjustment(1,"pen", -1);
        
        customer1.setTransaction(transaction1);
        customer2.setTransaction(transaction2);
        
        Thread thread_1 = new Thread(customer1);
        Thread thread_2 = new Thread(customer2);

        thread_1.setName("Thread 1");
        thread_2.setName("Thread 2");

        thread_1.start();
        thread_2.start();

        
        System.out.println("end");
    }
}
