package we.chrisoli.lifestyletracker.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import we.chrisoli.lifestyletracker.R;
import we.chrisoli.lifestyletracker.db.DatabaseAccess;
import we.chrisoli.lifestyletracker.model.User;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar loading;

    private DatabaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // deklaration (bind)
        loading = findViewById(R.id.loading);
        db = new DatabaseAccess(getBaseContext(), MODE_PRIVATE);

        // Datenbank erstelen, wenn App erstes mal geöffnet wird
        if (firstAppStart()) {
            db.createDatabases();
        }

        // login, wenn Account erstellt
        SharedPreferences prefStayLoggedIn = getSharedPreferences("loggedin", MODE_PRIVATE);
        if (prefStayLoggedIn.getBoolean("loggedin", true)) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            login();
        }
    }

    private boolean firstAppStart() {
        // Abfrage ob die App zum Erstenmal geöffnent wird
        boolean first = false;
        SharedPreferences sharedPreferences = getSharedPreferences("firstStart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("firstStart", false) == false) {
            first = true;
            editor.putBoolean("firstStart", true);
            editor.commit();
        }
        return first;
    }

    public void login() {
        LinearLayout view = new LinearLayout(this);
        EditText firstname = new EditText(this);
        EditText lastname = new EditText(this);
        firstname.setInputType(InputType.TYPE_CLASS_TEXT);
        lastname.setInputType(InputType.TYPE_CLASS_TEXT);
        view.setOrientation(LinearLayout.VERTICAL);
        firstname.setHint(getResources().getString(R.string.firstname));
        lastname.setHint(getResources().getString(R.string.lastname));
        view.addView(firstname);
        view.addView(lastname);
        float dpi = getResources().getDisplayMetrics().density;
        @SuppressLint("RestrictedApi") AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.first_and_lastname))
                .setView(view, (int) (20 * dpi), (int) (10 * dpi), (int) (20 * dpi), (int) (0 * dpi))
                .setPositiveButton(getResources().getString(R.string.create_account), (dialog, which) -> {
                    if ((firstname.getText() != null) && (lastname.getText() != null)) {
                        if (firstname.getText().toString().length() > 0 && lastname.getText().toString().length() > 0) {
                            String cFirstname = firstname.getText().toString().toLowerCase().replace(" ", "");
                            cFirstname = cFirstname.substring(0, 1).toUpperCase() + cFirstname.substring(1);
                            String cLastname = lastname.getText().toString().toLowerCase().replace(" ", "");
                            cLastname = cLastname.substring(0, 1).toUpperCase() + cLastname.substring(1);
                            db.setUser(cFirstname, cLastname);
                            SharedPreferences prefStayLoggedIn = getSharedPreferences("loggedin", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefStayLoggedIn.edit();
                            editor.putBoolean("loggedin", true);
                            editor.commit();
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_rounded));
        alertDialog.show();
    }
}