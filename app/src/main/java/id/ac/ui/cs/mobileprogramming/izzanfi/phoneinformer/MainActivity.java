package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements Communicable {

    private boolean mIsDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDualPane = false;
        setContentView(R.layout.activity_main);
        View fragmentDetailView = findViewById(R.id.detailMenuFragment);

        if (fragmentDetailView != null) {
            mIsDualPane = true;
        }
    }

    @Override
    public void displayDetails(String title, String description) {
        if (mIsDualPane) {
            //TODO: Implement dual pane view (for landscape)
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.detailMenuFragment);
            detailFragment.displayDetails(title, description);
        } else {
            //TODO: Implement single pane view (for portrait)
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("desc", description);
            startActivity(intent);
        }
    }

}