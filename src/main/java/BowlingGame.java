/**
 * 
 * @author DD
 * paramet bowlingCode
 */
public class BowlingGame {
    public int getBowlingScore(String bowlingCode) {
        char[] chars = bowlingCode.toCharArray();//bowlingCode描述保龄球比赛成绩的字符串
        int i = 0;//定义初始变量i从零开始
        int sum = 0;//定义总成绩，初始化为0
        int[] socere = new int[22];
        int x = 0;
        int current = 0;
        while (i < chars.length) {
            current = -10;
            if (chars[i] == '|') {
                while (i < chars.length - 1 && x < socere.length) {
                    i++;
                    socere[x] = ScoreofCurrentChar(chars[i]);
                    if (socere[x] == -2) {
                        socere[x] = 10;
                    }
                    x++;
                }
            }
            while (i < chars.length && chars[i] != '|') {
                int temp = current;
                current = ScoreofCurrentChar(chars[i]);
                if (current >= 0 && temp > current)
                    current = temp;
                i++;
                if (x < socere.length)
                    if (current == -2) {
                        socere[x++] = 10;
                        socere[x++] = current;
                    } else {
                        socere[x++] = current;
                    }
            }
            i++;

        }
        
        int[] socere2 = new int[10];
        int j = socere2.length - 1;

        while (j >= 0) {//计算十格中每格分数
            if (socere[2 * j + 1] == -2) {
                if (j == 9)
                    socere2[j] = socere[2 * j + 2] + socere[2 * j + 3] + 10;

                else if (socere[2 * (1 + j) ] == 10 ) {
                    socere2[j] = 10+ socere[2 * (2 + j) ]+10;
                }
                else if (socere[2*(j+1)] <10)
                {
                    socere2[j] = 10 +socere[2*(j+1)+1];
                }
                socere[2 * j + 1] = 10;
            } else if (socere[2 * j + 1] == -1) {
                socere2[j] = socere[j * 2 + 1 + 1] + 10;
                socere[2 * j + 1] = 10;
            } else
                socere2[j] = socere[(j + 1) * 2 - 1];
            j--;
        }
        j = 0;
        while (j < socere2.length) {//计算总分数
            sum += socere2[j++];
        }
        
        return sum;
    }

   
    private int ScoreofCurrentChar(char c) {
        int current;
        if (c == '/')
            current = -1;
        else if (c == '-')
            current = 0;
        else if (c == 'X')
            current = -2;
        else
            current = c - '0';
        return current;
    }
}
