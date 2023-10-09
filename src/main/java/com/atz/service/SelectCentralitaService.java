package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.SelectCentralitaDAO;
import com.atz.persistencia.TEstadoLineaCentralita;
import com.atz.persistencia.TFuncionDetectores;
import com.atz.persistencia.TTipoCentral;
import com.atz.persistencia.TTipoDetectores;
import com.atz.persistencia.TTipoEquipoAuxiliar;
import com.atz.persistencia.TTipoPulsadores;
import com.atz.persistencia.TTipoSirenas;

@Service
public class SelectCentralitaService {

	@Autowired
	private SelectCentralitaDAO dao;
	
	@Transactional(readOnly=true)
	public List<TTipoEquipoAuxiliar> getAllTipoEquipoAuxiliar() {
		return this.dao.getAllTipoEquipoAuxiliar();
	}

	@Transactional(readOnly=true)
	public List<TEstadoLineaCentralita> getAllEstadoLineaCentralita() {
		return this.dao.getAllEstadoLineaCentralita();
	}

	@Transactional(readOnly=true)
	public List<TTipoSirenas> getAllTipoSirenas() {
		return this.dao.getAllTipoSirenas();
	}
	
	@Transactional(readOnly=true)
	public List<TTipoDetectores> getAllTipoDetectores() {
		return this.dao.getAllTipoDetectores();
	}
	
	@Transactional(readOnly=true)
	public List<TFuncionDetectores> getAllFuncionDetectores() {
		return this.dao.getAllFuncionDetectores();
	}

	@Transactional(readOnly=true)
	public List<TTipoPulsadores> getAllTipoPulsadores() {
		return this.dao.getAllTipoPulsadores();
	}

	@Transactional(readOnly=true)
	public List<TTipoCentral> getAllTipoCentral() {
		return this.dao.getAllTipoCentral();
	}
}
