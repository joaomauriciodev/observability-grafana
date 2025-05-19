package com.joaomauriciodev.observability_grafana;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

public class AppMetrics {
    private static final AppMetrics INSTANCE = new AppMetrics();

    private final Counter requisicoesTotais;
    private final Counter requisicoesComErro;

    private final Gauge usuariosAtivos;

    private final Histogram tempoResposta;

    private final Summary tamanhoRequisicao;

    private AppMetrics(){
        requisicoesTotais = Counter.build()
                .name("app_requisicoes_totais")
                .help("Total de requisições recebidas")
                .labelNames("endpoint", "metodo")
                .register();

        requisicoesComErro = Counter.build()
                .name("app_requisicoes_erro")
                .help("Total de requisições com erro")
                .labelNames("endpoint", "codigo_erro")
                .register();

        usuariosAtivos = Gauge.build()
                .name("app_usuarios_ativos")
                .help("Número de usuários ativos no sistema")
                .labelNames("tipo_usuario")
                .register();

        tempoResposta = Histogram.build()
                .name("app_tempo_resposta_segundos")
                .help("Tempo de resposta em segundos")
                .labelNames("endpoint")
                .buckets(0.01, 0.05, 0.1, 0.5, 1.0, 2.0, 5.0, 10.0)
                .register();

        tamanhoRequisicao = Summary.build()
                .name("app_tamanho_requisicao_bytes")
                .help("Tamanho das requisições em bytes")
                .labelNames("endpoint")
                .quantile(0.5, 0.05)   // 50º percentil com erro de 5%
                .quantile(0.9, 0.01)   // 90º percentil com erro de 1%
                .register();
    }

    public static AppMetrics getInstance(){
        return INSTANCE;
    }

    public void incrementarRequisicao(String endpoint, String metodo){
        requisicoesTotais.labels(endpoint, metodo).inc();
    }

    public void incrementarErro(String endpoint, String codigoErro){
        requisicoesComErro.labels(endpoint, codigoErro).inc();
    }

    public void incrementarUsuariosAtivos(String tipoUsuario){
        usuariosAtivos.labels(tipoUsuario).inc();
    }

    public void decrementarUsuariosAtivos(String tipoUsuario){
        usuariosAtivos.labels(tipoUsuario).dec();
    }
    public void definirUsuariosAtivos(String tipoUsuario, int quantidade) {
        usuariosAtivos.labels(tipoUsuario).set(quantidade);
    }

    public Histogram.Timer iniciarTempoResposta(String endpoint) {
        return tempoResposta.labels(endpoint).startTimer();
    }

    public void observarTamanhoRequisicao(String endpoint, double tamanhoBytes) {
        tamanhoRequisicao.labels(endpoint).observe(tamanhoBytes);
    }
}

