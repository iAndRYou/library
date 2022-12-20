Projekt agh.pz1.library:

Implementacja problemu czytelników i pisarzy w programowaniu wielowątkowym, współbieżnym.

Klasa Solution zawiera metodę główną, obsługuje ona klasę Library:
- każdy czytelnik i pisarz zaimplementowany jako osobny wątek odpowiednio klasy Reader i Writer
- kolejność uruchamiania wątków jest losowa
- żywotność każdego z wątków to 30 sekund
- komunikacja między wątkami za pomocą semaforów:
	- queue: zarządza kto jest pierwszy w kolejce
	- capacity: pilnuje by w bibliotece nie było więcej niż 5 czytelników
	- lib: pilnuje dostępu do biblioteki tak, by byli w niej albo czytelnicy albo pisarz
	- readerAction: pilnuje, aby tylko jeden czytelnik mógł w danym momencie wejść lub wyjść

Aby uruchomić program wystarczy uruchomić komendę

	java -jar   solution-1.0-jar-with-dependencies.jar

Domyślnie klasa Solution uruchomi 10 czytelników i 3 pisarzy. Można zmienić te wartości uruchamiając program z argumentami kolejno:	
l. czytelników	l. pisarzy	np.:

	java -jar   solution-1.0-jar-with-dependencies.jar  5  1	

- uruchomi program z 5 czytelnikami i 1 pisarzem

Sposób w jaki zostało zaimplementowane rozwiązanie zakłada przestrzeganie zasady „kto pierwszy ten lepszy” według kolejności w kolejce. 
Zapewniony jest również brak zagłodzenia któregokolwiek z pisarzy i czytelników, każdy zostanie obsłużony.

