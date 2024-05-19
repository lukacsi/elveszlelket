@echo off
REM Set paths to JDK and JavaFX
set JDK_PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.11.9-hotspot
set PATH_TO_FX=%~dp0javafx-sdk-17.0.11\lib

if not exist target\classes (
    mkdir target\classes
)

setlocal enabledelayedexpansion
set SOURCES=
for /R src\main %%f in (*.java) do (
    set SOURCES=!SOURCES! %%f
)
"%JDK_PATH%\bin\javac.exe" --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -encoding UTF-8 -d target\classes %SOURCES%

if errorlevel 1 (
    echo Compilation failed.
    exit /b 1
)

"%JDK_PATH%\bin\java.exe" --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -cp target\classes skeleton.elveszlelket.Main
