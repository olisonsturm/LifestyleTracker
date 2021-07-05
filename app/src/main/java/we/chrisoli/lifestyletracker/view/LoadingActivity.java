package we.chrisoli.lifestyletracker.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private boolean firstAppStart() {
        // Abfrage ob die App zum Erstenmal geöffnent wird
        boolean first = false;
        SharedPreferences sharedPreferences = getSharedPreferences("firstStart", MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("firstStart", false) == false) {
            first = true;
            sharedPreferencesEditor.putBoolean("firstStart", true);
            sharedPreferencesEditor.commit();
        }
        return first;
    }

    private void login() {
        SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        int uid = sharedPreferences.getInt("uid", -1);
        if (uid == -1) {
            User createUser = new User();
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
                                createUser.setUid(0);
                                createUser.setFirstname(cFirstname);
                                createUser.setLastname(cLastname);
                            }
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.cancle), null)
                    .create();
            alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_rounded));
            alertDialog.show();
            db.setUser(createUser);
            sharedPreferencesEditor.putInt("uid", 0);
        }
    }
}