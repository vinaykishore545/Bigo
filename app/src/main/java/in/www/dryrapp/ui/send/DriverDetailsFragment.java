package in.www.dryrapp.ui.send;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import in.www.dryrapp.Api;
import in.www.dryrapp.ApiClient;
import in.www.dryrapp.BookingRecycler;
import in.www.dryrapp.Example;
import in.www.dryrapp.LoginActivity;
import in.www.dryrapp.Order;
import in.www.dryrapp.R;
import in.www.dryrapp.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverDetailsFragment extends Fragment  {
    TextView name,number,pick_in,pick_up,date,cost;
    ImageView img1;
    private List<Order> examples=new ArrayList<>();
    Context c;
    LinearLayoutManager llmV, llmH;
    private Api apiInterface;
    String name1,number1,pick1,pickup1,cost1,date1;
    String st,xx;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_driver_details, container, false);

        TextView tx=(TextView)root.findViewById(R.id.tx);
        recyclerView=root.findViewById(R.id.rec12);
       tx.setText(LoginActivity.getText());
         st=tx.getText().toString().trim();
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        star();

    }

    private void star() {

        /*img=requireActivity().findViewById(R.id.img);*/
        Api service = ApiClient.getClient().create(Api.class);
        Call<List<Example>> userCall = service.creatExample(st);
        userCall.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if(response.body().get(0).getOrders().get(0).getDriver()==null)
                {
                    Toast.makeText(getContext(),"driver is not assigned",Toast.LENGTH_LONG).show();
                }
                try {
                    if (response.body().get(0).getResponse() == 1) {
                        examples.clear();
                        examples = response.body().get(0).getOrders();
                        int j=response.body().get(0).getOrders().size();
                /*Toast.makeText(getContext(),String.valueOf(personals.size()),Toast.LENGTH_LONG).show();
                Log.d("message", String.valueOf((personals)));*/

                            Toast.makeText(getContext(),String.valueOf(j),Toast.LENGTH_LONG).show();

                            BookingRecycler adapter = new BookingRecycler(examples, getContext());
                            layoutManager = new LinearLayoutManager(getContext());
                            llmV = new LinearLayoutManager(c);
                            llmV.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(llmV);
                            recyclerView.setHasFixedSize(true);
                        }

       else {
                       Toast.makeText(getContext(), "driver is not registerd", Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),"driver is not register",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
    }
