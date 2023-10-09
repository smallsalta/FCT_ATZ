package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_funcion_detectores", schema = "public")
public class TFuncionDetectores extends TSelectCentralita implements java.io.Serializable {

}
