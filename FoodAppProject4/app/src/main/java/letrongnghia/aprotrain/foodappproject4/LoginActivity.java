package letrongnghia.aprotrain.foodappproject4;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText Phone;
    private EditText PassWord;
    private Button SignIn;
    private TextView Register;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://app-food-52860-default-rtdb.firebaseio.com/");
    private boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Phone = findViewById(R.id.editPhone);
        PassWord = findViewById(R.id.editPassWord);
        SignIn = findViewById(R.id.loginBtn);
        Register = findViewById(R.id.textView_register);


        SignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String phoneTxt = Phone.getText().toString();
                final String passTxt = PassWord.getText().toString();

                if (phoneTxt.isEmpty() || passTxt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập Email và Pass", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)) {
                                final String getPassWord = snapshot.child(phoneTxt).child("password").getValue(String.class);
                                if (getPassWord.equals(passTxt)) {
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Sai Pass", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Sai Email", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

      PassWord.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent event) {
              final int Right = 2;
              if (event.getAction() == MotionEvent.ACTION_UP) {
                  if (event.getRawX() >= PassWord.getRight() - PassWord.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = PassWord.getSelectionEnd();
                        if(passwordVisible){
                            PassWord.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_visibility_off_24, 0);
                            PassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else {
                            PassWord.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_visibility_24, 0);
                            PassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        PassWord.setSelection(selection);
                        return true;
                  }

              }

              return false;
          }
      });

        }
        private void Register () {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }



}
