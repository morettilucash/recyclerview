package com.example.calculadora.lista_datos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora.Interface.JsonPlaceHolderApi;
import com.example.calculadora.MainActivity;
import com.example.calculadora.Models.Posts;
import com.example.calculadora.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDatos extends AppCompatActivity {

    private ArrayList<Posts> posts;
    private Button regresar;
    private MyListAdapter adapter;
    private TextView jsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_datos);

        jsonTextView = findViewById(R.id.jsonTextView);

        regresar = (Button) findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarAct1();
            }
        });

        posts = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.jsonRecViev);
        adapter = new MyListAdapter(posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getPosts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()) {
                    System.out.println("Error de solicitud: " + response.code());
                    return;
                }

                List<Posts> postsList = response.body();
                for (Posts post : postsList) {
                    String content = "";
                    content += "userId" + post.getUserId() + "\n";
                    content += "id" + post.getId() + "\n";
                    content += "title" + post.getTitle() + "\n";
                    content += "body" + post.getBody() + "\n \n";
                    jsonTextView.append(content);
                    posts.add(post);
                }
                adapter.notifyDataSetChanged();
                System.out.println("postsList: " + postsList);

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }

    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

        ArrayList<Posts> listdata;

        public MyListAdapter(ArrayList<Posts> listdata) {
            this.listdata = listdata;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.fila_post, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // final Posts p = listdata.get(position);
            holder.tvId.setText(Integer.toString(listdata.get(position).getId()));
            holder.tvTitle.setText(listdata.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvId;
            public TextView tvTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tvId = (TextView) itemView.findViewById(R.id.tvId);
                this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            }
        }
    }

    public void deletePosts() {
        jsonTextView.setText("");
        this.posts.clear();
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get:
                getPosts();
                break;
            case R.id.delete:
                deletePosts();
                break;
        }
    }

    public void regresarAct1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

