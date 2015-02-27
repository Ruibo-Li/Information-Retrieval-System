a)
Ruibo Li (rl2733)
Siyao Li (sl3766)

b)
Files:
COMSE6111
    - README.md
    - Project1
        - COMS E6111_ Project 1.pdf
        - pom.xml
        - project1.iml
        - readme.txt
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
        - target
            - MANIFEST.MF
            - classes
                - Main.class
                - StopWords.txt
                - IRModel
                    - Naive.class
                    - NaiveTunned.class
                - pojo
                    - SearchResult.class
                - service
                    - Bing.class
            - generated-sources
                - annotations

c)
Our project is built as a maven project. To run the program, type the following command under the directory COMSE6111/Project1
$ java -jar ./target/project1-1.0-SNAPSHOT-jar-with-dependencies.jar

d)
Besides Main.java, the source code of our implementation consists of 3 package: IRModel, pojo and service.
(1)The IRModel package mainly contains the implementation of our information retrieval model. The model mainly contains the definition and implementation of the class =====, along with several useful functions for the manipulation of the vectors, which are used in the query expansion process.
(2)The pojo package contains the definition and implementation of the class SearchResult, which is used for conveniently storing and representing the search results from the Bing API.
(3)The service package contains the definition and implementation of class Bing. The class has three methods to respectively deal with the problem of searching the query using Bing API, changing the format of the search result and interacting with the user.

e)
The algorithm we use for the query expansion is the Rocchio algorithm. The parameters we select are beta = 0.75 and gamma = 0.25. We define some stop words in the file StopWords.txt, which are considered as meaningless for improving the search result and will never be added to the query. For each term, we calculate a score based on the Racchio algorithm formula. In addition, we use idf value to adjust the weight of each document. Then we select the two terms with the highest score as the new key words to be added to the query. 

f)
Bing Search Account Key:
OGwFXUbDNNw/U2hhi/vhjWdahU36dTuk5V2ZUis7+VU

g)

