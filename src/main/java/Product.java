import java.util.List;

public class Product {

    private String name;
    private String brand;
    private String price;
    private String id;
    private List<String> colors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product(String name, String brand, String price, String id, List<String> colors) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.id = id;
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", colors=" + colors +
                '}';
    }
}
