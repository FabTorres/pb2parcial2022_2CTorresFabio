package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Exceptions.UsuarioExistenteException;

public class Banco {

	private ArrayList<Cuenta> cuentas;
	private HashMap<Integer, Titular> usuarios;

	public Banco() {
		this.cuentas = new ArrayList<>();
		this.usuarios = new HashMap<>();
	}

	public void agregarCuenta(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}

	public Boolean transferir(Double monto, Cuenta origen, Cuenta destino) throws MontoInsuficienteException {
		if (origen.extraer(monto)) {
			destino.depositar(monto);
			return true;
		}
		return false;
	}

	public Boolean transferir(Integer numCuentaOrigen, Integer numCuentaDestino, Double monto)
			throws CuentaNoEncontradaException, MontoInsuficienteException {
		Cuenta origen = buscarCuenta(numCuentaOrigen);
		Cuenta destino = buscarCuenta(numCuentaDestino);
		return transferir(monto, origen, destino);
	}

	public Cuenta buscarCuenta(Integer numCuenta) throws CuentaNoEncontradaException {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getNumCuenta().equals(numCuenta)) {
				return cuenta;
			}
		}
		throw new CuentaNoEncontradaException("No se encontro la cuenta");
	}

	public Integer obtenerSumatoriaDePuntosDeCuentasCorrientes() {
		Integer total = 0;
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getTipo().equals("CuentaCorriente")) {
				total += ((CuentaCorriente) cuenta).getPuntos();
			}
		}
		return total;
	}

	public void agregarUsuario(Integer key, Titular titular) throws UsuarioExistenteException {
		if (this.usuarios.containsKey(key)) {
			throw new UsuarioExistenteException("El usuario ya existe");
		}
		this.usuarios.put(key, titular);

	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public HashMap<Integer, Titular> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashMap<Integer, Titular> usuarios) {
		this.usuarios = usuarios;
	}

	public Titular buscarUsuario(Integer key) {
		return this.usuarios.get(key);
	}

}
