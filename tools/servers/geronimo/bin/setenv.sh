GERONIMO_OPTS="$GERONIMO_OPTS -Dfile.encoding=UTF8 @java.security.manager.option@ -Djava.security.policy==@app.server.geronimo.dir@/etc/server.policy -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=512m"
