@echo off
REM Set paths to JDK and JavaFX
if defined JAVA_HOME (
    set "JDK_PATH=%JAVA_HOME%"
) else (
REM SET JDK PATH HERE IF JAVA_HOME IS NOT SET!!!!!!!!!!
    set "JDK_PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.11.9-hotspot"
)
set PATH_TO_FX=%~dp0javafx-sdk-17.0.11\lib

if not exist target\classes (
    mkdir target\classes
)

set SOURCE_LIST=temp_sources.txt
del /f /q %SOURCE_LIST% 2>nul
for /R src\main %%f in (*.java) do (
    echo %%f >> %SOURCE_LIST%
)

"%JDK_PATH%\bin\javac.exe" --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -encoding UTF-8 -d target\classes @%SOURCE_LIST%
if errorlevel 1 (
    echo Compilation failed.
    del /f /q %SOURCE_LIST%
    exit /b 1
)
"%JDK_PATH%\bin\java.exe" --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -cp target\classes skeleton.elveszlelket.Main

del /f /q %SOURCE_LIST%