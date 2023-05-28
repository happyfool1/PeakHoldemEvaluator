//package evaluate_streets;

public class Classification  implements   Constants {

    /*-  **************************************************************************
    *
    * This class contains methods that characterize the boards foe Flop, Turn, River
    * and hands ( board + hole Cards ) with made hands and draws analyzed.
    * In general, a single integer value is used to identify the type of the board 
    * or hand.   For example:
    * The Flop is evaluated as a combination of High, Middle, and Low cards.
    * So a flop with A 7 5 is HML ( High Medium Low ). 
    * There are exactly 10 combinations so a value of 0 to 9 is assigned. 
    * At the same time the hand ( hole cards + Flop cards ) is evaluated for
    * draws and made hands. Then at showdown, the hand is evaluated for best hand
    * or not. After many thousands of games simulated the percentage of the time that
    * one of the 10 HML combinations combined with draws, made hands, and winning hands
    * is calculated as a percentage. That percentage is a clue as how to play this
    * particuler Flop, hand, or draw. If you don not have a made hand, or good draw,
    * then it tells you what to expect from your opponent.
    * 
    * There are many different ways to convert a Flop into a single integer such as
    * simply adding the values of the 13 cards ( 0 to 12 ) and arraiv at a number 
    * between 0 and 36. So, same analysis as the exampled above.
    *
    * We will be experimenting with many different ways to characterize a Flop, Turn,
    * or River board and with hands. Where exactly we will end up is unknown but  
    * after all this is an experiment.
    *
    * There is a parallell project, PeakHoldemHandHistory that evaluates millions of 
    


     * This class is used to analyze thousands or millions of simulated hands.
    * Using a monti carlo methodology determine characteristics of a Hold'em 
    * 6-max no limit game. 
    * Specifically to find ways to characterize a Flop, such as HML ( High Medium Low )
    * which results in only 10 Flop types. A million or more hands are run, and for 
    * each hand run the draws and made hands and other data is analyzed and collected
    * in arrays which will be analuzed after a run is completed. 
    * We analyze each street and Showdown.
    * 
    * This is not a final implememtation. We will be experimenting contineously 
    * trying to find ways to characterize a Flop that will result in  information on
    * how best to play a hand. 
    * For example for a Flop type:
    *   It may result in a high percentage of made hands and we do not have a made hand.
    *   It may result in a low percentage of made hands and we hve have a made hand.
    *   It may result in a hiogh percentage of strong draws.
    *   It may result in a low percentage of strong draws.
    *   It may result in a a high percentage of times that the made hand is the winning
    *   hand at Showdown.
    *   And many more.
    *
    *
    *   
    * 
    * @author PEAK_
    ***************************************************************************** */



    
}
