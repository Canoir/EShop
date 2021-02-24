package ir.justdev.lab.myeshop.Engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;
import java.util.Map;

import ir.justdev.lab.myeshop.Engine.Model.PutExtra;
import ir.justdev.lab.myeshop.Engine.RecyclerView.RecyclerViewAdapter;

public class Utils {
    private final Context context;
    private final Activity activity;

    public Utils(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public Object getSharedPreferences(String key, Object _default) {
        SharedPreferences sharedpreferences = context.getSharedPreferences("EShop", Context.MODE_PRIVATE);
        if (_default instanceof String) {
            return sharedpreferences.getString(key, (String) _default);
        } else if (_default instanceof Boolean) {
            return sharedpreferences.getBoolean(key, (Boolean) _default);
        } else if (_default instanceof Float) {
            return sharedpreferences.getFloat(key, (Float) _default);
        } else if (_default instanceof Integer) {
            return sharedpreferences.getInt(key, (Integer) _default);
        } else if (_default instanceof Long) {
            return sharedpreferences.getLong(key, (Long) _default);
        }
        return null;
    }

    public void setSharedPreferences(String key, Object data) {
        SharedPreferences.Editor editor = context.getSharedPreferences("EShop", Context.MODE_PRIVATE).edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        }
        editor.apply();
    }

    //  Activity => Context
    public void goTo(Class _class) {
        activity.startActivity(new Intent(context, _class));
        activity.finish();
    }

    public void goTo(Class _class, PutExtra... putExtras) {
        Intent a = new Intent(context, _class);
        for (PutExtra putExtra : putExtras)
            if (putExtra.data instanceof Integer)
                a.putExtra(putExtra.name, (Integer) putExtra.data);
        activity.startActivity(a);
        activity.finish();
    }

    public void goTo(Class _class, boolean notFinished) {
        activity.startActivity(new Intent(context, _class));
    }

    public void goTo(Class _class, boolean notFinished, PutExtra... putExtras) {
        Intent a = new Intent(context, _class);
        for (PutExtra putExtra : putExtras)
            if (putExtra.data instanceof Integer)
                a.putExtra(putExtra.name, (Integer) putExtra.data);
        activity.startActivity(a);
    }

    public void addRecyclerView(int recyclerViewId, RecyclerView.LayoutManager layoutManager, RecyclerViewAdapter adapter) {
        RecyclerView rcv1 = activity.findViewById(recyclerViewId);
        rcv1.setLayoutManager(layoutManager);
        rcv1.setAdapter(adapter);
    }

    public void addRecyclerView(int recyclerViewId, RecyclerView.LayoutManager layoutManager, RecyclerViewAdapter adapter, View view) {
        RecyclerView rcv1 = view.findViewById(recyclerViewId);
        rcv1.setLayoutManager(layoutManager);
        rcv1.setAdapter(adapter);
    }

    public boolean setFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }

    public void alert(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void alert(String message, int length) {
        Toast.makeText(context, message, length).show();
    }

    //GET[url:String,Header[?],Query[?],Result]
    /*
     *   "Token" : "ADASDKAAOUDJAJAKW",
     *   "Session": ["A","B"],
     * */
    public void GET(String url, FutureCallback<JsonObject> result) {
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(String url, FutureCallback<JsonArray> result, boolean isArray) {
        Ion.with(context)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(String url, Map<String, List<String>> queries, FutureCallback<JsonObject> result) {
        Ion.with(context)
                .load(url)
                .addQueries(queries)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(String url, Map<String, List<String>> queries, FutureCallback<JsonArray> result, boolean isArray) {
        Ion.with(context)
                .load(url)
                .addQueries(queries)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(Map<String, List<String>> headers, String url, FutureCallback<JsonObject> result) {
        Ion.with(context)
                .load(url)
                .addHeaders(headers)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(Map<String, List<String>> headers, String url, FutureCallback<JsonArray> result, boolean isArray) {
        Ion.with(context)
                .load(url)
                .addHeaders(headers)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(String url, Map<String, List<String>> headers, Map<String, List<String>> queries, FutureCallback<JsonObject> result) {
        Ion.with(context)
                .load(url)
                .addHeaders(headers)
                .addQueries(queries)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject r) {
                        result.onCompleted(e, r);
                    }
                });
    }

    public void GET(String url, Map<String, List<String>> headers, Map<String, List<String>> queries, FutureCallback<JsonArray> result, boolean isArray) {
        Ion.with(context)
                .load(url)
                .addHeaders(headers)
                .addQueries(queries)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray r) {
                        result.onCompleted(e, r);
                    }
                });
    }
    //POST
}
