package com.joaomauriciodev.observability_grafana;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

public class MetricsConfig {
    private HTTPServer server;

    public void inicializarServidor(int porta) throws IOException{
        DefaultExports.initialize();

        server = new HTTPServer(porta);

        System.out.println("Servidor de métricas iniciado na porta " + porta);
    }

    public void encerrarServidor(){
        if (server != null){
            server.close();
        }
    }


}
