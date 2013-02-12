MainServer.java runs at localhost:8080, serving a local servlet concurrently with a redirection proxy.  
The redirection target is launched by FakeRemoteServer.java, running at localhost:8100.  The main 
methods for both must be launched with '/screwdriver/src/main/resources/web' as the base directory.

To access the local servlet, hit http://localhost:8080/local - this will show a simple JSP page
that displays the value of the 'action' parameter, if one is passed as part of the query (e.g. /local?action=doStuff)

To access the redirection target, hit http://localhost:8080/redirect - this will show a JSP page from 
the fake remote server.  To configure the redirection logic, edit RedirectingProxyServlet.java.