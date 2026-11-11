#!/bin/bash
rm -r compl
mkdir compl
javac *.java -d compl
cd compl
java Main
cd ..
