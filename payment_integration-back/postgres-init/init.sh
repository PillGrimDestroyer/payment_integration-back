#!/usr/bin/env sh
set -e

#
#  ВНИМАНИЕ!  Этот файл запускается только тогда,
#             когда папка volumes отсутствует при запуске docker-compose up
#

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL

    ALTER ROLE postgres WITH ENCRYPTED PASSWORD '111';

    CREATE USER payment_integration WITH ENCRYPTED PASSWORD '111';
    CREATE DATABASE payment_integration WITH OWNER payment_integration;
    GRANT ALL ON DATABASE payment_integration TO payment_integration;

EOSQL
