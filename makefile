JC = javac
.SUFFIXES: .java .class

run: classes 
	java MEMGEM

.java.class:
	$(JC) $*.java

CLASSES = \
	FlashEngine.java \
	Deck.java \
	Question.java \
	MEMGEMEvent.java \
	MEMGEM.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *.swp
