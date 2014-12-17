@echo off
set batDir=%~dp0
echo %batDir%
set libPath=%batDir%lib
echo %libPath%
set CLASS_PATH=
for  %%s in ( %libPath%\*.jar ) do  call :addcp %%s

goto :exeJava

:addcp
SET CLASS_PATH=%1;%CLASS_PATH%
goto :EOF

:exeJava
echo %JAVA_HOME%/bin/java  -DbasePath=%batDir%  -classpath "%CLASS_PATH%"   com.quick3.Main
%JAVA_HOME%/bin/javaw  -DbasePath=%batDir%  -classpath "%CLASS_PATH%"   com.quick3.Main