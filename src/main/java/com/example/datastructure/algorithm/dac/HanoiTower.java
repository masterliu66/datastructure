package com.example.datastructure.algorithm.dac;

/**
 * className HanoiTower
 * packageName com.example.datastructure.algorithm
 * description
 *
 * @author CCC
 * @date 2021/1/8 22:22
 */
public class HanoiTower {

    public static void main(String[] args) {
        new HanoiTower().hanoiTower(3, 'A', 'B', 'C');
    }

    private int count = 0;

    public void hanoiTower(int num, char a, char b, char c) {

        // 只有一个盘子, 直接从A移动到C
        if (num == 1) {
            move(1, a, c);
            return;
        }

        // 盘子数量n>=2, 将盘子分为两个部分
        // 1.最下方的盘子n; 2.上方的所有盘子1~n-1
        // 先将(1~n-1)号盘子从A移动到B
        hanoiTower(num - 1, a, c, b);
        // 将n号盘子从A移动到C
        move(num, a, c);
        // 再将(1~n-1)号盘子从B移动到C
        hanoiTower(num - 1, b, a, c);
    }

    private void move(int disks, char a, char b) {
        System.out.println((++count) + ": " + disks + "号圆盘 " + a + "->" + b);
    }

}
