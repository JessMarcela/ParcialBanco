/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;


public class Cliente {
    
    private String nombre;
    private int id;
    private ArrayList<TarjetaCredito> cuentasCredito = new ArrayList();
    private ArrayList<CuentaAhorros> cuentasAhorro = new ArrayList();

    public Cliente() {
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<TarjetaCredito> getCuentasCredito() {
        return cuentasCredito;
    }

    public void setCuentasCredito(ArrayList<TarjetaCredito> cuentasCredito) {
        this.cuentasCredito = cuentasCredito;
    }

    public ArrayList<CuentaAhorros> getCuentasAhorro() {
        return cuentasAhorro;
    }

    public void setCuentasAhorro(ArrayList<CuentaAhorros> cuentasAhorro) {
        this.cuentasAhorro = cuentasAhorro;
    }

    
}
