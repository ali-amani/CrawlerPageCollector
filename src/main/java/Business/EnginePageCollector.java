package Business;

import Models.PageContent;
import Models.Pivot;
import Models.ShopPageContent;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnginePageCollector {


    private List<Pivot> pivotList;
    private Map<String, List<PageContent>> mapOfMarkets;
    private MongoClient mongoClient;

    public EnginePageCollector(List<Pivot> pivotList) {
        this.pivotList = pivotList;
        mapOfMarkets = new HashMap<String, List<PageContent>>();
        this.mongoClient = new MongoClient("localhost", 27017);
    }

    public void getAllShopPage() {

        try {
            for (Pivot pivot : pivotList) {
                Document doc = Jsoup.connect(pivot.getPivot()).get();
                System.out.println("parent link : " + doc.title());

                this.mapOfMarkets.put(doc.title(), getShopInfo(doc));
                System.out.println("/////////////////////////////////");
            }

        } catch (Exception e) {
            Logger.getLogger(EnginePageCollector.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        MongoDatabase database = mongoClient.getDatabase("mydb");
        System.out.println(database.getName());
//        return list ;
    }

    private List<PageContent> getShopInfo(Document doc) throws Exception {
        List<PageContent> links = new ArrayList<PageContent>();
        Elements elements = doc.select(".deal a");
        int count = 0;
        for (Element element : elements) {
            String name = doc.select(".deal-title").get(count).text();
            String link = element.absUrl("href");
            System.out.println(count + " >> name of market : " + name + "link : " + link);

            links.add(new PageContent(link, name, getShopContent(link)));
            count++;
        }
        return links;
    }

    private ShopPageContent getShopContent(String link) {
        ShopPageContent shopPageContent = new ShopPageContent();
        Document doc;
        try {

            doc = Jsoup.connect(link).get();
            List<String> useConditions = doc.select(".deal-custom-conditions .list li").eachText(); // useConditions
            String name = doc.select(".vendor-name span").text(); // Vendor Name
            String discount = doc.select(".deal-discount-number").first().text(); // Discount
            List<String> prices = doc.select(".deal-details-right-section").select(".deal-price").eachText();
            //images
            List<String> Gallery = doc.select(".slide_element div img").eachAttr("data-src");
            //Description
            List<String> Description = doc.select(".deal-description-wrapper")
                    .select(".deal-page-card-cnt").eachText();

            List<String> features = doc.select(".deal-features-wrapper")
                    .select(".deal-features").first()
                    .select(".deal-page-card-cnt").eachText();

            List<String> shopInfo = doc.select(".deal-vendor-info").eachText(); // Vender Info
            //mag Location
            Element map = doc.select(".deal-map-wrapper").first();
            String mapCoordinate = map.attr("data-location");

            shopPageContent.setUseConditions(useConditions);
            shopPageContent.setDescription(Description);
            shopPageContent.setDiscount(discount);
            shopPageContent.setFeatures(features);
            shopPageContent.setGallery(Gallery);
            shopPageContent.setMapCoordinate(mapCoordinate);
            shopPageContent.setName(name);
            shopPageContent.setPrices(prices);
            shopPageContent.setShopInfo(shopInfo);

        } catch (Exception e) {
            Logger.getLogger(EnginePageCollector.class.getName()).log(Level.WARNING, null, e);
        }

        return shopPageContent;
    }

}
