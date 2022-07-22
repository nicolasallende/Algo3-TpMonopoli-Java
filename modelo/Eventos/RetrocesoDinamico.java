package modelo.Eventos;

import java.util.HashMap;

import modelo.Jugador;

public class RetrocesoDinamico extends Evento {
	private HashMap< Integer , MovimientoDinamico> dicAvanceDinamico;
    private static String nombre = "Retroceso Dinamico";
    private int movimiento = 0;

    public RetrocesoDinamico(){
        super();
        dicAvanceDinamico = new HashMap < Integer , MovimientoDinamico>();
    	
        RestarLasPropiedades restarLasPropiedades = new RestarLasPropiedades();
    	for (int i = 2 ; i <= 6 ; i++) {
    		dicAvanceDinamico.put(i, restarLasPropiedades);
    	}
    		
    	MovimientoEnBaseALaPlata dividirPorLaPlata = new MovimientoEnBaseALaPlata ();
    	for (int i = 7 ; i <= 10 ; i++) {
    		dicAvanceDinamico.put(i, dividirPorLaPlata);
    	}
    	
    	RestarDosPasos restarDosPasos = new RestarDosPasos();
    	for (int i = 11 ; i <= 12  ; i++) {
    		dicAvanceDinamico.put(i, restarDosPasos);
    	}
    }

    public int calcularNuevaPosicion(Jugador jugador, int tiro) {
    	return 	dicAvanceDinamico.get(tiro).calcularMovimiento( tiro, jugador);
    }


    @Override
    public void aplicarEfecto(Jugador jugador) {
        int tiro = jugador.ultimoTiroRealizado();
        moverAlJugador(jugador, tiro);
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    public void moverAlJugador(Jugador jugador, int tiro){
        this.movimiento = - calcularNuevaPosicion(jugador, tiro);
        if(this.movimiento < 0){
            jugador.moverse(this.movimiento, jugador);
        }
    }

    public int getMovimiento(){
        return this.movimiento;
    }
}
