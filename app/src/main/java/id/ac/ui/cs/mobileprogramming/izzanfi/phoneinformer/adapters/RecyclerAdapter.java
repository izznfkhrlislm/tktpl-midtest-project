package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.Communicable;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.datas.MainMenuItem;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<MainMenuItem> mainMenuItemList;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<MainMenuItem> mainMenuItemList) {
        this.context = context;
        this.mainMenuItemList = mainMenuItemList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemView = LayoutInflater.from(this.context)
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(menuItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainMenuItem currentItem = mainMenuItemList.get(position);
        holder.setData(currentItem, position);
        holder.setMainMenuItemOnClickListener();
    }

    @Override
    public int getItemCount() {
        return this.mainMenuItemList.size();
    }

    // MyViewHolder: inner class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private View menuItemView;
        private int position = 0;
        private MainMenuItem currentMainMenuItem;

        public MyViewHolder(View menuItemView) {
            super(menuItemView);
            this.menuItemView = menuItemView;
        }

        public void setData(MainMenuItem currentData, int position) {
            TextView menuTitleTextView = menuItemView.findViewById(R.id.menuItemTitle);
            menuTitleTextView.setText(currentData.getMenuTitle());

            ImageView menuButtonImageView = menuItemView.findViewById(R.id.imgRow);
            menuButtonImageView.setImageResource(currentData.getImgCode());
            this.position = position;
            this.currentMainMenuItem = currentData;
        }

        public void setMainMenuItemOnClickListener() {
            menuItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Communicable communicable = (Communicable) v.getContext();
                    communicable.displayDetails(currentMainMenuItem.getMenuTitle(),
                            currentMainMenuItem.getMenuDescription());
                }
            });
        }
    }
}
