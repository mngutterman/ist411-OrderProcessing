/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mdr5325
 */
public class Order extends Transaction {
        
    public static final String YES = "y";
    
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    private Customer customer;
    
    /*public Order(int id) {
        super(id);
    }*/
    
    public Order(int id, String _itemName, int _quantity, Customer _customer) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
        this.customer = _customer;
    }
    
    @Override
    public void process()
    {
        Map<String, InventoryItem> items = inventory.getItems();
        InventoryItem item = items.get(itemName);
        
        if (item != null) {
            // this must be synchronized because if a different customer buys this item mid order,
            // you will still assume the item has it's original quantity
            synchronized(item){

                // item exists in the inventory
                int stockOfItem = item.getQuantity();

                if (stockOfItem >= this.quantity)
                {
                    // create a sale
                    System.out.println("initiating sale of " + itemName);
                    Sale sale = new Sale(0,item.getName(),quantity, customer);
                    sale.process();
                }
                else if (stockOfItem > 0)
                {
                    // not enough in stock to fullfill order
                    Scanner reader = new Scanner(System.in);  // Reading from System.in
                    System.out.println("There is only " + stockOfItem + " " + itemName + "(s) left. Would you like to purchase that amount? (y/n): ");
                    String response = reader.nextLine().toLowerCase();

                    if (response.equals(YES))
                    {
                        System.out.println("initiating sale of " + itemName);
                        Sale sale = new Sale(0,item.getName(),stockOfItem, customer);
                        sale.process();
                    }
                    else
                    {
                        System.out.println("Thanks anyways!");
                    }                

                } 
                else
                {
                    System.out.println("We are currently all out of this item, sorry.");
                }

            } 
        }
        else 
        {
                // item does not exist in the inventory
                System.out.println("We do not carry this item, sorry.");
        }
    }
}