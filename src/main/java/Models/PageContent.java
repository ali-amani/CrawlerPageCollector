package Models;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class PageContent {
    //for the contents of the page to be rightly saved
    @BsonProperty("link")
    private String link ;

    @BsonProperty("title")
    private String title ;

    @BsonProperty("shopPageContent")
    private ShopPageContent shopPageContent ;


    public PageContent(String link, String title) {
        this.link = link;
        this.title = title;

    }

    @BsonCreator
    public PageContent(
            @BsonProperty("link") String link,
            @BsonProperty("title") String title,
            @BsonProperty("shopPageContent") ShopPageContent shopPageContent
    ) {
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
        return new ShopPageContent();
    }
}
