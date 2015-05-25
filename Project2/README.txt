a)
Ruibo Li (rl2733)
Siyao Li (sl3766)

b)
Files:
rl2733-proj2
    - README.txt
    - transcript.txt
    - Project2
        - RUN.jar
        - pom.xml
        - project2.iml
        - src
            - main
                -java
                    - driver
                        - Driver.java
                    - services
                        - APIService.java
                    - part1
                        - pojo
                            - BusinessmanPojo
                            - CoachesPojo
                            - DeathPojo
                            - FilmPojo
                            - PlayersRosterPojo
                        - Extractor
                    - part2
                        - QueryProcessor.java
                    - Main.java


c)
Our project is built as a maven project. To run the program, type the following command under the directory rl2733-proj2/Project2/:
$ mvn clean package
$ java -jar ./target/project2-1.0-SNAPSHOT-jar-with-dependencies.jar -key <Freebase API key> -q <query> -t <infobox|question>
or
$ java -jar ./target/project2-1.0-SNAPSHOT-jar-with-dependencies.jar -key <Freebase API key> -f <file of queries> -t <infobox|question>
or
$ java -jar ./target/project2-1.0-SNAPSHOT-jar-with-dependencies.jar -key <Freebase API key>

Note: "mvn clean package" will download necessary plugin and libraries, which might take *a few minutes*. After it says BUILD SUCCESS, jar project2-1.0-SNAPSHOT-jar-with-dependencies.jar will appear in the rl2733-proj2/Project2/target/ directory. Then run the jar file with parameters. If there is some problem when building the project, please use the back-up jar RUN.jar, which is generated in clic by ourselves.

d)
Besides Main.java, the source code of our implementation consists of 4 package: driver, part1, part2 and services.
(1)The driver package mainly contains the implementation of getting information from part1 and part2, and print the information in the appropriate format according to the requirements. 
(2)The part1 package contains the implementation files for part1 of this project. The Extractor.java file defines the class Extractor which is used in part1 for extracting information from the Json result of Freebase API. And pojo package under part1 package contains object definitions for different types of the result.
(3)The part2 package contains the QueryProcessor class which is used for processing questions in part2 through Freebase MQLRead API. 
(4)The services package contains the definition and implementation of class APIService. The class has methods to respectively deal with the problem of searching the query using Freebase Search API, getting results from Freebase Topic API and getting answer of the query from Freebase MQLRead API.

f)
Freebase API Key:
Requests per second per user: 10

g)
For part1, we don't filter the result. For example, when we get the result of "NFL" in part1, the result contains the content in which "NFL" is considered as an author.
