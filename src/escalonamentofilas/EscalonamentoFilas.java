package escalonamentofilas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mathe
 */
public class EscalonamentoFilas {

    static ArrayList<Processo> fila1 = new ArrayList();
    static ArrayList<Processo> fila2 = new ArrayList();
    static ArrayList<Processo> fila3 = new ArrayList();
    
    static int contPross = 0; //Contador de processos para determinar o id
    
    static Random aleatorio = new Random(); //Usada para gerar um UCP aleatório
    
    public static void main(String[] args) throws InterruptedException {
        
        while(true) {
            new Thread().sleep(3000);
            
            gerarProcesso(); //Gera ou não um novo processo
            
            //Informa a quantidade de processos em cada fila
            System.out.println("A fila 1 tem " + fila1.size() + " processo(s)");
            System.out.println("A fila 2 tem " + fila2.size() + " processo(s)");
            System.out.println("A fila 3 tem " + fila3.size() + " processo(s)");
            
            //Verifica se as filas com mais prioridade tem processos e os executa
            if(fila1.size() > 0) {
                processoFila1();
            } else if(fila2.size() > 0) {
                processoFila2();
            } else if(fila3.size() > 0) {
                processoFila3();
            } else {
                System.out.println("Não há processos para serem executados\n");
            }
        }
    }
    
    /**
     * Geração de um processo
     */
    public static void gerarProcesso() {
        //Sorteia um booleano aleatório para decidir se gera um processo ou não
        boolean aux = aleatorio.nextBoolean();
        
        if(aux) { //Se sim, cria um novo processo
            contPross++;
            Processo p = new Processo(contPross, aleatorio.nextInt(9) + 1); //Sorteia o UCP do processo de 1 a 10
            fila1.add(p); //Adiciona o processo no final da fila 1
        }
    }
    
    /**
     * Execução de um processo da fila 1
     * @throws InterruptedException 
     */
    public static void processoFila1() throws InterruptedException {
        int aux = 2 - fila1.get(0).getUCP(); //Subtrai o quantum pelo UCP do processo
        
        //Exibe qual processo está sendo executado
        System.out.println("\nExecutando processo " + fila1.get(0).getId() + " na fila 1...");
        new Thread().sleep(2000); //Aguarda o tempo do quantum
        
        if(aux >= 0) { //Verifica se o processo foi concluído
            System.out.println("Processo da fila 1 concluído\n");
        } else {
            fila1.get(0).setUCP(Math.abs(aux)); //Redefine o UCP do processo com o que restou da subtração
            fila2.add(fila1.get(0)); //Adiciona o processo no final da fila 2
            System.out.println("O processo da fila 1 não foi concluído");
            System.out.println("Resta(m) " + Math.abs(aux) + " UCP\n");
        }
        fila1.remove(0); //Remove o processo da fila 1
    }
    
    /**
     * Execução de um processo da fila 2
     * @throws InterruptedException 
     */
    public static void processoFila2() throws InterruptedException {
        int aux = 4 - fila2.get(0).getUCP(); //Subtrai o quantum pelo UCP do processo
        
        //Exibe qual processo está sendo executado
        System.out.println("\nExecutando processo " + fila2.get(0).getId() + " na fila 2...");
        new Thread().sleep(4000); //Aguarda o tempo do quantum
        
        if(aux >= 0) { //Verifica se o processo foi concluído
            System.out.println("Processo da fila 2 concluído\n");
        } else {
            fila2.get(0).setUCP(Math.abs(aux)); //Redefine o UCP do processo com o que restou da subtração
            fila3.add(fila2.get(0)); //Adiciona o processo no final da fila 3
            System.out.println("O processo da fila 2 não foi concluído");
            System.out.println("Restam " + Math.abs(aux) + " UCP\n");
        }
        fila2.remove(0); //Remove o processo da fila 2
    }
    
    /**
     * Execução de um processo da fila 3
     * @throws InterruptedException 
     */
    public static void processoFila3() throws InterruptedException {
        int aux = 8 - fila3.get(0).getUCP(); //Subtrai o quantum pelo UCP do processo
        
        //Exibe qual processo está sendo executado
        System.out.println("\nExecutando processo " + fila3.get(0).getId() + " na fila 3...");
        new Thread().sleep(8000); //Aguarda o tempo do quantum
        
        if(aux >= 0) { //Verifica se o processo terminou
            System.out.println("Processo da fila 3 concluído\n");
        } else {
            fila3.get(0).setUCP(Math.abs(aux)); //Redefine o UCP do processo com o que restou da subtração
            fila3.add(fila3.get(0)); //Adiciona o processo no final da própria fila 3
            System.out.println("O processo da fila 3 não foi concluído");
            System.out.println("Restam " + Math.abs(aux) + " UCP\n");
        }
        fila3.remove(0); //Remove o processo do começo da fila 3
    }
}
