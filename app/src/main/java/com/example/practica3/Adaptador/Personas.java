package com.example.practica3.Adaptador;

public class Personas {
    String nombre;
    double salario;
    int horas;
    public Personas(String nombre, double salario, int horas){
        this.nombre = nombre;
        this.salario = salario;
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
