package com.amador.deportes;

/**
 *
 * Clase modelo que representa a los deportes
 *
 * */
public class Deportes {

    private int imv;
    private String nombre;
    private boolean seguido;
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getImv() {
        return imv;
    }

    public void setImv(int imv) {
        this.imv = imv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSeguido() {
        return seguido;
    }

    public void setSeguido(boolean seguido) {
        this.seguido = seguido;
    }

    public Deportes(int imv, String nombre, boolean seguido, boolean visible) {
        this.imv = imv;
        this.nombre = nombre;
        this.seguido = seguido;
        this.visible = visible;
    }
}
