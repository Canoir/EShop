package ir.justdev.lab.myeshop.Fragment.Main;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class Main_HomeFragment extends Fragment {

    private View view;
    private Product[] products = new Product[12];
    private Brand[] brands = new Brand[4];
    private Context context;

    public Main_HomeFragment(Context context) {
        this.context = context;
    }

    public static Main_HomeFragment newInstance(Context context) {
        return new Main_HomeFragment(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        dataFiller();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Utils utils = new Utils(context, getActivity());
        view = inflater.inflate(R.layout.fragment_main_home, container, false);
//        //region fragment_main_home_btn1 On Click
//        view.findViewById(R.id.fragment_main_home_btn1).setOnClickListener(view -> {
//            utils.setSharedPreferences("isLogged", false);
//            utils.goTo(SplashActivity.class);
//        });
//        //endregion
        //region RecyclerView
        setRecyclerViewBrands(utils);
        setRecyclerViewMostSeen(utils);
        setRecyclerViewLatestProducts(utils);
        setRecyclerViewLatestOff(utils);
        //endregion
        return view;
    }

    private void setRecyclerViewBrands(Utils utils) {
        utils.addRecyclerView(R.id.fragment_main_home_rcv1, new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(context, R.layout.recycler_view_item_brands, brands.length, new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        Picasso.get().load(brands[position].address).into((ImageView) itemView.findViewById(R.id.recycler_view_item_brands_img1));
                        ((TextView) itemView.findViewById(R.id.recycler_view_item_brands_txt1)).setText(brands[position].title);
                    }
                }),view);
    }

    private void setRecyclerViewMostSeen(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Integer.compare(t1.seen, product.seen);
            }
        });
        utils.addRecyclerView(R.id.fragment_main_home_rcv2, new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(context, R.layout.recycler_view_item_most_seen, 10, new RecyclerViewMethod() {
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
                }),view);
    }

    private void setRecyclerViewLatestProducts(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Long.compare(t1.setDate.getTime(), product.setDate.getTime());
            }
        });
        utils.addRecyclerView(R.id.fragment_main_home_rcv3, new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(context, R.layout.recycler_view_item_latest_products, 10, new RecyclerViewMethod() {
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
                }),view);
    }

    private void setRecyclerViewLatestOff(Utils utils) {
        final Product[] products = this.products.clone();
        Arrays.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Byte.compare(t1.off, product.off);
            }
        });
        utils.addRecyclerView(R.id.fragment_main_home_rcv4, new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                new RecyclerViewAdapter(context, R.layout.recycler_view_item_latest_off, 10, new RecyclerViewMethod() {
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
                }),view);
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
