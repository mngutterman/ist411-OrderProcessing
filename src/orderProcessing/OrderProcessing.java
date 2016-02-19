/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Random;


/**
 *
 * @author Matt
 */
public class OrderProcessing {

    public static final int NUM_TRANSACTIONS = 10;
    
    public static final int EXCHANGE = 1;
    public static final int INVENTORY_ADJUSTMENT = 2;
    public static final int ORDER = 3;
    public static final int RETURN = 4;

    public static Thread[] transactions = new Thread[10];

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                        
        // README:::
        // Right now I just have this working with the inventory realizing it has run out of a 
        // particular item when one user buys all the stock and another user tries to purchase the item
        //
        // So really, I have only successfully sychronized order right now.
        
        /*Customer customer1 = new Customer(0, "John",10);
        Customer customer2 = new Customer(1, "Mike",10);
        
        Order transaction1 = new Order(0,"pen",2, customer1);
        Order transaction2 = new Order(0,"paper",1, customer2);
        
//        Return transaction1 = new Return(0,"pen",2);
//        Return transaction2 = new Return(0,"pen",1);
   
//        Exchange transaction1 = new Exchange(0,"pen", "paper");
//        Exchange transaction2 = new Exchange(1,"paper", "pen");
        
//        InventoryAdjustment transaction1 = new InventoryAdjustment(0,"pen", 1);
//        InventoryAdjustment transaction2 = new InventoryAdjustment(1,"pen", -1);

        customer1.setTransaction(transaction1);
        customer2.setTransaction(transaction2);
        
        Thread thread_1 = new Thread(transaction1);
        Thread thread_2 = new Thread(transaction2);

        thread_1.setName("Thread 1");
        thread_2.setName("Thread 2");

        thread_1.start();
        thread_2.start();

        
        System.out.println("end");*/
        generateTransactions();
        runTransactions();
        
    }
    
    public static void generateTransactions()
    {
        Random rand = new Random();
        
        Customer customer = new Customer(0,"Mike",100.0);
        
        //Thread[] transactions = new Thread[10];

        for(int x = 0; x< NUM_TRANSACTIONS; x++)
        {
            int transactionType = rand.nextInt(4) + 1;
            Transaction transaction = null;
            
            switch (transactionType)
            {
                case EXCHANGE:
                    transaction = new Exchange(0,"pen", "paper");
                    //break;
                case INVENTORY_ADJUSTMENT:
                    transaction = new InventoryAdjustment(0,"pen", 1);
                    break;
                case ORDER:
                    transaction = new Order(0,"pen",2, customer);
                    break; 
                case RETURN:
                    transaction = new Return(0,"pen",2);
                    break; 
                default:
                    System.out.println("UNKONWN TRANSACTION TYPE");
                    break;
            }     
            transactions[x] = new Thread(transaction);
        }
        System.out.println("blah");
    }
    
    
    public static void runTransactions()
    {
        for(int x = 0; x< NUM_TRANSACTIONS; x++)
        {
            transactions[x].start();
        }

    }
    
    
}
