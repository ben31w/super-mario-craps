# Compile .java files and place .class files in bin/
javac src/supermariocraps/*.java -d bin/

# Copy images to bin/ directory
mkdir bin/supermariocraps/images
cp -r src/supermariocraps/images/* bin/supermariocraps/images/

# Create executable SuperMarioCraps.jar file using CrapsDriver as entry point and 
# including all files in bin/
jar cfe SuperMarioCraps.jar supermariocraps.CrapsDriver -C bin .