package jbar.music;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Obtener los datos enviados desde DynamicFormActivity
        String formResult = getIntent().getStringExtra("formResult");

        // Mostrar los datos en un TextView
        TextView tvResult = findViewById(R.id.tvResult);
        tvResult.setText(formResult != null ? formResult : "No se recibió información");
    }
}
