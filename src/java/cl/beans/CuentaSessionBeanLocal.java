/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.model.Usuario;
import javax.ejb.Local;

/**
 *
 * @author roman
 */
@Local
public interface CuentaSessionBeanLocal {
     Usuario buscarUsuario(String rut);
    Usuario login(String rut, String clave);
    String registro(Usuario user);
}
