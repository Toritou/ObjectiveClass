package Modelo;

import java.util.Objects;

public class Medico {
    private String nombre;
    private String rut;
    private String especialidad;

    public Medico(String nombre, String rut, String especialidad) {
        this.nombre = nombre;
        this.rut = rut;
        this.especialidad = especialidad;
    }


    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medico medico = (Medico) obj;
        return nombre.equals(medico.nombre) && especialidad.equals(medico.especialidad);
    }

    public int hashCode() {
        return Objects.hash(nombre, especialidad);
    }

    public String toString() {
        return "Doctor{" +
                "nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
