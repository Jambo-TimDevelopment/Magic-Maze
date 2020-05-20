package Panel;

import java.awt.EventQueue;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Assets.Statistics;
import Object.Global.Maze;
import Object.Global.Ui;
import Object.Primitive.DataGame;
import Object.Primitive.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Game_Panel extends JFrame {
    public static boolean isGame;
    private static int level = 0;
    //Constant
    private static int SIZE_MAP = 8;
    //Panel
    private JPanel contentPane;
    //Ui
    private Ui ui = new Ui();
    //Objects
    private static Maze maze = new Maze(SIZE_MAP);
    private static Player player = new Player(1, 1, maze);
    //Infomation
    public static Integer countLevelsInThisSession;

    //DataGame dataGame
    private static void testSerializ(DataGame dataGame) {
        for (int i = 0; i <= maze.SIZE_MAP_i; i++) {
            for (int j = 0; j <= maze.SIZE_MAP_j; j++) {
                if (dataGame.field[i][j] == 3) {
                    System.out.println("player " + maze.getField()[i][j] + " координаты " + i + " " + j);
                }
            }
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        // testSerializ();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game_Panel frame = new Game_Panel();
                    frame.setVisible(true);

                    saveGameOnExit(frame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        playSound();
    }

    /**
     * Create the frame.
     *
     * @throws IOException
     */
    public Game_Panel() {
        setTitle("Magic maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 680, 580);
        setSize(1980, 1080);
        //Создание панель
        createPanel();
        //Отрисовка игрового поля
        drowGameField(contentPane);
    }

    /**
     * Create the JPanel.
     */
    private void createPanel() {
        contentPane = new JPanel();
        contentPane.setFocusable(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    /**
     * Drawing the game field panel and driver keyboard.
     */
    private void drowGameField(JPanel contentPane) {
        //В начале рисуется UI
        final JPanel panel = new DrawingField(maze, ui);

        panel.setBorder(new BevelBorder(BevelBorder.LOWERED,
                null, null, null, null));
        panel.setBounds(0, 0, 1980, 1080);
        contentPane.add(panel);

        contentPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        try {
                            checkFinishPoint(player.moveLeft(), panel);
                        } catch (IOException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_RIGHT:
                        try {
                            checkFinishPoint(player.moveRight(), panel);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_DOWN:
                        try {
                            checkFinishPoint(player.moveDown(), panel);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        getContentPane().repaint();

                        break;

                    case KeyEvent.VK_UP:
                        try {
                            checkFinishPoint(player.moveUp(), panel);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_ENTER:
                        isGame = true;
                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_1:
                        isGame = true;
                        level = 1;
                        load_1(panel);

                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_2:
                        isGame = true;
                        level = 2;
                        load_2(panel);

                        getContentPane().repaint();
                        break;

                    case KeyEvent.VK_3:
                        isGame = true;
                        level = 3;
                        load_3(panel);
                        ((DrawingField) panel).setField(maze);
                        getContentPane().repaint();
                        break;
                    default:
                        return;
                }
            }

        });
    }

    public void checkFinishPoint(boolean isFinish, JPanel panel) throws IOException {
        if (isFinish) {
            maze = new Maze((int) (maze.SIZE_MAP_i * 1.5));
            ((DrawingField) panel).setField(maze);
            player = new Player(1, 1, maze);
        }
    }

    public static void saveGameOnExit(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DataGame dataGame = new DataGame(maze.getField(), maze.SIZE_MAP_i, maze.SIZE_MAP_j);
                if (level == 1) {
                    System.out.println(" Координаты сохранения " + dataGame.p_i + " " + dataGame.p_j);
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save1.out"));
                        objectOutputStream.writeObject(dataGame);
                        objectOutputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (level == 2) {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save2.out"));
                        objectOutputStream.writeObject(dataGame);
                        objectOutputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (level == 3) {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save3.out"));
                        objectOutputStream.writeObject(dataGame);
                        objectOutputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        maze = new Maze(72);
                    }

                }
                System.exit(0);
            }
        });
    }

    public static void load_1(JPanel panel) {
        try {
            System.out.println("Загрузка уровня игры");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save1.out"));
            DataGame dataGame = (DataGame) objectInputStream.readObject();
            objectInputStream.close();

            maze.SIZE_MAP_i = dataGame.SIZE_MAP_i;
            maze.SIZE_MAP_j = dataGame.SIZE_MAP_j;
            maze.loadField(dataGame.field);
            player.i = dataGame.p_i;
            player.j = dataGame.p_j;
            player = new Player(dataGame.p_i, dataGame.p_j, maze);
            testSerializ(dataGame);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ((DrawingField) panel).setField(maze);
    }

    public static void load_2(JPanel panel) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save2.out"));
            DataGame dataGame = (DataGame) objectInputStream.readObject();
            objectInputStream.close();

            maze.loadField(dataGame.field);
            maze.SIZE_MAP_i = dataGame.SIZE_MAP_i;

            testSerializ(dataGame);

            maze.SIZE_MAP_j = dataGame.SIZE_MAP_j;
            player.i = dataGame.p_i;
            player.j = dataGame.p_j;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ((DrawingField) panel).setField(maze);
    }

    public static void load_3(JPanel panel) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream("save3.out"));
            DataGame dataGame = (DataGame) objectInputStream.readObject();
            objectInputStream.close();

            maze.loadField(dataGame.field);
            maze.SIZE_MAP_i = dataGame.SIZE_MAP_i;

            testSerializ(dataGame);

            maze.SIZE_MAP_j = dataGame.SIZE_MAP_j;
            player.i = dataGame.p_i;
            player.j = dataGame.p_j;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ((DrawingField) panel).setField(maze);
    }

    /**
     * Load game field and other variable on start the game level
     */


    public static void playSound() {
        try {
            File soundFile = new File("src/Assets/snd.wav"); //Звуковой файл

            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            Clip clip = AudioSystem.getClip();

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            clip.open(ais);

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!

            //Если не запущено других потоков, то стоит подождать, пока клип не закончится
            //В GUI-приложениях следующие 3 строчки не понадобятся
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        } catch (InterruptedException exc) {
        }
    }
}
