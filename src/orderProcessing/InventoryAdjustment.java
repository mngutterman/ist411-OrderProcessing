/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Map;

/**
 *
 * @author mdr5325
 */
public class InventoryAdjustment extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    
    public InventoryAdjustment(int id, String _itemName, int _quantity) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
    }
    
    @Override
    public synchronized void process()
    {
        System.out.println("TRANSACTION " + this.getTransactionID() + " - INVENTORY ADJUSTMENT - Start Transaction");
        
        Map<String, InventoryItem> items = inventory.getItems();
        
        // If we do not carry the item, cancel the transaction
        InventoryItem item = items.get(itemName);
        if (item != null) {
            synchronized(item)
            {
                if (item.getQuantity() + quantity >= 0)
                {
                    item.increaseQuantityBy(this.quantity);
                    System.out.println("TRANSACTION " + this.getTransactionID() + " - INVENTORY ADJUSTMENT - " + item.getName() + " adjusted by " + this.quantity);
                }
                else
                {
                    System.out.println("TRANSACTION " + this.getTransactionID() + " - INVENTORY ADJUSTMENT - Cannot Have Negative Inventory. Transaction Canceled");
                }
            }
        } else {
            // item does not exist in the inventory
            System.out.println("TRANSACTION " + this.getTransactionID() + " - INVENTORY ADJUSTMENT - Do Not Carry Item. Inventory Adjustment Canceled");
        }
        
        System.out.println("TRANSACTION " + this.getTransactionID() + " - INVENTORY ADJUSTMENT - End Transaction");

    }
    
    @Override
    public void run() {
        this.process();
    }
    
}