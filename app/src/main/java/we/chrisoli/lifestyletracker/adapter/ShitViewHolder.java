package we.chrisoli.lifestyletracker.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.model.Shit;
import we.chrisoli.lifestyletracker.model.Type;

class ShitViewHolder extends RecyclerView.ViewHolder {

    private List<Type> typeList;

    CardView card;
    TextView title;
    ImageView minus;
    TextView amount;
    ImageView plus;

    public ShitViewHolder(View view, List<Type> typeList) {
        super(view);
        card = view.findViewById(R.id.card);
        title = view.findViewById(R.id.title);
        minus = view.findViewById(R.id.minus);
        amount = view.findViewById(R.id.amount);
        plus = view.findViewById(R.id.plus);

        this.typeList = typeList;
    }

    void bindData(int position) {

        Shit water = (Shit) typeList.get(position);

        title.setText("Shit");
        amount.setText("1");

        minus.setOnClickListener(v -> {

        });

        plus.setOnClickListener(v -> {

        });
    }

}
