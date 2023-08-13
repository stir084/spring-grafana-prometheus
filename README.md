# spring-grafana-prometheus

grafana-prometheus sample for windows 10     
백엔드 서버에서 Actuator로 Metrics를 만들면 프로메테우스가 수집하고 grafana가 시각화 해주는 개념이다.

# 설치

https://grafana.com/grafana/download?platform=windows   
https://prometheus.io/download/   

# Prometheus 설정

promethous.yml 파일에 아래 내용 추가   
```javascript
scrape_configs:
  - job_name: 'spring--app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']
```
# Prometheus 실행

설치 폴더 prometheus-2.46.0.windows-amd64 밑에 prometheus.exe 실행   
localhost:9090으로 접속 가능   

# grafana 실행

powershell 에서 C:\Program Files\GrafanaLabs\grafana\bin> .\grafana-server.exe   
localhost:3000으로 접속 가능   
초기비번은 admin admin   

# 백엔드 설정
```javascript
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'io.micrometer:micrometer-registry-prometheus'
```
```javascript
management.endpoints.web.exposure.include=*
server.tomcat.mbeanregistry.enabled=true
management.endpoint.health.show-details=always

management.prometheus.metrics.export.enabled=true
management.metrics.export.prometheus.path=/prometheus
```
# 3개의 서버가 잘 붙었는지 확인하기 

http://localhost:8080/actuator/prometheus 으로 접속 후 promethe
localhost:9090에 들어가서 status -> target으로 들어가서 Prometheus 서버와 백엔드 서버가 연결되어 있는지 확인한다.   
grafana에서 초기 화면에서 Add you first Datasource 클릭   
Prometheus 클릭 후 url에 http://host.docker.internal:9090/ 입력한다.   
우측 위 상단에 import dashboard로 id가 4701인 대쉬보드를 검색 후 만들어놓은 datasource를 추가하면 완료.   

![image](https://github.com/stir084/spring-grafana-prometheus/assets/47946124/c774b66d-184c-4ee6-8ec8-cdaad917b1c6)

스레드 개수 확인 가능   

![image](https://github.com/stir084/spring-grafana-prometheus/assets/47946124/5582dd19-dad8-4ea4-91ab-97eae1b2d0d7)


