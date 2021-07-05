package we.chrisoli.lifestyletracker.view.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.adapter.HomeAdapter;
import we.chrisoli.lifestyletracker.databinding.FragmentHomeBinding;
import we.chrisoli.lifestyletracker.db.DataAccess;
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

        user = User.getInstance();

        recyclerView = view.findViewById(R.id.recycler_view);

        typeList = new ArrayList<>();
        DataAccess data = new DataAccess();
        //typeList.add(data.getWater(user));
        //typeList.add(data.getPee(user));
        //typeList.add(data.getShit(user));
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