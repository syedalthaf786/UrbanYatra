@echo off
if not exist out mkdir out
javac -d out src\com\ubranyatra\app\Main.java src\com\ubranyatra\model\*.java src\com\ubranyatra\service\*.java src\com\ubranyatra\ui\*.java src\com\ubranyatra\util\*.java
if errorlevel 1 exit /b 1
java -cp out com.ubranyatra.app.Main
