docker-compose up kafka-cluster
#enter bash
docker exec -it kafka-cluster bash
#run without bash
docker exec -it kafka-cluster kafka-topics --create -topic file-stream-connector-1-standalone --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
docker exec -it kafka-cluster sh -c " cd /my-data/file-stream-connector && ls"
docker exec -it kafka-cluster sh -c " cd /my-data/file-stream-connector && connect-standalone worker.properties file-stream-connector.properties"
docker exec -it kafka-cluster kafka-topics --create -topic file-stream-connector-1-distributed --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
docker exec -it kafka-cluster sh -c "kafka-console-consumer --topic file-stream-connector-1-distributed --from-beginning --bootstrap-server 127.0.0.1:9092"
docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' course-catalog-operational-db

kafka-topics --create -topic instructors --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --list --zookeeper 127.0.0.1:2181
kafka-console-consumer --topic instructors --from-beginning --bootstrap-server 127.0.0.1:9092
connect-distributed courses-connector.json

kafka-topics --create --topic legacy-instructors --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic legacy-instructors-offset --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic legacy-instructors-config --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic legacy-instructors-status --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181

kafka-topics --create --topic other-legacy-instructors --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic other-legacy-instructors-offset --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic other-legacy-instructors-status --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181
kafka-topics --create --topic other-legacy-instructors-config --partitions 3 --replication-factor 1 --zookeeper 127.0.0.1:2181

connect-distributed other-legacy-instructors-jdbc-connector.properties
connect-distributed postgres-sink-other-legacy-instructors.properties

curl -i -X PUT -H  "Content-Type:application/json" http://localhost:8083/connectors/legacy-instructors-debezium-postgres-connector/config -d '{
            "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
            "database.hostname": "instructors-legacy-db",
            "database.port": "5432",
            "database.user": "postgres",
            "database.password": "123456",
            "database.dbname": "instructors-db",
            "database.server.name": "dbserver1",
            "database.history.kafka.bootstrap.servers": "broker:29092",
            "database.history.kafka.topic": "dbhistory.demo" ,
            "topic.prefix":"legacy-instructors",
            "table.whitelist": "instructors",
            "decimal.handling.mode": "double",
            "include.schema.changes": "true",
            "transforms": "unwrap,addTopicPrefix",
            "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",
            "transforms.addTopicPrefix.type":"org.apache.kafka.connect.transforms.RegexRouter",
            "transforms.addTopicPrefix.regex":"(.*)",
            "transforms.addTopicPrefix.replacement":"postgres-debezium-$1"
    }'
