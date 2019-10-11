import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String storeUrl = "https://www.aboutyou.de/maenner/bekleidung";
    private final static String fileName = "Aboutyou.json";
    private static List<Product> productList = new ArrayList<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static int counter;

    public static void main(String[] args) {

        try {
            Connection.Request request = Jsoup.connect(storeUrl).request();
            Document document = Jsoup.connect(storeUrl)
                    .cookies(request.cookies())
                    .timeout(1000)
                    .userAgent("Chrome")
                    .get();

            Elements productClasses = document.getElementsByAttributeValue("class", "ProductTile__Wrapper-sc-1qheze-0 eZqmvf");

            productClasses.parallelStream().forEach(productClass -> {
                String url = productClass.attr("href");
                String name = getProductName(url);
                String brand = productClass.getElementsByTag("p").text();
                String price = productClass.getElementsByAttributeValue("data-test-id", "ProductPriceFormattedBasePrice").text();
                String id = productClass.attr("id");
                List<String> colors = productClass.select("li").eachAttr("color");
                counter++;
                productList.add(new Product(name, brand, price, id, colors));
            });

            mapper.writeValue(new File(fileName), productList);
            System.out.println("Number of products = "+counter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProductName(String url) {
        int firstIndex = url.lastIndexOf("/");
        int lastIndex = url.lastIndexOf("-");
        return url.substring(firstIndex, lastIndex).replace("-", " ").substring(1);
    }

}
