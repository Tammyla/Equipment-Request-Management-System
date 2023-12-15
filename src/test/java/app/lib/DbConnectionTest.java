package app.lib;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    @Test
    void getConnectionTest(){
        Connection dbConnection = DbConnection.connect();
        assertNotNull(dbConnection, "Connection should be successful.");
    }

}