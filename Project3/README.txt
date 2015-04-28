a.
Ruibo Li (rl2733)
Siyao Li (sl3766)

b.
Files:
rl2733-proj3
    - README.txt
    - example-run.txt
    - INTEGRATED-DATASET.csv
    - Project3
        - RUN.jar
        - pom.xml
        - project3.iml
        - src
            - main
                -java
                    - Main.java
                    - apriori
                        - FrequentItems.java
                        - ItemSet.java
                        - WordID.java
                    - postProcess
                        - Filter.java

c.
(a) 
    We generate our INTEGRATED-DATASET file from the data set "Vehicle Classification Counts (2012-2013)". Each row in the original dataset is a daily traffic record over the day (24h) for a certain type of vehicle (bus, commercial, taxi, etc.) at a certain location (road, avenue, etc). 
(b)
    1. 
        We generate the file using a python script.
    2. 
        We removed the following columns from the original dataset: 'ID', 'Segment ID', 'Date'. Because these columns are considered as meaningless for the rules we want to find.
    3. 
        To avoid the big amount of meaningless rules, we combined different statistical columns from the original dataset in the following way:
        In the original dataset, each field represents 1-hour interval, and each row contains 24 such fields (which is a record for a day). We divide the 24 hours equally into 8 time slots, each of which are 3 hours. And for each time slots, the counting number equals to the sum of values corresponding to its three hours in the original dataset. And also we assign the counting numbers into different slots( or intervals). Some cells in the original data sets are empty. For those cells, we just ignore it and did not generate any pre-processed item.
    4.
        The format for the pre-processed items is: [time slot]-[traffic slots]*[size of interval]. For example, we have the item "2-1*50" with car type "school bus" in our data set, which indicates the amount of traffic for school bus at 6AM to 9AM is between 50 and 100. The number "2" in the item determines the time slots (2*3)AM to (2*3+3)AM. And the number "1" in the item means the traffic is between 1*50 and 1*50+50. 
(c)
    We would like to analysis the traffic feature and trend for different type of vehicles in different time of a day. For example, we want to know how does the amount of traffic look like over a day for school bus or commercial vehicles or taxis. Those information is very important for planning and managing the traffic for a city. For example, if we find in a certain time slots, the traffic of school bus is very high, then the mayor of the city may plan a special line for school bus at the time slots to ensure the safety and timing of school bus. The interesting associate rules in our output are expected in this pattern: [trafficInfo, trafficInfo,...] => [vehicle type].         

d.
Our project is built as a maven project. To run the program, type the following command under the directory rl2733-proj3/Project3/:
$ mvn clean package
$ java -jar ./target/project3-1.0-SNAPSHOT-jar-with-dependencies.jar <csv file> <min_sup> <min_conf>

Note: "mvn clean package" will download necessary plugin and libraries, which might take *a few minutes*. After it says BUILD SUCCESS, jar project3-1.0-SNAPSHOT-jar-with-dependencies.jar will appear in the rl2733-proj1/Project3/target/ directory. Then run the jar file with parameters. If there is some problem when building the project, please use the back-up jar RUN.jar, which is generated in clic by ourselves.

e.
We followed the algorithm mentioned in the paper, section 2.1, which consists of join step and prune step. 


f.
$ java -jar ./target/project3-1.0-SNAPSHOT-jar-with-dependencies.jar INTEGRATED-DATASET.csv 0.01 0.4

We filtered the result in the output.txt to find those associate rules with the pattern: [trafficInfo, trafficInfo,...] => [vehicle type].
And the following is the what we find after filtering:
[4-0*50, 1-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=82.05128205128204%, Supp=1%)
[4-0*50, 0-0*50, 1-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=82.05128205128204%, Supp=1%)
[4-0*50, 0-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=80.0%, Supp=1%)
[4-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=77.27272727272727%, Supp=1%)
[1-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=72.0%, Supp=1%)
[0-0*50, 1-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=72.0%, Supp=1%)
[0-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=70.58823529411765%, Supp=1%)
[2-1*50, 3-0*50]=> [School Bus] (Conf=67.85714285714286%, Supp=1%)
[1-2*50]=> [Auto] (Conf=67.27272727272727%, Supp=1%)
[0-2*50]=> [Auto] (Conf=67.1875%, Supp=1%)
[4-0*50, 0-0*50, 1-0*50, 2-1*50]=> [School Bus] (Conf=66.66666666666666%, Supp=1%)
[4-0*50, 0-0*50, 2-1*50]=> [School Bus] (Conf=65.3061224489796%, Supp=1%)
[4-0*50, 1-0*50, 2-1*50]=> [School Bus] (Conf=64.0%, Supp=1%)
[7-0*50, 3-1*50]=> [Commercial] (Conf=62.96296296296296%, Supp=1%)
[0-1*50]=> [Auto] (Conf=62.16216216216216%, Supp=1%)
[7-0*50, 4-1*50]=> [Commercial] (Conf=61.40350877192983%, Supp=1%)
[6-0*50, 3-1*50]=> [Commercial] (Conf=59.70149253731343%, Supp=1%)
[6-0*50, 4-1*50]=> [Commercial] (Conf=59.154929577464785%, Supp=1%)
[4-1*50, 5-1*50]=> [Commercial] (Conf=56.060606060606055%, Supp=1%)
[4-0*50, 2-1*50]=> [School Bus] (Conf=53.96825396825397%, Supp=1%)
[1-1*50]=> [Auto] (Conf=48.837209302325576%, Supp=1%)
[6-0*50, 5-1*50]=> [Commercial] (Conf=47.45762711864407%, Supp=1%)
[6-0*50, 7-0*50, 5-1*50]=> [Commercial] (Conf=47.05882352941176%, Supp=1%)
[7-1*50]=> [Taxis] (Conf=46.57534246575342%, Supp=1%)
[3-2*50]=> [Commercial] (Conf=46.15384615384615%, Supp=1%)
[4-1*50, 3-1*50]=> [Commercial] (Conf=45.744680851063826%, Supp=1%)
[2-0*50, 3-1*50]=> [Commercial] (Conf=44.5945945945946%, Supp=1%)
[7-0*50, 5-1*50]=> [Commercial] (Conf=41.964285714285715%, Supp=1%)
[3-1*50]=> [Commercial] (Conf=41.31736526946108%, Supp=2%)
[4-1*50]=> [Commercial] (Conf=40.49079754601227%, Supp=2%)

And we select the rule for each vehicle with highest confidence:
[4-0*50, 1-0*50, 2-1*50, 3-0*50]=> [School Bus] (Conf=82.05128205128204%, Supp=1%)
[1-2*50]=> [Auto] (Conf=67.27272727272727%, Supp=1%)
[7-0*50, 3-1*50]=> [Commercial] (Conf=62.96296296296296%, Supp=1%)
[7-1*50]=> [Taxis] (Conf=46.57534246575342%, Supp=1%)
Here we got the traffic pattern for 4 type of vehicles.
From the rules, we could inference that:
For school bus, the highest traffic is from 6AM to 9AM (50-100 per road), and the traffic remains from 9AM to 3PM (0-50 per road). So it is better to plan a special line for school bus from 6AM to 9AM.
For commercial vehicles, its traffic is high from 9AM to 12PM (50-100 per road) as well as 9PM to 12AM (0-50 per road). These time slots are the peek hours for going to work and going back home. So the traffic of commercial vehicles are high in response to the peek hours.
For taxis, its traffic is high from 9PM to 12AM (50-100 per road). I suspect that at the night, people are likely to hangout with friends commuting by taxis.

g

