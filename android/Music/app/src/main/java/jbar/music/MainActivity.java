package jbar.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String[] notes = {"do", "re", "mi", "fa", "sol", "la", "si"}; // Opciones de notas
    private String correctNote; // Nota que se reproducirá y será la correcta
    private int playCount = 0; // Contador de reproducciones
    private final int maxPlayCount = 3; // Máximo número de reproducciones permitidas
    private Button btnPlayNote; // Botón de reproducir nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el botón para reproducir la nota
        btnPlayNote = findViewById(R.id.btnPlayNote);
        btnPlayNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playCount < maxPlayCount) {
                    playNoteAudio(correctNote); // Reproduce la nota seleccionada
                    playCount++;
                    if (playCount == maxPlayCount) {
                        btnPlayNote.setEnabled(false); // Deshabilita el botón si se alcanzan los intentos
                        Toast.makeText(MainActivity.this, "Ya no puedes reproducir esta nota.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        generateRandomNote(); // Selecciona una nota aleatoria al iniciar la actividad
        generateNoteOptions(); // Genera los botones de opciones
    }

    // Método para seleccionar una nota aleatoria
    private void generateRandomNote() {
        Random random = new Random();
        correctNote = notes[random.nextInt(notes.length)]; // Selecciona una nota aleatoria
        playCount = 0; // Reinicia el contador de reproducciones
        btnPlayNote.setEnabled(true); // Asegura que el botón de reproducción esté habilitado
    }

    // Método para generar 4 opciones de botones, incluyendo la correcta
    private void generateNoteOptions() {
        GridLayout noteOptionsContainer = findViewById(R.id.noteOptionsContainer);
        noteOptionsContainer.removeAllViews(); // Limpia las opciones existentes

        // Generar un conjunto de 3 opciones incorrectas
        Random random = new Random();
        String[] shuffledNotes = notes.clone(); // Clonar las notas para evitar modificar el arreglo original
        for (int i = 0; i < shuffledNotes.length; i++) {
            int swapIndex = random.nextInt(shuffledNotes.length);
            String temp = shuffledNotes[i];
            shuffledNotes[i] = shuffledNotes[swapIndex];
            shuffledNotes[swapIndex] = temp;
        }

        // Agregar la nota correcta y 3 notas incorrectas
        int correctNoteIndex = random.nextInt(4); // Posición aleatoria para la nota correcta
        for (int i = 0; i < 4; i++) {
            String note = (i == correctNoteIndex) ? correctNote : shuffledNotes[i];
            Button noteButton = new Button(this);
            noteButton.setText(note); // Texto tal cual
            noteButton.setAllCaps(false); // Desactiva la transformación a mayúsculas
            noteButton.setTextColor(getResources().getColor(R.color.spotify_white));
            noteButton.setTextSize(16);
            noteButton.setPadding(16, 16, 16, 16);
            noteButton.setBackgroundResource(R.drawable.rounded_button); // Aplica el diseño personalizado
            noteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(note); // Verifica si la opción seleccionada es correcta
                }
            });

            // Configurar parámetros del botón para el GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0; // Ocupa espacio proporcional
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f); // Peso de la columna
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f); // Peso de la fila
            params.setMargins(8, 8, 8, 8); // Margen entre los botones
            noteButton.setLayoutParams(params);

            // Agregar el botón al GridLayout
            noteOptionsContainer.addView(noteButton);
        }
    }

    // Método para reproducir la nota
    private void playNoteAudio(String note) {
        int resId = getResources().getIdentifier("note_" + note, "raw", getPackageName());
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();
    }

    // Método para verificar si la respuesta es correcta
    private void checkAnswer(String selectedNote) {
        if (selectedNote.equals(correctNote)) {
            Toast.makeText(this, "¡Correcto! Has identificado la nota.", Toast.LENGTH_SHORT).show();
            generateRandomNote(); // Cambia a una nueva nota después de una respuesta correcta
            generateNoteOptions(); // Genera nuevas opciones
        } else {
            Toast.makeText(this, "Incorrecto, intenta de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }
}
