all:
	mkdir -p ./src/java
	mkdir -p db
	mkdir -p class
	javacc -OUTPUT_DIRECTORY=./src/java/ Parser.jj
	javac -cp ./lib/je-7.0.6.jar -d ./class ./src/*.java
	javac -cp "./lib/je-7.0.6.jar:./class" -d ./class ./src/java/* 
	jar cvmf .manifest.txt SimpleDBMS.jar -C class/ .

run: all
	java -jar SimpleDBMS.jar

clean:
	rm -rf ./src/java
	rm -rf ./class
