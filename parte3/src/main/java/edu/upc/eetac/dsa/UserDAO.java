package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by OriolGresa on 23/11/16.
 */
public class UserDAO {
    static final Logger logger = Logger.getLogger(UserDAO.class);


    public static Connection getConnection() {
        Connection con = null;
        try {
            String host = "localhost";
            int port = 3306;
            String database = "examen";
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            info.setProperty("useSSL", "false");
            info.setProperty("serverTimezone", "UTC");
            con = DriverManager.getConnection(url, info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public String getValors(Field f) {
        String res = null;
        try {
            Method m = this.getClass().getMethod(getUpper(f.getName()), null);
            res = m.invoke(this, null).toString();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        return res;
    }

    public void insertarElementos(PreparedStatement preparedStatement) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        int i = 1;
        Field[] fields = this.getClass().getFields();

        for (Field f : fields) {
            String res = getValors(f);
            preparedStatement.setObject(i, res);
            i++;
        }
    }

    public String getUpper(String m) {
        String result = Character.toUpperCase(m.charAt(0)) + m.substring(1);
        return "get".concat(result);
    }

    public void insertUser() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO User (name,password) VALUES (?,?);");
        logger.info("QUERY:" + sb.toString() + "\n");

        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertarElementos(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectUser(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM User WHERE name = '").append(name).append("'");
        logger.info("QUERY: " + sb.toString());

        Connection con = getConnection();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();
            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) { //lo ejecuto el numero de veces de columnas que tenga en la tabla
                try {
                    //if (rsmd.getColumnTypeName(i).equals("INT")) {//para la columna i,si es del tipo int
                    //  System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getInt(i)); //obtengo la etiqueta de la columna y el entero (id=1...)
                    //}
                    if (rsmd.getColumnTypeName(i).equals("VARCHAR")) { //si es del tipovarchar, obtengo lo que es tambien
                        System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getString(i));
                    }
                    if (i == rsmd.getColumnCount()) { //cuando i=numero de columnas, voy al siguiente y salgo del bucle,reiniciando i
                        rs.next();
                        i = 0;
                    }
                } catch (Exception e) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        StringBuffer sb = new StringBuffer();
        Field[] fields = this.getClass().getDeclaredFields();
        String p=(getValors(fields[0]));
        sb.append("UPDATE User SET name=?,password=? WHERE name='" + p + "'");

        logger.info("QUERY: " + sb.toString());

        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertarElementos(preparedStatement);
            preparedStatement.execute();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        StringBuffer sb = new StringBuffer();
        Field[] fields = this.getClass().getDeclaredFields();
        String p =(getValors(fields[0]));
        sb.append("DELETE FROM User WHERE name='" + p +"'");
        System.out.println("QUERY: " + sb.toString());
        Connection con = getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<User> getAllUsers() throws SQLException {
        Connection con = getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = stmt.executeQuery("SELECT * FROM User ORDER BY name");
        List<User> lista = new ArrayList<User>();
        while (rs.next()) {
            User user = new User(rs.getString("name"), rs.getString("password"));
            lista.add(user);
        }
        //System.out.println(lista);
            logger.info("lista de usuarios ordenados alfabeticamente:");
        for (User u : lista) {
            logger.info("name: " + u.getName() +", password: " + u.getPassword());
        }
        return lista;
    }
}

