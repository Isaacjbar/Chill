import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// Ejemplo de componentes HeroUI; ajusta según tu librería real
import { Card, CardHeader, CardBody, CardFooter } from '@heroui/card';
import { Button } from '@heroui/button';

// Ejemplo de arreglo de temas. Sustituye por tus datos reales.
const initialTopics = [
  { id: 1, name: 'IoT U1' }
];

export function Dashboard() {
  const [topics, setTopics] = useState(initialTopics);
  const navigate = useNavigate();

  // Maneja la creación de un nuevo tema (ejemplo simple)
  const handleCreateTopic = () => {
    const newId = topics.length + 1;
    const newTopic = { id: newId, name: `Tema ${newId}` };
    setTopics([...topics, newTopic]);
  };

  // Navega según el modo seleccionado
  const handleModeClick = (topicId, mode) => {
    // Ajusta la ruta de navegación según tu lógica
    navigate(`/${topicId}/${mode}`);
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      {/* Encabezado con el botón para crear tema */}
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">Lista de Temas</h1>
        <Button
          color="primary"
          onClick={handleCreateTopic}
          className="rounded shadow"
        >
          Crear Tema
        </Button>
      </div>

      {/* Contenedor de cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {topics.map((topic) => (
          <Card key={topic.id} className="shadow bg-white">
            <CardHeader className="border-b">
              <h2 className="text-lg font-bold">{topic.name}</h2>
            </CardHeader>

            <CardBody>
              <p className="text-gray-600">
                Selecciona un modo de estudio para empezar a practicar.
              </p>
              {/* Botones circulares para cada modo */}
              <div className="flex space-x-3 mt-4">
                <Button
                  className="w-10 h-10 rounded-full bg-blue-500 text-white font-bold hover:bg-blue-600"
                  onClick={() => handleModeClick(topic.id, 'normal')}
                  title="Modo Normal"
                >
                  N
                </Button>
                <Button
                  className="w-10 h-10 rounded-full bg-yellow-500 text-white font-bold hover:bg-yellow-600"
                  onClick={() => handleModeClick(topic.id, 'intermediate')}
                  title="Modo Intermedio"
                >
                  I
                </Button>
                <Button
                  className="w-10 h-10 rounded-full bg-red-500 text-white font-bold hover:bg-red-600"
                  onClick={() => handleModeClick(topic.id, 'dificil')}
                  title="Modo Difícil"
                >
                  D
                </Button>
              </div>
            </CardBody>

            <CardFooter className="border-t">
              <p className="text-sm text-gray-500">
                ID del tema: {topic.id}
              </p>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  );
}
