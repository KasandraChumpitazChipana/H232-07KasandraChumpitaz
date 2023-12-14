-- Poner en uso BD Master 
USE master;

-- Crear la base de datos db_bodegapanchita
IF DB_ID (N'db_JasDigitalMedio') IS NOT NULL
	DROP DATABASE db_JasDigitalMedio
GO
CREATE DATABASE db_JasDigitalMedio
GO

-- Poner es uso la base de datos db_bodegapanchita
USE db_JasDigitalMedio;

-- Cambiamos el idioma a español y ver si se cambio 
SET LANGUAGE Español;
SELECT @@language AS 'Idioma';
                      
					  
											----------------------------------------------------------
									     --------------------  T  A  B  L  A  S  -------------------------
											----------------------------------------------------------

------------<> MAESTROS

-- 01 Tabla goods (Bienes)
CREATE TABLE goods (
    code varchar(10)  NOT NULL,
    amount int  NOT NULL,
    details_goods varchar(100)  NOT NULL,
    book_value decimal(8,2)  NOT NULL,
    date_entry date  NOT NULL,
    date_depreciation date  NOT NULL,
    state char(4)  NOT NULL,
    depreciation_id int  NOT NULL,
    area_id int  NOT NULL,
    CONSTRAINT state_pk PRIMARY KEY  (code)
);

-- 02 Tabla area
CREATE TABLE area (
    id int  NOT NULL IDENTITY (1,1),
    name varchar(70)  NOT NULL,
    name_head_area varchar(80)  NOT NULL,
    lastname_head_area varchar(90)  NOT NULL,
    CONSTRAINT area_pk PRIMARY KEY  (id)
);

-- 03 Tabla depreciation(depreciacion)
CREATE TABLE depreciation (
    id int  NOT NULL  IDENTITY (1,1),
    descriptions_good varchar(100)  NOT NULL,
    percentage INT  NOT NULL,
    CONSTRAINT depreciation_pk PRIMARY KEY  (id)
);

------------<> TRANSACCIONALES

-- 07 Tabla inventory (inventario)
CREATE TABLE inventory (
    id int  NOT NULL  IDENTITY (1,1),
    goods_code varchar(10)  NOT NULL,
    date_time datetime  NOT NULL,
    state char(4)  NOT NULL,
    CONSTRAINT inventory_pk PRIMARY KEY  (id)
);



                                             ----------------------------------------------------------
								 --------------------  R  E  S  T  R  I  C  C  I  O  N  E  S  -------------------------
											 ----------------------------------------------------------

----------<> Restricciones para la Tabla goods (bienes)
-- Restricción para estados  válidos (ALTA o BAJA)
ALTER TABLE goods
    ADD CONSTRAINT chk_state_goods CHECK(state ='ALTA' OR state ='BAJA');

-- Restricción para asignar un valor por defecto la fecha actual a la columna date_time
ALTER TABLE goods
    ADD CONSTRAINT df_date_goods DEFAULT GETDATE() FOR date_entry;

-- Restriccion para asegurar que el campo amount sera un valor positivo
ALTER TABLE goods
    ADD CONSTRAINT chk_amount_goods CHECK(amount > 0);

----------<> Restricciones para la Tabla inventory (inventario)
-- Restricción para asignar un valor por defecto la fecha y hora actual a la columna date_time
ALTER TABLE inventory
    ADD CONSTRAINT df_date_time_inventory DEFAULT GETDATE() FOR date_time;

-- Restricción para estados  válidos (ALTA o BAJA)
ALTER TABLE inventory
    ADD CONSTRAINT chk_state_inventory CHECK(state ='ALTA' OR state ='BAJA');



                                             ----------------------------------------------------------------------
									   ----------------------  R  E  L  A  C  I  O  N  E  S  ---------------------------
											 ----------------------------------------------------------------------

-- foreign keys

-- 1. Un bien puede estar en uno o varias veces en el inventario
ALTER TABLE inventory ADD CONSTRAINT inventory_goods
    FOREIGN KEY (goods_code)
    REFERENCES goods (code);

-- 2. Un area puede estar una o muchas bienes
ALTER TABLE goods ADD CONSTRAINT state_area
    FOREIGN KEY (area_id)
    REFERENCES area (id);

-- 3. Una depreciacion puede estar una o muchos bienes
ALTER TABLE goods ADD CONSTRAINT goods_depreciation
    FOREIGN KEY (depreciation_id)
    REFERENCES depreciation (id);


-------------<>
-- Índice no agrupado para la tabla goods en la columna details_goods
CREATE NONCLUSTERED INDEX idx_details_goods
ON goods (details_goods);


-----------------------------------<>Insercion MAESTRAS

-- TABLA AREA
INSERT INTO area(name, name_head_area, lastname_head_area)
VALUES
('PRODUCCION','Angel Gabriel', 'Castilla Sandoval'),
('CONTABILIDAD','Fernando', 'Sandoval Medina'),
('MARKETING','Luis Alberto', 'Barrios Paredes'),
('RECURSOS HUMANOS', 'Claudia María', 'Martínez Rodríguez'),
('VENTAS',  'Mario Tadeo', 'Farfán Castillo');

-- TABLA DEPRECIATON
INSERT INTO depreciation(descriptions_good, percentage)
VALUES
('Ganado de trabajo y reproducción redes de pesca', 25),
('Vehículo de transporte terrestre (excepto ferrocarriles; horno en general)',20),
('Maquinaria y equipo utilizados por las actividades mineras, petroleras y de construcción',20),
('Equipos de procesamiento de datos', 25),
('Maquinaria y equipo adquirido a partir del 1/1/1991',  10),
('Otros bienes del activo fijo',  10);
SELECT*FROM depreciation;

-- TABLA GOODS
INSERT INTO goods(code, amount, details_goods, book_value, date_depreciation, depreciation_id, area_id, state)
VALUES
('EQ-001', '12', 'Escritorio color gris', '400.00', '2023/12/31', 2, 1, 'ALTA'),
('EQ-002','10', 'Escritorio color marrón', '900.00', '2023/12/31', 4, 2, 'ALTA'),
('EQ-003','2', 'Escritorio color gris', '490.00', '2023/12/31', 2, 3, 'ALTA'),
('EQ-004','32', 'Escritorio color gris', '90.00', '2023/12/31', 1, 4, 'ALTA'),
('EQ-005', '9', 'Escritorio color gris', '40.00', '2023/12/31', 2, 1, 'BAJA');

-----------------------------------<>Insercion TRANSACCIONALES
-- TABLA AREA
INSERT INTO inventory(goods_code, state)
VALUES
(1, 'ALTA'),
(2, 'ALTA'),
(3, 'ALTA'),
(4, 'ALTA'),
(1, 'BAJA');
