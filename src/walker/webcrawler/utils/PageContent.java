/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walker.webcrawler.utils;

import org.jsoup.nodes.Document;

/**
 *
 * @author Ali Amani
 */
public class PageContent {
//for the contents of the page to be righty saved

    private String link ;
    private String title ;    
    
    private ShopPageContent shopPageContent ;
       

    public PageContent(String link, String title) {
        this.link = link;
        this.title = title;
        
    }

    public PageContent(String link, String title, ShopPageContent shopPageContent) {
        this.link = link;
        this.title = title;
        
        this.shopPageContent = shopPageContent;
    }
    
    public PageContent() {
    }

    public ShopPageContent getShopPageContent() {
        return shopPageContent;
    }

    public void setShopPageContent(ShopPageContent shopPageContent) {
        this.shopPageContent = shopPageContent;
    }
        
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public static ShopPageContent fillTheShopPage(){
        ShopPageContent spc = new ShopPageContent();
        return spc ;
    }
}
