package we.chrisoli.lifestyletracker.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.databinding.ActivityMainBinding;
import we.chrisoli.lifestyletracker.db.DataAccess;
import we.chrisoli.lifestyletracker.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private Button login;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // bind views
        first = findViewById(R.id.firstname);
        last = findViewById(R.id.lastname);
        login = findViewById(R.id.login);
        loading = findViewById(R.id.loading);

        // enable button login
        login.setEnabled(true);

        login.setOnClickListener(v -> {
            // anzeigen der progressbar
            loading.setVisibility(View.VISIBLE);
            // user erstellen
            User loginUser = new User();
            loginUser.setFirstname(first.getText().toString());
            loginUser.setLastname(last.getText().toString());
            // hochladen des users
            DataAccess data = new DataAccess();
            data.setUser(loginUser);
            // kleine zeitverzögerung
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //nichts
            }
            // prgressbar beenden
            loading.setVisibility(View.GONE);
            // start app
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        // TODO: wenn eingeloggt dann überspringen des logins
    }
}