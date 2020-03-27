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


## *Descripción del servicio interno.*

proceso de pago, pedido.
**- Proceso de pago: En el momento de ultimar la compra y el usuario esta logueado, el servicio interno envia un correo al usuario, informandole del pedido. Con una factura en pdf.**

## Equipo de desarrollo.
Nombre y Apellidos | e-mail | GitHub
-------------------|----------------------|-----------------
:man_student: Darwin Chumapi Granda | :e-mail: ds.chumapi.2017@alumnos.urjc.es | https://github.com/DSCHG
:man_student: Javier Rubio López | :e-mail: j.rubiol.2017.2017@alumnos.urjc.es |https://github.com/JaviRubi
:man_student: Eva María Hoyo de la Cruz | :e-mail:  em.hoyo@alumnos.urjc.es  |  https://github.com/Eva87

### Trello
https://trello.com/b/QmLREBu5/practica-dad

## *Diagrama vistas*

![Toko](diagramavistas.PNG "uml")


## *Descripción de las vistas*

- Común a todas las vistas:
**Las paginas tienen implementada seguridad CSRF de modo que si alguien intenta suplantarnos, no le llegue la informacion.**
**En todas las paginas ha sido incluido un boton para entrar a la identificacion de usuario, y para salir de la misma**
En todas las vistas se ve una barra de navegación arriba la cual está compuesta por una serie de botones y un buscador con su botón asociado.
Se ve el logotipo de la empresa (toko), el cual te lleva al index al pulsarlo desde cualquier página.
En orden de izquierda a derecha se ven los botones, y la vista a la que te llevan: Inicio(index), Contacto(contacto), crear usuario(crud), crear proveedor(crudproveedor), crear producto(crudproducto), un icono de un carrito(carrito).  y el botón de búsqueda(crudbusqueda). 
Y al pie de la vista se puede observar el equipo de desarrollo.

- Carrito:
Posee un botón para poder formalizar el pedido y que este sea almacenado en la base de datos.
El carrito esta diseñado para poder introducir articulos de la base de datos en el mismo para luego poder comprarlo mas adelante.

- InfoPedido:
**Para entrar en esta vista previamente se ha debido pasar por el login.**
Te muestra la información relativa al pedido

- contacto:
Contiene un texto que indica los datos de contacto de la empresa.

- crud:
Se permite incluir el nombre, el correo y la contraseña del usuario para que se almacene en la base de datos.
Es la pagina que permite incluir usuarios a la base de datos.

- crudbusqueda:
Si al usar el buscador que está en la barra de navegación no se pasa ningún parámetro, te muestra todo el contenido de productos, en caso de pasar parámetros, te busca el producto que coincida en nombre con lo pasado.
Si precisas buscar un articulo es mas sencillo siempre usar un buscador que navegar entre multiples archivos para ver si la empresa lo tiene disponible.

- crudproducto:
**Para entrar en esta vista previamente se ha debido pasar por el login y solo en caso de que sea administrador.**
Se puede introducir el nombre, el precio y el id_proveedor, y se almacena en la base de datos de productos, incluyendo la referencia al proveedor que lo vende
Esta vista permite incluir en la base de datos productos.

- crudproveedor:
**Para entrar en esta vista previamente se ha debido pasar por el login y solo en caso de que sea administrador.**
Se puede introducir el nombre, el producto y el precio, y se almacena en la base de datos de proveedores y de productos.
Se permite incluir proveedores en la base de datos.

- index:
Tiene un botón para terminar la navegación por la aplicación.
Es la pagina principal donde se ven al iniciar la lista de los productos incluidos en la base de datos. 

- mostrarPedidos:
**Para entrar en esta vista previamente se ha debido pasar por el login.**
Te muestra los pedidos que han sido realizados

- vistaProducto: 
Muestra el producto que se haya seleccionado previamente y el precio del mismo.

## Modelo de controladores

![Toko](controlleruml.png "uml")

## *Modelo E/R*

![Toko](e-r.png "uml")

## *Esquema Clases.*

![Toko](umldad2.png "uml") 

## *Capturas.*

- Login
![Toko](login.PNG "uml")
- Login error
![Toko](loginerror.PNG "uml")
- Busqueda de productos
![Toko](busqueda.PNG "uml")
- Carro 
![Toko](carrito.PNG "uml")
- Indice
![Toko](index.PNG "uml")
- Proveedor
![Toko](proveedor.PNG "uml")
- Producto
![Toko](producto1.PNG "uml")
![Toko](producto2.PNG "uml")
- Ver producto
![Toko](verproducto.PNG "uml")
- Ver pedido
![Toko](verpedido.PNG "uml")
- Ver informacíon del pedido
![Toko](verinfopedido.PNG "uml")
-Usuarios
![Toko](crud.PNG "uml")
-Contacto
![Toko](contacto.PNG "uml")
## Descripción del despliegue de la aplicación.
1. Creación de una maquina virtual en virtual box y instalación del sistema operativo Ubuntu, para facilitar este paso a los usuarios pueden seguir este [enlace][https://www.geeknetic.es/Noticia/17411/Como-usar-VirtualBox-para-crear-una-maquina-virtual.html]
3. Instalación de java 1.8 en el sistema operativo ubuntu utilizando el comando~~~$ sudo apt install openjdk-8-jdk~~~
4. Instalacion de mysql, configuración y creación de un usuario **test** para la aplicación.
5. Descarga del entorno de desarrollo spring tools 4.
6. Descarga del proyecto alojado en github y importado a el entorno de desarrollo.
7. Inicialización de la primera aplicación que es la interfaz de usuario.
8. Inicializacion de la segunda aplicación que representara nuestro servicio interno.
9. Apertura de un browser para poder acceder a la aplicacion a traves de la ruta "https://localhost:8443". 
