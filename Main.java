import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame implements ActionListener{
    //members
    int panel = 0;
    StartPanel sp = null;
    MyPanel mp = null;
    JMenuBar jmb = null;
    JMenu jm1 = null;
    JMenu jm2 = null;
    JMenuItem jmi1 = null;
    JMenuItem jmi2 = null;
    JMenuItem jmi3 = null;

    public static void main(String args[]){
        Main m = new Main();
    }
    public Main(){
        sp=new StartPanel();
        jmb=new JMenuBar();
        jm1=new JMenu("file(f)");
        jm1.setMnemonic('f');;
        jm2=new JMenu("game(g)");
        jm2.setMnemonic('g');
        jmi1=new JMenuItem("save");
        jmi1.addActionListener(this);
        jmi1.setActionCommand("save");
        jmi2=new JMenuItem("load");
        jmi2.addActionListener(this);
        jmi2.setActionCommand("load");
        jmi3=new JMenuItem("new game");
        jmi3.addActionListener(this);
        jmi3.setActionCommand("newgame");
        jm1.add(jmi1);
        jm1.add(jmi2);
        jm2.add(jmi3);
        jmb.add(jm2);
        jmb.add(jm1);
        this.setJMenuBar(jmb);
        this.add(sp);
        this.setSize(900, 700);
        this.setTitle("Tank");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newgame")){
            if (panel == 1){
                this.remove(mp);
            }
            mp = new MyPanel();
            Thread t = new Thread(mp);
            t.start();
            this.remove(sp);
            this.add(mp);
            this.addKeyListener(mp);
            this.setVisible(true);
            panel = 1;
        }
        if (e.getActionCommand().equals("save")){
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("save to....");
            jfc.showSaveDialog(null);
            jfc.setVisible(true);

            String file = jfc.getSelectedFile().getAbsolutePath();
            FileReader fr = null;
            BufferedReader br = null;
            try{
                fr = new FileReader(file);
                br = new BufferedReader(fr);
            } catch (Exception ex){
                ex.printStackTrace();
            }
            finally{
                try{
                    fr.close();
                    br.close();
                } catch (Exception a){
                    a.printStackTrace();
                }
            }
        }else if (e.getActionCommand().equals("load")){
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("load");
            jfc.showOpenDialog(null);
            jfc.setVisible(true);

            String file = jfc.getSelectedFile().getAbsolutePath();
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);

            } catch (Exception a){
                a.printStackTrace();
            }
            finally {
                try {
                    bw.close();
                    fw.close();
                } catch (Exception a){
                    a.printStackTrace();
                }
            }
        }
    }
}
