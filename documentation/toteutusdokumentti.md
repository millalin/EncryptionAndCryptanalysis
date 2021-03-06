# Toteutusdokumentti

Ohjelma toteuttaa kolme eri salausargoritmia: Caesar Cipher, Vigenere Cipher ja lohkosalausalgoritmin Blowfish. Caesar Cipher ja Vigenere Cipher ovat yksinkertaisempia salausmenetelmiä ja ohjelma toteuttaa myös niiden murtamisyritykset, silloin kun salausavain ei ole tiedossa. 

JavaDoc generoidaan komennolla

    mvn javadoc:javadoc

Javadocia voi tarkastella avaamalla selaimella tiedosto sijainnista target/site/apidocs/index.html

[Generoitu JavaDoc](./apidocs)

### Ohjelman yleisrakenne

Ohjelma koostuu seuraavista pakkauksista:

#### cryptanalysis.ui

Sisältää graafisen käyttöliittymän ja on myös ohjelman pääluokka, joka käynnistää ohjelman. Frekvenssianalyysin kaavion piirtäminen toteutetaan myös tässä luokassa. 

#### cryptanalysis.blowfish

Sisältää luokan [Blowfish.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/blowfish/Blowfish.java), joka toteuttaa Blowfish salauksen ja purkamisen. Pakkauksessa sijaitseva Boxes luokka sisältää S-boxit ja P-boxit, joita käytetään alustuksessa ja salauksessa. 

Sisältää myös luokan TestJavaOwnBlowfish.java, jota ei käytetä varsinaisessa ohjelmassa ja sisältää Javan valmiin Blowfish toteutuksen testejä. Luokkaa on käytetty ainoastaan verrattaessa omaa Blowfish toteutusta Javan valmiiseen toteutukseen.

#### cryptanalysis.ciphers

Sisältää luokan [CaesarCipher.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/ciphers/CaesarCipher.java) ja [VigenereCipher.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/ciphers/VigenereCipher.java), jotka toteuttavat kyseiset salaukset ja niiden purkamisen.


#### cryptanalysis.breaking

Sisältää luokat [BreakingCaesarCipher.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/breaking/BreakingCaesarCipher.java), [BreakingVigenereCipher.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/breaking/BreakingVigenereCipher.java) ja [FrequencyAnalysis.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/breaking/FrequencyAnalysis.java). Näissä yritetään murtaa salauksia tietämättä salausavainta.

#### cryptanalusis.datastructures

Sisältää kaikkien omien tietorakenteiden toteutukset. Sisältää luokat [MyArrayList.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/datastructures/MyArrayList.java) ja [MyHashMap.java](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/EncryptionCryptAnalysis/src/main/java/cryptanalysis/datastructures/MyHashMap.java) sekä KeyValue.java. 

#### cryptanalysis.cipherTest

Testiluokka, joka testaa Blowfish salausta, Caesar salausta ja Vigenere salausta.

#### cryptanalysis.datastructuresTest

Sisältää luokan DatastructureTest.java joka testaa omien tietorakenteiden oikein toimivuutta.  

### Salaus

#### Caesar Cipher

Caesar salakirjoitus on yksinkertainen salausmenetelmä, jossa jokainen salattava kirjain korvataan aakkosissa aina sovitun siirtomäärän jälkeen tulevalla kirjaimella. Aakkoston päättyessä siirto jatkuu aakkosten alusta. Caesar Cipher voidaan murtaa esimerkiksi käyttäen hyväksi frekvenssianalyysiä. 

#### Vigenere Cipher

Vigenere salaus on moniaakkosellinen salaus ja se käyttää useampaa salaus-aakkosta tiedon salaamiseen. Avainsana määrittää, mitä kirjaimia siirretään eteenpäin minkäkin verran. Avainsanan kirjaimet muutetaan siirtonumeroksi, jonka jälkeen tektiä käydään läpi muuttaen sitä eri siirroilla. Murtoa ei voida toteuttaa pelkällä kirjainten frekvenssianalyysillä. Esimerkiksi Kasiskin menetelmällä voidaan selvittää avainsanan pituus ja muodostaa erikseen eri osioista frekvenssianalyysin avulla arvaus avaimesta. 

#### Blowfish

Blowfish on vuonna 1993 kehitetty erittäin vahva salausargoritmi. Blowfish on lohkosalaus ja data salataan 64 bitin eli 8 tavun lohkoissa tehden 16 kierrosta. Salausavaimen pituus voi olla 32-448 bittiä. Blowfishiä pidetään edelleen turvallisena salauksena, etenkin käytettäessä pidempiä salausavaimia, ja se on myös erittäin nopea salausalgoritmi esimerkiksi verrattuna DES:iin.

