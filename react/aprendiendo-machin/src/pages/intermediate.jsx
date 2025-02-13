import React, { useState } from 'react';
import { shuffleArray} from '../utils/shuffleArray'; 
import { questions } from '../json/questions'; 

export function Intermediate() {
  // Mezclar las preguntas al iniciar, con sus opciones (por si quieres mostrarlas como guía)
  const initialQuestionFlow = shuffleArray(
    questions.map((q) => {
      return {
        ...q,
        options: shuffleArray(q.options) // Opcional: si aún quieres barajarlas
      };
    })
  );

  // Arreglo de preguntas en orden aleatorio
  const [questionFlow, setQuestionFlow] = useState(initialQuestionFlow);

  // Índice de la pregunta actual
  const [currentIndex, setCurrentIndex] = useState(0);

  // Cuántas se han contestado correctamente
  const [answeredCount, setAnsweredCount] = useState(0);

  // Respuesta que el usuario escribe
  const [writtenAnswer, setWrittenAnswer] = useState('');

  // Feedback de la pregunta actual
  const [feedback, setFeedback] = useState('');

  // Almacena la respuesta correcta para mostrarla tras un error
  const [correctAnswer, setCorrectAnswer] = useState('');

  // Obtener la pregunta actual
  const currentQuestion = questionFlow[currentIndex] || null;

  // Verificar la respuesta que el usuario ingresó
  const handleVerify = () => {
    if (!currentQuestion) return;

    // Normalizamos la cadena para comparar, quita espacios extras, mayúsculas, etc.
    const userAnswer = writtenAnswer.trim().toLowerCase();
    const realAnswer = currentQuestion.answer.trim().toLowerCase();

    // Comparación directa
    if (userAnswer === realAnswer) {
      setFeedback('Correcto');
      setCorrectAnswer(''); // Limpia la respuesta correcta en caso de anterior error
    } else {
      setFeedback('Incorrecto');
      setCorrectAnswer(currentQuestion.answer);
    }
  };

  // Pasar a la siguiente pregunta (solo si está correcta)
  const handleNext = () => {
    if (feedback !== 'Correcto') return; // Evita avanzar si está mal

    // Incrementa el conteo de preguntas correctas
    setAnsweredCount((prev) => prev + 1);

    // Avanza al siguiente índice
    setCurrentIndex((prev) => {
      const nextIndex = prev + 1;
      return nextIndex < questionFlow.length ? nextIndex : prev;
    });

    // Limpia los estados relacionados
    setWrittenAnswer('');
    setFeedback('');
    setCorrectAnswer('');
  };

  // Retroceder a la pregunta anterior
  const handlePrev = () => {
    setCurrentIndex((prev) => {
      const newIndex = prev - 1;
      return newIndex >= 0 ? newIndex : 0; // Evita índice -1
    });

    // Limpia campos al retroceder (podrías mantenerlos si quieres)
    setWrittenAnswer('');
    setFeedback('');
    setCorrectAnswer('');
  };

  // Reiniciar la sesión (volver a mezclar preguntas)
  const resetSession = () => {
    const newQuestionFlow = shuffleArray(
      questions.map((q) => ({
        ...q,
        options: shuffleArray(q.options) // Opcional
      }))
    );
    setQuestionFlow(newQuestionFlow);
    setCurrentIndex(0);
    setAnsweredCount(0);
    setWrittenAnswer('');
    setFeedback('');
    setCorrectAnswer('');
  };

  return (
    <div className="p-4 max-w-md mx-auto bg-white shadow-lg rounded-lg">
      {/* Progreso */}
      <p className="mb-2">
        Preguntas respondidas: {answeredCount} / {questions.length}
      </p>

      {/* Si no hay pregunta actual, terminamos */}
      {currentQuestion ? (
        <>
          <h2 className="text-lg font-bold mb-2">
            {currentQuestion.question}
          </h2>

          {/* Campo de texto donde se escribe la respuesta */}
          <textarea
            className="w-full p-2 border border-gray-300 rounded mb-2 focus:outline-none focus:ring-2 focus:ring-blue-300"
            rows="4"
            value={writtenAnswer}
            onChange={(e) => setWrittenAnswer(e.target.value)}
            placeholder="Escribe tu respuesta aquí..."
          />

          {/* Botón Verificar */}
          <button
            className="p-2 bg-blue-500 text-white rounded-lg"
            onClick={handleVerify}
          >
            Verificar
          </button>

          {/* Muestra feedback */}
          {feedback && (
            <p className="mt-4 font-bold">
              {feedback === 'Correcto' ? '¡Respuesta correcta!' : 'Respuesta incorrecta'}
            </p>
          )}

          {/* Si es incorrecto, mostramos la respuesta correcta */}
          {feedback === 'Incorrecto' && correctAnswer && (
            <p className="mt-2 text-red-600">
              La respuesta correcta es: <br />
              <span className="font-semibold">{correctAnswer}</span>
            </p>
          )}

          {/* Botones para navegar */}
          <div className="mt-4 flex gap-2">
            {/* Botón para regresar pregunta */}
            {currentIndex > 0 && (
              <button
                className="p-2 bg-yellow-500 text-white rounded-lg"
                onClick={handlePrev}
              >
                Anterior
              </button>
            )}

            {/* Avanzar solo si está correcto */}
            <button
              className="p-2 bg-green-500 text-white rounded-lg"
              onClick={handleNext}
              disabled={feedback !== 'Correcto'}
            >
              Siguiente
            </button>
          </div>
        </>
      ) : (
        <p className="font-bold">¡Has respondido todas las preguntas!</p>
      )}

      {/* Botón para reiniciar */}
      <button
        className="mt-4 ml-4 p-2 bg-gray-500 text-white rounded-lg"
        onClick={resetSession}
      >
        Reiniciar Sesión
      </button>
    </div>
  );
};
