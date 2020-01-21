package in.www.dryrapp.ui.slideshow;

import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import java.io.IOException;
import java.util.List;

import in.www.dryrapp.Api;
import in.www.dryrapp.ApiClient;
import in.www.dryrapp.Example;
import in.www.dryrapp.LoginActivity;
import in.www.dryrapp.Orderview;
import in.www.dryrapp.R;
import in.www.dryrapp.Recycler;
import in.www.dryrapp.RecyclerAdapter;
import in.www.dryrapp.ui.gallery.Enter_DetailsFragment;
import in.www.dryrapp.ui.home.HomeFragment;
import in.www.dryrapp.ui.send.DriverDetailsFragment;
import in.www.dryrapp.ui.tools.ToolsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderOverviewFragment extends Fragment implements View.OnClickListener {

    private SlideshowViewModel slideshowViewModel;
    TextView date,time,duration,address,type,cost1,drivername,number,tx,pick4,drop4,idser4;
    Integer st=40;
    String carin,carup,ser,id,st3;
    private Api apiInterface;
    static String rul;
   TextView yes1,no1;
    Context c;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_order_overview, container, false);
       date=root.findViewById(R.id.date);
        time=root.findViewById(R.id.time);
        pick4=(TextView)root.findViewById(R.id.carpick);
        drop4=(TextView)root.findViewById(R.id.carup);
        idser4=(TextView)root.findViewById(R.id.idser);

        duration =root.findViewById(R.id.hour);
        drivername=root.findViewById(R.id.car_name);
        number=root.findViewById(R.id.car_number);


        TextView tx=(TextView)root.findViewById(R.id.tv);
        tx.setText(LoginActivity.getText());
        st3=tx.getText().toString().trim();



        address=root.findViewById(R.id.address);
        type=root.findViewById(R.id.type);
        cost1=root.findViewById(R.id.cost);
        try {
            Bundle bundle= getArguments();
            final   String name =bundle.getString("it1");
            String time1=bundle.getString("it2");
            String duration1=bundle.getString("it3");
            String location =bundle.getString("loc");
            String type1=bundle.getString("it");
            String carname=bundle.getString("it4");
            String carnumber=bundle.getString("it5");
             ser=bundle.getString("service");
             carin=bundle.getString("car_pick");
             idser4.setText(ser);
             carup=bundle.getString("car_drop");
            Toast.makeText(getContext(),ser,Toast.LENGTH_SHORT).show();
           number.setText(carnumber);
           drivername.setText(carname);
            date.setText(name);
            pick4.setText(carin);
            drop4.setText(carup);
            time.setText(time1);
            int intVariableName = Integer.parseInt(duration1);

            int sum =intVariableName * st;
            cost1.setText(Integer.toString(sum));
             duration.setText(duration1);
           address.setText(location);
            type.setText(type1);

        }
        catch (Exception e)
        {
            System.out.println("Error " + e.getMessage());
        }







        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        button();

    }


    private void button() {

        Button ok=(Button)requireView().findViewById(R.id.submit);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      /*  String id1=id.trim();*/
            String date2 = date.getText().toString().trim();
            String time2 = time.getText().toString().trim();
            String duration2 = duration.getText().toString().trim();
            String address2 = address.getText().toString().trim();
            String type1 = type.getText().toString().trim();
            String cost2 = cost1.getText().toString().trim();
            String car_pick2 = carin.trim();
            String car_drop2 = carup.trim();
            String carnumber = number.getText().toString().trim();
            String driver_name = drivername.getText().toString().trim();
            String serv_id = ser.trim();

        /*FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.container,new ToolsFragment()).commit();
        Toast.makeText(getContext(),"welecome",Toast.LENGTH_SHORT).show();*/
            apiInterface = ApiClient.getClient().create(Api.class);
            Call<List<Orderview>> userCall = apiInterface.creatOrder(st3, car_pick2, car_drop2, serv_id, date2, time2, duration2, driver_name, carnumber, type1, address2, cost2);
            userCall.enqueue(new Callback<List<Orderview>>() {
                @Override
                public void onResponse(Call<List<Orderview>> call, Response<List<Orderview>> response) {
                    Toast.makeText(getContext(),response.body().get(0).getOrderId(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(), String.valueOf(response.body().get(0).getResponse()), Toast.LENGTH_SHORT).show();
                    if (response.body().get(0).getResponse() == 1) {
                        try {
                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView(R.layout.dialog);
                            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                            lp.copyFrom(dialog.getWindow().getAttributes());
                            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            lp.gravity = Gravity.CENTER;
                            dialog.setCancelable(true);

                            dialog.setCanceledOnTouchOutside(true);
                            dialog.getWindow().setBackgroundDrawable(null);
                            dialog.show();
                            yes1 = dialog.findViewById(R.id.yes);
                            yes1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                    FragmentManager manager = getFragmentManager();
                                    manager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();

                                    Toast.makeText(getContext(), "we ", Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                        catch (Exception e)
                        {
                               Toast.makeText(getContext(),"welecjcb",Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getContext(),"respone is baba",Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<List<Orderview>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

    }
    public static String getText(){
        String data = rul.trim();
        return data;
    }
}