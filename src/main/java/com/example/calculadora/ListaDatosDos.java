package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculadora.Adapters.ListaDatosDosAdapter;
import com.example.calculadora.Interface.JsonPlaceHolderApi;
import com.example.calculadora.Models.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDatosDos extends AppCompatActivity {

    private Button regresar;
    private ArrayList<Posts> posts;
    private ListaDatosDosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_datos2);
        regresar = (Button) findViewById(R.id.regresar2);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarAct1();
            }
        });

        posts = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.jsonRecViev);
        adapter = new ListaDatosDosAdapter(posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void getPosts2() {
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
                    toast("Error en la solicitud: " + response.message());
                    return;
                }
                List<Posts> postsList = response.body();
                for (Posts p : postsList) {
                    posts.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                toast("Error en la solicitud: " + t.getCause());
            }

        });
    }

    public void deletePosts2() {
        posts.clear();
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get2:
                getPosts2();
                break;
            case R.id.delete2:
                deletePosts2();
                break;
            case R.id.regresar2:
                regresarAct1();
                break;
        }
    }

    public void regresarAct1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
