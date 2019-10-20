# Reasons for this bash script failing might be that the MySQL database is not reachable.

#!/bin/bash

winpty docker run -it -p 3000:3000 -t canvas-drawer