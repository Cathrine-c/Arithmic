package InterviewImportant.Arithmiy;

import java.util.Set;

public class Main {


    //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    //'.' 匹配任意单个字符
    //'*' 匹配零个或多个前面的那一个元素
    //所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        dp[0][0] = true;
        for (int i=0;i<p.length();i++){
            if (p.charAt(i)=='*'&&dp[0][i-1]){
                dp[0][i+1] = true;

            }
        }


        for (int i=0;i<s.length();i++){
            for (int j=0;j<p.length();j++){
                if (p.charAt(j)=='.'||p.charAt(j)==s.charAt(i)){
                    dp[i+1][j+1] = dp[i][j];
                }

                if (p.charAt(j)=='*'){
                    if (p.charAt(j-1)!=s.charAt(i)&&p.charAt(j-1)!='.'){
                        dp[i+1][j+1] = dp[i+1][j-1];

                    }else {
                        dp[i+1][j+1] = (dp[i+1][j]||dp[i][j+1]||dp[i+1][j-1]);

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //判断字符串是否能够被分割
    public static boolean wordBreak(String s, Set<String> dict){

        if (s.length() == 0) {
            return false;
        }

        boolean[] canBreak = new boolean[s.length()+1];

        for (int i=1;i<=s.length();i++){

            if (dict.contains(s.substring(0,i))){
                canBreak[i] = true;
                continue;
            }

            for (int j=i-1;j>0;j--){
                if (canBreak[j]&&dict.contains(s.substring(j,i))){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[s.length()];
    }



}
