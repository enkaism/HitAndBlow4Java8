import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by enkaism on 12/22/15.
 */
public class Game {

    private int countPlay;

    private HABArrayList<Integer> answer;
    private HABArrayList<Integer> input;

    /**
     * コンストラクタ
     * countPlay, answer, inputの初期化
     * answerの生成
     */
    public Game() {
        countPlay = 0;
        answer = new HABArrayList<>(Arrays.asList(0, 0, 0, 0));
        input = new HABArrayList<>(Arrays.asList(0, 0, 0, 0));
        generateAnswer();
    }

    /**
     * ゲームを開始する
     */
    public void start() {
        System.out.println("Game Started");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                countPlay++;
                System.out.println(Const.NUMBER_OF_DIGITS + "桁の数値を入力してください。(" + countPlay + "回目)");
                String in = scanner.next();
                if (!setInput(in)) continue;

                System.out.println(getHits() + "ヒット, " + getBlows() + "ブロー");
                if (getHits() == Const.NUMBER_OF_DIGITS) {
                    System.out.println(countPlay + "回目でクリア！");
                    System.out.println("おめでとうございます！");
                    break;
                }
            }
        }
    }

    /**
     * ランダムで答えを生成する
     */
    public void generateAnswer() {
        Integer[] numberList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Collections.shuffle(Arrays.asList(numberList));
        IntStream.range(0, answer.size()).forEach((i) -> answer.set(i, numberList[i]));
    }

    /**
     * 入力文字列からHABArrayListへ変換する
     * 入力に誤りがある場合はfalseを返す
     *
     * @param in 入力文字列
     * @return validな入力かどうか
     */
    public boolean setInput(String in) {
        if (in.length() != Const.NUMBER_OF_DIGITS) {
            System.out.println("入力が" + Const.NUMBER_OF_DIGITS + "桁ではありません。");
            return false;
        }

        if (!in.matches("\\d+")) {
            System.out.println("入力は数値のみです。");
            return false;
        }

        if ((int) in.chars().boxed().distinct().count() != Const.NUMBER_OF_DIGITS) {
            System.out.println("入力に重複があります。");
            return false;
        }

        IntStream.range(0, input.size())
                .forEach(i -> input.set(i, Character.getNumericValue(in.charAt(i))));
        return true;
    }

    /**
     * ヒット数を返す
     *
     * @return ヒット数
     */
    private int getHits() {
        return (int) IntStream.range(0, answer.size())
                .filter(i -> answer.get(i) == input.get(i))
                .count();
    }

    /**
     * ブロー数を返す
     *
     * @return
     */
    private int getBlows() {
        return (int) IntStream.range(0, answer.size())
                .filter(i -> answer.get(i) != input.get(i) && answer.contains(input.get(i)))
                .count();
    }
}
