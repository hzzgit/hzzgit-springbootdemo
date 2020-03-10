package com.hzz.springbootdao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import net.fxft.common.jdbc.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class SpringConnectionhzzSource implements ConnectionhzzSource {
    private static final Logger log = LoggerFactory.getLogger(net.fxft.cloud.jdbc.SpringConnectionSource.class);
    private DataSource dataSource;

    public SpringConnectionhzzSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(this.dataSource);
    }

    public void setAutoCommit(Connection conn, boolean autoCommit) {
    }

    public void rollback(Connection conn) {
    }

    public void commit(Connection conn) {
    }

    public void close(Connection conn) {
        DataSourceUtils.releaseConnection(conn, this.dataSource);
    }

    public void close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var3) {
            log.error("stmt.close出错！", var3);
        }

    }

    public void close(Statement stmt, Connection conn) {
        this.close(stmt);
        this.close(conn);
    }
}

