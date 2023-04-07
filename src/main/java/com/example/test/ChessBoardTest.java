package com.example.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * className ChessBoardTest
 * packageName com.example.test
 * description
 *
 * @author CCC
 * @date 2021/1/18 19:17
 */
public class ChessBoardTest {


    //棋盘的行
    private static int X;
    //棋盘的列
    private static int Y;
    //使用二维数组记录棋盘上的各个位置是否被走过
    private static boolean[][] visited;
    //游戏是否成功结束
    private static boolean finished;

    /**
     * 程序输出：
     * 共耗时: 59 毫秒
     * 1	16	37	32	3	18	47	22
     * 38	31	2	17	48	21	4	19
     * 15	36	49	54	33	64	23	46
     * 30	39	60	35	50	53	20	5
     * 61	14	55	52	63	34	45	24
     * 40	29	62	59	56	51	6	9
     * 13	58	27	42	11	8	25	44
     * 28	41	12	57	26	43	10	7
     */
    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        X = 8;
        Y = 8;
        int row = 1; //马初始位置的行，从1开始编号
        int column = 1; //马初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X][Y];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        tryTravel(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        //输出棋盘的最后情况
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
    /**
     * 完成骑士周游问题的算法
     * 递归可以实现回溯。
     * @param chessBoard 棋盘
     * @param row 马儿当前的位置的行 从0开始
     * @param column 马儿当前的位置的列  从0开始
     * @param step 是第几步 ,初始位置就是第1步。棋盘用二维数组表示，数组的每个元素记录的是步数
     */
    public static void tryTravel(int[][] chessBoard,int row, int column, int step){
        //数组元素赋值，将步数赋给当前的元素
        chessBoard[row][column]=step;
        //表示该点已经被访问了
        visited[row][column]=true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));

        pointSort(ps);

        //如果当前位置可以走的下一个位置的集合不为空，说明还可以继续放下走
        while (!ps.isEmpty()){
            Point point = ps.remove(0);
            if(!visited[point.y][point.x]){
                tryTravel(chessBoard,point.y,point.x,step+1);
            }
        }

        //判断马儿是否完成了任务，使用   step 和应该走的步数比较 ，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明: step < X * Y  成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程

        /* 举例：假设初始位置为2，接下来跳的位置为5，7，9.当跳到第9步时，发现已经没有任何位置可以跳，list为空，循环结束
          则将9这位置的步数和访问标志重置。因为使用了递归，当9这个位置执行结束时，7 会选择另一个位置作为下一步（9已经从list
         中移除），继续进行7的循环。如果7的所有下一步都不能走通，则7的循环结束，7这位置的步数和访问标志重置。5重新选择除了
         7之外的下一步进行尝试，如果仍然全部走不通，则将5这个位置的步数和访问标志重置......,不断尝试，不断回溯*/
        if(step < X*Y && !finished){
            chessBoard[row][column] = 0;
            visited[row][column]=false;
        }else{
            finished=true;
        }

    }
    /**
     * 功能： 根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中(ArrayList), 最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        //创建一个Point
        Point p1 = new Point();

        //将curPoint能走的坐标值赋给p1，然后将p1的坐标放入到list中。
        //表示马儿可以走5这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }


    /**
     *1,该方法对于集合进行排序，每个集合中保存的是下一步可以走的位置，选择该位置再下一步能走得步数最少的哪个位置，
     * 按从小到大进行排序。
     *
     * 排序的其实就是利用了贪心算法的思想，每次选择最优的那一步的结果，则整体上可以达到最优。
     *
     * 2,排序的目的是为了减少回溯次数，即试错的次数。
     * 经测试，排序之后程序运行时间缩短为不到50毫秒。排序前，大概50秒。
     *
     *3,举例说明：现在马在0这个位置上，下一步能走的位置有3，4，5，。如果马跳到3这个位置上，下下一步有4个位置可以周，如果跳到
     * 4上，下下一步有1个位置可以周，如果跳到5上，下下一步有6步可以走。那么优先选择跳到4上。可以减少试错的次数
     *
     * @param ps 表示在当前位置上，马还能走哪些位置的集合。
     */
    public static void pointSort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();

                if(count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

    }

}
