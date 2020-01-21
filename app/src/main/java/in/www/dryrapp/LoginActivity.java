package in.www.dryrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    static String tx;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView register=findViewById(R.id.register);
        final EditText mobileF=findViewById(R.id.mobile);
        final EditText passwordF=findViewById(R.id.password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        Button sign=findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("please wait");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String mobile=mobileF.getText().toString().trim();
                String password=passwordF.getText().toString().trim();
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
                if (password.length() < 4) {
                    passwordF.setError("password  alteast six character is required");
                    passwordF.requestFocus();
                    return;
                }
                Api service = ApiClient.getClient().create(Api.class);
                Call<List<Login>> userCall = service.creatLogin(mobile,password);
                userCall.enqueue(new Callback<List<Login>>() {
                    @Override
                    public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                        if(response.body().get(0).getResponse()==1)
                        {
                            progressDialog.dismiss();
                            tx=response.body().get(0).getUserId();
                            Intent i =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"User Sucessfully login",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"User not login",Toast.LENGTH_LONG).show();
                              progressDialog.dismiss();
                        }



                        /* Toast.makeText(LoginActivity.this, "" + response.body().get(0).getResponse(), Toast.LENGTH_SHORT).show();*/

                    }

                    @Override
                    public void onFailure(Call<List<Login>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
    public static String getText(){
        String data = tx.trim();
        return data;
    }
}
