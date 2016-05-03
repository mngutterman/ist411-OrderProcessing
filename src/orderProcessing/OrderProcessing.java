/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.awt.Font;
import java.util.Random;
import javax.swing.*;        



/**
 *
 * @author Matt
 */
public class OrderProcessing {

    /*public static final int NUM_TRANSACTIONS = 100000;
    public static final int NUM_TRANSACTION_TYPES = 4;
    
    public static final int EXCHANGE = 1;
    public static final int INVENTORY_ADJUSTMENT = 2;
    public static final int ORDER = 3;
    public static final int RETURN = 4;

    public static Thread[] transactions = new Thread[NUM_TRANSACTIONS];
    */
    private static Customer customer = new Customer(0,"Mike",100.0);

    
    /**
     * @param args the command line arguments
     */
             
    
    
        
             
    /*public static void main(String[] args) {
        
        generateTransactions();
        runTransactions();
    }*/
    
    public static void submitBuyTransaction(String itemName, int itemQuantity)
    {
        Transaction transaction = new Order(0, itemName, itemQuantity, customer);
        Thread thread = new Thread(transaction);
        thread.start();
        
    }
    
    public static void submitReturnTransaction(String itemName, int itemQuantity)
    {
        Transaction transaction = new Return(0, itemName, itemQuantity);
        Thread thread = new Thread(transaction);
        thread.start();
    }
    
    public static void submitInventoryAdjustmentTransaction(String itemName, int itemQuantity)
    {
        Transaction transaction = new InventoryAdjustment(0, itemName, itemQuantity);
        Thread thread = new Thread(transaction);
        thread.start();
    }
    
    public static void submitExchangeTransaction(String itemName_1, String itemName_2)
    {
        Transaction transaction = new Exchange(0, itemName_1, itemName_2);
        Thread thread = new Thread(transaction);
        thread.start();
    }
    
    /*public static void generateTransactions()
    {
        Random rand = new Random();
        
        //Customer customer = new Customer(0,"Mike",100.0);
                
        for(int x = 0; x< NUM_TRANSACTIONS; x++)
        {
            int transactionType = rand.nextInt(NUM_TRANSACTION_TYPES) + 1;
            Transaction transaction = null;
            
            switch (transactionType)
            {
                case EXCHANGE:
                    transaction = new Exchange(x,"pen", "paper");
                    break;
                case INVENTORY_ADJUSTMENT:
                    transaction = new InventoryAdjustment(x,"pen", 1);
                    break;
                case ORDER:
                    transaction = new Order(x,"pen",2, customer);
                    break; 
                case RETURN:
                    transaction = new Return(x,"pen",2);
                    break; 
                default:
                    System.out.println("UNKONWN TRANSACTION TYPE");
                    break;
            }     
            transactions[x] = new Thread(transaction);
        }
    }
    
    public static void runTransactions()
    {
        for(int x = 0; x< NUM_TRANSACTIONS; x++)
        {
            transactions[x].start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // handle the exception...        
                // For example consider calling Thread.currentThread().interrupt(); here.
            }
        }
    }*/
    
    
}