Blowfishin pseudokoodi:

![alt text](./pics/pseudokoodi.png)

### Salausten toteutus

Caesar salaus salaa tekstin, jossa on käytössä englannin kielen aakkosia. Se ei muuta muita merkkejä salauksessa, joten ne pysyvät salatessa ja purkaessa ennallaan. Tämä johtuu siitä, että Caesarin alkuperäisessä salauksessa salausavaimia on 26 ja salausta suoritetaan vain perustekstille. Myös vigenere cipher salaa vain kirjaimet ja jättää erikoismerkit ennalleen. Se käyttää avaimen kirjaimista muodostamiaan siirtoja samoin kuin Caesar. Molemmat salaukset purkavat oikealla avaimella tekstit takaisin oikeaan muotoonsa, myös erikoismerkit. Vigeneren avainsanassa tulee käyttää vain merkkejä a-z tai A-Z tai avaus ei toimi oikein. Myöskään välilyöntiä ei tule käyttää. 

Murroissa yritetään saada selville salauksessa käytetty avain ja yksinkertaisen Caesarin tapauksessa tämä onnistuu myös käyden läpi 26 eri vaihtoehtoa. Nopeampi murto saavutetaan käyttämällä hyväksi frekvenssianalyysiä. Frekvenssianalyysiä käytettäessä tekstin tulee olla tarpeeksi pitkä ja noudattaa englannin kielen todennäköisyyksiä, jotta sen onnistuu murtaa oikea avain. Vigeneren murrossa toimii parhaiten teksti, jossa ei ole välilyöntejä, sillä murrossa tunnistetaan 3 kirjaimen yhdistelmiä, jolloin niitä saadaan näin enemmän. Toisaalta tällöin vaikuttaa eri sanojen yhdistelmät tekstissä. 

Blowfish salaus salaa tekstin monipuolisemmin 64 bitin lohkoissa. Se ottaa huomioon ASCII merkistön merkit ja salaa ja purkaa jokaisen merkin. Blowfish toteutus salaa tekstin heksadesimaalimuotoon. Tutkittaessa salauksia, Blowfish toteutus salaa tarvittavat kierrokset ja tekee alustukset S-boxeille ja P-boxille. Salauksen pituus eroaa isoissa, erikoisissa tekstitiedostoissa Javan toteutuksen salauksen pituudesta. Lyhyillä teksteillä salauksien pituudet ovat samat. Myös salattu teksti eroaa Javan toteutuksesta. Blowfish toteutus purkaa salatun tekstin oikein, silloin kun sillä on oikea salausavain. Salauksessa ja avauksessa teksti käydään läpi kerran.  

### Saavutetut aikavaativuudet

Kaikki 3 salausta toimivat lineaarisessa ajassa O(n) sekä salatessa, että purkaessa salausta. Salauksissa syöte käydään kerran läpi salaten teksti. Caesar Cipher käy läpi salattavan syötteen ja salaa sen samalla salausavaimella (numerolla). Vigenere Cipher muodostaa annetusta avaimesta ensin tekstin mittaisen avaimen ja käy sen jälkeen salattavan tekstin läpi muodostaen joka avaimen kirjaimen kohdalle sen vaatiman määrän siirtoa merkille. Blowfish salaus salaa syötteen 64 bitin lohkoissa tehden joka salausta 16 kierrosta. Salauksen avaus on Blowfishissa sama toiminto vastakkaiseen suuntaan. 

Erot avainten pituudessa eivät vaikuttaneet merkittävästi salausaikaan. Vigenere salauksen murrossa joudutaan käymään teksti läpi useaan kertaan, kun tekstistä ensin etsitään yhteisiä tekijöitä ja niitä käydään läpi uudelleen. Murtoa saattaa myös hidastaa oma HashMap toteutus. Salausten ja niiden purkamisen, sekä murtojen ja murtoyritysten toteutuneita aikoja voi nähdä [testausdokumentin](https://github.com/millalin/EncryptionAndCryptanalysis/blob/master/documentation/testausdokumentti.md) suorituskykytestaus osiossa.


### Lähteet

https://www.schneier.com/academic/blowfish/download.html
 
https://en.wikipedia.org/wiki/Blowfish_(cipher)

https://en.wikipedia.org/wiki/Caesar_cipher

https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher

https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Kasiski.html

https://crypto.interactive-maths.com/

