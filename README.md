# ACME CRM

### Installation Environnement Production


Serveur AWS

Serveur Tomcat
@IP : 13.38.56.240
DNS : ec2-13-38-56-240.eu-west-3.compute.amazonaws.com

Serveur Tomcat http://ec2-13-38-56-240.eu-west-3.compute.amazonaws.com:8080

# Connexion SSH :
ssh -i "EPSI2023_G1.pem" admin@ec2-13-38-56-240.eu-west-3.compute.amazonaws.com

# Nous pousserons nos packages *.war sur le serveur pour un déploiement automatisé: 
scp -i "EPSI2023_G1.pem" sample.war admin@ec2-13-38-56-240.eu-west-3.compute.amazonaws.com:/opt/tomcat/webapps


