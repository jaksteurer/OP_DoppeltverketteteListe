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
	
	//Vergleich mit LinkedList
	final static int DURCHL�UFE = 2;
	static HashMap<Integer, Object[]> zeitenListe1 = new HashMap<Integer, Object[]>();
	static HashMap<Integer, Object[]> zeitenll = new HashMap<Integer, Object[]>();
	
	public static void main(String[]args) {
		
		System.out.println("-------------------Liste1-------------------");
		for(int i = 0; i<=DURCHL�UFE;i++) {
		final long TimeListe1Start = System.currentTimeMillis();
		List liste1 = new List(5);
		System.out.println("Liste erstellt + erster Eintrag: ");
		liste1.listeAusgeben();
		liste1.einf�genLetzteStelle(4);
		liste1.einf�genErsteStelle(1);
		System.out.println("head und tail hinzugef�gt: ");
		liste1.listeAusgeben();
		liste1.einf�genBeliebigeStelle(50, 1);
		liste1.einf�genBeliebigeStelle(60, 3);
		System.out.println("zwei Elemente an beliebiger Stelle hinzugef�gt:");
		liste1.listeAusgeben();
//		liste1.tauschen(2, 0);
//		System.out.println("drittes Element mit erstem getauscht:");
		//liste1.listeAusgeben();
		liste1.l�schenErsteStelle();
		System.out.println("erstes Element gel�scht: ");
		liste1.listeAusgeben();
		final long TimeListe1Ende = System.currentTimeMillis();
		long dauer = TimeListe1Ende - TimeListe1Start;
		System.out.println("\tDauer der eigenen Liste: "
				+dauer+" Millisekunden.");
		zeitenListe1.put(i, new Object[] {dauer});
		System.out.println("----------------------");
		}
		
		System.out.println("\n--------------Java Linked List--------------");
		for(int i = 0;i<=DURCHL�UFE;i++) {
		final long TimellStart = System.currentTimeMillis();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.add(1);
		System.out.println("\tListe erstellt + erster Eintrag: "+ ll);
		ll.addLast(8);
		ll.addFirst(4);
		System.out.println("\thead und tail hinzugef�gt: "+ ll);
		//.add(stelle, zahl)
		ll.add(2, 6);
		ll.add(3, 7);
		System.out.println("\tzwei Elemente an beliebiger Stelle hinzugef�gt: "+ll);
		ll.removeFirst();
		System.out.println("\terstes Element gel�scht: "+ll);
		final long TimellEnde= System.currentTimeMillis();
		long dauer = TimellEnde - TimellStart;
		System.out.println("\tDauer der eigenen Liste: "
				+dauer+" Millisekunden.");
		zeitenll.put(i, new Object[] {dauer});
		System.out.println("----------------------");
		}
		System.out.println("\n---------Dauer der Listendurchl�ufe---------");
		System.out.println("---Zeiten Liste 1---");
		for(int key : zeitenListe1.keySet()) {
			System.out.println("Durchlauf: "+key + "\n\t " + Arrays.toString(zeitenListe1.get(key))+" Millisekunden" );
		}
		System.out.println("\n---Zeiten Linked List von JAVA---");
		for(int key : zeitenll.keySet()) {
			System.out.println("Durchlauf: "+key + "\n\t " + Arrays.toString(zeitenll.get(key))+" Millisekunden" );
		}

	}

}
