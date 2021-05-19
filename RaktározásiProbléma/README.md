# Mesterseges-Intelligencia-Feladatok
## Raktározási probléma

Legyen adott egy 𝑷 ∈ 𝑁
𝐿×𝑊 mátrix, amely a raktárban elhelyezett raklapok pozícióját
tartalmazza. Legyen adott továbbá V= {(𝑙𝑖
, 𝑤𝑖)}𝑖=1𝑁 , raklapok halmaza, ahol li az i.
raklap hossza, wi pedig az i. raklap szélessége, illetve egy O = {(𝑗𝑖
, 𝑘𝑖
)}𝑖=1
𝑀 oszlopok
halmaza, melyek a raktár celláinak határánál elhelyezkedő tartóoszlopokat jelölik, ahol
j a hosszanti, k a szélességi koordináta a P mátrix bal-felső sarkától indexelve.


Célunk, hogy az összes V-beli csomagot elhelyezzük a P mátrixban. A raklapok
elforgatása megengedett, sőt, a legtöbb esetben szükséges. Egy tartóoszlop csak a
raklapok szélénél és sarkánál helyezkedhet el, nem mehet keresztül azon. Példa egy
hibás megoldásra:


A problémáról egyébként belátható, hogy NP-nehéz, ám nagy méret esetén is léteznek
hatékony algoritmusok, amelyek megoldást találnak.


A feladat megvalósítása java nyelven.
