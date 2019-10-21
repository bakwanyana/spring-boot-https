# spring-boot-https
SSL (https) single authentication example using Spring Boot microservices as client and server.
Based on Udemy course: https://www.udemy.com/course/ssl-simplified/learn/lecture/6842254#overview

## Get it running
### Generate certificate / keyStore / trustStore
#### Server key store
Generate a keystore with password "P@ssw0rd" that has a JKS cert called "self-signed-cert" that will be valid for 31 days 
* keytool -genkey -keyalg RSA -alias self-signed-cert -keystore keystore -storepass P@ssw0rd -validity 31 -keysize 2048

Export the certificate to file "self-signed-cert.cer" (as you would do when distributing it to clients)
* keytool -export -keystore keystore -alias self-signed-cert -file self-signed-cert.cer

#### Client trust store
Create a new trust store that will contain the exported cert from above (typically provided by your service provider)
* keytool -import -file self-signed-cert.cer -alias self-signed-cert -keystore trust-store

### Run Configurations
#### Server
The server application.properties is already setup to point to server/src/main/resources/keystore/keystore. All you have to do is paste the keystore generated in the server/src/main/resources/keystore/ folder.

You can then run the server with 
* mvn spring-boot:run

You can test via a brower by navigating to: https://localhost:8445/hello (you may have to accept an untrusted cert since it is a self-signed cert)

#### Client
You have options when it comes to getting the client running. 
* add JVM arguments to your run configuration in the IDE: -Djavax.net.ssl.trustStore=<pathToYourTrustStore> -Djavax.net.ssl.trustStorePassword=<yourTrustStorePassword>
* import self-signed-cert.cer into Java's cacert keystore	

You can run the client with
* mvn spring-boot:run

At this point, you should be able to hit http://localhost:8080/hello. This will in turn call the server using the truststore cert