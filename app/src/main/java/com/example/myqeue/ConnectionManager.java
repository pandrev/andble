package com.example.myqeue;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionManager {
    Tasks pendingOperation = null;
    ConcurrentLinkedQueue<Tasks> operationQueue = new ConcurrentLinkedQueue<Tasks>();
    Handler handler = new Handler();
    Context ctx;

    public synchronized void enqueueOperation(Tasks task) {
        operationQueue.add(task);
        if(pendingOperation == null) {
            doNextOperation();
        }
    }

    public void connect() {

        enqueueOperation(new Connect(device, context.getApplicationContext()));
        
    }

    public synchronized void doNextOperation() {
        if (pendingOperation != null) {
            Log.e("ConnectionManager", "an operation is pending! Aborting.");
            return;
        }

        Tasks operation = operationQueue.poll();

       if (operation == null) {
           Log.e("ConnectionManager", "The qeue is emppy!!!");
           return;
       }

        pendingOperation = operation;

        if (operation instanceof TaskOne) {
            Log.d("TASK ONE", "Iniciado o Task 1");
            final Runnable r = new Runnable() {

                @Override
                public void run() {

                    Log.d("TASK ONE", "Taks 1 terminou a tarefa");
                    signalEndOfOperation();
                }

            };
            handler.postDelayed(r, 5000);
        }
        if (operation instanceof TaskTwo) {
            Log.d("TASK TWO", "Iniciado o Task 2");
            final Runnable r = new Runnable() {

                @Override
                public void run() {
                    Log.d("TASK TWO", "Taks 2 terminou a tarefa");
                    signalEndOfOperation();
                }
            };
            handler.postDelayed(r, 5000);

        }
        if (operation instanceof TaskThree) {
            Log.d("TASK THREE", "Iniciado o Task 3");
            final Runnable r = new Runnable() {

                @Override
                public void run() {

                    Log.d("TASK THREE", "Taks 3 terminou a tarefa");
                    signalEndOfOperation();
                }
            };
            handler.postDelayed(r, 5000);

        }
    }

    private synchronized void signalEndOfOperation() {
        //Log.d("ConnectionManager", "End of $pendingOperation");
        pendingOperation = null;
        if (!operationQueue.isEmpty()) {
            doNextOperation();
        }
    }
}
