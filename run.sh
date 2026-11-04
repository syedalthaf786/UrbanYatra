#!/bin/sh
set -e
mkdir -p out
javac -d out src/com/ubranyatra/app/Main.java src/com/ubranyatra/model/*.java src/com/ubranyatra/service/*.java src/com/ubranyatra/ui/*.java src/com/ubranyatra/util/*.java
java -cp out com.ubranyatra.app.Main
