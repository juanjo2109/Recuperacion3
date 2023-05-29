package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @javafx.fxml.FXML
    private TableView tvEmpleado;
    private ObservableList<Empleado> datos;
    @javafx.fxml.FXML
    private TableColumn tcnumempcol;
    @javafx.fxml.FXML
    private TableColumn tcextension;
    @javafx.fxml.FXML
    private TableColumn tcemail;
    @javafx.fxml.FXML
    private TableColumn tcofficecode;
    @javafx.fxml.FXML
    private TableColumn tcreport;
    @javafx.fxml.FXML
    private TableColumn tcjobtitle;
    @javafx.fxml.FXML
    private TableColumn tcapellido;
    @javafx.fxml.FXML
    private TableColumn tcnombre;

    public void initialize(){
        cargarDatosTabla();

    }
    private void cargarDatosTabla () {
         datos = obtenerEmpleados();
         tcnumempcol.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("employeeNumber"));
         tcapellido.setCellValueFactory(new PropertyValueFactory<Empleado,String>("lastName"));
         tcnombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("firstName"));
         tcextension.setCellValueFactory(new PropertyValueFactory<Empleado,String>("extension"));
         tcemail.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email"));
         tcofficecode.setCellValueFactory(new PropertyValueFactory<Empleado,String>("officeCode"));
         tcreport.setCellValueFactory(new PropertyValueFactory<Empleado,Integer>("reportsTo"));
         tcjobtitle.setCellValueFactory(new PropertyValueFactory<Empleado,String>("jobTitle"));
         tvEmpleado.setItems(datos);
    }
    public ObservableList obtenerEmpleados() {

        ObservableList datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM employees "
                    + "ORDER By lastName";

            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsulta.add(new Empleado(
                        resultadoConsulta.getInt("employeeNumber"),
                        resultadoConsulta.getString("lastName"),
                        resultadoConsulta.getString("firstName"),
                        resultadoConsulta.getString("extension"),
                        resultadoConsulta.getString("email"),
                        resultadoConsulta.getString("officeCode"),
                        resultadoConsulta.getInt("reportsTo"),
                        resultadoConsulta.getString("jobTitle"))
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

    @javafx.fxml.FXML
    public void altaempleado(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader(EmpleadoVista.class.getResource("altaempleado.fxml"));
    }
}
