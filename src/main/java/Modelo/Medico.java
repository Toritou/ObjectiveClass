package Modelo;

import java.util.Objects;

public class Medico {
    private String nombre;
    private String especialidad;

    public Medico(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }


    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medico medico = (Medico) obj;
        return nombre.equals(medico.nombre) && especialidad.equals(medico.especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, especialidad);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
