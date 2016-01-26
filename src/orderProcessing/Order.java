/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mdr5325
 */
public class Order extends Transaction {

    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    
    /*public Order(int id) {
        super(id);
    }*/
    
    public Order(int id, String _itemName, int _quantity) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
    }
    
    @Override
    public void process()
    {
        Map<String, InventoryItem> items = inventory.getItems();
        
        InventoryItem item = items.get(itemName);
        if (item != null) {
            // item exists in the inventory
            int stockOfItem = item.getQuantity();
            
            if (stockOfItem >= this.quantity)
            {
                // create a sale
                System.out.println("SALE");
                Sale sale = new Sale(0,item.getName(),quantity);
                sale.process();
                
            }
            else 
            {
                // not enough in stock to fullfill order
                System.out.println("ONLY " + stockOfItem + " LEFT, SORRY");
                
                
                
            }
            
        } else {
            // item does not exist in the inventory
            System.out.println("WE ARE OUT OF THIS ITEM");
        }

    }
}