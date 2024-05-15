#!/bin/bash

# Define the source directory and the main class
SRC_DIR="src/main/java"
MAIN_CLASS="skeleton.elveszlelket.App"
TARGET_DIR="target/classes"

# Create the target directory if it doesn't exist
mkdir -p $TARGET_DIR

# Compile the Java files
echo "Compiling Java files..."
javac -d $TARGET_DIR $(find $SRC_DIR -name "*.java")

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi

# Run the main class
echo "Running the application..."
java -cp $TARGET_DIR $MAIN_CLASS
