//region Package
package ir.justdev.lab.myeshop.Activity;
//endregion

//region Imports

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewAdapter;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewMethod;
import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.Models.Model.Brand;
import ir.justdev.lab.myeshop.Models.Model.Product;
import ir.justdev.lab.myeshop.R;
//endregion

//region MainActivity

public class MainActivity extends AppCompatActivity {
    private Product[] products = new Product[12];
    private Brand[] brands = new Brand[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataFiller();
        setContentView(R.layout.activity_main);
        //region Init
        final Utils utils = new Utils(getApplicationContext(), MainActivity.this);
        //endregion
        //region activity_main_btn1 On Click
        findViewById(R.id.activity_main_btn1).setOnClickListener(view -> {
            utils.setSharedPreferences("isLogged", false);
            utils.goTo(SplashActivity.class);
        });
        //endregion
        //region RecyclerView
        setRecyclerViewBrands(utils);
        setRecyclerViewMostSeen(utils);
        setRecyclerViewLatestProducts(utils);
        setRecyclerViewLatestOff(utils);
        //endregion
    }

    private void setRecyclerViewBrands(Utils utils) {
        utils.addRecyclerView(R.id.activity_main_rcv1, new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_brands, brands.length, new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        Picasso.get().load(brands[position].address).into((ImageView) itemView.findViewById(R.id.recycler_view_item_brands_img1));
                        ((TextView) itemView.findViewById(R.id.recycler_view_item_brands_txt1)).setText(brands[position].title);
                    }
                }));
    }

    private void setRecyclerViewMostSeen(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Integer.compare(t1.seen, product.seen);
            }
        });
        utils.addRecyclerView(R.id.activity_main_rcv2, new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_most_seen, 10, new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        TextView brand = (TextView) itemView.findViewById(R.id.recycler_view_item_most_seen_txt1);
                        TextView name = (TextView) itemView.findViewById(R.id.recycler_view_item_most_seen_txt2);
                        TextView price = (TextView) itemView.findViewById(R.id.recycler_view_item_most_seen_txt3);
                        TextView off = (TextView) itemView.findViewById(R.id.recycler_view_item_most_seen_txt4);
                        TextView totalPrice = (TextView) itemView.findViewById(R.id.recycler_view_item_most_seen_txt5);
//
                        brand.setText(products[position].brand.title + ": ");
                        name.setText(products[position].name);
                        price.setText(products[position].price + "");
                        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        off.setText(products[position].off + "%");
                        long _totalPrice = (long) products[position].price - (long) ((long) products[position].price * (long) products[position].off / 100);
                        totalPrice.setText(_totalPrice + "");
//
                        Picasso.get().load(products[position].imageAddress).into((ImageView) itemView.findViewById(R.id.recycler_view_item_most_seen_img1));

                    }
                }));
    }

    private void setRecyclerViewLatestProducts(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Long.compare(t1.setDate.getTime(), product.setDate.getTime());
            }
        });
        utils.addRecyclerView(R.id.activity_main_rcv3, new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_latest_products, 10, new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        TextView brand = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_txt1);
                        TextView name = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_txt2);
                        TextView price = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_txt3);
                        TextView off = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_txt4);
                        TextView totalPrice = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_txt5);
//
                        brand.setText(products[position].brand.title + ": ");
                        name.setText(products[position].name);
                        price.setText(products[position].price + "");
                        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        off.setText(products[position].off + "%");
                        long _totalPrice = (long) products[position].price - (long) ((long) products[position].price * (long) products[position].off / 100);
                        totalPrice.setText(_totalPrice + "");
//
                        Picasso.get().load(products[position].imageAddress).into((ImageView) itemView.findViewById(R.id.recycler_view_item_latest_products_img1));

                    }
                }));
    }

    private void setRecyclerViewLatestOff(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Byte.compare(t1.off, product.off);
            }
        });
        utils.addRecyclerView(R.id.activity_main_rcv4, new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_latest_off, 10, new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        TextView brand = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_off_txt1);
                        TextView name = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_off_txt2);
                        TextView price = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_off_txt3);
                        TextView off = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_off_txt4);
                        TextView totalPrice = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_off_txt5);
//
                        brand.setText(products[position].brand.title + ": ");
                        name.setText(products[position].name);
                        price.setText(products[position].price + "");
                        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        off.setText(products[position].off + "%");
                        long _totalPrice = (long) products[position].price - (long) ((long) products[position].price * (long) products[position].off / 100);
                        totalPrice.setText(_totalPrice + "");
//
                        Picasso.get().load(products[position].imageAddress).into((ImageView) itemView.findViewById(R.id.recycler_view_item_latest_off_img1));

                    }
                }));
    }

    private void dataFiller() {
        brands[0] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mac-pro-hero-cto?wid=277&hei=222&fmt=jpeg&qlt=95&.v=1572646185753", "Mac");
        brands[1] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-mini-select-201911?wid=465&hei=530&fmt=jpeg&qlt=95&op_usm=0.5%2C0.5&.v=1584056010058", "iPad");
        brands[2] = new Brand("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-select-2019-family?wid=441&amp;hei=529&amp;fmt=jpeg&amp;qlt=95&amp;op_usm=0.5,0.5&amp;.v=1567022175704", "iPhone");
        brands[3] = new Brand("https://www.apple.com/v/watch/shared/compare/d/images/overview/compare_se__crebhd9hhdea_large.jpg", "Watch");
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
}
//endregion