package in.www.dryrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingRecycler extends RecyclerView.Adapter<BookingRecycler.MyViewHolder> {

    private List<Order> examples;
    Context context;
    static String tx,data;
    public BookingRecycler(List<Order> examples, Context context)
    {
        this.examples=examples;
        this.context = context;


    }
    @Override
    public BookingRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookingRecycler.MyViewHolder holder, int position) {

        holder.date.setText(examples.get(position).getDate());
        holder.time.setText(examples.get(position).getTime());
        holder.pickup.setText(examples.get(position).getPickupLocation());
        holder.pickin.setText(examples.get(position).getDropLocation());
        holder.servid.setText(examples.get(position).getServiceId());
        holder.price.setText(examples.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,time,pickin,pickup,servid,price;
        Button bt;
        ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
            /* id =itemView.findViewById(R.id.id);*/
            date = itemView.findViewById(R.id.date_booking);
            time=itemView.findViewById(R.id.time_booking);
            pickin=itemView.findViewById(R.id.pickin_booking);
            pickup=itemView.findViewById(R.id.pickup_booking);
            price=itemView.findViewById(R.id.booking_price);
            servid=itemView.findViewById(R.id.booking_serid);



        }
    }
}
