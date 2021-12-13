package com.ryujinknee.mycars;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private List<CarsModel> carsModelList;
    MyCarAdapter caradapter;
    RecyclerView rvCar;
    ImageView icon_search;
    EditText search_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        icon_search=(ImageView) findViewById(R.id.search_icon);
        carsModelList = new ArrayList<>();
        rvCar=(RecyclerView)findViewById(R.id.rv) ;
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);


        // button click

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custonview, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();


        dialogView.findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text=dialogView.findViewById(R.id.carname_edit);
                String text=search_text.getText().toString();

                if(text!=null){
                    getCarsByName(text);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Enter Car Name.",Toast.LENGTH_LONG).show();
                }
            }

            private void getCarsByName(String text) {
                Call<CarsModel> call = RetrofitClient.getInstance().getMyApi().getCarByName(text);
                call.enqueue(new Callback<CarsModel>() {
                    @Override
                    public void onResponse(Call<CarsModel> call, Response<CarsModel> response) {
                        // CarsModel carModel =new CarsModel();
                        //carsModelList=response.body().getCars();
                        List<Car> mycars= response.body().getCars();
                        if(response.body().getCars().size()==0){
                            Toast.makeText(getApplicationContext(), "Sorry no car we have by this name", Toast.LENGTH_LONG).show();
                        }else {
                            caradapter = new MyCarAdapter(HomeActivity.this, mycars);

                            rvCar.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                            rvCar.setAdapter(caradapter);
                            caradapter.notifyDataSetChanged();

                            //alertDialog.cancel();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<CarsModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                    }




                });

            }
        });

        /// close







        icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custonview, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                dialogView.findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        search_text=dialogView.findViewById(R.id.carname_edit);
                        String text=search_text.getText().toString();

                        if(text!=null){
getCarsByName(text);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please Enter Car Name.",Toast.LENGTH_LONG).show();
                        }
                    }

                    private void getCarsByName(String text) {
                        Call<CarsModel> call = RetrofitClient.getInstance().getMyApi().getCarByName(text);
                        call.enqueue(new Callback<CarsModel>() {
                            @Override
                            public void onResponse(Call<CarsModel> call, Response<CarsModel> response) {
                                // CarsModel carModel =new CarsModel();
                                //carsModelList=response.body().getCars();
                                List<Car> mycars= response.body().getCars();
                                if(response.body().getCars().size()==0){
                                    Toast.makeText(getApplicationContext(), "Sorry no car we have by this name", Toast.LENGTH_LONG).show();
                                }else {
                                    caradapter = new MyCarAdapter(HomeActivity.this, mycars);

                                    rvCar.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                                    rvCar.setAdapter(caradapter);
                                    caradapter.notifyDataSetChanged();

                                    //alertDialog.cancel();
                                    alertDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<CarsModel> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                            }




                        });

                    }
                });
            }
        });







//getCars();


    }

    private void getCars() {

        Call<CarsModel> call = RetrofitClient.getInstance().getMyApi().getAllCars();
        call.enqueue(new Callback<CarsModel>() {
            @Override
            public void onResponse(Call<CarsModel> call, Response<CarsModel> response) {
               // CarsModel carModel =new CarsModel();
                //carsModelList=response.body().getCars();
                List<Car> mycars= response.body().getCars();
                caradapter = new MyCarAdapter(HomeActivity.this, mycars);
                rvCar.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                rvCar.setAdapter(caradapter);
            }

            @Override
            public void onFailure(Call<CarsModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }




        });
    }
}