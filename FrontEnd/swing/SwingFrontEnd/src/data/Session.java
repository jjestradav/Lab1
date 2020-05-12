/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class Session {
 
    private    HashMap<String,Object> attributes;
    private static Session instance = null;
    
    public static Session getInstance(){
        if(instance ==  null)
            instance= new Session();
        
        return instance;
    }


    private Session() {
        this.attributes = new HashMap();
    }
    
    public Object getAttribute(String attribute){
        return attributes.get(attribute);
    }
    
    public void setAttibute(String attribute, Object value){
        attributes.put(attribute, value);
    }
    
    public void removeAttribute(String attribute){
        attributes.remove(attribute);
    }
}