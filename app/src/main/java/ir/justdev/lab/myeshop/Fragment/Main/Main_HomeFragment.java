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

import ir.justdev.lab.myeshop.Data;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewAdapter;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewMethod;
import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.Models.Model.Brand;
import ir.justdev.lab.myeshop.Models.Model.Product;
import ir.justdev.lab.myeshop.R;

public class Main_HomeFragment extends Fragment {

    private View view;
    private Context context;
    private Product[] products;
    private Brand[] brands;

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
                }), view);
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
                }), view);
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
                }), view);
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
                }), view);
    }

    private void dataFiller() {
        Data data = new Data();
        brands = data.getBrands();
        products = data.getProducts();
    }
}
