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
public class Sale extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    
    public Sale(int id, String _itemName, int _quantity) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
    }
    
    @Override
    public void process()
    {
        Map<String, InventoryItem> items = inventory.getItems();
        InventoryItem item = items.get(itemName);
        
        // TODO:Add item to user cart
        
        
        item.decreaseQuantityBy(this.quantity);
                
       

    }
}
