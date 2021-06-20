# SoccerElo
Advanced Queries for European Soccer  
Database project using data from clubelo.com, create more advanced/useful queries related to club/nation/league elo.
  
This project will allow you to better understand European Football, come up with interesting and cool statistics about the game over the decades it has been played, and allow you to quickly look up relational information across teams/time instead of using a simple key-value system like www.clubelo.com had implemented. 

All date queries should be of the form YYYY-MM-DD.

This project includes:  
A script to pull the dataset from www.clubelo.com by using their API  
A series of JDBC calls to a locally hosted MySQL server to populate a local database  
A series of JDBC calls and SQL queries to request information from our database  
A command-line interface for the user when they run our program, which will allow them to run the various queries we created, with pretty-printed output  

Feature Set (List of Queries):  
  All team names listed in the database.  
  All team elos for a specified date.  
  All entries throughout history for a team.  
  Top 32 teams on a specified date (roughly UEFA qualification).  
  Current elo of a team.  
  Maximum elo that a team has ever had.  
  Minimum elo that a team has ever had.  
  Best team (highest elo) on a specific date.  
  All time best X teams. A single club will only show up once.  
  Elo change for a team between two dates, or over a given year.  
  Top 20 most improved teams over a given month and year.  
  Top 20 most improved teams over a given year.  
  Biggest upset (loss of elo) for a team.  

Software used for project:  
JDBC, SQL, Maven, Lombok, Netbeans, Git.  

Project Context:  
This website: www.clubelo.com was created by another football fan who has painstakingly scraped and assimilated data from a variety of sources to create one database of the ‘elo’ ratings of all European Football Clubs for the last 50+ years.
The idea behind ‘elo ratings’ is to act as a statistically sound identifier for how “good” a team is. Every single team in the European Football Association has an elo rating, and teams with higher elo ratings are statistically better than teams with low elo ratings. Whenever two football clubs play each other in a match, they both walk away with a different elo rating after the match is over -- with elo points being transferred from the losing team to the winning team. However, it is not a static amount of points that are transferred, but instead the amount of points that are transferred is determined by a composition of variables including but not limited to: the elo scores of the teams before the match (a low ranking team pulling off an “upset” by winning against a high ranking team will get a lot more points than the other way around), the difference in goals, home advantage, etc. 

User Installation:  
Prerequisites  
Make sure you have MySQL installed and running.  
Ensure that Lombok and MySQLConnector libraries compile correctly on command line or through your IDE.  
There are known issues with NetBeans and Lombok integration. If possible, use IntelliJ.  
Connecting to Local Database  
Open the project in an IDE or run it headless from Terminal.  
Edit the JDBC url, username, and password strings in Main.java according to your local MySQL server.  
Run Main.java  
When you first run it, it will ask you if you want to update your statistics. Say yes, and wait several hours for the data to be pulled down from the website and populated into your local MySQL database.  
Finally, the command-line will loop and wait for you to enter queries. The queries will all be listed when you enter “help”, and other inputs like “currentelo”, “teams”, etc will allow you to execute the related SQL queries and the results will be printed onto the screen.  
Enter “quit” to exit the program.  

Detailed Description of Internals of Program:  

External Tools  
Lombok - Used to reduce boilerplate and allow for focus on SQL queries instead of pesky Java details.  
MySQLConnector - Used to connect to the MySQL database and execute queries/batch statements.  
Maven - Used a build tool to simplify the usage of Lombok and MySQL Connector libraries.  

Important Classes  
Main.java - Developer sets up database connection information, including username and password for local database. Calls CLI to handle user interaction.  

CLI.java - Polls the user whether they want to update the local database, and calls DBUpdater if needed. Then, polls the user for actions to execute. These actions are then executed by the DAO, the resulting rows that are returned through the JDBC call are converted into pojos via custom-written converters, and finally, the resulting data is displayed to the user. If there was some error with the query, a (hopefully) helpful error message will be shown.  

DBUpdater.java - Pulls current information from the public www.clubelo.com API. After comparing this data to the local database information, the local database is updated through batch insert command and deletion of out-of-date entries.  

EloDAO.java - The format for each query is listed at the top as a raw String. This allows queries to be nested, if needed, but the developer must be careful to format the SQL query correctly with String.format. Most queries are immediately converted to the local EloEntry POJO, with a few exceptions that are handled by other conversion methods.  

EloEntry.java - POJO for database entries in the ClubEloEntry table. Holds the entryId, club name, rank, elo, start date, end date, country, and level of play. Lombok handles the Getters, Setters, Constructors, and Builder.  

ResultSetConverter.java - Converts MySQL ResultSet objects into designated objects. Usually, this will be the EloEntry POJO, but also has methods to convert a simple COUNT(*) result to an integer, convert to an EloChange object, and any other relevant conversions.  

NOTE: ROOM FOR EXPANSION  
we can potentially use current elo to create new matches and predict game outcomes based off factors such as home field advantage. Can also add a little randomness to this so we can almost simulate games and not completely know the outcome
