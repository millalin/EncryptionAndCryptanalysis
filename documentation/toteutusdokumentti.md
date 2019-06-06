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

uint32_t P[18];
uint32_t S[4][256];

uint32_t f (uint32_t x) {
   uint32_t h = S[0][x >> 24] + S[1][x >> 16 & 0xff];
   return ( h ^ S[2][x >> 8 & 0xff] ) + S[3][x & 0xff];
}

void encrypt (uint32_t & L, uint32_t & R) {
   for (int i=0 ; i<16 ; i += 2) {
      L ^= P[i];
      R ^= f(L);
      R ^= P[i+1];
      L ^= f(R);
   }
   L ^= P[16];
   R ^= P[17];
   swap (L, R);
}

void decrypt (uint32_t & L, uint32_t & R) {
   for (int i=16 ; i > 0 ; i -= 2) {
      L ^= P[i+1];
      R ^= f(L);
      R ^= P[i];
      L ^= f(R);
   }
   L ^= P[1];
   R ^= P[0];
   swap (L, R);
}

  // ...
  // initializing the P-array and S-boxes with values derived from pi; omitted in the example
  // ...
{
   for (int i=0 ; i<18 ; ++i)
      P[i] ^= key[i % keylen];
   uint32_t L = 0, R = 0;
   for (int i=0 ; i<18 ; i+=2) {
      encrypt (L, R);
      P[i] = L; P[i+1] = R;
   }
   for (int i=0 ; i<4 ; ++i)
      for (int j=0 ; j<256; j+=2) {
         encrypt (L, R);
         S[i][j] = L; S[i][j+1] = R;
      }
}


[Kasiski](https://crypto.interactive-maths.com/kasiski-analysis-breaking-the-code.html)
[Kas](https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Kasiski.html)
