
public class List {
	
	public Elem tail;
	public Elem head;
	int laenge;
	
	public List(int z) {
		this.tail = head;
		this.head = new Elem(z);
	}
	//zeigt länge der Liste an
	public int listenLänge() {
		laenge=0;
		Elem aktuellerHead = head;
		while(aktuellerHead != null) {
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
		einfügenBeliebigeStelle(laenge, z);
	}
	//Eintrag an erster Stelle hinzufügen
	public void einfügenErsteStelle(int z) {
		einfügenBeliebigeStelle(getIndexByElem(head), z);
	}
	//Eintrag an beliebiger Stelle hinzufügen
	public void einfügenBeliebigeStelle(int stelle, int z) {
		if(stelle<0) System.out.println("[einfügenBeliebigeStelle] index muss positiv sein!");
		if (stelle > laenge) System.out.println("[einfügenBeliebigeStelle] index muss kleiner als die laenge der Liste sein!");
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
		if(stelle < 0) System.out.println("[löschenBeliebigeStelle] index muss positiv sein!");
		if(stelle >= laenge) System.out.println("[löschenBeliebigeStelle] index muss kleiner als laenge sein!");
		Elem elemLöschen = getElemByIndex(stelle);
		Elem nächstesE = elemLöschen.nextAddress;
		Elem letztesE = elemLöschen.prevAddress;
		nächstesE.prevAddress = letztesE;
		letztesE.nextAddress = nächstesE;	
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
	//Liste mit allen Einträgen wird ausgegeben
	public void listeAusgeben() {
		Elem aktuellerHead = head;
		for(int i = 0; i<= laenge;i++){
			System.out.println("Wert: "+aktuellerHead.zahl+"\nvorherige Adresse: "+aktuellerHead.prevAddress
					+"\nnächste Adressse: "+aktuellerHead.prevAddress);
		}
	}
	public static void main(String[]args) {
		
		List liste = new List(2);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.einfügenErsteStelle(1);
		liste.einfügenLetzteStelle(3);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.einfügenBeliebigeStelle(1, 6);
		System.out.println("LISTE:"); liste.listeAusgeben();
		liste.tauschen(1, 2);
		System.out.println("LISTE:"); liste.listeAusgeben();
		

//		System.out.println("LISTE:"); l.listeAusgeben();	
//		System.out.println("LAENGE: "); l.listenLänge();
//		System.out.println("beliebige Stelle löschen: "); l.löschenBeliebigeStelle(5);
//		System.out.println("erste Stelle löschen: "); l.löschenErsteStelle();
//		System.out.println("letzte Stelle löschen: "); l.löschenLetzteStelle();
//		System.out.println("zwei Stellen tauschen: "); l.tauschen(2, 3);
//		System.out.println("LISTE:"); l.listeAusgeben();	
//		System.out.println("LAENGE: "); l.listenLänge();
//		
//		System.out.println("--------LISTE 1--------"); l.listeAusgeben();
//		System.out.println("-----LÄNGE LISTE 1-----");l.listenLänge();
//		System.out.println("----ELEMENT LÖSCHEN----");l.löschenBeliebigeStelle(5);
//		System.out.println("-----LÄNGE LISTE 1-----");l.listenLänge();	
	}

}
