package in.www.dryrapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import in.www.dryrapp.ui.gallery.Enter_DetailsFragment;
import in.www.dryrapp.ui.share.ServicesFragment;
import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Personal> personals;
    Context context;
    static String tx,data;
    public RecyclerAdapter(List<Personal> personals, Context context)
    {
        this.personals=personals;
        this.context = context;


    }
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_services,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, final int position) {

      /*  holder.id.setText(personals.get(position).getCategoryId());*/
        holder.name.setText(personals.get(position).getCategoryName());
        holder.minprice.setText(personals.get(position).getMinimumPrice());
        holder.pricetype.setText(personals.get(position).getPriceType());
        Glide.with(context).load(personals.get(position).getCategoryImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tx=personals.get(position).getCategoryId();
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
        return personals.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
       TextView id,name,minprice,maxprice,pricetype,descrip;
       Button bt;
       ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
           /* id =itemView.findViewById(R.id.id);*/
            name=itemView.findViewById(R.id.name);
            minprice=itemView.findViewById(R.id.minprice);
            pricetype=itemView.findViewById(R.id.pricetype);
            img=itemView.findViewById(R.id.img);
            bt=itemView.findViewById(R.id.selct);



        }
    }

}
