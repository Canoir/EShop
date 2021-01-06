package ir.justdev.lab.myeshop.Fragment.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ir.justdev.lab.myeshop.Activity.CategoryActivity;
import ir.justdev.lab.myeshop.Data;
import ir.justdev.lab.myeshop.Engine.Model.PutExtra;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewAdapter;
import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.Models.Model.Brand;
import ir.justdev.lab.myeshop.R;

public class Main_CategoryFragment extends Fragment {

    private View view;
    private Brand[] brands;

    public Main_CategoryFragment() {
    }

    public static Main_CategoryFragment newInstance() {
        return new Main_CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        dataFiller();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Utils utils = new Utils(getContext(), getActivity());
        view = inflater.inflate(R.layout.fragment_main_category, container, false);
        //region Recyclers
        setRecyclerViewBrands(utils);
        //endregion
        return view;
    }

    private void setRecyclerViewBrands(Utils utils) {
        utils.addRecyclerView(R.id.fragment_main_category_rcv1, new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false),
                new RecyclerViewAdapter(getContext(), R.layout.recycler_view_item_brands_fit, brands.length, (holder, position, itemView) -> {
                    Picasso.get().load(brands[position].address).into((ImageView) itemView.findViewById(R.id.recycler_view_item_brands_fit_img1));
                    ((TextView) itemView.findViewById(R.id.recycler_view_item_brands_fit_txt1)).setText(brands[position].title);
                    itemView.findViewById(R.id.recycler_view_item_brands_fit_ln1).setOnClickListener(view -> {
                        utils.goTo(CategoryActivity.class, true, new PutExtra("id", position));
                    });
                }), view);
    }


    private void dataFiller() {
        Data data = new Data();
        brands = data.getBrands();
    }
}
