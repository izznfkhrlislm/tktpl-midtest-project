package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters.RecyclerAdapter;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.datas.MainMenuItemDataProvider;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        setupRecyclerView(rootView);

        return rootView;
    }

    private void setupRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.menuRecyclerView);
        System.out.println(MainMenuItemDataProvider.getDatas().isEmpty());
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), MainMenuItemDataProvider.getDatas());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }
}
