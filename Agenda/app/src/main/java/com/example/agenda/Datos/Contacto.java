package com.example.agenda.Datos;

public class Contacto
{
    private int id;
    private String nombre;
    private String telefono;
    private int categoria;
    private String telefono2;
    private String email;
    private String direccion;
    private String aux;

    public Contacto()
    {

    }
    public Contacto(int id, String nombre, String telefono, int categoria, String telefono2, String email, String direccion, String aux)
    {
        this.id=id;
        this.nombre=nombre;
        this.telefono=telefono;
        this.categoria=categoria;
        this.telefono2=telefono2;
        this.email=email;
        this.direccion=direccion;
        this.aux=aux;
    }

    public int getId() {return id;}

    public String getNombre() {return nombre;}

    public String getTelefono() {return telefono;}

    public int getCategoria() {return categoria;}

    public String getTelefono2() {return telefono2;}

    public String getEmail() {return email;}

    public String getDireccion() {return direccion;}

    public String getAux() {return aux;}

    public void setId(int id) {this.id = id;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setCategoria(int categoria) {this.categoria = categoria;}

    public void setTelefono2(String telefono2) {this.telefono2 = telefono2;}

    public void setEmail(String email) {this.email = email;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public void setAux(String aux) {this.aux = aux;}
}