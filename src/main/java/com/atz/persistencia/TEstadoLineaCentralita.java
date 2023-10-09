package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_estado_linea_centralita", schema = "public")
public class TEstadoLineaCentralita extends TSelectCentralita implements java.io.Serializable {

}
