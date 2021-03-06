package AccesoDatos;

import Modelo.Ficha;
import Modelo.Ficha.Punto;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import oracle.jdbc.internal.OracleTypes;

public class FichaService extends Servicio {

    private static final String insertaFicha = "{call insertar_ficha(?,?,?,?)}";
    private static final String listarFichas = "{?=call listar_ficha()}";

    public FichaService() {
    }

    public List<Ficha> listarFicha() throws GlobalException, NoDataException {
        conectar();
        ResultSet rs = null;
        List<Ficha> coleccion = new ArrayList();
        Ficha ficha = null;
        CallableStatement pstmt = null;
        boolean imp = false;
        try {
            pstmt = conexion.prepareCall(listarFichas);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                if (rs.getString("derecha").equals("S")) {
                    imp = true;
                }
                int id = rs.getInt("id_ficha");
                ficha = new Ficha(id,
                        Punto.values()[rs.getInt("valor1")], Punto.values()[rs.getInt("valor2")],
                        imp);
                coleccion.add(ficha);
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

    public void insertarFicha(Ficha ficha) throws GlobalException, NoDataException {
        conectar();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertaFicha);
            pstmt.setInt(1, ficha.getId_ficha());
            pstmt.setInt(2, ficha.getValor1().getIntValue());
            pstmt.setInt(3, ficha.getValor2().getIntValue());
            if (ficha.fueVolteada()) {
                pstmt.setString(4, "S");
            } else {
                pstmt.setString(4, "N");
            }
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
