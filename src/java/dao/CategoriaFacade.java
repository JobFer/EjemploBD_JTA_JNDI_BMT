package dao;

import entities.Categoria;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

//Como se usa "UserTransaction", solo se permite BEAN. Da error si se pone CONTAINER o si se deja en blanco
@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @EJB
    private EJBSubordinado eJBSubordinado;

    @Resource
    private EJBContext context;

    @PersistenceContext(unitName = "EjemploBD_JTA_JNDI_BMTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    public void crearCategoria() throws Exception{
        System.out.println("*************** principal ****************");
        UserTransaction tx = context.getUserTransaction();

        try {
            tx.begin();

            em.persist(new Categoria("miCategoria"));
//            em.persist(new Categoria(1)); //Para que se produzca una excepcion

            eJBSubordinado.subordinado();
            
        } catch (Exception ex) {
            System.out.println("************ Excepcion: " + ex.getMessage());
        }finally{
            tx.commit();
        }
    }
}
