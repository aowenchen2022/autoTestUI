isDebug=$1


if [ "$isDebug" == "debug" ]; then
mvn clean  integration-test -Dmaven.surefire.debug 
else
mvn clean integration-test
fi


