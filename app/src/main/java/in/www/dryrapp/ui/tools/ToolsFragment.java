package in.www.dryrapp.ui.tools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import in.www.dryrapp.Api;
import in.www.dryrapp.ApiClient;
import in.www.dryrapp.DriverId;
import in.www.dryrapp.Example;
import in.www.dryrapp.LoginActivity;
import in.www.dryrapp.R;
import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private final String Url="order/booking";
    String  st7,stid;
    private Api apiInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.driverid, container, false);
        TextView tx=(TextView)root.findViewById(R.id.t2);
        tx.setText(OrderOverviewFragment.getText());
        st7=tx.getText().toString().trim();

        TextView tx1=(TextView)root.findViewById(R.id.idDriver);
        tx1.setText(OrderOverviewFragment.getText());
        stid=tx1.getText().toString().trim();
       String sr=Url+st7;
      /*  Api service = ApiClient.getClient().create(Api.class);
        Call<List<DriverId>> userCall = service.creatDriver(sr,stid);
        userCall.enqueue(new Callback<List<DriverId>>() {
            @Override
            public void onResponse(Call<List<DriverId>> call, Response<List<DriverId>> response) {
                Toast.makeText(getContext(),String.valueOf(response.body().get(0).getResponse()), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<DriverId>> call, Throwable t) {

            }
        });
*/


        return root;
    }
}

