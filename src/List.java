
public class List {
	
	public Elem tail;
	public Elem head;
	int laenge;
	
	public List(int z) {
		this.tail = head;
		this.head = new Elem(z);
	}
	
	public void listenLänge() {
		int laenge=0;
		Elem aktuellerHead = head;
		while(aktuellerHead != null) {
			laenge++;
			aktuellerHead = aktuellerHead.nextAddress;
		}
		System.out.println("länge: "+laenge);
	}
	
	private Elem getElemByIndex(int index) {
		Elem aktuellerHead = head;
		int zaehler = 0;
		while (index != zaehler) {
			aktuellerHead = aktuellerHead.nextAddress;
			zaehler++;
			if (aktuellerHead == null) {
				System.out.println("Es existiert kein Element mit index " + index + ".");
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
	
	public void elemhinzufügen(int z) {
		Elem neuesE = new Elem(z);
		tail.nextAddress = neuesE;
		neuesE.prevAddress = tail;
		tail = neuesE;
	}
	
	
	public void elemlöschen(int i) {
		if(i < 0) System.out.println("index muss positiv sein!");
		if(i >= laenge) System.out.println("index muss kleiner als laenge sein!");
		if(i == getIndexByElem(head)) head = head.nextAddress;
		if(i == getIndexByElem(tail)) tail = tail.nextAddress;
		
		Elem elemLöschen = getElemByIndex(i);
		Elem letztesE = elemLöschen.prevAddress;
		Elem nächstesE = elemLöschen.nextAddress;
		letztesE.nextAddress = nächstesE;
		nächstesE.prevAddress = letztesE;		
	}
	
	public void tauschen(int i1, int i2) {
		if(i1 == i2)System.out.println("es gibt nichts zu vertauschen.");
		Elem e1 = getElemByIndex(i1);
		Elem e2 = getElemByIndex(i2);
		
		Elem tmp = e1.nextAddress;
		e1.nextAddress = e2.nextAddress;
		e2.nextAddress = tmp;
		tmp = e1.prevAddress;
		e1.prevAddress = e2.prevAddress;
		e2.prevAddress = tmp;
		
		e1.prevAddress = e2;
		e2.nextAddress = e1;
		
		e2.prevAddress.nextAddress = e2;
		e1.nextAddress.prevAddress = e1;
	}
	
	public void listeAusgeben() {
		Elem aktuellerHead = head;
		while(aktuellerHead != null) {
			System.out.println("Wert: "+aktuellerHead.zahl+"\nvorherige Adresse: "+aktuellerHead.prevAddress
					+"nächste Adressse"+aktuellerHead.prevAddress);
		}
	}
	
	public static void main(String[]args) {
		
		List liste1 = new List(5);
		liste1.elemhinzufügen(9);
		liste1.elemhinzufügen(7);
		liste1.elemhinzufügen(11);
		liste1.elemhinzufügen(1);
		System.out.println("--------LISTE 1--------"); liste1.listeAusgeben();
		System.out.println("-----LÄNGE LISTE 1-----");liste1.listenLänge();
		System.out.println("----ELEMENT LÖSCHEN----");liste1.elemlöschen(5);
		System.out.println("-----LÄNGE LISTE 1-----");liste1.listenLänge();	
	}

}
