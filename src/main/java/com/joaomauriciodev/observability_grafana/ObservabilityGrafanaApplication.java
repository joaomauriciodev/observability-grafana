package com.joaomauriciodev.observability_grafana;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.util.Random;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ObservabilityGrafanaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ObservabilityGrafanaApplication.class, args);
		try {

			MetricsConfig metricsConfig = new MetricsConfig();
			metricsConfig.inicializarServidor(8080);

			AppMetrics metrics = AppMetrics.getInstance();

			metrics.definirUsuariosAtivos("administrador", 1);
			metrics.definirUsuariosAtivos("comum", 10);

			// Simula a aplicação em execução
			simulaAplicacao(metrics);

		} catch (IOException e) {
			System.err.println("Erro ao iniciar servidor de métricas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void simulaAplicacao(AppMetrics metrics){
		Random random = new Random();
		String[] endpoints = {"/api/usuarios", "/api/produtos", "/api/pedidos", "/api/pagamentos"};
		String[] metodos = {"GET", "POST", "PUT", "DELETE"};
		String[] tiposUsuario = {"comum", "premium", "administrador"};

		System.out.println("Simulando tráfego de aplicação...");

		while (true){
			try {
				String endpoint = endpoints[random.nextInt(endpoints.length)];
				String metodo = metodos[random.nextInt(metodos.length)];

				metrics.incrementarRequisicao(endpoint, metodo);

				try (var timer = metrics.iniciarTempoResposta(endpoint)){
					Thread.sleep(random.nextInt(500));

					if (random.nextDouble() < 0.1){
						String codigoErro = String.valueOf(400 + random.nextInt(5) * 100);
						metrics.incrementarErro(endpoint, codigoErro);
					}
				}

				metrics.observarTamanhoRequisicao(endpoint, random.nextInt(10000));

				if (random.nextDouble() > 0.7) {
					String tipoUsuario = tiposUsuario[random.nextInt(tiposUsuario.length)];
					if (random.nextBoolean()) {
						metrics.incrementarUsuariosAtivos(tipoUsuario);
					} else {
						metrics.decrementarUsuariosAtivos(tipoUsuario);
					}
				}

				Thread.sleep(100);
			}  catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			break;
		}
		}
	}
}
