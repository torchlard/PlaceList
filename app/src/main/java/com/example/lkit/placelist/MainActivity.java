package com.example.lkit.placelist;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    set menu button
    final ActionBar ab = getSupportActionBar();
    if(ab != null){
      ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
      ab.setDisplayHomeAsUpEnabled(true);
    }

    ViewPager viewpager = findViewById(R.id.viewPager);
    if(viewpager != null){
      setupViewPager(viewpager);
    }

//    setup tab
    TabLayout tabLayout = findViewById(R.id.tab_view);
    tabLayout.setupWithViewPager(viewpager);

//    set drawer
    mDrawerLayout = findViewById(R.id.drawer_layout);
    NavigationView navView = findViewById(R.id.nav_view);
    setupDrawerContent(navView);


  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu){
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
//      press menu button, open drawer
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupDrawerContent(NavigationView navView){
    navView.setNavigationItemSelectedListener( (menuItem) -> {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    });
  }

  private void setupViewPager(ViewPager viewpager){
    Adapter adapter = new Adapter(getSupportFragmentManager());
    PageListFragment pf1 = new PageListFragment();
    PageListFragment pf2 = new PageListFragment();
    PageListFragment pf3 = new PageListFragment();
    pf1.setArguments(setarg(1));
    pf2.setArguments(setarg(2));
    pf3.setArguments(setarg(3));

    adapter.addFragment(pf1, "Page 1");
    adapter.addFragment(pf2, "Page 2");
    adapter.addFragment(pf3, "Page 3");
    viewpager.setAdapter(adapter);
  }

  private Bundle setarg(int index){
    Bundle args = new Bundle();
    args.putInt("index",index);

    return args;
  }

  static class Adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragment = new ArrayList<>();
    private final List<String> mTitle = new ArrayList<>();

    private Adapter(FragmentManager fm){ super(fm); }
    private void addFragment(Fragment fm, String title){
      mFragment.add(fm);
      mTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {return mFragment.get(position);}

    @Override
    public int getCount(){ return mFragment.size();}

    @Override
    public CharSequence getPageTitle(int position){
      return mTitle.get(position);
    }
  }

}






