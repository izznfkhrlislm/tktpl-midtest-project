package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;

public class DeviceSpecificationAdapter extends RecyclerView.Adapter<DeviceSpecificationAdapter.DeviceSpecificationHolder> {
    private List<DeviceSpecification> deviceSpecifications;
    private Context context;
    private Callable callable;

    public DeviceSpecificationAdapter(Context context, List<DeviceSpecification> deviceSpecificationList, Callable callable) {
        this.context = context;
        this.deviceSpecifications = deviceSpecificationList;
        this.callable = callable;
    }

    @NonNull
    @Override
    public DeviceSpecificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View deviceMenuView = LayoutInflater.from(context)
                .inflate(R.layout.device_specs_card_item, parent, false);
        return new DeviceSpecificationHolder(deviceMenuView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceSpecificationHolder holder, int position) {
        DeviceSpecification currentItem = deviceSpecifications.get(position);
        holder.setData(currentItem, position);
    }

    @Override
    public int getItemCount() {
        return deviceSpecifications.size();
    }

    //inner class
    class DeviceSpecificationHolder extends RecyclerView.ViewHolder {
        private View menuView;
        private int position = 0;
        private DeviceSpecification currentItem;

        public DeviceSpecificationHolder(View menuView) {
            super(menuView);
            this.menuView = menuView;
        }

        public void setData(DeviceSpecification currentData, int position) {
            TextView textViewModelName = menuView.findViewById(R.id.modelNameText);
            textViewModelName.setText(currentData.getDeviceName());

            this.position = position;
            this.currentItem = currentData;

            menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callable.onClickCallback(currentItem);
                }
            });
        }

        public void setDeviceSpecificationItemOnClickListener() {
            menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ViewHolder", "clicked!");
                }
            });
        }
    }
}
