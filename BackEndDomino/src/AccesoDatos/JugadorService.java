package AccesoDatos;

import Modelo.Ficha;
import Modelo.Ficha.Punto;
import Modelo.Jugador;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import oracle.jdbc.internal.OracleTypes;

public class JugadorService extends Servicio {

    private static final String insertaJugador = "{call insertar_jugador(?,?)}";
    private static final String listarJugador = "{?=call listar_jugador()}";

    public JugadorService() {
    }

    public List<Jugador> listarJugador() throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        List<Jugador> coleccion = new ArrayList();
        Jugador jugador = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(listarJugador);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                jugador = new Jugador(rs.getString("id_jugador"),rs.getInt("partidasGanadas"));
                coleccion.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no válida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public void insertarJugador(Jugador jugador) throws GlobalException, NoDataException {
        conectar();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertaJugador);
            pstmt.setString(1, jugador.getNombre());
            pstmt.setInt(2, jugador.getPartidasGanadas());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

}
