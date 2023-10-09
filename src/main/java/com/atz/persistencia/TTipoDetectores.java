package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_tipo_detectores", schema = "public")
public class TTipoDetectores extends TSelectCentralita implements java.io.Serializable {

}
