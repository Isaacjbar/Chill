import React, { useState, useEffect } from "react";
import { SafeAreaView, View } from "react-native";
import Display from "./display";
import Button from "./button";

const Index = () => {
  const [result, setResult] = useState("0");
  const [lastResult, setLastResult] = useState("");

  useEffect(() => {
    console.log("El valor de result cambió:", result);
  }, [result]);

  /**
   * Devuelve el último "número" del string, es decir, 
   * el substring después del último operador (÷, ×, −, +).
   */
  const getLastNumber = (expr) => {
    // Buscamos el último operador
    const match = expr.match(/[÷×−+]/g);
    if (!match) return expr; // No hay operadores, todo es un número
    // Índice del último operador
    const lastOperatorIndex = Math.max(
      expr.lastIndexOf("÷"),
      expr.lastIndexOf("×"),
      expr.lastIndexOf("−"),
      expr.lastIndexOf("+")
    );
    // Retornamos lo que va después de ese operador
    return expr.slice(lastOperatorIndex + 1);
  };

  /**
   * Reemplaza símbolos "÷", "×", "−" por "/", "*", "-" 
   * para poder evaluarlos con eval.
   */
  const replaceSymbolsForEval = (expr) => {
    return expr
      .replace(/÷/g, "/")
      .replace(/×/g, "*")
      .replace(/−/g, "-");
  };

  /**
   * handleOperation se encarga de procesar la entrada.
   */
  const handleOperation = (label, type) => {
    // Si el resultado actual es "Error", reiniciamos antes de seguir
    if (result === "Error") {
      setResult("0");
    }

    switch (type) {
      case "number":
        // Manejo de punto decimal
        if (label === ".") {
          // Verificar si ya existe un punto en el último número
          const lastNum = getLastNumber(result);
          if (lastNum.includes(".")) {
            // Ya existe un decimal en el último número, no hacemos nada
            return;
          }
          // Si el último carácter es un operador o el display es "0", añadimos "0."
          if (
            ["÷", "×", "−", "+"].includes(result.slice(-1)) ||
            result === "0"
          ) {
            setResult((prev) => prev + (prev === "0" ? "." : "0."));
          } else {
            // De lo contrario, concatenamos el "."
            setResult((prev) => prev + ".");
          }
        } else {
          // Manejo de dígitos 0-9
          if (result === "0") {
            // Si el display está en "0", reemplazamos directamente
            setResult(label);
          } else {
            // Concatenamos el dígito
            setResult((prev) => prev + label);
          }
        }
        break;

      case "function":
        if (label === "AC") {
          // Reiniciamos la calculadora
          setResult("0");
          setLastResult("");
        } else if (label === "+/-") {
          // Cambiamos el signo del último número
          // Si es "0", no hacemos nada
          if (result !== "0") {
            // Si hay un operador al final, no cambiamos el signo
            const lastChar = result.slice(-1);
            if (["÷", "×", "−", "+"].includes(lastChar)) return;

            // Convertimos a número y multiplicamos por -1
            setResult((prev) => {
              // Obtenemos la parte anterior (sin el último número)
              const lastNum = getLastNumber(prev);
              const numericValue = parseFloat(lastNum) * -1;
              // Reemplazamos el último número por el valor con signo invertido
              return (
                prev.slice(0, prev.length - lastNum.length) +
                numericValue.toString()
              );
            });
          }
        } else if (label === "%") {
          // Manejo de porcentaje: toma el último número y lo divide entre 100
          const lastChar = result.slice(-1);
          // Si termina en operador, no hacemos nada
          if (["÷", "×", "−", "+"].includes(lastChar)) return;

          setResult((prev) => {
            const lastNum = getLastNumber(prev);
            const numericValue = parseFloat(lastNum) / 100;
            return (
              prev.slice(0, prev.length - lastNum.length) +
              numericValue.toString()
            );
          });
        }
        break;

      case "operator":
        if (label === "=") {
          // Evaluar la expresión
          try {
            // Si termina en operador o en ".", quita ese carácter
            let temp = result;
            const lastChar = temp.slice(-1);
            if (
              ["÷", "×", "−", "+"].includes(lastChar) ||
              lastChar === "."
            ) {
              temp = temp.slice(0, -1);
            }

            const expression = replaceSymbolsForEval(temp);
            const evalResult = eval(expression); // Uso simplificado
            setResult(evalResult.toString());
            setLastResult(`${temp} = ${evalResult}`);
          } catch (error) {
            setResult("Error");
          }
        } else {
          // Operadores básicos: ÷, ×, −, +
          const lastChar = result.slice(-1);
          // Si ya hay un operador al final, lo reemplazamos
          if (["÷", "×", "−", "+"].includes(lastChar)) {
            setResult((prev) => prev.slice(0, -1) + label);
          } else {
            setResult((prev) => prev + label);
          }
        }
        break;

      default:
        break;
    }
  };

  return (
    <SafeAreaView className="flex-1 bg-black">
      <Display result={result} lastResult={lastResult} />
      <View className="grid grid-cols-4 gap-3 p-4">
        <Button label="AC" type="function" onPress={handleOperation} />
        <Button label="+/-" type="function" onPress={handleOperation} />
        <Button label="%" type="function" onPress={handleOperation} />
        <Button label="÷" type="operator" onPress={handleOperation} />

        <Button label="7" type="number" onPress={handleOperation} />
        <Button label="8" type="number" onPress={handleOperation} />
        <Button label="9" type="number" onPress={handleOperation} />
        <Button label="×" type="operator" onPress={handleOperation} />

        <Button label="4" type="number" onPress={handleOperation} />
        <Button label="5" type="number" onPress={handleOperation} />
        <Button label="6" type="number" onPress={handleOperation} />
        <Button label="−" type="operator" onPress={handleOperation} />

        <Button label="1" type="number" onPress={handleOperation} />
        <Button label="2" type="number" onPress={handleOperation} />
        <Button label="3" type="number" onPress={handleOperation} />
        <Button label="+" type="operator" onPress={handleOperation} />

        <View className="col-span-2">
          <Button label="0" type="number" wide onPress={handleOperation} />
        </View>
        <Button label="." type="number" onPress={handleOperation} />
        <Button label="=" type="operator" onPress={handleOperation} />
      </View>
    </SafeAreaView>
  );
};

export default Index;
