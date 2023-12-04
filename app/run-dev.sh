#!/bin/bash
export BEARER_CODE=1234567
export JDBC_URL="jdbc:sqlite:${PWD}/test.db"
export JDBC_USERNAME=""
export JDBC_PASSWORD=""
export SQL_QUERY="select phone from data where username = ?"
export TEST_DB="test.db"

create_test_db() {
    rm -f -- ${TEST_DB}
    sqlite3 ${TEST_DB} << EOF
create table data(username varchar, phone varchar);
insert into data values ('user1', '111-111-user1');
insert into data values ('user2', '222-222-user2');
insert into data values ('user3', '333-333-user3');
.exit
EOF

}

create_test_db
./gradlew --console=plain quarkusDev -Dquarkus.http.host=0.0.0.0
# -DdebugHost=0.0.0.0 dont work
