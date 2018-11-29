package Comunicacion;

import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Notifications.CambioSalaNotification;
import Comunicacion.Notifications.NuevoUsuarioNotification;
import Comunicacion.Notifications.UsuarioSalioSalaNotification;
import Comunicacion.Responses.CrearSalaResponse;
import Comunicacion.Responses.GetProfileResponse;
import Comunicacion.Responses.GetSalasResponse;
import Comunicacion.Responses.LoginResponse;
import Comunicacion.Responses.RegisterResponse;
import Comunicacion.Responses.UnirseSalaResponse;

public class ManejadorDeRespuestas {
	public interface EscuchadorRegister {
		public void notificarRegisterResponse(RegisterResponse registerResponse);
	}
	private EscuchadorRegister escuchadorRegister;
	
	public interface EscuchadorLogin {
		public void notificarLoginResponse(LoginResponse loginResponse);
	}
	private EscuchadorLogin escuchadorLogin;
	
	public interface EscuchadorSalas {
		public void notificarSalasResponse(GetSalasResponse salaResponse);
	}
	private EscuchadorSalas escuchadorSalas;
	
	public interface EscuchadorCrearSala {
		public void notificarCrearSalaResponse(CrearSalaResponse crearSalaResponse);
	}
	private EscuchadorCrearSala escuchadorCrearSala;
	
	public interface EscuchadorEntrarSala {
		public void notificarUnirseSalaResponse(UnirseSalaResponse unirseSalaResponse);
	}
	private EscuchadorEntrarSala escuchadorEntrarSala;
	
	public interface EscuchadorUsuario {
		public void notificarUsuarioResponse(GetProfileResponse profileResponse);
	}
	private EscuchadorUsuario escuchadorUsuario;
	
	public interface EscuchadorCambioSala {
		public void notificarCambioSala(CambioSalaNotification cambioSalaNotification);
	}
	private EscuchadorCambioSala escuchadorCambioSala;
	
	public interface EscuchadorJuegoComenzo {
		public void notificarJuegoComenzo(JuegoIniciadoNotification juegoIniciadoNotification);
	}
	private EscuchadorJuegoComenzo escuchadorJuegoComenzo;
	
	public interface EscuchadorEstadoPartida {
		public void notificarEstadoPartida(EstadoPartidaNotification estadoPartidaNotification);
	}
	private EscuchadorEstadoPartida escuchadorEstadoPartida;
	
	public interface EscuchadorPartidaFinalizada {
		public void notificarPartidaFinalizada(JuegoFinalizadoNotification juegoFinalizadoNotification);
	}
	private EscuchadorPartidaFinalizada escuchadorPartidaFinalizada;
	
	// Constructor privado porque es un Singleton.
	private ManejadorDeRespuestas() { }
	
	public void manejarMensaje(Enviable mensaje) {
		switch (mensaje.getTipoMensaje()) {
			case LOGIN_RESPONSE:
				if (escuchadorLogin != null)
					escuchadorLogin.notificarLoginResponse((LoginResponse) mensaje);
				break;
			case REGISTER_RESPONSE:
				if (escuchadorRegister != null)
					escuchadorRegister.notificarRegisterResponse((RegisterResponse) mensaje);
				break;
			case GET_USUARIO_RESPONSE:
				if (escuchadorUsuario != null)
					escuchadorUsuario.notificarUsuarioResponse((GetProfileResponse) mensaje);
				break;
			case GET_SALAS_RESPONSE:
				if (escuchadorSalas != null)
					escuchadorSalas.notificarSalasResponse((GetSalasResponse) mensaje);
				break;
			case CREATE_SALA_RESPONSE:
				if (escuchadorCrearSala != null)
					escuchadorCrearSala.notificarCrearSalaResponse((CrearSalaResponse) mensaje);
				break;
			case JOIN_SALA_RESPONSE:
				if (escuchadorEntrarSala != null)
					escuchadorEntrarSala.notificarUnirseSalaResponse((UnirseSalaResponse) mensaje);
				break;
			case NOTIFICACION_CAMBIO_SALA:
				if (escuchadorCambioSala != null)
					escuchadorCambioSala.notificarCambioSala((CambioSalaNotification) mensaje);
				break;
			case GAME_STARTED:
				if (escuchadorJuegoComenzo != null)
					escuchadorJuegoComenzo.notificarJuegoComenzo((JuegoIniciadoNotification) mensaje);
				break;
			case MENSAJE_PARTIDA:
				if (escuchadorEstadoPartida != null)
					escuchadorEstadoPartida.notificarEstadoPartida((EstadoPartidaNotification) mensaje);
				break;
			case GAME_FINISHED:
				if (escuchadorPartidaFinalizada != null)
					escuchadorPartidaFinalizada.notificarPartidaFinalizada((JuegoFinalizadoNotification) mensaje);
				break;
			default:
				throw new RuntimeException("Tipo de mensaje desconocido.");
		}
	}
	
	private static ManejadorDeRespuestas instancia;
	
	public static ManejadorDeRespuestas getInstancia() {
		if (instancia == null)
			instancia = new ManejadorDeRespuestas();
		return instancia;
	}

	public void setEscuchadorRegister(EscuchadorRegister escuchadorRegister) {
		this.escuchadorRegister = escuchadorRegister;
	}

	public void setEscuchadorLogin(EscuchadorLogin escuchadorLogin) {
		this.escuchadorLogin = escuchadorLogin;
	}

	public void setEscuchadorSalas(EscuchadorSalas escuchadorSalas) {
		this.escuchadorSalas = escuchadorSalas;
	}

	public void setEscuchadorCrearSala(EscuchadorCrearSala escuchadorCrearSala) {
		this.escuchadorCrearSala = escuchadorCrearSala;
	}

	public void setEscuchadorEntrarSala(EscuchadorEntrarSala escuchadorEntrarSala) {
		this.escuchadorEntrarSala = escuchadorEntrarSala;
	}

	public void setEscuchadorUsuario(EscuchadorUsuario escuchadorUsuario) {
		this.escuchadorUsuario = escuchadorUsuario;
	}

	public void setEscuchadorCambioSala(EscuchadorCambioSala escuchadorCambioSala) {
		this.escuchadorCambioSala = escuchadorCambioSala;
	}

	public void setEscuchadorJuegoComenzo(EscuchadorJuegoComenzo escuchadorJuegoComenzo) {
		this.escuchadorJuegoComenzo = escuchadorJuegoComenzo;
	}

	public void setEscuchadorEstadoPartida(EscuchadorEstadoPartida escuchadorEstadoPartida) {
		this.escuchadorEstadoPartida = escuchadorEstadoPartida;
	}

	public void setEscuchadorPartidaFinalizada(EscuchadorPartidaFinalizada escuchadorPartidaFinalizada) {
		this.escuchadorPartidaFinalizada = escuchadorPartidaFinalizada;
	}

	public static void setInstancia(ManejadorDeRespuestas instancia) {
		ManejadorDeRespuestas.instancia = instancia;
	}
	
}
