package we.chrisoli.lifestyletracker.view.ui.yesterday;

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

import we.chrisoli.lifestyletracker.databinding.FragmentYesterdayBinding;

public class YesterdayFragment extends Fragment {

    private YesterdayViewModel yesterdayViewModel;
    private FragmentYesterdayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yesterdayViewModel =
                new ViewModelProvider(this).get(YesterdayViewModel.class);

        binding = FragmentYesterdayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textYesterday;
        yesterdayViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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