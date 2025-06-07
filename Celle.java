//Jeg definerer et par variabler som jeg skal bruke og lager en enkelt array med lengde 8
public class Celle {
    public boolean levende;
    public int antNaboer;
    public int antLevendeNaboer;
    public Celle[] naboer = new Celle[8];
    //Her skriver jeg konstruktør som setter cellen til å være død med en gang
    public Celle(){
        levende = false;
        antNaboer = 0;
        antLevendeNaboer = 0;
    }
//De neste tre metoder er enkelte metoder som bare endrer levende status
    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true;
    }

    public boolean erLevende(){
        return levende;
    }
//Dette her er en metode som jeg skal bruke for å tegne rutenett, hvis død =. hvis levende = O
    public char hentStatusTegn(){
        char character;
        if(levende == true){
            character = 'O';
            return character;
        }
        else{
            character = '.';
            return character;
        }

    }
    //Den legger en nabo til den arrayen og øker antall naboer med 1
    public void leggTilNabo(Celle cell){
            naboer[antNaboer] = cell;
            antNaboer = antNaboer + 1;
    }
    //Her går jeg gjennom array av naboer og sjekker hvor mange som er levende sånn at neste generasjon kan funke 
    //Jeg sjekker om det er null, fordi jeg vil ikke sjekke tome plasser hvor det ikke fins celler
    public void tellLevendeNaboer(){
        int antall = 0;
        for(int i = 0; i<naboer.length;i++){
            if(naboer[i] != null){
                if(naboer[i].erLevende() == true){
                antall++;
                }
            }
        }
        antLevendeNaboer = antall;
    }
    //Her oppdaterer jeg status ved å følge reglene til spillet med enkelte if-sjekker
    public void oppdaterStatus(){
        if(erLevende()){
            if(antLevendeNaboer == 2 || antLevendeNaboer == 3){
                settLevende();
            }
            else{
                settDoed();
            }
        }
        else{
            if(antLevendeNaboer == 3){
                settLevende();
            }
        }
    }   
}