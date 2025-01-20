# Challenge-ForoHudOracle

## Descripción
Este proyecto es una API de foro desarrollada en Java utilizando Spring Boot. Permite la creación, edición y eliminación de tópicos de discusión, así como la gestión de usuarios y la autenticación mediante tokens JWT.

## Requisitos Previos
- Java 17 o superior
- Maven 3.6.3 o superior
- IDE de su preferencia (IntelliJ, Eclipse, VSCode, etc.)

## Configuración del Proyecto

### Clonar el Repositorio

      ```sh
        git clone <URL-del-repositorio>
        cd Challenge-ForoHudOracle
        ---------------------------------------
Configuración de Propiedades
Asegúrese de configurar las siguientes propiedades en application.properties o application.yml:

        api.security.expiration=12
        api.security.secret=YourSecretKey
        spring.datasource.url=jdbc:mysql://localhost:3306/foroapi
        spring.datasource.username=usuario
        spring.datasource.password=contraseña

Contribuciones
Todo comentario o sugerencia para mejora de este proyecto es bienvenido
