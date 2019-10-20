# Reasons for this bash script failing might be that the MySQL database is not reachable.

#!/bin/bash

winpty docker run -it -p 9093:8080 -t springio/postsmicroservice