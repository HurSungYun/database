all: jar
	mkdir -p db

parser: Parser.jj
	mkdir -p ./src/java
	javacc -OUTPUT_DIRECTORY=./src/java/ Parser.jj

class: parser
	mkdir -p class
	javac -cp ./lib/je-7.0.6.jar -d ./class ./src/*.java
	javac -cp "./lib/je-7.0.6.jar:./class" -d ./class ./src/java/* 

jar: class
	jar cvmf .manifest.txt SimpleDBMS.jar -C class/ .

run: all
	java -jar SimpleDBMS.jar

clean:
	rm -rf ./src/java
	rm -rf ./class

hardclean:
	rm -rf ./src/java
	rm -rf ./class
	rm -rf ./SimpleDBMS.jar
	rm -rf ./db
