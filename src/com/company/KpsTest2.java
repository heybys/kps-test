package com.company;

import java.util.Scanner;

public class KpsTest2 {

    static Scanner scan = new Scanner(System.in);

    static int[][] map;
    static int N;
    static int M;
    static int max = 0;

    public static void main(String[] args) {

        M = scan.nextInt(); // 5
        N = scan.nextInt(); // 7
        String data = scan.next();
        String[] scores = data.split(",");

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(scores[i * M + j]);
            }
        }

        Point neo = new Point(0, 0);
        Point frodo = new Point(0, M - 1);

        move(neo, frodo, 0, 0);

        System.out.println(max);

    }

    public static void move(Point neo, Point frodo, int sum, int depth) {
        int[][] dir = {{1, -1}, {1, 0}, {1, 1}};

        sum += map[neo.x][neo.y];
        sum += (neo.x == frodo.x && neo.y == frodo.y) ? 0 : map[frodo.x][frodo.y];

        if (depth == N - 1) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            Point nxtNeo = new Point(neo.x + dir[i][0], neo.y + dir[i][1]);

            if (!isOut(nxtNeo.x, nxtNeo.y)) {
                for (int j = 0; j < 3; j++) {
                    Point nxtFrodo = new Point(frodo.x + dir[j][0], frodo.y + dir[j][1]);

                    if (!isOut(nxtFrodo.x, nxtFrodo.y)) {
                        move(nxtNeo, nxtFrodo, sum, depth + 1);
                    }
                }
            }
        }

    }

    public static boolean isOut(int x, int y) {
        if (x < 0 || x >= N) {
            return true;
        } else if (y < 0 || y >= M) {
            return true;
        } else {
            return false;
        }
    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}