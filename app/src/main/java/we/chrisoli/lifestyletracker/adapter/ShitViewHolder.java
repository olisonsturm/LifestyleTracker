package we.chrisoli.lifestyletracker.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.db.DatabaseAccess;
import we.chrisoli.lifestyletracker.model.Shit;
import we.chrisoli.lifestyletracker.model.Type;

class ShitViewHolder extends RecyclerView.ViewHolder {

    private List<Type> typeList;
    private DatabaseAccess db;

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
        db = new DatabaseAccess(view.getContext());
    }

    void bindData(int position) {

        Shit shit = (Shit) typeList.get(position);

        title.setText("Shit");
        if (shit.getAmount() < 1) {
            amount.setText("0");
        } else {
            amount.setText(String.valueOf(shit.getAmount()));
        }

        minus.setOnClickListener(v -> {
            shit.setAmount(shit.getAmount() - 1);
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd.MM.yyyy");
            shit.setDate(formatter.format(new Date()));
            db.setShit(shit);
            amount.setText(String.valueOf(shit.getAmount()));
        });

        plus.setOnClickListener(v -> {
            shit.setAmount(shit.getAmount() + 1);
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd.MM.yyyy");
            shit.setDate(formatter.format(new Date()));
            db.setShit(shit);
            amount.setText(String.valueOf(shit.getAmount()));
        });
    }

}
