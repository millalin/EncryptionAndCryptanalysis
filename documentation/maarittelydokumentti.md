# Määrittelydokumentti

## Ratkaistava ongelma

### Salaus 

Ohjelmassa tarkoituksena on käyttää salausta eli kryptausta, jossa viesti salataan salatekstiksi (ciphertext) eri salausalgoritmejä käyttäen.

### Salauksen murtaminen ja kryptoanalyysi

Tarkoituksena on yrittää murtaa/purkaa eri salausmenetelmiä sekä tehdä ainakin merkkien frekvenssianalyysi, jonka avulla salattuja viestejä yritetään analysoida. 


### Toteutettavat algoritmit ja tietorakenteet

Toteutetaan tunnettuja salausmenetelmiä aloittaen yksinkertaisesta Caesar salakirjoituksesta [Caesar Cipher](https://en.wikipedia.org/wiki/Caesar_cipher). Lisäksi toteutetaan ainakin Vigenèren salaus [Vigenère cipher] (https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher#Frequency_analysis). Tarkoitus on toteuttaa myös [Blowfish](https://fi.wikipedia.org/wiki/Blowfish) salakirjoitusalgoritmi, joka on kehitetty syrjäyttämään DES. 

Lisäksi toteutetaan [frekvenssianalyysi](https://learncryptography.com/attack-vectors/frequency-analysis), jota käytetään salakirjoituksen purkamiseen yksinkertaisessa korvaussalakirjoituksessa. Toteutus tehdään englannin kielellä. [Kasinskin menetelmää](https://en.wikipedia.org/wiki/Kasiski_examination) käytetään salausavaimen pituuden selvittämiseen Vigenère cipherin murtamisessa. 

Salausalgoritmit toteutetaan taulukoita käyttäen. Salauksen purkamisessa tulee luultavasti käyttää ainakin hajautustaulua. 

#### Valinnat

Valinnat on tehty sen pohjalta, että halutaan nähdä, miten yksinkertaisempia salakirjoituksia on mahdollista murtaa ja myös miten ne toteutetaan. Caesar cipherin salakirjoitus on varmastikin kohtuu yksinkertainen toteuttaa, mutta sen murtaminen ja ajankäyttö siinä vaikuttaa kiinnostavalta. Etenkin kiinnostaa myös monimutkaisempien salakirjoitusten mahdollinen murtaminen.  

#### Mitä syötteitä saa ja miten käytetään

Tarkoituksena on, että salattava tieto annetaan tekstimuodossa (String). 

### Tavoitteena olevat aika- ja tilavaativuudet

Salausalgoritmien luominen tapahtuu erittäin nopeasti vakioajassa. 

Ceasar cipherissä joka kirjainta siirretään vakio x määrän, joka tuottaa vain 26 mahdollista yhdistelmää. Vigenère cipher on moniaakkosellinen salakirjoitus, jossa siirto on erilainen joka merkillä. Tällöin arvauksien määrä kasvaa eksponentiaalisesti. Aikavaativuus murtoon riippuu avaimen pituudesta. Avaimen pituuden ollessa k, aikavaativuus on o(26^k). Avaimen pituuden selvittämisellä voidaan tehostaa Vigeneren murtoa. 

### Lähteet      
