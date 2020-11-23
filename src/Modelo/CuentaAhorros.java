/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author frealgagu
 */
public class CuentaAhorros extends Cuenta {
    
    private int clave;

    public CuentaAhorros() {
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }
    
    @Override
    public void retirar(int saldo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int generarContrasena(){
        return (int)(100000 * Math.random());
    }
}
