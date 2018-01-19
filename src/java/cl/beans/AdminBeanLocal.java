/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.model.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roman
 */
@Local

public interface AdminBeanLocal {
    List<Categoria> categoriaList();
    List<Producto> productoList();
    String insert(Object o);
    
}
