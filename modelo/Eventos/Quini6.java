package modelo.Eventos;

import java.util.Hashtable;

import modelo.Jugador;

public class Quini6 extends Evento {
    private Hashtable<String,Integer> historialDePaso;
    private static long premiosPorCaida[] = { 50000, 30000 };
    private static String nombre = "Quini6";

    public Quini6(){
        super();
        historialDePaso = new Hashtable<String, Integer>();
    }

    public void darPremioPorCaida(int numCaida, Jugador jugador){
        if (numCaida == 0 || numCaida == 1){
            jugador.recibirDinero( premiosPorCaida[numCaida]);
        }
    }

    public void aplicarEfecto(Jugador jugador) {
        int numCaida;
        if (! historialDePaso.containsKey(jugador.nombre()) ){
            historialDePaso.put(jugador.nombre(), 0);
            numCaida = 0;
        } else {
            Integer valor = historialDePaso.get(jugador.nombre());
            numCaida = (int) valor;
        }
        darPremioPorCaida(numCaida,jugador);
        historialDePaso.put(jugador.nombre(),numCaida+1);
    }

    @Override
    public String getNombre() {
        return nombre;
    }


}
