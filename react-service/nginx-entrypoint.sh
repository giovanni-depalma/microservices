envsubst < /etc/nginx/conf.d/nginx.template > /etc/nginx/conf.d/default.conf;
#registrazione consul
#curl xxxxxxxxx
exec nginx -g 'daemon off;'
