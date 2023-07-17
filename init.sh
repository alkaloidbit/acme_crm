#!/bin/bash

mysql -u root -pRvTC6F8D < ./sql/init_db.sql
echo './install_db: Db reinit.'
mysql -u root -pRvTC6F8D < ./sql/chatgpt_insertion_script.sql
echo './fixtures.sql: Db populated with fixtures data.'

