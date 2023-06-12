package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HelloController {
    private final String usuario = "adminer";
    private final String passwd = "adminer";
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";

    private Connection conexionBBDD;
    @FXML
    private TextField ne;
    @FXML
    private TextField apellido;
    @FXML
    private TextField nombre;
    @FXML
    private TextField extel;
    @FXML
    private TextField email;
    @FXML
    private TextField codof;
    @FXML
    private TextField respo;
    @FXML
    private TextField trabajo;
    @FXML
    private Button alta;
    @FXML
    private Button salida;
    @FXML
    private GridPane general;

public  boolean altaauX=true;
    public  boolean actualizarauX=true;

    public Boolean altaEmpleado() {
        int registrosAfectadosConsulta = 0;
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO employees ("
                    + "  employeeNumber,"
                    + "  lastName,"
                    + "  firstName,"
                    + "  extension,"
                    + "  email,"
                    + "  officeCode,"
                    + "  reportsTo,"
                    + "  jobTitle)"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setInt(1, Integer.parseInt(ne.getText()));
            st.setString(2, apellido.getText() );
            st.setString(3, nombre.getText() );
            st.setString(4, extel.getText());
            st.setString(5, email.getText());
            st.setString(6, codof.getText() );

            st.setInt(7, Integer.parseInt(respo.getText()));
            st.setString(8, trabajo.getText());


            // Ejecutamos la consulta preparada (con las ventajas de seguridad y velocidad en el servidor de BBDD
            // nos devuelve el número de registros afectados. Al ser un Insert nos debe devolver 1 si se ha hecho correctamente
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();
            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }



}