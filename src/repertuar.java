import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class repertuar {
//dane
	//zabezpieczenia
	private String haslo, hashHaslo;
	//tablica obiektow
	protected char[] dataPremiery;
	protected musical[] repertuar, wynikiWyszukiwania, usuwanieIDodawanie;
	protected int repertuarRozmiar=0, wybranyMusical=0, ileZnaleziono=0, wyborZnaleziono=0; 
	
//metody glowne
	
	//wyswietlanie calej tablicy
	public void wyswietlRepertuar() {
		for(int x=0; x<repertuarRozmiar; x++) {
			wybranyMusical = x;
			wyswietlMusical();
			System.out.print("\n");
		}
	} 
	
	//dodawanie musicalu
	public void dodajMusical(String Title, String Author, String Date, String NumberOfTracks, String TicketPrice, String Duration) {
		zwiekszRepertuar();
		repertuar[repertuarRozmiar-1].setTitle(Title);
		repertuar[repertuarRozmiar-1].setAuthor(Author);
		repertuar[repertuarRozmiar-1].setDate(zmienNaTabliceZnakow(Date));
		repertuar[repertuarRozmiar-1].setNumberOfTracks(Integer.parseInt(NumberOfTracks));
		repertuar[repertuarRozmiar-1].setTicketPrice(Float.parseFloat(TicketPrice));
		repertuar[repertuarRozmiar-1].setDuration(Integer.parseInt(Duration));
	}
	
	//usuwanie musicalu
	public void usunMusical() {
		System.out.print("\n"+repertuar.length+"\n");
		//tablica pomocnicza
		usuwanieIDodawanie = new musical[repertuarRozmiar-1];
		
		//usuwamy pierwszy
		if(wybranyMusical==0) System.arraycopy(repertuar, 1, usuwanieIDodawanie, 0, usuwanieIDodawanie.length);
		//usuwamy ostatni
		if(wybranyMusical==repertuarRozmiar-1) System.arraycopy(repertuar, 0, usuwanieIDodawanie, 0, usuwanieIDodawanie.length);
		//usuwamy w srodku tablicy
		if(wybranyMusical > 0 && wybranyMusical < repertuarRozmiar-1) {
			System.arraycopy(repertuar, 0, usuwanieIDodawanie, 0, wybranyMusical);
			System.arraycopy(repertuar, wybranyMusical+1, usuwanieIDodawanie, wybranyMusical, repertuarRozmiar - wybranyMusical - 1);
		}
		
		repertuarRozmiar--;
		repertuar = new musical[repertuarRozmiar];
		System.arraycopy(usuwanieIDodawanie, 0, repertuar, 0, repertuarRozmiar);
	}
	
	//edycja musical
	public void zaktualizujMusical(int wyborEdycji, String wartoscEdycji) {
		switch(wyborEdycji) {
		case 1: {repertuar[wybranyMusical].setTitle(wartoscEdycji); break;}
		case 2: {repertuar[wybranyMusical].setAuthor(wartoscEdycji); break;}
		case 3: {repertuar[wybranyMusical].setDate(zmienNaTabliceZnakow(wartoscEdycji)); break;}
		case 4: {repertuar[wybranyMusical].setNumberOfTracks(Integer.parseInt(wartoscEdycji)); break;}
		case 5: {repertuar[wybranyMusical].setTicketPrice(Float.parseFloat(wartoscEdycji)); break;}
		case 6: {repertuar[wybranyMusical].setDuration(Integer.parseInt(wartoscEdycji)); break;}
		}
	} 
	
	//szukanie musicalu
		//szukaj po tytule
		public void znajdzAutora(String wartosc) {
			int czyRowne = 0;
			ileZnaleziono = 0; 
			wyborZnaleziono = 0;
			
			//liczy ile znaleziono
			for(int x=0; x<repertuarRozmiar; x++) {
				if(wartosc.equals(repertuar[x].getAuthor()))
					ileZnaleziono++;
				}
			wynikiWyszukiwania = new musical[ileZnaleziono];
			
			//wpisuje wyniki do tabeli
			for(int x=0; x<repertuarRozmiar; x++) {
				if(wartosc.equals(repertuar[x].getAuthor())) {
					System.arraycopy(repertuar, x, wynikiWyszukiwania, czyRowne, 1);
					czyRowne++;
				}
			}
		}
		
		//szukaj po czasie trwania
		public void znajdzCzasTrwania(String min, String max) {
			int czyRowne = 0;
			int czasTrwaniaMin = Integer.parseInt(min);
			int czasTrwaniaMax = Integer.parseInt(max);
			ileZnaleziono = 0;
			wyborZnaleziono = 0;
			
			//liczy ile znaleziono
			for(int x=0; x<repertuarRozmiar; x++) {
				if(repertuar[x].getDuration() >= czasTrwaniaMin && repertuar[x].getDuration() <= czasTrwaniaMax)
					ileZnaleziono++;
				}
			wynikiWyszukiwania = new musical[ileZnaleziono];
				
			//wpisuje wyniki do tabeli
			for(int x=0; x<repertuarRozmiar; x++) {
				if(repertuar[x].getDuration() >= czasTrwaniaMin && repertuar[x].getDuration() <= czasTrwaniaMax) {
					System.arraycopy(repertuar, x, wynikiWyszukiwania, czyRowne, 1);
					czyRowne++;
				}
			}
		}
	
	//wczytywanie pliku tekstowego
	public boolean wczytajPlik() throws FileNotFoundException {
		try {
			String wiersz;
			String[] podzielonyWiersz;
			File przykladowyRepertuar = new File("D:\\Miz\\Study\\projekt_jo\\Brodway_plan_DKlamer_JOWarunek/repertuar.txt");
			Scanner czytajPlik = new Scanner(przykladowyRepertuar);
			
			while(czytajPlik.hasNextLine()) {
				wiersz = czytajPlik.nextLine();
				podzielonyWiersz = wiersz.trim().split("\\s+");
				
				dodajMusical(podzielonyWiersz[0],podzielonyWiersz[1],podzielonyWiersz[2],podzielonyWiersz[3],podzielonyWiersz[4],podzielonyWiersz[5]);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	//aktualizacja istniejacego pliku
	public boolean zaktualizujPlik() {
		try {
			FileWriter wpisuj = new FileWriter("repertuar.txt");
			for(int x=0; x<repertuarRozmiar; x++) {
				wpisuj.write(repertuar[x].getTitle()+" ");
				wpisuj.write(repertuar[x].getAuthor()+" ");
				wpisuj.write(String.valueOf(repertuar[x].getDate())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getNumberOfTracks())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getTicketPrice())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getDuration())+"\n");
			}
			wpisuj.close();
		} catch(IOException e) {
			return false;
		}
		return true;
	}
	
	//zapis do nowego pliku
	public boolean zapiszPlik() {
		try {
			FileWriter wpisuj = new FileWriter("nowyRepertuar.txt");
			for(int x=0; x<repertuarRozmiar; x++) {
				wpisuj.write(repertuar[x].getTitle()+" ");
				wpisuj.write(repertuar[x].getAuthor()+" ");
				wpisuj.write(String.valueOf(repertuar[x].getDate())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getNumberOfTracks())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getTicketPrice())+" ");
				wpisuj.write(String.valueOf(repertuar[x].getDuration())+"\n");
			}
			wpisuj.close();
		} catch(IOException e) {
			return false;
		}
		return true;
	}
	
