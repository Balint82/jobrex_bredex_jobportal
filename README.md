# jobrex_bredex_jobportal
Backend testwork


Windows OS
jdk-11
Maven
H2
Swagger

Álláskereső alkalmazás

Ez a projekt egy Java 11-ben írt álláskereső alkalmazás, melyet a Spring framework segítségével valósítottunk meg. Az alkalmazás egy inmemory adatbázist vagy file alapú megoldást használ az adatok tárolására, beleértve a kezdeti adatfeltöltést is.
Követelmények:

    Kliensalkalmazás regisztrációja (POST /client):
        Kliensek regisztrációja névvel és érvényes e-mail címmel. Szerver visszaad egy UUID API kulcsot.

    Állások létrehozása (POST /position):
        Állások létrehozása megnevezéssel és helyszínnel. Szerver ellenőrzi az API kulcsot.

    Állások keresése (GET /position/search):
        Keresés kulcsszóval és helyszínnel. Szerver visszaadja a találatok URL listáját.

    Keresési eredmények megnyitása (GET /position/id):
        Álláshirdetés részleteinek megtekintése.

    Validációs hibák kezelése:
        Egységes hibatípus visszaadása, részletezve a hibás mezőket.



Job Search Application

This project is a job search application written in Java 11 using the Spring framework. The application uses an inmemory database or file-based solution for data storage, including initial data seeding.
Requirements:

    Client Registration (POST /client):
        Clients register with a name and valid email address. Server returns a UUID API key.

    Create Job Positions (POST /position):
        Create job positions with title and location. Server verifies API key.

    Search Job Positions (GET /position/search):
        Search by keyword and location. Server returns a list of URLs for matched postings.

    Open Search Results (GET /position/id):
        View details of a job posting.

    Handling Validation Errors:
        Unified error type returned, detailing fields with validation errors.


