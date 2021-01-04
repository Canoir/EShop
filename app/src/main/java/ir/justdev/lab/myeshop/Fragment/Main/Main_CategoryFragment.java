package ir.justdev.lab.myeshop.Fragment.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ir.justdev.lab.myeshop.R;

public class Main_CategoryFragment extends Fragment {

    private View view;

    public Main_CategoryFragment() {
    }

    public static Main_CategoryFragment newInstance() {
        return new Main_CategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_category, container, false);
    }
}
