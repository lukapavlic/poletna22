FROM jboss/wildfly:16.0.0.Final
WORKDIR /opt/jboss/wildfly
ADD ./sampleProject.war ./standalone/deployments/
ADD ./standalone.xml ./standalone/configuration/

EXPOSE 8080
CMD ["./bin/standalone.sh"]

#
#
