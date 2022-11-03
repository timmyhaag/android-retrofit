//Created by Timothy Haag


package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public CommentAdapter adapter;
    public RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Comments>> call = service.getAllComments();
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                progressDialog.dismiss();
                dataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "An error occurred. Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dataList(List<Comments> commentsList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CommentAdapter(commentsList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ItemTouchListener(MainActivity.this, recyclerView, (view, position) -> {
            Intent intent = new Intent(view.getContext(), DetailedView.class);
            String post = adapter.getItemDesc(position);
            intent.putExtra("postDesc", post);
            view.getContext().startActivity(intent);

        }));
    }
}