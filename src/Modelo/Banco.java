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
    
    public boolean verificarID(int id){
        
         for(int i=0; i<clientes.size(); i++){
            
           if(clientes.get(i).getId() == id)
               return true;
            
        }
        return false;
        
    }
    
}
