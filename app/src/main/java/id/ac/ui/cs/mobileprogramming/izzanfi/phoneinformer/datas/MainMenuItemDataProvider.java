package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.datas;

import java.util.*;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class MainMenuItemDataProvider {

    private static ArrayList<Integer> IMG_RESOURCES = new ArrayList<>(
        Arrays.asList(
            R.drawable.round_smartphone_24,
            R.drawable.round_memory_24,
            R.drawable.round_battery_charging_full_24,
            R.drawable.round_android_24
        )
    );

    private static ArrayList<String> MENU_ITEM_NAMES = new ArrayList<>(
            Arrays.asList(
                    "Phone Information",
                    "CPU Information",
                    "Battery Information",
                    "System Information"
            )
    );

    public static ArrayList<MainMenuItem> getDatas() {
        ArrayList<MainMenuItem> res = new ArrayList<>();
        for (int i = 0; i < IMG_RESOURCES.size(); i++) {
            MainMenuItem item = new MainMenuItem(IMG_RESOURCES.get(i), MENU_ITEM_NAMES.get(i), "Description");
            res.add(item);
        }

        return res;
    }
}
