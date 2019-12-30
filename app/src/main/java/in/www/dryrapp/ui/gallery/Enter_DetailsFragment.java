package in.www.dryrapp.ui.gallery;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import in.www.dryrapp.R;

public class Enter_DetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private GalleryViewModel galleryViewModel;
    private TextView dateText,timeText;
    int hour,mint;
    Calendar currentTime;
    int clickcount=0;
    TextView txt1;
    TextView tx123;
    String format;
    ArrayAdapter<CharSequence> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_enter_details, container, false);
        dateText =(TextView)root. findViewById(R.id.enter_date);
        ImageView img=(ImageView)root. findViewById(R.id.calender);
        timeText=(TextView)root. findViewById(R.id.enter_time);
        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        mint=currentTime.get(Calendar.MINUTE);
        selectTimeFormate(hour);
        ImageView arror = (ImageView)root.findViewById(R.id.im);
        final TextView duration = (TextView)root.findViewById(R.id.duration);
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
                dialog.getWindow().setAttributes(lp);
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



        Spinner spinner1 =(Spinner)root.findViewById(R.id.select_car);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.car_type, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

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

        String date=month +"/"+ datOfMonth +"/" + year;
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



}