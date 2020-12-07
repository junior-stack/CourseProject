Group0014 Conference Project
======

# Install
make sure you have the latest version of gradle installed
```
brew install gradle
```

make sure you have the correct java jdk (script runs on mac, ignore if already set up)

```
/usr/libexec/java_home -V # (you will see different versions of java on your own machine
#    13.0.1, x86_64:     "OpenJDK 13.0.1"        /Library/Java/JavaVirtualMachines/openjdk-13.0.1.jdk/Contents/Home
#    13, x86_64: "OpenJDK 13"    /Library/Java/JavaVirtualMachines/adoptopenjdk-13.jdk/Contents/Home
#    1.8.0_272, x86_64:  "AdoptOpenJDK 8"        /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home

# find out JAVA_HOME for 1.8 by running
/usr/libexec/java_home -v 1.8.0_272 # should output the following, or something similar
# /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
# set the JAVA_HOME environment variable in gradle.properties to be the output of the above!
# or, if you are confident that jdk 1.8 is the system default, do not do anything

# instead of modifying gradle.properties, you can manually set this environment variable for this bash session
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
```

```
# cleans the left over build files
gradle clean
# builds the executable jar (fat jar)
gradle jar
```
