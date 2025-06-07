//Jeg lager variablene
public class Verden {
   public int genNr = 0;
    public int antRader, antKolonner;
    public Rutenett rutene; 
//Konstruktør hvor jeg lager rutenettet som det skal spilles på
    public Verden(int rad,int kol){
        antRader = rad;
        antKolonner = kol;
        rutene = new Rutenett(antRader,antKolonner); //Viktig å lage new Rutenett etter antRader og Kolonner sånn at de blir ikke 0
        rutene.fyllMedTilfeldigeCeller();
        rutene.kobleAlleCeller();
    }   
    //Med den jeg tegner rutenettet inn i terminalen og skriver informasjon om denne generasjon
    public void tegn(){
        rutene.tegnRutenett();
        int antLevende = rutene.antallLevende();
        System.out.println("Generasjonnummer: " + genNr + ". Antall levende celler: " + antLevende);
    }
    //Metode som dreper eller får celler til å leve, jeg har 2 separate for-løkker fordi den vill ha hatt problemer med å følge hvor mange levende celler det virklig er hvis de var i den samme for-løkke
    public void oppdatering(){
        Celle cell;
        for(int x = 0; x<antRader; x++){
            for(int y=0;y<antKolonner;y++){
                if(rutene.hentCelle(x,y) != null){
                    cell = rutene.hentCelle(x,y);
                    cell.tellLevendeNaboer();
                }
            }
        }
        for(int x = 0; x<antRader; x++){
            for(int y=0;y<antKolonner;y++){
                if(rutene.hentCelle(x,y) != null){
                    cell = rutene.hentCelle(x,y);
                    cell.oppdaterStatus();
                }
            }
        }
        genNr = genNr +1; //Øker generasjon nummer
    }
}
