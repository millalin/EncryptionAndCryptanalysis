# Toteutusdokumentti

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



[Kasiski](https://crypto.interactive-maths.com/kasiski-analysis-breaking-the-code.html)
[Kas](https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Kasiski.html)
