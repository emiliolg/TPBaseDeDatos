package biblioteca.model;

import java.security.InvalidParameterException;
import java.time.LocalDate;

public class Empleado {

    public enum Turno {
        MANANA, TARDE, NOCHE;

        @Override
        public String toString() {
            switch (this) {
                case MANANA:
                    return "Mañana";
                case TARDE:
                    return "Tarde";
                case NOCHE:
                    return "Noche";
            }
            return "";
        }
    }

    private final int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Turno turno;

    public Empleado(int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        switch (turno) {
            case "Mañana":
                this.turno = Turno.MANANA;
                break;
            case "Tarde":
                this.turno = Turno.TARDE;
                break;
            case "Noche":
                this.turno = Turno.NOCHE;
                break;
            default:
                throw new IllegalArgumentException("Turno invalido: " + turno);
        }
    }

}
