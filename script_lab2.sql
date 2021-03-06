DELETE from Ficha;
DELETE from Jugador;
DELETE from Jugada;
DELETE from Juego;
DELETE from JugadasJuego;
DELETE from FichasJugador;
DELETE from FichasJuego;

DROP TABLE Ficha;
DROP TABLE Jugador;
DROP TABLE Jugada;
DROP TABLE Juego;
DROP TABLE JugadasJuego;
DROP TABLE FichasJugador;
DROP TABLE FichasJuego;

CREATE OR REPLACE PACKAGE types 
as
TYPE ref_cursor is ref cursor;
END;
/

-- //////////////////////////////////
--              FICHA
-- //////////////////////////////////

CREATE TABLE Ficha(
id_ficha int,
valor1 int,
valor2 int,
derecha varchar(1),
constraint pkFicha primary key (id_ficha)
);
/

CREATE OR REPLACE procedure insertar_ficha(xid_ficha int,xvalor1 int, xvalor2 int, xderecha varchar)
AS
BEGIN
 insert into Ficha(id_ficha,valor1,valor2,derecha)
 values (xid_ficha,xvalor1,xvalor2,xderecha);
END;
/

CREATE OR REPLACE FUNCTION listar_ficha
RETURN types.ref_cursor
AS
ficha_cursor types.ref_cursor;
BEGIN
OPEN ficha_cursor FOR
select ficha.id_ficha,ficha.valor1, ficha.valor2, ficha.derecha
from Ficha;
RETURN ficha_cursor;
END;
/

EXECUTE insertar_ficha(1,0,0,'N');
EXECUTE insertar_ficha(2,0,1,'N');
EXECUTE insertar_ficha(3,0,2,'N');
EXECUTE insertar_ficha(4,0,3,'N');
EXECUTE insertar_ficha(5,0,4,'N');
EXECUTE insertar_ficha(6,0,5,'N');
EXECUTE insertar_ficha(7,0,6,'N');

EXECUTE insertar_ficha(8,1,1,'N');
EXECUTE insertar_ficha(9,1,2,'N');
EXECUTE insertar_ficha(10,1,3,'N');
EXECUTE insertar_ficha(11,1,4,'N');
EXECUTE insertar_ficha(12,1,5,'N');
EXECUTE insertar_ficha(13,1,6,'N');

EXECUTE insertar_ficha(14,2,2,'N');
EXECUTE insertar_ficha(15,2,3,'N');
EXECUTE insertar_ficha(16,2,4,'N');
EXECUTE insertar_ficha(17,2,5,'N');
EXECUTE insertar_ficha(18,2,6,'N');

EXECUTE insertar_ficha(19,3,3,'N');
EXECUTE insertar_ficha(20,3,4,'N');
EXECUTE insertar_ficha(21,3,5,'N');
EXECUTE insertar_ficha(22,3,6,'N');

EXECUTE insertar_ficha(23,4,4,'N');
EXECUTE insertar_ficha(24,4,5,'N');
EXECUTE insertar_ficha(25,4,6,'N');

EXECUTE insertar_ficha(26,5,5,'N');
EXECUTE insertar_ficha(27,5,6,'N');

EXECUTE insertar_ficha(28,6,6,'N');

-- //////////////////////////////////
--              JUGADOR
-- //////////////////////////////////

CREATE TABLE Jugador(
id_jugador varchar(30),
partidasGanadas int,
constraint pkJugador primary key (id_jugador)
);
/

CREATE OR REPLACE procedure insertar_jugador(xid_jugador varchar, xpartidasGanadas int)
AS
BEGIN
 insert into Jugador(id_jugador, partidasGanadas)
 values (xid_jugador,xpartidasGanadas);
END;
/

CREATE OR REPLACE FUNCTION listar_jugador
RETURN types.ref_cursor
AS
jugador_cursor types.ref_cursor;
BEGIN
OPEN jugador_cursor FOR
select jugador.id_jugador, jugador.partidasGanadas
from Jugador;
RETURN jugador_cursor;
END;
/

EXECUTE insertar_jugador('reinaldo',6);

-- //////////////////////////////////
--              JUGADASJUEGO
-- //////////////////////////////////

CREATE TABLE JugadasJuego(
id_jugadasJuego int,
id_jugada int,
constraint pkJugadasJuego primary key (id_jugadasJuego),
constraint fkJugada foreign key (id_jugada) references Jugada(id_jugada)
);
/

-- //////////////////////////////////
--              JUGADA
-- //////////////////////////////////

CREATE TABLE Jugada(
id_jugada int,
id_jugador varchar(30),
id_ficha int,
derecha varchar(1),
secuencia int,
constraint pkJugada primary key (id_jugada),
constraint fkJugador foreign key (id_jugador) references Jugador(id_jugador),
constraint fkFicha foreign key (id_ficha) references Ficha(id_ficha)
);
/

CREATE OR REPLACE procedure insertar_jugada(xid_jugada int, xid_jugador varchar,xid_ficha int,xderecha varchar,xsecuencia int)
AS
BEGIN
 insert into Jugada(id_jugada, id_jugador, id_ficha, derecha, secuencia)
 values (xid_jugada, xid_jugador, xid_ficha, xderecha, xsecuencia);
END;
/

CREATE OR REPLACE FUNCTION listar_jugada
RETURN types.ref_cursor
AS
jugada_cursor types.ref_cursor;
BEGIN
OPEN jugada_cursor FOR
select jugada.id_jugada, jugada.id_jugador, jugada.id_ficha, jugada.derecha, jugada.secuencia
from Jugada;
RETURN jugada_cursor;
END;
/

EXECUTE insertar_jugada(1,'reinaldo',2,'N',1);

