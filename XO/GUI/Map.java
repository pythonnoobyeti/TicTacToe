package XO.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static int Size = 5;
    public static int empty_Cell_Counter = Size * Size;
    public static char value_Human = 'x';
    public static char value_AI = 'o';
    public static char sym_Empty = ' ';
    private static int sym_Win = 4;
    public static char[][] surface;
    public static Random rand = new Random();
    private int dh;
    private int dw;
    public static boolean humanStep;

    Map() {
        setBackground(Color.getHSBColor(1, 3, 0.6f));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
    }

    protected void makeMapMassive() {
        surface = new char[Size][Size];
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                surface[i][j] = sym_Empty;
            }
        }
    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / dw;
        int cellY = e.getY() / dh;
        human_In(cellY, cellX);
        if (check_Win(value_Human)){
            System.out.println("Human Win!");
            setVisible(false);
        }
        if(empty_Cell_Counter==0){
            System.out.println("Draw!Game over!!");
            setVisible(false);}
        if (humanStep) {
            ai_In();
            if (check_Win(value_AI)){
                System.out.println("Computer Win!");
                setVisible(false);
            }
        }
        if(empty_Cell_Counter==0){
            System.out.println("Draw!Game over!!");
            setVisible(false);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
        drawSym(g);
    }

    public void drawSym(Graphics graphics) {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if (surface[i][j] == value_Human) {
                    graphics.setColor(Color.ORANGE);
                    graphics.fillOval((i * (getWidth() / Size)) + 5,
                            (j * (getHeight() / Size)) + 5,
                            (getWidth() / Size) - 10, (getHeight() / Size) - 10);
                } else if (surface[i][j] == value_AI) {
                    graphics.setColor(Color.MAGENTA);
                    graphics.fillOval((i * (getWidth() / Size)) + 5,
                            (j * (getHeight() / Size)) + 5,
                            (getWidth() / Size) - 10, (getHeight() / Size) - 10);
                }
            }
        }
        repaint();
    }

    public void drawLine(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        dw = getWidth() / Size;
        dh = getHeight() / Size;
        for (int i = 0; i < Size; i++) {
            graphics.drawLine(0, dh * i, getWidth(), dh * i);
            graphics.drawLine(dw * i, 0, dw * i, getHeight());
        }

    }

    public static void human_In(int x, int y) {
        if (cell_Is_Empty(x, y)) {
            surface[y][x] = value_Human;
            empty_Cell_Counter = empty_Cell_Counter - 1;
            humanStep = true;
        } else return;
    }

    public static void ai_In() {
        int x;
        int y;
        do {
            x = rand.nextInt(Size);
            y = rand.nextInt(Size);
        } while (!cell_Is_Empty(x, y));
        surface[y][x] = value_AI;
        empty_Cell_Counter = empty_Cell_Counter - 1;
        humanStep = false;

    }
    public static boolean check_Win(char sym) {
        int v;
        int h;
        int d_1;
        int d_2;
        for(int i = 0; i < Size; i++) {
            for (int j=0;j<Size;j++){
                v = 0;
                h = 0;
                d_1 = 0;
                d_2 = 0;
                if (surface [i][j]==sym){
                    for (int z=0;z<(sym_Win*2)-1;z++) {
                        try {
                            //Проверка вертикали
                            if (surface[i-(sym_Win-1)+z][j] == sym) {
                                v++;
                                if (v == sym_Win) return true;
                            }else v = 0;

                            //Проверка горизонтали
                            if (surface[i][j-(sym_Win-1)+z] == sym) {
                                h++;
                                if (h == sym_Win) return true;
                            }else h = 0;

                            //Проверка диагонали слева направо
                            if (surface[i-(sym_Win-1)+z][j-(sym_Win-1)+z] == sym) {
                                d_1++;
                                if (d_1 == sym_Win) return true;
                            }else d_1 = 0;

                            //Проверка диагонали справа налево
                            if (surface[i-(sym_Win-1)+z][j+(sym_Win-1)-z] == sym) {
                                d_2++;
                                if (d_2 == sym_Win) return true;
                            }else d_2 = 0;
                        }
                        catch (ArrayIndexOutOfBoundsException e) {;}
                    }
                }
            }
        }
        return false;
    }


    public static boolean cell_Is_Empty(int x, int y) {
        return surface[y][x] == sym_Empty;
    }

    public static boolean cell_Is_Valid(int x, int y) {
        return x >= 0 && y >= 0 && x < Size && y < Size;
    }

}
