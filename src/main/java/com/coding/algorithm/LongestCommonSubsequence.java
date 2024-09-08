package com.coding.algorithm;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Solution {


    /**
     * 获取所有的LCS
     */
     public void backtrackAllLCS(int[][] dp, String text1, String text2, int i, int j, StringBuilder currentLCS, Set<String> resultSet) {
        if (i == 0 || j == 0) {
            resultSet.add(currentLCS.reverse().toString());
            currentLCS.reverse(); // 反转回来
            return;
        }

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            currentLCS.append(text1.charAt(i - 1));
            backtrackAllLCS(dp, text1, text2, i - 1, j - 1, currentLCS, resultSet);
            currentLCS.deleteCharAt(currentLCS.length() - 1); // 回溯时移除最后一个字符
        } else {
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                backtrackAllLCS(dp, text1, text2, i - 1, j, currentLCS, resultSet);
            }
            if (dp[i][j - 1] >= dp[i - 1][j]) {
                backtrackAllLCS(dp, text1, text2, i, j - 1, currentLCS, resultSet);
            }
        }
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        Set<String> resultSet = new HashSet<>();
        backtrackAllLCS(dp, text1, text2, m, n, new StringBuilder(), resultSet);
        for (String str : resultSet) {
            System.out.println(str);
        }
        return dp[m][n];
    }
}


public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "ABCBDAB", text2 = "BDCABA";
        System.out.println(new Solution().longestCommonSubsequence(text1, text2));
//        CyclicBarrier cb = new CyclicBarrier(2, () -> {
//            System.out.println("即将打破屏障");
//        });
//
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "开始运行");
//                try {
//                    cb.await();
//                    System.out.println(Thread.currentThread().getName() + "已经穿越了第1个屏障");
//                    cb.await();
//                    System.out.println(Thread.currentThread().getName() + "已经穿越了第2个屏障");
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }
}