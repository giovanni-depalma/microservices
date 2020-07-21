FROM node:12-slim as prepare
WORKDIR /workspace
COPY package.json yarn.lock tsconfig.json .npmrc* ./
RUN yarn install

COPY src/ ./src
COPY public/ ./public

#test stage
FROM prepare AS test
RUN CI=true yarn test --passWithNoTests 

FROM prepare AS build
RUN CI=true yarn build

FROM nginx:1.19.1-alpine AS final
COPY .docker/default.conf.template /etc/nginx/templates/default.conf.template
COPY .docker/docker-entrypoint.d/* /docker-entrypoint.d
COPY .docker/consul-register.json /consul-register.json
COPY --from=build /workspace/build /usr/share/nginx/html

# This is a hack around the envsubst nginx config. Because we have `$uri` set
# up, it would replace this as well. Now we just reset it to its original value.
ENV uri \$uri
ENV BASE_URL /
