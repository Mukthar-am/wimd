__*Build and installation*__
- The build script is preconfigured to deploy to tomcat-7/8 server, locally.
- A simple maven deploy plugin takes care of deploying it to local tomcat, provided the tomcat instance is up and running and accessible at port “8080"
- Deploy command (If deployment is for the first time): mvn clean install package tomcat7:deploy
- Redeploying post any of the changes or if the app is pre-installed: mvn clean install package tomcat7:redeploy
- The build script is preconfigured to deploy to tomcat-7/8 server, locally. 
- A simple maven deploy plugin takes care of deploying it to local tomcat, provided the tomcat instance is up and running and accessible at port “8080"
- Deploy command (If deployment is for the first time): mvn clean install package tomcat7:deploy
- Redeploying post any of the changes or if the app is pre-installed: mvn clean install package tomcat7:redeploy
- Project's pom.xml is using maven-tomcat plugin to have a automated build and deployment of the war far, provided the tomcat server is up and running at localhost and port 8080. 
- Use pom.xml to change the tomcat server's configuration from default to a custom configurations. 

__*Access as a web services (RestFul)*__
URL: http://localhost:8080/wimd/

__*Availability of the service/API*__
__*Location service*__
Method - PUT
URI: http://localhost:8080/wimd/drivers/11/location
Request Headers: Content-Type: application/json, Accept: application/json, car-make, car-mode, registraction, color, segment, name (String data types)


__*Find driver service*__
Method: GET
URI: http://localhost:8080/wimd/drivers?latitude=15.97161921&longitude=55.19463451&radius=101&limit=1&accuracy=0.1
Request Headers: Accept: application/json

__*Implementation tech stack*__
__Spring framework__ to develop restful services.
__Maven__ for build and automated deployment
__TestNG framework__ used as a framework for testing automation of unit tests. *Importantly*: The development was complete TDD as it was making use of automated tests and edge cases for making the code more robost and fail proof.
__tomcat__ as a simple web container to serve services
__JDK-8__ development environment
__K-D Tree__ (k-dimensional tree) for storing all the location cordinates and nearest-locatin retrival in O(1) space. 
__Distance computational class__ drived/implemneted out of a white patter submitted, converting the formulae to java code.
__Design patterns__ like, singleton, Abstract and factory patterns
__Oops SOLID principles__ like, Single responsibility principle and Open-Closed principle used at most of the places.

__*Scope of improvement/s*__
__Verticle scability:__ 
1) If I avoide recreating k-d tree is at every find-driver query and have a self balanced k-d tree, then a huge time optimisation can be achieved.
2) Only a single driver node is returned as of now, rather, if a list of drivers limiting min of 1 and a max of 10, based on the distance of each nearest-cordinate within the k-d tree's axis, then further more optimisation complete requirement/ask will be achieved. This in-turn also takes care of limiting the output to 10 or a configurable number. 


