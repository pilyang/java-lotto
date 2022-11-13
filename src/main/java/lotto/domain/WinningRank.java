package lotto.domain;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum WinningRank {
    NONE_RANKED(0, "",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount < 3)),
    FIFTH_RANK(5_000, "3개 일치",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount == 3)),
    FOURTH_RANK(50_000, "4개 일치",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount == 4)),
    THIRD_RANK(1_500_000, "5개 일치",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount == 5 && !isContainBonusNumber)),
    SECOND_RANK(30_000_000, "5개 일치, 보너스 볼 일치",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount == 5 && isContainBonusNumber)),
    FIRST_RANK(2_000_000_000, "6개 일치",
            (duplicatedNumberCount, isContainBonusNumber) -> (duplicatedNumberCount == 6));


    private static final String DECIMAL_FORMAT = "###,###";

    private final int prizeMoney;
    private final String winningRule;
    private final BiFunction<Integer, Boolean, Boolean> isWinExpression;

    WinningRank(int prizeMoney, String winningRule, BiFunction<Integer, Boolean, Boolean> isWinExpression) {
        this.prizeMoney = prizeMoney;
        this.winningRule = winningRule;
        this.isWinExpression = isWinExpression;
    }


    public static WinningRank findByWinningCondition(int duplicatedNumberCount, boolean isContainBonusNumber) {
        return Arrays.stream(WinningRank.values())
                .filter(rank -> rank.isWin(duplicatedNumberCount, isContainBonusNumber))
                .findAny()
                .orElse(NONE_RANKED);
    }

    public boolean isWin(int duplicatedNumberCount, boolean isContainBonusNumber) {
        return isWinExpression.apply(duplicatedNumberCount, isContainBonusNumber).booleanValue();
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        return this.winningRule + " (" + decimalFormat.format(this.prizeMoney) + "원)";
    }
}
