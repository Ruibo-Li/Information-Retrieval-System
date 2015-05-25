a)
Ruibo Li (rl2733)
Siyao Li (sl3766)

b)
Files:
rl2733-proj1
    - readme.txt
    - Project1
        - RUN.jar
        - pom.xml
        - project1.iml
        - src
            - main
                - resourses
                    - StopWords.txt
                -java
                    - Main.java
                    - service
                        - Bing.java
                    - IRModel
                        - Naive.java
                        - NaiveTunned.java
                    - pojo
                        - SearchResult.java


c)
Our project is built as a maven project. To run the program, type the following command under the directory rl2733-proj1/Project1/:
$ mvn clean package
$ java -jar ./target/project1-1.0-SNAPSHOT-jar-with-dependencies.jar <bing account key> <precision> <query>

Note: "mvn clean package" will download necessary plugin and libraries, which might take *a few minutes*. After it says BUILD SUCCESS, jar project1-1.0-SNAPSHOT-jar-with-dependencies.jar will appear in the rl2733-proj1/Project1/target/ directory. Then run the jar file with parameters. If there is some problem when building the project, please use the back-up jar RUN.jar, which is generated in clic by ourselves. 

d)
Besides Main.java, the source code of our implementation consists of 3 package: IRModel, pojo and service.
(1)The IRModel package mainly contains the implementation of our information retrieval model. The model mainly contains the definition and implementation of the class =====, along with several useful functions for the manipulation of the vectors, which are used in the query expansion process.
(2)The pojo package contains the definition and implementation of the class SearchResult, which is used for conveniently storing and representing the search results from the Bing API.
(3)The service package contains the definition and implementation of class Bing. The class has three methods to respectively deal with the problem of searching the query using Bing API, changing the format of the search result and interacting with the user.

e)
The algorithm we use for the query expansion is the Rocchio algorithm. The parameters we select are beta = 0.75 and gamma = 0.25. We define some stop words in the file StopWords.txt, which are considered as meaningless for improving the search result and will never be added to the query. For each term, we calculate a score based on the Racchio algorithm formula. In addition, we use idf value to adjust the weight of each document. Then we select the two terms with the highest score as the new key words to be added to the query. 

f)
Bing Search Account Key:

g)

