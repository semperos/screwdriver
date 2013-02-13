MainServer.java runs at localhost:8000, serving a local servlet concurrently with a redirection proxy.  
The redirection target is launched by FakeRemoteServer.java, running at localhost:8100.  The main 
methods for both must be launched with '/screwdriver/src/main/webapp' as the base directory.

To access the local servlet, hit http://localhost:8000/local - this will show a simple JSP page
that displays the value of the 'action' parameter, if one is passed as part of the query (e.g. /local?action=doStuff)

To access the redirection target, hit http://localhost:8000/redirect - this will show a JSP page from 
the fake remote server.  To configure the redirection logic, edit RedirectingProxyServlet.java.

To access this README file statically, hit http://localhost:8000/static/README.txt