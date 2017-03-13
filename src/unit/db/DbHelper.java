package unit.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

public class DbHelper {

    public Connection conn = null;
    public ResultSet rs = null;
    private String DatabaseDriver = "oracle.jdbc.driver.OracleDriver";
    private String strcon = "dbc:oracle:thin:@11.0.160.84:1521:esbdb";
    private String UserName = "esb";
    private String PassWord = "esb";

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getStrcon() {
        return strcon;
    }

    public void setStrcon(String strcon) {
        this.strcon = strcon;
    }

    public void setDatabaseDriver(String Driver) {
        this.DatabaseDriver = Driver;
    }

    public String getDatabaseDriver() {
        return this.DatabaseDriver;
    }

    
    //------------------------------------------------------------
    public DbHelper() {
        try {
            Class.forName(DatabaseDriver);
        } catch (ClassNotFoundException e) {
            System.err.println("加载驱动器出现问题：" + e.getMessage());
            System.out.println("加载驱动器出现问题：" + e.getMessage());
        }
    }

    public DbHelper(String connStr) {
        try {
            Class.forName(DatabaseDriver);
            strcon = connStr;
        } catch (ClassNotFoundException e) {
            System.err.println("加载驱动器出现问题：" + e.getMessage());
            System.out.println("加载驱动器出现问题：" + e.getMessage());
        }
    }

    public DbHelper(String DbDriver, String connStr) {
        try {
            strcon = connStr;
            DatabaseDriver = DbDriver;
            Class.forName(DatabaseDriver);
        } catch (ClassNotFoundException e) {
            System.err.println("加载驱动器出现问题：" + e.getMessage());
            System.out.println("加载驱动器出现问题：" + e.getMessage());
        }
    }

    public DbHelper(String DbDriver, String connStr, String userNmae, String passWord) throws Exception {
        try {
            strcon = connStr;
            DatabaseDriver = DbDriver;
            UserName = userNmae;
            PassWord = passWord;
            Class.forName(DatabaseDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception("加载驱动器出现问题：" + e.getMessage());
        }
    }

    /**
     * 测试连接是否成功
     * @return
     */
    public Boolean TestDataSource() {
        Boolean bool = true;
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            if (conn == null) {
                bool = false;
            }
        } catch (SQLException ex) {
            bool = false;
        }
        CloseDatabase();
        return bool;
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行插入
     * @param sql
     * @return
     */
    public int executeInsert(String sql) {
        int num = 0;
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("执行插入出现问题：" + ex.getMessage());
            System.out.println("执行插入出现问题：" + ex.getMessage());
        }
        CloseDatabase();
        return num;
    }

    /**
     * 执行查询
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql) {
        rs = null;
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("执行查询出现问题：" + ex.getMessage());
            System.out.println("执行查询出现问题：" + ex.getMessage());
        }
        return rs;
    }

    /**
     * 执行查询得到记录数
     * @param sql
     * @return
     */
    public int executeQueryCount(String sql) {
        rs = null;
        int num = 0;
        try{
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            num = Integer.parseInt(rs.getString(1));
            rs.close();
            stmt.close();
        }catch (SQLException ex){
            System.err.println("执行查询出现问题：" + ex.getMessage());
            System.out.println("执行查询出现问题：" + ex.getMessage());
        }
        CloseDatabase();
        return num;
    }

    /**
     * 执行删除
     * @param sql
     * @return
     */
    public int executeDelete(String sql) {
        int num = 0;
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("执行删除出现问题：" + ex.getMessage());
            System.out.println("执行删除出现问题：" + ex.getMessage());
        }
        CloseDatabase();
        return num;
    }

    /**
     * 执行存储过称获得对应表数据
     * @param c_cxzd
     * @param c_dxmc
     * @param c_gltj
     * @param c_page
     * @param c_xsts
     * @return
     */
    public List<Object> executeProcedure(String c_cxzd, String c_dxmc, String c_gltj, int c_page, int c_xsts, int p_count) {
        ResultSet resultSet = null;
        List<Object> list = new ArrayList<Object>();
        try {
            conn = DriverManager.getConnection(strcon, UserName, PassWord);
            CallableStatement proc = conn.prepareCall("{ call  rx_qzxt_package.usp_rx_pagination(?,?,?,?,?,?,?)}");
            proc.setString(1, c_cxzd);//字段
            proc.setString(2, c_dxmc);//表名
            proc.setString(3, c_gltj);//过滤条件
            proc.setFloat(4, c_page);//当前页
            proc.setFloat(5, c_xsts);//每页条数
            proc.setFloat(6, p_count);//总数
            proc.registerOutParameter(6, oracle.jdbc.OracleTypes.FLOAT);
            proc.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            proc.execute();
            resultSet = ((OracleCallableStatement) proc).getCursor(7);
            p_count = proc.getInt(6);
            list.add(resultSet);
            list.add(p_count);
        } catch (SQLException ex) {
            System.err.println("执行删除出现问题：" + ex.getMessage());
            System.out.println("执行删除出现问题：" + ex.getMessage());
        }
        // CloseDatabase();
        return list;
    }

    /**
     * 关闭数据库
     */
    public void CloseDatabase() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception end) {
            System.err.println("执行关闭数据库出现问题：" + end.getMessage());
            System.out.println("执行关闭数据库出现问题：" + end.getMessage());
        }
    }
}