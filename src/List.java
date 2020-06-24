import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class List {

	public Elem tail;
	public Elem head;

	public List(int z) {
		this.head = new Elem(z);
		this.tail = head;
	}
	//zeigt l�nge der Liste an
	public int listenL�nge() {
		int laenge=1;
		Elem aktuellerHead = head;
		while(aktuellerHead != tail) {
			laenge++;
			aktuellerHead = aktuellerHead.nextAddress;
		}
		//System.out.println("l�nge: "+laenge);
		return laenge;
	}
	private Elem getElemByIndex(int index) {
		Elem aktuellerHead = head;
		int zaehler = 0;
		while (index != zaehler) {
			aktuellerHead = aktuellerHead.nextAddress;
			zaehler++;
			if (aktuellerHead == null) {
				System.out.println("[getElemByIndex] Es existiert kein Element mit index " + index + ".");
				return null;
			}
		}
		return aktuellerHead;
	}
	private int getIndexByElem(Elem e) {
		int i = 0;
		Elem aktuellerHead = head;
		while (aktuellerHead != e) {
			i++;
			aktuellerHead = aktuellerHead.nextAddress;
		}
		return i;
	}
	//Eintrag an letzter Stelle hinzuf�gen	
	public void einf�genLetzteStelle(int z) {
		einf�genBeliebigeStelle(z, listenL�nge());
	}
	//Eintrag an erster Stelle hinzuf�gen
	public void einf�genErsteStelle(int z) {
		einf�genBeliebigeStelle(z, getIndexByElem(head));
	}
	//Eintrag an beliebiger Stelle hinzuf�gen
	public void einf�genBeliebigeStelle(int z, int stelle ) {
		if(stelle<0) {
			System.out.println("[einf�genBeliebigeStelle] index muss positiv sein!");
			return;
		}
		if (stelle > listenL�nge()) {
			System.out.println("[einf�genBeliebigeStelle] index muss kleiner als die laenge der Liste sein!");
			return;
		}
		Elem newElem = new Elem(z);
		if(stelle == listenL�nge()) {
			this.tail.nextAddress = newElem;
			newElem.prevAddress = tail;
			tail = newElem;
			return;
		}
		if(stelle== getIndexByElem(head)) {
			head.prevAddress = newElem;
			newElem.nextAddress=head;
			head = newElem;
			return;
		}
		Elem nextElem = getElemByIndex(stelle);
		Elem prevElem = getElemByIndex(stelle-1);
		newElem.prevAddress = prevElem;
		newElem.nextAddress = nextElem;
		nextElem.prevAddress = newElem;
		prevElem.nextAddress = newElem;
	}
	//Element an letzter Stelle l�schen
	public void l�schenLetzteStelle() {
		l�schenBeliebigeStelle(getIndexByElem(tail));
	}
	//Element an erster Stelle l�schen
	public void l�schenErsteStelle() {
		l�schenBeliebigeStelle(getIndexByElem(head));
	}
	//Element an beliebiger Stelle l�schen
	public void l�schenBeliebigeStelle(int stelle) {
		if(stelle < 0) {
			System.out.println("[l�schenBeliebigeStelle] index muss positiv sein!"); 
			return;
		}
		if(stelle >= listenL�nge()) {
			System.out.println("[l�schenBeliebigeStelle] index muss kleiner als laenge sein!");
			return;
		}
		if(stelle == getIndexByElem(head)) {
			head = head.nextAddress;
			return;
		}
		if (stelle == getIndexByElem(tail)) {
			tail = tail.prevAddress;
			return;
		}
		Elem elemL�schen = getElemByIndex(stelle);
		Elem n�chstesE = elemL�schen.nextAddress;
		Elem letztesE = elemL�schen.prevAddress;
		n�chstesE.prevAddress = letztesE;
		letztesE.nextAddress = n�chstesE;	
	}
	//zwei Elemente miteinander tauschen
	public void tauschen(int stelle1, int stelle2) {
		if(stelle1 == stelle2) {
			System.out.println("[tauschen] es gibt nichts zu vertauschen.");
			return;
		}
		if(stelle1 > stelle2) {
			int tmp = stelle1;
			stelle1 = stelle2;
			stelle2 = tmp;
		}
		Elem tausch1 = getElemByIndex(stelle1);
		Elem tausch2 = getElemByIndex(stelle2);
		if(tausch1 == null || tausch2 == null) {
			System.out.println("[tauschen] falsche eingabe");
			return;
		}
		if(tausch1 == head) head = tausch2;
		if(tausch2 == tail) tail = tausch1;
		if(tausch1.nextAddress == tausch2) {
			tausch1.nextAddress = tausch2.nextAddress;
			tausch2.prevAddress = tausch1.prevAddress;
			if(tausch1.nextAddress!=null) {
				tausch1.nextAddress.prevAddress = tausch1;
			}
			if(tausch2.prevAddress!=null) {
				tausch1.prevAddress.nextAddress = tausch2;
			}
			tausch2.nextAddress = tausch1;
			tausch1.prevAddress = tausch2;
		}else {
			Elem p = tausch2.prevAddress;
			Elem n = tausch2.nextAddress;

			tausch2.prevAddress = tausch1.prevAddress;
			tausch2.nextAddress = tausch1.nextAddress;

			tausch1.prevAddress = p;
			tausch1.nextAddress = n;

			if (tausch2.nextAddress != null) {
				tausch2.nextAddress.prevAddress = tausch2;
			}
			if (tausch2.prevAddress != null) {
				tausch2.prevAddress.nextAddress = tausch2;
			}

			if (tausch1.nextAddress != null) {
				tausch1.nextAddress.prevAddress = tausch1;
			}
			if (tausch1.prevAddress != null) {
				tausch1.prevAddress.nextAddress = tausch1;
			}
		}
	}
	//Liste mit allen Eintr�gen wird ausgegeben
	public void listeAusgeben() {
		Elem aktuellerHead = head;
		for(int i = 1; i<= listenL�nge();i++){
			System.out.println("\tWert: "+aktuellerHead.zahl+"\n\tvorherige Adresse: "+aktuellerHead.prevAddress
					+"\n\tn�chste Adressse: "+aktuellerHead.prevAddress);
			aktuellerHead = aktuellerHead.nextAddress;
		}
	}

	final static int DURCHL�UFE = 50;
	static HashMap<Integer, Object[]> zeitenListe1 = new HashMap<Integer, Object[]>();
	static HashMap<Integer, Object[]> zeitenll = new HashMap<Integer, Object[]>();
	static HashMap<Integer, Object[]> zeitenArrayl = new HashMap<Integer, Object[]>();

	public static void main(String[]args) throws IOException {

		//Zeitstempel f�r Textdatei
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		

		//Eigene Linked List
		for(int i = 1; i<=DURCHL�UFE;i++) {
			final long Liste1Erstellen = System.nanoTime();
			List liste1 = new List(5);
			final long TimeListe1Ende = System.nanoTime();
			//System.out.println("Liste erstellt + erster Eintrag: ");
			//liste1.listeAusgeben();
			final long Liste1Einf�genLetzteStelle = System.nanoTime();
			liste1.einf�genLetzteStelle(4);
			final long Liste1Einf�genLetzteStelleEnde = System.nanoTime();
			final long Liste1Einf�genErsteStelle = System.nanoTime();
			liste1.einf�genErsteStelle(1);
			final long Liste1Einf�genErsteStelleEnde = System.nanoTime();
			//System.out.println("head und tail hinzugef�gt: ");
			//liste1.listeAusgeben();
			final long Liste1Einf�genBeliebigeStelle = System.nanoTime();
			liste1.einf�genBeliebigeStelle(50, 1);
			liste1.einf�genBeliebigeStelle(60, 3);
			final long Liste1Einf�genBeliebigeStelleEnde = System.nanoTime();
			//System.out.println("zwei Elemente an beliebiger Stelle hinzugef�gt:");
			//liste1.listeAusgeben();
			//liste1.tauschen(2, 0);
			//System.out.println("drittes Element mit erstem getauscht:");
			//liste1.listeAusgeben();
			final long Liste1l�schenErsteStelle = System.nanoTime();
			liste1.l�schenErsteStelle();
			final long Liste1l�schenErsteStelleEnde = System.nanoTime();
			//System.out.println("erstes Element gel�scht: ");
			//liste1.listeAusgeben();
			long dauerErstellen = TimeListe1Ende - Liste1Erstellen;
			long dauerEinf�genLetzteStelle = Liste1Einf�genLetzteStelleEnde - Liste1Einf�genLetzteStelle;
			long dauerEinf�genErsteStelle = Liste1Einf�genErsteStelleEnde - Liste1Einf�genErsteStelle;
			long dauerEinf�genBeliebigeStelle =  Liste1Einf�genBeliebigeStelleEnde - Liste1Einf�genBeliebigeStelle;
			long dauerL�schenErsteStelle =  Liste1l�schenErsteStelleEnde - Liste1l�schenErsteStelle;
			zeitenListe1.put(i, new Object[] {dauerErstellen, dauerEinf�genLetzteStelle, dauerEinf�genErsteStelle,
					dauerEinf�genBeliebigeStelle, dauerL�schenErsteStelle});
		}
		//JAVA Linked List
		for(int i = 1;i<=DURCHL�UFE;i++) {
			final long llErstellen = System.nanoTime();
			LinkedList<Integer> ll = new LinkedList<Integer>();
			ll.add(1);
			final long llErstellenEnde = System.nanoTime();
			//System.out.println("\tListe erstellt + erster Eintrag: "+ ll);
			final long llAddLast = System.nanoTime();
			ll.addLast(8);
			final long llAddLastEnde= System.nanoTime();
			final long llAddFirst = System.nanoTime();
			ll.addFirst(4);
			final long llAddFirstEnde= System.nanoTime();
			//System.out.println("\thead und tail hinzugef�gt: "+ ll);
			//.add(stelle, zahl)
			final long llAddCertain = System.nanoTime();
			ll.add(2, 6);
			ll.add(3, 7);
			final long llAddCertrainEnde= System.nanoTime();
			//System.out.println("\tzwei Elemente an beliebiger Stelle hinzugef�gt: "+ll);
			final long llRemoveFirst = System.nanoTime();
			ll.removeFirst();
			final long llRemoveFirstEnde= System.nanoTime();
			//System.out.println("\terstes Element gel�scht: "+ll);
			long dauerErstellen = llErstellenEnde - llErstellen;
			long dauerAddLast = llAddLastEnde - llAddLast;
			long dauerAddFirst = llAddFirstEnde - llAddFirst;
			long dauerAddCertain = llAddCertrainEnde - llAddCertain;
			long dauerRemoveFirst = llRemoveFirstEnde - llRemoveFirst;
			zeitenll.put(i, new Object[] {dauerErstellen, dauerAddLast, dauerAddFirst, dauerAddCertain, dauerRemoveFirst});
		}

		for(int i = 1;i<=DURCHL�UFE;i++) {
			final long ArrErstellen = System.nanoTime();
			ArrayList<Integer> Arrl = new ArrayList<Integer>();
			Arrl.add(1);
			final long ArrErstellenEnde = System.nanoTime();
			final long ArrAddCertain = System.nanoTime();
			Arrl.add(1, 6);
			Arrl.add(2, 7);
			final long ArrAddCertrainEnde= System.nanoTime();
			//System.out.println("\tzwei Elemente an beliebiger Stelle hinzugef�gt: "+ll);
			final long ArrRemoveCertain = System.nanoTime();
			Arrl.remove(2);
			final long ArrRemoveCertainEnde= System.nanoTime();
			//System.out.println("\terstes Element gel�scht: "+ll);
			long dauerErstellen = ArrErstellenEnde - ArrErstellen;
			long dauerAddCertain = ArrAddCertrainEnde - ArrAddCertain;
			long dauerRemoveCertain = ArrRemoveCertainEnde - ArrRemoveCertain;
			zeitenArrayl.put(i, new Object[] {dauerErstellen, dauerAddCertain, dauerRemoveCertain});
		}

		PrintWriter pwListe1 = new PrintWriter(new BufferedWriter(new FileWriter("Steurer_Zeiten_"+ sdf.format(timestamp) +".txt")));

		pwListe1.println("\t\t\t\t\t|Eigene-Liste\t\t|Linked-List\t\t|Array-List|");
		for(int key : zeitenListe1.keySet()) {
			try {
				pwListe1.println("\nDurchlauf: "+key+"\t------------------------|-----------------------|-----------------------|");
			pwListe1.println("\tErstellen:\t\t\t|"+ (zeitenListe1.get(key)[0])+"\t\t\t|"
					+(zeitenll.get(key)[0])+"\t\t\t|"+(zeitenArrayl.get(key)[0]));
			pwListe1.println("\tEinf�genLetzteStelle:\t\t|"+ (zeitenListe1.get(key)[1])+"\t\t\t|"
					+(zeitenll.get(key)[1])+"\t\t\t|"+(zeitenArrayl.get(key)[1]));
			pwListe1.println("\tEinf�genErsteStelle:\t\t|"+ (zeitenListe1.get(key)[2])+"\t\t\t|"
					+(zeitenll.get(key)[2])+"\t\t\t|"+(zeitenArrayl.get(key)[2]));
			pwListe1.println("\tEinf�genBeliebigeStelle:\t|"+ (zeitenListe1.get(key)[3])+"\t\t\t|"
					+(zeitenll.get(key)[3])+"\t\t\t|");
			pwListe1.print("\tL�schenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[4])+"\t\t\t|"
					+(zeitenll.get(key)[4])+"\t\t\t|");
			//Mit .flush(); werden die Dateien vom Puffer in die Textdatei geladen();
			pwListe1.flush();
			}catch(Exception e){
				System.out.println("Fehler!!: "+e);
			}
		}
		pwListe1.close();

		//Consolenausgabe
		System.out.println("\t\t\t\t\t|Eigene-Liste\t\t|Linked-List\t\t|Array-List|");
		for(int key : zeitenListe1.keySet()) {
			System.err.println("\nDurchlauf: "+key+"\t------------------------|-----------------------|-----------------------|");
			System.out.println("\tErstellen:\t\t\t|"+ (zeitenListe1.get(key)[0])+"\t\t\t|"
					+(zeitenll.get(key)[0])+"\t\t\t|"+(zeitenArrayl.get(key)[0]));
			System.out.println("\tEinf�genLetzteStelle:\t\t|"+ (zeitenListe1.get(key)[1])+"\t\t\t|"
					+(zeitenll.get(key)[1])+"\t\t\t|"+(zeitenArrayl.get(key)[1]));
			System.out.println("\tEinf�genErsteStelle:\t\t|"+ (zeitenListe1.get(key)[2])+"\t\t\t|"
					+(zeitenll.get(key)[2])+"\t\t\t|"+(zeitenArrayl.get(key)[2]));
			System.out.println("\tEinf�genBeliebigeStelle:\t|"+ (zeitenListe1.get(key)[3])+"\t\t\t|"
					+(zeitenll.get(key)[3])+"\t\t\t|");
			System.out.print("\tL�schenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[4])+"\t\t\t|"
					+(zeitenll.get(key)[4])+"\t\t\t|");
		}
	}

}
