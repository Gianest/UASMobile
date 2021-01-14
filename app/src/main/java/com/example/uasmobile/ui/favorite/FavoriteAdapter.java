package com.example.uasmobile.ui.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.uasmobile.R;
import com.example.uasmobile.ui.Team.Team;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.TeamViewHolder> {
    private List<Favorite> teams ;
    private Context context;
    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    FavoriteAdapter(List<Favorite> teams) {
        this.teams = teams;
    }
    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list, parent, false);
            return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamViewHolder holder, final int position) {
        holder.Tim.setText(teams.get(position).getTim());
        holder.desc.setText(teams.get(position).getDesc());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(holder.itemView.getContext())
                .load(teams.get(position).getImagev())
                .apply(requestOptions)
                .into(holder.imagev);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(teams.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder{
        TextView Tim;
        TextView desc;
        ImageView imagev;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            imagev  = itemView.findViewById(R.id.img_item_photo);
            Tim = itemView.findViewById(R.id.tv_item_name);
            desc = itemView.findViewById(R.id.tv_item_detail);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Object data);
    }
}
