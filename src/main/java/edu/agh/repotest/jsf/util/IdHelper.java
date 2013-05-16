/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.util;

/**
 *
 * @author pawel
 */
public class IdHelper {
    /**
     * 
     * @param id
     * @param part ( 1 is 0 )
     * @return 
     */
    public  static String getIdPart( String id, int part){
        System.out.println("Before slit" + id);
        final String[] splitted = id.split(":");
        System.out.println("Splitted" + splitted);
        if( Math.abs(part) > splitted.length){
            throw new IllegalArgumentException(String.format("Id has %s part but you are trying to get %s", splitted.length,part));
        }
            
        if( part < 0 ){
            return splitted[splitted.length + part];
        }else{
             return splitted[ part - 1 ];
        }    
        
    }
}
