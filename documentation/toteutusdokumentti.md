# Toteutusdokumentti

Ohjelma toteuttaa kolme eri salausargoritmia: Caesar Cipher, Vigenere Cipher ja lohkosalausalgoritmin Blowfish. Caasar Cipher ja Vigenere Cipher ovat yksinkertaisempia salausmenetelmiä ja ohjelma toteuttaa myös niiden murtamisyritykset, silloin kun salausavain ei ole tiedossa. 

### Ohjelman yleisrakenne

Ohjelma koostuu seuraavista pakkauksista:

#### cryptanalysis.ui

Sisältää graafisen käyttöliittymän ja on myös ohjelman pääluokka, joka käynnistää ohjelman. Frekvenssianalyysin kaavion piirtäminen toteutetaan myös tässä luokassa. 

##### cryptanalysis.blowfish

Sisältää luokan Blowfish, joka toteuttaa Blowfish salauksen ja purkamisen. Pakkauksessa sijaitseva Boxes luokka sisältää S-boxit ja P-boxit, joita käytetään alustuksessa ja salauksessa. 

#### cryptanalysis.ciphers

Sisältää luokan Caesar Cipher ja Vigenere Cipher, jotka toteuttavat kyseiset salaukset ja niiden purkamisen.

#### cryptanalusis.dataStructures

Sisältää kaikkien omien tietorakenteiden toteutukset. Sisältää luokat MyArrayList, MyHashMap ... ..

#### cryptanalysis.cipherTest

testiluokka, joka testaa saĺauksia


### Salaus

#### Caesar Cipher

#### Vigenere Cipher

#### Blowfish

Blowfish on vuonna 1993 kehitetty erittäin vahva salausargoritmi. Blowfish on lohkosalaus ja data salataan 64 bitin eli 8 tavun lohkoissa tehden 16 kierrosta. Salausavaimen pituus voi olla 32-448 bittiä. Blowfishiä pidetään edelleen turvallisena salauksena ja se on myös erittäin nopea salausalgoritmi esimerkiksi verrattuna DES:iin.

Blowfishin pseudokoodi:

![alt text](pseudokoodi.png)

[Kasiski](https://crypto.interactive-maths.com/kasiski-analysis-breaking-the-code.html)
[Kas](https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Kasiski.html)
