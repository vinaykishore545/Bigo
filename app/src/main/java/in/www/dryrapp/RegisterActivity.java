package in.www.dryrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button ok=findViewById(R.id.ok);
        final EditText nameF=findViewById(R.id.name);
        final EditText emailF=findViewById(R.id.email);
        final EditText mobileF=findViewById(R.id.mobile);
        final EditText passwordF=findViewById(R.id.password);
        final EditText fci=findViewById(R.id.fci);
        final EditText typeF=findViewById(R.id.type);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String email=emailF.getText().toString().trim();
                String name=nameF.getText().toString().trim();
                String mobile=mobileF.getText().toString().trim();
                String password=passwordF.getText().toString().trim();
                String fcmId =fci.getText().toString().trim();
                String type=typeF.getText().toString().trim();
                if (name.isEmpty()) {
                    nameF.setError("name is required");
                    nameF.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    emailF.setError("email is required");
                    emailF.requestFocus();
                    return;
                }
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailF.setError("enter Valid Eemail");
                    emailF.requestFocus();
                    return;
                }
                if (mobile.isEmpty()) {
                    mobileF.setError("mobile is required");
                    mobileF.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passwordF.setError("password is required");
                    passwordF.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    passwordF.setError("password  alteast six character is required");
                    passwordF.requestFocus();
                    return;

                }
                if (fcmId.isEmpty()) {
                    fci.setError("fci is reqiuerd");
                    fci.requestFocus();
                    return;
                }
                if (type.isEmpty()) {
                    typeF.setError("type is required");
                    typeF.requestFocus();
                    return;

                }
                Api service = ApiClient.getClient().create(Api.class);
                Call<List<MSG>> userCall = service.createUser(name,email,mobile,password,fcmId,type);
                userCall.enqueue(new Callback<List<MSG>>() {
                    @Override
                    public void onResponse(Call<List<MSG>> call, Response<List<MSG>> response) {
                        if (response.body().get(0).getResponse()==1)
                        {
                            progressDialog.dismiss();
                            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"user sucessfuly registered",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"user not registered",Toast.LENGTH_SHORT).show();
                        }


                        Toast.makeText(getApplicationContext(), "" + response.body().get(0).getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call <List<MSG>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
