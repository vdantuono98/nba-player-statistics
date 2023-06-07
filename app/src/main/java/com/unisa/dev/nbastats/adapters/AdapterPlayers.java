package com.unisa.dev.nbastats.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.unisa.dev.nbastats.R;
import com.unisa.dev.nbastats.models.PlayerModel;
import com.unisa.dev.nbastats.utilities.StringConverter;

import java.util.List;

public class AdapterPlayers extends RecyclerView.Adapter<AdapterPlayers.ViewHolder>{

    private List<PlayerModel> list;
    private Context context;
    private OnPlayerClicked onPlayerClickedListener;

    public AdapterPlayers(List<PlayerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_player_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int actualPos = position;

        holder.teamName.setText(StringConverter.getInstance().getTeamName(list.get(actualPos).getTeamAbbrevation()));
        holder.playerName.setText(list.get(actualPos).getName());
        holder.season.setText(list.get(actualPos).getSeason());

        holder.boxContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlayerClickedListener.onPlayerClicked(list.get(actualPos));
            }



            
        });

        String URL = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/"+list.get(actualPos).getNBAID()+".png";
        Glide.with(context)
                .load(URL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.placeholder)
                .into(holder.playerPhoto);


        String URL12 = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/"+list.get(actualPos).getNBAID()+".png";
        Glide.with(context)
                .load(URL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.placeholder)
                .into(holder.playerPhoto);





    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView playerPhoto;
        private TextView playerName, teamName, season;
        private RelativeLayout boxContainer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerPhoto = itemView.findViewById(R.id.playerPhoto);
            playerName = itemView.findViewById(R.id.namePlayer);
            teamName = itemView.findViewById(R.id.squadName);
            season = itemView.findViewById(R.id.seasonYear);
            boxContainer = itemView.findViewById(R.id.boxContainer);
        }
    }

    public interface OnPlayerClicked{
        void onPlayerClicked(PlayerModel playerModel);
    }

    public void setOnPlayerClickedListener(OnPlayerClicked onPlayerClickedListener){
        this.onPlayerClickedListener = onPlayerClickedListener;

    }
}
