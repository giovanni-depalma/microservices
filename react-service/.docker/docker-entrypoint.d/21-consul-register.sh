if [ "$SERVICE_NAME" == "" ]; then
    export SERVICE_NAME=${HOSTNAME}
fi
envsubst < /consul-register.json > /consul-register.resolved.json
curl -X PUT -H "Content-Type: application/json" \
    -d @/consul-register.resolved.json \
    http://${SERVICE_DISCOVERY_HOST}:8500/v1/agent/service/register
