package com.joaomauriciodev.observability_grafana.observability;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;

public class ObservabilityPrometheus {
    static final Counter totalRequests = Counter.build()
            .name("app_total_requests")
            .help("Total de requisições recebidas")
            .register();

    static final Gauge totalUsersOnline = Gauge.build()
            .name("app_total_users_online")
            .help("Total de usuários online")
            .register();

    static final Histogram responseTime = Histogram.build()
            .name("app_response_time")
            .help("Tempo de resposta em segundos")
            .buckets(0.1, 0.5, 1.0, 2.0, 5.0)
            .register();
}
