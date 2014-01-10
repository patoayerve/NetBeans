/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricio Ayerve
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class noughtsandcrosses extends JFrame implements ActionListener {
//Button array in grid layout
    private static int TILES = 9;
 
   JButton[] tArray = new JButton[TILES];
   int shotcounter;
   String temp;
   JFrame TFrame;
   int j;
   int k;
 
   public noughtsandcrosses(){
   TFrame = new JFrame();
     Container  con = this.getContentPane();
 con.setLayout( new GridLayout (3,0));
 for (int i = 0;  i < tArray.length; i++){
 tArray[i] = new JButton("");
 con.add(tArray[i]);
   tArray[i].addActionListener(this);
 }
    }
 
          public void actionPerformed(ActionEvent e) {
 
        for (j = 0; j < tArray.length; j++) {
            if (e.getSource() == tArray[j]) {
                temp = Integer.toString(++shotcounter);
 
                if (shotcounter % 2 == 0) {
 
                    temp = "0";
                    tArray[j].setText("0");
 
                } else {
 
                    temp = "X";
                  tArray[j].setText("X");
                }
 
                tArray[j].setEnabled(false);
                showWinner(j, temp);
            }
        }
    }
public void showWinner( int j, String temp) {
        if (tArray[0].getText() == tArray[1].getText() && tArray[0].getText() == tArray[2].getText() && tArray[0].getText() != "") {
            displayDialog(k);
 
        } else if (tArray[3].getText() == tArray[4].getText() && tArray[3].getText() == tArray[5].getText() && tArray[3].getText() != "") {
            displayDialog(k);
 
 
        } else if (tArray[6].getText() == tArray[7].getText() && tArray[6].getText() == tArray[8].getText() && tArray[6].getText() != "") {
            displayDialog(k);
 
        } else if (tArray[0].getText() == tArray[3].getText() && tArray[0].getText() == tArray[6].getText() && tArray[0].getText() != "") {
            displayDialog(k);
 
 
        } else if (tArray[1].getText() == tArray[4].getText() && tArray[1].getText() == tArray[7].getText() && tArray[1].getText() != "") {
            displayDialog(k);
 
 
        } else if (tArray[2].getText() == tArray[5].getText() && tArray[2].getText() == tArray[8].getText() && tArray[2].getText() != "") {
            displayDialog(k);
 
 
 
        } else if (tArray[0].getText() == tArray[4].getText() && tArray[0].getText() == tArray[8].getText() && tArray[0].getText() != "") {
          displayDialog(k);
 
 
        } else if (tArray[2].getText() == tArray[4].getText() && tArray[2].getText() == tArray[6].getText() && tArray[2].getText() != "") {
           displayDialog(k);
 
        } else if (shotcounter > 8) {
 
        }
    }
 
   public void displayDialog(int k) {
       int result = JOptionPane.showConfirmDialog(null,"Well done! Player " + temp + " is Victorious!!!!!!! Excellent!!! Do you wish to close the game?", "Do you wish to close?", JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION){
            System.exit(0); 
            }
    }
    
    public static void main(String args[]) {
          noughtsandcrosses window = new noughtsandcrosses();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Noughts And Crosses");
        window.setPreferredSize(new Dimension(500, 250));
        window.setVisible(true);
        window.pack();
   }
}