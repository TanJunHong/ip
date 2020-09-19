#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/duke/Duke.java ../src/main/java/duke/commands/*.java ../src/main/java/duke/data/exception/DukeException.java ../src/main/java/duke/data/task/*.java ../src/main/java/duke/data/TaskList.java ../src/main/java/duke/parser/Parser.java ../src/main/java/duke/storage/Storage.java ../src/main/java/duke/ui/Ui.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi