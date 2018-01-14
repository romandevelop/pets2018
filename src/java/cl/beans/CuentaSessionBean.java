/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author roman
 */
@Stateless
public class CuentaSessionBean implements CuentaSessionBeanLocal {

     @PersistenceContext(unitName = "persistPets")
    private EntityManager em;
    
    
    @Override
    public Usuario buscarUsuario(String rut) {
        return em.find(Usuario.class, rut);
    }

    @Override
    public Usuario login(String rut, String clave) {
        Usuario user = buscarUsuario(rut);
        System.out.println("clave user:"+user.getClave());
        System.out.println("clave recibida:"+clave);
        return user!=null?(user.getClave().equals(clave)?user:null):null;
    }

    @Override
    public String registro(Usuario user) {
        try{
            em.persist(user);
        }catch(Exception e){
            return e.getMessage();
        }
        return "cuenta creada";
    }

}
