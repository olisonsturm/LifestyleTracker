package we.chrisoli.lifestyletracker.view.ui.alltime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import we.chrisoli.lifestyletracker.databinding.FragmentAllTimeBinding;

public class AllTimeFragment extends Fragment {

    private AllTimeViewModel allTimeViewModel;
    private FragmentAllTimeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allTimeViewModel =
                new ViewModelProvider(this).get(AllTimeViewModel.class);

        binding = FragmentAllTimeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAllTime;
        allTimeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}