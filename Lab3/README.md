# Micro-services setup 

This is a lab on how to setup multiple micro services serving a single front-end application running on docker. The servers are built with springboot framework and the Frontend has been integrated to work with JSF. 

To deploy this application you need to build and deploy each individual service by following the associated README instructions. 

Something that is lacking from the current setup is that database access is hardcoded and is not currently configured at runetime so changes to the persistence.xml is necessary if the MySQL server changes host address. 


# End Points

	192.168.99.100:3000					(canvas-drawer)
	192.168.99.100:9090					(frontend)
	192.168.99.100:9091/users			
	192.168.99.100:9092/messages
	192.168.99.100:9093/posts
