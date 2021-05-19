# Mesterseges-Intelligencia-Feladatok
## Vírusfertozésre való érzékenység modellezése Bayes-hálóval


Feladat:

Orvosbiológiai területen gyakran találkozhatunk olyan problémákkal, ahol az összefüggések bizonytalanok
az élo szervezetek komplexitása valamint a vizsgálati módszerek és a megfigyelhetőség korlátai miatt. Ilyenkor az egyik lehetséges eszköz, melyet ilyen jellegu tudás reprezentációjára használhatunk, a valószínűségi gráfos modellek osztálya. Ezek a modellek egyfelol lehetőové teszik a bizonytalan tudás ábrázolását, másrészt az így felépített összefüggések rendszerében való következtetést.

A feladat egy vírusfertozésre való érzékenységet vizsgáló Bayes-háló kialakítása és az abban való következtetés. A vírusfertozésre való érzékenységhez és egy potenciális vírusfertozés súlyosságához kötődő függőségi kapcsolatokat az alábbi modell írja le: 

- Potenciális vírusfertozés súlyosságát leíró változó ( Virus_sulyossag), amely három értéket vehet fel:
{enyhe, közepes, súlyos}.

A vírusfertozés súlyosságát három előozményváltozó befolyásolja: 
- gyenge immunrendszer (Gyenge_immun:{nem,igen})
- elhízottság (Elhizott:{nem,igen})
- életkor (Eletkor:{nem kitett,kitett})


A vírusfertozés súlyosságának következményeként többféle tünet állhat elő: 
- láz (Laz:{nem,igen})
- fájdalom (Fajdalom:{nem,igen})
– hasmenés (Hasmenes:{nem,igen})
- orrfolyás (Orrfolyas:{nem,igen})
- levertség (Levertseg:{nem,igen}).


A vírusfertozés súlyosságát egy további faktor befolyásolja, melyet a vírusfertőzésre való érzékenység csomóponttal jelölünk: (Virus_erzekeny:{nem,igen}).


A vírusfertozésre való érzékenységet számos genetikai tényezőbefolyásolja, ezek hatását két vagy háromértéku változókkal modellezzük. Tehát a ˝ Variáns-1, Variáns-2,..., Variáns-k nevu csomópontok azt jelölik, hogy egy adott genetikai tényezo milyen mértékben tekinthető rizikósnak a vírusérzékenység szempontjából {alacsony, magas} vagy {alacsony, közepes, magas}. A Variáns csomópontok száma és
struktúrája bemenetenként változó lehet.


A modellben szereplo változók közötti függőségi kapcsolatokat az 1. ábrán látható gráfstuktúra reprezentálja. A vírusfertozés súlyosságához kapcsolódó csomópontok alkotta részgráf struktúrája kötött, a vírusfertőzésre való érzékenységet reprezentáló csomóponthoz kapcsolódó genetikai variánsok alkotta részgráf viszont
egyedi minden feladatnál (eltéro számú csomópontot és éleket tartalmaz). A paraméterezést lokális feltételes valószínuségi táblák formájában a bemenet adja meg. 


A feladat a következo részekre osztható:
1. A Bayes-háló struktúrájának (aciklikus irányított gráf) kialakítása a bemenetben megadott szülo–gyermek függoségi viszonyok alapján. 
2. A Bayes-háló paraméterezésének meghatározása lokális feltételes valószínuségi táblák segítségével a bemenet alapján.
3. Evidencia változók értékének rögzítése a bemenet alapján.
4. Egzakt következtetés megvalósítása egy kijelölt célváltozóra, adott evidenciák mellett.
5. Célváltozó eloszlásának (lehetséges értékei valószínuségének) visszaadása eredményként. 


Bayes háló és a benne való következtetés megvalósítása.
