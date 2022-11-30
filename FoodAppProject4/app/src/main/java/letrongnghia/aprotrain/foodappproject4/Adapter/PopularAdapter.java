package letrongnghia.aprotrain.foodappproject4.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import letrongnghia.aprotrain.foodappproject4.R;
import letrongnghia.aprotrain.foodappproject4.ShowDetailActivity;
import letrongnghia.aprotrain.foodappproject4.database.FoodData;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHoler> {
    ArrayList<FoodData> popularFood;



    public PopularAdapter(ArrayList<FoodData> popularFood) {

        this.popularFood = popularFood;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHoler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHoler holder, int position) {
        holder.title.setText(popularFood.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularFood.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
            TextView title, fee;
            ImageView pic;
            ConstraintLayout addBtn;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
