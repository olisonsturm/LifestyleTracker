package we.chrisoli.lifestyletracker.view.ui.home;

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

    private User user;

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<Type> typeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        user = new User();

        recyclerView = view.findViewById(R.id.recycler_view);

        typeList = new ArrayList<>();
        DatabaseAccess db = new DatabaseAccess(getContext(), 0);
        typeList.add(db.getWater(user));
        typeList.add(db.getPee(user));
        typeList.add(db.getShit(user));
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