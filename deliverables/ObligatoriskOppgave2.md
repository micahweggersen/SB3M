### Roller
Vi har valgt å fortsette med de rollene vi lagde sist. Rollene har fungert bra, og ingen på teamet ønsker noe endring her. I tillegg syns vi rollene vi har til nå er tilstrekkelige, og derfor har vi bestemt oss for å ikke dele ut nye ansvarsområder. Vi på teamet er fortsatt veldig fleksible, og foretrekker å ta på oss ansvar der det trengs, fremfor å la bestemte roller sette grenser for hva vi kan gjøre. 

### Prosjektmetodikk
Til å starte med ønsket vi å følge metodikken kanban, men vi endte med en metodikk basert på tillit og tilgjengelighet, men kanban forblir fortsatt grunnlaget for vår metodikk. Dette ønsker vi å fortsette med da det fungerer godt for oss. Vi passer fortsatt på at ingen jobber med flere oppgaver enn de har kapasitet til, og bruker project boardet på trello og parprogrammering flittig. Hittil har denne metodikken fungert bra for teamet. Det er klart og tydelig hvilke oppgaver vi har foran oss, og hvem som jobber med hva til enhver tid. Her er vårt project board: https://trello.com/b/dbMlsUpA/roborally 

### Gruppedynamikk og kommunikasjon
Dynamikken i gruppen er god, alle blir hørt og respektert. Vi kjenner hver vår plass i gruppen og hvilke arbeidsoppgaver vi selv har. Disse jobber vi så med hver for oss, eller sammen dersom oppgaven er krevende. Vi kommuniserer godt på møtene, da vi rapporterer til hverandre, stiller spørsmål, og tar opp eventuelle saker. 

### Vektlegging av commits
Fordelingen av commits er jevnere fordelt denne gangen enn sist, men vi benytter oss fortsatt mye av par-programmering, både fysisk og via code with me, som kan ha bidratt til en skjevfordeling av commits. 

Vi har valgt å beholde de forskjellige branch-ene våre, slik at det er lettere å se hvordan vi har jobbet. Planen er å fjerne disse etter hvert. Vi har hatt problemer med git med at git forteller at en commit ikke ble pushet, men så har den blitt pushet, som forklarer hvorfor noen commits har blitt pushet flere ganger. I tillegg har Malin slitt med å pushe på grunn av tekniske problemer, men hun har vært med på å kode via code with me. 

### Referater
Alle referatene ligger på vår google drive: https://drive.google.com/drive/folders/1VpaGimflcbXHnR0sDl15mE1TTSsk2_fC?usp=sharing 

### Retrospektiv frem til nå
Frem til nå synes vi at vi har vært flinke til å ha gjort ting i god tid i forveien, alle har bidratt både til arbeidet og på møtene, og vi har klart å holde orden i prosjektet. 

Noen nye forbedringspunkter vi har satt oss er at vi kan bli bedre på å lage tester til koden, samt å bruke git. Vi ønsker å forbedre oss på testing grunnet tilbakemeldingen vi fikk på forrige obligatoriske oppgave. I tillegg kan vi forbedre oss på å kommunisere utenom når vi har møter. Vi ønsker også å planlegge tiden vi har litt bedre til neste gang, slik at arbeidsmengden blir så jevnt fordelt som mulig. 

### Brukerhistorier i prioritert rekkefølge
1. Som spiller ønsker jeg at spillet deler ut riktig mengde kort til meg, slik at jeg kan spille etter spillereglene for kort
 * Akseptansekriterier:
  	* Gitt at det er starten på en ny runde, skal kortene deles ut og bli synlige for spillerne.
  	* Gitt at jeg har en skade, skal antall kort utdelt til meg være mindre enn om jeg ikke har en skade (ikke implementert enda)
  	* Gitt at jeg har x antall skader, skal jeg få tildelt x antall færre kort enn ni kort. (ikke implementert enda)
 * Arbeidsoppgaver:
  	* GUI for kort ***
  	* Antallslogikk for utdeling
  	* Utdelingslogikk
 
