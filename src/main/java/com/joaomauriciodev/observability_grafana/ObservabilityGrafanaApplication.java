package com.joaomauriciodev.observability_grafana;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ObservabilityGrafanaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ObservabilityGrafanaApplication.class, args);

	}
}
