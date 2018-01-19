/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.model.Categoria;
import cl.model.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author roman
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "persistPets")
    private EntityManager em;
    
    @Override
    public List<Categoria> categoriaList() {
        return em.createQuery("select c from Categoria c").getResultList();
    }

    @Override
    public List<Producto> productoList() {
        return em.createQuery("select c from Producto c").getResultList();
    }

    @Override
    public String insert(Object o) {
        try{
           em.persist(o);
        }catch(Exception e){
            return "Error:"+e.getMessage();
        }
        return "creado con exito";
    }

    
}
