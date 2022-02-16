package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ItemActivity extends AppCompatActivity{
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();
    ;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText nameOfItem;
    private EditText urlLink;
    private Button add;
    private Button cancel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewPopUpWindow();
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(ItemActivity.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

//        TextView url = findViewById(R.id.item_url);
//
//        url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String urlToString = url.getText().toString();
//                gotoUrl(urlToString);
//            }
//        });
    }

//    private void gotoUrl(String s){
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW, uri));
//
//    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put image information id into instance
            //outState.putInt(KEY_OF_INSTANCE + i + "0", itemList.get(i).getImageSource());
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getUrl());
            // put isChecked information into instance
            outState.putBoolean(KEY_OF_INSTANCE + i + "3", itemList.get(i).getStatus());
        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String itemUrl = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
                    boolean isChecked = savedInstanceState.getBoolean(KEY_OF_INSTANCE + i + "3");

                    // We need to make sure names such as "XXX(checked)" will not duplicate
                    // Use a tricky way to solve this problem, not the best though
                    if (isChecked) {
                        itemName = itemName.substring(0, itemName.lastIndexOf("("));
                    }
                    ItemCard itemCard = new ItemCard(itemName, itemUrl, isChecked);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to opne this Activity
//        else {
//            ItemCard item1 = new ItemCard("Gmail", "Example description", false);
//            ItemCard item2 = new ItemCard("Google", "Example description", false);
//            ItemCard item3 = new ItemCard("Youtube", "Example description", false);
//            itemList.add(item1);
//            itemList.add(item2);
//            itemList.add(item3);
//        }

    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        rviewAdapter = new RviewAdapter(itemList);


        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                rviewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onCheckBoxClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onCheckBoxClick(position);

                rviewAdapter.notifyItemChanged(position);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);

//        TextView url = findViewById(R.id.item_url);
//
//        url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String urlToString = url.getText().toString();
//                gotoUrl(urlToString);
//            }
//        });
    }

//    private void gotoUrl(String s){
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW, uri));
//
//    }

    private void createNewPopUpWindow(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        nameOfItem = (EditText) contactPopupView.findViewById(R.id.nameOfItem);
        urlLink = (EditText) contactPopupView.findViewById(R.id.urlLink);

//        String itemName = nameOfItem.getText().toString();
//        String itemUrl = urlLink.getText().toString();

        add = (Button) contactPopupView.findViewById(R.id.add);
        cancel = (Button) contactPopupView.findViewById(R.id.cancel);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = nameOfItem.getText().toString();
                String itemUrl = urlLink.getText().toString();

                int position = 0;
                Log.d("tag", "itemName" + itemName );
                Log.d("tag", "itemUrl" + itemUrl );
                itemList.add(position, new ItemCard(itemName, itemUrl, false));
//                Toast.makeText(ItemActivity.this, "Add an item", Toast.LENGTH_SHORT).show();

                rviewAdapter.notifyDataSetChanged();

                position++;

                dialog.dismiss();

                showSnackbar();

                //gotoUrl(itemUrl);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

//    private void gotoUrl(String s){
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW, uri));
//
//    }

    public void showSnackbar(){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.ConstraintLayout), "Item Added!", Snackbar.LENGTH_INDEFINITE)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(findViewById(R.id.ConstraintLayout), "Undo Successful", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();

    }



//    private void addItem(int position) {
//        itemList.add(position, new ItemCard("No Logo item", "Item id: " + Math.abs(new Random().nextInt(100000)), false));
//        Toast.makeText(ItemActivity.this, "Add an item", Toast.LENGTH_SHORT).show();
//
//        rviewAdapter.notifyItemInserted(position);
//    }
}
























