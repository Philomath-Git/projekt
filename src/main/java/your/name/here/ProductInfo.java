package your.name.here;


public class ProductInfo {
    //    String size;
//    boolean milk;
    int product_id;
    String brand_id;
    String category_id;
    int price;

    // In order for Spring to serialize Coffee objects, we need
    // to define getter and setter methods for each attribute

    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    // brand_id
    public String getBrand_id() {
        return this.brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    // category_id
    public String getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    // price
    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    // we will use the toString method in later examples
    //public String toString() {
    //    return "empty";
    //	//"[" + size + "," + Boolean.toString(milk) + "]";
    //}
}
