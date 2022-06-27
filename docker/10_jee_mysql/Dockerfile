FROM jboss/wildfly:19.0.0.Final
WORKDIR /opt/jboss/wildfly
ADD ./OsebeDemo.war ./standalone/deployments/
ADD ./mysql-connector-java-8.0.18.jar ./standalone/deployments/
ADD ./standalone.xml ./standalone/configuration/

EXPOSE 8080
CMD ["./bin/standalone.sh"]

#
#
