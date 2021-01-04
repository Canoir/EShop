package ir.justdev.lab.myeshop.Fragment.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ir.justdev.lab.myeshop.R;

public class Main_CartFragment extends Fragment {

    private View view;

    public Main_CartFragment() {
    }

    public static Main_CartFragment newInstance() {
        return new Main_CartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_cart, container, false);
    }
}
