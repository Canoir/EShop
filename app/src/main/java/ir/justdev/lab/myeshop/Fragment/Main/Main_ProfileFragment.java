package ir.justdev.lab.myeshop.Fragment.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.justdev.lab.myeshop.Activity.SplashActivity;
import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.R;

public class Main_ProfileFragment extends Fragment {

    private View view;
    private Utils utils;

    public Main_ProfileFragment() {
    }

    public static Main_ProfileFragment newInstance() {
        return new Main_ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        utils = new Utils(getContext(),getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_main_profile, container, false);
        //region fragment_main_profile_btn1 On Click
        v.findViewById(R.id.fragment_main_profile_btn1).setOnClickListener(view -> {
            utils.setSharedPreferences("isLogged", false);
            utils.goTo(SplashActivity.class);
        });
        //endregion
        return v;
    }
}
