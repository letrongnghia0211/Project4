package letrongnghia.aprotrain.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import letrongnghia.aprotrain.foodapp.Database.Food;
import letrongnghia.aprotrain.foodapp.adapter.FoodAdapter;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Food> datalist = new ArrayList<>();
    FoodAdapter foodAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        datalist.add(new Food(R.drawable.food, "Pizza", "Ok", 100000));
        datalist.add(new Food(R.drawable.food1, "Hamberger", "Ok", 70000));
        datalist.add(new Food(R.drawable.food2, "Coffe", "Ok", 25000));

        foodAdapter = new FoodAdapter(this, datalist);
        listView.setAdapter(foodAdapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.menu_edit_item:
                this.currentIndex = index;
                showDialogAdd();
                return true;
            case R.id.menu_delete_item:
                datalist.remove(index);
                foodAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_item:
                showDialogAdd();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showDialogAdd(){
        AlertDialog.Builder builer = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_food, null);

        final EditText editTitle = view.findViewById(R.id.df_title);
        final EditText editContent = view.findViewById(R.id.df_content);
        final EditText editPrice = view.findViewById(R.id.df_price);

        if(currentIndex >= 0){
            editTitle.setText(datalist.get(currentIndex).getTitle());
            editContent.setText(datalist.get(currentIndex).getContext());
            editPrice.setText(String.valueOf(datalist.get(currentIndex).getPrice()));

        }

        builer.setView(view);
        builer.setTitle("Add/Update Item")
                .setPositiveButton("Save Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();
                float price = Float.parseFloat(editPrice.getText().toString());

                if(currentIndex >= 0){
                    datalist.get(currentIndex).setTitle(title);
                    datalist.get(currentIndex).setContext(content);
                    datalist.get(currentIndex).setPrice(price);
                    currentIndex = -1;
                }else{
                    Food food = new Food(R.drawable.food, title, content, price);
                    datalist.add(food);
                }


                foodAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Canel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builer.show();
    }
}