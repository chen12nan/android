main: ./com/test/Test.java Sync.java
	javac ./com/test/Test.java
	jar cvf test.jar ./com/test/*.class
	echo "jar package finished ..."
	javac -classpath ./test.jar Sync.java
	echo "compile finish ..."
	java Sync

clean:
	rm ./com/test/*.class test.jar *.class
