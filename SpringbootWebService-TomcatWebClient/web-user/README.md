for Linux
./mvnw clean package

for Windows
.\mvnw clean package

IntelliJIdea
artifact deploy as web-user:war exploded and Application context "/"

Eclipse
change server.xml
section Host
change parameter path in Context to "/"
<Context docBase="web-user" path="/" reloadable="true" source="org.eclipse.jst.jee.server:web-user"/>