//sortowania
	//zwracanie pierwszej litery
	protected int zwrocKodAsciPierwszejLitery(Integer rekord) {
		char[] tablicaZnakow;
		int kodPierwszejLitery;
		tablicaZnakow = repertuar[rekord].getTitle().toCharArray();
		kodPierwszejLitery = tablicaZnakow[0];
		return kodPierwszejLitery;
	}
	
	//sortowanie po tytule alfabetycznie
	protected void sortujAdoZ() {
		for (int x = 1; x <= repertuarRozmiar - 1; x++)
			for (int y = 0; y <= repertuarRozmiar -1 - x; y++)
				if(zwrocKodAsciPierwszejLitery(y) > zwrocKodAsciPierwszejLitery(y+1)) {
					musical rekordKopia = repertuar[y];
					repertuar[y] = repertuar[y+1];
					repertuar[y+1] = rekordKopia;
				}
	}
	
	//sortowanie po tytule od z do a
	protected void sortujZdoA() {
		for (int x = 1; x <= repertuarRozmiar - 1; x++)
			for (int y = 0; y <= repertuarRozmiar -1 - x; y++)
				if(zwrocKodAsciPierwszejLitery(y) < zwrocKodAsciPierwszejLitery(y+1)) {
					musical rekordKopia = repertuar[y];
					repertuar[y] = repertuar[y+1];
					repertuar[y+1] = rekordKopia;
				}
	}
	
	//sortowanie po cenie biletu rosnaca
	protected void sortujCenaRosnaca() {
		for (int x = 1; x <= repertuarRozmiar - 1; x++)
			for (int y = 0; y <= repertuarRozmiar -1 - x; y++)
				if (repertuar[y].getTicketPrice() > repertuar[y+1].getTicketPrice()) {
					musical rekordKopia = repertuar[y];
					repertuar[y] = repertuar[y+1];
					repertuar[y+1] = rekordKopia;
				}
	}
	
	//sortowanie po cenie biletu malejaca
	protected void sortujCenaMalejaca() {
		for (int x = 1; x <= repertuarRozmiar - 1; x++)
			for (int y = 0; y <= repertuarRozmiar -1 - x; y++)
				if (repertuar[y].getTicketPrice() < repertuar[y+1].getTicketPrice()) {
					musical rekordKopia = repertuar[y];
					repertuar[y] = repertuar[y+1];
					repertuar[y+1] = rekordKopia;
				}
	}
	
