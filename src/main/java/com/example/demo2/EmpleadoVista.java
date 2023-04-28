package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class EmpleadoVista {
    @javafx.fxml.FXML
    private Button altavent;
    private final String usuario = "adminer";
    private final String passwd = "adminer";
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";

    private Connection conexionBBDD;
    public ObservableList obtenerEmpleados() {

        ObservableList datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM products "
                    + "ORDER By productName";

            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsulta.add(new Empleado(
                        resultadoConsulta.getString("productCode"),
                        resultadoConsulta.getString("productName"),
                        resultadoConsulta.getString("productLine"),
                        resultadoConsulta.getString("productScale"),
                        resultadoConsulta.getString("productVendor"),
                        resultadoConsulta.getString("productDescription"),
                        resultadoConsulta.getInt("quantityInStock"),
                        resultadoConsulta.getDouble("buyPrice"),
                        resultadoConsulta.getDouble("MSRP"))
                );
                System.out.println("Row [1] added " + resultadoConsulta.toString());
            }
            conexionBBDD.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            conexionBBDD.close();
        } finally {
            return datosResultadoConsulta;
        }
    }

}
