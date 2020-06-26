import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class List{

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

	final static long DURCHL�UFE = 1000000;

	static long zeitenListe1[];
	static long zeitenll[];
	static long zeitenArrayl[];

	public static long[] zeitmessungEigeneList() {
		long gesamtEinf�genLetzteStelle = 0;
		long gesamtEinf�genErsteStelle = 0;
		long gesamtEinf�genBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtl�nge = 0;
		long gesamtl�schenLetzteStelle = 0;
		long gesamtl�schenErsteStelle = 0;
		long gesamtl�schenBeliebigeStelle = 0;

		for(int i = 1; i<=DURCHL�UFE;i++) {
			List liste1 = new List(5);
			final long Liste1Einf�genLetzteStelle = System.nanoTime();
			liste1.einf�genLetzteStelle(4);
			final long Liste1Einf�genLetzteStelleEnde = System.nanoTime();
			final long Liste1Einf�genErsteStelle = System.nanoTime();
			liste1.einf�genErsteStelle(1);
			final long Liste1Einf�genErsteStelleEnde = System.nanoTime();
			final long Liste1Einf�genBeliebigeStelle = System.nanoTime();
			liste1.einf�genBeliebigeStelle(50, 1);
			final long Liste1Einf�genBeliebigeStelleEnde = System.nanoTime();
			final long Liste1tauschen = System.nanoTime();
			liste1.tauschen(1, 2);
			final long Liste1tauschenEnde = System.nanoTime();
			final long Liste1l�nge = System.nanoTime();
			liste1.listenL�nge();
			final long Liste1l�ngeEnde = System.nanoTime();
			final long Liste1l�schenLetzteStelle = System.nanoTime();
			liste1.l�schenLetzteStelle();
			final long Liste1l�schenLetzteStelleEnde = System.nanoTime();
			final long Liste1l�schenErsteStelle = System.nanoTime();
			liste1.l�schenErsteStelle();
			final long Liste1l�schenErsteStelleEnde = System.nanoTime();
			final long Liste1l�schenBeliebigeStelle = System.nanoTime();
			liste1.l�schenBeliebigeStelle(1);
			final long Liste1l�schenBeliebigeStelleEnde = System.nanoTime();

			long dauerEinf�genLetzteStelle = Liste1Einf�genLetzteStelleEnde - Liste1Einf�genLetzteStelle;
			long dauerEinf�genErsteStelle = Liste1Einf�genErsteStelleEnde - Liste1Einf�genErsteStelle;
			long dauerEinf�genBeliebigeStelle =  Liste1Einf�genBeliebigeStelleEnde - Liste1Einf�genBeliebigeStelle;
			long dauertauschen = Liste1tauschenEnde - Liste1tauschen;
			long dauerl�nge = Liste1l�ngeEnde - Liste1l�nge;
			long dauerL�schenLetzteStelle = Liste1l�schenLetzteStelleEnde - Liste1l�schenLetzteStelle;
			long dauerL�schenErsteStelle =  Liste1l�schenErsteStelleEnde - Liste1l�schenErsteStelle;
			long dauerL�schenBeliebigeStelle = Liste1l�schenBeliebigeStelleEnde - Liste1l�schenBeliebigeStelle;

			gesamtEinf�genLetzteStelle += dauerEinf�genLetzteStelle;
			gesamtEinf�genErsteStelle += dauerEinf�genErsteStelle;
			gesamtEinf�genBeliebigeStelle += dauerEinf�genBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtl�nge += dauerl�nge ;
			gesamtl�schenLetzteStelle += dauerL�schenLetzteStelle;
			gesamtl�schenErsteStelle += dauerL�schenErsteStelle;
			gesamtl�schenBeliebigeStelle += dauerL�schenBeliebigeStelle;

			zeitenListe1 = new long[] {gesamtEinf�genLetzteStelle, gesamtEinf�genErsteStelle, gesamtEinf�genBeliebigeStelle,
					gesamttauschen,gesamtl�nge, gesamtl�schenLetzteStelle, gesamtl�schenErsteStelle, gesamtl�schenBeliebigeStelle};
		}
		return zeitenListe1;
	}
	public static long[] zeitmessungLinkedList(){
		long gesamtEinf�genLetzteStelle = 0;
		long gesamtEinf�genErsteStelle = 0;
		long gesamtEinf�genBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtl�nge = 0;
		long gesamtl�schenLetzteStelle = 0;
		long gesamtl�schenErsteStelle = 0;
		long gesamtl�schenBeliebigeStelle = 0;

		for(int i = 1;i<=DURCHL�UFE;i++) {
			LinkedList<Integer> ll = new LinkedList<Integer>();
			ll.add(2);
			final long llAddLast = System.nanoTime();
			ll.addLast(8);
			final long llAddLastEnde= System.nanoTime();
			final long llAddFirst = System.nanoTime();
			ll.addFirst(4);
			final long llAddFirstEnde= System.nanoTime();
			final long llAddCertain = System.nanoTime();
			ll.add(2, 6);
			final long llAddCertainEnde= System.nanoTime();
			final long llSwap = System.nanoTime();
			Collections.swap(ll,1,2);
			final long llSwapEnde = System.nanoTime();
			final long llL�nge = System.nanoTime();
			ll.size();
			final long llL�ngeEnde = System.nanoTime();
			final long llRemoveLast = System.nanoTime();
			ll.removeLast();
			final long llRemoveLastEnde= System.nanoTime();
			final long llRemoveFirst = System.nanoTime();
			ll.removeFirst();
			final long llRemoveFirstEnde= System.nanoTime();
			final long llRemoveCertain = System.nanoTime();
			ll.remove(0);
			final long llRemoveCertainEnde= System.nanoTime();

			long dauerEinf�genLetzteStelle = llAddLastEnde - llAddLast;
			long dauerEinf�genErsteStelle = llAddFirstEnde - llAddFirst;
			long dauerEinf�genBeliebigeStelle =  llAddCertainEnde - llAddCertain;
			long dauertauschen = llSwapEnde - llSwap;
			long dauerl�nge = llL�ngeEnde - llL�nge;
			long dauerL�schenLetzteStelle = llRemoveLastEnde - llRemoveLast;
			long dauerL�schenErsteStelle =  llRemoveFirstEnde - llRemoveFirst;
			long dauerL�schenBeliebigeStelle = llRemoveCertainEnde - llRemoveCertain;

			gesamtEinf�genLetzteStelle += dauerEinf�genLetzteStelle;
			gesamtEinf�genErsteStelle += dauerEinf�genErsteStelle;
			gesamtEinf�genBeliebigeStelle += dauerEinf�genBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtl�nge += dauerl�nge ;
			gesamtl�schenLetzteStelle += dauerL�schenLetzteStelle;
			gesamtl�schenErsteStelle += dauerL�schenErsteStelle;
			gesamtl�schenBeliebigeStelle += dauerL�schenBeliebigeStelle;

			zeitenll = new long[] {gesamtEinf�genLetzteStelle, gesamtEinf�genErsteStelle, gesamtEinf�genBeliebigeStelle,
					gesamttauschen, gesamtl�nge, gesamtl�schenLetzteStelle, gesamtl�schenErsteStelle, gesamtl�schenBeliebigeStelle};
		}
		return zeitenll;
	}
	public static long[] zeitmessungArrayList() {
		long gesamtEinf�genLetzteStelle = 0;
		long gesamtEinf�genErsteStelle = 0;
		long gesamtEinf�genBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtl�nge = 0;
		long gesamtl�schenLetzteStelle = 0;
		long gesamtl�schenErsteStelle = 0;
		long gesamtl�schenBeliebigeStelle = 0;

		for(int i = 1;i<=DURCHL�UFE;i++) {
			ArrayList<Integer> Arrl = new ArrayList<Integer>();
			Arrl.add(1);
			Arrl.add(3);
			final long ArrEinf�genLetzteStelle = System.nanoTime();
			//Zahl, Stelle
			Arrl.add(1, 1);
			final long ArrEinf�genLetzteStelleEnde = System.nanoTime();
			final long ArrEinf�genErsteStelle = System.nanoTime();
			Arrl.add(1, 0);
			final long ArrEinf�genErsteStelleEnde = System.nanoTime();
			final long ArrAddCertrain= System.nanoTime();
			Arrl.add(1, 1);
			final long ArrAddCertrainEnde= System.nanoTime();
			final long Arrl�nge= System.nanoTime();
			Arrl.size();
			final long Arrl�ngeEnde= System.nanoTime();
			final long ArrRemoveCertain= System.nanoTime();
			//Stelle
			Arrl.remove(2);
			final long ArrRemoveCertainEnde= System.nanoTime();			

			long dauerEinf�genLetzteStelle = ArrEinf�genLetzteStelleEnde - ArrEinf�genLetzteStelle;
			long dauerEinf�genErsteStelle = ArrEinf�genErsteStelleEnde - ArrEinf�genErsteStelle;
			long dauerEinf�genBeliebigeStelle =  ArrAddCertrainEnde - ArrAddCertrain;
			long dauertauschen = 0;
			long dauerl�nge = Arrl�ngeEnde - Arrl�nge;
			long dauerL�schenLetzteStelle = 0;
			long dauerL�schenErsteStelle =  0;
			long dauerL�schenBeliebigeStelle = ArrRemoveCertainEnde - ArrRemoveCertain;

			gesamtEinf�genLetzteStelle += dauerEinf�genLetzteStelle;
			gesamtEinf�genErsteStelle += dauerEinf�genErsteStelle;
			gesamtEinf�genBeliebigeStelle += dauerEinf�genBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtl�nge += dauerl�nge ;
			gesamtl�schenLetzteStelle += dauerL�schenLetzteStelle;
			gesamtl�schenErsteStelle += dauerL�schenErsteStelle;
			gesamtl�schenBeliebigeStelle += dauerL�schenBeliebigeStelle;

			zeitenArrayl = new long[] {gesamtEinf�genLetzteStelle, gesamtEinf�genErsteStelle, gesamtEinf�genBeliebigeStelle,
					gesamttauschen, gesamtl�nge, gesamtl�schenLetzteStelle, gesamtl�schenErsteStelle, gesamtl�schenBeliebigeStelle};
		}
		return zeitenArrayl;
	}
	public static void tabelle() {
		zeitmessungEigeneList();
		zeitmessungLinkedList();
		zeitmessungArrayList();
		System.out.println("Durchl�ufe: "+DURCHL�UFE);
		System.out.println("\t\t\t\t|\tEigene-Liste\t|\tLinked-List\t|\tArray-List\t|");
		System.out.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
		System.out.println("Einf�genLetzteStelle:\t\t|\t"+ (zeitenListe1[0]/DURCHL�UFE) +" ns\t\t|\t"
				+(zeitenll[0]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[0]/DURCHL�UFE)+" ns\t\t|");
		System.out.println("Einf�genErsteStelle:\t\t|\t"+ (zeitenListe1[1]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[1]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[1]/DURCHL�UFE)+" ns\t\t|");
		System.out.println("Einf�genBeliebigeStelle:\t|\t"+ (zeitenListe1[2]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[2]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[2]/DURCHL�UFE)+" ns\t\t|");
		System.out.println("tauschen:\t\t\t|\t"+ (zeitenListe1[3]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[3]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[3]/DURCHL�UFE)*/+"\t\t|");
		System.out.println("l�nge:\t\t\t\t|\t"+ (zeitenListe1[4]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[4]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[4]/DURCHL�UFE)+" ns\t\t|");
		System.out.println("L�schenLetzteStelle:\t\t|\t"+ (zeitenListe1[5]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[5]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[5]/DURCHL�UFE)*/+"\t\t|");
		System.out.println("L�schenErsteStelle:\t\t|\t"+ (zeitenListe1[6]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[6]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[6]/DURCHL�UFE)*/+"\t\t|");
		System.out.println("L�schenBeliebigeStelle:\t\t|\t"+ (zeitenListe1[7]/DURCHL�UFE)+" ns\t\t|\t"
				+(zeitenll[7]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[7]/DURCHL�UFE)+" ns\t\t|");
		System.out.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
	}
	public static void tabelleInTextDatei() {
		//Zeitstempel f�r Textdatei
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		PrintWriter txt;
		try {
			txt = new PrintWriter(new BufferedWriter(new FileWriter("Steurer_Zeiten_"+ sdf.format(timestamp) +".txt")));
			txt.println("Durchl�ufe: "+DURCHL�UFE);
			txt.println("\t\t\t\t|\tEigene-Liste\t|\tLinked-List\t|\tArray-List\t|");
			txt.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
			zeitmessungEigeneList();
			zeitmessungLinkedList();
			zeitmessungArrayList();
			txt.println("Einf�genLetzteStelle:\t\t|\t"+ (zeitenListe1[0]/DURCHL�UFE) +" ns\t\t|\t"
					+(zeitenll[0]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[0]/DURCHL�UFE)+" ns\t\t|");
			txt.println("Einf�genErsteStelle:\t\t|\t"+ (zeitenListe1[1]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[1]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[1]/DURCHL�UFE)+" ns\t\t|");
			txt.println("Einf�genBeliebigeStelle:\t|\t"+ (zeitenListe1[2]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[2]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[2]/DURCHL�UFE)+" ns\t\t|");
			txt.println("tauschen:\t\t\t|\t"+ (zeitenListe1[3]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[3]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[3]/DURCHL�UFE)*/+"\t\t|");
			txt.println("l�nge:\t\t\t\t|\t"+ (zeitenListe1[4]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[4]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[4]/DURCHL�UFE)+" ns\t\t|");
			txt.println("L�schenLetzteStelle:\t\t|\t"+ (zeitenListe1[5]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[5]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[5]/DURCHL�UFE)*/+"\t\t|");
			txt.println("L�schenErsteStelle:\t\t|\t"+ (zeitenListe1[6]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[6]/DURCHL�UFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[6]/DURCHL�UFE)*/+"\t\t|");
			txt.println("L�schenBeliebigeStelle:\t\t|\t"+ (zeitenListe1[7]/DURCHL�UFE)+" ns\t\t|\t"
					+(zeitenll[7]/DURCHL�UFE)+" ns\t\t|\t"+ (zeitenArrayl[7]/DURCHL�UFE)+" ns\t\t|");
			txt.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
			//Mit .flush(); werden die Dateien vom Puffer in die Textdatei geladen();
			txt.flush();
			txt.close();
		} catch (IOException e) {
			System.out.println("[tabelleInTextDatei()]:"+ e);
		}
	}	

	public static void main(String[]args) {
		//Consolenausgabe
		tabelle();
		//Ausgabe in Textdatei
		tabelleInTextDatei();
	}
	
}