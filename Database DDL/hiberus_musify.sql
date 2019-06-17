-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-06-2019 a las 21:16:17
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hiberus_musify`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artist`
--

CREATE TABLE `artist` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `artist`
--

INSERT INTO `artist` (`ID`, `Name`, `Year`) VALUES
(1, 'The Aristocrats', 1997),
(2, 'Modern Jazz Quartet', 1960),
(3, 'Pantera', 1992),
(5, 'Lamb of God', 1995),
(6, 'Jackson Five', 1977),
(7, 'Pink Floyd', 1965),
(978, 'Metallica', 1987),
(4567, 'The Who', 1968),
(7367, 'Animals As Leaders', 1987);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artists_related`
--

CREATE TABLE `artists_related` (
  `Artist` bigint(20) NOT NULL,
  `Related` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `artists_related`
--

INSERT INTO `artists_related` (`Artist`, `Related`) VALUES
(1, 7),
(2, 1),
(5, 3),
(7367, 3),
(7367, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `people`
--

CREATE TABLE `people` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Years` int(11) NOT NULL,
  `ID_Artist` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `people`
--

INSERT INTO `people` (`ID`, `Name`, `Years`, `ID_Artist`) VALUES
(1, 'Guthrie Govan', 45, 1),
(2, 'David Burbeck', 60, 2),
(121, 'Mark Morton', 45, 5),
(123, 'Chris Adler', 50, 5),
(5555, 'James Brown', 70, 5),
(7367, 'James Brown', 75, NULL),
(9067, 'Jon Gomm', 38, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `styles`
--

CREATE TABLE `styles` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(100) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `styles`
--

INSERT INTO `styles` (`ID`, `Name`) VALUES
(0, 'Metal'),
(1, 'Jazz'),
(2, 'Progresivo'),
(4, 'Pop'),
(5, 'Groove Metal'),
(6, 'Funk'),
(7, 'Soul'),
(8, 'Psychedelic Rock'),
(255, 'Djent'),
(2783, 'Thrash Metal'),
(2793, 'Rock'),
(4567, 'Rock And Roll'),
(9738, 'Progressive Metal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `style_artist`
--

CREATE TABLE `style_artist` (
  `Style` bigint(20) NOT NULL,
  `Artist` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `style_artist`
--

INSERT INTO `style_artist` (`Style`, `Artist`) VALUES
(0, 3),
(0, 5),
(0, 978),
(0, 7367),
(1, 1),
(1, 2),
(2, 1),
(4, 6),
(5, 5),
(6, 6),
(7, 6),
(8, 7),
(255, 7367),
(2783, 978),
(2793, 978),
(4567, 4567),
(9738, 7367);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `artists_related`
--
ALTER TABLE `artists_related`
  ADD PRIMARY KEY (`Artist`,`Related`),
  ADD KEY `Artist` (`Artist`,`Related`),
  ADD KEY `fk_related_artist` (`Related`);

--
-- Indices de la tabla `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Artist` (`ID_Artist`);

--
-- Indices de la tabla `styles`
--
ALTER TABLE `styles`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `style_artist`
--
ALTER TABLE `style_artist`
  ADD PRIMARY KEY (`Style`,`Artist`),
  ADD KEY `Style` (`Style`,`Artist`) USING BTREE,
  ADD KEY `artist_fk` (`Artist`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artists_related`
--
ALTER TABLE `artists_related`
  ADD CONSTRAINT `fk_id_artist` FOREIGN KEY (`Artist`) REFERENCES `artist` (`ID`),
  ADD CONSTRAINT `fk_related_artist` FOREIGN KEY (`Related`) REFERENCES `artist` (`ID`);

--
-- Filtros para la tabla `people`
--
ALTER TABLE `people`
  ADD CONSTRAINT `fk_artist` FOREIGN KEY (`ID_Artist`) REFERENCES `artist` (`ID`);

--
-- Filtros para la tabla `style_artist`
--
ALTER TABLE `style_artist`
  ADD CONSTRAINT `artist_fk` FOREIGN KEY (`Artist`) REFERENCES `artist` (`ID`),
  ADD CONSTRAINT `style_fk` FOREIGN KEY (`Style`) REFERENCES `styles` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
