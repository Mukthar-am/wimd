Build and installation
- The build script is preconfigured to deploy to tomcat-7/8 server, locally.
- A simple maven deploy plugin takes care of deploying it to local tomcat, provided the tomcat instance is up and running and accessible at port “8080"

- Deploy command (If deployment is for the first time): mvn clean install package tomcat7:deploy

- Redeploying post any of the changes or if the app is pre-installed: mvn clean install package tomcat7:redeploy
