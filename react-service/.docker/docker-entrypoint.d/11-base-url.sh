#!/bin/sh
if [ "$BASE_URL" != "/" ]; then
    echo "window.ENV_BASE_URL = \"$BASE_URL\";" > /usr/share/nginx/html/config.js
    sed -i "s,<head>,<head><base href=\"$BASE_URL\" />," /usr/share/nginx/html/index.html
else
    echo "window.ENV_BASE_URL = \"/\";" > /usr/share/nginx/html/config.js
fi
