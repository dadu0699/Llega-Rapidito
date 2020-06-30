package org.jd.modelos;

public class Conductor {

    private String DPI;
    private String nombres;
    private String apellidos;
    private String licencia;
    private String genero;
    private String fechaNacimiento;
    private String telefono;
    private String direccion;

    public Conductor(String DPI, String nombres, String apellidos, String licencia,
            String genero, String fechaNacimiento, String telefono, String direccion) {
        this.DPI = DPI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
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

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
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

    @Override
    public String toString() {
        return this.DPI + " -- " + this.nombres + " " + this.apellidos;
    }
}
