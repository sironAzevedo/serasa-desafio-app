CREATE SCHEMA IF NOT EXISTS PUBLIC;

CREATE TABLE PUBLIC.TBL_PESSOA
(
  ID BIGINT auto_increment NOT NULL,
  NOME VARCHAR (100) NOT NULL,
  TELEFONE VARCHAR (20) NOT NULL,
  IDADE NUMERIC(2,0) NOT NULL,
  SCORE BIGINT NOT NULL,
  CIDADE VARCHAR (50) NOT NULL,
  ESTADO VARCHAR (50) NOT NULL,
  REGIAO VARCHAR (50) NOT NULL,
  DATA_INCLUSAO DATE NOT NULL,

  constraint tbl_pessoa_pk primary key (ID)
);

CREATE TABLE PUBLIC.TBL_AFINIDADE
(
    ID BIGINT auto_increment NOT NULL,
    REGIAO VARCHAR (50) NOT NULL,
    UNIQUE(REGIAO),

    constraint tbl_afinidade_pk primary key (ID)
);

CREATE TABLE PUBLIC.TBL_ESTADO
(
    ID BIGINT auto_increment NOT NULL,
    ID_AFINIDADE BIGINT NOT NULL,
    DESC_ESTADO CHAR (2) NOT NULL,

    constraint tbl_estado_pk primary key (ID),
    constraint tbl_afinidade_fk foreign key (ID_AFINIDADE) references PUBLIC.TBL_AFINIDADE(ID)
);

CREATE TABLE PUBLIC.TBL_SCORE
(
    ID BIGINT auto_increment NOT NULL,
    DESCRICAO VARCHAR (50) NOT NULL,
    INICIAL NUMERIC(4,0) NOT NULL,
    FINAL NUMERIC(4,0) NOT NULL,

    constraint tbl_score_pk primary key (ID)
);
