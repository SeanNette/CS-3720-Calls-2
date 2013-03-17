VER = 106
VERSION = 1.06

WEBPAGE = http://bach.istc.kobe-u.ac.jp/cream/
WEBTITLE = Cream: Constraint Programming Class Library for Java
PACKAGE = jp.ac.kobe_u.cs.cream
DOCTITLE = Cream version $(VERSION) Core API Specification
JAVADOC  = javadoc -locale en_US \
	-windowtitle '$(DOCTITLE)' -doctitle '$(DOCTITLE)' \
	-header '<em><b>$(DOCTITLE)</b></em><br>' \
	-bottom '<address><a href="$(WEBPAGE)">$(WEBTITLE)</a></address>' \
	-link http://java.sun.com/javase/6/docs/api \
	-classpath ../classes -sourcepath ../src \
	-public -d ../docs/api

all: zip

zip: jar javadoc
	rm -f cream$(VER).zip
	cd ..; zip -r build/cream$(VER).zip src classes docs \
	build/Makefile build/cream$(VER).jar build/examples.jar

jar: classes
	jar cvf cream$(VER).jar -C ../classes jp
	jar cvf examples.jar -C ../classes examples

classes:
	javac -sourcepath ../src -d ../classes ../src/jp/ac/kobe_u/cs/cream/*.java
	javac -sourcepath ../src -d ../classes ../src/examples/*.java

javadoc:
	rm -rf ../docs/api
	LANG=C $(JAVADOC) $(PACKAGE) examples

clean:
	rm -rf ../classes/* ../docs/api cream$(VER).zip cream$(VER).jar examples.jar

