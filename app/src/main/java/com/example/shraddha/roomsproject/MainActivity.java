package com.example.shraddha.roomsproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TextView mToolText;
    Fragment selectedFragment = null;
    public static BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.nav_girl:
                            selectedFragment = new GirlsRoomFragment();
                            break;
                        case R.id.nav_boys:
                            selectedFragment = new BoysRoomFragment();
                            break;
                        case R.id.nav_individuals:
                            selectedFragment = new IndividualsRoomFragment();
                            break;
                        case R.id.nav_family:
                            selectedFragment = new FamilyRoomFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase mFirebaseDatabase;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.setPersistenceEnabled(true);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        toolbar=(Toolbar) findViewById(R.id.room_toolbar);
        mToolText = (TextView) findViewById(R.id.tool_text);

        mToolText.setText("Book Your Room");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new GirlsRoomFragment()).commit();


    }

    @Override
    public void onBackPressed() {
        if(!((new GirlsRoomFragment()).getClass().equals(selectedFragment.getClass())) ) {
            View view = this.bottomNavigationView.findViewById(R.id.nav_girl);
            view.performClick();
        }
        else {

            finish();

        }
    }
}