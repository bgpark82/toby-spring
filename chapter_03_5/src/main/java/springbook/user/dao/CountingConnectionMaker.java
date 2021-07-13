package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{

    int counter = 0;

    private ConnectionMaker connectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.connectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return connectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
