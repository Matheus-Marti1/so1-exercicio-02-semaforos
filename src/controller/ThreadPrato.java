package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {

    private final int threadId;
    private final Semaphore semaforo;

    public ThreadPrato(int threadId, Semaphore semaforo) {
        this.threadId = threadId;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        if (threadId % 2 == 0){
            LasanhaBol();
        } else {
            SopaCeb();
        }
        try {
            semaforo.acquire();
            EntregarPrato();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }

    }

    private void EntregarPrato() {
        if (threadId % 2 == 0){
            System.out.println("Lasanha a Bolonhesa #" + threadId + " está sendo entregue");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Lasanha a Bolonhesa #" + threadId + " entregue.");
        } else {
            System.out.println("Sopa de Cebola #" + threadId + " está sendo entregue");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Sopa de Cebola #" + threadId + " entregue.");
        }
    }

    private void LasanhaBol() {
        double tempoCoz = (Math.random() * 0.6) + 0.6;
        double progresso = 0;
        System.out.println("Prato de Lasanha a Bolonhesa #" + threadId + " iniciou o cozimento e irá levar " + tempoCoz + " s. para concluir.");
        while (progresso < tempoCoz){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            progresso += 0.1;
            if (progresso >= tempoCoz){
                System.out.println("Lasanha a Bolonhesa #" + threadId + " finalizou o cozimento.");
            } else {
                System.out.println("Lasanha a Bolonhesa #" + threadId + " progresso: " + (progresso * 100)/tempoCoz + "%");
            }
        }
    }

    private void SopaCeb() {
        double tempoCoz = (Math.random() * 0.3) + 0.5;
        double progresso = 0;
        System.out.println("Prato de Sopa de Cebola #" + threadId + " iniciou o cozimento e irá levar " + tempoCoz + " s. para concluir.");
        while (progresso < tempoCoz){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            progresso += 0.1;
            if (progresso >= tempoCoz){
                System.out.println("Sopa de Cebola #" + threadId + " finalizou o cozimento.");
            } else {
                System.out.println("Sopa de Cebola #" + threadId + " progresso: " + (progresso * 100)/tempoCoz + "%");
            }
        }
    }
}
