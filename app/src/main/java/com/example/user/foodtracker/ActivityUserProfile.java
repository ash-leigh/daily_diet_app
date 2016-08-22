package com.example.user.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

/**
 * Created by user on 21/08/2016.
 */
public class ActivityUserProfile extends AppCompatActivity {

//    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
//        bottomBar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabSelectedListener() {
//
//            @Override
//            public void onMenuItemSelected(int itemId) {
//                switch (itemId) {
//                    case R.id.home_item:
//                        Log.d("TEST: ", "HOME");
//                        Intent homeIntent = new Intent(ActivityUserProfile.this, ActivityMain.class);
//                        startActivity(homeIntent);
//                        break;
//                    case R.id.profile_item:
//                        Log.d("TEST: ", "USER");
//                        Intent profileIntent = new Intent(ActivityUserProfile.this, ActivityUserProfile.class);
//                        startActivity(profileIntent);
//                        break;
//                    case R.id.meal_log_item:
//                        Log.d("TEST: ", "LOG");
//                        Intent logIntent = new Intent(ActivityUserProfile.this, ActivityMealLog.class);
//                        startActivity(logIntent);
//                        break;
//                    case R.id.analysis_item:
//                        Log.d("TEST: ", "ANALYSIS");
//                        Intent analysisIntent = new Intent(ActivityUserProfile.this, ActivityDietAnalysis.class);
//                        startActivity(analysisIntent);
//                        break;
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home_item:
                Intent homeIntent = new Intent(ActivityUserProfile.this, ActivityMain.class);
                startActivity(homeIntent);
                return true;
            case R.id.profile_item:
                Intent userIntent = new Intent(ActivityUserProfile.this, ActivityUserProfile.class);
                startActivity(userIntent);
                return true;
            case R.id.meal_log_item:
                Intent logIntent = new Intent(ActivityUserProfile.this, ActivityMealLog.class);
                startActivity(logIntent);
                return true;
            case R.id.analysis_item:
                Intent analysisIntent = new Intent(ActivityUserProfile.this, ActivityDietAnalysis.class);
                startActivity(analysisIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
