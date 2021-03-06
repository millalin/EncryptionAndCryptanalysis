# Määrittelydokumentti

## Ratkaistava ongelma

### Salaus 

Ohjelmassa tarkoituksena on käyttää salausta eli kryptausta, jossa viesti salataan salatekstiksi (ciphertext) eri salausalgoritmejä käyttäen. Tarkoituksena on selvittää miten näitä salauksia voidaan murtaa tietämättä avainta. 

### Salauksen murtaminen ja kryptoanalyysi

Tarkoituksena on yrittää murtaa/purkaa eri salausmenetelmiä sekä tehdä ainakin merkkien frekvenssianalyysi, jonka avulla salattuja viestejä yritetään analysoida. Vigenere Cipherin murtamisessa käytetään hyväksi myös esimerkiksi Kasiskin metodia.  


### Toteutettavat algoritmit ja tietorakenteet

Toteutetaan tunnettuja salausmenetelmiä aloittaen yksinkertaisesta Caesar salakirjoituksesta [Caesar Cipher](https://en.wikipedia.org/wiki/Caesar_cipher). Lisäksi toteutetaan ainakin Vigenèren salaus [Vigenère cipher](https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher#Frequency_analysis). Tarkoitus on toteuttaa myös [Blowfish](https://fi.wikipedia.org/wiki/Blowfish) salakirjoitusalgoritmi, joka on kehitetty syrjäyttämään DES. 

Lisäksi toteutetaan [frekvenssianalyysi](https://learncryptography.com/attack-vectors/frequency-analysis), jota käytetään salakirjoituksen purkamiseen yksinkertaisessa korvaussalakirjoituksessa. Toteutus tehdään englannin kielellä. [Kasinskin menetelmää](https://en.wikipedia.org/wiki/Kasiski_examination) käytetään salausavaimen pituuden selvittämiseen Vigenère cipherin murtamisessa. 

Salausalgoritmit toteutetaan taulukoita käyttäen. Salauksen murtamisessa käytetään hajautustaulua ja taulukkolistaa. 

#### Valinnat

Valinnat on tehty sen pohjalta, että halutaan nähdä, miten yksinkertaisempia salakirjoituksia on mahdollista murtaa ja myös miten ne toteutetaan. Caesar cipherin salakirjoitus on varmastikin kohtuu yksinkertainen toteuttaa. Myös vigenere on kohtuullisen yksinkertainen mutta sen murtaminen ja ajankäyttö siinä vaikuttaa kiinnostavalta. Etenkin kiinnostaa myös monimutkaisempien salakirjoitusten mahdollinen murtaminen.  

#### Mitä syötteitä saa ja miten käytetään

Tarkoituksena on, että salattava tieto annetaan tekstimuodossa (String). Tämä muutetaan tavuiksi ja käsitellään tavuina tai bitteinä.  

### Tavoitteena olevat aika- ja tilavaativuudet

Salausalgoritmien luomisen ja salaamisen aikavaativuus on yleensä lineaarinen. Encrypt ja decrypt aikavaativuudet ovat siis O(n), jossa n on tekstin pituus.

Ceasar cipherissä joka kirjainta siirretään vakio x määrän, joka tuottaa vain 26 mahdollista yhdistelmää. Vigenère cipher on moniaakkosellinen salakirjoitus, jossa siirto on erilainen joka merkillä. Tällöin arvauksien määrä kasvaa eksponentiaalisesti. Vigeneren murtoa voidaan tehostaa Kasiskin menetelmän ja frekvenssianalyysin avulla. Salauksen murtamisaika riippuu tekstin pituudesta. Toisaalta murtaminen muuttuu todennäköisemmäksi esimerkiksi Vigeneressä, mitä pidempi teksti on kyseessä suhteessa avaimen pituuteen. Vigeneren salauksen murtamisessa selvitettäessä avaimen pituutta käytetään HashMapia, jonka haun keskimääräinen aikavaativuus on 0(1) sekä taulukkolistaa. Syöte käydään läpi muodostaen toistuvia kirjainyhdistelmiä, joka vie ajan O(n).    

### Lähteet      

Muita ylläolevien linkkien lähteiden lisäksi on käytetty lähteitä:

https://en.wikipedia.org/wiki/Encryption

https://en.wikipedia.org/wiki/Cryptography

https://crypto.interactive-maths.com/introduction-to-cryptography.html

http://practicalcryptography.com/ciphers/caesar-cipher/

 
