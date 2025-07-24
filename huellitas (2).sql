-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-07-2025 a las 02:56:11
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `huellitas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `nombreUsuario` varchar(45) NOT NULL,
  `password` varchar(25) NOT NULL,
  `idper` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`nombreUsuario`, `password`, `idper`) VALUES
('jhosua', '123456', 1),
('lelo', '123456', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adopciones`
--

CREATE TABLE `adopciones` (
  `ida` int(11) NOT NULL,
  `fecha` varchar(25) NOT NULL,
  `obs` text DEFAULT NULL,
  `idmas` int(11) NOT NULL,
  `idadop` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `adopciones`
--

INSERT INTO `adopciones` (`ida`, `fecha`, `obs`, `idmas`, `idadop`) VALUES
(3, '10/03/2023', 'ninguna', 2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adoptantes`
--

CREATE TABLE `adoptantes` (
  `direccion` varchar(100) NOT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `ocupacion` varchar(75) NOT NULL,
  `idper` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `adoptantes`
--

INSERT INTO `adoptantes` (`direccion`, `telefono`, `correo`, `ocupacion`, `idper`) VALUES
('siempre viva', 75324232, 'diego@gmail.com', 'universitario', 3),
('costa nera', 7323451, 'jose@gmail.com', 'albañil', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especies`
--

CREATE TABLE `especies` (
  `ide` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `especies`
--

INSERT INTO `especies` (`ide`, `nombre`) VALUES
(1, 'perro'),
(2, 'gato');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `idhistorial` int(11) NOT NULL,
  `diagnostico` text NOT NULL,
  `fechaConsulta` varchar(25) NOT NULL,
  `medicamentos` varchar(100) DEFAULT NULL,
  `idmas` int(11) NOT NULL,
  `idvete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`idhistorial`, `diagnostico`, `fechaConsulta`, `medicamentos`, `idmas`, `idvete`) VALUES
(1, 'resfrio', '24/01/2025', 'pildoras', 2, 5),
(2, 'patas rajadas', '13/04/2025', 'ninguno', 2, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `idm` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `idesp` int(11) NOT NULL,
  `raza` varchar(50) DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `obs` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascotas`
--

INSERT INTO `mascotas` (`idm`, `nombre`, `idesp`, `raza`, `color`, `edad`, `obs`) VALUES
(1, 'albino', 1, 'huski', 'blanco', 3, 'ninguna'),
(2, 'cachuchin', 1, 'comun', 'negro', 5, 'ninguna');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `idp` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `paterno` varchar(50) DEFAULT NULL,
  `materno` varchar(50) DEFAULT NULL,
  `ci` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`idp`, `nombre`, `paterno`, `materno`, `ci`) VALUES
(1, 'joani', 'huanca', 'chiri', 231234123),
(2, 'leo', 'chirino', 'gutierrez', 234123),
(3, 'diego', 'ramirez', 'lopez', 63232212),
(4, 'jose', 'aruquipa', 'lecoña', 62131312),
(5, 'julio', 'lemus', 'aruquipa', 1623434);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `veterinarios`
--

CREATE TABLE `veterinarios` (
  `tituloProfesional` varchar(100) NOT NULL,
  `nroMatricula` int(11) NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  `estado` enum('activo','inactivo') DEFAULT 'activo',
  `idper` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `veterinarios`
--

INSERT INTO `veterinarios` (`tituloProfesional`, `nroMatricula`, `especialidad`, `estado`, `idper`) VALUES
('Titular', 65331, 'Titular', 'activo', 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idper`),
  ADD KEY `fk_administrador_personas1_idx` (`idper`);

--
-- Indices de la tabla `adopciones`
--
ALTER TABLE `adopciones`
  ADD PRIMARY KEY (`ida`),
  ADD KEY `fk_adopciones_mascotas1_idx` (`idmas`),
  ADD KEY `fk_adopciones_adoptantes1_idx` (`idadop`);

--
-- Indices de la tabla `adoptantes`
--
ALTER TABLE `adoptantes`
  ADD PRIMARY KEY (`idper`),
  ADD KEY `fk_adoptantes_personas1_idx` (`idper`);

--
-- Indices de la tabla `especies`
--
ALTER TABLE `especies`
  ADD PRIMARY KEY (`ide`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`idhistorial`),
  ADD KEY `fk_historial_mascotas1_idx` (`idmas`),
  ADD KEY `fk_historial_veterinarios1_idx` (`idvete`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`idm`),
  ADD KEY `idesp` (`idesp`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`idp`);

--
-- Indices de la tabla `veterinarios`
--
ALTER TABLE `veterinarios`
  ADD PRIMARY KEY (`idper`),
  ADD KEY `fk_veterinarios_personas1_idx` (`idper`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adopciones`
--
ALTER TABLE `adopciones`
  MODIFY `ida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `especies`
--
ALTER TABLE `especies`
  MODIFY `ide` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `idhistorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `idm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `idp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `fk_administrador_personas1` FOREIGN KEY (`idper`) REFERENCES `personas` (`idp`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `adopciones`
--
ALTER TABLE `adopciones`
  ADD CONSTRAINT `fk_adopciones_adoptantes1` FOREIGN KEY (`idadop`) REFERENCES `adoptantes` (`idper`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_adopciones_mascotas1` FOREIGN KEY (`idmas`) REFERENCES `mascotas` (`idm`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `adoptantes`
--
ALTER TABLE `adoptantes`
  ADD CONSTRAINT `fk_adoptantes_personas1` FOREIGN KEY (`idper`) REFERENCES `personas` (`idp`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `fk_historial_mascotas1` FOREIGN KEY (`idmas`) REFERENCES `mascotas` (`idm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_historial_veterinarios1` FOREIGN KEY (`idvete`) REFERENCES `veterinarios` (`idper`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD CONSTRAINT `mascotas_ibfk_1` FOREIGN KEY (`idesp`) REFERENCES `especies` (`ide`);

--
-- Filtros para la tabla `veterinarios`
--
ALTER TABLE `veterinarios`
  ADD CONSTRAINT `fk_veterinarios_personas1` FOREIGN KEY (`idper`) REFERENCES `personas` (`idp`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
