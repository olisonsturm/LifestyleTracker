package we.chrisoli.lifestyletracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.model.Type;

public class HomeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Type> typeList;

    public HomeAdapter(Context context, List<Type> typeList) {
        this.context = context;
        this.typeList = typeList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            default: //Type.TYPE_WATER:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_list_item, parent, false);
                return new WaterViewHolder(itemView, typeList);
            case Type.TYPE_PEE:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_list_item, parent, false);
                return new PeeViewHolder(itemView, typeList);
            case Type.TYPE_SHIT:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_list_item, parent, false);
                return new ShitViewHolder(itemView, typeList);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Type.TYPE_WATER:
                ((WaterViewHolder) holder).bindData(position);
                break;
            case Type.TYPE_PEE:
                ((PeeViewHolder) holder).bindData(position);
                break;
            case Type.TYPE_SHIT:
                ((ShitViewHolder) holder).bindData(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (typeList == null) {
            return 0;
        } else {
            return typeList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return typeList.get(position).getType();
    }

}
