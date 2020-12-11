package ir.justdev.lab.myeshop.Engine.RecyclerView;

import android.view.View;

public interface RecyclerViewMethod {
    public void onItem(RecyclerViewAdapter.ViewHolder holder, int position, View itemView);
}
