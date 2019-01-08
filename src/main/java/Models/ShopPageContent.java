package Models;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

public class ShopPageContent {

    @BsonProperty("useConditions")
    private List<String> useConditions; // h1 title

    @BsonProperty("name")
    private String name;

    @BsonProperty("discount")
    private String discount;

    @BsonProperty("prices")
    private List<String> prices;

    @BsonProperty("Gallery")
    private List<String> Gallery;

    @BsonProperty("Description")
    private List<String> Description;

    @BsonProperty("features")
    private List<String> features;

    @BsonProperty("shopInfo")
    private List<String> shopInfo;

    @BsonProperty("mapCoordinate")
    private String mapCoordinate;


    public ShopPageContent() {
    }

    @BsonCreator
    public ShopPageContent(
            @BsonProperty("useConditions")
                    List<String> useConditions, // h1 title
            @BsonProperty("name")
                    String name,
            @BsonProperty("discount")
                    String discount,
            @BsonProperty("prices")
                    List<String> prices,
            @BsonProperty("Gallery")
                    List<String> Gallery,
            @BsonProperty("Description")
                    List<String> Description,
            @BsonProperty("features")
                    List<String> features,
            @BsonProperty("shopInfo")
                    List<String> shopInfo,
            @BsonProperty("mapCoordinate")
                    String mapCoordinate
    ) {
        this.useConditions = useConditions;
        this.name = name;
        this.discount = discount;
        this.prices = prices;
        this.Gallery = Gallery;
        this.Description = Description;
        this.features = features;
        this.shopInfo = shopInfo;
        this.mapCoordinate = mapCoordinate;
    }

    public List<String> getUseConditions() {
        return useConditions;
    }

    public void setUseConditions(List<String> useConditions) {
        this.useConditions = useConditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public List<String> getGallery() {
        return Gallery;
    }

    public void setGallery(List<String> Gallery) {
        this.Gallery = Gallery;
    }

    public List<String> getDescription() {
        return Description;
    }

    public void setDescription(List<String> Description) {
        this.Description = Description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(List<String> shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getMapCoordinate() {
        return mapCoordinate;
    }

    public void setMapCoordinate(String mapCoordinate) {
        this.mapCoordinate = mapCoordinate;
    }


}
