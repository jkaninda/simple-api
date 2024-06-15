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


### Docker
- Docker services
- Docker Networks
- Docker volumes
- Docker Container communication
- Docker Container Healthcheck
- Docker Image build
- Docker Rootless Container

### Kubernetes

- Kubernetes Pods
- Kubernetes Deployments
- Kubernetes services
- Kubernetes ConfigMap
- Kubernetes Volumes
- Kubernetes Rootless Pods
- Kubernetes Ingress

## Links:
- [Docker Hub](https://hub.docker.com/r/jkaninda/simple-api)
- [Github Source code ](https://github.com/jkaninda/simple-api)
- []
- [Simple Microservices Github](https://github.com/jkaninda/simple-microservices)

## API Doc

> /    `GET`
> 
> Response:
```json
{
	"success": true,
	"code": 200,
	"message": "OK",
	"error": null,
	"data": "Hello from Simple API, everything is ok"
}
```

> /books   `GET`
> 
> Get all books
```json
{
	"success": true,
	"code": 200,
	"message": "OK",
	"error": null,
	"data": [
      {
        "title": "The Kubernetes Bible",
        "author": "Nassim Kebbani, Piotr Tylenda",
        "country": "USA",
        "imageLink": "images/things-fall-apart.jpg",
        "language": "English",
        "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
        "pages": 652,
        "year": 2022, 
        "createdAt": "2024-04-20 10:34",
        "updatedAt": "2024-04-20 10:34"
  }, 
  {
    "title": "Kubernetes - An Enterprise Guide",
    "author": "Marc Boorshtein, Scott Surovich",
    "country": "USA",
    "imageLink": "images/things-fall-apart.jpg",
    "language": "English",
    "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
    "pages": 652,
    "year": 2022,
    "createdAt": "2024-04-20 10:34",
    "updatedAt": "2024-04-20 10:34"
  },  
  {
    "title": "System Design Interview Vol1",
    "author": "Alex U & Sahn Lam",
    "country": "USA",
    "imageLink": "images/things-fall-apart.jpg",
    "language": "English",
    "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
    "pages": 652,
    "year": 2022,
    "createdAt": "2024-04-20 10:34",
    "updatedAt": "2024-04-20 10:34"
  },  
  {
     "title": "System Design Interview Vol2",
    "author": "Alex U & Sahn Lam",
    "country": "USA",
    "imageLink": "images/things-fall-apart.jpg",
    "language": "English",
    "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
    "pages": 652,
    "year": 2022,
    "createdAt": "2024-04-20 10:34",
    "updatedAt": "2024-04-20 10:34"
  }
	]
}
```

> /books   `POST`
>
> Create a new book
> 

```json
{
      "title": "The Kubernetes Bible",
      "author": "Nassim Kebbani, Piotr Tylenda",
      "country": "USA",
      "imageLink": "images/things-fall-apart.jpg",
      "language": "English",
      "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
      "pages": 652,
      "year": 2022
	
}
```


> /books/{id}   `PUT`
>
> Update aBook
>

```json
{
      "title": "The Kubernetes Bible",
      "author": "Nassim Kebbani, Piotr Tylenda",
      "country": "USA",
      "imageLink": "images/things-fall-apart.jpg",
      "language": "English",
      "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
      "pages": 652,
      "year": 2022
	
}
```

> /books/{id}   `GET`
>
> Get book by Id
> 
```json
{
	"success": true,
	"code": 200,
	"message": "OK",
	"error": null,
	"data": {
      "title": "The Kubernetes Bible",
      "author": "Nassim Kebbani, Piotr Tylenda",
      "country": "USA",
      "imageLink": "images/things-fall-apart.jpg",
      "language": "English",
      "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
      "pages": 652,
      "year": 2022
	}
}
```
> /books/{id}   `DELETE`
>
> Delete book by Id
>

> /deleteAll   `DELETE`
>
> Delete all books
> 
> Restart the application and if Database seed `(RUN_DATA_SEED= true)` is enabled, the predefined data will be restored

> /version `GET`
>
> Get API Version
> 
>
## Run on Docker

Download Docker Desktop (https://www.docker.com/products/)

### Create Network

```shell
docker network create web
```
### Deploy Simple APi

Create `compose.yaml` file and paste the content below

```yaml
version: '3.9'
services:
  simple-api:
    container_name: simple-api
    image: jkaninda/simple-api:latest
    restart: unless-stopped
    ports:
      - "8080:8080"
    networks:
      - web
networks:
  web:
    external: true
    name: web
```

```shell
docker compose up -d
```
### Advanced Docker deployment
Let's apply  some configurations to our simple-api service and deploy the redis database, so that we can interact with the API

simple-api and redis must be connected to the same network, so they can communicate with each other

Create `compose.yaml` file and paste the content below

```yaml
version: '3.9'
services:
  simple-api:
    container_name: simple-api
    image: jkaninda/simple-api:latest
    restart: unless-stopped
    environment:
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
      - SPRING_DATA_REDIS_TIMEOUT=60000
      - SPRING_DATA_REDIS_PASSWORD=password
      ## Insert Redis fake data, requires Redis data source || By default is disabled
      - RUN_DATA_SEED= true
    ports:
      - "8080:8080"
    networks:
      - web
  redis:
    image: redis:alpine
    container_name: redis
    restart: unless-stopped
    command: redis-server --appendonly yes --requirepass password
    expose:
      - 6379
    volumes:
      - ./redis:/data
    networks:
      - web
networks:
  web:
    external: true
    name: web
```
```shell
docker compose up -d
```

### Environment variable from a file

Create `simple-api.env` file and paste the content below
```conf
SPRING_DATA_REDIS_HOST=redis
SPRING_DATA_REDIS_PORT=6379
SPRING_DATA_REDIS_TIMEOUT=60000
SPRING_DATA_REDIS_PASSWORD=password
##Insert Redis fake data, requires Redis data source || By default is disabled
RUN_DATA_SEED=true
```

Let's update our `compose.yaml` file

```yaml
version: '3.9'
services:
  simple-api:
    container_name: simple-api
    image: jkaninda/simple-api:latest
    restart: unless-stopped
    env_file:
      - simple-api.env
    ports:
      - "8080:8080"
    networks:
      - web
  redis:
    image: redis:alpine
    container_name: redis
    restart: unless-stopped
    command: redis-server --appendonly yes --requirepass "${SPRING_DATA_REDIS_PASSWORD}"
    env_file:
      - simple-api.env
    expose:
      - 6379
    volumes:
      - ./redis:/data
    networks:
      - web
networks:
  web:
    external: true
    name: web
```
### Docker container with Health check

```yaml
version: '3.9'
services:
  simple-api:
    container_name: simple-api
    image: jkaninda/simple-api:latest
    restart: unless-stopped
    env_file:
      - simple-api.env
    ports:
      - "8080:8080"
    ## Application Health check
    healthcheck:
      test: wget --no-verbose --tries=1 --spider http://localhost:8080/internal/health/live || exit 1
    networks:
      - web
  redis:
    image: redis:alpine
    container_name: redis
    restart: unless-stopped
    command: redis-server --appendonly yes --requirepass "${SPRING_DATA_REDIS_PASSWORD}"
    env_file:
      - simple-api.env
    expose:
      - 6379
    volumes:
      - ./redis:/data
    networks:
      - web
networks:
  web:
    external: true
    name: web
```
## Build Docker image

`Dockerfile`

```dockerfile
# Buil Stage
FROM maven:3.8.7-amazoncorretto-17 AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/
COPY pom.xml /tmp/pom.xml
COPY src /tmp/src/
RUN mvn clean package -B

FROM amazoncorretto:17-alpine3.19-jdk
ENV VERSION="1.0"
LABEL author="Jonas Kaninda"
LABEL github="https://github.com/jkaninda/simple-api"
ARG JAR_FILE=target/*.jar
# Copy from build stage
COPY --from=MAVEN_TOOL_CHAIN /tmp/$JAR_FILE /App/api.jar
COPY ./data /data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/App/api.jar"]
```

```shell
docker build -f docker/Dockerfile -t jkaninda/simple-api:latest . 
```


### Screenshots

- Docker


<img src="https://raw.githubusercontent.com/jkaninda/simple-api/main/screenshots/docker.png"/>

- Insomnia
- 
<img src="https://raw.githubusercontent.com/jkaninda/simple-api/main/screenshots/insomnia.png"/>

## Interact with the API

To test our API we need an api client, let's download insomnia

Download Insomnia (https://insomnia.rest/) for your platform



## RUN on Kubernetes

### Create a Kubernetes cluster on Docker using Kind

### Install kind (https://kind.sigs.k8s.io/docs/user/quick-start/)

On macOS via Homebrew:
```shell
brew install kind

```
On Windows via Chocolatey (https://chocolatey.org/packages/kind)

```shell
choco install kind

```

### Create cluster

```shell
cat <<EOF | kind create cluster --config=-
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
  kubeadmConfigPatches:
  - |
    kind: InitConfiguration
    nodeRegistration:
      kubeletExtraArgs:
        node-labels: "ingress-ready=true"
  extraPortMappings:
  - containerPort: 80
    hostPort: 80
    protocol: TCP
  - containerPort: 443
    hostPort: 443
    protocol: TCP
EOF
```
### Deploy Simple API

> Create Kubernetes Namespace
> 
```shell
kubectl create namespace simple-api
```

> Deploy Simple APi
> 

```shell
cat <<EOF | kubectl apply -f -
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
  namespace: simple-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-api
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      containers:
      - name: simple-api
        image: jkaninda/simple-api:latest
        resources:
          limits:
            memory: "750Mi"
            cpu: "500m"
        ports:
        - containerPort: 8080
EOF

```
```shell
kubectl  -n simple-api get pods
```
### Create Simple API Service

```shell
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Service
metadata:
  name: simple-api-svc
  namespace: simple-api
spec:
  selector:
    app: simple-api
  ports:
  - port: 8080
    targetPort: 8080
EOF
```

```shell
kubectl -n simple-api get svc
NAME             TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)    AGE
simple-api-svc   ClusterIP   10.96.127.66   <none>        8080/TCP   42s
```

### Deploy All

 

```shell
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Namespace
metadata:
    name: simple-api
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: simple-api
  namespace: simple-api
data:
  SPRING_DATA_REDIS_HOST: 'redis-svc'
  SPRING_DATA_REDIS_PORT: '6379'
  SPRING_DATA_REDIS_PASSWORD: 'password'
  ## Insert Redis fake data, requires Redis data source || By default is disabled
  RUN_DATA_SEED: 'true'
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
  namespace: simple-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-api
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      containers:
      - name: simple-api
        image: jkaninda/simple-api:latest
        envFrom:
         - configMapRef:
                name: simple-api
        resources:
          limits:
            memory: "750Mi"
            cpu: "500m"
        ports:
        - containerPort: 8080
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: simple-api
spec:
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
      - name: redis
        image: redis:alpine
        command:
          - redis-server
          - --appendonly yes 
          - --requirepass "password"
        resources:
          limits:
            memory: "128Mi"
            cpu: "500m"
        ports:
        - containerPort: 6379
        volumeMounts:
         - mountPath: /data
           name: data
      volumes:
        - name: data
          emptyDir: {}
---
apiVersion: v1
kind: Service
metadata:
  name: redis-svc
  namespace: simple-api
spec:
  selector:
    app: redis
  ports:
  - port: 6379
    targetPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: simple-api-svc
  namespace: simple-api
spec:
  selector:
    app: simple-api
  ports:
  - port: 8080
    targetPort: 8080
EOF
```




Or create `deployment.yaml` file and paste the content below

```yaml
apiVersion: v1
kind: Namespace
metadata:
    name: simple-api
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: simple-api
  namespace: simple-api
data:
  SPRING_DATA_REDIS_HOST: 'redis-svc'
  SPRING_DATA_REDIS_PORT: '6379'
  SPRING_DATA_REDIS_PASSWORD: 'password'
  ## Insert Redis fake data, requires Redis data source || By default is disabled
  RUN_DATA_SEED: 'true'
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
  namespace: simple-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-api
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      containers:
      - name: simple-api
        image: jkaninda/simple-api:latest
        envFrom:
         - configMapRef:
                name: simple-api
        resources:
          limits:
            memory: "750Mi"
            cpu: "500m"
        ports:
        - containerPort: 8080
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: simple-api
spec:
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
      - name: redis
        image: redis:alpine
        command:
          - redis-server
          - --appendonly yes 
          - --requirepass "password"
        resources:
          limits:
            memory: "128Mi"
            cpu: "500m"
        ports:
        - containerPort: 6379
        volumeMounts:
         - mountPath: /data
           name: data
      volumes:
        - name: data
          emptyDir: {}
---
apiVersion: v1
kind: Service
metadata:
  name: redis-svc
  namespace: simple-api
spec:
  selector:
    app: redis
  ports:
  - port: 6379
    targetPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: simple-api-svc
  namespace: simple-api
spec:
  selector:
    app: simple-api
  ports:
  - port: 8080
    targetPort: 8080
---
```

### Deploy

```shell
kubectl apply -f deployment.yaml
```

### Get all resources in the simple-api namespace

```shell
kubectl -n simple-api get all
NAME                              READY   STATUS    RESTARTS   AGE
pod/redis-6c8787c7d4-2txqr        1/1     Running   0          37s
pod/simple-api-7fcd9cf54f-rp47w   1/1     Running   0          37s

NAME                     TYPE        CLUSTER-IP    EXTERNAL-IP   PORT(S)    AGE
service/redis-svc        ClusterIP   10.96.251.1   <none>        6379/TCP   37s
service/simple-api-svc   ClusterIP   10.96.79.12   <none>        8080/TCP   37s

NAME                         READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/redis        1/1     1            1           38s
deployment.apps/simple-api   1/1     1            1           38s

NAME                                    DESIRED   CURRENT   READY   AGE
replicaset.apps/redis-6c8787c7d4        1         1         1       37s
replicaset.apps/simple-api-7fcd9cf54f   1         1         1       38s
```

### Get pods

```shell
kubectl -n simple-api get po
NAME                          READY   STATUS    RESTARTS   AGE
redis-5868d6f857-8kdjj        1/1     Running   0          32m
simple-api-7fcd9cf54f-lgwnp   1/1     Running   0          30m
```

### Kubernetes deployment port forward

```shell
kubectl -n simple-api port-forward deployment/simple-api 8080:8080
```

### Log
To see the application's logs

```shell
kubectl -n simple-api  logs simple-api-7fcd9cf54f-lgwnp
```
`simple-api-7fcd9cf54f-lgwnp` is the name of the pod, you cane replace it by the name of your pod

### Scalability

Scalability is the property of a system to handle a growing amount of work.

In our case we want to scale our application as the requests growing

> Scale API
>
```shell
kubectl -n simple-api scale deployment/simple-api --replicas 2
```


#### Ingress

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: simple-api-ingress
  labels:
    name: simple-api
    namespace: simple-api
spec:
  rules:
  ## replace by your hostname
  - host: simple-api.localhost
    http:
      paths:
      - pathType: Prefix
        path: "/"
        backend:
          service:
            name: simple-api-svc
            port: 
              number: 8080
```

### Run as Non-root
To run the pod as non-root user, you can change the image tag by adding `rootless`

```shell
cat <<EOF | kubectl apply -f -
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
  namespace: simple-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-api
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      containers:
      - name: simple-api
        image: jkaninda/simple-api:rootless
        resources:
          limits:
            memory: "750Mi"
            cpu: "500m"
        ports:
        - containerPort: 8080
EOF
```

### Advanced deployment with Health check and security context

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: simple-api
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: simple-api
  namespace: simple-api
data:
  SPRING_DATA_REDIS_HOST: 'redis-svc'
  SPRING_DATA_REDIS_PORT: '6379'
  SPRING_DATA_REDIS_PASSWORD: 'password'
  ## Insert Redis fake data, requires Redis data source || By default is disabled
  RUN_DATA_SEED: 'true'
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: simple-api
  namespace: simple-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: simple-api
  template:
    metadata:
      labels:
        app: simple-api
    spec:
      securityContext:
        runAsUser: 1000
        runAsGroup: 1000
      containers:
        - name: simple-api
          image: jkaninda/simple-api:rootless
          envFrom:
            - configMapRef:
                name: simple-api
          readinessProbe:
            httpGet:
              path: /internal/health/ready
              port: 8080
            initialDelaySeconds: 30
            periodSeconds: 10
            timeoutSeconds: 10
            # HTTP Health Check
          livenessProbe:
              httpGet:
                path: /internal/health/live
                port: 8080
              initialDelaySeconds: 60
              periodSeconds: 30
              timeoutSeconds: 10
          resources:
            limits:
              memory: "750Mi"
              cpu: "500m"
          ports:
            - containerPort: 8080
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: simple-api
spec:
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
        - name: redis
          image: redis:alpine
          command:
            - redis-server
            - --appendonly yes
            - --requirepass password
          resources:
            limits:
              memory: "128Mi"
              cpu: "500m"
          ports:
            - containerPort: 6379
          volumeMounts:
            - mountPath: /data
              name: data
      volumes:
        - name: data
          emptyDir: {}
---
apiVersion: v1
kind: Service
metadata:
  name: redis-svc
  namespace: simple-api
spec:
  selector:
    app: redis
  ports:
    - port: 6379
      targetPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: simple-api-svc
  namespace: simple-api
spec:
  selector:
    app: simple-api
  ports:
    - port: 8080
      targetPort: 8080
---
```
## Install with Monitoring
### Docker

Create `config/datasources.yaml` and copy the content bellow

```yaml
apiVersion: 1
datasources:
  - name: Prometheus
    type: prometheus
    access: proxy
    url: http://prometheus:9090
    isDefault: true
```

Create `config/prometheus.yaml` and copy the content bellow

```yaml
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
Create compose.yaml and copy the content bellow

```yaml
version: '3.9'
services:
  simple-api:
    container_name: simple-api
    image: jkaninda/simple-api:latest
    restart: unless-stopped
    env_file:
      - simple-api.env
    ports:
      - "8080:8080"
    healthcheck:
      test: wget --no-verbose --tries=1 --spider http://localhost:8080/internal/health/live || exit 1
    networks:
      - web
  redis:
    image: redis:alpine
    container_name: redis
    restart: unless-stopped
    command: redis-server --appendonly yes --requirepass "${SPRING_DATA_REDIS_PASSWORD}"
    env_file:
      - simple-api.env
    expose:
      - 6379
    volumes:
      - ./redis:/data
    networks:
      - web
  prometheus:
    image: jkaninda/prometheus:v2.52.0
    container_name: prometheus
    restart: unless-stopped
    expose:
      - 9090
    volumes:
      - ./config/prometheus.yml:/etc/prometheus/prometheus.yml
    networks:
      - web

  grafana:
    image: jkaninda/grafana:11.0.0
    container_name: grafana
    restart: unless-stopped
    expose:
      - 3000:3000
    volumes:
      - ./config/datasources.yaml:/etc/grafana/provisioning/datasources/datasources.yaml
      - ./grafana:/var/lib/grafana
    networks:
      - web
networks:
  web:
    external: true
    name: web
```

Open http://localhost:3000 in your web browser

Grafana's default credentials is username `admin` and password`admin`
Use this ID to import Spring boot grafana dashboard ``11378``

<img src="https://raw.githubusercontent.com/jkaninda/simple-api/main/screenshots/grafana_login.png"/>

<img src="https://raw.githubusercontent.com/jkaninda/simple-api/main/screenshots/metrics.png"/> 

<img src="https://raw.githubusercontent.com/jkaninda/simple-api/main/screenshots/grafana.png"/> 


### Kubernetes


