package com.alejandro.mibasedatos;

public class Contacto {
    int id;
    String usuario;
    String email;
    String tel;
    String fecha_nacimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Contacto(int id, String usuario, String email, String tel, String fecha_nacimiento) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.tel = tel;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String toString() {
        return usuario + "\n" + email;
    }
}
