#!/bin/bash

# Set paths to JavaFX
PATH_TO_FX="$(dirname "$0")/javafx-sdk-17.0.11/lib"

# Create target/classes directory if it does not exist
if [ ! -d "target/classes" ]; then
    mkdir -p "target/classes"
fi

SOURCE_LIST="temp_sources.txt"
rm -f $SOURCE_LIST

# Find all .java files and write them to SOURCE_LIST
find src/main -name "*.java" > $SOURCE_LIST

# Compile the Java sources
"$JDK_PATH/bin/javac" --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -encoding UTF-8 -d target/classes @$SOURCE_LIST
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    rm -f $SOURCE_LIST
    exit 1
fi

# Run the Java application
"$JDK_PATH/bin/java" --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp target/classes skeleton.elveszlelket.Main

# Clean up
rm -f $SOURCE_LIST
