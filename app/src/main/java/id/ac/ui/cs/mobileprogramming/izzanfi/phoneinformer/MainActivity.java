package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.DetailFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity implements Communicable {

    private boolean mIsDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDualPane = false;
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        FragmentManager mainActivityFm = getSupportFragmentManager();
        FragmentTransaction mainActivityFt = mainActivityFm.beginTransaction();
        mainActivityFt.replace(R.id.menuFragment, new MenuFragment());
        mainActivityFt.commit();

        View fragmentDetailView = findViewById(R.id.detailMenuFragment);
        if (fragmentDetailView != null) {
            mIsDualPane = true;
        }
    }

    @Override
    public void displayDetails(String title, String description) {
        if (mIsDualPane) {
            //TODO: Implement dual pane view (for landscape)
            FragmentManager mainActivityFm = getSupportFragmentManager();
            FragmentTransaction mainActivityFt = mainActivityFm.beginTransaction();
            mainActivityFt.replace(R.id.detailMenuFragment, DetailFragment.newInstance(title, description));
            mainActivityFt.commit();

        } else {
            //TODO: Implement single pane view (for portrait)
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("desc", description);
            startActivity(intent);
        }
    }

}