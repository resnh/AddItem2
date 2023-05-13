package com.example.additem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.DriverPropertyInfo;
import java.util.List;

import kotlinx.coroutines.MainCoroutineDispatcher;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter stadiumArrayAdapter;
    ListView listAll;


    Button AddStadium,ViewAll,delete;

    DataBaseHelper dataBaseHelper;
 private DataBaseHandler dataBaseHandler;
    private RecyclerView objectRecyclerView;
RVadapter objectRVadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
try{
    objectRecyclerView= findViewById(R.id.imagesRV);

    dataBaseHandler=new DataBaseHandler(this);
}
catch(Exception e){

}



       AddStadium=findViewById(R.id.AddStadium);
       ViewAll=findViewById(R.id.ViewAll);
        listAll=findViewById(R.id.ListView);
        delete=findViewById(R.id.Delete);
        AddStadium.setOnClickListener( new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), AddItemForm.class);
                                            startActivity(intent);
                                        }
                                    });
        ViewAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                ShowStadiumsOnListView(dataBaseHelper);

                try{
                    objectRVadapter = new RVadapter(dataBaseHandler.getAllImagesData());
                    objectRecyclerView.setHasFixedSize(true);
                    objectRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    objectRecyclerView.setAdapter(objectRVadapter);
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this , e.getMessage(), Toast.LENGTH_SHORT).show();

                }



                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        listAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemModel clickedItem= (ItemModel) parent.getItemAtPosition(position);
                dataBaseHelper=new DataBaseHelper(MainActivity.this);
                delete.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dataBaseHelper.deleteOne(clickedItem);
                        ShowStadiumsOnListView(dataBaseHelper);
                        Toast.makeText(MainActivity.this,"Deleted "+ clickedItem.getName().toString() + " Stadium", Toast.LENGTH_SHORT).show();
                    }
                });


            }//end ontiemclick
        }); //Added By Reema










    }
    private void ShowStadiumsOnListView(DataBaseHelper dataBaseHelper) {
        stadiumArrayAdapter  = new ArrayAdapter<ItemModel>(MainActivity.this , android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        listAll.setAdapter(stadiumArrayAdapter);
    }




}