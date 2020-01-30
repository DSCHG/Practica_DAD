# Nombre de la aplicacion web.
TOKO
## Descripción de la temática.

La aplicación que se pretende hacer consiste en una tienda online donde nuestros productos podrán ser vistos por usuarios no registrados pudiendo incluso ser añadidos al carrito de compra, además de saber antes de formalizar
la compra si hay stock o debera esperar a que la empresa reciba el producto, pero para que el usuario formalice la compra, debe primero registrarse y asi poder conseguir acceso a la pasarela de pago.

## Enumeración y descripción de entidades principales.

- Usuario(privado): Nombre, contraseña, email, lista de productos del carrito, numero de tarjeta, Lista de pedidos
- Pedido(privado): Lista de productos pagados.
- Carrito(privado): Lista de productos seleccionados por el usuario.
- Proveedor(público): Productos que se tienen o tendran de un distribuidor determinado.
- Producto(público): Objetos disponibles para su compra y el stock de los mismos

## Esquema E/R.

![Toko](umldad.PNG "uml") 

## Descripción del servicio interno.

proceso de pago, pedido.
- Proceso de pago: En el momento de ultimar la compra y el usuario esté logueado, el servicio interno se encargará de actualizar los productos que estén disponibles tras la compra y enviar un correo al usuario, informandole del pedido.

- Actualización de las bases de datos: Actualizaciones como las que se producen al añadir un producto al carrito o al realizar un pedido.

## Equipo de desarrollo.
Nombre y Apellidos | e-mail | GitHub
-------------------|----------------------|-----------------
:man_student: Darwin Chumapi Granda | :e-mail: ds.chumapi.2017@alumnos.urjc.es | https://github.com/DSCHG
:man_student: Javier Rubio López | :e-mail: j.rubiol.2017.2017@alumnos.urjc.es |https://github.com/JaviRubi
:man_student: Eva María Hoyo de la Cruz | :e-mail:  em.hoyo@alumnos.urjc.es  |  https://github.com/Eva87

### Trello
https://trello.com/b/QmLREBu5/practica-dad

