package in.www.dryrapp.ui.share;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import in.www.dryrapp.R;
public class ServicesFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private int buttonState = 1;
    TextView bt1,bt2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_services, container, false);
        bt1= root.findViewById(R.id.personal);
        bt2= root.findViewById(R.id.bussiness);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt2.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt1.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        return root;
    }
}
