package in.www.dryrapp.ui.gallery;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import java.util.Calendar;

import in.www.dryrapp.MainActivity;
import in.www.dryrapp.R;
import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;

public class Enter_DetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private GalleryViewModel galleryViewModel;
    private TextView dateText,timeText;
    int hour,mint;
    Calendar currentTime;
    int clickcount=0;
    TextView txt1;
    TextView duration;
    String name;
    EditText car_name,car_number,car_location,pick1,drop1;
    TextView tx123;
    String format,myur;


    ArrayAdapter<CharSequence> adapter;
    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_enter_details, container, false);
        dateText =(TextView)root. findViewById(R.id.enter_date);
        ImageView img=(ImageView)root. findViewById(R.id.calender);

        timeText=(TextView)root. findViewById(R.id.enter_time);
        car_name= (EditText)root.findViewById(R.id.carname);

        car_number=(EditText)root.findViewById(R.id.carnumber);
        car_location=(EditText)root.findViewById(R.id.location);
        pick1=(EditText)root.findViewById(R.id.pic);
        drop1=(EditText)root.findViewById(R.id.drop);

           try {
               Bundle bundle= getArguments();
               name =bundle.getString("it1");
               Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();
           }
           catch (Exception e)
           {
              Toast.makeText(getContext(),"mesage iw sornd ",Toast.LENGTH_SHORT).show();
           }

        /*Bundle bundle= getArguments();
        name =bundle.getString("it1");
        Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();*/

        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        mint=currentTime.get(Calendar.MINUTE);
        selectTimeFormate(hour);
        ImageView arror = (ImageView)root.findViewById(R.id.im);
          duration = (TextView)root.findViewById(R.id.duration);
      /*  Button id=(Button)root.findViewById(R.id.enter);
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time=dateText.toString().trim();
                String date=timeText.toString().trim();
                String duration1 =duration.toString().trim();
                String item = myur.trim();
                Toast.makeText(getContext(),date,Toast.LENGTH_SHORT).show();
            }
        });*/
        arror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                lp.gravity = Gravity.CENTER;
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(null);
                dialog.show();
                ImageView plus1=(ImageView)dialog.findViewById(R.id.plus);
                ImageView min1=(ImageView)dialog.findViewById(R.id.min);
                TextView ok = (TextView)dialog.findViewById(R.id.ok);
                final TextView tv=(TextView)dialog.findViewById(R.id.txt);
                tv.setText(duration.getText());
                plus1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickcount++;
                        tv.setText(Integer.toString(clickcount));

                    }
                });
                min1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(clickcount>0)
                            clickcount--;
                        tv.setText(Integer.toString(clickcount));


                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        duration.setText(tv.getText());
                    }
                });


            }
        });



      /*  final Spinner spinner1 =(Spinner)root.findViewById(R.id.select_car);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.car_type, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);*/
      /* spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String myurl = adapterView.getItemAtPosition(i).toString();
              *//* Toast.makeText(getContext(),myurl,Toast.LENGTH_SHORT).show();*//*
           }
       });*/

        timeText.setText(hour+ ":" +mint+" "+ format);
        ImageView img1 =(ImageView)root. findViewById(R.id.time);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int mint) {
                        selectTimeFormate(hour);
                        timeText.setText(hour+ ":" +mint + " " +format);
                    }
                },hour,mint,true);
                timePickerDialog.show();

            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        return root;
    }
    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE));

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int datOfMonth) {

        String date=month+1 +"/"+ datOfMonth +"/" + year;
        dateText.setText(date);
    }

    public  void selectTimeFormate(int hour)
    {
        if(hour == 0)
        {
            hour+=12;
            format="Am";
        }else if(hour ==12)
        {
            format="pm";
        }else if(hour>12)
        {
            hour -=12;
            format = "pm";

        }else
        {
            format="Am";
        }

    }

  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }*/
  @Override
  public void onStart() {
      super.onStart();
      addItemsOnSpinner();
         button();

  }

    private void button() {
      Button id = (Button)requireView().findViewById(R.id.enter);
      id.setOnClickListener(this);

    }

    public void addItemsOnSpinner() {
             Spinner spinner1 =(Spinner)requireView().findViewById(R.id.select_car);
         /*Spinner spinner1 =(Spinner)root.findViewById(R.id.select_car);*/
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.car_type, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            myur = adapterView.getItemAtPosition(i).toString();




     /* if(myur!=adapterView.getItemAtPosition(0).toString()) {

          myur = adapterView.getItemAtPosition(i).toString();
          Toast.makeText(getContext(), myur, Toast.LENGTH_SHORT).show();
      }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
      Toast.makeText(getContext(),"no item is selected",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {

           String it1=dateText.getText().toString().trim();
           String it2=timeText.getText().toString().trim();
           String it3 = duration.getText().toString().trim();
           String it4 =car_number.getText().toString().trim();
           String it5=car_number.getText().toString().trim();
           String it=car_location.getText().toString().trim();
           String car_pick1=pick1.getText().toString().trim();
           String car_drop1=drop1.getText().toString().trim();

           String name1=name.trim();
       String item = myur.trim();

        if (it1.isEmpty()) {
            dateText.setError("select the date");
            dateText.requestFocus();
            return;
        }
        if (it2.isEmpty()) {
            timeText.setError("select the time");
            timeText.requestFocus();
            return;
        }
        if (it3.isEmpty()) {
            duration.setError("select the duration");
            duration.requestFocus();
            return;
        }
        if (it.isEmpty()) {
            car_location.setError("select the date");
            car_location.requestFocus();
            return;
        }




        OrderOverviewFragment orderOverviewFragment = new OrderOverviewFragment();

        Bundle bundle= new Bundle();
        bundle.putString("it1",it1);
        bundle.putString("it2",it2);
        bundle.putString("it3",it3);
        bundle.putString("it4",it4);
        bundle.putString("it5",it5);
       bundle.putString("it",item);
        bundle.putString("loc",it);
        bundle.putString("car_pick",car_pick1);
        bundle.putString("car_drop",car_drop1);
          bundle.putString("service",name1);
        orderOverviewFragment.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.container,orderOverviewFragment).commit();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.nav_home, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }
}