package view;

import controller.ThreadPrato;

import java.util.concurrent.Semaphore;

public class Cozinha {
    public static void main (String[] args){
        int permissoes = 1;
        Semaphore semaforo = new Semaphore(permissoes);
        for (int threadId = 1; threadId < 6; threadId++){
            Thread tPrato = new ThreadPrato(threadId, semaforo);
            tPrato.start();
        }
    }
}
