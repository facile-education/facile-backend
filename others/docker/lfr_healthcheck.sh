#!/bin/bash

http_validation=$(curl -s -o /dev/null -w "%{http_code}" https://dev-ent-gve.com/lfr/api/jsonws/user.userutils/get-user-infos?p_auth=0 --insecure)

if [[ $http_validation == 200 || $http_validation == "403" ]]
then
  exit 0
else
  exit 1
fi
