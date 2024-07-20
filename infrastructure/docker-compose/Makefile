include .env

.PHONY: clean

run-redis:
	@echo "Running redis with single instance !"
	- docker compose -f network.yml -f redis-infra.yml up
run-redis-slave:
	@echo "Running redis with slave !"
	- docker compose -f network.yml -f redis-slave.yml up
run-postgres:
	@echo "Running postgres !"
	- docker compose -f network.yml -f postgres-infra.yml up
run-mysql:
	@echo "Running MySQL !"
	- docker compose -f network.yml -f mariadb_infra.yml up
run-amq:
	@echo "Running AMQ with 3 node !"
	- docker compose -f network.yml -f amq-infra.yml up
run-kong:
	@echo "Running Kong !"
	- docker compose -f network.yml -f kong_infra.yml up
run-redpanda:
	@echo "Running redpanda !"
	- docker compose -f network.yml -f redpanda/redpanda.yml
	@echo "waiting container up ...... !"
	- sleep 5m
	@echo "Running initiate step create dummy chat-room !"
	- docker exec -it redpanda-0 rpk topic create chat-room
	- sleep 3s
	- exit;
run-cassandra:
	@echo "Running cassandra !"
	- docker compose -f network.yml -f cassandra.yml up
run-cassandra-cluster:
	@echo "Running cassandra cluster !"
	- docker compose -f network.yml -f cassandra-cluster.yml up
stop-all-container:
	@echo "Trying stop all container"
	- docker stop $(docker ps -a -q)
clean:
	@echo "Remove all image and volume !"
	- docker rm -vf $(docker ps -aq)
