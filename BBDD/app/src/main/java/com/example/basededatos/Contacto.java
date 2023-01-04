package com.example.basededatos;

class Contacto
{
    private int id;
    private String nombre;
    private int telefono;
    //meter telefono mejor como String

    public Contacto()
    {

    }

    public Contacto(String nombre, int telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Contacto(int id, String nombre, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getTelefono()
    {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
