package com.dh.Backend1_TrabajoIntegrador.dao.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.BD;
import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.modelo.Domicilio;
import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteImpl implements IDao<Paciente> {
    private static final Logger LOGGER = Logger.getLogger(PacienteImpl.class);
    DomicilioImpl domicilioImpl = new DomicilioImpl();

    @Override
    public Paciente consultarPorId(Integer id) {
        Connection connection = null;

        Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM PACIENTES WHERE ID=?"
            );

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Domicilio domicilio = domicilioImpl.consultarPorId(rs.getInt(4));

                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        domicilio,
                        rs.getString(4),
                        rs.getDate(5).toLocalDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public List<Paciente> consultarTodos() {
        Connection connection = null;

        List<Paciente> pacienteList = new ArrayList<>();
        Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM PACIENTES"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Domicilio domicilio = domicilioImpl.consultarPorId(rs.getInt(6));

                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        domicilio,
                        rs.getString(4),
                        rs.getDate(5).toLocalDate());

                pacienteList.add(paciente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return pacienteList;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            domicilioImpl.guardar(paciente.getDomicilio());

            LOGGER.info("Estamos guardando un paciente");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PACIENTES (" +
                            "NOMBRE, APELLIDO, DNI, FECHA_ALTA, DOMICILIO_ID) VALUES " +
                            "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaAlta()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                paciente.setId(rs.getInt(1));
                System.out.println("Se guardó el paciente con nombre " +
                        paciente.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info("Guardamos el paciente con nombre " + paciente.getNombre());
        return paciente;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DOMICILIO=?," +
                            "DNI=?, FECHA_ALTA=? WHERE ID=?"
            );

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDomicilio().getId());
            preparedStatement.setString(4, paciente.getDni());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaAlta()));
            preparedStatement.setInt(6, paciente.getId());

            preparedStatement.execute();

            System.out.println("Este es el nuevo nombre del paciente" + paciente.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Eliminamos Paciente con id: " + id);
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM PACIENTES WHERE ID = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Paciente eliminado con id: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

