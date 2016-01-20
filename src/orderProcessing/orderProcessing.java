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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inventoryItem item1 = new inventoryItem("pen",2, .50);
        inventoryItem item2 = new inventoryItem("paper",1, 1.0);
        
        inventory inv = new inventory();
        inv.addItem(item1);
        inv.addItem(item2);
        
        
        
        Iterator it = inv.getItems().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            
            String name = (String) pair.getKey();
            inventoryItem item = (inventoryItem) pair.getValue();
            
            System.out.println("item : " + name + ", quantity: " + item.getQuantity() + ", cost: " + item.getCost());
            it.remove(); // avoids a ConcurrentModificationException
        }
        //inventory inv = new inventory();
    }
    
}
