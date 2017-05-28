REM	curl test script for las2peer service

REM test authentication with test user alice
curl -v GET http://localhost:8080/ocd/validate --user alice:pwalice
curl -v -X GET "http://localhost:8080/cdService/simulation/1/parameters" --user alice:pwalice
curl -v -H "Content-Type: application/json"  -X POST -d "{"graphId":"4","dynamic":"Replicator","payoffValues":["1.1","1.2","1.3","0.0"],"iterations":"20"} "http://localhost:8080/cdService/simulation"  --user alice:pwalice



PAUSE

REM more curl commandlines...
PAUSE

