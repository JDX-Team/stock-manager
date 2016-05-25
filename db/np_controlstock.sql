-- phpMyAdmin SQL Dump
-- version 4.5.3.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 25-05-2016 a las 14:47:42
-- Versión del servidor: 5.5.47-MariaDB
-- Versión de PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `np_controlstock`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_Acciones`
--

CREATE TABLE `ADM_Acciones` (
  `id_accion` int(11) NOT NULL,
  `accion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_Controllers`
--

CREATE TABLE `ADM_Controllers` (
  `id_controller` int(11) NOT NULL,
  `controller` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_ER_rol_funcionalidad`
--

CREATE TABLE `ADM_ER_rol_funcionalidad` (
  `id_rol` int(11) NOT NULL,
  `id_func` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_ER_usuarios_roles`
--

CREATE TABLE `ADM_ER_usuarios_roles` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ADM_ER_usuarios_roles`
--

INSERT INTO `ADM_ER_usuarios_roles` (`id_usuario`, `id_rol`) VALUES
(30, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_Funcionalidades`
--

CREATE TABLE `ADM_Funcionalidades` (
  `id_func` int(11) NOT NULL,
  `id_controller` int(11) NOT NULL,
  `id_accion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_GenericRepo`
--

CREATE TABLE `ADM_GenericRepo` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_GenericRepoRel`
--

CREATE TABLE `ADM_GenericRepoRel` (
  `id` int(11) NOT NULL,
  `id_rel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_Roles`
--

CREATE TABLE `ADM_Roles` (
  `id_rol` int(11) NOT NULL,
  `rol` varchar(50) NOT NULL,
  `fec_mod` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ADM_Roles`
--

INSERT INTO `ADM_Roles` (`id_rol`, `rol`, `fec_mod`) VALUES
(1, 'Superadmin', '0000-00-00 00:00:00'),
(2, 'Administrador', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADM_Usuarios`
--

CREATE TABLE `ADM_Usuarios` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fec_mod` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ADM_Usuarios`
--

INSERT INTO `ADM_Usuarios` (`id_usuario`, `usuario`, `password`, `fec_mod`) VALUES
(1, 'root', '$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy', '0000-00-00 00:00:00'),
(28, 'test', '$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy', '0000-00-00 00:00:00'),
(30, 'usuario_prueba', '$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy', '0000-00-00 00:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ADM_Acciones`
--
ALTER TABLE `ADM_Acciones`
  ADD PRIMARY KEY (`id_accion`);

--
-- Indices de la tabla `ADM_Controllers`
--
ALTER TABLE `ADM_Controllers`
  ADD PRIMARY KEY (`id_controller`);

--
-- Indices de la tabla `ADM_ER_rol_funcionalidad`
--
ALTER TABLE `ADM_ER_rol_funcionalidad`
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_func` (`id_func`);

--
-- Indices de la tabla `ADM_ER_usuarios_roles`
--
ALTER TABLE `ADM_ER_usuarios_roles`
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `ADM_Funcionalidades`
--
ALTER TABLE `ADM_Funcionalidades`
  ADD PRIMARY KEY (`id_func`),
  ADD KEY `id_controller` (`id_controller`),
  ADD KEY `id_accion` (`id_accion`);

--
-- Indices de la tabla `ADM_GenericRepo`
--
ALTER TABLE `ADM_GenericRepo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ADM_GenericRepoRel`
--
ALTER TABLE `ADM_GenericRepoRel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_rel` (`id_rel`);

--
-- Indices de la tabla `ADM_Roles`
--
ALTER TABLE `ADM_Roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `ADM_Usuarios`
--
ALTER TABLE `ADM_Usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ADM_Acciones`
--
ALTER TABLE `ADM_Acciones`
  MODIFY `id_accion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ADM_Controllers`
--
ALTER TABLE `ADM_Controllers`
  MODIFY `id_controller` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ADM_Funcionalidades`
--
ALTER TABLE `ADM_Funcionalidades`
  MODIFY `id_func` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ADM_GenericRepo`
--
ALTER TABLE `ADM_GenericRepo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ADM_GenericRepoRel`
--
ALTER TABLE `ADM_GenericRepoRel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ADM_Roles`
--
ALTER TABLE `ADM_Roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `ADM_Usuarios`
--
ALTER TABLE `ADM_Usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ADM_ER_rol_funcionalidad`
--
ALTER TABLE `ADM_ER_rol_funcionalidad`
  ADD CONSTRAINT `ADM_ER_rol_funcionalidad_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `ADM_Roles` (`id_rol`),
  ADD CONSTRAINT `ADM_ER_rol_funcionalidad_ibfk_2` FOREIGN KEY (`id_func`) REFERENCES `ADM_Funcionalidades` (`id_func`);

--
-- Filtros para la tabla `ADM_ER_usuarios_roles`
--
ALTER TABLE `ADM_ER_usuarios_roles`
  ADD CONSTRAINT `ADM_ER_usuarios_roles_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `ADM_Roles` (`id_rol`),
  ADD CONSTRAINT `ADM_ER_usuarios_roles_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `ADM_Usuarios` (`id_usuario`);

--
-- Filtros para la tabla `ADM_Funcionalidades`
--
ALTER TABLE `ADM_Funcionalidades`
  ADD CONSTRAINT `ADM_Funcionalidades_ibfk_1` FOREIGN KEY (`id_controller`) REFERENCES `ADM_Controllers` (`id_controller`),
  ADD CONSTRAINT `ADM_Funcionalidades_ibfk_2` FOREIGN KEY (`id_accion`) REFERENCES `ADM_Acciones` (`id_accion`);

--
-- Filtros para la tabla `ADM_GenericRepoRel`
--
ALTER TABLE `ADM_GenericRepoRel`
  ADD CONSTRAINT `ADM_GenericRepoRel_ibfk_1` FOREIGN KEY (`id_rel`) REFERENCES `ADM_GenericRepo` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
