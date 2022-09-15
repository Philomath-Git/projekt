package your.name.here;


public class UserProfile {
    //    String size;
//    boolean milk;
    String time;
    String country;
    String device;
    String action;
    String origin;
    ProductInfo product_info;


    // In order for Spring to serialize Coffee objects, we need
    // to define getter and setter methods for each attribute
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    // country
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //Device
    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device) {
        this.device = device;
    }


    //Action
    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    //origin
    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    //product_info
    public ProductInfo getProduct_info() {
        return this.product_info;
    }

    public void setProductInfo(ProductInfo product_info) {
        this.product_info = product_info;
    }

    // we will use the toString method in later examples
    //public String toString() {
    //    return "[" + country + ",de " + device + "]";
    //}
}
