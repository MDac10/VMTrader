# VMTrader
CS2212 final project.

Submitted by: Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid

## Project Description
  The source (src) folder holds all the files dedicated to this project however they are divided into gui and utils folder when implemented.
Hence the additon of package sources at the beginning of each file. 

  The logins.txt file should be added to the larger cryptoAnalyzer Maven project file, not within the deeper files. If this textfile is not in the correct folder, the LoginUI will not be able to validate usernames and passwords correctly. The logins.txt file is organized to show the accepted username and its accepted password as "username,password".

  When user first uses the platform the LoginUI will run. If an incorrect combination of a username and a password is input and the user clicks "Submit", an error window will appear and when the user selecrs "Ok" the window will close and they will have to run the program again.

  Any of the combinations in the logins.txt file will allow the user to successfully log into the trading platform. For example if the user puts "user1" as username and "1234" as password, a "login successful" window will pop up. When "Ok" is selected teh trading platform should open up.

  On the trading platform, to the right is the window where the user can input a broker name, the list of cryptocoins of interest, the strategy of choice, and if they wish, the date to look at. If date is not selected or is selected out of bounds (a day in the future) the date that will be used is the current date.

  When the user clicks "Perform Trade", DataVisualizationCreator is called and the function creatCharts will run. This will allow for the table output on the top left portion of the window and the live data visualization on the bottom left portion of the window to be conducted.

## Info Before Project Usage
### Dependencies
- Needs Windows 10 or later
- Needs MacOS, most recent version
- Eclipse should use compiler version 11
- JavaSE-1.7

### Executing the Program
The program can be run in two different ways:
 1. Starting at LoginUI.java which will open the login page and can be navigated from there.
 2. Starting at NewUI.java which will open the main trading platform window, without the need for logging in.

Once the program is running the user can follow the description above about the processes of the trading platform software...

### Terminating the program
The program can be terminated in IDE if the close/'x' button on the top bar of the window is selected.

### Packaging
The program should be run through Eclipse IDE and the folders necessary for this Maven Java project can be found in the zip file.

## Authors
@MDac10

@vitowong0

@IngridSin

@xiaoyunbonato
