package jbar.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ScalesActivity extends AppCompatActivity {

    private String scaleType; // Tipo de escala seleccionada
    private String currentScaleTone; // Tono actual de la escala (C, D, etc.)
    private List<String> currentScaleNotes; // Notas de la escala actual
    private int currentNoteIndex = 0; // Índice de la nota actual
    private int playCount = 0; // Contador de reproducciones
    private final int maxPlayCount = 3; // Máximo número de reproducciones permitidas
    private MediaPlayer mediaPlayer; // Reproductor de audio
    private LinearLayout llSpacesContainer; // Contenedor para los espacios

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scales);

        // Obtener el tipo de escala desde el Intent
        scaleType = getIntent().getStringExtra("formResult");

        if (scaleType == null || !ScaleRepository.SCALES.containsKey(scaleType)) {
            Toast.makeText(this, "Escala no válida seleccionada", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad si el tipo de escala es inválido
            return;
        }

        // Configurar título
        TextView tvScaleTitle = findViewById(R.id.tvScaleTitle);

        // Contenedor de espacios
        llSpacesContainer = findViewById(R.id.llSpacesContainer);

        // Generar escala aleatoria
        generateRandomScale();
        tvScaleTitle.setText("Escala: " + currentScaleTone + " " + scaleType);

        // Configurar botones
        Button btnPlayNote = findViewById(R.id.btnPlayNote);
        btnPlayNote.setOnClickListener(v -> playCurrentNote());

        Button btnNextNote = findViewById(R.id.btnNextNote);
        btnNextNote.setOnClickListener(v -> moveToNextNote());

        Button btnNextScale = findViewById(R.id.btnNextScale);
        btnNextScale.setOnClickListener(v -> {
            Toast.makeText(this, "Terminaste la escala " + currentScaleTone + " " + scaleType, Toast.LENGTH_SHORT).show();
            generateRandomScale();
            tvScaleTitle.setText("Escala: " + currentScaleTone + " " + scaleType);
        });
    }

    private void generateRandomScale() {
        Map<String, List<String>> scalesByTone = ScaleRepository.SCALES.get(scaleType);
        Object[] tones = scalesByTone.keySet().toArray();
        Random random = new Random();
        currentScaleTone = (String) tones[random.nextInt(tones.length)];
        currentScaleNotes = scalesByTone.get(currentScaleTone);

        currentNoteIndex = 0; // Reiniciar el índice
        playCount = 0; // Reiniciar el contador de reproducciones

        // Generar los espacios y opciones dinámicamente
        generateSpacesForNotes();
        generateOptionsForNotes();
    }


    // Generar los espacios para las notas de la escala
    private void generateSpacesForNotes() {
        llSpacesContainer.removeAllViews(); // Limpiar espacios previos

        for (int i = 0; i < currentScaleNotes.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText("_"); // Mostrar guion bajo para representar un espacio vacío
            textView.setTextSize(18);
            textView.setPadding(16, 16, 16, 16);
            textView.setTextColor(getResources().getColor(R.color.spotify_white));
            llSpacesContainer.addView(textView);
        }
    }

    // Reproducir la nota actual
    private void playCurrentNote() {
        if (playCount < maxPlayCount) {
            String currentNote = currentScaleNotes.get(currentNoteIndex);

            // Verifica si la nota es sostenida (contiene "#") y ajusta el nombre del archivo
            String resourceName = currentNote.contains("#")
                    ? "note_" + currentNote.toLowerCase().replace("#", "_s")
                    : "note_" + currentNote.toLowerCase();

            int resId = getResources().getIdentifier(resourceName, "raw", getPackageName());

            if (resId == 0) {
                Toast.makeText(this, "Archivo de audio no encontrado para: " + currentNote, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();
            playCount++;
        } else {
            Toast.makeText(this, "No puedes reproducir esta nota más de 3 veces.", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToNextNote() {
        if (currentNoteIndex < currentScaleNotes.size() - 1) {
            // Actualizar el espacio correspondiente
            TextView textView = (TextView) llSpacesContainer.getChildAt(currentNoteIndex);
            textView.setText(currentScaleNotes.get(currentNoteIndex));

            currentNoteIndex++;
            playCount = 0; // Reiniciar contador de reproducciones

            // Regenerar las opciones para la nueva nota
            generateOptionsForNotes();
        } else {
            // Última nota: Completar el último espacio
            TextView textView = (TextView) llSpacesContainer.getChildAt(currentNoteIndex);
            textView.setText(currentScaleNotes.get(currentNoteIndex));

            Toast.makeText(this, "¡Completaste la escala " + currentScaleTone + " " + scaleType + "!", Toast.LENGTH_SHORT).show();

            // Mostrar botón para avanzar a otra escala
            Button btnNextScale = findViewById(R.id.btnNextScale);
            btnNextScale.setVisibility(View.VISIBLE);
        }
    }


    // Generar las opciones para las notas
    private void generateOptionsForNotes() {
        GridLayout glOptionsContainer = findViewById(R.id.glOptionsContainer);
        glOptionsContainer.removeAllViews(); // Limpiar opciones previas

        // Seleccionar 3 opciones incorrectas
        Random random = new Random();
        List<String> allNotes = List.of("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
        List<String> options = new ArrayList<>(allNotes);
        options.removeAll(currentScaleNotes); // Elimina las notas de la escala actual
        Collections.shuffle(options); // Mezcla las notas restantes

        // Mantener la correcta y 3 incorrectas
        List<String> finalOptions = new ArrayList<>();
        finalOptions.add(currentScaleNotes.get(currentNoteIndex)); // Nota correcta
        finalOptions.addAll(options.subList(0, 3)); // Agrega 3 incorrectas
        Collections.shuffle(finalOptions); // Mezclar las opciones finales

        // Crear botones para las opciones
        for (String option : finalOptions) {
            Button optionButton = new Button(this);
            optionButton.setText(option);
            optionButton.setTextColor(getResources().getColor(R.color.spotify_white));
            optionButton.setTextSize(16);
            optionButton.setPadding(16, 16, 16, 16);
            optionButton.setBackgroundResource(R.drawable.rounded_button);
            optionButton.setOnClickListener(v -> checkAnswer(option));

            // Configurar layout params
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0; // Ocupa espacio proporcional
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.setMargins(8, 8, 8, 8);
            optionButton.setLayoutParams(params);

            glOptionsContainer.addView(optionButton);
        }
    }

    // Validar la respuesta seleccionada
    private void checkAnswer(String selectedNote) {
        if (selectedNote.equals(currentScaleNotes.get(currentNoteIndex))) {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
            moveToNextNote();
        } else {
            Toast.makeText(this, "Incorrecto, intenta de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
