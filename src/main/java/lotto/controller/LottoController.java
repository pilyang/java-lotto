package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningRank;

import java.util.ArrayList;
import java.util.List;

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

    public WinningRank judgeRank(WinningLotto winningLotto, Lotto lotto) {
        // todo: judgeRank 구현하기
        return null;
    }

    private int countMatchedNumbers(WinningLotto winningLotto, Lotto lotto) {
        List<Integer> myLottoNumbers = lotto.getNumbers();
        int count = 0;
        for(Integer number : myLottoNumbers) {
            if(winningLotto.isContainNumber(number)){
                count++;
            }
        }
        return count;
    }

}
