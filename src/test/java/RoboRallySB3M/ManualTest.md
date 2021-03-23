### Testing av konstruksjon av brett
* Formål: 
  * Sjekke at brettet som velges konstrueres med riktige dimensjoner.
  * Sjekke at elementene på brettet er plassert korrekt. 
* Utførelse:
  * Kjør programmet, kontroller at brettet blir synlig. Ved valgt av brettet example.tmx, kontroller at brettet har bredde og høyde lik 5 ruter. 
  * Kontroller at hullet er plassert i posisjon (2,2).
  * Kontroller at flagget er plassert i posisjon (5,5).
  * Kontroller at det er plassert vegg i posisjon:
    * (2,0) mot øst i ruten
    * (1,1) mot vest og sør i ruten
    * (3,1) mot øst og sør i ruten
    * (0,2) mot nord i ruten
    * (4,2) mot sør i ruten
    * (1,3) mot vest og nord i ruten
    * (3,3) mot øst og nord i ruten
    * (2,4) mot øst i ruten

### Testing av bevegelse til Player på brett
* Formål:
  * Sjekke at spiller går i den retningen man taster
* Utførelse:
  * Kjør programmet, (player starter på posisjon (0,0). Tast så høyre piltast. Kontroller at spilleren har flyttet seg ét steg til høyre, altså fra koordinat (0,0) til (1,0). 
  * Gjenta for piltastene opp, ned og venstre. Kontroller at spilleren beveger seg i samme retning som valgt piltast. 

### Testing av interaksjon mellom vegg og Player
* Formål:
  * Sjekke at spiller ikke kan gå fra celle uten vegg til celle med vegg plassert i retning mot spiller
* Utførelse:
  * Kjør programmet og beveg spiller til et koordinat som ligger inntil en vegg, f.eks. (1,0).
  * Tast så høyre piltast, og kontroller at spilleren forblir på samme posisjon, altså posisjon (1,0).


* Formål: 
  * Sjekke at spiller kan gå fra celle uten vegg til celle med vegg som ikke er plassert i retning mot spiller
* Utførelse:
  * Kjør programmet og beveg spiller til koordinat (0,1).
  * Tast så piltast opp, og kontroller at spilleren beveger seg til (0,2).


* Formål:
  * Sjekke at spiller kan bevege seg ut av en celle med vegg når spiller beveges ut av cellen i en retning som ikke har en vegg
* Utførelse:
  * Kjør programmet og beveg spiller til (0,2)
  * Tast piltast ned, og kontroller at spiller flytter seg til (0,1)


* Formål:
  * Sjekke at spiller ikke kan bevege seg ut av en celle med vegg, når veggen er plassert i samme retning som spiller beveger seg i.
* Utførelse:
  * Kjør programmet og beveg spiller til (0,2)
  * Tast piltast opp, og kontroller at spiller forblir på samme posisjon, altså (0,2)

### Testing av interaksjon mellom hull og Player
* Formål:
  * Sjekke at spiller dør når den går over et hull
* Utførelse:
  * Kjør programmet, bruk piltastene for å bevege spiller til en posisjon ved siden av et hull, f.eks. posisjon (1,2).
  * Tast så høyre piltast slik at du beveger spiller til samme posisjon som hullet, (2,2), og kontroller at spilleren dør ved at øynene vises som røde kryss.


### Testing av utdeling av kort
* Formål:
  * Sjekke at det blir delt ut 9 kort
  * Sjekke at man kan velge 5 kort fra de 9
  * Sjekke at roboten beveger seg riktig ut ifra de 5 valgte kortene
* Utførelse:
  * Kjør programmet, trykk D (D for deal cards).
  * I terminalen blir det listet opp instruksjoner om hva man skal gjøre, og det skal bli listet opp 9 kort med informasjon om type kort og prioritetsverdi. Kontroller at dette står i terminalen.
  * Trykk på de talltastene som korresponderer til de kortene du vil ha
  * Kontroller at roboten flytter på seg automatisk etter fem kort har blitt valgt.
  * Kontroller at bevegelsene stemmer overens med bevegelsene på kortet.

### Testing av Player bevegelse ut i fra kort
Fem formål som er aktuelle for Move One, Move Two, Move Three og Back Up:
1. Fri bane, bevege spiller fra celle uten vegg til annen celle uten vegg
2. Bevege spiller fra celle uten vegg til en celle med vegg som er plassert i retning mot spiller. Veggen skal hindre bevegelse.
3. Bevege spiller fra celle uten vegg til en celle med vegg som ikke er plassert mot spiller. Veggen skal ikke hindre bevegelse.
4. Bevege spiller ut fra celle med vegg der spiller beveges ut av cellen i en retning som ikke har en vegg. Veggen skal ikke hindre bevegelse.
5. Beve spiller ut fra celle med vegg der spiller beveges ut av cellen i en retning som har vegg. Veggen skal hindre bevegelse. 

#### Move One
* Formål 1:
  * Kjør spillet, tast 1, og kontroller at spiller har flyttet seg fra (0,0) til (0,1).
* Formål 2:
  * Kjør spillet, beveg spiller til (1,0) ved å bruke piltastene.
  * Tast 1, og kontroller at spiller forblir på samme posisjon.
* Formål 3: 
  * Kjør spillet, beveg spiller til (0,1) ved å bruke piltastene.
  * Tast 1, og kontroller at spiller beveger seg til (0,2)
* Formål 4:
  * Kjør spillet, beveg spiller til (3,3) med piltastene.
  * Tast 1, og kontroller at spiller forblir på samme posisjon. 
* Formål 5:
  * Kjør spillet, beveg spiller til (1,1) med piltastene. 
  * Tast 1, og kontroller at spiller beveger seg til (1,2)

#### Move Two
* Formål 1:Denne testen er ikke mulig ved valgte bane, da det ikke går an å gå to steg frem ved å kun bruke tomme celler.
* Formål 2:
  * Kjør spillet, beveg spiller til (1,0) ved å bruke piltastene.
  * Tast 2, og kontroller at spiller forblir på samme posisjon.
* Formål 3:
  * Kjør spillet, tast 2, og kontroller at spiller beveger seg fra (0,0) til (1,3)
* Formål 4:
  * Kjør spillet, beveg spiller til (0,2) med piltastene.
  * Tast 2, og kontroller at spiller forblir på samme posisjon.
* Formål 5:
  * Kjør spillet, beveg spiller til (1,1) med piltastene.
  * Tast 2, kontroller at spiller beveger seg til (1,3)

#### Move Three
* Formål 1: Denne testen er ikke mulig ved valgte bane, da det ikke går an å gå tre steg frem ved å kun bruke tomme celler.
* Formål 2 og 3:
  * Kjør spillet, beveg spiller til (0,1) med piltastene.
  * Tast 3, og kontroller at spiller stopper opp i (0,2).
    * Får sjekket at spiller ikke kommer seg til (0,3) pga. veggen stopper den
    * Får sjekket at spiller kommer seg inn i celle (0,2) pga. veggen ikke er plassert i retning mot spiller.
* Formål 4:
  * Kjør spillet, beveg spiller til (0,2).
  * Tast 3, kontroller at spiller forblir på samme posisjon.
* Formål 5:
  * Kjør spillet, beveg spiller til (1,1).
  * Tast 3, kontroller at spiller stopper opp i (1,3).

#### Rotate Left 
* Formål: Sjekke at spiller roterer 90 grader mot venstre, ved valg av kortet Rotate Left.
  * Kjør spillet og tast 4. Kontroller at spiller har rotert 90 grader mot venstre, altså at den peker mot vest (med bena).

#### Rotate Right
* Formål: Sjekke at spiller roterer 90 grader mot høyre, ved valg at kortet Rotate Right.
  * Kjør spillet og tast 5. Kontroller at spiller har rotert 90 grader mot venstre, altså at den peker mot øst (med bena).

#### U-Turn
* Formål: Sjekke at spiller roterer 180 grader ved valg at kortet U-Turn.
  * Kjør spillet og tast 6. Kontroller at spiller har rotert 180 grader, altså at den peker mot nord (med bena)

#### Back Up
* Formål 1: 
  * Kjør spillet, beveg spiller til (0,1) med piltastene.
  * Tast 7, og kontroller at spiller flytter seg til (0,0)
* Formål 2:
  * Kjør spillet, beveg spiller til (0,3) med piltastene.
  * Tast 7, og kontroller at spiller forblir på samme posisjon.
* Formål 3: 
  * Kjør spillet, beveg spiller til (1,2) med piltastene.
  * Tast 7, og kontroller at spiller beveger seg til (1,1)
* Formål 4:
  * Kjør spillet, beveg spiller til (1,1) med piltastene.
  * Tast 7, og kontroller at spiller forblir på samme posisjon.
* Formål 5:
  * Kjør spillet, beveg spiller til (0,2) med piltastene.
  * Tast 7, og kontroller at spiller beveger seg til (0,1).



