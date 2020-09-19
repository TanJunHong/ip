@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\duke\Duke.java ..\src\main\java\duke\commands\*.java ..\src\main\java\duke\data\exception\DukeException.java ..\src\main\java\duke\data\task\*.java ..\src\main\java\duke\data\TaskList.java ..\src\main\java\duke\parser\Parser.java ..\src\main\java\duke\storage\Storage.java ..\src\main\java\duke\ui\Ui.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
