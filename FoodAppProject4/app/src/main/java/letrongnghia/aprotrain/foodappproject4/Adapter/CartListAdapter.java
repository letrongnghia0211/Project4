package letrongnghia.aprotrain.foodappproject4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import letrongnghia.aprotrain.foodappproject4.CartListActivity;
import letrongnghia.aprotrain.foodappproject4.Helper.ManagementCart;
import letrongnghia.aprotrain.foodappproject4.Interface.ChangeNumberItemsListener;
import letrongnghia.aprotrain.foodappproject4.R;
import letrongnghia.aprotrain.foodappproject4.database.FoodData;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodData> foodData;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodData> foodData, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodData = foodData;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(foodData.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodData.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodData.get(position).getNumberInCart() * foodData.get(position).getFee()) * 100) / 100));
        holder.num.setText(foodData.get(position).getNumberInCart());

        int drawableReourceId = holder.itemView.getContext().getResources().getIdentifier(foodData.get(position).getPic()
                , "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.pic);
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(foodData, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minisNumberFood(foodData, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.titleTxt);
            feeEachItem= itemView.findViewById(R.id.feeEachItem);
            pic= itemView.findViewById(R.id.picCart);
            totalEachItem= itemView.findViewById(R.id.totalFeeTxt);
            num= itemView.findViewById(R.id.numberItemTxt);
            plusItem= itemView.findViewById(R.id.plusCartBtn);
            minusItem= itemView.findViewById(R.id.minusCartBtn);
        }
    }


}
