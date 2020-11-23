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
import java.util.ArrayList;

public class Control implements ActionListener {

    Cliente cliente;
    CuentaAhorros ahorros;
    TarjetaCredito credito;
    Banco banco = new Banco();
    Formulario formulario = new Formulario();
    int si = 0, contador = 1, cedula;

    public Control() {

        formulario.getRegistrarse().addActionListener(this);
        formulario.getTarjetaCrédito().addActionListener(this);
        formulario.getListo().addActionListener(this);
        formulario.getCuentaAhorros().addActionListener(this);
        formulario.getAccederalSistema().addActionListener(this);
        formulario.getVerSaldo().addActionListener(this);
        formulario.getConsignarCuenta().addActionListener(this);

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

            if (banco.verificarID(cliente.getId()) == -1) {
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
                credito.setSaldo(Integer.parseInt(this.formulario.getCupoSaldo().getText()));
                credito.setNumero(contador);
                cliente.getCuentasCredito().add(credito);
                banco.getClientes().add(cliente);
                formulario.mostrarResultado("El número de cuenta es "+contador);
                contador++;
                this.formulario.getRegistrarseDialog().dispose();
            } else {
                ahorros.setSaldo(Integer.parseInt(this.formulario.getCupoSaldo().getText()));
                ahorros.setClave(ahorros.generarContrasena());
                ahorros.setNumero(contador);
                cliente.getCuentasAhorro().add(ahorros);
                banco.getClientes().add(cliente);
                formulario.mostrarResultado("El número de cuenta es "+contador+" \nLa clave es: "+ahorros.getClave());
                 contador++;
                this.formulario.getRegistrarseDialog().dispose();
            }
        }

        if (ae.getSource() == this.formulario.getCuentaAhorros()) {

            cliente = new Cliente();
            ahorros = new CuentaAhorros();
            cliente.setId(Integer.parseInt(this.formulario.getIdNuevo().getText()));

            if (banco.verificarID(cliente.getId()) == -1) {
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
             cedula = formulario.solicitarEntero("Digite su ID para acceder al sistema");
            if (cedula >= 0) {
                if (banco.verificarID(cedula) != -1) {
                    this.formulario.getOpcionesUsuario().setSize(400, 410);
                    this.formulario.getOpcionesUsuario().setLocationRelativeTo(formulario);
                    formulario.getOpcionesUsuario().setVisible(true);
                } else {
                    formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                }
            } else {
                formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
            }
        }

        if (ae.getSource() == this.formulario.getVerSaldo()) {

            if (formulario.getTarjetaCreditoOpcion().isSelected()) {
               
                if (cedula >= 0) {
                    int usuario = banco.verificarID(cedula);
                    if (usuario != -1) {
                        int cuenta = formulario.solicitarEntero("Digite el número de la cuenta que desea ver el saldo: ");
                        int posicionCuenta = banco.verificarCuentaCredito(usuario, cuenta);
                        if (posicionCuenta != -1) {
                            long saldo = banco.getClientes().get(usuario).getCuentasCredito().get(posicionCuenta).getSaldo();
                            formulario.getRespuestaCuentas().setText("El saldo de la tarjeta " + cuenta + " es: " + saldo);
                        } else {
                            formulario.getRespuestaCuentas().setText("No existe una tarjeta de crédito con el número " + cuenta);
                        }
                    } else {
                        formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                    }
                } else {
                    formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
                }
            }

            if (formulario.getCuentaAhorrosOpcion().isSelected()) {

               
                if (cedula >= 0) {
                    int usuario = banco.verificarID(cedula);
                    if (usuario != -1) {
                        int cuenta = formulario.solicitarEntero("Digite el número de la cuenta que desea ver el saldo: ");
                        int posicionCuenta = banco.verificarCuentaAhorro(usuario, cuenta);
                        if (posicionCuenta != -1) {
                            long saldo = banco.getClientes().get(usuario).getCuentasAhorro().get(posicionCuenta).getSaldo();
                            formulario.getRespuestaCuentas().setText("El saldo de la cuenta " + cuenta + " es: " + saldo);
                        } else {
                            formulario.getRespuestaCuentas().setText("No existe una cuenta de ahorros con el número " + cuenta);
                        }
                    } else {
                        formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                    }
                } else {
                    formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
                }
            }

            if (ae.getSource() == this.formulario.getConsignarCuenta()) {

                if (formulario.getTarjetaCreditoOpcion().isSelected()) {
                  
                    if (cedula >= 0) {
                        int usuario = banco.verificarID(cedula);
                        if (usuario != -1) {
                            int cuenta = formulario.solicitarEntero("Digite el número de la cuenta que desea ver el saldo: ");
                            int posicionCuenta = banco.verificarCuentaCredito(usuario, cuenta);
                            if (posicionCuenta != -1) {
                                long saldo = formulario.solicitarLong("Digite cuánto quiere consignar: ");
                                long saldoExistente = banco.getClientes().get(usuario).getCuentasCredito().get(posicionCuenta).getSaldo();
                                long cupo = banco.getClientes().get(usuario).getCuentasCredito().get(posicionCuenta).getCupo();
                                if (saldo + saldoExistente <= cupo) {
                                    banco.getClientes().get(usuario).getCuentasCredito().get(posicionCuenta).consignar(usuario);
                                } else {
                                    formulario.mostrarResultado("No puede consignar ese valor, el saldo no debe exceder el límite del cupo");
                                }
                            } else {
                                formulario.getRespuestaCuentas().setText("No existe una tarjeta de crédito con el número " + cuenta);
                            }
                        } else {
                            formulario.mostrarResultado("El usuario con ese ID no existe en nuestra base de datos");
                        }
                    } else {
                        formulario.mostrarResultado("El ID no puede ser menor o igual a 0");
                    }
                }

                if (formulario.getCuentaAhorrosOpcion().isSelected()) {

                    if (formulario.getCuentaAhorrosOpcion().isSelected()) {
        
                        if (cedula >= 0) {
                            int usuario = banco.verificarID(cedula);
                            if (usuario != -1) {
                                int cuenta = formulario.solicitarEntero("Digite el número de la cuenta que desea ver el saldo: ");
                                int posicionCuenta = banco.verificarCuentaAhorro(usuario, cuenta);
                                if (posicionCuenta != -1) {
                                    long saldo = formulario.solicitarLong("Digite cuánto quiere consignar: ");      
                                    if (saldo > 0) {
                                        banco.getClientes().get(usuario).getCuentasAhorro().get(posicionCuenta).consignar(usuario);
                                    } else {
                                        formulario.mostrarResultado("No puede consignar valores menores a 0");
                                    }
                                } else {
                                    formulario.getRespuestaCuentas().setText("No existe una tarjeta de crédito con el número " + cuenta);
                                }
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
    }

}