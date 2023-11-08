# FACILE Back-end

FACILE is powered by Liferay 7, more precisely 7.4.3.97

## Get started (dev)

Install the Liferay's bundle with gradle task "initBundle":

```sh
./gradlew initBundle
```

This will download the associated version of Liferay bundle, in the 'bundle' directory.
Update tomcat's server.conf file to enable AJP on a port different than 8080.
Copy (and update if needed) others/p_auth_token.jsp file to tomcat-9.0.80/webapps/ROOT/
Start Liferay

```sh
cd $LIFERAY-HOME/tomcat-9.0.80
./bin/startup.sh
```

At first run, Liferay allows you to configure the database. All types of database can be used, but we recommend MariaDB.
You can also import a dump prior to the first run (see the facile-frontend project).

## Compile sources

Run the 'deploy' task on all modules, this generates a bunch of jar files, that will be automatically deployed to the Liferay bundle.
