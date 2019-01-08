import java.util.ArrayList;
import java.util.List;

public class WebCrawler {
    public static void main(String[] args) {
        // TODO code application logic here
        List<Pivot> pvs = new ArrayList<Pivot>();
        //pvs.add(new Pivot("https://github.com/yasserg/crawler4j"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%B1%D8%B3%D8%AA%D9%88%D8%B1%D8%A7%D9%86-%D9%88-%DA%A9%D8%A7%D9%81%DB%8C-%D8%B4%D8%A7%D9%BE"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%AA%D8%A6%D8%A7%D8%AA%D8%B1-%D9%88-%D9%87%D9%86%D8%B1"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%AA%D9%81%D8%B1%DB%8C%D8%AD%DB%8C-%D9%88-%D9%88%D8%B1%D8%B2%D8%B4%DB%8C"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%A2%D9%85%D9%88%D8%B2%D8%B4%DB%8C"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%B3%D9%84%D8%A7%D9%85%D8%AA%DB%8C-%D9%88-%D9%BE%D8%B2%D8%B4%DA%A9%DB%8C"));
        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
                + "%D8%B2%DB%8C%D8%A8%D8%A7%DB%8C%DB%8C-%D9%88-%D8%A2%D8%B1%D8%A7%DB%8C%D8%B4%DB%8C"));
        pvs.add(new Pivot("https://takhfifan.com/global/"
                + "%D8%AA%D8%AE%D9%81%DB%8C%D9%81%D8%A7%D9%86%20%D9%87%D8%A7%DB%8C%20%D9%85%D8%B3%D8%A7%D9%81%D8%B1%D8%AA%DB%8C"));
        EnginePageCollector collector = new EnginePageCollector(pvs);
        collector.getAllShopPage();
        //collector.searchSubPivots();
//        collector.searchSubPivotsContent() ;


    }
}
