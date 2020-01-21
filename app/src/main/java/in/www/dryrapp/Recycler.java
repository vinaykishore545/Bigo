package in.www.dryrapp;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;

import in.www.dryrapp.ui.gallery.Enter_DetailsFragment;

public class Recycler extends RecyclerView.Adapter<Recycler.MyViewHolder> {
    private List<Bussiness> bussinesses;
    Context context;
    Button bt;

    static String tx;
    public Recycler(List<Bussiness> bussinesses, Context context)
    {
        this.bussinesses=bussinesses;
        this.context = context;


    }

    @Override
    public Recycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_services,parent,false);
        return new Recycler.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Recycler.MyViewHolder holder, final int position) {
        holder.name.setText(bussinesses.get(position).getCategoryName());
        holder.minprice.setText(bussinesses.get(position).getMinimumPrice());
        holder.pricetype.setText(bussinesses.get(position).getPriceType());
        Glide.with(context).load(bussinesses.get(position).getCategoryImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tx=bussinesses.get(position).getCategoryId();
                Bundle bundle= new Bundle();
                bundle.putString("it1",tx);
                Enter_DetailsFragment enter_detailsFragment=new Enter_DetailsFragment();

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().add(R.id.container,enter_detailsFragment).commit();
                enter_detailsFragment.setArguments(bundle);

            }
        });

    }
    @Override
    public int getItemCount() {
        return bussinesses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, minprice, maxprice, pricetype, descrip;
        ImageView img;
        Button bt;

        MyViewHolder(View itemView) {
            super(itemView);
            /* id =itemView.findViewById(R.id.id);*/
            name = itemView.findViewById(R.id.name);
            minprice = itemView.findViewById(R.id.minprice);
            pricetype = itemView.findViewById(R.id.pricetype);
            img = itemView.findViewById(R.id.img);
            bt=itemView.findViewById(R.id.selct);

        }
    }
}
