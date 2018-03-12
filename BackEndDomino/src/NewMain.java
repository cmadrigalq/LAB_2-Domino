import AccesoDatos.GlobalException;
import AccesoDatos.JugadorService;
import AccesoDatos.NoDataException;
import Modelo.Jugador;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
        JugadorService js = new JugadorService();
        Jugador j = new Jugador("Juan de Dios",10);
        js.insertarJugador(j);
    }
    
}
