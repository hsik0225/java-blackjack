package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Scanner;

public class InputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ERROR_IS_NOT_Y_OR_N = "y 또는 n을 입력해주세요";
    private static final String ASK_RECEIVE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%s";
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    
    public static String getPlayerName() {
        System.out.println(ASK_PLAYER_NAMES);
        
        String input = scanner.nextLine();
    
        System.out.println();
        
        return input;
    }
    
    public static boolean wantsReceive(Player player) {
        System.out.printf(ASK_RECEIVE_CARD, player.getName(), LINE_SEPARATOR);
        
        String input = scanner.nextLine();
        if (!isYorN(input)) {
            throw new IllegalArgumentException(ERROR_IS_NOT_Y_OR_N);
        }
        
        return input.equalsIgnoreCase("y");
    }
    
    private static boolean isYorN(String input) {
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n");
    }
}