2. Som spiller ønsker jeg at jeg kan velge og bestemme rekkefølge på kortene som jeg vil, for å lage best mulig strategi for å vinne
 * Akseptansekriterier:
  	* Gitt at jeg skal til å velge kort, må jeg velge antall lovlige kort. (ikke implementert enda)
  	* Gitt at jeg har fem kort så skal jeg velge rekkefølgen de har. 
 * Arbeidsoppgaver
  	* Skadelogikk 
  	* Velge riktig kort
  	* GUI for kort***
 
3. Som spiller ønsker jeg at roboten skal bevege seg etter bevegelsen beskrevet på kortet, og etter rekkefølgen jeg som spiller valgte ut fra utdelte kort, for å bevege roboten nærmest mulig ønsket posisjon og unngå hinder på brettet. 
 * Akseptansekriterier:
  	* Gitt at jeg har valgt et kort, skal roboten bevege seg ut i fra informasjonen på kortet.
 * Arbeidsoppgaver:
  	* Bevegelses validering
  	* Retningsverdi
 	 * Rekkefølge valg

4. Som spiller ønsker jeg et hinder som gjør at jeg bare kan gå i et bestemt område slik at det er bevegelsesrestriksjoner.
 * Akseptansekriterier:
  	* Gitt at jeg beveger meg mot en vegg så skal jeg ikke bevege roboten lengre enn til veggen.
 * Arbeidsoppgaver
  	* Bevegelses validering
 
5. Som spiller ønsker jeg å kunne spille mot andre på ulike maskiner, slik at det er konkurranse og jeg ikke spiller mot meg selv (ikke implementert enda)
 * Akseptansekriterier:
  	* Gitt at jeg spiller mot noen andre, skal de kunne spille på en annen maskin enn meg
 * Arbeidsoppgaver:
 	 * Server
	  	* Kommentar til server - Denne ble jobbet endel med men vi møtte på noen utfordringer som ikke lot seg løses før leveringsfristen og er derfor ikke en del av implementasjonen.

### Arbeidsfordeling
Vi fordelte oss i grupper og begynte med de oppgavene vi mente var viktigst å bli ferdig med først, og jobbet oss nedover listen. Vi prioriterte oppgavene i en rekkefølge slik at vi får best mulig arbeidsflyt. Både for at det skal gå an å dele ut kort, og bevege spilleren ut ifra kortene, er det nødvendig å ha de forskjellige kort-klassene opprettet og velfungerende. Derfor implementerte vi dem først slik at vi skulle slippe å stoppe midt i en annen oppgave og gå tilbake til det.

Beatrix, Maria og Malin begynte med oppgaven å lage klassene vi trenger for kort, etter hvert hjalp hele gruppa til med denne oppgaven. Malin skulle også tegne kortene slik at vi kan ha dem visuelt på brettene også. Micah fikk oppgaven å opprette en server slik at vi etter hvert kan spille fra flere maskiner. Sondre fikk oppgaven å bevege spilleren ut ifra kortene og håndtering av hinder (walls) på brettet. 

### Testing
Da vi bruker Gmx biblioteket så har vi måtte testet mye manuelt.
Vi har manuelt testet funksjonalitet som angår grafikk. Disse ligger i ManualTest.md, som du finner sammen med de andre testene.
Vi har lagd spesielle brett etter behov for å gjøre testingen, og lagd alle kombinasjoner av celler med forskjellig innhold. 
Vi har dekket alle mulige kombinasjoner av celler. 



### Kjente bugs
Det er mulig å gå forbi hull når spiller utfører bevegelsene til et kort. 
Etter man har valgt fem kort, utføres disse i ét steg, slik at man ikke kan se aksjonen til hvert enkelt kort. Vi jobber med å få til stegvis utførelse av kort.
Det er mulig å gå utenfor brettet. Denne buggen har blitt nedprioritert, da vi har valgt å prioritere stegvis utførelse av kort.
Ved ny runde når man trykker “D”, blir et nytt deck laget hver gang, istedenfor å bruke kortene i et og samme deck til det går tom for kort.
