//region Package
package ir.justdev.lab.myeshop.Activity;
//endregion

//region Imports

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.Fragment.Main.Main_CartFragment;
import ir.justdev.lab.myeshop.Fragment.Main.Main_CategoryFragment;
import ir.justdev.lab.myeshop.Fragment.Main.Main_HomeFragment;
import ir.justdev.lab.myeshop.Fragment.Main.Main_ProfileFragment;
import ir.justdev.lab.myeshop.Fragment.Main.Main_SearchFragment;
import ir.justdev.lab.myeshop.R;
//endregion

//region MainActivity

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //region Init
        final Utils utils = new Utils(getApplicationContext(), MainActivity.this);
        //endregion
        ((BottomNavigationView) findViewById(R.id.activity_main_bn1)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_navigation_menu_home:
                    return utils.setFragment(Main_HomeFragment.newInstance(MainActivity.this), R.id.activity_main_fl1);
                case R.id.bottom_navigation_menu_category:
                    return utils.setFragment(Main_CategoryFragment.newInstance(), R.id.activity_main_fl1);
                case R.id.bottom_navigation_menu_search:
                    return utils.setFragment(Main_SearchFragment.newInstance(), R.id.activity_main_fl1);
                case R.id.bottom_navigation_menu_cart:
                    return utils.setFragment(Main_CartFragment.newInstance(), R.id.activity_main_fl1);
                case R.id.bottom_navigation_menu_profile:
                    return utils.setFragment(Main_ProfileFragment.newInstance(), R.id.activity_main_fl1);
            }
            return false;
        });
        utils.setFragment(Main_HomeFragment.newInstance(MainActivity.this), R.id.activity_main_fl1);
    }
}
//endregion