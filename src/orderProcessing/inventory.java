/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author Matt
 */
public class inventory {

    private Map<String, inventoryItem> items = new HashMap<String, inventoryItem>(); 
    
    public inventory(){
         
    }
    
    public inventory(HashMap<String, inventoryItem> _items)
    {
        items = _items;
    }
    
    public Map<String, inventoryItem> getItems()
    {
        return items;
    }
    
    public void addItem(inventoryItem _item)
    {
        String itemName = _item.getName();
        int itemQuantity = _item.getQuantity();
        double itemCost = _item.getCost();
                
        inventoryItem item = items.get(itemName);
        
        if (item != null) {
            item.increaseQuantityBy(itemQuantity);
        }
        // item has not been created in our hashMap. create it and set its quantity / cost
        else 
        {
            item = new inventoryItem(itemName, itemQuantity, itemCost);
            items.put(itemName,item);
        }
    }
    
    
    public void populateInventoryWithItems()
    {
        inventoryItem item1 = new inventoryItem("pen",2, .50);
        inventoryItem item2 = new inventoryItem("paper",1, 1.0);
        
        this.addItem(item1);
        this.addItem(item2);
        
        
    }
    
}
