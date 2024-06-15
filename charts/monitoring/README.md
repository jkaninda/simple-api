# Simple Monitoring

- Prometheus
- Grafana

```
├── charts
│   ├── grafana
│   │   ├── charts
│   │   ├── Chart.yaml
│   │   ├── templates
│   │   │   ├── configmap.yaml
│   │   │   ├── deployment.yaml
│   │   │   ├── _helpers.tpl
│   │   │   ├── hpa.yaml
│   │   │   ├── ingress.yaml
│   │   │   ├── NOTES.txt
│   │   │   ├── pvc.yaml
│   │   │   ├── serviceaccount.yaml
│   │   │   ├── service.yaml
│   │   │   └── tests
│   │   │       └── test-connection.yaml
│   │   └── values.yaml
│   └── prometheus
│       ├── charts
│       ├── Chart.yaml
│       ├── templates
│       │   ├── configmap.yaml
│       │   ├── deployment.yaml
│       │   ├── _helpers.tpl
│       │   ├── hpa.yaml
│       │   ├── ingress.yaml
│       │   ├── NOTES.txt
│       │   ├── pvc.yaml
│       │   ├── serviceaccount.yaml
│       │   ├── service.yaml
│       │   └── tests
│       │       └── test-connection.yaml
│       └── values.yaml
├── Chart.yaml
└── values.yaml
```
## Config
### Prometheus

Prometheus Helm Chart comes with a default configuration

```yaml
    config:
      enabled: true
      data:
        prometheus.yml: |
            global:
              scrape_interval: 15s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
              evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.
              # scrape_timeout is set to the global default (10s).
            scrape_configs:
              # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
              - job_name: "simple-api"
                metrics_path: '/actuator/prometheus'
                # scheme defaults to 'http'.
                scrape_interval: 30s # poll very quickly for a more responsive demo
                static_configs:
                  - targets: ["simple-api:8080"]
                    labels:
                      application: 'Simple API'
```

### Grafana
Grafana Helm Chart comes with a datasource configuration

```yaml
    config:
      enabled: true
      data: 
        datasources.yaml: |
          apiVersion: 1
          datasources:
            - name: Prometheus
              type: prometheus
              access: proxy
              url: http://monitoring-prometheus:9090 # prometheus URL
              isDefault: true
```


