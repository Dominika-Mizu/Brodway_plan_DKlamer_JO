import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Brodway {
	//utworzenie obiektu
	static repertuar brodway = new repertuar();
	
	//dane
	public static String titleNew, authorNew, dateNew, numberOfTracksNew, ticketPriceNew, durationNew;
	public static boolean wyszukiwanie = true, wyswietlaniePoKolei = true;
	
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		//zaladowanie pliku z haslem
		if(brodway.wczytajPlikZHaslem()) {System.out.print("Nie znaleziono pliku z haslem, program zostanie wylaczony"); System.exit(0);}
		
		//menu glowne
		menu_glowne: do {
			System.out.print("\n\n\n\n1. Wyswietl musicale pojedynczo");
			System.out.print("\n2. Wyswietl caly repertuar");
			System.out.print("\n3. Dodaj musical");
			System.out.print("\n4. Szukaj musicalu");
			System.out.print("\n5. Zaladuj plik");
			System.out.print("\n6. Zaktualizuj plik podstawowy");
			System.out.print("\n7. Utworz nowy czysty plik");
			System.out.print("\n8. Posortuj repertuar");
			System.out.print("\n0. Wyjdz z programu");
		
		//input uzytkownika
			Scanner input = new Scanner(System.in);
			System.out.print("\n\nWybierz z menu:");
			Integer wybor = input.nextInt();
		
		//menu glowne switch
			switch (wybor) {
			
			//wyswietl pojedynczo musicale
			case 1:{
				if(brodway.czyIstnieje()) {
					Scanner czytaj = new Scanner(System.in);
					wyswietlaniePoKolei = true;
					if(brodway.czyIstnieje()) {
						brodway.wybranyMusical = 0;
						podmenu_wyswietlanie: while(wyswietlaniePoKolei) {
							//wyjscie z menu jezeli usunieto ostatni
							if(brodway.repertuarRozmiar == 0) break podmenu_wyswietlanie;
							
							//wyswietlanie
							System.out.print("\n");
							if(brodway.repertuarRozmiar == 1) {
								System.out.print("To jest ostatni musical. Usuniecie spowoduje powrot do menu glownego.\n");
							}
							
							//wyswietlenie musicalu
							System.out.print("\nWybrany:\n");
							brodway.wyswietlMusical();
							System.out.print("\n\n");
							
							//wyswietlanie pojedynczo podmenu
							System.out.print("Wybrany: "+ brodway.wybranyMusical+"\nRepertuar rozmiar: "+brodway.repertuarRozmiar+"\n");
							if(brodway.wybranyMusical < brodway.repertuarRozmiar - 1) System.out.print("1. Nastepny\n");
							if(brodway.wybranyMusical > 0) System.out.print("2. Poprzedni\n");
							System.out.print("3. Usun\n4. Zaktualizuj\n5. Powrot do menu glownego");
							
							Integer wyswietlaniePojedynczoWybor = czytaj.nextInt();
							
							switch(wyswietlaniePojedynczoWybor){
							case 1: if(brodway.wybranyMusical < brodway.repertuarRozmiar - 1) brodway.wybranyMusical++; break;
							case 2: if(brodway.wybranyMusical > 0) brodway.wybranyMusical--; break;
							case 3: {
								//sprawdzanie hasla
								if(sprawdzHaslo()) {
									brodway.usunMusical();
									if(brodway.wybranyMusical == brodway.repertuarRozmiar) brodway.wybranyMusical--;
									break;
								} else System.out.print("Podano zle haslo."); break;
							}
							case 4: {
								//sprawdzanie hasla
								if(sprawdzHaslo()) {
									aktualizujMusical(); 
									break;
								} else System.out.print("Podano zle haslo."); break;
							}
							case 5: wyswietlaniePoKolei = false; break;
							}
						}
					}
				} else System.out.print("\nRepertuar jest pusty");
			break;
			}
			
			//wyswietl caly repertuar
			case 2:{if(brodway.czyIstnieje()) brodway.wyswietlRepertuar(); else System.out.print("\nRepertuar jest pusty"); break;}
			
			//dodaj musical
			case 3:{
				//sprawdzanie hasla
				if(sprawdzHaslo()) {
					Scanner czytaj = new Scanner(System.in);
					
					System.out.print("\nPodaj dane musicalu\n\n");
					//tytul
					System.out.print("Podaj tytul musicalu:\n");
					titleNew = czytaj.nextLine();
					//walidacja
					while(brodway.walidujString(titleNew.trim())) {
						System.out.print("\nW podanej wartosci powinny znalezc sie tylko litery. Prosze podac wartosc jeszcze raz:");
						titleNew = czytaj.nextLine();
					}
					
					//autor
					System.out.print("Podaj autora musicalu:\n");
					authorNew = czytaj.nextLine();
					//walidacja
					while(brodway.walidujString(authorNew.trim())) {
						System.out.print("\nWprowadzono nieprawidlowa ilosc badz litere. Prosze podac wartosc jeszcze raz:");
						authorNew = czytaj.nextLine();
					}
				
					//data
					System.out.print("Podaj date premiery (dzien, miesiac, rok) bez kropek i spacji:\n");
					dateNew = czytaj.nextLine();
					//walidacja i sprawdzenie ilosci cyfr
					while( (dateNew.length()!=8) || (brodway.walidujInteger(dateNew.trim())) ) {
						System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry w ilosci 8. Prosze podac wartosc jeszcze raz:");
						dateNew = czytaj.nextLine();
					}
					
					//liczba utworow
					System.out.print("Podaj ilosc utworow w musicalu:\n");
					numberOfTracksNew = czytaj.nextLine();
					//walidacja
					while(brodway.walidujInteger(numberOfTracksNew.trim())) {
						System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
						numberOfTracksNew = czytaj.nextLine();
					}
					
					//cena biletu
					System.out.print("Podaj cene biletu:\n");
					ticketPriceNew = czytaj.nextLine();
					//walidacja
					while(brodway.walidujInteger(ticketPriceNew.trim())) {
						System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
						ticketPriceNew = czytaj.nextLine();
					}
					
					//czas trwania
					System.out.print("Podaj czas trwania musicalu(w minutach):\n");
					durationNew = czytaj.nextLine();
					//walidacja
					while(brodway.walidujInteger(durationNew.trim())) {
						System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
						durationNew = czytaj.nextLine();
					}
					
					//utworzenie rekordu
					brodway.dodajMusical(titleNew.trim(), authorNew.trim(), dateNew.trim(), numberOfTracksNew.trim(), ticketPriceNew.trim(), durationNew.trim());
					break;
				} else System.out.print("Podano zle haslo."); break;
			}
			
			//szukanie musicalu
			case 4:{
				Integer wyborPodmenuSzukanie;
				Scanner czytaj = new Scanner(System.in);
				System.out.print("Wedlug czego chcesz szukac?\n");
				System.out.print("1. Autor\n2. Czas trwania\n3. Powrot\n");
				wyborPodmenuSzukanie = czytaj.nextInt();
				
				switch(wyborPodmenuSzukanie) {
					//tytul
					case 1:{
						System.out.print("Podaj autora ktorego szukasz:\n");
						String tytul = czytaj.next();
						
						//wywolujemy metode wyszukujaca
						brodway.znajdzAutora(tytul);
						if(brodway.czyIstniejeRezultat()) 
							//przechodzimy do podmenu w celu wyswietlenia wynikow
							wyswietlWyniki();
						else System.out.print("Nie znaleziono musicalu z podanym autorem.");
						break;
					}
					//czas trwania
					case 2:{
						System.out.print("Podaj minimalny czas trwania musicalu:\n");
						String czasMin = czytaj.next();
						System.out.print("Podaj maksymalny czas trwania musicalu:\n");
						String czasMax = czytaj.next();
						
						//wywolujemy metode wyszukujaca
						brodway.znajdzCzasTrwania(czasMin, czasMax);
						if(brodway.czyIstniejeRezultat()) 
							//przechodzimy do podmenu w celu wyswietlenia wynikow
							wyswietlWyniki();
						else System.out.print("Nie znaleziono musicalu w podanym przedziale czasowym.");
						break;
					}
					case 3: break;
				}
				break;
			}
			
			//wczytywanie pliku
			case 5:{ if(brodway.wczytajPlik()) System.out.print("Wczytano plik"); else System.out.print("Nie znaleziono pliku."); break; }
			
			//aktualizacja pliku
			case 6:{if(brodway.repertuarRozmiar == 0) System.out.print("Brak danych do zapisu"); else brodway.zaktualizujPlik(); break;}
			
			//zapis nowego pliku
			case 7:{if(brodway.repertuarRozmiar == 0) System.out.print("Brak danych do zapisu"); else brodway.zapiszPlik(); break;}
			
			//sortowanie
			case 8:{
				//sprawdzanie hasla
				if(sprawdzHaslo()) {
					Integer sortowanieWybor;
					Scanner czytaj = new Scanner(System.in);
					System.out.print("Jak chcesz posortowac rekordy?\n");
					System.out.print("1. Rosnaca po cenie biletow\n2. Malejaco po cenie biletow\n3. Alfabetycznie a-z tytul\n4. Od z do a tytul\n5. Powrot\n");
					
					//podmenu do sortowania
					sortowanieWybor = czytaj.nextInt();
					switch(sortowanieWybor) {
					case 1: brodway.sortujCenaRosnaca(); break;
					case 2: brodway.sortujCenaMalejaca(); break;
					case 3: brodway.sortujAdoZ(); break;
					case 4: brodway.sortujZdoA(); break;
					case 5: break;
					}
					break;
				} else System.out.print("Podano zle haslo."); break;
			}
			
			case 0:{break menu_glowne;}
			}
			
		}while(true);

	}
	
	//podmenu do aktualizacji musicalu
	public static void aktualizujMusical() {
		String nowaWartosc;
		Scanner czytaj = new Scanner(System.in);
		System.out.print("\nWybrany:\n");
		brodway.wyswietlMusical();
		System.out.print("\n\nCo chcesz edytowac?\n");
		System.out.print("1. Tytul\n2. Autora\n3. Date\n4. Liczbe utworow\n5. Cene biletu\n6. Czas trwania\n7. Powrot\n");
		
		Integer aktualizujWybor = czytaj.nextInt();
		switch(aktualizujWybor) {
			//tytul
			case 1:{
				System.out.print("Podaj nowy tytul: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujString(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko litery. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(1, nowaWartosc.trim());
				break;
			}
			//autor
			case 2:{
				System.out.print("Podaj nowego autora: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujString(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko litery. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(2, nowaWartosc.trim());
				break;
			}
			//data
			case 3:{
				System.out.print("Podaj nowa date: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujInteger(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(3, nowaWartosc.trim());
				break;
			}
			//liczba utworow
			case 4:{
				System.out.print("Podaj nowa liczbe utworow: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujInteger(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(4, nowaWartosc.trim());
				break;
			}
			//cena biletu
			case 5:{
				System.out.print("Podaj nowa cene biletu: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujInteger(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(5, nowaWartosc.trim());
				break;
			}
			//czas trwania
			case 6:{
				System.out.print("Podaj nowy czas trwania: ");
				nowaWartosc = czytaj.next();
				
				//walidacja
				while(brodway.walidujInteger(nowaWartosc.trim())) {
					System.out.print("\nW podanej wartosci powinny znalezc sie tylko cyfry. Prosze podac wartosc jeszcze raz:");
					nowaWartosc = czytaj.nextLine();
				}
				
				brodway.zaktualizujMusical(6, nowaWartosc.trim());
				break;
			}
			case 7: break;
			default: break;
		}
	}

	//podmenu do przegladania wynikow
	public static void wyswietlWyniki() {
		Scanner czytaj = new Scanner(System.in);
		//resetujemy zmienna
		brodway.wyborZnaleziono = 0;
		wyszukiwanie = true;
		while(wyszukiwanie) {
			
			//wyswietlenie musicalu
			System.out.print("\n");
			brodway.wyswietlZnalezionyMusical();
			System.out.print("\n");
			
			//podmenu 
			if(brodway.wyborZnaleziono < brodway.ileZnaleziono-1) System.out.print("1. Nastepny\n");
			if(brodway.wyborZnaleziono > 0) System.out.print("2. Poprzedni\n");
			System.out.print("3. Powrot do menu glownego.\n");
			
			Integer wyborPodmenuZnalezione = czytaj.nextInt();
			switch(wyborPodmenuZnalezione) {
				case 1:if(brodway.wyborZnaleziono < brodway.ileZnaleziono-1) brodway.wyborZnaleziono++; break;
				case 2:if(brodway.wyborZnaleziono > 0) brodway.wyborZnaleziono--; break;
				case 3:wyszukiwanie = false; break;
			}
		}
	}
	
	//sprawdzanie hasla
	public static boolean sprawdzHaslo() throws NoSuchAlgorithmException {
		System.out.print("Prosze podac haslo: ");
		Scanner czytaj = new Scanner(System.in);
		String haslo = czytaj.next();
		if(brodway.sprawdzHaslo(haslo)) return true;
		return false;
	}
	
}
