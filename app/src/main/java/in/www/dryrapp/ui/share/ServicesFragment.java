package in.www.dryrapp.ui.share;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import in.www.dryrapp.Api;
import in.www.dryrapp.ApiClient;
import in.www.dryrapp.Bussiness;
import in.www.dryrapp.MainActivity;
import in.www.dryrapp.Personal;
import in.www.dryrapp.R;
import in.www.dryrapp.Recycler;
import in.www.dryrapp.RecyclerAdapter;
import in.www.dryrapp.ui.gallery.Enter_DetailsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ServicesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    /* private RecyclerAdapter adapter;*/
    private List<Personal> personals;
    private List<Bussiness> bussinesses;
    LinearLayoutManager llmV, llmH;
    Context c;
    static String tx;
    private Api apiInterface;
    private ShareViewModel shareViewModel;
    private int buttonState = 1;
    TextView bt1,bt2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.personal, container, false);
        bt1= root.findViewById(R.id.personal);
        bt2= root.findViewById(R.id.bussiness);
        recyclerView=root.findViewById(R.id.rec);
        bt1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        bt2.setBackgroundColor(getResources().getColor(R.color.white));
        apiInterface = ApiClient.getClient().create(Api.class);
        Call<List<Personal>> call = apiInterface.getPersonal();
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call.enqueue(new Callback<List<Personal>>() {
            @Override
            public void onResponse(Call<List<Personal>> call, Response<List<Personal>> response) {
                Log.i("recycler","wthin in method");
                personals =response.body();
progressDialog.dismiss();
                /*Toast.makeText(getContext(),String.valueOf(personals.size()),Toast.LENGTH_LONG).show();
                Log.d("message", String.valueOf((personals)));*/
                RecyclerAdapter  adapter =new RecyclerAdapter(personals,getContext());
                layoutManager = new LinearLayoutManager(getContext());
                llmV = new LinearLayoutManager(c);
                llmV.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(llmV);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Personal>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                bt1.setBackgroundColor(getResources().getColor(R.color.colorfdff));
                bt2.setBackgroundColor(getResources().getColor(R.color.white));
                apiInterface = ApiClient.getClient().create(Api.class);
                Call<List<Personal>> call = apiInterface.getPersonal();
             call.enqueue(new Callback<List<Personal>>() {
                 @Override
                 public void onResponse(Call<List<Personal>> call, Response<List<Personal>> response) {
                     Log.i("recycler","wthin in method");
                     personals =response.body();
                     progressDialog.dismiss();
                     Toast.makeText(getContext(),String.valueOf(personals.size()),Toast.LENGTH_LONG).show();
                     Log.d("message", String.valueOf((personals)));
                     RecyclerAdapter  adapter =new RecyclerAdapter(personals,getContext());
                     layoutManager = new LinearLayoutManager(getContext());
                     llmV = new LinearLayoutManager(c);
                     llmV.setOrientation(LinearLayoutManager.VERTICAL);
                     recyclerView.setAdapter(adapter);
                     recyclerView.setLayoutManager(llmV);
                     recyclerView.setHasFixedSize(true);
                 }

                 @Override
                 public void onFailure(Call<List<Personal>> call, Throwable t) {
                     Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                 }
             });


            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(getResources().getColor(R.color.colorfdff));
                bt1.setBackgroundColor(getResources().getColor(R.color.white));
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                apiInterface = ApiClient.getClient().create(Api.class);
                Call<List<Bussiness>> call = apiInterface.getBussiness();
                call.enqueue(new Callback<List<Bussiness>>() {
                    @Override
                    public void onResponse(Call<List<Bussiness>> call, Response<List<Bussiness>> response) {
                        Log.i("recycler","wthin in method");
                       progressDialog.dismiss();
                        bussinesses =response.body();
                        Recycler adapter =new Recycler(bussinesses,getContext());
                        layoutManager = new LinearLayoutManager(getContext());
                        llmV = new LinearLayoutManager(c);
                        llmV.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(llmV);
                        recyclerView.setHasFixedSize(true);
                    }

                    @Override
                    public void onFailure(Call<List<Bussiness>> call, Throwable t) {
                        Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        return root;
    }

}
