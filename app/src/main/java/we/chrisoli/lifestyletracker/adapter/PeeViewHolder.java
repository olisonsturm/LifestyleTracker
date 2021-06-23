package we.chrisoli.lifestyletracker.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.model.Pee;
import we.chrisoli.lifestyletracker.model.Type;

class PeeViewHolder extends RecyclerView.ViewHolder {

    private List<Type> typeList;

    CardView card;
    TextView title;
    ImageView minus;
    TextView amount;
    ImageView plus;

    public PeeViewHolder(View view, List<Type> typeList) {
        super(view);
        card = view.findViewById(R.id.card);
        title = view.findViewById(R.id.title);
        minus = view.findViewById(R.id.minus);
        amount = view.findViewById(R.id.amount);
        plus = view.findViewById(R.id.plus);

        this.typeList = typeList;
    }

    void bindData(int position) {

        Pee water = (Pee) typeList.get(position);

        title.setText("Pee");
        amount.setText("1");

        minus.setOnClickListener(v -> {

        });

        plus.setOnClickListener(v -> {

        });
    }

}
