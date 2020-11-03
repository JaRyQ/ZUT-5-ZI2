# ZUT-S1-5-ZI2

- [x] ### lab_5
>W ramach zadania należy utworzyć model danych dla aplikacji w języku Java przechowującej dane o klasach w szkole. Model danych powinien być utworzony jako zestaw encji danych (@Entity) zgodnych ze standardem JPA.

Pojęcia do uwzględnienia w modelu:

    *Uczeń - reprezentuje podstawowe dane osobowe ucznia, przynależy do dokładnie jednej klasy
    *Nauczyciel - reprezentuje podstawowe dane osobowe nauczyciela, ma jedną klasę - w której jest wychowawcą, naucza kilka przedmiotów
    *Klasa - reprezentuje szkolną klasę, klasa ma nazwę, poziom (rok nauczania), listę uczniów, nauczyciela/wychowawcę, listę przedmiotów
    *Przedmiot - reprezentuje przedmiot realizowany przez pojedynczą klasę, ma nazwę oraz nauczyciela prowadzącego.

Dla powyższego modelu proszę utworzyć klasy wg własnego projektu oraz dodać kod definiujący zestaw przykładowych danych (min. 3 klasy, po 5 uczniów na klasę, 3 nauczycieli, min. 4 przedmioty na klasę.

Przykładowe wyświetlenia danych:

    -dla wybranej osoby ucznia wyświetlić jej przedmioty
    -dla wybranego nauczyciela wyświetlić wszystkich jego uczniów z klas, z którymi ma realizuje przedmioty.
