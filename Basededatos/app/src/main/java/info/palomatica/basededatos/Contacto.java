package info.palomatica.basededatos;

public class Contacto
{
    private int id;
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono)
    {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Contacto(int id, String nombre, String telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Contacto()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
}
