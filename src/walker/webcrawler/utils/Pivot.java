/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walker.webcrawler.utils;

/**
 *
 * @author Ali Amani
 */
public class Pivot {
    //is for the links only
    
    private String pivot ; // link

    public Pivot(String pivot) {
        this.pivot = pivot;
    }

    public String getPivot() {
        return pivot;
    }

    public void setPivot(String pivot) {
        this.pivot = pivot;
    }
    
    
    
}
