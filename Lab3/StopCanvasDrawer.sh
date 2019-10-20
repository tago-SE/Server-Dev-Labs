# Reasons for this bash script failing might be that the MySQL database is not reachable.

#!/bin/bash

imagename='canvas-drawer'
winpty docker stop $(docker ps | awk '{split($2,image,":"); print $1, image[1]}' | awk -v image=$imagename '$2 == image {print $1}')
