import React from 'react';
import { TouchableOpacity, Text } from 'react-native';

// Colores para replicar la calculadora de iOS
const COLORS = {
  functionBg: '#d4d4d2',  // Gris claro
  operatorBg: '#ff9500',  // Naranja iOS
  numberBg:   '#333333',  // Gris oscuro para números
};

const Button = ({ label, type, wide, onPress }) => {
  // Definimos color de fondo y de texto por defecto (botón numérico)
  let backgroundColor = COLORS.numberBg;
  let textColor = '#fff';

  // Ajustamos colores según el "type"
  if (type === 'function') {
    backgroundColor = COLORS.functionBg;
    textColor = '#000';
  } else if (type === 'operator') {
    backgroundColor = COLORS.operatorBg;
    textColor = '#fff';
  }

  // Clases base para todos los botones
  const baseClasses = 'rounded-full justify-center items-center';
  
  // Tamaño normal de los botones (cuadrados/circulares)
  let sizeClasses = 'w-16 h-16';
  
  // Si el botón es "wide" (por ejemplo, el "0"), se hace más ancho y con forma de pastilla
  if (wide) {
    sizeClasses = 'w-36 h-16 justify-center items-start px-5'; 
    // "items-start" y "px-5" para que el texto quede a la izquierda, imitando iOS
  }

  return (
    <TouchableOpacity
      style={{ backgroundColor }}
      className={`${baseClasses} ${sizeClasses} m-1`}
      activeOpacity={0.7}
      onPress={() => onPress(label, type)}
    >
      <Text style={{ color: textColor }} className="text-2xl font-semibold">
        {label}
      </Text>
    </TouchableOpacity>
  );
};

export default Button;
