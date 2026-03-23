# Devoir-INF222
Ce devoir porte sur la conception d’une API backend a l’aide d’une cleeroute 
				BLOG API	 	 
      Blog API est une application backend permettant de gérer un systeme complet. Elle permet de creer,lire,modifier, supprimer des articles de blog ainsi que de gerer les utilisateurs et leurs authentications  Ce projet a été devellopé en java avec le framework Spring Boot en suivant les bonnes pratiques de devellopement moderne  					
      TECHNOLOGIES UTILISÉES  		Technologie: java 	Version: 17+ 	Role: language principal  		
Technologie: Spring Boot	Version: 3.X	Role:Framework backend   		
Technologie: Gradle		Version: 8.X	Role: outil de build  		  	
Prérequis:  Avant de lancer ton projet assure toi d'avoir installé JavaJDK17 ou superieur(java --version) et Git (git --version)  un editeur de code: VS Code,IDEA ou Eclipse  					INSTALLATION ET DEMARRAGE  	  				
1-Cloner le projet  				  		
git clone https://github.com/kennedy cd blog  				
2- Configurer la base de données  		ouvrir le fichier src/main/resources. ouvre le fichier src/main/resources/applications.poperties et configure te base de données  			# Base de données H2(en memoire pour les tests)  			spring.datasource.url= jdbc:h2:mem:blogdb  			spring.datasource.driver-class-name=org.h2.Driver  			spring.datasource.username= sa  		spring.datasource.password= ton mot de passe  			  			
# ou MSQL (pour la production)  			spring.datasource.url= jdbc:mysql://localhost:3306/blog_db  	spring.datasource.username= root  		spring.datasource.password= ton_mot_de_passe  			spring.jpa.hibernate.ddl-auto= update  			spring.jpa.show-sql= true  	
3-Compiler et lancer le projet  			sur Linux/Mac:  				./gradlew bootRun  
sur Windows gradlew.bat bootRun			
4- Acceder a l'application  		une fois demarrer l'API est accesible a http://localhost:8080  			
Console H2( si base H2 activée):  			http://localhost:8080/h2-console  			  STRUCTURE DU PROJET  			blog/  				
.gradle/  	
gradle/  					
Wrapper/  					
gradle-wrapper.jar  						gradle-wrapper.properties  				src/  		
main/ 					
java/  						com/kennedy/blog/  							controller/  							model/  							repository/  							service/  							security/  							BlogApplication.java  						ressources/  							application.properties  				test/  				
.gitattributes  				.gitignore  				build.gradle  				
gradlew	gradlew.bat  				settings.gradle  				README.md  						
  	FONCTIONNALITÉS 				creer un article  					lire/afficher les articles  			lire un article unique via son ID  		modifier un article  					supprimer un article via son ID  		Rechercherun article via son ID  				API ENDPOINTS  						AUTHENTIFICATIONS  				Methode: post  Route: /api/auth/register  Description: creer un compte authentification requise : non  				
Methode: post  Route: /api/auth/login     Description: se connecter	authentification requise : non  		Exemple-Inscription:  					post/auth/register  					{  						
"nom":"kennedy"  						"email":"kennedy@exemple.com  			"password":"motdepasse123"  					}  				
Exemple-connexion:  					post/api/auth/login  					{  						"email":"kennedy@exemple.com  			"password": "motdepase123"  					}  				
reponses:  					
{  					 	"token":"eyJhbGci0iJIUzI1NiIsInR5cC:  	"type" : "bearer"  					 	  		ARTICLES 			
Methodes: GET 	Route: /api/posts  	Description: lister tout les articles 
authentification requise : non
Methode : POST  Route: /api/posts/	Description: creer un article           Authentification requise:oui  			Methode: PUT    Route: /api/posts/{id}	Description: modifier un article        Authentication requise: oui  			Méthode: DELETE Route: /api/posts/{id}  Description: supprimer un article       Authentification: oui  						Exemple-creer un article:  				POST/api/posts  							autorisation: bearer<ton_token_jwt>  	
{  							  
"titre": "Mon premierarticle"  			 "contenu": "contenu de l'article"  	 "categorie": "technologie"  							 }  								TESTS  						 sur Linux/Mac  						 ./gradlew test  						  sur Windows  						  gradlew.bat test  						 Les rapports sont generés dans build/reports/tests/test/index
