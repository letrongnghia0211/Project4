package letrongnghia.aprotrain.foodappproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserActivity extends AppCompatActivity {
    private TextView textViewWelcome, textViewFullName, textViewEmail, textViewDob, textViewGender, textViewPhone;
    private ProgressBar progressBar;
    private String fullname, email, dob, gender, phone;
    private ImageView imageView;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        textViewWelcome = findViewById(R.id.textview_welcome);
//        textViewFullName = findViewById(R.id.textView_show_full_name);
//        textViewEmail = findViewById(R.id.textView_show_email);
//        textViewDob = findViewById(R.id.textView_show_dob);
//        textViewGender = findViewById(R.id.textView_show_gender);
//        textViewPhone = findViewById(R.id.textView_show_phone);
//
//        authProfile = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = authProfile.getCurrentUser();




    }


    public void MainActivity(View view) {
        startActivity(new Intent(UserActivity.this, MainActivity.class));
    }
}