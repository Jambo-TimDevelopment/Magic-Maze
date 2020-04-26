package Panel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import Assets.Statistics;
import Object.Maze;
import Object.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JTextArea;

public class Game_Panel extends JFrame {
    private int SIZE_MAP = 24;
    private int COUNT_POINTS = 0;
    private JPanel contentPane;
    private Maze gameField = new Maze(SIZE_MAP, COUNT_POINTS);
    private Player player = new Player(1, 1, gameField);
    private JTextArea Text_out;
    private Statistics infomation;
    public static Integer countLevelsInThisSession;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game_Panel frame = new Game_Panel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void checkFinishPoint(boolean isFinish, JPanel panel) throws IOException {
        if(isFinish) {
            gameField = new Maze(gameField.SIZE_MAP * 2, gameField.COUNT_CHEK_POINTS * 2);
            Text_out.setText("Пройдено уровней: " + infomation.getText() +
                    "\n" + "Во время этой сессии: " + infomation.getCountLevelsInThisSession());
            ((DrawField) panel).setField(gameField);
            player = new Player(1, 1, gameField);
        }
    }

    /**
     * Create the frame.
     * @throws IOException
     */
    public Game_Panel() throws IOException {
        setTitle("Magic maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 680, 580);


        createPanel();
        drowTextField();
        drowField(contentPane);
    }

    private void drowTextField() throws IOException {
        infomation = new Statistics();
        try {
            countLevelsInThisSession = Integer.parseInt(infomation.getText());
        }catch(Exception e) {
            countLevelsInThisSession = -1000;
        }

        Text_out = new JTextArea();
        Text_out.setBounds(650, 11, 262, 148);
        contentPane.add(Text_out);
        Text_out.setColumns(10);
        Text_out.setText("Пройдено уровней: " + infomation.getText() +
                "\n" + "Во время этой сессии: " + infomation.getCountLevelsInThisSession());
    }

    private void drowField(JPanel contentPane){
        final JPanel panel = new DrawField(gameField);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED,
                null, null, null, null));
        panel.setBounds(10, 11, 630, 523);
        contentPane.add(panel);

        contentPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
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

                    default:
                        return;
                }
            }

        });
    }

    private void createPanel(){
        contentPane = new JPanel();
        contentPane.setFocusable(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
}
