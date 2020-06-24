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

	final static int DURCHLÄUFE = 50;
	static HashMap<Integer, Object[]> zeitenListe1 = new HashMap<Integer, Object[]>();
	static HashMap<Integer, Object[]> zeitenll = new HashMap<Integer, Object[]>();
	static HashMap<Integer, Object[]> zeitenArrayl = new HashMap<Integer, Object[]>();

	public static void main(String[]args) throws IOException {

		//Zeitstempel für Textdatei
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		

		//Eigene Linked List
		for(int i = 1; i<=DURCHLÄUFE;i++) {
			final long Liste1Erstellen = System.nanoTime();
			List liste1 = new List(5);
			final long TimeListe1Ende = System.nanoTime();
			//System.out.println("Liste erstellt + erster Eintrag: ");
			//liste1.listeAusgeben();
			final long Liste1EinfügenLetzteStelle = System.nanoTime();
			liste1.einfügenLetzteStelle(4);
			final long Liste1EinfügenLetzteStelleEnde = System.nanoTime();
			final long Liste1EinfügenErsteStelle = System.nanoTime();
			liste1.einfügenErsteStelle(1);
			final long Liste1EinfügenErsteStelleEnde = System.nanoTime();
			//System.out.println("head und tail hinzugefügt: ");
			//liste1.listeAusgeben();
			final long Liste1EinfügenBeliebigeStelle = System.nanoTime();
			liste1.einfügenBeliebigeStelle(50, 1);
			liste1.einfügenBeliebigeStelle(60, 3);
			final long Liste1EinfügenBeliebigeStelleEnde = System.nanoTime();
			//System.out.println("zwei Elemente an beliebiger Stelle hinzugefügt:");
			//liste1.listeAusgeben();
			//liste1.tauschen(2, 0);
			//System.out.println("drittes Element mit erstem getauscht:");
			//liste1.listeAusgeben();
			final long Liste1löschenErsteStelle = System.nanoTime();
			liste1.löschenErsteStelle();
			final long Liste1löschenErsteStelleEnde = System.nanoTime();
			//System.out.println("erstes Element gelöscht: ");
			//liste1.listeAusgeben();
			long dauerErstellen = TimeListe1Ende - Liste1Erstellen;
			long dauerEinfügenLetzteStelle = Liste1EinfügenLetzteStelleEnde - Liste1EinfügenLetzteStelle;
			long dauerEinfügenErsteStelle = Liste1EinfügenErsteStelleEnde - Liste1EinfügenErsteStelle;
			long dauerEinfügenBeliebigeStelle =  Liste1EinfügenBeliebigeStelleEnde - Liste1EinfügenBeliebigeStelle;
			long dauerLöschenErsteStelle =  Liste1löschenErsteStelleEnde - Liste1löschenErsteStelle;
			zeitenListe1.put(i, new Object[] {dauerErstellen, dauerEinfügenLetzteStelle, dauerEinfügenErsteStelle,
					dauerEinfügenBeliebigeStelle, dauerLöschenErsteStelle});
		}
		//JAVA Linked List
		for(int i = 1;i<=DURCHLÄUFE;i++) {
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
			//System.out.println("\thead und tail hinzugefügt: "+ ll);
			//.add(stelle, zahl)
			final long llAddCertain = System.nanoTime();
			ll.add(2, 6);
			ll.add(3, 7);
			final long llAddCertrainEnde= System.nanoTime();
			//System.out.println("\tzwei Elemente an beliebiger Stelle hinzugefügt: "+ll);
			final long llRemoveFirst = System.nanoTime();
			ll.removeFirst();
			final long llRemoveFirstEnde= System.nanoTime();
			//System.out.println("\terstes Element gelöscht: "+ll);
			long dauerErstellen = llErstellenEnde - llErstellen;
			long dauerAddLast = llAddLastEnde - llAddLast;
			long dauerAddFirst = llAddFirstEnde - llAddFirst;
			long dauerAddCertain = llAddCertrainEnde - llAddCertain;
			long dauerRemoveFirst = llRemoveFirstEnde - llRemoveFirst;
			zeitenll.put(i, new Object[] {dauerErstellen, dauerAddLast, dauerAddFirst, dauerAddCertain, dauerRemoveFirst});
		}

		for(int i = 1;i<=DURCHLÄUFE;i++) {
			final long ArrErstellen = System.nanoTime();
			ArrayList<Integer> Arrl = new ArrayList<Integer>();
			Arrl.add(1);
			final long ArrErstellenEnde = System.nanoTime();
			final long ArrAddCertain = System.nanoTime();
			Arrl.add(1, 6);
			Arrl.add(2, 7);
			final long ArrAddCertrainEnde= System.nanoTime();
			//System.out.println("\tzwei Elemente an beliebiger Stelle hinzugefügt: "+ll);
			final long ArrRemoveCertain = System.nanoTime();
			Arrl.remove(2);
			final long ArrRemoveCertainEnde= System.nanoTime();
			//System.out.println("\terstes Element gelöscht: "+ll);
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
			pwListe1.println("\tEinfügenLetzteStelle:\t\t|"+ (zeitenListe1.get(key)[1])+"\t\t\t|"
					+(zeitenll.get(key)[1])+"\t\t\t|"+(zeitenArrayl.get(key)[1]));
			pwListe1.println("\tEinfügenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[2])+"\t\t\t|"
					+(zeitenll.get(key)[2])+"\t\t\t|"+(zeitenArrayl.get(key)[2]));
			pwListe1.println("\tEinfügenBeliebigeStelle:\t|"+ (zeitenListe1.get(key)[3])+"\t\t\t|"
					+(zeitenll.get(key)[3])+"\t\t\t|");
			pwListe1.print("\tLöschenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[4])+"\t\t\t|"
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
			System.out.println("\tEinfügenLetzteStelle:\t\t|"+ (zeitenListe1.get(key)[1])+"\t\t\t|"
					+(zeitenll.get(key)[1])+"\t\t\t|"+(zeitenArrayl.get(key)[1]));
			System.out.println("\tEinfügenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[2])+"\t\t\t|"
					+(zeitenll.get(key)[2])+"\t\t\t|"+(zeitenArrayl.get(key)[2]));
			System.out.println("\tEinfügenBeliebigeStelle:\t|"+ (zeitenListe1.get(key)[3])+"\t\t\t|"
					+(zeitenll.get(key)[3])+"\t\t\t|");
			System.out.print("\tLöschenErsteStelle:\t\t|"+ (zeitenListe1.get(key)[4])+"\t\t\t|"
					+(zeitenll.get(key)[4])+"\t\t\t|");
		}
	}

}
