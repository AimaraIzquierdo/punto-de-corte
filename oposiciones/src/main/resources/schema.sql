-- ======================
-- ACADEMIAS
-- ======================

CREATE TABLE IF NOT EXISTS Academias (
                                         id_aca INT AUTO_INCREMENT PRIMARY KEY,
                                         nombre_aca VARCHAR(30),
    email_aca VARCHAR(50),
    telefono_aca INT,
    direccion_aca VARCHAR(60)
    );

-- ======================
-- OPOSICIONES
-- ======================

CREATE TABLE IF NOT EXISTS Oposiciones (
                                           id_opo INT AUTO_INCREMENT PRIMARY KEY,
                                           nombre_opo VARCHAR(100)
    );

-- ======================
-- TEMAS
-- ======================

CREATE TABLE IF NOT EXISTS Temas (
                                     id_tema INT AUTO_INCREMENT PRIMARY KEY,
                                     nombre_tema VARCHAR(50),
    desc_tema TEXT,
    id_opo INT,

    FOREIGN KEY (id_opo)
    REFERENCES Oposiciones(id_opo)
    );

-- ======================
-- USUARIOS
-- ======================

CREATE TABLE IF NOT EXISTS Usuarios (
                                        id_usu INT AUTO_INCREMENT PRIMARY KEY,
                                        nombre_usu VARCHAR(30),
    ape_usu VARCHAR(50),
    email_usu VARCHAR(50),
    contrasena_usu VARCHAR(100),
    id_rol INT,
    id_aca INT,
    fecha_registro DATE,
    foto_perfil VARCHAR(255),
    espacio_almacenar INT,

    FOREIGN KEY (id_rol)
    REFERENCES Roles(id_rol),

    FOREIGN KEY (id_aca)
    REFERENCES Academias(id_aca)
    );

-- ======================
-- TEMARIOS
-- ======================

CREATE TABLE IF NOT EXISTS Temarios (
                                        id_temario INT AUTO_INCREMENT PRIMARY KEY,
                                        nombre_temario VARCHAR(50),
    tipo_temario VARCHAR(50),
    ruta_temario VARCHAR(100),
    fecha_temario DATE,
    desc_temario TEXT,

    id_usu INT,
    id_aca INT,
    id_opo INT,

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_aca)
    REFERENCES Academias(id_aca),

    FOREIGN KEY (id_opo)
    REFERENCES Oposiciones(id_opo)
    );

-- ======================
-- TEST
-- ======================

CREATE TABLE IF NOT EXISTS Test (
                                    id_test INT AUTO_INCREMENT PRIMARY KEY,
                                    nombre VARCHAR(30),

    id_tema INT,
    id_usu INT,

    fecha_test DATE,

    visible VARCHAR(20),

    FOREIGN KEY (id_tema)
    REFERENCES Temas(id_tema),

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu)
    );

-- ======================
-- PREGUNTAS
-- ======================

CREATE TABLE IF NOT EXISTS Preguntas (
                                         id_preg INT AUTO_INCREMENT PRIMARY KEY,

                                         enunciado_preg TEXT,

                                         tipo_preg VARCHAR(30),

    id_test INT,

    FOREIGN KEY (id_test)
    REFERENCES Test(id_test)
    );

-- ======================
-- OPCIONES
-- ======================

CREATE TABLE IF NOT EXISTS Opciones (
                                        id_opcion INT AUTO_INCREMENT PRIMARY KEY,

                                        texto VARCHAR(100),

    es_correcta BOOLEAN,

    id_preg INT,

    FOREIGN KEY (id_preg)
    REFERENCES Preguntas(id_preg)
    );

-- ======================
-- RESULTADOS TEST
-- ======================

CREATE TABLE IF NOT EXISTS Resultados_test (
                                               id_resul INT AUTO_INCREMENT PRIMARY KEY,

                                               nota FLOAT,

                                               fecha_resul DATE,

                                               respuestas TEXT,

                                               id_usu INT,
                                               id_test INT,

                                               FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_test)
    REFERENCES Test(id_test)
    );

-- ======================
-- SUSCRIPCIONES
-- ======================

CREATE TABLE IF NOT EXISTS Suscripciones (
                                             id_susc INT AUTO_INCREMENT PRIMARY KEY,

                                             tipo VARCHAR(20),

    inicio_susc DATE,
    fin_susc DATE,

    tipo_pago VARCHAR(30),

    estado VARCHAR(20),

    id_usu INT,

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu)
    );

