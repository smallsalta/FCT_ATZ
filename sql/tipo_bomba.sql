alter table t_parte_linea
    add column orden_bomba int4,
    add column tipo_bomba int4,
    add column marca_bomba varchar,
    add column modelo_bomba varchar,
    add column fecha_bomba date,
    add column motor_bomba varchar,
    add column voltaje_bomba float8,
    add column rpm_bomba float8,
    add column manometro_bomba varchar,
    add column esfera_bomba varchar,
    add column valvulas_bomba varchar,
    add column saltos_bomba varchar,
    add column fusibles_bomba varchar,
    add column alarma_bomba varchar,
    add column caudalimetro_bomba float8,
    add column presion_bomba float8;

create table if not exists t_tipo_bie_bomba (
    oid int4 primary key unique,
    tipo varchar not null
);

alter table t_parte_linea
    add column tipo_bie_bomba int4;

insert into t_tipo_bie_bomba values 
    (1, 'Principal'), 
    (2, 'Jocker'), 
    (3, 'Diesel');

alter table t_parte_linea
    add constraint fk_tipo_bie_bomba
    foreign key (tipo_bie_bomba)
    references t_tipo_bie_bomba (oid);