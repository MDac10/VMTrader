# VMTrader
CS2212 final project.

The source (src) folder holds all the files dedicated to this project however they are divided into gui and utils folder when implemented.
Hence the additon of package sources at the beginning of each file. 

The logins.txt file should be added to the larger cryptoAnalyzer Maven project file, not within the deeper files. If this textfile is not in the correct folder, the LoginUI will not be able to validate usernames and passwords correctly.

NewUI is the main class that the user interacts with. They are able to input their name, interested coins, and select a strategy from the drop-down menu. Then can add and delete rows as they wish, as well as select a date to perform trades on.
