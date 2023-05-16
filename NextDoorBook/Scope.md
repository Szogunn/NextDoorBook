# NextDoorBook
### 1.Rejestracja i logowanie użytkowników:
Utwórz modele dla użytkowników, zawierające pola takie jak email, hasło, imię, nazwisko itp.
Stwórz kontrolery obsługujące rejestrację nowych użytkowników oraz logowanie istniejących.
Zaimplementuj mechanizm uwierzytelniania i autoryzacji, na przykład przy użyciu Spring Security.
Ogranicz rejestrację i logowanie tylko do osób w Twojej lokalnej społeczności.
//Możesz zaimplementować prosty mechanizm uwierzytelniania, na przykład przy użyciu hasła jednorazowego wysyłanego na numer telefonu lub kodu dostępu udzielanego osobiście.//
### 2.Zarządzanie książkami:
Utwórz model Book (książka) zawierający pola takie jak tytuł, autor, rok wydania, opis, dostępność itp.
Stwórz kontroler obsługujący operacje związane z książkami, takie jak dodawanie nowej książki, edycja istniejącej, usuwanie, wyszukiwanie po tytule lub autorze itp.
//Możesz również uwzględnić możliwość dodawania zdjęć okładek książek.//
### 3.Wypożyczanie książek:
Utwórz model Rental (wypożyczenie) zawierający pola takie jak identyfikator wypożyczenia, data wypożyczenia, data zwrotu, status wypożyczenia itp.
Stwórz kontroler obsługujący operacje związane z wypożyczaniem, takie jak wypożyczanie konkretnej książki przez użytkownika, wyświetlanie listy wypożyczeń dla użytkownika, przedłużanie terminu zwrotu itp.
### 4.Profil użytkownika:
Utwórz stronę profilu użytkownika, gdzie będzie można zobaczyć informacje o użytkowniku, listę wypożyczonych książek, historię wypożyczeń itp.
Umożliw użytkownikowi zmianę swoich danych osobowych, jak również zmianę hasła.
### 5.System powiadomień:
Dodaj system powiadomień, który będzie informować użytkowników o ważnych informacjach, na przykład o zbliżającym się terminie zwrotu książki, o nowych dostępnych książkach itp.
Powiadomienia można realizować na różne sposoby, na przykład poprzez wiadomości e-mail, powiadomienia push w aplikacji itp.
### 6.Wyszukiwanie książek:
Stwórz funkcję wyszukiwania książek na podstawie tytułu, autora, gatunku itp.
Umożliw użytkownikom filtrowanie i sortowanie wyników wyszukiwania.
### 7.Komunikacja w społeczności:
Dodaj funkcje komunikacji w społeczności, na przykład tablicę ogłoszeń, gdzie użytkownicy mogą zamieszczać informacje o dostępnych książkach do wypożyczenia lub zamiany.
### 8.Komentarze i oceny:
Użytkownicy będą mogli dodawać komentarze i oceny do książek, które przeczytali. Będzie to umożliwiać innym użytkownikom dowiedzenie się o opinii na temat danej książki i wyborze odpowiedniej lektury.
### 9.System rezerwacji:
Użytkownicy mogą wysłać prośbę o wypożyczenie konkretnej książki i po akceptacji właściciela będą mogli odebrać ją osobiście.
### 10.Panel administracyjny:
Dla administratorów aplikacji można stworzyć panel administracyjny, który umożliwi zarządzanie użytkownikami, książkami, raportowanie itp.