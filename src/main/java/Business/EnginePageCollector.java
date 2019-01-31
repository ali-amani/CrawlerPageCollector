package Business;

import Models.PageContent;
import Models.Pivot;
import Models.ShopPageContent;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class EnginePageCollector {


    private List<Pivot> pivotList;
    private Map<String, List<PageContent>> mapOfMarkets;
    private com.mongodb.client.MongoClient mongoClient;
    private int rownum ;
    private static final String FILE_NAME1 = "./رستوران و کافی شاپ.xlsx";
    private static final String FILE_NAME2 = "./هنر و تئاتر.xlsx";
    private static final String FILE_NAME3 = "./تفریحی و ورزشی.xlsx";
    private static final String FILE_NAME4 = "./آموزش.xlsx";
    private static final String FILE_NAME5 = "./سلامتی و پزشکی.xlsx";
    private static final String FILE_NAME6 = "./زیبایی و آرایش.xlsx";
    private static final String FILE_NAME7 = "./تور و سفر.xlsx";
    private static final String FILE_NAME = "./اطلاعات تخفیفان.xlsx";
    private Object[] columnNames = {"نام", "لینک" , "شرایط استفاده" , "میزان تخفیف" ,
            "قیمت" , "لیست تصاویر" , "توضیحات" , "ویژگی ها" ,  "اطلاعات" , "مختصات"};

    private XSSFWorkbook workbook ;
    private XSSFSheet sheet ;

    public EnginePageCollector(List<Pivot> pivotList) {
        this.pivotList = pivotList;
        this.rownum = 0 ;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("takhfifan datas");
        Row row = sheet.createRow(rownum++);
        int colNum = 0 ;
        for (Object object:columnNames){
            Cell cell = row.createCell(colNum++);

            if (object instanceof String) {
                cell.setCellValue((String) object);
            } else if (object instanceof Integer) {
                cell.setCellValue((Integer) object);
            }
        }

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
                String title = doc.title() ;
//                if(title.length()>100)
                title = title.substring(0,35);
                System.out.println("parent link : " + title);
                MongoCollection<PageContent> collection = database.getCollection(title, PageContent.class);
                if (!collectionNames.into(new ArrayList<String>()).contains(title))
                    database.createCollection(title);

                Row row = sheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue((String) title);

                List<PageContent> pageContents = getShopInfo(doc);
                collection.insertMany(pageContents);
                this.mapOfMarkets.put(title, pageContents);
                System.out.println("/////////////////////////////////");
            }
            try {
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);

                sheet.autoSizeColumn(6);
                sheet.autoSizeColumn(7);
                sheet.autoSizeColumn(8);
                sheet.autoSizeColumn(9);

                FileOutputStream outputStream = new FileOutputStream(FILE_NAME7);
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
            count ++ ;
//            if(count-1 == 180 || count-1 == 90 )
//                continue;
            System.out.println(count-1 + " >> name of market : " + name + "link : " + link);
            ShopPageContent shopContent = getShopContent(link) ;
            //filling excel
            int colNum = 0 ;
            Row row = sheet.createRow(rownum++);

            Cell cell1 = row.createCell(colNum++);
            cell1.setCellValue((String) name);
            Cell cell2 = row.createCell(colNum++);
            cell2.setCellValue((String) link);
            Cell cell3 = row.createCell(colNum++);
            cell3.setCellValue((String) shopContent.getUseConditions().toString());
            Cell cell4 = row.createCell(colNum++);
            cell4.setCellValue((String) shopContent.getDiscount());
            Cell cell5 = row.createCell(colNum++);
            cell5.setCellValue((String) shopContent.getPrices().toString());
            Cell cell6 = row.createCell(colNum++);
            cell6.setCellValue((String) shopContent.getGallery().toString());
            Cell cell7 = row.createCell(colNum++);
            cell7.setCellValue((String) shopContent.getDescription().toString());
            Cell cell8 = row.createCell(colNum++);
            cell8.setCellValue((String) shopContent.getFeatures().toString());
            Cell cell9 = row.createCell(colNum++);
            cell9.setCellValue((String) shopContent.getShopInfo().toString());
            Cell cell10 = row.createCell(colNum++);
            cell10.setCellValue((String) shopContent.getMapCoordinate());



            System.out.println("Done");
            links.add(new PageContent(link, name, shopContent));

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

            List<String> prices = doc.select(".deal-details-right-section")
                    .select(".deal-price").eachText();
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
