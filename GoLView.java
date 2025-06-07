import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.Scanner;
import java.util.ArrayList;

public class GoLView{

    private JFrame vindu;
    private JPanel hovedpanel;
    private JPanel oppePanel;
    private Timer timer;
    ArrayList<JButton> buttons = new ArrayList<>();

    public GoLView(){

        Scanner sc = new Scanner(System.in); //Spør brukeren om hvor mange råder og kolloner han vil ha
        System.out.println("Hvor mange raader vil du ha? (Kun tall)");
        int raad = sc.nextInt();
        System.out.println("Hvor mange kolloner vil du ha? (Kun tall)");
        int kol = sc.nextInt();
        sc.close();

        try{ //De sa på forelesningen at vi må ha den sånn at den kan kjøre cross-platform 
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch(Exception e){
            System.exit(1);
        }
    

        Verden verden = new Verden(raad,kol);

        vindu = new JFrame("Game of Life"); //Hoved vindu
        vindu.setLayout(new BorderLayout());
        vindu.setSize(600, 400);
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        oppePanel = new JPanel(); //Øverste siden (med antall levende osv.)
        oppePanel.setBorder(new LineBorder(Color.GRAY, 1)); 
        vindu.add(oppePanel,BorderLayout.NORTH);

        JTextField genTekstFelt = new JTextField();
        int teller = 0;
        genTekstFelt.setText("Generasjon nummer: " +teller);
        genTekstFelt.setEditable(false);
        genTekstFelt.setPreferredSize(new Dimension(150, 35)); //Her prøver jeg bare å sette størrelse
        genTekstFelt.setBorder(null);
        genTekstFelt.setBackground(oppePanel.getBackground());
        oppePanel.add(genTekstFelt);

        JTextField levendeTekstFelt = new JTextField();
        int levendeCell = verden.rutene.antallLevende();
        levendeTekstFelt.setText("Antall levende: " +levendeCell);
        levendeTekstFelt.setEditable(false);
        levendeTekstFelt.setPreferredSize(new Dimension(150, 35));
        levendeTekstFelt.setBorder(null);
        levendeTekstFelt.setBackground(oppePanel.getBackground()); //Setter samme bakgrun sånn at det blender inn i oppePanel (bedre utseendre i min mening)
        oppePanel.add(levendeTekstFelt);


        JButton nesteKnappen = new JButton("Start");
        nesteKnappen.setBorder(new LineBorder(Color.GRAY, 1));
        nesteKnappen.setPreferredSize(new Dimension(90, 35));
        class nesteBehandler implements ActionListener{

            int teller = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                timer.start(); //Starter en timer sånn at et kjører hver 2s
                verden.oppdatering();
                sjekkCellene(verden);
                int levendeCell = verden.rutene.antallLevende();
                teller++;
                genTekstFelt.setText("Generasjon nummer: " +teller);
                levendeTekstFelt.setText("Antall levende: " +levendeCell);
            }
        }
        timer = new Timer(2000, new nesteBehandler()); //Jeg trenger en timer sånn at den kjører hver 2s
        nesteKnappen.addActionListener(new nesteBehandler()); 
        oppePanel.add(nesteKnappen);

        JButton avsluttKnappen = new JButton("Avslutt"); //Avslutter programmet
        avsluttKnappen.setBorder(new LineBorder(Color.GRAY, 1));
        avsluttKnappen.setPreferredSize(new Dimension(90, 35));
        class avsluttBehandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
        avsluttKnappen.addActionListener(new avsluttBehandler()); 
        oppePanel.add(avsluttKnappen);

        hovedpanel = new JPanel(); //Her skal alle cellene gå
        hovedpanel.setLayout(new GridLayout(raad,kol));
        vindu.add(hovedpanel,BorderLayout.CENTER);


        for(int r = 0; r<raad;r++){
            for(int k = 0; k<kol; k++){
                JButton celle = new JButton("");
                Celle ruteCelle = verden.rutene.hentCelle(r, k);
                if(verden.rutene.hentCelle(r, k).levende){
                    celle.setText("*");
                }
                class trykkBehandler implements ActionListener{
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int levendeCell = verden.rutene.antallLevende();
                        if(celle.getText() == "*"){
                            celle.setText("");
                            ruteCelle.settDoed();
                            levendeCell--;
                            levendeTekstFelt.setText("Antall levende: " +levendeCell);
                        }
                        else{
                            celle.setText("*");
                            ruteCelle.settLevende();
                            levendeCell++;
                            levendeTekstFelt.setText("Antall levende: " +levendeCell);
                        }

                    }
                }
                celle.addActionListener(new trykkBehandler()); 
                buttons.add(celle); //Jeg legger det inn i en arrayListe sånn at det er lettere å endre dem senere
                celle.setMargin(new Insets(0, 0, 0, 0)); // Remove margins
                celle.setBorder(new LineBorder(Color.GRAY, 1)); // Set border
                celle.setContentAreaFilled(false); // Remove padding
                hovedpanel.add(celle);
            }
        }
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }

    public void sjekkCellene(Verden verden){ //Lager en metode som skal sjekke om cellene er dø eller ikke(jeg skal bruke det for å oppdatere hovedpanel for hver neste generasjon)
        int teller = 0;
        for(int r = 0; r < verden.rutene.antRader; r++){
            for(int k = 0; k<verden.rutene.antKolonner; k++){
                if(verden.rutene.hentCelle(r, k).levende){
                    buttons.get(teller).setText("*");
                }
                else{
                    buttons.get(teller).setText("");
                }
                teller++;
            }
        }
    }
public static void main(String[] args) {
    
    GoLView gameOfLife = new GoLView();
}
}
