package letrongnghia.aprotrain.foodapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import letrongnghia.aprotrain.foodapp.Database.Food;
import letrongnghia.aprotrain.foodapp.R;

public class FoodAdapter extends BaseAdapter {
    Activity activity;
    List<Food> datalist;
    
    public FoodAdapter(Activity activity, List<Food> datalist){
        this.activity = activity;
        this.datalist = datalist;

    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView title = view.findViewById(R.id.food_title);
        TextView content = view.findViewById(R.id.food_content);
        TextView price = view.findViewById(R.id.food_price);


        Food food = datalist.get(position);
        imageView.setImageResource(food.getId());
        title.setText(food.getTitle());
        content.setText(food.getContext());
        price.setText(String.valueOf(food.getPrice()));

        return view;
    }
}
