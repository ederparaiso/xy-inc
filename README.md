API REST para busca de endereços utilizando a base dos correios (www.buscacep.com.br) construída utilizando Spring Boot, Java 8, Tomcat embedded.

Requisitos: 
- Java 8
- Apache maven

Execução:
- No diretório raíz da aplicação execute "mvn spring-boot:run".
- A aplicação será compilada e disponibilizada no endereço http://localhost:8080.

Serviços disponíveis:
- Busca de endereço por cep:
http://localhost:8080/address?zipCode=38400352

- Busca de endereço por logradouro:
http://llocalhost:8080/address/zipcode?streetName=avenida olegario maciel

Formato do retorno: json com os dados dos endereços encontrados.