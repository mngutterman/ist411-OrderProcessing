/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Matt
 */
public class orderProcessing {

    /*
    
    Order : check inventory -> tell it to make a sale (generate sale)
    Sale: make sale (don't check inventory?)
    */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //populateInventoryWithItems();
        inventory inv = new inventory();
        inv.populateInventoryWithItems();
        
        
        // this will iterate over all the elements of the inventory hashMap
        Iterator it = inv.getItems().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            
            String name = (String) pair.getKey();
            inventoryItem item = (inventoryItem) pair.getValue();
            
            System.out.println("item : " + name + ", quantity: " + item.getQuantity() + ", cost: " + item.getCost());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
