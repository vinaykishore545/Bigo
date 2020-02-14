package in.www.dryrapp.ui.home;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import in.www.dryrapp.R;
import in.www.dryrapp.ui.send.DriverDetailsFragment;
import in.www.dryrapp.ui.share.ServicesFragment;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private GoogleMap mMap;
    LocationManager locationManager;
    String provider;

    FusedLocationProviderClient fusedLocationProviderClient;
    private  static final  int REQUEST_CODE=101;
    private Location currentLocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);


        Button bt=(Button)root.findViewById(R.id.sub);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ServicesFragment servicesFragment=new ServicesFragment();
               FragmentManager manager=getFragmentManager();
               manager.beginTransaction().replace(R.id.nav_host_fragment,servicesFragment).commit();


               /* FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.container,new ServicesFragment()).commit();*/

            }
        });

        return root;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng bhima = new LatLng(16.544396, 81.519695);
        mMap.addMarker(new MarkerOptions().position(bhima).title("Marker in bhimavarama"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bhima,18));

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

}