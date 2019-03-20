import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Processo {
    private int tempo;
    private String nome;

    public Processo(String nome, int tempo){
        this.nome = nome;
        this.tempo = tempo;
    }
    public String getNome(){
        return this.nome;
    }
    public int getTempo(){
        return this.tempo;
    }
    public void setTempo(int tempo){
        this.tempo = tempo;
    }

}


class FilaRunning{
    public List<Processo> processos = new LinkedList<Processo>();
    public int executando;

    public void insere(Processo p) {
        this.processos.add(p);
        this.executando ++;
    }
    public void remove(Processo p){
        this.processos.remove(p);
        this.executando --;
    }
    public void listar(){

        for(Processo p : this.processos){
            System.out.print(p.getNome());
            System.out.print("\n");
            System.out.print("Tempo:"+p.getTempo());
            System.out.print("\n");

            System.out.print("\n--\n");

        }
        System.out.print(this.executando+" Processos Em Running\n----------------------------\n");
    }

}

class FilaReady{
    public List<Processo> processos = new LinkedList<Processo>();
    public int executando;

    public void insere(Processo p) {
        this.processos.add(p);
        this.executando ++;
    }
    public void remove(Processo p){
        this.processos.remove(p);
        this.executando --;
    }
    public void listar(){
        System.out.println("-----------------------------");
        for(Processo p : this.processos){
            System.out.print(p.getNome());
            System.out.print("\n");
            System.out.print("Tempo:"+p.getTempo());
            System.out.print("\n");

            System.out.print("\n--\n");

        }
        System.out.print(this.executando+" Processos Em Ready\n----------------------------\n");
    }

}

public class FilaQuaseSimples{ //main

    public static void executar(FilaRunning run, FilaReady ready){
        while(run.executando >0){
            Processo p = run.processos.get(0);
            System.out.println("executando "+p.getNome());
            p.setTempo(p.getTempo()-1);
            if (p.getTempo() > 0){
                ready.insere(p);
            }
            run.remove(p);
        }
        while(ready.executando > 0 && run.executando < 2){
            Processo p = ready.processos.get(0);
            run.insere(p);
            ready.remove(p);
        }
        run.listar();
        ready.listar();
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        FilaRunning Running = new FilaRunning();
        FilaReady ready = new FilaReady();
        while(true){

            // se pressionar a tecla um cria um novo processo
            // se pressionar a tecla 2, executo um tempo
            System.out.println("pressione 1 para criar um novo processo, ou 2 para executar");
            int i = ler.nextInt();
            if (i==1){
                //crio novo processo
                System.out.println("Nome do processo:");
                String nome = ler.next();
                System.out.println("Tempo de execucao");
                int time = ler.nextInt();
                Processo p = new Processo(nome, time);
                ready.insere(p);

            }else{
                //executo um tempo
                executar(Running, ready);

            }


        }


    }


}