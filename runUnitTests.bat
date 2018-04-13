del Testing/*.class
javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar Testing/*.java
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore Testing.TestLogic
pause