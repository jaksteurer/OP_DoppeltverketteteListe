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
	//zeigt länge der Liste an
	public int listenLänge() {
		int laenge=1;
		Elem aktuellerHead = head;
		while(aktuellerHead != tail) {
			laenge++;
			aktuellerHead = aktuellerHead.nextAddress;
		}
		//System.out.println("länge: "+laenge);
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
	//Eintrag an letzter Stelle hinzufügen	
	public void einfügenLetzteStelle(int z) {
		einfügenBeliebigeStelle(z, listenLänge());
	}
	//Eintrag an erster Stelle hinzufügen
	public void einfügenErsteStelle(int z) {
		einfügenBeliebigeStelle(z, getIndexByElem(head));
	}
	//Eintrag an beliebiger Stelle hinzufügen
	public void einfügenBeliebigeStelle(int z, int stelle ) {
		if(stelle<0) {
			System.out.println("[einfügenBeliebigeStelle] index muss positiv sein!");
			return;
		}
		if (stelle > listenLänge()) {
			System.out.println("[einfügenBeliebigeStelle] index muss kleiner als die laenge der Liste sein!");
			return;
		}
		Elem newElem = new Elem(z);
		if(stelle == listenLänge()) {
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
	//Element an letzter Stelle löschen
	public void löschenLetzteStelle() {
		löschenBeliebigeStelle(getIndexByElem(tail));
	}
	//Element an erster Stelle löschen
	public void löschenErsteStelle() {
		löschenBeliebigeStelle(getIndexByElem(head));
	}
	//Element an beliebiger Stelle löschen
	public void löschenBeliebigeStelle(int stelle) {
		if(stelle < 0) {
			System.out.println("[löschenBeliebigeStelle] index muss positiv sein!"); 
			return;
		}
		if(stelle >= listenLänge()) {
			System.out.println("[löschenBeliebigeStelle] index muss kleiner als laenge sein!");
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
		Elem elemLöschen = getElemByIndex(stelle);
		Elem nächstesE = elemLöschen.nextAddress;
		Elem letztesE = elemLöschen.prevAddress;
		nächstesE.prevAddress = letztesE;
		letztesE.nextAddress = nächstesE;	
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
	//Liste mit allen Einträgen wird ausgegeben
	public void listeAusgeben() {
		Elem aktuellerHead = head;
		for(int i = 1; i<= listenLänge();i++){
			System.out.println("\tWert: "+aktuellerHead.zahl+"\n\tvorherige Adresse: "+aktuellerHead.prevAddress
					+"\n\tnächste Adressse: "+aktuellerHead.prevAddress);
			aktuellerHead = aktuellerHead.nextAddress;
		}
	}

	final static long DURCHLÄUFE = 1000000;

	static long zeitenListe1[];
	static long zeitenll[];
	static long zeitenArrayl[];

	public static long[] zeitmessungEigeneList() {
		long gesamtEinfügenLetzteStelle = 0;
		long gesamtEinfügenErsteStelle = 0;
		long gesamtEinfügenBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtlänge = 0;
		long gesamtlöschenLetzteStelle = 0;
		long gesamtlöschenErsteStelle = 0;
		long gesamtlöschenBeliebigeStelle = 0;

		for(int i = 1; i<=DURCHLÄUFE;i++) {
			List liste1 = new List(5);
			final long Liste1EinfügenLetzteStelle = System.nanoTime();
			liste1.einfügenLetzteStelle(4);
			final long Liste1EinfügenLetzteStelleEnde = System.nanoTime();
			final long Liste1EinfügenErsteStelle = System.nanoTime();
			liste1.einfügenErsteStelle(1);
			final long Liste1EinfügenErsteStelleEnde = System.nanoTime();
			final long Liste1EinfügenBeliebigeStelle = System.nanoTime();
			liste1.einfügenBeliebigeStelle(50, 1);
			final long Liste1EinfügenBeliebigeStelleEnde = System.nanoTime();
			final long Liste1tauschen = System.nanoTime();
			liste1.tauschen(1, 2);
			final long Liste1tauschenEnde = System.nanoTime();
			final long Liste1länge = System.nanoTime();
			liste1.listenLänge();
			final long Liste1längeEnde = System.nanoTime();
			final long Liste1löschenLetzteStelle = System.nanoTime();
			liste1.löschenLetzteStelle();
			final long Liste1löschenLetzteStelleEnde = System.nanoTime();
			final long Liste1löschenErsteStelle = System.nanoTime();
			liste1.löschenErsteStelle();
			final long Liste1löschenErsteStelleEnde = System.nanoTime();
			final long Liste1löschenBeliebigeStelle = System.nanoTime();
			liste1.löschenBeliebigeStelle(1);
			final long Liste1löschenBeliebigeStelleEnde = System.nanoTime();

			long dauerEinfügenLetzteStelle = Liste1EinfügenLetzteStelleEnde - Liste1EinfügenLetzteStelle;
			long dauerEinfügenErsteStelle = Liste1EinfügenErsteStelleEnde - Liste1EinfügenErsteStelle;
			long dauerEinfügenBeliebigeStelle =  Liste1EinfügenBeliebigeStelleEnde - Liste1EinfügenBeliebigeStelle;
			long dauertauschen = Liste1tauschenEnde - Liste1tauschen;
			long dauerlänge = Liste1längeEnde - Liste1länge;
			long dauerLöschenLetzteStelle = Liste1löschenLetzteStelleEnde - Liste1löschenLetzteStelle;
			long dauerLöschenErsteStelle =  Liste1löschenErsteStelleEnde - Liste1löschenErsteStelle;
			long dauerLöschenBeliebigeStelle = Liste1löschenBeliebigeStelleEnde - Liste1löschenBeliebigeStelle;

			gesamtEinfügenLetzteStelle += dauerEinfügenLetzteStelle;
			gesamtEinfügenErsteStelle += dauerEinfügenErsteStelle;
			gesamtEinfügenBeliebigeStelle += dauerEinfügenBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtlänge += dauerlänge ;
			gesamtlöschenLetzteStelle += dauerLöschenLetzteStelle;
			gesamtlöschenErsteStelle += dauerLöschenErsteStelle;
			gesamtlöschenBeliebigeStelle += dauerLöschenBeliebigeStelle;

			zeitenListe1 = new long[] {gesamtEinfügenLetzteStelle, gesamtEinfügenErsteStelle, gesamtEinfügenBeliebigeStelle,
					gesamttauschen,gesamtlänge, gesamtlöschenLetzteStelle, gesamtlöschenErsteStelle, gesamtlöschenBeliebigeStelle};
		}
		return zeitenListe1;
	}
	public static long[] zeitmessungLinkedList(){
		long gesamtEinfügenLetzteStelle = 0;
		long gesamtEinfügenErsteStelle = 0;
		long gesamtEinfügenBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtlänge = 0;
		long gesamtlöschenLetzteStelle = 0;
		long gesamtlöschenErsteStelle = 0;
		long gesamtlöschenBeliebigeStelle = 0;

		for(int i = 1;i<=DURCHLÄUFE;i++) {
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
			final long llLänge = System.nanoTime();
			ll.size();
			final long llLängeEnde = System.nanoTime();
			final long llRemoveLast = System.nanoTime();
			ll.removeLast();
			final long llRemoveLastEnde= System.nanoTime();
			final long llRemoveFirst = System.nanoTime();
			ll.removeFirst();
			final long llRemoveFirstEnde= System.nanoTime();
			final long llRemoveCertain = System.nanoTime();
			ll.remove(0);
			final long llRemoveCertainEnde= System.nanoTime();

			long dauerEinfügenLetzteStelle = llAddLastEnde - llAddLast;
			long dauerEinfügenErsteStelle = llAddFirstEnde - llAddFirst;
			long dauerEinfügenBeliebigeStelle =  llAddCertainEnde - llAddCertain;
			long dauertauschen = llSwapEnde - llSwap;
			long dauerlänge = llLängeEnde - llLänge;
			long dauerLöschenLetzteStelle = llRemoveLastEnde - llRemoveLast;
			long dauerLöschenErsteStelle =  llRemoveFirstEnde - llRemoveFirst;
			long dauerLöschenBeliebigeStelle = llRemoveCertainEnde - llRemoveCertain;

			gesamtEinfügenLetzteStelle += dauerEinfügenLetzteStelle;
			gesamtEinfügenErsteStelle += dauerEinfügenErsteStelle;
			gesamtEinfügenBeliebigeStelle += dauerEinfügenBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtlänge += dauerlänge ;
			gesamtlöschenLetzteStelle += dauerLöschenLetzteStelle;
			gesamtlöschenErsteStelle += dauerLöschenErsteStelle;
			gesamtlöschenBeliebigeStelle += dauerLöschenBeliebigeStelle;

			zeitenll = new long[] {gesamtEinfügenLetzteStelle, gesamtEinfügenErsteStelle, gesamtEinfügenBeliebigeStelle,
					gesamttauschen, gesamtlänge, gesamtlöschenLetzteStelle, gesamtlöschenErsteStelle, gesamtlöschenBeliebigeStelle};
		}
		return zeitenll;
	}
	public static long[] zeitmessungArrayList() {
		long gesamtEinfügenLetzteStelle = 0;
		long gesamtEinfügenErsteStelle = 0;
		long gesamtEinfügenBeliebigeStelle = 0;
		long gesamttauschen = 0;
		long gesamtlänge = 0;
		long gesamtlöschenLetzteStelle = 0;
		long gesamtlöschenErsteStelle = 0;
		long gesamtlöschenBeliebigeStelle = 0;

		for(int i = 1;i<=DURCHLÄUFE;i++) {
			ArrayList<Integer> Arrl = new ArrayList<Integer>();
			Arrl.add(1);
			Arrl.add(3);
			final long ArrEinfügenLetzteStelle = System.nanoTime();
			//Zahl, Stelle
			Arrl.add(1, 1);
			final long ArrEinfügenLetzteStelleEnde = System.nanoTime();
			final long ArrEinfügenErsteStelle = System.nanoTime();
			Arrl.add(1, 0);
			final long ArrEinfügenErsteStelleEnde = System.nanoTime();
			final long ArrAddCertrain= System.nanoTime();
			Arrl.add(1, 1);
			final long ArrAddCertrainEnde= System.nanoTime();
			final long Arrlänge= System.nanoTime();
			Arrl.size();
			final long ArrlängeEnde= System.nanoTime();
			final long ArrRemoveCertain= System.nanoTime();
			//Stelle
			Arrl.remove(2);
			final long ArrRemoveCertainEnde= System.nanoTime();			

			long dauerEinfügenLetzteStelle = ArrEinfügenLetzteStelleEnde - ArrEinfügenLetzteStelle;
			long dauerEinfügenErsteStelle = ArrEinfügenErsteStelleEnde - ArrEinfügenErsteStelle;
			long dauerEinfügenBeliebigeStelle =  ArrAddCertrainEnde - ArrAddCertrain;
			long dauertauschen = 0;
			long dauerlänge = ArrlängeEnde - Arrlänge;
			long dauerLöschenLetzteStelle = 0;
			long dauerLöschenErsteStelle =  0;
			long dauerLöschenBeliebigeStelle = ArrRemoveCertainEnde - ArrRemoveCertain;

			gesamtEinfügenLetzteStelle += dauerEinfügenLetzteStelle;
			gesamtEinfügenErsteStelle += dauerEinfügenErsteStelle;
			gesamtEinfügenBeliebigeStelle += dauerEinfügenBeliebigeStelle;
			gesamttauschen += dauertauschen;
			gesamtlänge += dauerlänge ;
			gesamtlöschenLetzteStelle += dauerLöschenLetzteStelle;
			gesamtlöschenErsteStelle += dauerLöschenErsteStelle;
			gesamtlöschenBeliebigeStelle += dauerLöschenBeliebigeStelle;

			zeitenArrayl = new long[] {gesamtEinfügenLetzteStelle, gesamtEinfügenErsteStelle, gesamtEinfügenBeliebigeStelle,
					gesamttauschen, gesamtlänge, gesamtlöschenLetzteStelle, gesamtlöschenErsteStelle, gesamtlöschenBeliebigeStelle};
		}
		return zeitenArrayl;
	}
	public static void tabelle() {
		zeitmessungEigeneList();
		zeitmessungLinkedList();
		zeitmessungArrayList();
		System.out.println("Durchläufe: "+DURCHLÄUFE);
		System.out.println("\t\t\t\t|\tEigene-Liste\t|\tLinked-List\t|\tArray-List\t|");
		System.out.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
		System.out.println("EinfügenLetzteStelle:\t\t|\t"+ (zeitenListe1[0]/DURCHLÄUFE) +" ns\t\t|\t"
				+(zeitenll[0]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[0]/DURCHLÄUFE)+" ns\t\t|");
		System.out.println("EinfügenErsteStelle:\t\t|\t"+ (zeitenListe1[1]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[1]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[1]/DURCHLÄUFE)+" ns\t\t|");
		System.out.println("EinfügenBeliebigeStelle:\t|\t"+ (zeitenListe1[2]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[2]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[2]/DURCHLÄUFE)+" ns\t\t|");
		System.out.println("tauschen:\t\t\t|\t"+ (zeitenListe1[3]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[3]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[3]/DURCHLÄUFE)*/+"\t\t|");
		System.out.println("länge:\t\t\t\t|\t"+ (zeitenListe1[4]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[4]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[4]/DURCHLÄUFE)+" ns\t\t|");
		System.out.println("LöschenLetzteStelle:\t\t|\t"+ (zeitenListe1[5]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[5]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[5]/DURCHLÄUFE)*/+"\t\t|");
		System.out.println("LöschenErsteStelle:\t\t|\t"+ (zeitenListe1[6]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[6]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[6]/DURCHLÄUFE)*/+"\t\t|");
		System.out.println("LöschenBeliebigeStelle:\t\t|\t"+ (zeitenListe1[7]/DURCHLÄUFE)+" ns\t\t|\t"
				+(zeitenll[7]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[7]/DURCHLÄUFE)+" ns\t\t|");
		System.out.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
	}
	public static void tabelleInTextDatei() {
		//Zeitstempel für Textdatei
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		PrintWriter txt;
		try {
			txt = new PrintWriter(new BufferedWriter(new FileWriter("Steurer_Zeiten_"+ sdf.format(timestamp) +".txt")));
			txt.println("Durchläufe: "+DURCHLÄUFE);
			txt.println("\t\t\t\t|\tEigene-Liste\t|\tLinked-List\t|\tArray-List\t|");
			txt.println("--------------------------------|-----------------------|-----------------------|-----------------------|");
			zeitmessungEigeneList();
			zeitmessungLinkedList();
			zeitmessungArrayList();
			txt.println("EinfügenLetzteStelle:\t\t|\t"+ (zeitenListe1[0]/DURCHLÄUFE) +" ns\t\t|\t"
					+(zeitenll[0]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[0]/DURCHLÄUFE)+" ns\t\t|");
			txt.println("EinfügenErsteStelle:\t\t|\t"+ (zeitenListe1[1]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[1]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[1]/DURCHLÄUFE)+" ns\t\t|");
			txt.println("EinfügenBeliebigeStelle:\t|\t"+ (zeitenListe1[2]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[2]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[2]/DURCHLÄUFE)+" ns\t\t|");
			txt.println("tauschen:\t\t\t|\t"+ (zeitenListe1[3]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[3]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[3]/DURCHLÄUFE)*/+"\t\t|");
			txt.println("länge:\t\t\t\t|\t"+ (zeitenListe1[4]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[4]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[4]/DURCHLÄUFE)+" ns\t\t|");
			txt.println("LöschenLetzteStelle:\t\t|\t"+ (zeitenListe1[5]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[5]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[5]/DURCHLÄUFE)*/+"\t\t|");
			txt.println("LöschenErsteStelle:\t\t|\t"+ (zeitenListe1[6]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[6]/DURCHLÄUFE)+" ns\t\t|\t"+ "/"/*(zeitenArrayl[6]/DURCHLÄUFE)*/+"\t\t|");
			txt.println("LöschenBeliebigeStelle:\t\t|\t"+ (zeitenListe1[7]/DURCHLÄUFE)+" ns\t\t|\t"
					+(zeitenll[7]/DURCHLÄUFE)+" ns\t\t|\t"+ (zeitenArrayl[7]/DURCHLÄUFE)+" ns\t\t|");
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