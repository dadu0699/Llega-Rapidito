package org.jd.beans;

public class Cliente {

    private Integer DPI;
    private String nombres;
    private String apellidos;
    private String genero;
    private String fechaNacimiento;
    private String telefono;
    private String direccion;

    public Cliente(Integer DPI, String nombres, String apellidos, String genero,
            String fechaNacimiento, String telefono, String direccion) {
        this.DPI = DPI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getDPI() {
        return DPI;
    }

    public void setDPI(Integer DPI) {
        this.DPI = DPI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
