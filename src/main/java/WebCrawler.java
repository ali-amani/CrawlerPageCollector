import Business.EnginePageCollector;
import Models.Pivot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import com.sun.rowset.internal.Row;

import java.util.ArrayList;
import java.util.List;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WebCrawler {

    private static final String FILE_NAME = "./MyFirstExcel.xlsx";

    public static void main(String[] args) {
        // TODO code application logic here


//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Takhfifan Datas");
        /*Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };*/

        int rowNum = 0;
        System.out.println("Creating excel");

        /*
        *
        * for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }*/

//        try {
//            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
//            workbook.write(outputStream);
//            workbook.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Done");

        List<Pivot> pvs = new ArrayList<Pivot>();
        //pvs.add(new Pivot("https://github.com/yasserg/crawler4j"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%B1%D8%B3%D8%AA%D9%88%D8%B1%D8%A7%D9%86-%D9%88-%DA%A9%D8%A7%D9%81%DB%8C-%D8%B4%D8%A7%D9%BE"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%AA%D8%A6%D8%A7%D8%AA%D8%B1-%D9%88-%D9%87%D9%86%D8%B1"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%AA%D9%81%D8%B1%DB%8C%D8%AD%DB%8C-%D9%88-%D9%88%D8%B1%D8%B2%D8%B4%DB%8C"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%A2%D9%85%D9%88%D8%B2%D8%B4%DB%8C"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%B3%D9%84%D8%A7%D9%85%D8%AA%DB%8C-%D9%88-%D9%BE%D8%B2%D8%B4%DA%A9%DB%8C"));
//        pvs.add(new Pivot("https://takhfifan.com/cities/%D8%AA%D9%87%D8%B1%D8%A7%D9%86/"
//                + "%D8%B2%DB%8C%D8%A8%D8%A7%DB%8C%DB%8C-%D9%88-%D8%A2%D8%B1%D8%A7%DB%8C%D8%B4%DB%8C"));
        pvs.add(new Pivot("https://takhfifan.com/global/"
                + "%D8%AA%D8%AE%D9%81%DB%8C%D9%81%D8%A7%D9%86%20%D9%87%D8%A7%DB%8C%20%D9%85%D8%B3%D8%A7%D9%81%D8%B1%D8%AA%DB%8C"));
        EnginePageCollector collector = new EnginePageCollector(pvs);
        collector.getAllShopPage();
        //collector.searchSubPivots();
//        collector.searchSubPivotsContent() ;


    }
}
