package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_tipo_sirenas", schema = "public")
public class TTipoSirenas extends TSelectCentralita implements java.io.Serializable {

}
