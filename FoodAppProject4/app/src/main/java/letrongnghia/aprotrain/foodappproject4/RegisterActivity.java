package letrongnghia.aprotrain.foodappproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import letrongnghia.aprotrain.foodappproject4.database.UserAdmin;

public class RegisterActivity extends AppCompatActivity {

    private EditText FullName, Email, PassWord, ConfirmPass,Phone;
    private TextView SignIn;
    private Button ButtonRegister;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://app-food-52860-default-rtdb.firebaseio.com/");
    private boolean passwordVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = findViewById(R.id.editFullName);
        Email = findViewById(R.id.editEmail);
        PassWord = findViewById(R.id.editPassWord);
        ConfirmPass = findViewById(R.id.editComPassWord);
        Phone = findViewById(R.id.editPhone);
         SignIn = findViewById(R.id.textView_sign_in);
         ButtonRegister = findViewById(R.id.button_register);


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnameTxt = FullName.getText().toString();
                final String emailTxt = Email.getText().toString();
                final String passTxt = PassWord.getText().toString();
                final String confirmpassTxt = ConfirmPass.getText().toString();
                final String phoneTxt = Phone.getText().toString();

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || passTxt.isEmpty() || phoneTxt.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầu đủ thông tin !", Toast.LENGTH_SHORT).show();
                }else if(!passTxt.equals(confirmpassTxt)){
                    Toast.makeText(RegisterActivity.this, "Pass chưa trùng khớp. Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();

                }else {

                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(RegisterActivity.this, "Email đã được đăng kí", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("User").child(phoneTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("User").child(phoneTxt).child("email").setValue(emailTxt);
                                databaseReference.child("User").child(phoneTxt).child("password").setValue(passTxt);
                                databaseReference.child("User").child(phoneTxt).child("phone").setValue(phoneTxt);
                                Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

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
        ConfirmPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= ConfirmPass.getRight() - ConfirmPass.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = ConfirmPass.getSelectionEnd();
                        if(passwordVisible){
                            ConfirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_visibility_off_24, 0);
                            ConfirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else {
                            ConfirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_visibility_24, 0);
                            ConfirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        ConfirmPass.setSelection(selection);
                        return true;
                    }

                }

                return false;
            }
        });

    }
}