//Jeg lager variabler og en 2D array for å lage en rutenett på den
public class Rutenett {
    public int antRader,antKolonner;
    public Celle[][] rutene;
//Konstruktør hvor jeg bestemmer størelsen av rutenett
    public Rutenett(int rader, int kolonner){
        antRader = rader;
        antKolonner = kolonner;
        rutene = new Celle[rader][kolonner];
    }
    
//Den lager en ny Celle objekt, og legger den in på rutenett, den har en 1/3 sjanse for å bli levende
    public void lagCelle(int rad,int kol){
        Celle cell = new Celle();
        boolean tall = Math.random()<=0.333;
        if(tall == true){
            cell.settLevende();
        }
        rutene[rad][kol] = cell;
    }
//Metode for starten av spillet som fyller rutenett med tilfeldige celler, jeg bruker en nøstet for-løkke for å fylle den siden det er 2D
    public void fyllMedTilfeldigeCeller(){
        for(int i = 0;i<antRader;i++){
            for(int a = 0;a<antKolonner;a++){
                lagCelle(i,a);
            }
        }
    }
    //Bruker paramtetre for å finne den bestemte cell, og også sjekker om den fins
    public Celle hentCelle(int rad, int kol){
        if(rad < antRader && kol < antKolonner && rad >= 0 && kol >= 0){
            return rutene[rad][kol];
        }
        else{
            return null;
        }
    }
    //jeg tegner rutenett ved å bruke .hentStatusTegn fra forrige klasse
    public void tegnRutenett(){
        for(int i = 0; i<10;i++){ //Jeg bruker den for å ha tom terminal før jeg tegner rutenettet
            System.out.println("");
        }
        for(int x = 0; x<antRader;x++){
            System.out.println("");
            for(int y=0;y<antKolonner;y++){
                System.out.print(rutene[x][y].hentStatusTegn());
            }
        }
        System.out.println("");
    }
    //Jeg går gjennom hele rutenett og sjekker om det er mulig å legge til naboer(derfor har jeg så mange if sjekker)
    //Jeg kunne ha brukt en if-sjekk men den ser bedre ut sånn så jeg valgte å skrive det sånn
    public void settNaboer(int rad,int kol){
       for(int x = -1; x<2; x++){
            for(int y = -1; y<2;y++){
                if(x==0 && y==0){ // Ikke velg seg selv
                }
                else if(rad+x<0 || kol+y<0){ // Ikke utenfor rutenettet
                }
                else if(rad+x >= antRader || kol+y >= antKolonner){
                }
                else{
                    Celle host = hentCelle(rad,kol);
                    if(host == null){ //Dette her sjekker om det fins en celle der for hjørner osv.
                        continue;
                    }
                    Celle cell = hentCelle(rad+x,kol+y);
                    host.leggTilNabo(cell);
                }
            }
       }
    }
    //Jeg kobler alle celler ved å bruke den forrige metoder(brukes for spill start)
    public void kobleAlleCeller(){
        for(int i = 0;i<antRader;i++){
            for(int z = 0; z<antKolonner;z++){
                settNaboer(z,i);
            }
        }
    }
    //Jeg sjekker for levende celler for å kunne følge spill-reglene
    public int antallLevende(){
        int antall = 0;
        for(int i = 0;i<antRader;i++){
            for(int z = 0; z<antKolonner;z++){
                if(rutene[i][z].erLevende()){
                    antall++;
                }
            }
        }
        return antall;
    }

}
