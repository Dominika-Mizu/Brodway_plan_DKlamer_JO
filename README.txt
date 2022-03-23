Założenia aplikacji:


Broadway to program, który służy do obsługi aktualnego repertuaru na broadway’u. Program jest zabezpieczony hasłem, zapisanym w pliku, bez którego się nie uruchomi.

Aplikacja jest podzielona na trzy klasy:
musical - obiekty z sześcioma polami danych i podstawowymi metodami zwracającymi i zmieniającymi te pola
repertuar - główna klasa operacyjna, która gromadzi obiekty musical, zawiera większość metod logicznych całego programu
Brodway - główna klasa służąca do obsługi użytkownika i “rozmawiania” z nim, zawiera funkcję main

Funkcjonalność:

Wyswietl musicale pojedynczo
Przenosi użytkownika do podmenu z wyświetlaniem pojedynczych rekordów. Podmenu to umożliwia również usuwanie i edycję rekordów.
	Podmenu:
Nastepny
Poprzedni
Poprzedni i nastepny nie wyswietlaja sie przy odpowiednio ostatnim i pierwszym rekordzie.
Usun
Zabezpieczone haslem.
Zaktualizuj
Umozliwia aktualizacje kazdej wartosci rekordu, jest zabezpieczone haslem.
Powrot do menu glownego
Wyswietl caly repertuar
Wyswietla wszystkie rekordy.
Dodaj musical
Umożliwia dodanie nowego rekordu prosząc kolejno o następne wartości nowego rekordu.
Szukaj musicalu
Umożliwia szukanie musicalu według jednego z dwóch kryteriów:
	- autor
	- czas trwania musicalu (szukamy w przedziale)

Zaladuj plik
Wczytujemy gotowy plik z musicalami.
Zaktualizuj plik podstawowy
Aktualizujemy istniejący już gotowy plik z musicalami.
Utworz nowy czysty plik
Tworzymy nowy czysty plik.
Posortuj repertuar
Umożliwia posortowanie repertuaru według jednego z czterech kryteriów:
	- alfabetycznie według tytułu
	- od z do a według tytułu
	- rosnąco według ceny biletu
	- malejąco według ceny biletu
Wyjdz z programu - zamykamy program

Pliki uzupełniające:

plik z hasłem “haslo.txt”
plik z przykładowym repertuarem “repertuar.txt”