package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedView extends AppCompatActivity {
    ProgressDialog progressDialogNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        progressDialogNew = new ProgressDialog(DetailedView.this);
        progressDialogNew.setMessage("Loading....");
        progressDialogNew.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Comments>> call = service.getAllComments();

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                progressDialogNew.dismiss();

                Intent intent = getIntent();
                String postComments = intent.getStringExtra("postDesc");
                TextView textView = findViewById(R.id.titleNew);
                textView.setText(postComments);
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                progressDialogNew.dismiss();
                Toast.makeText(DetailedView.this, "An error occurred. Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }
}