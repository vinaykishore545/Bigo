package in.www.dryrapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import in.www.dryrapp.ui.gallery.Enter_DetailsFragment;
import in.www.dryrapp.ui.home.HomeFragment;
import in.www.dryrapp.ui.send.DriverDetailsFragment;
import in.www.dryrapp.ui.share.ServicesFragment;
import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_driver_details, R.id.nav_enter_details,
                 R.id.nav_services, R.id.nav_order_overview)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        FragmentManager fragmentManager =getSupportFragmentManager();
        if(id==R.id.nav_enter_details)
        {

            fragmentManager.beginTransaction().replace(R.id.container,new Enter_DetailsFragment()).commit();
        }
        if(id==R.id.nav_driver_details)
        {

            fragmentManager.beginTransaction().replace(R.id.container,new DriverDetailsFragment()).commit();
        }
        if(id==R.id.nav_order_overview)
        {

            fragmentManager.beginTransaction().replace(R.id.container,new OrderOverviewFragment()).commit();
        }

        if(id==R.id.nav_services)
        {

            fragmentManager.beginTransaction().replace(R.id.container,new ServicesFragment()).commit();
        }

        if(id==R.id.nav_home)
        {

            fragmentManager.beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        }





        return false;
    }
    }

