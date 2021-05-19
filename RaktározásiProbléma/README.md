# Mesterseges-Intelligencia-Feladatok
MI feladatok

Legyen adott egy 𝑷 ∈ 𝑁
𝐿×𝑊 mátrix, amely a raktárban elhelyezett raklapok pozícióját
tartalmazza. Legyen adott továbbá V= {(𝑙𝑖
, 𝑤𝑖
)}𝑖=1
𝑁 , raklapok halmaza, ahol li az i.
raklap hossza, wi pedig az i. raklap szélessége, illetve egy O = {(𝑗𝑖
, 𝑘𝑖
)}𝑖=1
𝑀 oszlopok
halmaza, melyek a raktár celláinak határánál elhelyezkedő tartóoszlopokat jelölik, ahol
j a hosszanti, k a szélességi koordináta a P mátrix bal-felső sarkától indexelve.
Például egy 5x7 méretű raktár és
V = {(4,2),(3,2),(1,2),(2,5),(2,2),(2,1),(3,1)},
O = {(2,2),(3,4)}
raklapok esetén például egy lehetséges allokáció:
1 1 1 1 4 4 6
1 1 1 1 4 4 6
2 2 3 3 4 4 7
2 2 5 5 4 4 7
2 2 5 5 4 4 7
Célunk, hogy az összes V-beli csomagot elhelyezzük a P mátrixban. A raklapok
elforgatása megengedett, sőt, a legtöbb esetben szükséges. Egy tartóoszlop csak a
raklapok szélénél és sarkánál helyezkedhet el, nem mehet keresztül azon. Példa egy
hibás megoldásra:
1 2 2
1 2 2
 P =
A problémáról egyébként belátható, hogy NP-nehéz, ám nagy méret esetén is léteznek
hatékony algoritmusok, amelyek megoldást találnak.
2. Feladat
Valósítsa meg a raktár feltöltését Java vagy 
