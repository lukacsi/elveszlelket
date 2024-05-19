@echo off

REM Clean and create bin directory
if exist bin (
    rmdir /s /q bin
)
mkdir bin

REM Compile the project
echo Compiling the project...
%JAVAC% -d bin -sourcepath src -cp "%PATH_TO_FX%\javafx-controls.jar;%PATH_TO_FX%\javafx-fxml.jar" src\skeleton\elveszlelket\Main.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)
echo Compilation successful.

REM Run the project
echo Running the project...
%JAVA% --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -cp bin skeleton.elveszlelket.Main
if %errorlevel% neq 0 (
    echo Execution failed.
    exit /b %errorlevel%
)
echo Execution successful.
pause