-- ======================
-- FLASHCARDS
-- ======================

CREATE TABLE IF NOT EXISTS Flashcards (
    id_flash INT AUTO_INCREMENT PRIMARY KEY,
    preg_flash TEXT,
    resp_flash TEXT,
    date_flash DATE,
    visibilidad VARCHAR(20),
    id_usu INT,
    id_tema INT,

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_tema)
    REFERENCES Temas(id_tema)
    );

-- ======================
-- EVENTOS
-- ======================

CREATE TABLE IF NOT EXISTS Eventos (
                                       id_evento INT AUTO_INCREMENT PRIMARY KEY,

                                       titulo_event VARCHAR(30),
    desc_event TEXT,

    inicio_event DATE,
    fin_event DATE,

    tipo VARCHAR(20),

    id_usu INT,
    id_aca INT,

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_aca)
    REFERENCES Academias(id_aca)
    );

-- ======================
-- METRICAS
-- ======================

CREATE TABLE IF NOT EXISTS Metricas (
                                        id_metr INT AUTO_INCREMENT PRIMARY KEY,

                                        tipo_metr VARCHAR(20),

    valor_metr FLOAT,

    fecha_metr DATE,

    detalle_metr TEXT,

    id_usu INT,
    id_tema INT,

    FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_tema)
    REFERENCES Temas(id_tema)
    );

-- ======================
-- VIDEOS
-- ======================

CREATE TABLE IF NOT EXISTS Videos (
                                      id_video INT AUTO_INCREMENT PRIMARY KEY,

                                      url_video VARCHAR(255),

    descrip_video TEXT,

    id_admin INT,

    id_tema INT,

    FOREIGN KEY (id_admin)
    REFERENCES Usuarios(id_usu),

    FOREIGN KEY (id_tema)
    REFERENCES Temas(id_tema)
    );

-- ======================
-- BLOG
-- ======================

CREATE TABLE IF NOT EXISTS Blog (
                                    id_blog INT AUTO_INCREMENT PRIMARY KEY,

                                    titulo_blog VARCHAR(255),

    content_blog TEXT,

    fecha_blog DATE,

    img_blog VARCHAR(255),

    id_admin INT,

    FOREIGN KEY (id_admin)
    REFERENCES Usuarios(id_usu)
    );

-- ======================
-- TIENDA
-- ======================

CREATE TABLE IF NOT EXISTS Tienda (
                                      id_produc   INT AUTO_INCREMENT PRIMARY KEY,
                                      nombre_produc VARCHAR(255),
    descrip_produc TEXT,
    precio      FLOAT,
    url_produc  VARCHAR(255),
    categoria   VARCHAR(50),
    id_admin    INT,
    FOREIGN KEY (id_admin) REFERENCES Usuarios(id_usu)
    );

-- ======================
-- CHAT IA
-- ======================

CREATE TABLE IF NOT EXISTS Chat_IA (
                                       id_chat INT AUTO_INCREMENT PRIMARY KEY,

                                       mens_usu TEXT,

                                       resp_ia TEXT,

                                       fecha TIMESTAMP,

                                       id_usu INT,

                                       FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu)
    );

-- ======================
-- AVISOS
-- ======================

CREATE TABLE IF NOT EXISTS Avisos (
                                      id_aviso INT AUTO_INCREMENT PRIMARY KEY,

                                      mensaje_aviso TEXT,

                                      crea_aviso TIMESTAMP,

                                      leido_aviso BOOLEAN,

                                      tipo_aviso INT,

                                      id_usu INT,

                                      FOREIGN KEY (id_usu)
    REFERENCES Usuarios(id_usu)
    );

-- ======================
-- MODULOS
-- ======================

CREATE TABLE IF NOT EXISTS Modulos (
                                       id_modulo INT AUTO_INCREMENT PRIMARY KEY,

                                       nombre VARCHAR(100),

    descripcion TEXT,

    imagen VARCHAR(255),

    texto_boton VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS Carrito (
                                       id_carrito  INT AUTO_INCREMENT PRIMARY KEY,
                                       id_usu      INT NOT NULL,
                                       id_produc   INT NOT NULL,
                                       cantidad    INT DEFAULT 1,
                                       FOREIGN KEY (id_usu)    REFERENCES Usuarios(id_usu),
    FOREIGN KEY (id_produc) REFERENCES Tienda(id_produc)
    );