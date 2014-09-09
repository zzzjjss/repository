if [ -z $JAVA_HOME ]; then
	echo "the JAVA_HOME is  empty."
	exit 1;
fi

classPath=""
for  f in lib/*.jar; do 
	classPath=${classPath}${f}":"
done
scriptPath=`readlink  -f  "$0"`
scriptFolder=`dirname "$scriptPath"`
#-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=y 
${JAVA_HOME}/bin/java  -DbasePath=$scriptFolder  -classpath $classPath   com.quick3.Main
