/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walker.webcrawler.enginpart;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import walker.webcrawler.utils.PageContent;
import walker.webcrawler.utils.Pivot;
import walker.webcrawler.utils.ShopPageContent;


/**
 *
 * @author Ali Amani
 */
public class EnginPageCollector {

    private List<Pivot> pivotList;
    private Map<String, List<PageContent>> mapOfMarkets;
    private MongoClient mongoClient;

    public EnginPageCollector(List<Pivot> pivotList) {
        this.pivotList = pivotList;
        mapOfMarkets = new HashMap<>();
        this.mongoClient = new MongoClient( "localhost" , 27017 );
    }

    

    public void getAllShopPage() {

        try {
            for (Pivot pivot : pivotList) {
                Document doc = Jsoup.connect(pivot.getPivot()).get();
                System.out.println("parent link : " + doc.title());

//                this.mapOfMarkets.put(doc.title(), getShopInfo(doc));

                System.out.println("/////////////////////////////////");
            }

        } catch (Exception e) {
            Logger.getLogger(EnginPageCollector.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        MongoDatabase database = mongoClient.getDatabase("mydb");
        System.out.println(database.getName());
//        return list ;
    }

    private List<PageContent> getShopInfo(Document doc) throws Exception {
        List<PageContent> links = new ArrayList<>();
        Elements elements = doc.select(".deal a");
        int count = 0;
        for (Element element : elements) {
            String name = doc.select(".deal-title").get(count).text();
            String link = element.absUrl("href");
            System.out.println(count + " >> name of market : " + name + "link : " + link);
            
            links.add(new PageContent(link, name , getShopContent(link)));
            count++;
        }
        return links;
    }

    private ShopPageContent getShopContent(String link) {
        
        try {
            Document doc = Jsoup.connect(link).get();
            ShopPageContent shopPageContent = new ShopPageContent();
            
            List<String> useConditions = doc.select(".deal-custom-conditions li span").eachText(); // useConditions
            String name = doc.select(".vendor-name span").text(); // Vendor Name
            String discount = doc.select(".deal-discount-number").text(); // Discount
            //images
            Elements imageElements = doc.select(".slid img");
            List<String> Gallery = imageElements.eachAttr("src");
            //Description
            List<String> Description = doc.select(".deal-page-card-cnt").eachText();
            List<String> shopInfo = doc.select(".deal-vendor-info").eachText() ; // Vender Info
            //mag Location
            Element map = doc.select(".deal-map-wrapper").first();
            String mapCordinate = map.attr("data-location") ;
            
            
                                                                         
        } catch (IOException ex) {
            Logger.getLogger(EnginPageCollector.class.getName()).log(Level.WARNING, null, ex);
        }
        
        return new ShopPageContent();
    }

}
