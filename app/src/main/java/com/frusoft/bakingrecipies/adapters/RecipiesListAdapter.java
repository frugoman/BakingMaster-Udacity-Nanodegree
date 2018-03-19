package com.frusoft.bakingrecipies.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frusoft.bakingrecipies.R;
import com.frusoft.bakingrecipies.databinding.RecipyListItemBinding;
import com.frusoft.bakingrecipies.models.Recipie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nfrugoni on 3/3/18.
 */

public class RecipiesListAdapter extends RecyclerView.Adapter<RecipiesListAdapter.RecipiesListItemViewHolder> {

    private List<Recipie> recipiesList;
    private Context context;
    private RecipyItemClickListener clickListener;

    public interface RecipyItemClickListener {
        void onClick(Recipie recipie);
    }

    public void setData(List<Recipie> recipies) {
        if (recipies == null || recipies.isEmpty())
            recipiesList = null;
        recipiesList = recipies;
        notifyDataSetChanged();
    }

    public RecipiesListAdapter(Context context, RecipyItemClickListener clickListener) {
        super();
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public RecipiesListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipy_list_item, parent, false);
        return new RecipiesListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipiesListItemViewHolder holder, int position) {
        holder.listItemView = DataBindingUtil.bind(holder.itemView);
        Recipie recipie = recipiesList.get(position);
        holder.listItemView.recipyListItemTv.setText(recipie.getName());
    }

    @Override
    public int getItemCount() {
        if (recipiesList == null || recipiesList.isEmpty())
            return 0;
        return recipiesList.size();
    }

    class RecipiesListItemViewHolder extends RecyclerView.ViewHolder {

        RecipyListItemBinding listItemView;

        public RecipiesListItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    clickListener.onClick(recipiesList.get(adapterPosition));
                }
            });
        }
    }
}
