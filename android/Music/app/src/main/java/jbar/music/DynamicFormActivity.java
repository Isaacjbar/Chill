package jbar.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DynamicFormActivity extends AppCompatActivity {

    private String selectedOption; // Opción seleccionada del Dashboard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_form);

        // Obtener la opción seleccionada del intent
        selectedOption = getIntent().getStringExtra("selectedOption");

        // Referencias a los componentes del layout
        TextView tvFormTitle = findViewById(R.id.tvFormTitle);
        LinearLayout dynamicContainer = findViewById(R.id.dynamicContainer);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Ajustar el título según la opción seleccionada
        tvFormTitle.setText("Configura: " + selectedOption);

        // Crear campos dinámicos según la opción seleccionada
        if ("Escalas".equals(selectedOption)) {
            createScaleForm(dynamicContainer);
        } else if ("Acordes".equals(selectedOption)) {
            createChordsForm(dynamicContainer);
        } else if ("Extra".equals(selectedOption)) {
            createExtraForm(dynamicContainer);
        }

        // Botón para enviar
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFormSubmission();
            }
        });
    }

    private void createScaleForm(LinearLayout container) {
        RadioGroup group = new RadioGroup(this);
        group.setOrientation(LinearLayout.VERTICAL);
        group.setTag("radioGroup"); // Agrega un tag para buscarlo fácilmente

        String[] options = {"Escala Mayor", "Escala Menor Natural", "Escala Pentatónica"};
        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextColor(getResources().getColor(R.color.spotify_white));
            group.addView(radioButton);
        }
        container.addView(group);
    }

    // Formulario para "Acordes"
    private void createChordsForm(LinearLayout container) {
        RadioGroup group = new RadioGroup(this);
        group.setOrientation(LinearLayout.VERTICAL);

        String[] options = {"Acordes Mayores", "Acordes Menores", "Acordes Séptimos"};
        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextColor(getResources().getColor(R.color.spotify_white));
            group.addView(radioButton);
        }
        container.addView(group);
    }

    // Formulario para "Extra"
    private void createExtraForm(LinearLayout container) {
        RadioGroup group = new RadioGroup(this);
        group.setOrientation(LinearLayout.VERTICAL);

        String[] options = {"Modo Lírico", "Modo Dórico", "Modo Frigio"};
        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextColor(getResources().getColor(R.color.spotify_white));
            group.addView(radioButton);
        }
        container.addView(group);
    }

    private void handleFormSubmission() {
        Intent intent = null;

        // Encuentra el radio seleccionado
        RadioGroup group = findViewById(R.id.dynamicContainer).findViewWithTag("radioGroup");
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == -1) {
            // Si no hay opción seleccionada, muestra un mensaje y regresa
            Toast.makeText(this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadio = findViewById(selectedId);
        String selectedOptionText = selectedRadio.getText().toString();

        // Redirige según la opción seleccionada
        switch (selectedOption) {
            case "Escalas":
                intent = new Intent(this, ScalesActivity.class);
                // Mapeo para garantizar que coincida con ScaleRepository
                String mappedScale = mapScaleType(selectedOptionText);
                intent.putExtra("formResult", mappedScale); // Enviar la escala exacta
                break;
            case "Acordes":
                intent = new Intent(this, ChordsActivity.class);
                intent.putExtra("formResult", selectedOptionText);
                break;
            case "Extra":
                intent = new Intent(this, ExtraActivity.class);
                intent.putExtra("formResult", selectedOptionText);
                break;
            default:
                Toast.makeText(this, "Configuración desconocida", Toast.LENGTH_SHORT).show();
                return;
        }

        startActivity(intent);
    }

    // Mapeo para garantizar que coincida con las claves de ScaleRepository
    private String mapScaleType(String userSelection) {
        switch (userSelection) {
            case "Escala Mayor":
                return "Mayor";
            case "Escala Menor Natural":
                return "Menor Natural";
            case "Escala Pentatónica":
                return "Pentatónica Mayor";
            default:
                return null; // Si no coincide, devuelve null
        }
    }


}
