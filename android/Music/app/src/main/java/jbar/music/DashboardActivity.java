package jbar.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GridLayout optionsContainer = findViewById(R.id.optionsContainer);
        String[] options = {"Notas", "Escalas", "Acordes", "Extra"};
        Class<?>[] activities = {MainActivity.class, DynamicFormActivity.class, DynamicFormActivity.class, DynamicFormActivity.class};

        for (int i = 0; i < options.length; i++) {
            String optionName = options[i];
            final int index = i; // Copia final de la variable

            Button optionButton = new Button(this);
            optionButton.setText(optionName);
            optionButton.setAllCaps(false);
            optionButton.setTextColor(getResources().getColor(R.color.spotify_white));
            optionButton.setTextSize(16);
            optionButton.setPadding(16, 16, 16, 16);
            optionButton.setBackgroundResource(R.drawable.rounded_button);

            optionButton.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, activities[index]);
                intent.putExtra("selectedOption", optionName);
                startActivity(intent);
            });

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.setMargins(8, 8, 8, 8);
            optionButton.setLayoutParams(params);

            optionsContainer.addView(optionButton);
        }
    }


    // Generar un OnClickListener para cada botón
    private View.OnClickListener getButtonClickListener(final Class<?> activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, activity);
                startActivity(intent);
            }
        };
    }
}
