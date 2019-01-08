package Business;

import Models.PageContent;
import Models.Pivot;
import Models.ShopPageContent;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class EnginePageCollector {


    private List<Pivot> pivotList;
    private Map<String, List<PageContent>> mapOfMarkets;
    private MongoClient mongoClient;

    public EnginePageCollector(List<Pivot> pivotList) {
        this.pivotList = pivotList;
        mapOfMarkets = new HashMap<String, List<PageContent>>();

        CodecRegistry pojoCodecRegistry = fromRegistries(com.mongodb.MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry).applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build();

        this.mongoClient = MongoClients.create(settings);

    }

    public void getAllShopPage() {

        try {
            MongoDatabase database = mongoClient.getDatabase("Crawler");

            MongoIterable<String> collectionNames = database.listCollectionNames();

            for (Pivot pivot : pivotList) {
                Document doc = Jsoup.connect(pivot.getPivot()).get();
                System.out.println("parent link : " + doc.title());
                MongoCollection<PageContent> collection = database.getCollection(doc.title(), PageContent.class);
                if (!collectionNames.into(new ArrayList<String>()).contains(doc.title()))
                    database.createCollection(doc.title());

                List<PageContent> pageContents = getShopInfo(doc);
                collection.insertMany(pageContents);
                this.mapOfMarkets.put(doc.title(), pageContents);
                System.out.println("/////////////////////////////////");
            }

        } catch (Exception e) {
            Logger.getLogger(EnginePageCollector.class.getName()).log(Level.SEVERE, e.getMessage());
        }

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
            if (count == 10)
                break;
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
