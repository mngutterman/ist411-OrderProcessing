/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author mdr5325
 */
public class Exchange extends Transaction{
    Inventory inventory = Inventory.getInstance();
    PointOfSaleUI ui = PointOfSaleUI.getInstance();

    private String customerItemName;   // What they have
    private String inventoryItemName;  // What they want
    
    public Exchange(int id, String _customerItemName, String _inventoryItemName) {
        super(id);
        this.customerItemName = _customerItemName;
        this.inventoryItemName = _inventoryItemName;
    }
    
    @Override
    public void process()
    {
        this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - Start Transaction");
        
        Map<String, InventoryItem> items = inventory.getItems();
        
        // If we do not carry the item, cancel the transaction
        InventoryItem inventoryItem = items.get(inventoryItemName);
        
        
        if (inventoryItem != null) {
            InventoryItem customerItem = items.get(customerItemName);
            if (customerItem != null)
            {
                InventoryItem firstItem;
                InventoryItem secondItem;
                
                // set an order based on id to avoid deadlock
                if (inventoryItem.getId() == Math.min(inventoryItem.getId(), customerItem.getId())) 
                {
                    firstItem = inventoryItem;
                    secondItem = customerItem;
                }
                else
                {
                    firstItem = customerItem;
                    secondItem = inventoryItem;
                }
                
                synchronized(firstItem)
                {
                    synchronized(secondItem)
                    {
                        if (inventoryItem.getQuantity() > 0)
                        {
                            inventoryItem.decreaseQuantityBy(1);
                            customerItem.increaseQuantityBy(1);
                                                
                            ui.sendAlertMessage("successfully exchanged item: " + customerItem.getName() + " for item: " + inventoryItem.getName());
                            this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - " + inventoryItem.getName() + " decreased by 1");
                            this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - " + customerItem.getName() + " increased by 1");
                        }
                        else
                        {
                            this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - Not Enough Inventory In Stock. Exchange Canceled");
                            ui.sendAlertMessage("not enough inventory in stock. transaction canceled");
                            
                                                       
                        }
                    }
                }

                
            }
            else
            {
                ui.sendAlertMessage("we do not carry " + customerItemName + ". transaction canceled");
                this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - Do Not Carry Item. Exchange Canceled");
            }
        } else {
            // item does not exist in the inventory
            ui.sendAlertMessage("we do not carry " + inventoryItemName + ". transaction canceled");
            this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - Do Not Carry Item. Exchange Canceled");
        
        }
        this.log("TRANSACTION " + this.getTransactionID() + " - EXCHANGE - End Transaction");
    }
    
    @Override
    public void run() {
        this.process();
    }
    
}
