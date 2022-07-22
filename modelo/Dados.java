package modelo;
/*
 * Da el valor y si vulelve a tirar
 */

public class Dados {

	public int contador;
	private boolean vuelveAJugar;
	private int ultimoTiro;
	public int primerDado;
	public int segundoDado;
	private static int MENOR_VALOR = 1;
	private static int MAYOR_VALOR = 6;

	public void inicializarDados() {
		this.contador = 0;
		this.vuelveAJugar = true;
		this.ultimoTiro = 0;
	}


	public boolean compararDados(int dado1, int dado2){
		if (dado1== dado2 && this.contador < 1){
			this.contador++;
			return true;
		}
		return false;
	}

	public int arrojarDado(){
		return MENOR_VALOR + (int)(Math.random() * MAYOR_VALOR);
	}

	public int tirarDados() {
		this.primerDado = this.arrojarDado();
		this.segundoDado = this.arrojarDado();
		this.vuelveAJugar = compararDados(this.primerDado, this.segundoDado);
		this.ultimoTiro = this.primerDado + this.segundoDado;
		return this.ultimoTiro;
	}

	public boolean jugadorVuelveATirar(){
		return this.vuelveAJugar;
	}

	//Metodo solo para testing
	public void tiraHastaQueSeanIguales(){
		this.tirarDados();
		while( this.primerDado != this.segundoDado ){
			this.tirarDados();
		}
	}

	public int valorDelUltimoTiro() {
		return this.ultimoTiro;
	}
}
