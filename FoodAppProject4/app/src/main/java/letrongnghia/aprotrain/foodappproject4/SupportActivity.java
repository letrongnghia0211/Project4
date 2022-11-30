package letrongnghia.aprotrain.foodappproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
    }

    public void MainActivity(View view) {
        startActivity(new Intent(SupportActivity.this, MainActivity.class));
    }
}