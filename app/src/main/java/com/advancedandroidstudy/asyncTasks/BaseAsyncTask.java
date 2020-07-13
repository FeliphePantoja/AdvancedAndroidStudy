package com.advancedandroidstudy.asyncTasks;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecutaListener<T> executListener;
    private final FinalizadaListener<T> finishListener;

    public BaseAsyncTask(ExecutaListener<T> executaListener,
                         FinalizadaListener<T> finalizadaListener) {
        this.executListener = executaListener;
        this.finishListener = finalizadaListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return this.executListener.quandoExecuta();
    }

    @Override
    protected void onPostExecute(T retorno) {
        super.onPostExecute(retorno);
        this.finishListener.quandoFinalizada(retorno);
    }

    public interface ExecutaListener<T> {
        T quandoExecuta();
    }

    public interface FinalizadaListener<T> {
        void quandoFinalizada(T resultado);
    }
}
