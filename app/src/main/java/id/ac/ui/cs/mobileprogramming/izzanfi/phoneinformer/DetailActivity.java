package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detailActivityIntent = getIntent();
        String detailTitle = detailActivityIntent.getStringExtra("title");
        String detailDesc = detailActivityIntent.getStringExtra("desc");

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        fragment.displayDetails(detailTitle, detailDesc);
    }
}
