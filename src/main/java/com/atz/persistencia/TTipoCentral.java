package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_tipo_central", schema = "public")
public class TTipoCentral extends TSelectCentralita implements java.io.Serializable {

}
