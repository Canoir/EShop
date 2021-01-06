package ir.justdev.lab.myeshop;

import java.util.Date;

import ir.justdev.lab.myeshop.Models.Model.Brand;
import ir.justdev.lab.myeshop.Models.Model.Product;

public class Data {

    private Brand[] brands = null;
    private Product[] products = null;

    public Product[] getProducts() {
        if (products == null) {
            products = new Product[12];
            if (brands == null)
                brands = getBrands();
            //      Mac
            products[0] = new Product("MacBook Pro 2020", brands[0].address, 70000000, 20, (byte) 99, new Date(new Date().getTime() - 100), brands[0]);
            products[1] = new Product("MacBook Pro 2019", brands[0].address, 60000000, 24, (byte) 80, new Date(new Date().getTime() - 400), brands[0]);
            products[2] = new Product("MacBook Pro 2018", brands[0].address, 50000000, 10, (byte) 10, new Date(new Date().getTime() - 1000), brands[0]);
            //      iPad
            products[3] = new Product("iPad Pro 2020", brands[1].address, 23000000, 120, (byte) 12, new Date(new Date().getTime() - 200), brands[1]);
            products[4] = new Product("iPad Pro 2019", brands[1].address, 21000000, 200, (byte) 25, new Date(new Date().getTime() - 900), brands[1]);
            products[5] = new Product("iPad Pro 2018", brands[1].address, 19000000, 180, (byte) 34, new Date(new Date().getTime() - 1200), brands[1]);
            //      iPhone
            products[6] = new Product("iPhone12 Pro Max", brands[2].address, 34000000, 600, (byte) 99, new Date(new Date().getTime() - 300), brands[2]);
            products[7] = new Product("iPhone11 Pro Max", brands[2].address, 24000000, 120, (byte) 99, new Date(new Date().getTime() - 500), brands[2]);
            products[8] = new Product("iPhone10 Pro Max", brands[2].address, 12000000, 900, (byte) 99, new Date(new Date().getTime() - 1100), brands[2]);
            //      iWatch
            products[9] = new Product("iWatch Pro 6", brands[3].address, 20000000, 1100, (byte) 99, new Date(new Date().getTime() - 600), brands[3]);
            products[10] = new Product("iWatch Pro 5", brands[3].address, 16000000, 1, (byte) 99, new Date(new Date().getTime() - 700), brands[3]);
            products[11] = new Product("iWatch Pro 4", brands[3].address, 12000000, 500, (byte) 99, new Date(new Date().getTime() - 800), brands[3]);
        }
        return products;
    }

    public Brand[] getBrands() {
        if (brands == null) {
            brands = new Brand[4];
            brands[0] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mac-pro-hero-cto?wid=277&hei=222&fmt=jpeg&qlt=95&.v=1572646185753", "Mac");
            brands[1] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-mini-select-201911?wid=465&hei=530&fmt=jpeg&qlt=95&op_usm=0.5%2C0.5&.v=1584056010058", "iPad");
            brands[2] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,0.5&amp;.v=1567022175704", "iPhone");
            brands[3] = new Brand("https://www.apple.com/v/watch/shared/compare/d/images/overview/compare_se__crebhd9hhdea_large.jpg", "Watch");
        }
        return brands;
    }
}
