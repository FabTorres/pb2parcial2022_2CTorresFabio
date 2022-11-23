package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.UsuarioExistenteException;

public class TestBanco {

//	@Test
//	public void queSePuedaCrearUnaCajaDeAhorro() {
//		CajaAhorro cajaAhorro = new CajaAhorro();
//		CuentaCorriente cuentaCorriente = new CuentaCorriente();
//		cuentaCorriente.extraer(45.50);
//	
//	}
	
//	@Test
//	public void queCompileLaPutaDivision() {
//		Cuenta cuenta = new CuentaCorriente();
//		Double numerador = 4.0;
//		Double denominador = 0.0;
////		Double valorObtenido = cuenta.division(numerador, denominador);
////		Double valorEsperado = null;
//		try {
//			assertNull(cuenta.division(cuenta.division(numerador, denominador),2.0));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("Hola");
////		assertEquals(valorEsperado, valorObtenido);
//	}
//	
//	@Test
//	public void queNoSePuedaDividirPorCero() {
//	Cuenta cuenta = new CuentaCorriente();
//	Double numerador = 4.0;
//	Double denominador = 0.0;
//	try {
//		assertNull(cuenta.division(cuenta.division(numerador, denominador),2.0));
//	} catch (Exception e) {
//		System.out.println(e.getMessage());
//	}
//	System.out.println("Segundo test, hello");
//}
	
	@Test(expected = CuentaNoEncontradaException.class)
	public void queAlBuscarUnaCuentaEnUnBancoQueNoExistaLanceUnaExcepcion() throws CuentaNoEncontradaException{
		Cuenta cajaAhorro1 = new CajaAhorro(123, 41823462, "Jorge", "Valdivia");
		Banco nuevoBanco = new Banco();
		nuevoBanco.agregarCuenta(cajaAhorro1);
		nuevoBanco.buscarCuenta(1234);
	}
	
	@Test(expected = MontoInsuficienteException.class)
	public void queNoSePuedaTransferirSiLaCuentaOrigenTieneMontoInsuficiente() throws MontoInsuficienteException {
		Cuenta cajaAhorro1 = new CajaAhorro(123, 41823462, "Jorge", "Valdivia");
		Cuenta cajaAhorro2 = new CajaAhorro(1234, 41823462, "Diegote", "Valdivia");
		Banco nuevoBanco = new Banco();
		nuevoBanco.agregarCuenta(cajaAhorro1);
		nuevoBanco.agregarCuenta(cajaAhorro2);
		cajaAhorro1.depositar(4500.0);
		try {
			nuevoBanco.transferir(123, 1234, 5000.0);
		} catch (CuentaNoEncontradaException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void agregarUsuarioAlBanco() {
		Banco banco = new Banco();
		Titular titular = new Titular("Jorgito", "Messi", 35617280);
		Integer key = 1;
		try {
			banco.agregarUsuario(key, titular);
		} catch (UsuarioExistenteException e) {
			e.getMessage();
		}
		Integer valorEsperado = 1;
		Integer valorObtenido = banco.getUsuarios().size();
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queDevuelvaElUsuarioBuscadoPorKey() {
		Banco banco = new Banco();
		Titular titular = new Titular("Jorgito", "Messi", 35617280);
		Integer key = 1;
		try {
			banco.agregarUsuario(key, titular);
		} catch (UsuarioExistenteException e) {
			System.out.println(e.getMessage());
		}
		Titular valorEsperado = titular;
		Titular valorObtenido = banco.buscarUsuario(1);
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queNoSePuedaAgregarUnaKeyDuplicada() {
		Banco banco = new Banco();
		Integer dni = 35617280;
		Integer dni2 = 43617280;
		Titular titular1 = new Titular("Jorgito", "Messi", dni);
		Titular titular2 = new Titular("Jorgito", "Messirve", dni);
		Titular titular3 = new Titular("Cristiano", "Ronaldo", dni2);
		try {
			banco.agregarUsuario(titular1.getDni(), titular1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			banco.agregarUsuario(titular2.getDni(), titular2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			banco.agregarUsuario(titular3.getDni(), titular3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//<nombre_coleccion>.EntrySet (devuelve una collection)
	//For(MapEntryKey value: <nombre-collection>.EntrySet){
	// Entry.getKey(obtiene la key de la posicion)
	// entry.getValue().getApelido() (devuelve algun valor del objeto dentro de la collection map}
}
