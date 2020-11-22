/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Banco;
import Modelo.Cliente;
import Modelo.CuentaAhorros;
import Modelo.TarjetaCredito;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {
    
    Cliente cliente;
    CuentaAhorros ahorros;
    TarjetaCredito credito;
    Banco banco = new Banco();
    Formulario formulario = new Formulario();
    int si = 0;
    
    public Control() {
        
        formulario.getRegistrarse().addActionListener(this);
        formulario.getTarjetaCrédito().addActionListener(this);
        formulario.getListo().addActionListener(this);
        this.formulario.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == this.formulario.getRegistrarse()) {
            this.formulario.getRegistrarseDialog().setSize(400, 380);
            this.formulario.getRegistrarseDialog().setLocationRelativeTo(formulario);
            this.formulario.getCambiarMensajeSaldo().setVisible(false);
            this.formulario.getCupoSaldo().setVisible(false);
            this.formulario.getListo().setVisible(false);
            this.formulario.getRegistrarseDialog().setVisible(true);
            
        }
        if (ae.getSource() == this.formulario.getTarjetaCrédito()) {
            
            cliente = new Cliente();
            credito = new TarjetaCredito();
            cliente.setId(Integer.parseInt(this.formulario.getIdNuevo().getText()));
            
            if (!banco.verificarID(cliente.getId())) {
                this.formulario.getCambiarMensajeSaldo().setText("Digite el cupo de la tarjeta: ");
                this.formulario.getCambiarMensajeSaldo().setVisible(true);
                this.formulario.getCupoSaldo().setVisible(true);
                this.formulario.getListo().setVisible(true);
                cliente.setNombre(this.formulario.getNombreNuevo().getText());
                si = 1;
            } else {
                formulario.mostrarResultado("El cliente no ha sido añadido. \nNo puede tener el mismo ID que uno ya ingresado");
                this.formulario.getRegistrarseDialog().dispose();
            }
        }
        
        if (ae.getSource() == this.formulario.getListo()) {
            if (si == 1) {
                credito.setCupo(Integer.parseInt(this.formulario.getCupoSaldo().getText()));
                credito.setNumero(credito.generarContrasena());
                cliente.getCuentasCredito().add(credito);
                banco.getClientes().add(cliente);
                this.formulario.getRegistrarseDialog().dispose();
            } else {
                ahorros.setSaldo(Integer.parseInt(this.formulario.getCupoSaldo().getText()));
                ahorros.setNumero(ahorros.generarContrasena());
                cliente.getCuentasAhorro().add(ahorros);
                banco.getClientes().add(cliente);
                this.formulario.getRegistrarseDialog().dispose();
            }
        }
        
        if (ae.getSource() == this.formulario.getCuentaAhorros()) {
            
            cliente = new Cliente();
            ahorros = new CuentaAhorros();
            cliente.setId(Integer.parseInt(this.formulario.getIdNuevo().getText()));
            
            if (!banco.verificarID(cliente.getId())) {
                this.formulario.getCambiarMensajeSaldo().setText("Digite el saldo de la cuenta: ");
                this.formulario.getCambiarMensajeSaldo().setVisible(true);
                this.formulario.getCupoSaldo().setVisible(true);
                this.formulario.getListo().setVisible(true);
                cliente.setNombre(this.formulario.getNombreNuevo().getText());
                si = 2;
            } else {
                formulario.mostrarResultado("El cliente no ha sido añadido. \nNo puede tener el mismo ID que uno ya ingresado");
                this.formulario.getRegistrarseDialog().dispose();
            }
            
        }
        
        if (ae.getSource() == this.formulario.getAccederalSistema()) {
            int cedula = formulario.solicitarEntero("Digite su ID para acceder al sistema");
            if (cedula <= 0) {
                if (banco.verificarID(cedula)) {
                    formulario.getOpcionesUsuario().setVisible(true);
                } else {
                    formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                }
            } else {
                formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
            }
        }
        
        if (ae.getSource() == this.formulario.getVerSaldo()) {
            
            if(formulario.getTarjetaCreditoOpcion().isSelected()){
                 int cedula = formulario.solicitarEntero("Digite su ID para acceder al sistema");
            if (cedula <= 0) {
                
               if (banco.verificarID(cedula)) {
                    formulario.getOpcionesUsuario().setVisible(true);
                } else {
                    formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                } 
                
            } else {
                formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
            }
                
            }
            
            if(formulario.getCuentaAhorrosOpcion().isSelected()){
                
                 int cedula = formulario.solicitarEntero("Digite su ID para acceder al sistema");
            if (cedula <= 0) {
                
                if (banco.verificarID(cedula)) {
                    formulario.getOpcionesUsuario().setVisible(true);
                } else {
                    formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                }
                
            } else {
                formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
            }
                
            }
            
        }
    }
    
}
