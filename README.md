King Test
---------
<p>The zip contains everything in the King directory src/ 
is the source locations and target contains the war file which
is deployed in the Dockerfile. </p>
<p> I used a bean/JSON for the input of scores which goes against your design.  
Also I build a war not a jar basically I'm more comfortable deploying 
spring boot on Tomcat 9 if this is a problem let me know? </p>
<p> I've tried to save time by not doing any JUnit tests, it was just as 
easy to test as I go. I have done one big one to test the max 15 requirement. </p>
<p> I tried the library you suggested and found that the api was more difficult  
than this way. I asked the questions and the agent replied do it any way 
you can. </p>


build
-----

<p>mvn package</p>

<p>produces King-0.0.1-SNAPSHOT.war in target</p>

deploy
------
<p>docker build --tag king-test:latest .</p>
<p>docker run --name container4 -d -p 8081:8080 king-test:latest</p>


postMan
-------
<p>http://localhost:8081/King-0.0.1-SNAPSHOT/3141/login</p>
<p> produces session key</p>
<p>http://localhost:8081/King-0.0.1-SNAPSHOT/1/score?sessionkey=above</p>
<p>{ "score" : 1234}</p>
<p>http://localhost:8081/King-0.0.1-SNAPSHOT/1/highscorelist</p>


