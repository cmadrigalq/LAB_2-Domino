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
    private static final String insertaFicha = "{call insertar_ficha(?,?,?)}";
    private static final String listarFichas  = "{?=call listar_ficha()}";
    
    public FichaService() {
    }

   /* public List<Ficha> listarFicha() throws GlobalException, NoDataException {
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
                Punto p = new Punto(rs.getInt("valor1"),rs.getInt("valor2"));
                if (rs.getString("importado").equals("S")) {
                    imp = true;
                }

                producto = new Producto(rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        imp,
                        tipoProducto);

                coleccion.add(producto);
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

    public void insertarProducto(Producto producto) throws GlobalException, NoDataException {
        conectar();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertarProducto);
            pstmt.setString(1, producto.getCodigo());
            pstmt.setString(2, producto.getNombre());
            pstmt.setInt(3, producto.getPrecio());
            if (producto.isImportado()) {
                pstmt.setInt(4, 1);
            } else {
                pstmt.setInt(4, 0);
            }
            pstmt.setInt(5, producto.getTipo().getCodigo());
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
    }*/

}
