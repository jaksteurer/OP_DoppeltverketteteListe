
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
					+"\n\tnächste Adressse: "+aktuellerHead.prevAddress+"\n");
			aktuellerHead = aktuellerHead.nextAddress;
		}
	}
	public static void main(String[]args) {
		
		System.out.println("-------------------Liste1-------------------");
		List liste1 = new List(5);
		System.out.println("Liste erstellt + erster Eintrag: ");
		liste1.listeAusgeben();
		liste1.einfügenLetzteStelle(4);
		liste1.einfügenErsteStelle(1);
		System.out.println("head und tail hinzugefügt: ");
		liste1.listeAusgeben();
		liste1.einfügenBeliebigeStelle(50, 1);
		liste1.einfügenBeliebigeStelle(60, 3);
		System.out.println("zwei Element an beliebiger Stelle hinzugefügt:");
		liste1.listeAusgeben();
		liste1.tauschen(2, 0);
		System.out.println("drittes Element mit erstem getauscht:");
		liste1.listeAusgeben();
		liste1.löschenErsteStelle();
		System.out.println("erstes Element löschen");
		liste1.listeAusgeben();
		
		System.out.println("-------------------Liste2-------------------");
		List liste2 = new List(3);
		System.out.println("Liste erstellt + erster Eintrag: ");
		liste2.listeAusgeben();
		liste2.einfügenLetzteStelle(4);
		liste2.einfügenErsteStelle(1);
		System.out.println("head und tail hinzugefügt: ");
		liste2.listeAusgeben();
		liste2.einfügenBeliebigeStelle(50, 2);
		System.out.println("Element an beliebiger Stelle hinzugefügt:");
		liste2.listeAusgeben();
		liste2.löschenBeliebigeStelle(1);
		System.out.println("Element an zweiter Stelle gelöscht: ");
		liste2.listeAusgeben();
	}

}
