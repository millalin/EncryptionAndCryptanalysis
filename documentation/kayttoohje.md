## Käyttöohje

Lataa tiedosto xxxxx.jar

### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

    java -jar EncryptionAndCryptanalysis.jar

### Alkunäkymä

Alkunäkymässä voi valita, minkä salauksen haluaa toteuttaa ja haluaako kirjoittaa tekstiä vai salata valmiin tekstitiedoston. 

![alt text](./pics/kayttoohje_start.png)

### Komentorivikomennot

Testit voidaan suorittaa komennolla

    mvn test

Testikattavuusraportti luodaan komennolla

    mvn jacoco:report

Kattavuusraportti on tarkasteltavissa selaimella avaamalla tiedosto sijainnista target/site/jacoco/index.html


### Salaukset

Caesar salauksessa tulee valita salausavain (numero) valikosta väliltä 1-26. Tämän jälkeen haluttu teksti voidaan salata tai purkaa. Options napista saa salatusta tekstistä näkyviin kaikki mahdolliset vaihtoehdot eri salausavaimilla. Frekvenssinapista on nähtävissä salatun tekstin aakkosten jakauma. Break napista saa näkyviin ohjelman laskeman arvauksen salatussa tekstissä käytetystä salausavaimesta.

![alt text](./pics/kayttoohje_caesar.png)

Vigenere salauksessa ylälaatikkoon tulee kirjoittaa salauksessa tai sen avaamisessa käytettävä salausavain. Salattu testi voidaan tämän jälkeen salata tai yrittää purkaa. Salatusta tekstistä voidaan etsiä todennäköisintä salausavaimen pituutta ja ehdotettua salausavainta. 

![alt text](./pics/kayttoohje_vigenere.png)
