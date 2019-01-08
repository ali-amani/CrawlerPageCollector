package Models;

import java.util.List;

public class ShopPageContent {
    private List<String> useConditions ; // h1 title
    private String name ;
    private String discount ;
    private List<String> prices ;
    private List<String> Gallery ;
    private List<String> Description ;
    private List<String> features ;
    private List<String> shopInfo ;
    private String mapCoordinate ;


    public ShopPageContent() {
    }

    public ShopPageContent(List<String> useConditions, String name, String discount, List<String> prices, List<String> Gallery, List<String> Description, List<String> features, List<String> shopInfo, String mapCoordinate) {
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
