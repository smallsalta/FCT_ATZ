package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TEstadoLineaCentralita;
import com.atz.persistencia.TFuncionDetectores;
import com.atz.persistencia.TTipoCentral;
import com.atz.persistencia.TTipoDetectores;
import com.atz.persistencia.TTipoEquipoAuxiliar;
import com.atz.persistencia.TTipoPulsadores;
import com.atz.persistencia.TTipoSirenas;

@Repository
public class SelectCentralitaDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TTipoEquipoAuxiliar> getAllTipoEquipoAuxiliar() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoEquipoAuxiliar").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TEstadoLineaCentralita> getAllEstadoLineaCentralita() {
		return this.sessionFactory.getCurrentSession().createQuery("from TEstadoLineaCentralita").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoSirenas> getAllTipoSirenas() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoSirenas").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoDetectores> getAllTipoDetectores() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoDetectores").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TFuncionDetectores> getAllFuncionDetectores() {
		return this.sessionFactory.getCurrentSession().createQuery("from TFuncionDetectores").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoPulsadores> getAllTipoPulsadores() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoPulsadores").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoCentral> getAllTipoCentral() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoCentral").list();
	}


}
