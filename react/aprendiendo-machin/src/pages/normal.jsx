import React, { useState } from "react";
import { shuffleArray} from '../utils/shuffleArray'; 
import { questions } from '../json/questions'; 

const Normal = () => {
  // 1) Mezclar todas las preguntas
  // 2) Mezclar las opciones de cada pregunta
  const initialQuestionFlow = shuffleArray(
    questions.map((q) => {
      return {
        ...q,
        options: shuffleArray(q.options),
      };
    })
  );

  // Arreglo de preguntas en orden aleatorio
  const [questionFlow, setQuestionFlow] = useState(initialQuestionFlow);

  // Índice de la pregunta actual
  const [currentIndex, setCurrentIndex] = useState(0);

  // ¿Cuántas se han contestado? (aparece en la interfaz)
  // Aquí reflejamos las que se han “validado” como correctas.
  const [answeredCount, setAnsweredCount] = useState(0);

  // Respuesta seleccionada
  const [selectedAnswer, setSelectedAnswer] = useState(null);

  // Feedback de la respuesta actual
  const [feedback, setFeedback] = useState("");

  // Obtener la pregunta actual
  const currentQuestion = questionFlow[currentIndex] || null;

  // Manejar el clic en una opción
  const handleAnswerClick = (option) => {
    if (!currentQuestion) return;
    setSelectedAnswer(option);

    if (option === currentQuestion.answer) {
      setFeedback("Correcto");
    } else {
      setFeedback("Incorrecto");
    }
  };

  // Pasar a la siguiente pregunta
  // Solo se permite pasar si la respuesta es correcta
  const handleNext = () => {
    if (feedback !== "Correcto") return; // Evita avanzar si está mal

    // Incrementa el conteo de preguntas correctas
    setAnsweredCount(answeredCount + 1);

    // Avanza al siguiente índice
    setCurrentIndex((prev) => {
      const next = prev + 1;
      return next < questionFlow.length ? next : prev;
    });

    // Reinicia la selección y el feedback
    setSelectedAnswer(null);
    setFeedback("");
  };

  // Retroceder a la pregunta anterior
  const handlePrev = () => {
    setCurrentIndex((prev) => {
      const next = prev - 1;
      return next >= 0 ? next : 0; // Evita que sea < 0
    });

    // Asumiendo que al regresar no borramos la respuesta ya dada,
    // podrías resetear aquí si quisieras.
    setSelectedAnswer(null);
    setFeedback("");
  };

  // Reiniciar sesión (volver a mezclar todo)
  const resetSession = () => {
    // Mezclar las preguntas y sus opciones de nuevo
    const newQuestionFlow = shuffleArray(
      questions.map((q) => {
        return {
          ...q,
          options: shuffleArray(q.options),
        };
      })
    );
    setQuestionFlow(newQuestionFlow);
    setCurrentIndex(0);
    setAnsweredCount(0);
    setSelectedAnswer(null);
    setFeedback("");
  };

  return (
    <div className="p-4 max-w-md mx-auto bg-white shadow-lg rounded-lg">
      {/* Muestra el progreso */}
      <p className="mb-2">
        Preguntas respondidas: {answeredCount} / {questions.length}
      </p>

      {/* Si no hay pregunta actual, significa que terminamos */}
      {currentQuestion ? (
        <>
          {/* Título de la pregunta */}
          <h2 className="text-lg font-bold">{currentQuestion.question}</h2>

          {/* Opciones (aleatorias) */}
          <ul className="mt-2">
            {currentQuestion.options.map((option, index) => (
              <li
                key={index}
                className={`
                    p-2 border rounded-lg cursor-pointer mt-2
                    ${selectedAnswer === option ? "bg-blue-300" : "bg-gray-100"}
                  `}
                onClick={() => handleAnswerClick(option)}
              >
                {option}
              </li>
            ))}
          </ul>

          {/* Muestra el feedback */}
          {feedback && <p className="mt-4 font-bold">{feedback}</p>}

          {/* Botones Anterior / Siguiente */}
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

            {/* Botón para avanzar a la siguiente pregunta
                  Solo se habilita si la respuesta es correcta */}
            <button
              className="p-2 bg-blue-500 text-white rounded-lg"
              onClick={handleNext}
              disabled={feedback !== "Correcto"}
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
        className="mt-4 ml-4 p-2 bg-green-500 text-white rounded-lg"
        onClick={resetSession}
      >
        Reiniciar Sesión
      </button>
    </div>
  );
};

export default Normal;
