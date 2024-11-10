package co.edu.unbosque.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppointmentController {
	private Set<String> citasAsignadas = new HashSet<>();
    private LocalDateTime ultimaCitaAsignada = null;
    private final String ARCHIVO_CITAS = "citas.txt";
    private static ArrayList<String> listaCitas = new ArrayList<>();

    public AppointmentController() {
        cargarCitasGuardadas();
    }

    public void cargarCitasGuardadas() {
        try {
            File archivo = new File(ARCHIVO_CITAS);
            if (archivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    citasAsignadas.add(linea.trim());
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        ultimaCitaAsignada = LocalDateTime.parse(linea.trim(), formatter);
                    } catch (Exception e) {
                        System.out.println("Error al parsear la fecha: " + linea);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las citas: " + e.getMessage());
        }
    }

    public void guardarCitas() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_CITAS));
            List<String> citasOrdenadas = new ArrayList<>(citasAsignadas);
            Collections.sort(citasOrdenadas);
            for (String cita : citasOrdenadas) {
                writer.write(cita);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar las citas: " + e.getMessage());
        }
    }

    public String generarCitas() {
        final int HORA_INICIO = 7;   // 7am
        final int HORA_FIN = 17;     // 5pm
        final int DURACION_CITA = 30; // duración en minutos

        // Definir una fecha de inicio específica para la generación de citas
        LocalDateTime fechaBase = LocalDateTime.of(2024, 11, 12, HORA_INICIO, 0);

        // Si hay una última cita asignada, comenzar desde esa fecha y hora
        if (ultimaCitaAsignada != null) {
            fechaBase = ultimaCitaAsignada.plusMinutes(DURACION_CITA);
        }

        // Encuentra y ajusta la siguiente cita disponible
        LocalDateTime citaAjustada = encontrarSiguienteCitaDisponible(fechaBase, HORA_INICIO, HORA_FIN, DURACION_CITA);
        ultimaCitaAsignada = citaAjustada;

        String citaFormateada = citaAjustada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        guardarCitas();

        System.out.println("Su cita está asignada para el día: " +
            citaAjustada.getDayOfMonth() + "/" +
            citaAjustada.getMonthValue() + "/" +
            citaAjustada.getYear() +
            "\nA las " + citaAjustada.getHour() + ":" +
            String.format("%02d", citaAjustada.getMinute()));
        listaCitas.add(citaFormateada);
        if(citaFormateada.contains(citaFormateada)) {
        	return   citaAjustada.getDayOfMonth() + "/" +
                    citaAjustada.getMonthValue() + "/" +
                    citaAjustada.getYear() +
                    "\nA las " + citaAjustada.getHour() + ":" +
                    String.format("%02d", citaAjustada.getMinute());
        }else {
        return citaFormateada;
    }}


    public LocalDateTime encontrarSiguienteCitaDisponible(LocalDateTime fecha, int horaInicio, int horaFin, int duracionCita) {
        // Si es fin de semana, ajustar al próximo día laboral
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY) {
            fecha = fecha.plusDays(2).withHour(horaInicio).withMinute(0);
        } else if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            fecha = fecha.plusDays(1).withHour(horaInicio).withMinute(0);
        }

        // Ajustar la cita si está fuera del horario laboral
        if (fecha.getHour() >= horaFin || (fecha.getHour() == horaFin && fecha.getMinute() > 0)) {
            fecha = fecha.plusDays(1).withHour(horaInicio).withMinute(0);
            // Si el día siguiente es fin de semana, ajustar al próximo día laboral
            if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY) {
                fecha = fecha.plusDays(2);
            } else if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fecha = fecha.plusDays(1);
            }
        }

        // Verificar si la fecha ya está ocupada
        String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        if (citasAsignadas.contains(fechaFormateada)) {
            return encontrarSiguienteCitaDisponible(fecha.plusMinutes(duracionCita), horaInicio, horaFin, duracionCita);
        }

        return fecha;
    }

    public void mostrarCitasAsignadas() {
        System.out.println("\nCitas asignadas:");
        List<String> citasOrdenadas = new ArrayList<>(citasAsignadas);
        Collections.sort(citasOrdenadas);
        for (String cita : citasOrdenadas) {
            System.out.println(cita);
        }
        System.out.println("Total de citas: " + citasAsignadas.size());
    }
	}

