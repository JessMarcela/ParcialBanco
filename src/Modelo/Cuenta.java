/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Observable;


public abstract class Cuenta extends Observable {
    
    private int numero;
    private long saldo;
    
    public void consignar(int saldo){
        
        this.saldo += saldo;
        setChanged();
        notifyObservers("Consignación de "+saldo+" \nSaldo total: "+this.saldo);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }
    
    public abstract void retirar(int saldo);
    
    public int generarContrasena(){
        return (int)(100000 * Math.random());
    }
}
