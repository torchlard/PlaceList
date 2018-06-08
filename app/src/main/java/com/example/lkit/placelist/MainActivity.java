package com.example.lkit.placelist;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  private DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mDrawerLayout = findViewById(R.id.drawer_layout);
    NavigationView navView = findViewById(R.id.nav_view);
    setupDrawerContent(navView);

  }

  private void setupDrawerContent(NavigationView navView){
    navView.setNavigationItemSelectedListener( (menuItem) -> {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
      }
    );
  }

}
