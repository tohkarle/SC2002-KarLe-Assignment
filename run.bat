@echo off

REM Compile Java code
echo Compiling program...
javac cams/Main.java

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilation successful

    REM Run Java program
    java cams/Main
) else (
    echo Compilation failed
    echo Note that JDK must be installed
)
