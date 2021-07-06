package we.chrisoli.lifestyletracker.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.adapter.HomeAdapter;
import we.chrisoli.lifestyletracker.db.DatabaseAccess;
import we.chrisoli.lifestyletracker.model.Type;
import we.chrisoli.lifestyletracker.model.User;

public class HomeFragment extends Fragment {

    DatabaseAccess db;

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<Type> typeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseAccess(getContext());

        recyclerView = view.findViewById(R.id.recycler_view);

        typeList = new ArrayList<>();
        typeList.add(db.getWater());
        typeList.add(db.getPee());
        typeList.add(db.getShit());
        System.out.println("TEST: "+db.getWater().getAmount());
        adapter = new HomeAdapter(getContext(), typeList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}