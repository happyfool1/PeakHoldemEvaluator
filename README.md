I'm sharing this code on GitHub so that everyone can freely use it. However, I request you to credit me if you incorporate it into your own project.

This project is used to analyze thousands or millions of simulated hands.
 Using a monte-carlo methodology determine characteristics of a Hold'em 
 6-max no limit game. 
 Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )
 which results in only 10 Flop types. A million or more hands are run, and for 
 each hand run the draws and made hands and other data is analyzed and collected
 in arrays which will be analyzed after a run is completed. 
 We analyze each street and Showdown.
 
 This is not a final implementation. We will be experimenting continuously trying to find ways to characterize 
 a Flop that will result in  information on how best to play a hand. 
 For example for a Flop type:
   It may result in a high percentage of made hands and we do not have a made hand.
   It may result in a low percentage of made hands and we hve have a made hand.
   It may result in a high percentage of strong draws.
   It may result in a low percentage of strong draws.
   It may result in a a high percentage of times that the made hand is the winning
   hand at Showdown.
   See the GUIAnalyzeMany Class.

There is a companion project PeakHoldemHandHistory that analyzes over ten million Pokerstars Hand History files
to determine basically the same things and much more. It looks at things like how bet size effects how frequently a 
player will call or fold. Far too many other things to describe here. It will be released here within a couple of
months.

In support of this objective are several classes that do things like analyze the board and hole cards to determine
draws and made hands. Useful just for itself. See the GUI Class.

This code is a work in progress, almost finished. Its origins lie within the PeakHoldem 6-max Texas Hold'em product I started developing years ago. Unfortunately, due to health issues, I wasn't able to finish the product, even though it was nearly complete. PeakHoldem never saw an official launch.

Now that my health has improved, I plan to finish this project and another one that analyzes Hand History. Despite these plans, I don't intend to commercially sell either of the projects.

Through both the PeakHoldemEvaluator and PeakHoldemHandHistory, my goal has been to present unique ways of analyzing poker hands. For instance, in Hand History, I've examined how different bet sizes affect a player's response and tried to figure out the optimal bet size for different outcomes. I'm conducting this analysis using a over 10 million hands.

In PeakHoldemEvaluator, I've tried to devise ways to categorize game boards, such as HML (High, Medium, Low), aiming to provide an alternative to the conventional wet/dry type of analysis. The objective is to determine the best strategy for playing hands on the 10 Flop HML types or the 1755 possible Flops.

Many Classes have been coded as part of this project, most of which have been fully tested. I don't want my effort on PeakHoldem to go to waste. Given that I don't plan to market it, I'll make most of the code publicly available. Some modifications will be needed to separate the code from PeakHoldem.

There are multiple useful classes within this project that could aid you in your own projects, such as the deck and card classes. They're not exclusive to this project. For example, if you need to deal cards randomly, these classes can be of help.

This project also introduces some novel Classes, containing preflop ranges and editors that were utilized in PeakHoldem and later generalized. These include the HandRange, HandRangeMultiple, EditRange, and GUIEditRange classes.

The HandHistor class could be valuable, as it creates Hand History files resembling those from PokerStars, which can even be imported into Holdem Manager.

PeakHoldemEvaluator, which is part of this project, runs millions of hands, collecting information on various aspects like characterizing a Flop as HML or as one of 1755 possible Flops and assessing each flop's performance at Showdown.

Another project, PeakHoldemSimulator, emulates a Texas Hold'em 6-max no limit game. It leverages this Class to evaluate the Hero's hand in the game, amassing detailed data. For opponents, it collects information only at Showdown when his hole cards are known. This simulator is unique as it is entirely controlled by external files editable by the user.

PeakHoldemHandHistory, analyzes over 10 million Hand History files, providing a deeper level of evaluation than existing tools like Holdem Manager.

As for me, I'm an 80-year-old coder who wrote my first program in 1965, in machine language, no less. I spent 30 years working for IBM, during which I developed three compilers and contributed to the Fortran optimizer. Most of my career was in manufacturing engineering, and I was friends with the designer of the IBM PC. No one knows his name. Now retired, I continue to code to keep my mind active. I'm an old-timer learning new tricks.


