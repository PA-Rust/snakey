package Comunicacion;

public class HiloCliente {
	private static HiloCliente instancia;
	
	private static Socket hiloCliente;
	
	private HiloCliente(String ip, int puerto) {
		try {
            hiloEscucha = new HiloEscucha(new Socket(host, puerto), new ControladorClienteFactory());
            hiloEscucha.addListener(this);
            hiloEscucha.start();
        } catch(IOException e) {
            throw new ZombielandException("No se pudo realizar la conexi�n con el servidor: " +
                                          e.getLocalizedMessage());
        }
	}
}
