### Testing av plassering og størrelse av spillebrett
* Formål:
	* Sjekke at spillebrettet er plassert korrekt på skjermen og at man kan se hele brettet
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Kontroller at spilleskjermen er plassert nederst i hjørnet til venstre på hele skjermen. 
	* Kontroller at hele brettet er innenfor skjermen, samt at alle elementer på spillebrettet er synlig for brukeren.

### Testing av plassering og størrelse av damage token spot holders
* Formål:
	* Sjekke at spot holders for damage tokens er plassert korrekt, samt har riktig størrelse.
* Utførelse: 
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Kontroller at ti grå damage token-ikoner er plassert til høyre for spillebrettet. De skal være på lik høyde med 3.-4. rad fra bunnen av spillebrettet. 
	* Kontroller at ikonene ikke overlapper hverandre og at alle er synlige og innenfor brettet.
	* Kontroller at de er plassert slik at to ikoner står over hver spot holder for kort.
	
### Testing av plassering og spørrelse av kort spot holders
* Formål:
	* Sjekke at spot holders for kort er korrekt plassert og har riktig størrelse, samt at de er nummerert. 
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Kontroller at fem grå rammer for kort er plassert nederst til høyre på skjermen. 
	* Kontroller at ikonene ikke overlapper hverandre og at alle er synlige og innenfor brettet.
	* Kontroller at alle rammene er nummerert fra 1-5 fra høyre til venstre. Tallet skal stå under rammen.
	* Kontroller at de er plassert slik at en ramme har to damage token spot holders ovenfor rammen.

### Testing av utdeling av kort på starten av en runde
* Formål:
	* Sjekke at kortene man har å velge blant når man taster "D" vises på skjermen.
	* Sjekke at disse er plassert korrekt og er nummerert.
* Utførelse: 
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Tast "D", og kontroller at 9 kort blir synlige omtrent mitt på skjermen til høyre.
	* Kontrollert at kortene er synlige og lesbare, og at de ikke overlapper hverken hverandre eller andre objekter.
	* Kontroller at de er nummerert 1-9 fra venstre til høyre.

### Testing av utdeling av kort etter spiller har mottatt damage tokens
* Formål:
	* Sjekke at spiller mottar korrekt antall kort i henhold til hvor mye skade roboten har.
* Urførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten inn i en laser. Nå skal du ha mottat en damage token.
	* Tast så "D", og kontroller at du nå kun får utdelt 8 kort.
	* Gjenta punkt 2 og kontroller at:
		* 2 damage tokens gir 7 kort
		* 3 damage tokens gir 6 kort
		* 4 damage tokens gir 5 kort
		* 5 eller flere damage tokens gir 5 kort

### Testing av valgte kort
* Formål:
	* Sjekke at valgte kort plasseres i kort spot holder-plassen.. 
	* Sjekke at de valgte kortene er plassert i korrekt rekkefølge.
	* Sjekke at kortene man kunne velge blant etter man taster "D" forsvinner etter man har valgt kort.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Tast så "D" og velg kort 1-5. Kontroller at rekken med valgmuligheter forsvinner, og at de valge kortene plasseres i kort spot holder-plassen. 
	* Kontroller at nummeret under kortet ved velgeprosessen stemmer overens med nummeret under kort spot holder-plassen.
	
### Testing av life tokens ved oppstart
* Formål:
	* Sjekke at riktig antall life tokens tegnes opp ved oppstart av et spill, og at disse er plassert korrekt.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Kontroller at det er tre life tokens som er synlige på skjermen.
	* Kontroller at de er plassert over rammene for damage tokens, og at de ikke overlapper hverken hverandre eller andre objekter.

### Testing av life tokens ved død
* Formål:
	* Sjekke at et life token forsvinner fra skjermen dersom roboten dør.
* Utførelse: 
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene og beveg roboten til et hull.
	* Kontroller at et life token forsvinner fra skjermen, altså at det går fra 3 til 2 stykker.

### Testing av life tokens ved oppnådd maks skade
* Formål:
	* Sjekke at en life token blir fjernet fra skjermen dersom roboten har 10 damage tokens.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten inn og ut av en laser 10 ganger, slik at du mottar 10 damage tokens.
	* Kontroller at et life token fjernes fra skjermen når roboten har mottat den tiende damage token.

### Testing av life tokens ved reperasjon
* Formål:
	* Sjekke at en life token blir tegnet opp på skjermen dersom du går innom en reparasjonsstasjon og i utgangspunktet har mindre enn 3 life tokens.
	* Sjekke at det ikke tegnes opp mer enn 3 life tokens.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten til et hull, slik at man mister et liv. Da skal 2 life tokens være på skjermen.
	* Bruk så piltastene til å bevege roboten til en reparasjonsstasjon.
	* Kontroller at du får et nytt liv, og dermed har 3 life tokens. 

### Testing av damage tokens ved skade
* Formål:
	* Sjekke at man mottar en damage token ved skade. 
	* Sjekke at laser forårsaker skade.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten inn i en tile hvor det er laser.
	* Kontroller at du mottar en damage token, og at den blir tegnet opp i damage token plassholderen helt til høyre.
	* Beveg roboten ut av laseres, og deretter inn i laseren igjen.
	* Kontroller at du mottar enda en damage token, og at denne tegnes opp i neste plassholder til venstre for den første.

### Testing av damage tokens ved maks skade
* Formål:
	* Sjekke at alle damage tokens fjernes fra skjermen dersom maks antall blir oppnådd.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten inn og ut av en laser 10 ganger, slik at du mottar 10 damage tokens.
	* Kontroller at alle damage tokens fjernes fra skjermen når 10 skader blir nådd. 

### Testing av damage tokens ved tap av liv
* Formål:
	* Sjekke at antall damage tokens blir nullstilt når roboten mister et liv.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten inn i en laser, og kontroller at du mottar minst én damage token.
	* Bruk så piltastene til å bevege roboten til et hull. Kontroller at alle damage tokens fjernes fra skjermen.

### Testing av player-posisjon ved død
* Formål:
	* Sjekke at spiller spawner på riktig plass etter død. Det skal være siste check point, altså flagg, du besøkte.
* Utførelse:
	* Kjør programmet. Når menyen dukker opp på skjermen, trykk på den øverste knappen "NEW GAME".
	* Bruk piltastene til å bevege roboten til flagg 1. 
	* Beveg så roboten til et hull, slik at du dør.
	* Kontroller at roboten nå spawner på flagg 1.


