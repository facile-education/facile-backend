# Nero 4.0.0 - LFR7 upgrade

ENT Nero powered by Liferay7

## Get started (dev)

Pull the repository
Install the Liferay's bundle "./gradlew initBundle"
Update tomcat server conf to enable AJP
Start Liferay and configure database
Import dump or create default roles before synchronising users and orgs
Copy (and update if needed) others/p_auth_token.jsp file to tomcat/webapps/ROOT/
Update instance configuration (host, landing and logout)
