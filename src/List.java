
public class List {
	
	public Elem tail;
	public Elem head;
	int laenge;
	
	public List(int z) {
		this.tail = head;
		this.head = new Elem(z);
	}
	//zeigt l�nge der Liste an
	public int listenL�nge() {
		laenge=0;
		Elem aktuellerHead = head;
		while(aktuellerHead != null) {
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
		einf�genBeliebigeStelle(laenge, z);
	}
	//Eintrag an erster Stelle hinzuf�gen
	public void einf�genErsteStelle(int z) {
		einf�genBeliebigeStelle(getIndexByElem(head), z);
	}
	//Eintrag an beliebiger Stelle hinzuf�gen
	public void einf�genBeliebigeStelle(int stelle, int z) {
		if(stelle<0) System.out.println("[einf�genBeliebigeStelle] index muss positiv sein!");
		if (stelle > laenge) System.out.println("[einf�genBeliebigeStelle] index muss kleiner als die laenge der Liste sein!");
		Elem newElem = new Elem(z);
		if(stelle == laenge) {
			tail.nextAddress = newElem;
			newElem.prevAddress = tail;
			tail = newElem;
		}
		if(stelle== getIndexByElem(head)) {
			head.prevAddress = newElem;
			newElem.nextAddress=head;
			head = newElem;
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
		if(stelle < 0) System.out.println("[l�schenBeliebigeStelle] index muss positiv sein!");
		if(stelle >= laenge) System.out.println("[l�schenBeliebigeStelle] index muss kleiner als laenge sein!");
		Elem elemL�schen = getElemByIndex(stelle);
		Elem n�chstesE = elemL�schen.nextAddress;
		Elem letztesE = elemL�schen.prevAddress;
		n�chstesE.prevAddress = letztesE;
		letztesE.nextAddress = n�chstesE;	
	}
	
	public void tauschen(int stelle1, int stelle2) {
		if(stelle1 == stelle2)System.out.println("[tauschen] es gibt nichts zu vertauschen.");
		Elem tausch1 = getElemByIndex(stelle1);
		Elem tausch2 = getElemByIndex(stelle2);
		
		Elem tmp = tausch1.nextAddress;
		tausch1.nextAddress = tausch2.nextAddress;
		tausch2.nextAddress = tmp;
		
		tmp = tausch1.prevAddress;
		tausch1.prevAddress = tausch2.prevAddress;
		tausch2.prevAddress = tmp;
		
		tausch1.prevAddress = tausch2;
		tausch2.nextAddress = tausch1;
		
		tausch2.prevAddress.nextAddress = tausch2;
		tausch1.nextAddress.prevAddress = tausch1;
	}
	//Liste mit allen Eintr�gen wird ausgegeben
	public void listeAusgeben() {
		Elem aktuellerHead = head;
		for(int i = 0; i<= laenge;i++){
			System.out.println("Wert: "+aktuellerHead.zahl+"\nvorherige Adresse: "+aktuellerHead.prevAddress
					+"\nn�chste Adressse: "+aktuellerHead.prevAddress);
		}
	}
	public static void main(String[]args) {
		
		List liste = new List(2);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.einf�genErsteStelle(1);
		liste.einf�genLetzteStelle(3);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.einf�genBeliebigeStelle(1, 6);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.tauschen(1, 2);
		System.out.println("LISTE:"); liste.listeAusgeben();
		

//		System.out.println("LISTE:"); l.listeAusgeben();	
//		System.out.println("LAENGE: "); l.listenL�nge();
//		System.out.println("beliebige Stelle l�schen: "); l.l�schenBeliebigeStelle(5);
//		System.out.println("erste Stelle l�schen: "); l.l�schenErsteStelle();
//		System.out.println("letzte Stelle l�schen: "); l.l�schenLetzteStelle();
//		System.out.println("zwei Stellen tauschen: "); l.tauschen(2, 3);
//		System.out.println("LISTE:"); l.listeAusgeben();	
//		System.out.println("LAENGE: "); l.listenL�nge();
//		
//		System.out.println("--------LISTE 1--------"); l.listeAusgeben();
//		System.out.println("-----L�NGE LISTE 1-----");l.listenL�nge();
//		System.out.println("----ELEMENT L�SCHEN----");l.l�schenBeliebigeStelle(5);
//		System.out.println("-----L�NGE LISTE 1-----");l.listenL�nge();	
	}

}
