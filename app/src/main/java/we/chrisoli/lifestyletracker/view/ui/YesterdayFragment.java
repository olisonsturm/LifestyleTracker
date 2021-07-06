package we.chrisoli.lifestyletracker.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.db.DatabaseAccess;

public class YesterdayFragment extends Fragment {

    private DatabaseAccess db;

    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yesterday, container, false);

        db = new DatabaseAccess(getContext());
        listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, db.getYesterday());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value=adapter.getItem(position);
                Toast.makeText(getContext(),value,Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}