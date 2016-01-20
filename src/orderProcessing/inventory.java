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
 * @author Matt
 */
public class inventory {
    //private ArrayList<inventoryItem> elements
    private Map<String, inventoryItem> items = new HashMap<String, inventoryItem>();
    
    public inventory(HashMap<String, inventoryItem> _items)
    {
        items = _items;
    }
}
