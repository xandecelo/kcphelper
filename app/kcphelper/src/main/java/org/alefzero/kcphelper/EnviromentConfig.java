package org.alefzero.kcphelper;

public enum EnviromentConfig {
    BEARER_CODE, SQL_QUERY, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD;

    public static String getenv(EnviromentConfig env) {
        return System.getenv(env.toString());
    }
}
