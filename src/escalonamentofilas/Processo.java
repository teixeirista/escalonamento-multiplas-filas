package escalonamentofilas;

/**
 *
 * @author mathe
 */
public class Processo {
    
    private int id; //Identificador do processo
    private int ucp; //Tempo de processamento do processo
    
    public Processo(int i, int t) {
        id = i;
        ucp = t;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getUCP() {
        return ucp; 
    }
    
    public void setUCP(int t) {
        ucp = t;
    }
    
}
