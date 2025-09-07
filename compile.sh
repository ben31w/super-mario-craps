#!/bin/bash

# Compile .java files and place .class files in out/
javac src/supermariocraps/*.java -d out/

# Copy images to out/ directory
mkdir out/supermariocraps/images
cp -r src/supermariocraps/images/* out/supermariocraps/images/

# Create executable SuperMarioCraps.jar file using CrapsDriver as entry point and 
# including all files in out/
jar cfe SuperMarioCraps.jar supermariocraps.CrapsDriver -C out .