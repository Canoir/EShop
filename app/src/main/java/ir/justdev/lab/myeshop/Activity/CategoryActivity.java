package ir.justdev.lab.myeshop.Activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ir.justdev.lab.myeshop.Data;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewAdapter;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewMethod;
import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.Models.Model.Brand;
import ir.justdev.lab.myeshop.Models.Model.Product;
import ir.justdev.lab.myeshop.R;

public class CategoryActivity extends AppCompatActivity {
    private Utils utils;
    private int brandId;
    private Product[] products;
    private Brand[] brands;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataFiller();
        setContentView(R.layout.activity_category);
        init();
        //region RecyclerView
        setRecyclerViewBrandsProducts(utils);
        //endregion
        ((SearchView) findViewById(R.id.activity_category_sv1)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return setRecyclerViewBrandsProducts(utils, s);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return setRecyclerViewBrandsProducts(utils, s);
            }
        });
    }

    private void init() {
        utils = new Utils(getApplicationContext(), CategoryActivity.this);
        brandId = Integer.parseInt(getIntent().getExtras().get("id") + "");
    }

    private void setRecyclerViewBrandsProducts(Utils utils) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product p : this.products)
            if (p.brand.equals(brands[brandId]))
                products.add(p);
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Long.compare(t1.setDate.getTime(), product.setDate.getTime());
            }
        });
        //RecyclerView     
        utils.addRecyclerView(R.id.activity_category_rcv1, new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_latest_products_fit, products.size(), new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        TextView brand = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt1);
                        TextView name = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt2);
                        TextView price = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt3);
                        TextView off = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt4);
                        TextView totalPrice = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt5);
//
                        brand.setText(products.get(position).brand.title + ": ");
                        name.setText(products.get(position).name);
                        price.setText(products.get(position).price + "");
                        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        off.setText(products.get(position).off + "%");
                        long _totalPrice = (long) products.get(position).price - (long) ((long) products.get(position).price * (long) products.get(position).off / 100);
                        totalPrice.setText(_totalPrice + "");
//
                        Picasso.get().load(products.get(position).imageAddress).into((ImageView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_img1));

                    }
                }));
    }

    private boolean setRecyclerViewBrandsProducts(Utils utils, String s) {
        s = s.toLowerCase();
        ArrayList<Product> products = new ArrayList<>();
        for (Product p : this.products)
            if (p.brand.equals(brands[brandId]) && (p.name.toLowerCase().contains(s) || p.brand.title.toLowerCase().contains(s)))
                products.add(p);
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return Long.compare(t1.setDate.getTime(), product.setDate.getTime());
            }
        });
        //RecyclerView
        utils.addRecyclerView(R.id.activity_category_rcv1, new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false),
                new RecyclerViewAdapter(getApplicationContext(), R.layout.recycler_view_item_latest_products_fit, products.size(), new RecyclerViewMethod() {
                    @Override
                    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView) {
                        TextView brand = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt1);
                        TextView name = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt2);
                        TextView price = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt3);
                        TextView off = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt4);
                        TextView totalPrice = (TextView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_txt5);
//
                        brand.setText(products.get(position).brand.title + ": ");
                        name.setText(products.get(position).name);
                        price.setText(products.get(position).price + "");
                        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        off.setText(products.get(position).off + "%");
                        long _totalPrice = (long) products.get(position).price - (long) ((long) products.get(position).price * (long) products.get(position).off / 100);
                        totalPrice.setText(_totalPrice + "");
//
                        Picasso.get().load(products.get(position).imageAddress).into((ImageView) itemView.findViewById(R.id.recycler_view_item_latest_products_fit_img1));

                    }
                }));
        return true;
    }

    private void dataFiller() {
        Data data = new Data();
        brands = data.getBrands();
        products = data.getProducts();
    }
}
