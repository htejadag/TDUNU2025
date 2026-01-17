package com.example.MsEvaluacion.util;

import java.util.List;

public class EvaluacionCalculador {

    public static Double calcularPromedioPracticas(List<Double> notasPracticas) {
        if (notasPracticas == null || notasPracticas.isEmpty()) {
            return null;
        }

        double suma = notasPracticas.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        double promedio = suma / notasPracticas.size();
        return Math.round(promedio * 100.0) / 100.0;
    }

    public static Double calcularPromedioFinal(Double promedioPracticas, Double notaExamenParcial,
            Double notaExamenFinal) {
        if (promedioPracticas == null || notaExamenParcial == null || notaExamenFinal == null) {
            return null;
        }

        double promedio = (promedioPracticas + notaExamenParcial + notaExamenFinal) / 3.0;
        return Math.round(promedio * 100.0) / 100.0;
    }

    public static boolean esNotaValida(Double nota) {
        if (nota == null) {
            return false;
        }
        return nota >= 0 && nota <= 20;
    }

    public static boolean sonNotasValidas(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return false;
        }
        return notas.stream().allMatch(EvaluacionCalculador::esNotaValida);
    }
}