-- //////////////////////////////////
--           FICHAS JUGADOR
-- //////////////////////////////////

CREATE TABLE FichasJugador(
id_fichasJugador int,
id_jugador varchar(30),
id_ficha int,
constraint pkFichasJugador primary key (id_fichasJugador),
constraint fkJug foreign key (id_jugador) references Jugador(id_jugador),
constraint fkFich foreign key (id_ficha) references Ficha(id_ficha)
);
/

CREATE OR REPLACE procedure insertar_fichasJugador(xid_fichasJugador int, xid_jugador varchar,xid_ficha int)
AS
BEGIN
 insert into FichasJugador(id_fichasJugador, id_jugador, id_ficha)
 values (xid_fichasJugador, xid_jugador, xid_ficha);
END;
/

CREATE OR REPLACE FUNCTION listar_fichasJugador
RETURN types.ref_cursor
AS
fichasJugador_cursor types.ref_cursor;
BEGIN
OPEN fichasJugador_cursor FOR
select fichasJugador.id_fichasJugador, fichasJugador.id_jugador, fichasJugador.id_ficha
from FichasJugador;
RETURN fichasJugador_cursor;
END;
/

EXECUTE insertar_fichasJugador(1,'reinaldo',2);

-- //////////////////////////////////
--              JUEGO
-- //////////////////////////////////

CREATE TABLE Juego(
id_juego int,
id_jugador varchar(30),
id_ficha int,
id_fichasJugador int,
id_jugada int,
constraint pkJuego primary key (id_juego),
constraint fkJugadorAct foreign key (id_jugador) references Jugador(id_jugador),
constraint fkFichas foreign key (id_ficha) references Ficha(id_ficha),
constraint fkJugada foreign key (id_jugada) references Jugada(id_jugada),
constraint fkFichasJugador foreign key (id_fichasJugador) references FichasJugador(id_fichasJugador)
);
/

CREATE OR REPLACE procedure insertar_juego(xid_juego int, xid_jugador varchar,xid_ficha int, xid_fichasJugador int,xid_jugada int)
AS
BEGIN
 insert into Juego(id_juego, id_jugador,id_ficha, id_fichasJugador,id_jugada)
 values (xid_juego, xid_jugador,xid_ficha, xid_fichasJugador,xid_jugada);
END;
/

CREATE OR REPLACE FUNCTION listar_juegos
RETURN types.ref_cursor
AS
juego_cursor types.ref_cursor;
BEGIN
OPEN juego_cursor FOR
select juego.id_juego, juego.id_jugador,juego.id_ficha, juego.id_fichasJugador,juego.id_jugada
from Juego;
RETURN juego_cursor;
END;
/

EXECUTE insertar_juego(1,'reinaldo',2,1,1);


-- //////////////////////////////////
--           FICHA JUEGO
-- //////////////////////////////////

CREATE TABLE FichasJuego(
id_fichasJuego int,
id_juego int,
id_ficha int,
constraint pkFichasJuego primary key (id_fichasJuego),
constraint fkJuego foreign key (id_juego) references Juego(id_juego),
constraint fkFicha1 foreign key (id_ficha) references Ficha(id_ficha)
);
/

CREATE OR REPLACE procedure insertar_fichasJuego(xid_fichasJuego int, xid_juego varchar,xid_ficha int)
AS
BEGIN
 insert into FichasJuego(id_fichasJuego, id_juego, id_ficha)
 values (xid_fichasJuego, xid_juego, xid_ficha);
END;
/

CREATE OR REPLACE FUNCTION listar_fichasJuego
RETURN types.ref_cursor
AS
fichasJuego_cursor types.ref_cursor;
BEGIN
OPEN fichasJuego_cursor FOR
select fichasJuego.id_fichasJuego, fichasJuego.id_juego, fichasJuego.id_ficha
from FichasJuego;
RETURN fichasJuego_cursor;
END;
/

EXECUTE insertar_fichasJuego(1,1,2);

/*
CREATE OR REPLACE FUNCTION listar_productoByNombre(vnombre varchar)
	RETURN types.ref_cursor
	AS
		producto_cursor types.ref_cursor;
	BEGIN
	OPEN producto_cursor FOR
		select P.codigo,
			   P.nombre, 
			   P.precio,
			   P.importado,
			   T.id_tipo,
			   T.descripcion,
			   T.porcentaje
			   from Producto P
			   inner join TipoProducto T
               on P.id_tipo = T.id_tipo
			   where UPPER(P.nombre) like '%'||UPPER(vnombre)||'%';
RETURN producto_cursor;
END;
/

CREATE OR REPLACE FUNCTION listar_productoByTipo(tipo int)
	RETURN types.ref_cursor
	AS
		producto_cursor types.ref_cursor;
	BEGIN
	OPEN producto_cursor FOR
		select P.codigo,
			   P.nombre, 
			   P.precio,
			   P.importado,
			   T.id_tipo,
			   T.descripcion,
			   T.porcentaje
			   from Producto P
			   inner join TipoProducto T
               on P.id_tipo = T.id_tipo
			   where t.id_tipo = tipo;
RETURN producto_cursor;
END;
/

CREATE OR REPLACE FUNCTION listar_productoByCod(vcodigo varchar)
	RETURN types.ref_cursor
	AS
		producto_cursor types.ref_cursor;
	BEGIN
	OPEN producto_cursor FOR
		select P.codigo,
			   P.nombre, 
			   P.precio,
			   P.importado,
			   T.id_tipo,
			   T.descripcion,
			   T.porcentaje
			   from Producto P
			   inner join TipoProducto T
               on P.id_tipo = T.id_tipo
			   where P.codigo = vcodigo;
RETURN producto_cursor;
END;
/
*/


