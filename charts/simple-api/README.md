# Simple API

A simple REST API application with Redis database built with Spring boot to learn Docker and Kubernetes

## Learning topics:

- Spring boot Rest API
- Springboot Scheduler
- REST API HTTP status codes
- Microservices architecture
- Microservices communication
- Microservices caching system
- Application logging
- Application scaling
- Application Health check
- Application monitoring
- Grafana
- Prometheus

### Kubernetes

- Kubernetes Pods
- Kubernetes Deployments
- Kubernetes services
- Kubernetes ConfigMap
- Kubernetes Volumes
- Kubernetes Rootless Pods
- Kubernetes Ingress

#### Install Simple API

```shell
helm install simple-api oci://registry-1.docker.io/jkaninda/simple-api --version 0.1.1
```

#### Install Monitoring
- Prometheus
- Grafana

```shell
helm show all oci://registry-1.docker.io/jkaninda/monitoring --version 0.1.0
```

```shell
helm install monitoring oci://registry-1.docker.io/jkaninda/monitoring --version 0.1.0
```