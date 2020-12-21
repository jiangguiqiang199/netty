package com.carrot.pool.conn;

/**
 * @Author: Created by carrot
 * 2020/11/24 11:04
 */
public class ConnectionPoolTest {

    public static void main(String[] args) throws Exception {
        ConnectionPoolConfig connPoolConfig = new ConnectionPoolConfig();
        connPoolConfig.setMinIdle(5);
        connPoolConfig.setMaxIdle(8);
        ConnectionPool connPool = new ConnectionPool(connPoolConfig);
        Connection conn1 = connPool.borrowObject();
        Connection conn2 = connPool.borrowObject();
        Connection conn3 = connPool.borrowObject();
        Connection conn4 = connPool.borrowObject();
        Connection conn5 = connPool.borrowObject();
        conn1.report();
        connPool.returnObject(conn1);
        conn2.report();
        connPool.returnObject(conn2);
        conn3.report();
        connPool.returnObject(conn3);
        conn4.report();
        connPool.returnObject(conn4);
        conn5.report();
        connPool.returnObject(conn5);
        conn5.report();
        // 被归还的对象的引用，不可以再次归还
        // java.lang.IllegalStateException: Object has already been retured to this pool or is invalid
        try {
            connPool.returnObject(conn5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
