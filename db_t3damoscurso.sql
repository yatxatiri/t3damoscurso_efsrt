drop database if exists bd_t3damoscurso;
create database bd_t3damoscurso;
use bd_t3damoscurso;

-- Tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    id_plan INT REFERENCES planes(id_plan),
    rol VARCHAR(20) CHECK (rol IN ('usuario', 'administrador')),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de planes
CREATE TABLE planes (
    id_plan INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20),
    precio DECIMAL(10,2),
    max_usuarios INT
);

-- Relación de usuarios con planes (para gestionar tiempos de suscripción)
CREATE TABLE usuario_plan (
    id_usuario INT REFERENCES usuarios(id_usuario),
    id_plan INT REFERENCES planes(id_plan),
    fecha_inicio TIMESTAMP,
    fecha_expiracion TIMESTAMP,
    PRIMARY KEY (id_usuario, id_plan)
);

-- Tabla de cursos
CREATE TABLE cursos (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    categoria VARCHAR(100),
    es_gratuito INT
);

-- Tabla de clases
CREATE TABLE clases (
    id_clase INT AUTO_INCREMENT PRIMARY KEY,
    id_curso INT REFERENCES cursos(id_curso),
    resumen VARCHAR(100),
    id_profesor INT REFERENCES profesores(id_profesor)
);

-- Tabla de profesores
CREATE TABLE profesores (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100)
);

-- Historial de compras de cursos y planes
CREATE TABLE historial_compras (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT REFERENCES usuarios(id_usuario),
    id_curso INT REFERENCES cursos(id_curso),
    id_plan INT REFERENCES planes(id_plan),
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_compra VARCHAR(20) CHECK (tipo_compra IN ('curso', 'plan'))
);

-- Notas de venta
CREATE TABLE notas_venta (
    id_nota INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT REFERENCES usuarios(id_usuario),
    id_curso INT REFERENCES cursos(id_curso),
    id_plan INT REFERENCES planes(id_plan),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2)
);

alter table clases add constraint FKbachgu2487fctldodsx2etjfc foreign key (id_curso) references cursos (id)