//metody pomocnicze
	
	//ladowanie pliku z haslem
	protected boolean wczytajPlikZHaslem() throws FileNotFoundException, NoSuchAlgorithmException {
		try {
			String wiersz;
			File hasloPlik = new File("D:\\Miz\\Study\\projekt_jo\\Brodway_plan_DKlamer_JOWarunek/haslo.txt");
			
			Scanner czytaj = new Scanner(hasloPlik);
			
			while(czytaj.hasNextLine()) {
				wiersz = czytaj.nextLine();
				
				//hashowanie
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(wiersz.getBytes());
				byte[] bity = md.digest();
				StringBuilder i = new StringBuilder();
				for(int x = 0; x < bity.length; x++) {
					i.append(Integer.toString((bity[x] & 0xff) + 0x100, 16).substring(1));
				}
				
				hashHaslo = i.toString();
			}
		} catch(FileNotFoundException e) {
			return true;
		}
		return false;
	}
	
	//sprawdzenie hasla
	public boolean sprawdzHaslo(String wpisaneHaslo) throws NoSuchAlgorithmException {
		//hashowanie
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(wpisaneHaslo.getBytes());
		byte[] bity = md.digest();
		StringBuilder i = new StringBuilder();
		for(int x = 0; x < bity.length; x++) {
			i.append(Integer.toString((bity[x] & 0xff) + 0x100, 16).substring(1));
		}
		
		haslo = i.toString();
		if(haslo.equals(hashHaslo)) return true;
		return false;
	}
	
	
	//zmiana na tablice znakow
	public char[] zmienNaTabliceZnakow(String string){
		char[] charTab = new char[string.length()];
		for (int x=0; x < string.length(); x++) {
			charTab[x] = string.charAt(x);
		}
		return charTab;
	}
	
	//czy istnieje jakikolwiek
	public boolean czyIstnieje() {
		if(repertuarRozmiar>0) return true;
		return false;
	}
	
	//czy istnieje wynik wyszukiwania
	public boolean czyIstniejeRezultat() {
		if(ileZnaleziono > 0) return true;
		return false;
	}
	
	//zwiekszanie tablicy repertuar
	public void zwiekszRepertuar() {
		if(repertuarRozmiar > 0) {
			usuwanieIDodawanie = new musical[repertuar.length];
			System.arraycopy(repertuar, 0, usuwanieIDodawanie, 0, repertuar.length);
		}
		
		repertuarRozmiar++;
		repertuar = new musical[repertuarRozmiar];
		for(int x=0; x<repertuarRozmiar; x++) {
			repertuar[x]=new musical();
		}
		
		if(repertuarRozmiar-1 > 0) {
			System.arraycopy(usuwanieIDodawanie, 0, repertuar, 0, usuwanieIDodawanie.length);
		}
	}
	
	//wyswietlanie daty
	public void wyswietlDate(musical wartosc) {
		dataPremiery = new char[8]; 
		System.arraycopy(wartosc.getDate(), 0, dataPremiery, 0, 8);
		for(int x=0;x<8;x++) {
			System.out.print(dataPremiery[x]);
			if(x==1 || x==3) System.out.print(".");
		}
	}
	
	//wyswietlenie musicalu
	public void wyswietlMusical() {
		System.out.print(repertuar[wybranyMusical].getTitle()+" "+repertuar[wybranyMusical].getAuthor()+" ");
		wyswietlDate(repertuar[wybranyMusical]);
		System.out.print(" "+repertuar[wybranyMusical].getNumberOfTracks()+" "+repertuar[wybranyMusical].getTicketPrice()+" "+repertuar[wybranyMusical].getDuration());
	}
	//wyswietlenie wyniku wyszukiwania
	public void wyswietlZnalezionyMusical() {
			System.out.print(wynikiWyszukiwania[wyborZnaleziono].getTitle()+" "+wynikiWyszukiwania[wyborZnaleziono].getAuthor()+" ");
			wyswietlDate(wynikiWyszukiwania[wyborZnaleziono]);
			System.out.print(" "+wynikiWyszukiwania[wyborZnaleziono].getNumberOfTracks()+" "+wynikiWyszukiwania[wyborZnaleziono].getTicketPrice()+" "+wynikiWyszukiwania[wyborZnaleziono].getDuration());
		}
	
//walidacje
	//walidacja string
	public boolean walidujString(String wartosc) {
		char[] tablicaZnakow;

		//rozbijamy string na tablice znakow
		tablicaZnakow = wartosc.toCharArray();
 		
		for(int x = 0; x < tablicaZnakow.length; x++) {
			if( ((int)tablicaZnakow[x] >= 65 && (int)tablicaZnakow[x] <= 90) || ((int)tablicaZnakow[x] >= 97 && (int)tablicaZnakow[x] <= 122) ) return false;
		}
		return true;
	}
	
	//walidacja integer
	public boolean walidujInteger(String wartosc) {
		char[] tablicaZnakow;
		
		//rozbijamy string na tablice znakow
		tablicaZnakow = wartosc.toCharArray();
		
		for(int x = 0; x < tablicaZnakow.length; x++) {
			if( ((int)tablicaZnakow[x] >= 48 && (int)tablicaZnakow[x] <= 57) ) return false;
		}
		return true;
	}
}
