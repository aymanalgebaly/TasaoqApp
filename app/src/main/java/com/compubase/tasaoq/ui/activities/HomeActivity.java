package com.compubase.tasaoq.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.compubase.tasaoq.R;
import com.compubase.tasaoq.ui.fragments.AboutUsFragment;
import com.compubase.tasaoq.ui.fragments.CategoriesFragment;
import com.compubase.tasaoq.ui.fragments.FavoritesFragment;
import com.compubase.tasaoq.ui.fragments.HomeFragment;
import com.compubase.tasaoq.ui.fragments.MostSalesFragment;
import com.compubase.tasaoq.ui.fragments.TopRatedFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.cart_img)
    ImageView cartImg;
    @BindView(R.id.cart_badge)
    TextView cartBadge;
    @BindView(R.id.rel_toolbar)
    RelativeLayout relToolbar;

    int mCartItemCount = 10;
//    @BindView(R.id.imageSlider_flip)
//    ViewFlipper imageSliderFlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setupBadge();

        HomeFragment homeFragment = new HomeFragment();
        displaySelectedFragment(homeFragment);
    }


    private void setupBadge() {

        if (cartBadge != null) {
            if (mCartItemCount == 0) {
                if (cartBadge.getVisibility() != View.GONE) {
                    cartBadge.setVisibility(View.GONE);
                }
            } else {
                cartBadge.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (cartBadge.getVisibility() != View.VISIBLE) {
                    cartBadge.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            HomeFragment homeFragment = new HomeFragment();
            displaySelectedFragment(homeFragment);
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {

            FavoritesFragment favoritesFragment = new FavoritesFragment();
            displaySelectedFragment(favoritesFragment);

        } else if (id == R.id.nav_categories) {

            CategoriesFragment categoriesFragment = new CategoriesFragment();
            displaySelectedFragment(categoriesFragment);

        } else if (id == R.id.nav_star) {

            TopRatedFragment topRatedFragment = new TopRatedFragment();
            displaySelectedFragment(topRatedFragment);

        } else if (id == R.id.nav_most_sales) {

            MostSalesFragment mostSalesFragment = new MostSalesFragment();
            displaySelectedFragment(mostSalesFragment);

        } else if (id == R.id.nav_about_us) {

            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            displaySelectedFragment(aboutUsFragment);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.cart_img, R.id.cart_badge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cart_img:
                break;
            case R.id.cart_badge:
                break;
        }
    }
    public void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
