
--INSERT USUARIOS
INSERT INTO ADM_Roles (id_rol, rol, fec_mod) VALUES (1, 'Superadmin', CAST('1900-01-01 00:00:06.673' AS DateTime))
INSERT INTO ADM_Roles (id_rol, rol, fec_mod) VALUES (2, 'Administrador', CAST('1900-01-01 00:00:06.670' AS DateTime))

INSERT INTO ADM_Usuarios (id_usuario, usuario, fec_mod) VALUES (1, 'root', CAST('2015-12-23 00:00:00.000' AS DateTime))
INSERT INTO ADM_Usuarios (id_usuario, usuario, fec_mod) VALUES (28, 'test', CAST('2012-06-18 22:34:09.000' AS DateTime))
INSERT INTO ADM_Usuarios (id_usuario, usuario, fec_mod) VALUES (30, 'usuario_prueba', CAST('2015-12-23 16:54:50.493' AS DateTime))

INSERT INTO ADM_ER_usuarios_roles (id_usuario, id_rol) VALUES (30, 1)


--INSERT VISTAS y funcionalidades
INSERT INTO ADM_Controllers (id_controller, controller) VALUES (0, 'controller0')
INSERT INTO ADM_Controllers (id_controller, controller) VALUES (1, 'controller1')

INSERT INTO ADM_Acciones (id_accion, accion) VALUES (0, 'accion0')
INSERT INTO ADM_Acciones (id_accion, accion) VALUES (1, 'accion1')


INSERT INTO ADM_Funcionalidades (id_func, id_controller, id_accion) VALUES (0, 0, 0)
INSERT INTO ADM_Funcionalidades (id_func, id_controller, id_accion) VALUES (1, 1, 0)



