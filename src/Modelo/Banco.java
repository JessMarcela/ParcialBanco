/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author frealgagu
 */
public class Banco {
    
    ArrayList<Cliente> clientes = new ArrayList();

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public int verificarID(int id){
        
         for(int i=0; i<clientes.size(); i++){
            
           if(clientes.get(i).getId() == id)
               return i;
            
        }
        return -1; 
    }
    
    public int verificarCuentaCredito(int posicion, int numeroC){
        
        for(int i=0; i<clientes.get(posicion).getCuentasCredito().size(); i++){
            
            if(clientes.get(posicion).getCuentasCredito().get(i).getNumero() == numeroC)
                return i;
            
        }
        return -1;
    }
     public int verificarCuentaAhorro(int posicion, int numeroC){
        
        for(int i=0; i<clientes.get(posicion).getCuentasAhorro().size(); i++){
            
            if(clientes.get(posicion).getCuentasAhorro().get(i).getNumero() == numeroC)
                return i;
            
        }
        return -1;
    }
}
