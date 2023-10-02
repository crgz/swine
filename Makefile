
TOOLS_DIR = $(HOME)/Public
MAVEN_BASE_URL = https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries
JDK_BASE_URL = https://download.java.net/java/GA/jdk21/fd2272bbf8e04c3dbaee13770090416c/35/GPL/

#
# OS compatibility
#
OS := $(shell uname -s)
ifeq ($(OS),Linux)
JDK_FILE = openjdk-21_linux-x64_bin.tar.gz
MAVEN_FILE = apache-maven-3.6.3-bin.tar.gz
else ifeq ($(OS),Darwin)
JDK_FILE = openjdk-21_macos-x64_bin.tar.gz
MAVEN_FILE = TBD
else
$(error Unsupported operating system: $(OS))
endif

all: test

JAVA_HOME=$(TOOLS_DIR)/jdk-21/
test: maven
	JAVA_HOME=$(JAVA_HOME) ~/Public/apache-maven-3.6.3/bin/mvn test

.PHONY: maven
MAVEN = $(TOOLS_DIR)/apache-maven-3.6.3/bin/mvn
maven: java $(MAVEN)
$(MAVEN):  $(HOME)/Downloads/$(MAVEN_FILE)
	pv $^ | tar -xzC $(TOOLS_DIR)
	@touch $@ # Avoid rule re-evaluation since unzipping preserve file timestamps

$(HOME)/Downloads/$(MAVEN_FILE):
	mkdir -p $(@D)
	wget "${MAVEN_BASE_URL}/${MAVEN_FILE}" -P $(@D)

.PHONY: java
JAVA = $(TOOLS_DIR)/jdk-21/bin/java
java: $(JAVA)

$(JAVA):  $(HOME)/Downloads/$(JDK_FILE)
	pv $^ | tar -xzC $(TOOLS_DIR)
	@touch $@ # Avoid rule re-evaluation since unzipping preserve file timestamps

$(HOME)/Downloads/$(JDK_FILE):
	mkdir -p $(@D)
	wget "${JDK_BASE_URL}/${JDK_FILE}" -P $(@D)
