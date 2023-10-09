package com.atz.persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_tipo_pulsadores", schema = "public")
public class TTipoPulsadores extends TSelectCentralita implements java.io.Serializable {

}
