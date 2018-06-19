package beans;

import dao.CategoriaFacade;
import entities.Categoria;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "categoriaBean")
@RequestScoped
public class CategoriaBean {

    @EJB
    private CategoriaFacade categoriaFacade;

    public CategoriaBean() {
    }
    
    @PostConstruct
    public void inicio(){
        listaCategorias = categoriaFacade.findAll();
    }
    
    /*******************************************************/
    
//    private Categoria categoria = new Categoria();
//
//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }
    
    /*******************************************************/
    
    private List<Categoria> listaCategorias;

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    /*******************************************************/
    
    public void crearCategoria(){
        try{
            categoriaFacade.crearCategoria();  
        }catch(Exception e)
        {
            System.err.println("******************** Excepción: " + e.getMessage());
        }finally{
            listaCategorias = categoriaFacade.findAll();
        }
    }    
}
