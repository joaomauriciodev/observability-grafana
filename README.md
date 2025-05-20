# 📊 Observability com Grafana, Prometheus e Spring Boot

Este projeto demonstra como configurar uma stack de observabilidade utilizando Grafana, Prometheus e uma aplicação Spring Boot. O objetivo é monitorar métricas e logs de uma aplicação Java de forma eficiente.

---

## 📁 Estrutura do Projeto
observability-grafana/
├── monitoring/
│ └── prometheus/
│ └── prometheus.yml
├── src/
│ └── main/
│ └── java/
│ └── ...
├── docker-compose.yml
├── pom.xml
└── README.md

---

## 🚀 Tecnologias Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Micrometer](https://micrometer.io/) (para integração com Prometheus)

---

## 📦 Pré-requisitos

Antes de iniciar, você precisa ter instalado:

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ⚙️ Como Executar

1. **Clone o repositório:**

```bash
git clone https://github.com/joaomauriciodev/observability-grafana.git
cd observability-grafana
```

2. **Compile a aplicação Java:

```bash
./mvnw clean package
```

3. **Suba os serviços com Docker Compose:

```bash
docker-compose up -d
```

4. **AAcesse os dashboards no Grafana:
- URL: http://localhost:3000
- Usuário: admin
- Senha: admin

5. **Configure o Prometheus como fonte de dados:
- URL do Prometheus: http://prometheus:9090




