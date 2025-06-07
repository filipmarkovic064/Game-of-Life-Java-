import java.util.Scanner; // Importerte en scanner for at brukeren skal kunne velge størrelsen og antall generasjoner (valgfrie oppgave)

public class GameOfLife {
    //Jeg lager 2 inputs for at brukeren skal skrive antall rader og kolonner
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Skriv hvor mange rader du vil ha: ");
        int rad = input1.nextInt();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Skriv hvor mange kolonner du vil ha: ");
        int kol = input2.nextInt();

    // generer en verden objekt som det skal spilles på 

        Verden verden = new Verden(rad,kol);
        verden.tegn();

        // En while løkke som sjekker for brukeren sin input sånn at de kan bestemme når de vil avslutte spillet selv
        //Fungerer sånn at det fortsettes uansett av hva brukeren gjør, bortsett for når han skriver q i terminalen
        //Den input er i løkken sånn at den spør brukeren mellom hver generasjon og for å ikke få en uendelig while løkke
        String sjekk = "";
        while(!sjekk.equals("q")){
            Scanner whileinput = new Scanner(System.in);
            System.out.println("Trykk enter for å fortsette eller skriv 'q' for å avslutte ");
            sjekk = whileinput.nextLine();

            verden.oppdatering();
            verden.tegn();
        }
    }
}
