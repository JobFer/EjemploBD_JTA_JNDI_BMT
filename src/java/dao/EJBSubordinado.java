/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Categoria;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EJBSubordinado {
    
    @Resource
    private EJBContext context;    
    
    @PersistenceContext(unitName = "EjemploBD_JTA_JNDI_BMTPU")
    private EntityManager em;
    
    //Con BEAN no se permite ningun "TransactionAttributeType". Da error de compilacion.
//    @TransactionAttribute(value = TransactionAttributeType.NEVER)
//    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void subordinado() throws Exception {
        
        System.out.println("*************** subordinado ****************");
        UserTransaction tx = context.getUserTransaction();
        tx.begin();
        em.persist(new Categoria("miCategoriaSubordinada"));
//        em.persist(new Categoria(1));
        tx.commit();            
    }
}
