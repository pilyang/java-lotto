package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {

    public List<Lotto> generateLottoAuto(int number) {

        List<Lotto> generatedLottos = new ArrayList<>();

        while (generatedLottos.size() < number) {
            List<Integer> numbers =
                    Randoms.pickUniqueNumbersInRange(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER, Lotto.NUMBER_COUNT);
            generatedLottos.add(new Lotto(numbers));
        }

        return generatedLottos;
    }

    public Map<WinningRank, Integer> countWinLotto(WinningLotto winningLotto, List<Lotto> lottos) {
        Map<WinningRank, Integer> winLottoCount = new HashMap<>();

        for (Lotto lotto : lottos) {
            WinningRank rank = judgeRank(winningLotto, lotto);
            winLottoCount.put(rank, winLottoCount.getOrDefault(rank, 0) + 1);
        }

        return winLottoCount;
    }

    public WinningRank judgeRank(WinningLotto winningLotto, Lotto lotto) {
        int duplicatedNumberCount = countDuplicatedNumbers(winningLotto, lotto);
        boolean isContainBonusNumber = winningLotto.containBonusNumber(lotto);

        return WinningRank.findByWinningCondition(duplicatedNumberCount, isContainBonusNumber);
    }

    private int countDuplicatedNumbers(WinningLotto winningLotto, Lotto lotto) {
        List<Integer> myLottoNumbers = lotto.getNumbers();
        int count = 0;
        for (Integer number : myLottoNumbers) {
            if (winningLotto.isContainNumber(number)) {
                count++;
            }
        }
        return count;
    }

}
