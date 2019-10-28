package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.datas;

import java.util.*;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class MainMenuItemDataProvider {

    private static ArrayList<Integer> IMG_RESOURCES = new ArrayList<>(
        Arrays.asList(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
        )
    );

    public static ArrayList<MainMenuItem> getDatas() {
        ArrayList<MainMenuItem> res = new ArrayList<>();
        for (int i = 0; i < IMG_RESOURCES.size(); i++) {
            MainMenuItem item = new MainMenuItem(IMG_RESOURCES.get(i), "Menu item " + i, "Description");
            res.add(item);
        }

        return res;
    }
}
