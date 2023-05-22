import java.math.BigDecimal;

public class CallBetOrRaise {
    public static void bet(int player, BigDecimal amount) {
        BigDecimal stack = EvalData.stackBD[player];

        if (stack.compareTo(amount) < 0) {
            System.out.println("Player " + player + " does not have enough chips to bet " + amount);
            return;
        }

        EvalData.stackBD[player] = stack.subtract(amount);
        EvalData.moneyInBD[player] = EvalData.moneyInBD[player].add(amount);
        EvalData.betNowBD = EvalData.betNowBD.add(amount);
        EvalData.potBD = EvalData.potBD.add(amount);
    }

    public static void call(int player) {
        BigDecimal requiredCallAmount = EvalData.betNowBD.subtract(EvalData.moneyInBD[player]);
        BigDecimal stack = EvalData.stackBD[player];

        if (stack.compareTo(requiredCallAmount) < 0) {
            System.out.println("Player " + player + " does not have enough chips to call");
            return;
        }

        EvalData.stackBD[player] = stack.subtract(requiredCallAmount);
        EvalData.moneyInBD[player] = EvalData.moneyInBD[player].add(requiredCallAmount);
        EvalData.potBD = EvalData.potBD.add(requiredCallAmount);
    }

    public static void main(String[] args) {
        // Example usage
        // Initialize stacks
        for (int i = 0; i < EvalData.PLAYERS; i++) {
            EvalData.stackBD[i] = new BigDecimal(100);
            EvalData.moneyInBD[i] = EvalData.zeroBD;
        }

        // Player 0 bets 10
        bet(0, new BigDecimal(10));
        System.out.println("Player 0 bets 10");

        // Player 1 calls
        call(1);
        System.out.println("Player 1 calls");

        // Print stacks and pot
        for (int i = 0; i < EvalData.PLAYERS; i++) {
            System.out.println("Player " + i + " stack: " + EvalData.stackBD[i]);
        }
        System.out.println("Pot: " + EvalData.potBD);
    }
}